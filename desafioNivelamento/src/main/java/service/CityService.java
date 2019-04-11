package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import dao.CityDAO;
import dao.StateDAO;
import model.City;
import model.State;

public class CityService {
	private HashMap<String, City> CityMap;
	CityDAO cityJPA = new CityDAO();
	StateDAO stateJPA = new StateDAO();
	State state = new State();
	public CityService() {
		CityMap = new HashMap<>();
	}

	public Collection<City> listCity(String code) throws NumberFormatException, Exception {
		CityMap.clear();
		try {
			List<City> vetCity = new ArrayList();
			
			state = stateJPA.getState(Integer.parseInt(code));
			vetCity = cityJPA.listCity(state);
			for (int i = 0; i < vetCity.size(); i++) {
				City aux = vetCity.get(i);
				CityMap.put(Integer.toString(aux.getCode()), aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CityMap.values();
	}
}
