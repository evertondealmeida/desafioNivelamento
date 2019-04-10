package persistence;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import model.Shop;
import model.City;
import persistence.CityJPA;

public class ShopJPA {
	CityJPA cityJPA = new CityJPA();
	public void insertShop(Shop shop) throws SQLException {
		//City city = cityJPA.getCity(shop.getCodeCity());
		//shop.setCity(city);
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
	
}
