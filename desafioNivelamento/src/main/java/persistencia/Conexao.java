package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import java.sql.*;

public class Conexao {

	private Connection conexao;
	public Statement statement;
	public ResultSet resultset;
	public PreparedStatement prep;
    
	public Connection getConexao(){
        String url = "jdbc:sqlite:lojas.db";
        try {
           // System.out.println("Sucesso na Conexao....");
            return DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("Erro na Conexao....");
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
