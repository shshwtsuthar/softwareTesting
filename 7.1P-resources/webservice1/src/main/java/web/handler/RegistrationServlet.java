package web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.RegistrationService;

/**
 * HTTP endpoint for registration requests.
 */
public class RegistrationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String email = req.getParameter("email");
		String dob = req.getParameter("dob");

		String status = RegistrationService.register(firstName, lastName, email, dob)
				? "success"
				: "fail";

		resp.setContentType("text/html;charset=UTF-8");
		resp.setStatus(HttpServletResponse.SC_OK);

		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head><title>" + status + "</title></head>");
		writer.println("<body>");
		writer.println("<h2 id=\"registration-status\">Registration status: " + status + "</h2>");
		writer.println("</body>");
		writer.println("</html>");
	}
}
