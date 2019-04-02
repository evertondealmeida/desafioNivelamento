package controller;

import static spark.Spark.*;

import java.util.List;
import com.google.gson.Gson;
import model.City;
import model.Shop;
import model.StandardResponse;
import model.StatusResponse;
import persistence.CityDAO;
import persistence.ConnectionDAO;
import service.CityService;
import service.StateService;
import service.ShopService;
import spark.Response;

public class ShopController {
	public static void main(String[] args) throws Exception {
		final ShopService ShopService = new ShopService();
		final StateService StateService = new StateService();
		final CityService CityService = new CityService();

		post("/shop", (request, response) -> {
			response.type("application/json");
			Shop shop = new Gson().fromJson(request.body(), Shop.class);
			if (shop.nullFields(shop)) {
				if (ShopService.insertShop(shop).equals("")) {
					return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Loja inserida com sucesso"));
				} else {
					return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, ShopService.insertShop(shop)));
				}
			}
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "Não é possível inserir campos nulos"));
		});
		put("/shop/:id", (request, response) -> {
			response.type("application/json");
			Shop shop = new Gson().fromJson(request.body(), Shop.class);
			if (!shop.nullFields(shop))
				return new Gson()
						.toJson(new StandardResponse(StatusResponse.ERROR, "Não é possível inserir campos nulos"));

			if (!ShopService.updateShop(shop).equals("Esse identificador de shop não existe")) {
				if (ShopService.updateShop(shop).equals("")) {
					return new Gson()
							.toJson(new StandardResponse(StatusResponse.SUCCESS, "Loja atualizada com sucesso"));
				} else {
					return new Gson()
							.toJson(new StandardResponse(StatusResponse.ERROR, ShopService.updateShop(shop)));
				}
			} else {
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, ShopService.updateShop(shop)));
			}

		});
		delete("/shop/:id", (request, response) -> {

			response.type("application/json");
			if (!ShopService.deleteShop(request.params(":id")).equals("Esse identificador de shop não existe")) {
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Loja deletada com sucesso"));
			} else {
				return new Gson().toJson(
						new StandardResponse(StatusResponse.ERROR, ShopService.deleteShop(request.params(":id"))));
			}
		});
		get("/citys/:id", (request, response) -> {
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Request-Method", "*");
			response.header("Access-Control-Allow-Headers", "*");
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
					new Gson().toJsonTree(CityService.listCity(request.params(":id")))));
		});
		get("/search/:state/:city", (request, response) -> {
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Request-Method", "*");
			response.header("Access-Control-Allow-Headers", "*");
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson()
					.toJsonTree(ShopService.listShops(request.params(":state"), request.params(":city")))));
		});
		get("/states", (request, response) -> {
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Request-Method", "*");
			response.header("Access-Control-Allow-Headers", "*");
			return new Gson().toJson(
					new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(StateService.listStates())));
		});
		get("/getShop/:id", (request, response) -> {
			response.type("application/json");
			Shop shop = new Gson().fromJson(request.body(), Shop.class);
			shop = ShopService.getShop(request.params(":id"));

			if (shop != null) {
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(shop)));
			} else {
				return new Gson()
						.toJson(new StandardResponse(StatusResponse.ERROR, "Esse identificador de loja não existe"));
			}
		});

	}
}
