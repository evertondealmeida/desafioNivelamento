package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.City;
import model.City;
import persistence.CityDAO;
import persistence.CityDAO;

public class CityService {
	private HashMap<String, City> CityMap;
	CityDAO dao = new CityDAO();

	public CityService() {
		CityMap = new HashMap<>();
	}

	public Collection<City> listCity(String code) {
		CityMap.clear();
		try {
			List<City> vetCity = new ArrayList();
			vetCity = dao.listCity(Integer.parseInt(code));
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
