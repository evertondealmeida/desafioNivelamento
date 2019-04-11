package service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import dao.StateDAO;
import model.State;

public class StateService {
	private HashMap<String, State> StateMap;
	StateDAO stateJPA = new StateDAO();

	public StateService() {
		StateMap = new HashMap<>();
	}

	public Collection<State> listStates() {
		try {
			List<State> vetState;
			vetState = stateJPA.listState();
			for (int i = 0; i < vetState.size(); i++) {
				State aux = vetState.get(i);
				StateMap.put(Integer.toString(aux.getCode()), aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return StateMap.values();
	}
}
