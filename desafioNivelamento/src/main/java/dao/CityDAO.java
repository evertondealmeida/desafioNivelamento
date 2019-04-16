package dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.City;
import model.Shop;
import model.State;

public class CityDAO {

	protected EntityManager em = new JPAUtil().getEntityManager();
	
	public boolean searchCity(Shop shop){
		try {
			em.getTransaction().begin();
			City city = em.find(City.class, shop.getCity().getCode());
			em.getTransaction().commit();
			return city == null;
		}catch(Exception e) {
			em.getTransaction().rollback();
			return true;
		}
	}
	
	public City getCity(int cdCity){
		try {
			em.getTransaction().begin();
			City city = em.find(City.class, cdCity);
			em.getTransaction().commit();
			return city;
		}catch(Exception e) {
			em.getTransaction().rollback();
			return null;
		}	
	}

	public List<City> listCity(int cdState) throws SQLException {
		List<City> vetCity = new ArrayList();
		try {
			em.getTransaction().begin();    
					
			State state = em.find(State.class, cdState);
			if(state.equals(null)) return null;
			for(City allCity : state.getCity()){
				City city = new City();
				city.setCode(allCity.getCode());
				city.setName(allCity.getName());
				vetCity.add(city);			
			};
			em.getTransaction().commit();
	
		    return vetCity;
		}catch(Exception e) {
			em.getTransaction().rollback();
			return null;
		}	
	}
}
