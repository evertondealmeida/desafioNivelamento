package controller;

import static spark.Spark.*;

import java.util.List;

import com.google.gson.Gson;

import model.Cidade;
import model.Loja;
import persistence.CidadeDAO;
import persistence.Conexao;
import service.LojaService;
import service.Service;


public class LojaController {
	public static void main(String[] args) throws Exception {
		final Service LojaService = new LojaService();

        post("/Lojas", (request, response) -> {
            response.type("application/json");

            Loja Loja = new Gson().fromJson(request.body(), Loja.class);
            LojaService.inserirLoja(Loja);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/Lojas", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(LojaService.listarLojas())));
        });

        get("/Lojas/:id", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(LojaService.getLoja(request.params(":id")))));
        });

        put("/Lojas/:id", (request, response) -> {
            response.type("application/json");

            Loja toEdit = new Gson().fromJson(request.body(), Loja.class);
            Loja editedLoja = LojaService.atualizarLoja(toEdit);

            if (editedLoja != null) {
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(editedLoja)));
            } else {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Loja not found or error in edit")));
            }
        });

        delete("/Lojas/:id", (request, response) -> {
            response.type("application/json");

            LojaService.excluirLoja(request.params(":id"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Loja deleted"));
        });

        options("/Lojas/:id", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, (LojaService.obterLoja(request.params(":id"))) ? "Loja exists" : "Loja does not exists"));
        });

    }

	
}
