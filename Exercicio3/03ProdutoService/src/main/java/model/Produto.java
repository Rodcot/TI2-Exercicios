package model;

import java.util.Date;

public class Produto {
	public static final String DESCRICAO_PADRAO = "Novo Produto";
	public static final int MAX_ESTOQUE = 1000;
	
	private int id;
	private String descricao;
	private float preco;
	private int quantidade;
	private Date dataFabricacao;
	private Date dataValidade;

	public Produto() {
		this.id = -1;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.dataFabricacao = dataFabricacao;
		this.dataValidade = dataValidade;
	}
	
	public Produto(int id, String descricao, float preco, int quantidade, Date dataFabricacao, Date dataValidade) {
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
		this.dataFabricacao = dataFabricacao;
		this.dataValidade = dataValidade;
	}
	
	public int getid() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public float getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Date getDataFabricacao() {
		return dataFabricacao;
	}

	public Date getDataValidade() {
		return dataValidade;
	}
		
	
	public void setid(int id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		if (descricao.length() >= 3) {
			this.descricao = descricao;			
		}
	}
	
	public void setPreco(int preco) {
		if (preco > 0) {
			this.preco = preco;
		}
	}

	public void setQuantidade(int quantidade) {
		if (quantidade >= 0 && quantidade <= MAX_ESTOQUE) {
			this.quantidade = quantidade;
		}
	}

	public void setDataFabricacao(Date dataFabricacao) {
		Date agora = new Date();
		if (agora.compareTo(dataFabricacao) >= 0) {
			this.dataFabricacao = dataFabricacao;			
		}
	}
	
	public void setDataValidade(Date dataValidade) {
		if (dataFabricacao.before(dataValidade)) {
			this.dataValidade = dataValidade;			
		}
	}
}