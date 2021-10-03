package ti2cc;

import java.util.*;

public class Principal {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DAO dao = new DAO();
		dao.conectar();
		
		int option = 0;
		
		while(option != 5) {
			System.out.println("1.Listar");
			System.out.println("2.Inserir");
			System.out.println("3.Excluir");
			System.out.println("4.Atualizar");
			System.out.println("5.Sair");
			System.out.print(": ");
			
			option = sc.nextInt();
		
			//Inserir um elemento na tabela
			if(option == 2) {
				System.out.println("Digite o codigo: ");
				int inserirCodigo = sc.nextInt();
				System.out.println("Digite o login: ");
				String inserirLogin = sc.next();
				System.out.println("Digite a senha: ");
				String inserirSenha = sc.next();
				System.out.println("Digite o sexo: ");
				char inserirSexo = sc.next().charAt(0);
				System.out.println("Digite a idade: ");
				int inserirIdade = sc.nextInt();
				Usuario usuario = new Usuario(inserirCodigo, inserirLogin, inserirSenha, inserirSexo, inserirIdade);
				if(dao.inserirUsuario(usuario) == true) {
					System.out.println("Inser��o com sucesso -> " + usuario.toString());
				}
			}

			//Atualizar usu�rio
			if(option == 4) {
				System.out.print("Digite o c�digo do usu�rio para atualizar: ");
				int codigo = sc.nextInt();
				System.out.print("Digite novo login: ");
				String novoLogin = sc.next();
				System.out.print("Digite nova senha: ");
				String novaSenha = sc.next();
				System.out.print("Digite novo valor de sexo: ");
				char novoSexo = sc.next().charAt(0);
				System.out.print("Digite nova idade: ");
				int novaIdade = sc.nextInt();
				Usuario usuario = new Usuario(codigo, novoLogin, novaSenha, novoSexo, novaIdade);
				dao.atualizarUsuario(usuario);
			}
			
			//Excluir usu�rio
			if(option == 3) {
				System.out.println("Digite codigo do usuario a ser excluido: ");
				int codigoExcluir = sc.nextInt();
				
				boolean resposta = dao.excluirUsuario(codigoExcluir);
				if(resposta == true) {
					System.out.println("Usuario excluido com sucesso.");
				}else if(resposta == false) {
					System.out.println("N�o foi poss�vel excluir o usuario.");
				}else{
					System.out.println("Ocorreu algum problema.");
				}
			}
			
			//Mostrar usu�rios
			if(option == 1) {
				Usuario[] usuarios = dao.getUsuarios();
				if(usuarios == null) {
					System.out.println("Nenhum usuario encontrado.");
				}else {
					System.out.println("==== Mostrar usu�rios === ");		
					for(int i = 0; i < usuarios.length; i++) {
						System.out.println(usuarios[i].toString());
					}
				}
			}
			
		}
		System.out.println("Sa�da com sucesso.");
		dao.close();
	}
}