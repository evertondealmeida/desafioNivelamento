package controller;

import static spark.Spark.*;

import java.util.List;
import com.google.gson.Gson;
import model.ReplyMessage;
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
		final CityService cityService = new CityService();

		post("/shop", (request, response) -> {
			response.type("application/json");
			Shop shop = new Gson().fromJson(request.body(), Shop.class);
			String helper = ShopService.insertShop(shop);
			if (helper.equals(ReplyMessage.Empty))
				return new Gson()
						.toJson(new StandardResponse(StatusResponse.SUCCESS, ReplyMessage.InsertedSuccessfully));
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, helper));
		});

		delete("/shop/:id", (request, response) -> {
			response.type("application/json");
			String helper = ShopService.deleteShop(request.params(":id"));
			if (helper.equals(ReplyMessage.Empty))
				return new Gson()
						.toJson(new StandardResponse(StatusResponse.SUCCESS, ReplyMessage.DeletedSuccessfully));
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, helper));
		});

		put("/shop/:id", (request, response) -> {
			response.type("application/json");
			Shop shop = new Gson().fromJson(request.body(), Shop.class);
			String helper = ShopService.updateShop(shop);
			if (helper.equals(ReplyMessage.Empty))
				return new Gson()
						.toJson(new StandardResponse(StatusResponse.SUCCESS, ReplyMessage.ChangedSuccessfully));
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, helper));
		});

		get("/citys/:id", (request, response) -> {
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Request-Method", "*");
			response.header("Access-Control-Allow-Headers", "*");
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
					new Gson().toJsonTree(cityService.listCity(request.params(":id")))));
		});
		get("/search/:state/:city", (request, response) -> {
			response.type("application/json");
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Request-Method", "*");
			response.header("Access-Control-Allow-Headers", "*");
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
					new Gson().toJsonTree(ShopService.listShops(request.params(":state"), request.params(":city")))));
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
