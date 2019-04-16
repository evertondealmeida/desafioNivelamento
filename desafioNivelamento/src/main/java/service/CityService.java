package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import dao.CityDAO;
import model.City;

public class CityService {
	private HashMap<String, City> CityMap;
	CityDAO cityJPA = new CityDAO();
	public CityService() {
		CityMap = new HashMap<>();
	}

	public Collection<City> listCity(String code) throws Exception {
		CityMap.clear();
			List<City> vetCity = new ArrayList();
			vetCity = cityJPA.listCity(Integer.parseInt(code));
			for (int i = 0; i < vetCity.size(); i++) {
				City aux = vetCity.get(i);
				CityMap.put(Integer.toString(aux.getCode()), aux);
			}
		return CityMap.values();
	}
}
