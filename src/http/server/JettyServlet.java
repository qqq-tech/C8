package http.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpRequest;
import org.eclipse.jetty.client.api.ContentProvider;
import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.http.HttpFields;

import http.client.JettyClient;
import wrap.DataProcessingWrapper;

public class JettyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	protected void _doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// res.setStatus(200);
		// res.getWriter().write("Hello!");

		// io ���׽�Ʈ �񵿱� Servlet Ȯ��
		try {
			Thread.sleep(10_000); // 5�� ������
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setCharacterEncoding("utf-8");
			res.getWriter().println("<h1>Hello from HelloServlet</h1>");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	private void printStartLine(HttpServletRequest request) {
		 System.out.println("--- REQUEST-LINE - start ---");
		 System.out.println("request.getMethod() = " 		+ request.getMethod()); 	//GET
		 System.out.println("request.getProtocal() = " 		+ request.getProtocol()); 	//HTTP/1.1
		 System.out.println("request.getScheme() = " 		+ request.getScheme()); 	//http
		 
		 // http://localhost:8080/request-header
		 System.out.println("request.getRequestURL() = " 	+ request.getRequestURL());
		 
		 // /request-test
		 System.out.println("request.getRequestURI() = " 	+ request.getRequestURI());
		 
		 //username=hi
		 System.out.println("request.getQueryString() = " 	+ request.getQueryString());
		 System.out.println("request.isSecure() = " 		+ request.isSecure()); 		//https ��� ����
		 System.out.println("--- REQUEST-LINE - end ---");
		 System.out.println();
	}
	
	private void setHeaders(HttpRequest httpRequest, HttpServletRequest httpServletRequest) {
		HttpFields headers = new HttpFields();
		Enumeration headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			List mappedHeaderValues = new ArrayList();
			Enumeration headerValues = httpServletRequest.getHeaders(headerName);
			while (headerValues.hasMoreElements()) {
				mappedHeaderValues.add(headerValues.nextElement());
			}
			mappedHeaderValues.toArray(new String[mappedHeaderValues.size()]);
			headers.add(new HttpField(headerName,"" ));
			httpRequest.header(headerName,"");
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �񵿱� IO �׽�Ʈ
		response.setStatus(HttpServletResponse.SC_OK);
		response.setStatus(200);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		final AsyncContext asyncContext = request.startAsync();
		asyncContext.setTimeout(3_000); // �������� 3�� �̳� �۾� ������� client�� ���亸��. but �Ʒ� excutor�� ��� �����ϴ� ���·� ��������,.
		executor.schedule(() -> {
			try {
				DataProcessingWrapper dsw = new DataProcessingWrapper();
				dsw.prepareData(request,response);
				dsw.solve(request,response);
				String result = dsw.genResponse();
				asyncContext.getResponse().getWriter().print(result);
				asyncContext.getResponse().getWriter().flush();
			} catch (Exception ex) {
				System.out.println(ex);
			}
			asyncContext.complete();
		}, 0, TimeUnit.SECONDS);
	}
}
