package controller;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import model.City;
import model.State;
import model.Log;
import model.ReplyMessage;
import model.Shop;
import model.StandardResponse;
import model.StatusResponse;
import service.CityService;
import service.LogService;
import service.StateService;
import service.ShopService;
import spark.ModelAndView;
import spark.Spark;
import spark.TemplateEngine;
import spark.template.mustache.MustacheTemplateEngine;

public class ShopController {

	public static void main(String[] args) {
		final ShopService ShopService = new ShopService();
		final StateService StateService = new StateService();
		final CityService cityService = new CityService();
		final LogService logService = new LogService();
		staticFileLocation("/public");

		post("/shop", (request, response) -> {
			response.type("application/json");
			Shop shop = new Gson().fromJson(request.body(), Shop.class);
			String helper = ShopService.insertShop(shop);
			StatusResponse status = helper.equals(ReplyMessage.InsertedSuccessfully)? StatusResponse.SUCCESS : StatusResponse.ERROR;
			logService.insertLog("/Shop",status.getStatus(),  helper);
		    return new Gson().toJson(new StandardResponse(status,  helper));
		});
		
		get("/getShop/:id", (request, response) -> {
			try {
				response.type("application/json");
				Shop shop = new Gson().fromJson(request.body(), Shop.class);
				shop = ShopService.getShop(request.params(":id"));
				String helper = shop != null? ReplyMessage.SeeSuccessfully : ReplyMessage.IdShopNotExist;
				StatusResponse status = shop != null? StatusResponse.NOTE : StatusResponse.ERROR;	
				logService.insertLog("/getShop/" + request.params(":id"), status.getStatus(),helper);
				return shop != null? 
						new Gson().toJson(new StandardResponse(status, new Gson().toJsonTree(shop))):
						new Gson().toJson(new StandardResponse(status,helper));
			}catch(Exception e) {
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,ReplyMessage.DataBaseError));
			}

		});

		delete("/shop/:id", (request, response) -> {
			response.type("application/json");
			String helper = ShopService.deleteShop(request.params(":id"));
			StatusResponse status = helper.equals(ReplyMessage.InsertedSuccessfully)? StatusResponse.SUCCESS : StatusResponse.ERROR;
			logService.insertLog("/Shop/" + request.params(":id"),status.getStatus(),helper);
			return new Gson().toJson(new StandardResponse(status, helper));
		});

		put("/shop/:id", (request, response) -> {
			response.type("application/json");
			Shop shop = new Gson().fromJson(request.body(), Shop.class);
			String helper = ShopService.updateShop(shop, request.params(":id"));
			StatusResponse status = helper.equals(ReplyMessage.ChangedSuccessfully)? StatusResponse.SUCCESS : StatusResponse.ERROR;
			logService.insertLog("/Shop/"+ request.params(":id"),status.getStatus(),  helper);
		    return new Gson().toJson(new StandardResponse(status,  helper));
		});
		
		CorsFilter.apply();
		TemplateEngine engine = new MustacheTemplateEngine();
		Spark.get("/log", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.clear();
			List<Log> logs = new ArrayList<Log>();
			logs.addAll(logService.listLog());
			model.put("listLog", logs);
			return new ModelAndView(model, ReplyMessage.INDEX_HTML);
		}, engine);

		Spark.get("/citys/:id", (request, response) -> {
			response.type("application/json");
			try {
				Collection<City> citys = cityService.listCity(request.params(":id"));
				String helper = citys != null? ReplyMessage.ReturnsListSuccessfully : ReplyMessage.IdCityNotExist;
				StatusResponse status = helper.equals(ReplyMessage.ReturnsListSuccessfully)? StatusResponse.NOTE : StatusResponse.ERROR;
				logService.insertLog("/citys/" + request.params(":id"), status.getStatus(),helper);
				return citys != null? 
						new Gson().toJson(new StandardResponse(status, new Gson().toJsonTree(citys))):
						new Gson().toJson(new StandardResponse(status,helper));
			}catch(Exception e) {
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,ReplyMessage.DataBaseError));
			}				
		});

		Spark.get("/search/:city", (request, response) -> {
			response.type("application/json");
			try {
				Collection<Shop> shops = ShopService.listShops(request.params(":city"));
				String helper = shops != null? ReplyMessage.ReturnsListSuccessfully : ReplyMessage.IdStateNotExist;
				StatusResponse status = helper.equals(ReplyMessage.ReturnsListSuccessfully)? StatusResponse.NOTE : StatusResponse.ERROR;
				logService.insertLog("/search/" + request.params(":city"), status.getStatus(),helper);
				return shops != null? 
						new Gson().toJson(new StandardResponse(status, new Gson().toJsonTree(shops))):
						new Gson().toJson(new StandardResponse(status,helper));
			}catch(Exception e) {
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,ReplyMessage.DataBaseError));
			}				
		});

		Spark.get("/states", (request, response) -> {
			response.type("application/json");
			try {
				Collection<State> states = StateService.listStates();
				String helper = states != null? ReplyMessage.ReturnsListSuccessfully : ReplyMessage.StateNotExist;
				StatusResponse status = helper.equals(ReplyMessage.ReturnsListSuccessfully)? StatusResponse.NOTE : StatusResponse.ERROR;
				logService.insertLog("/states", status.getStatus(),helper);
				return states != null? 
						new Gson().toJson(new StandardResponse(status, new Gson().toJsonTree(states))):
						new Gson().toJson(new StandardResponse(status,helper));
			}catch(Exception e) {
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,ReplyMessage.DataBaseError));
			}				
		});
		notFound((request, response) -> {
			response.type("application/json");
			logService.insertLog("/", StatusResponse.ERROR.getStatus(), ReplyMessage.RoteNotExist);
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, ReplyMessage.RoteNotExist));
		});

	}
}
