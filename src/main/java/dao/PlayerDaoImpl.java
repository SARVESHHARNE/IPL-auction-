package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static utils.DBUtils.*;
import pojos.Player;

public class PlayerDaoImpl implements PlayerDao {
	private Connection cn=getConnection();
	private PreparedStatement pst1;
	
	
	
	public PlayerDaoImpl() throws SQLException {
		this.cn = getConnection();
		this.pst1 = cn.prepareStatement("insert into players values(default,?,?,?,?,?,?)");
	}
//insert into players values(default,?,?,?,?,?,?);


	@Override
	public String addPlayerToTeam(Player newPlayer, int teamId) throws SQLException {
		pst1.setString(1,newPlayer.getFirstName());
		pst1.setString(2,newPlayer.getLastName());
		pst1.setDate(3,newPlayer.getDob());
		pst1.setDouble(4,newPlayer.getBattingAvg());
		pst1.setInt(5,newPlayer.getWicketsTaken());
		pst1.setInt(6,teamId);
		pst1.execute();
		return "player added successfully ";
	}

}
