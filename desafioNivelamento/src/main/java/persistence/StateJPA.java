package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Shop;
import model.State;

public class StateJPA {

	public List<State> listState() throws SQLException {
		List<State> vetState = new ArrayList();
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		String jpql = "SELECT s FROM State s ORDER BY s.name DESC";

		Query query = em.createQuery(jpql);
		List<State> result = query.getResultList();

		State state;
		for (State allState : result) {
			state = new State();
			state.setCode(allState.getCode());
			state.setName(allState.getName());
			state.setUf(allState.getUf());
			vetState.add(state);
		}

		em.getTransaction().commit();

		em.close();

		return vetState;
	}
	
	public State getState(int code) throws Exception {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		State state = em.find(State.class, code);
		em.getTransaction().commit();

		em.close();
		return state;
	}

}
