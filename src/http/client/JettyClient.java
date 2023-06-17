package http.client;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.HashMap;
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
