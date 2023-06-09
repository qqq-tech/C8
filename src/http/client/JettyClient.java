package http.client;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

public class JettyClient {

	static String drainToString(Queue<Byte> queue) throws Exception {
		byte[] bytes = new byte[queue.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = queue.poll();
		}
		return new String(bytes, UTF_8);
	}

	public void mClient(String endpoint, HttpMethod method, String data) throws Exception {
		AtomicLong nextId = new AtomicLong();
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectTimeout(5_000);
		httpClient.setIdleTimeout(5_000);
		httpClient.start();
		ExecutorService executor = Executors.newCachedThreadPool();

		LinkedBlockingQueue<Byte> responses = new LinkedBlockingQueue<>();
		LinkedBlockingQueue<String> txtData = new LinkedBlockingQueue<>();
		CompletableFuture<Result> promise = new CompletableFuture<>();

		StringBuilder group = new StringBuilder(); //test
		StringBuilder responseBody = new StringBuilder(); //test 
		
		long id = nextId.getAndIncrement();
		for (int i = 0; i < 10; i++) {
			executor.submit(() -> {
				Request request = httpClient.newRequest(endpoint).method(method).onResponseHeaders(response -> {
					for (HttpField header : response.getHeaders())
						group.append(id + " < " + header + "\n");
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
					System.out.println(id + " << " + responseBody);
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
		}
		promise.isDone();
		promise.get().isSucceeded();
		responses.size();
		txtData.take();
		String responseData = drainToString(responses);

		executor.shutdown();
		executor.awaitTermination(2, TimeUnit.MINUTES);
		httpClient.stop();
	}

	public void sClient(String endpoint, HttpMethod method, String data) throws Exception {
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectTimeout(60_000);
		httpClient.setIdleTimeout(60_000);
		httpClient.start();
		Request request = httpClient.newRequest(endpoint).method(method); // HttpMethod.GET HttpMethod.POST
		request.header(HttpHeader.CONTENT_TYPE, "application/json");
		request.content(new StringContentProvider(data, "utf-8"));
		ContentResponse contentRes = request.send();
		System.out.println(contentRes.getContentAsString());
		httpClient.stop();
	}

}
