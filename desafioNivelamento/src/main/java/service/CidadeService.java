package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.Cidade;
import model.Cidade;
import persistence.CidadeDAO;
import persistence.CidadeDAO;

public class CidadeService {
	private HashMap<String, Cidade> CidadeMap;
	CidadeDAO dao = new CidadeDAO();
	public CidadeService() {
		CidadeMap = new HashMap<>();
	}

	public Collection<Cidade> listarCidade(String codigo) {
		CidadeMap.clear();
		try {
			List<Cidade> vetCidade = new ArrayList();;
			vetCidade = dao.listarCidade(Integer.parseInt(codigo));
			for (int i = 0; i < vetCidade.size(); i++) {
				Cidade aux = vetCidade.get(i);
				CidadeMap.put(Integer.toString(aux.getCodigo()), aux);
			}					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CidadeMap.values();		
	}
}
