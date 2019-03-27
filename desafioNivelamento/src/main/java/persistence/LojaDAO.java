package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cidade;
import model.Loja;

public class LojaDAO {

	public List<Loja> listarLojas(int codigoEstado, int codigoCidade) throws SQLException {
		List<Loja> vetLoja = new ArrayList();
		Connection connection = new Conexao().getConexao();
		String filtro = codigoEstado > 0 ? "estado.codigo = ?" : "estado.codigo > ?";
		 filtro += codigoCidade > 0 ? "and cidade.codigo = ?" : "and cidade.codigo > ?";
		String sql = "SELECT loja.* FROM loja \r\n" 
		        + "INNER JOIN cidade ON loja.codigoCidade = cidade.codigo\r\n"
				+ "INNER JOIN estado ON cidade.codigoEstado = estado.codigo\r\n"
				+ "WHERE "+filtro+" ORDER BY loja.nome ASC";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		sqlSelect.setInt(1, codigoEstado > 0 ? codigoEstado : 0);
		sqlSelect.setInt(2, codigoCidade > 0 ? codigoCidade : 0);
		ResultSet rs = sqlSelect.executeQuery();
		Loja Loja;
		while(rs.next()) {
			Loja = new Loja();
			Loja.setId(rs.getInt("id"));
			Loja.setNome(rs.getString("nome"));
			Loja.setEndereco(rs.getString("endereco"));
			Loja.setTelefone(rs.getString("telefone"));
			Loja.setCnpj(rs.getString("cnpj"));
			Loja.setHorarioAtendimento(rs.getString("horarioAtendimento"));
			Loja.setCodigoCidade(rs.getInt("codigoCidade"));
			vetLoja.add(Loja);
		}
		sqlSelect.close();
		connection.close();
		return vetLoja;
	}

	public void inserir(Loja Loja) throws SQLException {
		Connection connection = new Conexao().getConexao();
		String sql = "INSERT INTO loja VALUES(null,?,?,?,?,?,?)";
		PreparedStatement sqlInsert = connection.prepareStatement(sql);
		sqlInsert.setString(1, Loja.getNome());
		sqlInsert.setString(2, Loja.getEndereco());
		sqlInsert.setString(3, Loja.getTelefone());
		sqlInsert.setString(4, Loja.getCnpj());
		sqlInsert.setString(5, Loja.getHorarioAtendimento());
		sqlInsert.setInt(6, Loja.getCodigoCidade());
		sqlInsert.executeUpdate();
		sqlInsert.close();
		connection.close();
	}

	public void excluir(int id) throws SQLException {
		Connection connection = new Conexao().getConexao();
		String sql = "DELETE FROM Loja WHERE id = ?";
		PreparedStatement sqlDelete = connection.prepareStatement(sql);
		sqlDelete.setInt(1, id);
		sqlDelete.executeUpdate();
		sqlDelete.close();
		connection.close();
	}

	public void atualizar(Loja Loja) throws SQLException {
		Connection connection = new Conexao().getConexao();
		String sql = "UPDATE loja SET nome = ?, endereco = ?,telefone = ?, cnpj = ?, horarioAtendimento = ?, codigoCidade = ? WHERE id = ?";
		PreparedStatement sqlInsert = connection.prepareStatement(sql);
		sqlInsert.setString(1, Loja.getNome());
		sqlInsert.setString(2, Loja.getEndereco());
		sqlInsert.setString(3, Loja.getTelefone());
		sqlInsert.setString(4, Loja.getCnpj());
		sqlInsert.setString(5, Loja.getHorarioAtendimento());
		sqlInsert.setInt(6, Loja.getCodigoCidade());
		sqlInsert.setInt(7, Loja.getId());
		sqlInsert.executeUpdate();
		sqlInsert.close();
		connection.close();
	}

	public Loja obterLoja(int id) throws Exception {
		Connection connection = new Conexao().getConexao();
		String sql = "SELECT * FROM loja WHERE id = ?";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		sqlSelect.setInt(1, id);
		ResultSet rs = sqlSelect.executeQuery();
		Loja loja = new Loja();;
		if (rs.next()) {
			loja.setId(rs.getInt("id"));
			loja.setNome(rs.getString("nome"));
			loja.setEndereco(rs.getString("endereco"));
			loja.setTelefone(rs.getString("telefone"));
			loja.setCnpj(rs.getString("cnpj"));
			loja.setHorarioAtendimento(rs.getString("horarioAtendimento"));
			loja.setCodigoCidade(rs.getInt("codigoCidade"));
		}
		sqlSelect.close();
		connection.close();
		return loja;
	}
}
