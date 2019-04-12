package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dao.CityDAO;
import model.Shop;
import model.State;
import model.City;
import model.Log;

public class LogDAO {

	protected EntityManager em = new JPAUtil().getEntityManager();
	
	public void insertLog(Log log) throws SQLException {
		
		em.getTransaction().begin();	
		em.persist(log);
		em.getTransaction().commit();
	}

	public List<Log> listLog() throws SQLException {
		
		em.getTransaction().begin();

		TypedQuery<Log> query = em.createQuery("FROM Log",Log.class);
		List<Log> result = query.getResultList();

		em.getTransaction().commit();;

		return result;
	}
}
