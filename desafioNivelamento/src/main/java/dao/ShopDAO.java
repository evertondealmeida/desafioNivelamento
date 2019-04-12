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

	protected EntityManager em = new JPAUtil().getEntityManager();
	
	public void insertShop(Shop shop) throws SQLException {
		
		em.getTransaction().begin();	
		em.persist(shop);
		em.getTransaction().commit();
	}

	public Shop getShop(int id) throws Exception {

		em.getTransaction().begin();	
		Shop shop = em.find(Shop.class, id);
		em.getTransaction().commit();
		return shop;
	}
	
	public void update(Shop shop) throws SQLException {
	
		em.getTransaction().begin();
		Shop shopDB = em.find(Shop.class, shop.getId());
		
	    shopDB.setName(shop.getName());
	    shopDB.setAddress(shop.getAddress());
	    shopDB.setPhone(shop.getPhone());
	    shopDB.setCnpj(shop.getCnpj());
	    shopDB.setWorkingHour(shop.getWorkingHour());
	    shopDB.setCity(shop.getCity());
	    
	    em.getTransaction().commit();

	}
	
	public void delete(int id) throws SQLException {
	
		em.getTransaction().begin();
		Shop shop = em.find(Shop.class, id);
		em.remove(shop);
		em.getTransaction().commit();

	}
	
	public boolean checkShop(int id) throws SQLException {

		em.getTransaction().begin();
		Shop shop = em.find(Shop.class, id);
		em.getTransaction().commit();
		return shop == null;	
	}
	
	public List<Shop> listShop(int codeCity) throws SQLException {
		List<Shop> vetShop = new ArrayList();

		em.getTransaction().begin();	
		City city = em.find(City.class, codeCity);
		
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
		return vetShop;
	}		
}
