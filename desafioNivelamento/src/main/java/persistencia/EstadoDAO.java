package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Estado;

public class EstadoDAO {

	public List<Estado> listarEstado() throws SQLException {
		List<Estado> vetCidade = new ArrayList();
		Connection connection = new Conexao().getConexao();
		String sql = "SELECT * FROM estado";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		ResultSet rs = sqlSelect.executeQuery();
		Estado estado;
		while (rs.next()) {
			estado = new Estado();
			estado.setCodigo(rs.getInt("codigo"));
			estado.setNome(rs.getString("nome"));
			estado.setUf(rs.getString("uf"));
			vetCidade.add(estado);
		}
		sqlSelect.close();
		connection.close();
		return vetCidade;
	}

}
