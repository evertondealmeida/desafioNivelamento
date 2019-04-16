package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.State;

public class StateDAO {
	protected EntityManager em = new JPAUtil().getEntityManager();
	public List<State> listState() {
		try {
			em.getTransaction().begin();
	
			TypedQuery<State> query = em.createQuery("FROM State",State.class);
			List<State> result = query.getResultList();
	
			em.getTransaction().commit();;
	
			return result;
		}catch(Exception e) {
			em.getTransaction().rollback();
			return null;
		}	
	}
	
	public State getState(int code){
		try {
			em.getTransaction().begin();
			State state = em.find(State.class, code);
			em.getTransaction().commit();	
			return state;
		}catch(Exception e) {
			em.getTransaction().rollback();
			return null;
		}	
	}

}
