package service;

import static spark.Spark.*;

import model.Produto;
import dao.DAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import spark.Request;
import spark.Response;

public class ProdutoService {
	private DAO Dao;
	
	public ProdutoService() {
		Dao = new DAO();
		Dao.conectar();
	}
	
	public Object add(Request request, Response response) throws ParseException {
		String descricao = request.queryParams("descricao");
		int quantidade = Integer.valueOf(request.queryParams("quantidade"));
		float preco = Float.valueOf(request.queryParams("preco"));
		Date dataFabricacao = new SimpleDateFormat("dd/MM/yyyy").parse(request.queryParams("dataFabricacao"));
		Date dataValidade = new SimpleDateFormat("dd/MM/yyyy").parse(request.queryParams("dataValidade"));
		int id = Dao.getMaxId() + 1;
		
		Produto produto = new Produto(id, descricao, preco, quantidade, dataFabricacao, dataValidade);
		Dao.inserirProduto(produto);
		
		response.status(201);
		
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.valueOf(request.queryParams("id"));
		Produto produto = Dao.getProduto(id);
		
		if (produto != null) {
			response.header("Content-Type", "application/xml");
			response.header("Content-Encoding", "UTF-8");

			return "<produto>\n" +
					"\t<id>" + produto.getid() + "\t</id>\n" +
					"\t<descricao>" + produto.getDescricao() + "\t</descricao>\n" +
					"\t<preco>" + produto.getPreco() + "\t</preco>\n" +
					"\t<quantidade>" + produto.getQuantidade() + "\t</quantidade>\n" +
					"\t<fabricacao>" + produto.getDataFabricacao() + "\t</fabricacao>\n" +
					"\t<validade>" + produto.getDataValidade() + "\t</validade>\n" +
					"</produto>";
		} else {
			response.status(404);
			return "Produto nao encontrado";
		}
	}
	
	public Object update(Request request, Response response) {
		int id = Integer.valueOf(request.queryParams("id"));
		String coluna = request.queryParams("coluna");
		String valor = request.queryParams("valor");
		
		boolean status = Dao.atualizarProduto(id, coluna, valor);
		
		if (status) {
			return id;
		} else {
			response.status(404);
			return "Produto nao encontrado";
		}
	}
	
	public Object remove(Request request, Response response) {
		int id = Integer.valueOf(request.queryParams("id"));
		
		boolean status = Dao.excluirProduto(id);
		
		if (status) {
			return id;
		} else {
			response.status(404);
			return "Produto nao encontrado";
		}
	}
	
	public Object getAll(Request request, Response response) {
		StringBuffer produtos = new StringBuffer("<produtos type=\"array\">");
		for (Produto produto : Dao.getProdutos()) {
			produtos.append(
					"<produto>\n" +
					"\t<id>" + produto.getid() + "\t</id>\n" +
					"\t<descricao>" + produto.getDescricao() + "\t</descricao>\n" +
					"\t<preco>" + produto.getPreco() + "\t</preco>\n" +
					"\t<quantidade>" + produto.getQuantidade() + "\t</quantidade>\n" +
					"\t<fabricacao>" + produto.getDataFabricacao() + "\t</fabricacao>\n" +
					"\t<validade>" + produto.getDataValidade() + "\t</validade>\n" +
					"</produto>"
			);
		}
		produtos.append("</produtos>");
		response.header("Content-Type", "application/xml");
		response.header("Content-Encoding", "UTF-8");
		return produtos.toString();
	}
	
}