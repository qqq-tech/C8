package http.server;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JettyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void _doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.setStatus(200);
		//res.getWriter().write("Hello!");

		//io 블럭테스트 비동기 Servlet 확인
		try {
			Thread.sleep(10_000); //5초 딜레이
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setCharacterEncoding("utf-8");
			res.getWriter().println("<h1>Hello from HelloServlet</h1>");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		//비동기 IO 테스트
		final AsyncContext asyncContext = request.startAsync();
		asyncContext.setTimeout(3_000);  // 서버에서 3초 이내 작업 미종료시 client로 응답보냄. but 아래 excutor는 계속 동작하는 상태로 남아있음,.
		executor.schedule(() -> {
			try {
				Thread.sleep(10_000); //20초 딜레이
				response.setStatus(HttpServletResponse.SC_OK);
				response.setStatus(200);
				response.getWriter().write("Hello!");
				//response.setContentType("text/html");
				//response.setCharacterEncoding("utf-8");
				//response.getWriter().println("<h1>Hello from HelloServlet</h1>");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			asyncContext.complete();
		}, 0, TimeUnit.SECONDS);
	}
}
