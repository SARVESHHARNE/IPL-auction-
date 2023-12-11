package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TeamDaoImpl;

/**
 * Servlet implementation class FailuerPage
 */
@WebServlet("/failuer")
public class FailuerPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		TeamDaoImpl tm=(TeamDaoImpl) session.getAttribute("team_dao");
		String err=(String) session.getAttribute("err");
		
		try(PrintWriter pw=response.getWriter()){
			pw.print("<h2>Sorry to say but we can't take you</h2>");
			pw.print("<h3> resone : "+err+"</h3>");
			session.invalidate();
			pw.print("<a href='add_player_form'>go back </a>");
		}
	}

}
