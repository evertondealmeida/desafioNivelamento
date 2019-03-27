package service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.Estado;
import model.Estado;
import persistence.CidadeDAO;
import persistence.EstadoDAO;

public class EstadoService {
	private HashMap<String, Estado> EstadoMap;
	EstadoDAO dao = new EstadoDAO();
	public EstadoService() {
		EstadoMap = new HashMap<>();
	}

	public Collection<Estado> listarEstados() {	
		try {
			List<Estado> vetEstado;
			vetEstado = dao.listarEstado();
			for (int i = 0; i < vetEstado.size(); i++) {
				Estado aux = vetEstado.get(i);
				EstadoMap.put(Integer.toString(aux.getCodigo()), aux);
			}					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EstadoMap.values();		
	}
}
