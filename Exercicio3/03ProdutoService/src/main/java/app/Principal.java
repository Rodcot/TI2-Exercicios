package app;

import service.ProdutoService;
import static spark.Spark.*;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
    	port(7070);
    	
    	ProdutoService service = new ProdutoService();
    	
        get("/produtos", (req, res) -> service.getAll(req, res));
        get("/produto", (req, res) -> service.get(req, res));
        post("/produto/create", (req, res) -> service.add(req, res));
        post("/produto/update", (req, res) -> service.update(req, res));
        post("/produto/delete", (req, res) -> service.remove(req, res));
    }
}