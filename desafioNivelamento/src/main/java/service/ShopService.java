package service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import dao.CityDAO;
import dao.ShopDAO;
import model.ReplyMessage;
import model.Shop;

public class ShopService {
	private HashMap<String, Shop> ShopMap;
	ShopDAO shopJPA = new ShopDAO();
	CityDAO cityJPA = new CityDAO();
	public ShopService() {
		ShopMap = new HashMap<>();
	}
	
	public String insertShop(Shop shop) {
		try {
			if (!shop.nullFields(shop))return ReplyMessage.NullFields;
		
			shop.setCnpj(shop.onlyNumbers(shop.getCnpj()));
			if (!shop.checkCNPJ(shop))return ReplyMessage.SizeCNPJ;
		
			shop.setPhone(shop.onlyNumbers(shop.getPhone()));
			if (cityJPA.searchCity(shop))return ReplyMessage.IdCityNotExist;
		
			shopJPA.insertShop(shop);
			ShopMap.put(Integer.toString(shop.getId()), shop);
		
		}catch(Exception e) {
			return ReplyMessage.DataBaseError;
		}
		return ReplyMessage.InsertedSuccessfully;
		
	}
	
	public Shop getShop(String id) throws NumberFormatException, Exception {
		Shop shop = new Shop();
		shop = shopJPA.getShop(Integer.parseInt(id));
		if(shop == null) return null;
		shop.setCity(null);		
		ShopMap.put(Integer.toString(shop.getId()), shop);
		return ShopMap.get(id);
	}
	
	public String deleteShop(String id) {
		try {
			if (shopJPA.checkShop(Integer.parseInt(id))) return ReplyMessage.IdShopNotExist;
			shopJPA.delete(Integer.parseInt(id));	
		    ShopMap.remove(id);
		    return ReplyMessage.DeletedSuccessfully;
		}catch(Exception e) {
			return ReplyMessage.DataBaseError;
		}	
	}

	public Collection<Shop> listShops(String codeCity) throws SQLException {
		ShopMap.clear();
		codeCity = codeCity != null ? codeCity : "0";
		List<Shop> vetShop = shopJPA.listShop(Integer.parseInt(codeCity));
		if(vetShop.equals(null))return null;
		for (int i = 0; i < vetShop.size(); i++) {
			Shop aux = vetShop.get(i);
			ShopMap.put(Integer.toString(aux.getId()), aux);
		}
		return ShopMap.values();
	}

	public String updateShop(Shop shop,String id){
		try {
			if (shopJPA.checkShop(Integer.parseInt(id))) return ReplyMessage.IdShopNotExist;
			shop.setId(Integer.parseInt(id));
		
			if (!shop.idNullField(shop))return ReplyMessage.NullFields;
		
			shop.setCnpj(shop.onlyNumbers(shop.getCnpj()));
			if (!shop.checkCNPJ(shop))return ReplyMessage.SizeCNPJ;
		
			shop.setPhone(shop.onlyNumbers(shop.getPhone()));
			if (cityJPA.searchCity(shop))return ReplyMessage.IdCityNotExist;
		
			ShopMap.put(Integer.toString(shop.getId()), shop);
			shopJPA.update(shop);
		}catch(Exception e) {
			return ReplyMessage.DataBaseError;
		}
		return ReplyMessage.ChangedSuccessfully;
	}

}
