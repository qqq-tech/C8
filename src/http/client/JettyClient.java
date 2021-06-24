package http.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;

public class JettyClient {

	public static void main(String[] args) throws Exception {
		// ex)1. 단순 client
		// HttpClient httpClient = new HttpClient();
		// httpClient.setConnectTimeout(60_000);
		// httpClient.setIdleTimeout(60_000);
		// httpClient.start();
		// ContentResponse contentRes =
		// httpClient.newRequest("http://127.0.0.1:8080").send();
		// System.out.println(contentRes.getContentAsString());

		// ex2). 여러 클라이언트
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectTimeout(5_000);
		httpClient.setIdleTimeout(5_000);
		httpClient.start();
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			executor.submit(() -> {
				long t1 = System.currentTimeMillis();
				Request req = httpClient.newRequest("http://localhost:8080/hello");
				req.idleTimeout(15, TimeUnit.SECONDS);  //request 타임아웃동작함
				ContentResponse response = req.send();
				long t2 = System.currentTimeMillis();
				System.out.println("response time:" + (t2 - t1) + " ms");
				//System.out.println(response.getContentAsString());
				return true;
			});
		}
		executor.shutdown();
		executor.awaitTermination(2, TimeUnit.MINUTES);
		httpClient.stop();
	}

}
