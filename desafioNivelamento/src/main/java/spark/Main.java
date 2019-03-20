package spark;

import static spark.Spark.*;

import java.util.List;

import modelo.Cidade;
import persistencia.CidadeDAO;
import persistencia.Conexao;

public class Main {
	public static void main(String[] args) throws Exception {
		// get("/hello", (req, res) -> "Hello World");
		CidadeDAO dao = new CidadeDAO();
		Cidade cidade = dao.obter(4315602);

		System.out.println("Nome:" + cidade.getNome());

		List<Cidade> vetCidade = dao.listarCidades(43, "");
		for (int i = 0; i < vetCidade.size(); i++) {
			Cidade aux = vetCidade.get(i);
			System.out.println("Nome:" + aux.getNome());
		}
	}
}
