package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.State;

public class StateDAO {

	public List<State> listState() throws SQLException {
		List<State> vetCity = new ArrayList();
		Connection connection = new ConnectionDAO().getConnection();
		String sql = "SELECT * FROM state ORDER BY name ASC";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		ResultSet rs = sqlSelect.executeQuery();
		State state;
		while (rs.next()) {
			state = new State();
			state.setCode(rs.getInt("codigo"));
			state.setName(rs.getString("name"));
			state.setUf(rs.getString("uf"));
			vetCity.add(state);
		}
		sqlSelect.close();
		connection.close();
		return vetCity;
	}

}
