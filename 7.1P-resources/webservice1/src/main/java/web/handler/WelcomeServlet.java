package web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP endpoint for the server landing page.
 */
public class WelcomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setStatus(HttpServletResponse.SC_OK);

		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head><title>welcome</title></head>");
		writer.println("<body>");
		writer.println("<h1>Welcome, server is running!</h1>");
		writer.println("</body>");
		writer.println("</html>");
	}
}
