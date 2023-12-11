package pages;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PlayerDaoImpl;
import dao.TeamDaoImpl;
import pojos.Player;
import pojos.Team;

/**
 * Servlet implementation class ProcessPlayer
 */
@WebServlet("/process_add_player_form")
public class ProcessPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		TeamDaoImpl tm=(TeamDaoImpl) session.getAttribute("team_dao");
		PlayerDaoImpl pl= (PlayerDaoImpl) session.getAttribute("player_dao");
		String err_msg="";
		Player p=null;
		
		try {
			//System.out.println(request.getParameter("team_abbr"));
			Team t=tm.getTeamDetails(request.getParameter("team_abbr"));
			p=new Player(request.getParameter("fnm"), request.getParameter("lnm"), Date.valueOf(request.getParameter("dob")),Double.parseDouble(request.getParameter("avg")) , Integer.parseInt(request.getParameter("wickets")),t.getTeamId());
			Period pe=Period.between(LocalDate.parse(request.getParameter("dob")), LocalDate.now());
			if(t.getMaxAge()<pe.getYears()) {
				err_msg="age is greater than "+t.getMaxAge();
			}else if(t.getMinBattingAvg()>Double.parseDouble(request.getParameter("avg"))) {
				err_msg="batting avg is less than "+t.getMinBattingAvg();
			}else if(t.getMinWicketsTaken()>Integer.parseInt(request.getParameter("wickets"))) {
				err_msg="wickets taken is less than "+t.getMinWicketsTaken();
			}else {
				System.out.println(pl.addPlayerToTeam(p, t.getTeamId()));
			}
			System.out.println(err_msg);
			if(err_msg.equals("")) {
			request.setAttribute("player",p);
			RequestDispatcher rd = request.getRequestDispatcher("success");
			rd.forward(request, response);
			}else {
				session.setAttribute("err", err_msg);
				response.sendRedirect("failuer");
			}
			
		} catch (SQLException e) {
			throw new ServletException("Error in doPost"+getClass(),e);
		}
	}

}
