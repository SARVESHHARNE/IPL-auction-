package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojos.Team;
import static utils.DBUtils.*;

public class TeamDaoImpl implements TeamDao {
	private Connection cn;
	private PreparedStatement pst1, pst2;

	public TeamDaoImpl() throws SQLException {
		cn=getConnection();
		pst1 = cn.prepareStatement("select abbrevation from teams");
		pst2=cn.prepareStatement("select * from teams where  abbrevation=?");
		System.out.println("team dao created");
	}

	@Override
	public List<String> getTeamsAbbreviations() throws SQLException {
		List<String> list = new ArrayList<>();
		// exec query n process RST
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				list.add(rst.getString(1));
		}
		return list;
	}

	@Override
	public Team getTeamDetails(String abbreviation) throws SQLException {
		// set IN param
		Team t;
		pst2.setString(1, abbreviation);
		try (ResultSet rst = pst2.executeQuery()) {
			rst.next();
				t= new Team(rst.getInt(1), rst.getString(2), abbreviation, rst.getString(4), rst.getInt(5),rst.getDouble(6), rst.getInt(7));
		}
		return t;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		System.out.println("team dao cleaned up !");
	}

}
