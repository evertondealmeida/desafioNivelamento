package service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.Loja;
import persistence.CidadeDAO;
import persistence.LojaDAO;

public class LojaService {
	private HashMap<String, Loja> LojaMap;
	LojaDAO dao = new LojaDAO();
	public LojaService() {
		LojaMap = new HashMap<>();
	}

	public void inserirLoja(Loja Loja) {	
		try {
			LojaMap.put(Integer.toString(Loja.getId()), Loja);
			dao.inserir(Loja);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Collection<Loja> listarLojas(String codigoEstado, String codigoCidade) {
		LojaMap.clear();
		codigoEstado = codigoEstado != null ? codigoEstado : "0";
		codigoCidade = codigoCidade != null ? codigoCidade : "0";
		try {
			List<Loja> vetLoja;
			vetLoja = dao.listarLojas(Integer.parseInt(codigoEstado),Integer.parseInt(codigoCidade));
			for (int i = 0; i < vetLoja.size(); i++) {
				Loja aux = vetLoja.get(i);
				LojaMap.put(Integer.toString(aux.getId()), aux);
				System.out.println("Loja:" + aux.toString());
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LojaMap.values();
		
	}

	
	public Loja getLoja(String id) {
		return LojaMap.get(id);
	}

	
	
	public void excluirLoja(String id) {
		
		try {
			dao.excluir(Integer.parseInt(id));
			LojaMap.remove(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public boolean obterLoja(String id) {
		return LojaMap.containsKey(id);
	}

	
	public Loja atualizarLoja(Loja loja) {
		try {
			dao.atualizar(loja);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	

}
