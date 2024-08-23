package app;

import java.util.List;
import java.util.Scanner; 

import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		int x = 0;
		int id = 5;
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Digite um número: ");
			System.out.println("1)Listar");
			System.out.println("2)Inserir");
			System.out.println("3)Excluir");
			System.out.println("4)Atualizar");
			System.out.println("5)Sair");
			x = sc.nextInt();
			sc.nextLine();
			switch (x) {
				case 1:
				System.out.println("\n\n==== Listar usuários === ");
				List<Usuario> usuarios = usuarioDAO.getOrderByCodigo();
				for (Usuario u: usuarios) {
					System.out.println(u.toString());
				}
				break;

				case 2:
				System.out.println("\n\n==== Inserir usuário === ");
				System.out.println("Digite um login: ");
				String nome = sc.nextLine();
				System.out.println("Digite uma senha:");
				String senha = sc.nextLine();
				System.out.println("Digite uma idade: ");
				int idade = sc.nextInt();
				sc.nextLine();
				Usuario usuario = new Usuario(id++, nome, senha,idade);
				if(usuarioDAO.insert(usuario) == true) {
				System.out.println("Inserção com sucesso -> " + usuario.toString());
				}
				break;

				case 3:
				System.out.println("\n\n==== Excluir usuário === ");
				System.out.println("Digite um código: ");
				int codigo = sc.nextInt();
				System.out.println("\n\nExcluindo usuário (código " + codigo + ")");
				usuarioDAO.delete(codigo);
				break;

				case 4:
				int y=0;
				System.out.println("\n\n==== Atualizar usuario =====");
				System.out.println("Digite um codigo: ");
				int cod = sc.nextInt();
				System.out.println("1)login");
				System.out.println("2)senha");
				y = sc.nextInt();
				sc.nextLine();
				String resp;
				switch (y) {
					case 1:
					System.out.println("Digite o novo login:");
					resp = sc.nextLine();
					System.out.println("Atualizando login (código (" + cod + ")");
					Usuario usuario2 = new Usuario();
					usuario2 = usuarioDAO.get(cod);
					usuario2.setLogin(DAO.toMD5(resp));
					usuarioDAO.update(usuario2);
					break;

					case 2:
					System.out.println("Digite a nova senha:");
					resp = sc.nextLine();
					System.out.println("Atualizando senha (código (" + cod + ")");
					Usuario usuario3 = new Usuario();
					usuario3 = usuarioDAO.get(cod);
					usuario3.setSenha(DAO.toMD5(resp));
					usuarioDAO.update(usuario3);
					break;

					default:
					System.out.println("Erro!");
					break;
				}
				break;

				case 5:
				System.out.println("Saindo");
				break;

				default:
				System.out.println("Tente novamente");
				break;
			}
		}while(x!=5);

	}
}