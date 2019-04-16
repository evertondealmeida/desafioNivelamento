package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Log;

public class LogDAO {

	protected EntityManager em = new JPAUtil().getEntityManager();
	
	public void insertLog(Log log) {
		try {
			em.getTransaction().begin();	
			em.persist(log);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	public List<Log> listLog() {
		try {
			em.getTransaction().begin();
	
			TypedQuery<Log> query = em.createQuery("FROM Log",Log.class);
			List<Log> result = query.getResultList();
	
			em.getTransaction().commit();
			
			return result;
		}catch(Exception e) {
			em.getTransaction().rollback();
			return null;
		}			
	}
}
