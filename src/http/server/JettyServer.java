package http.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class JettyServer {

	public static void main(String[] args) throws Exception {
		new JettyServer().start();
	}

	public void start() throws Exception {
		// thread pool 제한하기
		QueuedThreadPool pool = new QueuedThreadPool(5);
		// pool.setIdleTimeout(5_000);
		pool.setName("server-pool");
		Server server = new Server(pool);

		// Server server = new Server();//기본 thread pool 사용 //250개임
		ServerConnector http = new ServerConnector(server);
		http.setHost("127.0.0.1");
		http.setPort(8080);
		//http.getConnectedEndPoints().forEach(endPoint -> endPoint.setIdleTimeout(3));
		server.addConnector(http);

		// 비동기서블렛?
		//
		 //ServletHandler servletHandler = new ServletHandler();
		 //servletHandler.addServletWithMapping(JettyServlet.class, "/hello");
		 //server.setHandler(servletHandler);

		// 비동기 IO 처리
		// ServletHolder asyncHolder = new ServletHolder(new JettyServlet());
		// context.setContextPath("/");
		// context.addServlet(JettyServlet.class, "/hello");
		// servletHandler.addServletWithMapping(JettyServlet.class, "/hello");  // /*  path도 됨
		// asyncHolder.setAsyncSupported(true);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.getSessionHandler().setStopTimeout(1_000);  
		context.getSessionHandler().setMaxInactiveInterval(5_000);
		context.setContextPath("/");
		ServletHolder asyncHolder = context.addServlet(JettyServlet.class, "/hello");
		asyncHolder.setAsyncSupported(true);
		server.setHandler(context);
		
		
		server.start();
		server.join();

	}
}
