package web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import web.handler.LoginServlet;
import web.handler.RegistrationServlet;
import web.handler.WelcomeServlet;


/**
 * HTTP server. Starts server and registers Java Servlets to URL routes.
 */
public class MyServer {

	private static final int PORT = 8082;

	public void start() throws Exception {
		Server server = new Server(PORT);

		ServletContextHandler handler = new ServletContextHandler(server, "/");

		handler.addServlet(WelcomeServlet.class, "/");

		handler.addServlet(LoginServlet.class, "/login");

		handler.addServlet(RegistrationServlet.class, "/reg");

		server.start();
		System.out.println("Server started!");
		server.join();
	}

	public static void main(String[] args) throws Exception {
		new MyServer().start();
	}
}
