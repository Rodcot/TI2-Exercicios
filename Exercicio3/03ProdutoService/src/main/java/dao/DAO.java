package dao;

import java.sql.*;
import model.Produto;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "produto";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "postgres";
		String password = "senha1234";
		boolean status = false;
		
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}
		
		return status;	
	}
	
	public boolean close() {
		boolean status = false;
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return status;
	}
	
	public boolean inserirProduto(Produto produto) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO produto (id, descricao, preco, quantidade, datafabricacao, datavalidade)"
					+ " VALUES (" + produto.getid() + ", " + "'" + produto.getDescricao()+ "'"  + ", " 
					+ produto.getPreco()  + ", " + produto.getQuantidade()
					+ ", '" + produto.getDataFabricacao() + "'"
					+ ", " + "'"  + produto.getDataValidade() + "'" +  ");";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	public boolean excluirProduto(int id) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	public Produto getProduto(int id) {
		Produto produto = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);;
			ResultSet rs = st.executeQuery("SELECT * FROM produto WHERE  id = '" + id + "'");
			rs.next();
			produto = new Produto(
				rs.getInt("id"),
				rs.getString("descricao"),
				rs.getFloat("preco"),
				rs.getInt("quantidade"),
				rs.getDate("dataFabricacao"),
				rs.getDate("dataValidade")
			);			
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return produto;
	}
	
	public Produto[] getProdutos() {
		Produto[] produtos = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);;
			ResultSet rs = st.executeQuery("SELECT * FROM produto");
			if (rs.next()) {
				rs.last(); 
				produtos = new Produto[rs.getRow()];
				rs.beforeFirst();
				for (int i = 0; rs.next(); i++) {
					produtos[i] = new Produto(
						rs.getInt("id"),
						rs.getString("descricao"),
						rs.getFloat("preco"),
						rs.getInt("quantidade"),
						rs.getDate("dataFabricacao"),
						rs.getDate("dataValidade")
					);			
				}
			}
			st.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		
		return produtos;
	}
	
	public boolean atualizarProduto(int id, String coluna, String valor) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);;
			st.executeUpdate("UPDATE produto SET " + coluna + " = '" + valor + "' WHERE id = " + id);
			status = true;
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	public int getMaxId() {
		int id = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);;
			ResultSet rs = st.executeQuery("select max(id) as max_id from produto");
			rs.next();
			id = rs.getInt("max_id");
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return id;
	}
}
