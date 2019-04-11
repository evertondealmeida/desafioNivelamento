package controller;

import static spark.Spark.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import model.ReplyMessage;
import model.Shop;
import model.StandardResponse;
import model.StatusResponse;
import service.CityService;
import service.StateService;
import service.ShopService;
import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

public class ShopController {
	
	private static final String HTML_INDEX_HTML = "src/main/resources/public/html/index.html";

	public static void main(String[] args) throws Exception {
		final ShopService ShopService = new ShopService();
		final StateService StateService = new StateService();
		final CityService cityService = new CityService();
		staticFileLocation("/public");
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
			String helper = ShopService.updateShop(shop,request.params(":id"));
			if (helper.equals(ReplyMessage.Empty))
				return new Gson()
						.toJson(new StandardResponse(StatusResponse.SUCCESS, ReplyMessage.ChangedSuccessfully));
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, helper));
		});
		get("/getShop/:id", (request, response) -> {
			response.type("application/json");
			Shop shop = new Gson().fromJson(request.body(), Shop.class);
			shop = ShopService.getShop(request.params(":id"));

			if (shop != null) return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(shop)));
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, ReplyMessage.IdShopNotExist));
			
		});
		CorsFilter.apply();

		Spark.get("/log", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			// model.clear();
			return new ModelAndView(model, HTML_INDEX_HTML);
		}, new MustacheTemplateEngine());

		Spark.get("/citys/:id", (request, response) -> {
			response.type("application/json");
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
					new Gson().toJsonTree(cityService.listCity(request.params(":id")))));
		});

		Spark.get("/search/:city", (request, response) -> {
			response.type("application/json");
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,new Gson().toJsonTree(ShopService.listShops(request.params(":city")))));
		});

		Spark.get("/states", (request, response) -> {
			response.type("application/json");
			return new Gson().toJson(
					new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(StateService.listStates())));
		});

	}
}
