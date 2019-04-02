package service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.Shop;
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
		String retorno;
		shop.setCnpj(shop.onlyNumbers(shop.getCnpj()));
		retorno = shop.checkCNPJ(shop) ? "" : "CNPJ deve conter 14 digitos";
		shop.setPhone(shop.onlyNumbers(shop.getPhone()));
		retorno += cityDAO.searchCity(shop.getCodeCity()) == true ? "": (retorno == "" ? "Esse código de city não existe" : ", Esse código de city não existe");
		if (retorno.equals("")) {
			ShopMap.put(Integer.toString(shop.getId()), shop);
			dao.insertShop(shop);
		}
		return retorno;
	}

	public Collection<Shop> listShops(String codeState, String codeCity) {
		ShopMap.clear();
		codeState = codeState != null ? codeState : "0";
		codeCity = codeCity != null ? codeCity : "0";
		try {
			List<Shop> vetShop;
			vetShop = dao.listShop(Integer.parseInt(codeState), Integer.parseInt(codeCity));
			for (int i = 0; i < vetShop.size(); i++) {
				Shop aux = vetShop.get(i);
				ShopMap.put(Integer.toString(aux.getId()), aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ShopMap.values();
	}

	public Shop getShop(String id) {
		Shop shop = new Shop();
		try {
			shop = dao.getShop(Integer.parseInt(id));
			ShopMap.put(Integer.toString(shop.getId()), shop);
			System.out.println("Shop:" + shop.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ShopMap.get(id);
	}

	public String deleteShop(String id) throws NumberFormatException, Exception {
		String retorno;
		retorno = dao.checkShop(Integer.parseInt(id)) == true ? "" : "Esse identificador de shop não existe";
		if (retorno.equals("")) {
			dao.delete(Integer.parseInt(id));
			ShopMap.remove(id);
			return retorno;
		} else {
			return retorno;
		}
	}

	public String updateShop(Shop shop) throws Exception {
		boolean validFields = true;
		String retorno;
		shop.setCnpj(shop.onlyNumbers(shop.getCnpj()));
		retorno = dao.checkShop(shop.getId()) == true ? "" : "Esse identificador de shop não existe";
		if (retorno.equals("")) {
			retorno = shop.checkCNPJ(shop) == true ? "" : "CNPJ deve conter 14 digitos";
			shop.setPhone(shop.onlyNumbers(shop.getPhone()));
			retorno += cityDAO.searchCity(shop.getCodeCity()) == true ? ""
					: (retorno == "" ? "Esse código de city não existe" : ", Esse código de city não existe");
			if (retorno.equals("")) {
				ShopMap.put(Integer.toString(shop.getId()), shop);
				dao.update(shop);
			}
			return retorno;
		}
		return retorno;

	}

}
