package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cidade;
import model.Estado;

public class CidadeDAO {
	
	public List<Cidade> listarCidade(int cdEstado) throws SQLException {
		List<Cidade> vetCidade = new ArrayList();
		Connection connection = new Conexao().getConexao();
		String sql = "SELECT * FROM cidade WHERE codigoEstado = ? ORDER BY nome ASC";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		sqlSelect.setInt(1, cdEstado);
		ResultSet rs = sqlSelect.executeQuery();
		 while (rs.next()){
			    Cidade cidade = new Cidade(); 
	            cidade.setCodigo(rs.getInt("codigo"));
	            cidade.setNome(rs.getString("nome")); 
	            cidade.setCodigoEstado(rs.getInt("codigoEstado"));
	            vetCidade.add(cidade);
	        }
	        sqlSelect.close();
	        connection.close();
	        return vetCidade;
	}
}
