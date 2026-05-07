package web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.LoginService;

/**
 * HTTP endpoint for login requests.
 */
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("passwd");
		String dob = req.getParameter("dob");

		String loginStatus = LoginService.login(username, password, dob) ? "success" : "fail";

		resp.setContentType("text/html;charset=UTF-8");
		resp.setStatus(HttpServletResponse.SC_OK);

		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head><title>" + loginStatus + "</title></head>");
		writer.println("<body>");
		writer.println("<h2 id=\"login-status\">Login status: " + loginStatus + "</h2>");
		writer.println("</body>");
		writer.println("</html>");
	}
}
