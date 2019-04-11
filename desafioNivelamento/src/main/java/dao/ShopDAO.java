package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.CityDAO;
import model.Shop;
import model.City;

public class ShopDAO {

	public void insertShop(Shop shop) throws SQLException {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(shop);
		em.getTransaction().commit();

		em.close();
	}

	public Shop getShop(int id) throws Exception {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		
		Shop shop = em.find(Shop.class, id);
	
		em.getTransaction().commit();

		em.close();
		return shop;
	}
	
	public void update(Shop shop) throws SQLException {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		Shop shopDB = em.find(Shop.class, shop.getId());
		
	    shopDB.setName(shop.getName());
	    shopDB.setAddress(shop.getAddress());
	    shopDB.setPhone(shop.getPhone());
	    shopDB.setCnpj(shop.getCnpj());
	    shopDB.setWorkingHour(shop.getWorkingHour());
	    shopDB.setCity(shop.getCity());
	    
	    em.getTransaction().commit();
		em.close();	
	}
	
	public void delete(int id) throws SQLException {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		Shop shop = em.find(Shop.class, id);
		em.remove(shop);
		em.getTransaction().commit();
		em.close();
	}
	
	public boolean checkShop(int id) throws SQLException {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		Shop shop = em.find(Shop.class, id);
		em.getTransaction().commit();
		
		em.close();		
		return shop == null;	
	}
	
	public List<Shop> listShop(int codeCity) throws SQLException {
		List<Shop> vetShop = new ArrayList();
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
	
		String jpql = "SELECT c FROM City c JOIN FETCH c.shop WHERE c.code = :pCodeCity";
		Query query = em.createQuery(jpql);
		query.setParameter("pCodeCity", codeCity);
		
		City city = (City) query.getSingleResult();
		if(city.equals(null)) return null;
		for(Shop allShop: city.getShop()){
			Shop shop = new Shop();
			shop.setId(allShop.getId());
			shop.setName(allShop.getName());
			shop.setAddress(allShop.getAddress());
			shop.setPhone(allShop.getPhone());
			shop.setCnpj(allShop.getCnpj());
			shop.setWorkingHour(allShop.getWorkingHour());
			vetShop.add(shop);
		};
		em.getTransaction().commit();

		em.close();
		return vetShop;
	}
		
		
}
