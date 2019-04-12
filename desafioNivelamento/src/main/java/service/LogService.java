package service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import dao.CityDAO;
import dao.LogDAO;
import dao.ShopDAO;
import model.Log;
import model.ReplyMessage;
import model.Shop;
import model.State;

public class LogService {
	private HashMap<String, Log> LogMap;
	LogDAO logJPA = new LogDAO();
	public LogService() {
		LogMap = new HashMap<>();
	}
	public void insertLog(String local,String typeMessage, String description) throws SQLException {
		Log log = new Log();
		log.setLocal(local);
		log.setTypeMessage(typeMessage);
		log.setDescription(description);
		log.DateFormated();
		logJPA.insertLog(log);
	}
	public Collection<Log> listStates() {
		try {
			List<Log> vetLog;
			vetLog = logJPA.listLog();	
			for (int i = 0; i < vetLog.size(); i++) {			
				Log aux = vetLog.get(i);
				LogMap.put(Integer.toString(aux.getId()), aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LogMap.values();
	}
}
