package http.client;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

public class JettyMClient {
	private AtomicLong nextId = new AtomicLong();

	private ConcurrentHashMap<Integer, String> contentMap = new ConcurrentHashMap<Integer, String>();
	private ConcurrentHashMap<Integer, Thread> threadMap = new ConcurrentHashMap<Integer, Thread>();

	public void storeContent(Integer contentId, String content) {
		contentMap.put(contentId, content);
	}

	public void removeContent(Integer contentId) {
		contentMap.remove(contentId);
	}

	public void startTimer(int id, Runnable task) {
		threadMap.put(id, new Thread(task));
		threadMap.get(id).start();
	}

	public void cancelTimer(int id) {
		threadMap.get(id).interrupt();
	}

	String drainToString(Queue<Byte> queue) throws Exception {
		byte[] bytes = new byte[queue.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = queue.poll();
		}
		return new String(bytes, UTF_8);
	}

	public Integer mClient(String endpoint, HttpMethod method, String data) throws Exception {
		final Integer contentId = (int) nextId.getAndIncrement();
		storeContent(contentId, data);

		// start timeout thread
		startTimer(contentId, new Runnable() {
			@Override
			public void run() {
				System.out.println("REPORT ID : " + contentId);
				try {
					mClientRunner(contentId, endpoint, method, data);
				} catch (Exception e) {
					System.out.println("Timeout Canceled - " + contentId);
					try {
						throw e;
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					// try {
					// throw new Exception(e.getCause());
					// } catch (Exception e1) {
					// e1.printStackTrace();
					// }
				}
			}
		});

		return contentId;
	}

	public Integer mClientRunner(Integer contentId, String endpoint, HttpMethod method, String data) throws Exception {
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectTimeout(5_000);
		httpClient.setIdleTimeout(5_000);
		httpClient.start();
		ExecutorService executor = Executors.newFixedThreadPool(1);

		LinkedBlockingQueue<Byte> responses = new LinkedBlockingQueue<>();
		LinkedBlockingQueue<String> txtData = new LinkedBlockingQueue<>();
		CompletableFuture<Result> promise = new CompletableFuture<>();

		StringBuilder group = new StringBuilder(); // test
		StringBuilder responseBody = new StringBuilder(); // test

		executor.submit(() -> {
			Request request = httpClient.newRequest(endpoint).method(method).onResponseHeaders(response -> {
				for (HttpField header : response.getHeaders())
					group.append(contentId + " < " + header + "\n");
			}).onResponseContent((response, content) -> {
				while (content.hasRemaining()) {
					try {
						responses.put(content.get());

					} catch (Exception e) {
						promise.completeExceptionally(e);
					}
				}
			}).onResponseSuccess(theResponse -> {
				try {
					txtData.put(new String(""));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(contentId + " << " + responseBody);
			});
			request.header(HttpHeader.CONTENT_TYPE, "application/json");
			request.content(new StringContentProvider(data, "utf-8"));
			// ´Ü¼ø response
			// ContentResponse contentRes = request.send();
			// System.out.println(contentRes.getContentAsString());
			// promise
			request.send((result) -> {
				promise.complete(result);
			});
			return true;
		});

		if (executor.awaitTermination(20, TimeUnit.SECONDS)) {
			System.out.println(" job are terminated");
			executor.shutdown();
			httpClient.stop();
			throw new TimeoutException();
		} else {
			System.out.println("job are completed");

			promise.isDone();
			promise.get().isSucceeded();
			responses.size();
			txtData.take();
			String responseData = drainToString(responses);
			storeContent(contentId, responseData);

			executor.shutdown();
			httpClient.stop();
		}

		return contentId;
	}
}
