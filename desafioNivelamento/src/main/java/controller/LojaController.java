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
            Loja loja = new Gson().fromJson(request.body(), Loja.class);
            if(LojaService.verificaJSON(loja)) {
            	if(LojaService.inserirLoja(loja).equals("")) {
            		return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"Loja atualizada com sucesso"));
            	}else {
            		return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, LojaService.inserirLoja(loja)));
            	}
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,"Não é possível inserir campos nulos"));
        });
        put("/Lojas/Editar/:id", (request, response) -> {
            response.type("application/json");
            Loja loja = new Gson().fromJson(request.body(), Loja.class);
            if(LojaService.verificaJSON(loja)) {
            	if(!LojaService.atualizarLoja(loja).equals("Esse identificador de loja não existe")) {
	            	if(LojaService.atualizarLoja(loja).equals("")) {
	            		return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,"Loja atualizada com sucesso"));
	            	}else {
	            		return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, LojaService.atualizarLoja(loja)));
	            	}
            	}else {
            		return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, LojaService.atualizarLoja(loja)));
            	}
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,"Não é possível inserir campos nulos"));
        });
        delete("/Lojas/Excluir/:id", (request, response) -> {
        	
            response.type("application/json");
            if(!LojaService.excluirLoja(request.params(":id")).equals("Esse identificador de loja não existe")) {
             return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Loja deletada com sucesso"));
            }else{
            	return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, LojaService.excluirLoja(request.params(":id"))));
            }
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
            Loja loja = new Gson().fromJson(request.body(), Loja.class);
            loja = LojaService.obterLoja(request.params(":id"));

            if(loja != null) {
            	return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(loja)));
            }else {
            	return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "Esse identificador de loja não existe"));
            }
        });
	
    }	
}
