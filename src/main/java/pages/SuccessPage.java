package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TeamDaoImpl;
import pojos.Player;

/**
 * Servlet implementation class SuccessPage
 */
@WebServlet("/success")
public class SuccessPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		TeamDaoImpl tm=(TeamDaoImpl) session.getAttribute("team_dao");
		
		try(PrintWriter pw=response.getWriter()){
			Player p=(Player) request.getAttribute("player");
			pw.print("<h2>HEllO "+p.getFirstName()+"</h2>");
			pw.print("<h3> you are selected in "+tm.getTeamDetails(request.getParameter("team_abbr")).getName()+"</h3>");
			session.invalidate();
			pw.print("<a href='add_player_form'>go back </a>");
		} catch (SQLException e) {
			throw new ServletException("err in do-post of " + getClass(), e);
		}
	}

}
