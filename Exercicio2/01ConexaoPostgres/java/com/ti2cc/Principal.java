import java.util.*;

package com.ti2cc;

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
				String inserirLogin = sc.nextLine();
				System.out.println("Digite a senha: ");
				String inserirSenha = sc.nextLine();
				System.out.println("Digite o sexo: ");
				char inserirSexo = sc.next().charAt(0);
				System.out.println("Digite a idade: ");
				int inserirIdade = sc.nextInt();
				Usuario usuario = new Usuario(inserirCodigo, inserirLogin, inserirSenha, inserirSexo, inserirIdade);
				if(dao.inserirUsuario(usuario) == true) {
					System.out.println("Inserção com sucesso -> " + usuario.toString());
				}
			}

			//Atualizar usuário
			if(option == 4) {
				System.out.print("Digite o código do usuário para atualizar: ");
				codigo = sc.nextInt();
				System.out.print("Digite 1.login/2.senha/3.sexo/4.idade para atualizar: ");
				String option = sc.nextInt();
				if(option == 1){
					System.out.print("Digite novo login: ");
					String novoLogin = sc.nextLine();
					dao.setLogin(novoLogin);
				}else if(option == 2){
					System.out.print("Digite nova senha: ");
					String novaSenha = sc.nextLine();
					dao.setSenha(novaSenha);
				}else if(option == 3){
					System.out.print("Digite novo valor de sexo: ");
					char novoSexo = sc.next().charAt(0);
					dao.setSexo(novoSexo);
				}else if(option == 4){
					System.out.print("Digite nova idade: ");
					int novaIdade = sc.nextInt();
					dao.setIdade(novaIdade);
				}else{
					System.out.print("Houve um problema na atualização");
				}
				dao.atualizarUsuario(codigo);
			}
			
			//Excluir usuário
			if(option == 3) {
				System.out.println("Digite codigo do usuario a ser excluido: ");
				int codigoExcluir = sc.nextInt();
				dao.excluirUsuario(codigoExcluir);
			}
			
			//Mostrar usuários
			if(option == 1) {
				Usuario[] usuarios = dao.getUsuarios();
				System.out.println("==== Mostrar usuários === ");		
				for(int i = 0; i < usuarios.length; i++) {
					System.out.println(usuarios[i].toString());
				}
			}
			
		}
		
		dao.close();
	}
}
