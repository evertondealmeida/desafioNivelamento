package service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import model.ReplyMessage;
import model.Shop;
import model.StandardResponse;
import model.StatusResponse;
import persistence.CityDAO;
import persistence.ShopDAO;

public class ShopService {
	private HashMap<String, Shop> ShopMap;
	ShopDAO dao = new ShopDAO();
	CityDAO cityDAO = new CityDAO();

	public ShopService() {
		ShopMap = new HashMap<>();
	}

	public String insertShop(Shop shop) throws SQLException {
		if (!shop.nullFields(shop))
			return ReplyMessage.NullFields;
		shop.setCnpj(shop.onlyNumbers(shop.getCnpj()));
		if (!shop.checkCNPJ(shop))
			return ReplyMessage.SizeCNPJ;
		shop.setPhone(shop.onlyNumbers(shop.getPhone()));
		if (cityDAO.searchCity(shop.getCodeCity()))
			return ReplyMessage.IdCityNotExist;
		dao.insertShop(shop);
		ShopMap.put(Integer.toString(shop.getId()), shop);
		return ReplyMessage.Empty;
	}

	public Collection<Shop> listShops(String codeState, String codeCity) throws SQLException {
		ShopMap.clear();
		codeState = codeState != null ? codeState : "0";
		codeCity = codeCity != null ? codeCity : "0";
		List<Shop> vetShop;
		vetShop = dao.listShop(Integer.parseInt(codeState), Integer.parseInt(codeCity));
		for (int i = 0; i < vetShop.size(); i++) {
			Shop aux = vetShop.get(i);
			ShopMap.put(Integer.toString(aux.getId()), aux);
		}
		return ShopMap.values();
	}

	public Shop getShop(String id) throws NumberFormatException, Exception {
		Shop shop = new Shop();
		shop = dao.getShop(Integer.parseInt(id));
		ShopMap.put(Integer.toString(shop.getId()), shop);
		return ShopMap.get(id);
	}

	public String deleteShop(String id) throws NumberFormatException, Exception {
		if (!dao.checkShop(Integer.parseInt(id)))
			return ReplyMessage.IdShopNotExist;
		dao.delete(Integer.parseInt(id));
		ShopMap.remove(id);
		return ReplyMessage.Empty;
	}

	public String updateShop(Shop shop) throws Exception {
		if (!shop.idNullField(shop))
			return ReplyMessage.NullFields;
		shop.setCnpj(shop.onlyNumbers(shop.getCnpj()));
		if (!dao.checkShop(shop.getId()))
			return ReplyMessage.IdShopNotExist;
		if (!shop.checkCNPJ(shop))
			return ReplyMessage.SizeCNPJ;
		shop.setPhone(shop.onlyNumbers(shop.getPhone()));
		if (!cityDAO.searchCity(shop.getCodeCity()))
			return ReplyMessage.IdCityNotExist;
		ShopMap.put(Integer.toString(shop.getId()), shop);
		dao.update(shop);
		return ReplyMessage.Empty;
	}

}
