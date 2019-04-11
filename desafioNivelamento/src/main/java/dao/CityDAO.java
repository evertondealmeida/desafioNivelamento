package dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.City;
import model.Shop;
import model.State;

public class CityDAO {

	public boolean searchCity(Shop shop) throws SQLException {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		City city = em.find(City.class, shop.getCity().getCode());
		em.getTransaction().commit();

		em.close();
		return city == null;
	}
	
	public City getCity(int cdCity) throws SQLException {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		City city = em.find(City.class, cdCity);
		em.getTransaction().commit();

		em.close();
		return city;
	}

	public List<City> listCity(State state) throws SQLException {
		List<City> vetCity = new ArrayList();
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();    
	    String jpql = "SELECT s FROM State s JOIN FETCH s.city WHERE s.code = :pCodeState";
		Query query = em.createQuery(jpql);
		query.setParameter("pCodeState", state.getCode());
		
		State getState = (State) query.getSingleResult();
		
		for(City allCity : getState.getCity()){
			City city = new City();
			city.setCode(allCity.getCode());
			city.setName(allCity.getName());
			vetCity.add(city);			
		};
		em.getTransaction().commit();

		em.close();
	    return vetCity;

		
		
		
		
		
		
		
		/*
		
		String jpql = "SELECT c FROM City c WHERE c.state = :pState ORDER BY name ASC";

		Query query = em.createQuery(jpql);
		query.setParameter("pState", state);
		
		List<City> result = query.getResultList();

		City city;
		for (City allCity : result) {
			city = new City();
			city.setCode(allCity.getCode());
			city.setName(allCity.getName());
			vetCity.add(city);
		}

		em.getTransaction().commit();

		em.close();

		return vetCity;*/
	}


}
