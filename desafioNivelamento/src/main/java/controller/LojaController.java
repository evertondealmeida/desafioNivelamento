package controller;

import static spark.Spark.*;

import java.util.List;

import com.google.gson.Gson;

import model.Cidade;
import model.Loja;
import persistence.CidadeDAO;
import persistence.Conexao;
import service.CidadeService;
import service.EstadoService;
import service.LojaService;
import spark.Response;


public class LojaController {
	public static void main(String[] args) throws Exception {
		final LojaService LojaService = new LojaService();
		final EstadoService EstadoService = new EstadoService();
		final CidadeService CidadeService = new CidadeService();

        post("/Lojas/Inserir", (request, response) -> {
            response.type("application/json");
            Loja Loja = new Gson().fromJson(request.body(), Loja.class);
            LojaService.inserirLoja(Loja);
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });
        put("/Lojas/Editar/:id", (request, response) -> {
            response.type("application/json");
            Loja toEdit = new Gson().fromJson(request.body(), Loja.class);
            Loja loja = LojaService.atualizarLoja(toEdit);
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });
        delete("/Lojas/Excluir/:id", (request, response) -> {
            response.type("application/json");
            LojaService.excluirLoja(request.params(":id"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Loja deleted"));
        });       
        get("/Lojas/Cidades/:id", (request, response) -> {   	
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(CidadeService.listarCidade(request.params(":id")))));
        });
        get("/Lojas/Procurar/:estado/:cidade", (request, response) -> {
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(LojaService.listarLojas(request.params(":estado"),request.params(":cidade")))));
        });      
        get("/Lojas/Estados", (request, response) -> {
        	response.type("application/json");
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(EstadoService.listarEstados())));
        });              
        get("/Lojas/ObterLoja/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(LojaService.obterLoja(request.params(":id")))));
        });
	
    }	
}
