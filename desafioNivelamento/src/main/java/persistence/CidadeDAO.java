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
	
	public Cidade obter(int codigo) throws Exception {
        Connection connection =  new Conexao().getConexao();
        String sql = "SELECT * FROM cidade WHERE codigo = ?";
        PreparedStatement sqlSelect = connection.prepareStatement(sql);
        sqlSelect.setInt(1, codigo);
        ResultSet rs =  sqlSelect.executeQuery();     
        Cidade cidade;
        if(rs.next()){
            cidade = new Cidade(); 
            cidade.setCodigo(rs.getInt("codigo"));
            cidade.setNome(rs.getString("nome"));
            cidade.setCodigoEstado(rs.getInt("codigoEstado"));
            return cidade;
        }
        sqlSelect.close();
        connection.close();
        return null;
    }
	public List<Cidade> listarCidade() throws SQLException {
		List<Cidade> vetCidade = new ArrayList();
		Connection connection = new Conexao().getConexao();
		String sql = "SELECT * FROM cidade";
		PreparedStatement sqlSelect = connection.prepareStatement(sql);
		ResultSet rs = sqlSelect.executeQuery();
		Cidade cidade;
		 while (rs.next()){
	        	cidade = new Cidade(); 
	            cidade.setCodigo(rs.getInt("codigo"));
	            cidade.setNome(rs.getString("nome")); 
	            cidade.setCodigoEstado(rs.getInt("codigoEstado"));
	            vetCidade.add(cidade);
	        }
	        sqlSelect.close();
	        connection.close();
	        return vetCidade;
	}
	public List<Cidade> listarCidades(int codigoEstado,String nomeCidade) throws SQLException{
        List<Cidade> vetCidade = new ArrayList();
        Connection connection =  new Conexao().getConexao();       
        String sql = "SELECT * FROM cidade WHERE codigoEstado = ? and nome Like ?";
        PreparedStatement sqlSelect = connection.prepareStatement(sql);
        sqlSelect.setInt(1, codigoEstado);
        sqlSelect.setString(2, nomeCidade!=""?nomeCidade+"%":"%"+""+"%");
        ResultSet rs =  sqlSelect.executeQuery();
        Cidade cidade;
        while (rs.next()){
        	cidade = new Cidade(); 
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
