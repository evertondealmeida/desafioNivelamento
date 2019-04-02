package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.City;
import model.State;

public class CityDAO {

	public List<City> listCity(int cdState) throws SQLException {
		List<City> vetCity = new ArrayList();
		Connection connection = new ConnectionDAO().getConnection();
		String sql = "SELECT * FROM city WHERE codeState = ? ORDER BY name ASC";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		sqlSelect.setInt(1, cdState);
		ResultSet rs = sqlSelect.executeQuery();
		while (rs.next()) {
			City city = new City();
			city.setCode(rs.getInt("code"));
			city.setName(rs.getString("name"));
			city.setCodeState(rs.getInt("codeState"));
			vetCity.add(city);
		}
		sqlSelect.close();
		connection.close();
		return vetCity;
	}

	public boolean searchCity(int cdCity) throws SQLException {
		boolean result = false;
		Connection connection = new ConnectionDAO().getConnection();
		String sql = "SELECT * FROM city WHERE code = ? ORDER BY name ASC";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		sqlSelect.setInt(1, cdCity);
		ResultSet rs = sqlSelect.executeQuery();
		if (rs.next()) {
			result = true;
		}
		sqlSelect.close();
		connection.close();
		return result;
	}
}
