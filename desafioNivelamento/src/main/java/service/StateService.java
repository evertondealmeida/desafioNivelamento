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

	public Collection<State> listStates() throws SQLException {
			List<State> vetState;
			vetState = stateJPA.listState();	
			for (int i = 0; i < vetState.size(); i++) {			
				State aux = vetState.get(i);
				aux.setCity(null);
				StateMap.put(Integer.toString(aux.getCode()), aux);
			}
		return StateMap.values();
	}
}
