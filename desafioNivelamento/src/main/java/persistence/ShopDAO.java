package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.City;
import model.Shop;

public class ShopDAO {

	public List<Shop> listShop(int codeState, int codeCity) throws SQLException {
		List<Shop> vetShop = new ArrayList();
		Connection connection = new ConnectionDAO().getConnection();
		String filtro = codeState > 0 ? "state.code = ?" : "state.code > ?";
		filtro += codeCity > 0 ? "and city.code = ?" : "and city.code > ?";
		String sql = "SELECT shop.* FROM shop \r\n" + "INNER JOIN city ON shop.codeCity = city.code\r\n"
				+ "INNER JOIN state ON city.codeState = state.code\r\n" + "WHERE " + filtro
				+ " ORDER BY shop.name ASC";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		sqlSelect.setInt(1, codeState > 0 ? codeState : 0);
		sqlSelect.setInt(2, codeCity > 0 ? codeCity : 0);
		ResultSet rs = sqlSelect.executeQuery();
		Shop Shop;
		while (rs.next()) {
			Shop = new Shop();
			Shop.setId(rs.getInt("id"));
			Shop.setName(rs.getString("name"));
			Shop.setAddress(rs.getString("address"));
			Shop.setPhone(rs.getString("phone"));
			Shop.setCnpj(rs.getString("cnpj"));
			Shop.setWorkingHour(rs.getString("workingHour"));
			Shop.setCodeCity(rs.getInt("codeCity"));
			vetShop.add(Shop);
		}
		sqlSelect.close();
		connection.close();
		return vetShop;
	}

	/*public void insertShop(Shop Shop) throws SQLException {
		Connection connection = new ConnectionDAO().getConnection();
		String sql = "INSERT INTO shop VALUES(null,?,?,?,?,?,?)";
		PreparedStatement sqlInsert = connection.prepareStatement(sql);
		sqlInsert.setString(1, Shop.getName());
		sqlInsert.setString(2, Shop.getAddress());
		sqlInsert.setString(3, Shop.getPhone());
		sqlInsert.setString(4, Shop.getCnpj());
		sqlInsert.setString(5, Shop.getWorkingHour());
		sqlInsert.setInt(6, Shop.getCodeCity());
		sqlInsert.executeUpdate();
		sqlInsert.close();
		connection.close();
	}

	public void delete(int id) throws SQLException {
		Connection connection = new ConnectionDAO().getConnection();
		String sql = "DELETE FROM Shop WHERE id = ?";
		PreparedStatement sqlDelete = connection.prepareStatement(sql);
		sqlDelete.setInt(1, id);
		sqlDelete.executeUpdate();
		sqlDelete.close();
		connection.close();
	}

	public void update(Shop Shop) throws SQLException {
		Connection connection = new ConnectionDAO().getConnection();
		String sql = "UPDATE shop SET name = ?, address = ?,phone = ?, cnpj = ?, workingHour = ?, codeCity = ? WHERE id = ?";
		PreparedStatement sqlInsert = connection.prepareStatement(sql);
		sqlInsert.setString(1, Shop.getName());
		sqlInsert.setString(2, Shop.getAddress());
		sqlInsert.setString(3, Shop.getPhone());
		sqlInsert.setString(4, Shop.getCnpj());
		sqlInsert.setString(5, Shop.getWorkingHour());
		sqlInsert.setInt(6, Shop.getCodeCity());
		sqlInsert.setInt(7, Shop.getId());
		sqlInsert.executeUpdate();
		sqlInsert.close();
		connection.close();
	}

	public Shop getShop(int id) throws Exception {
		Connection connection = new ConnectionDAO().getConnection();
		String sql = "SELECT * FROM shop WHERE id = ?";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		sqlSelect.setInt(1, id);
		ResultSet rs = sqlSelect.executeQuery();
		Shop shop = new Shop();
		;
		if (rs.next()) {
			shop.setId(rs.getInt("id"));
			shop.setName(rs.getString("name"));
			shop.setAddress(rs.getString("address"));
			shop.setPhone(rs.getString("phone"));
			shop.setCnpj(rs.getString("cnpj"));
			shop.setWorkingHour(rs.getString("workingHour"));
			shop.setCodeCity(rs.getInt("codeCity"));
		}
		sqlSelect.close();
		connection.close();
		return shop;
	}

	public boolean checkShop(int id) throws Exception {
		boolean resultado = false;
		Connection connection = new ConnectionDAO().getConnection();
		String sql = "SELECT * FROM shop WHERE id = ?";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		sqlSelect.setInt(1, id);
		ResultSet rs = sqlSelect.executeQuery();
		if (rs.next()) {
			resultado = true;
		}
		sqlSelect.close();
		connection.close();
		return resultado;
	}*/
}
