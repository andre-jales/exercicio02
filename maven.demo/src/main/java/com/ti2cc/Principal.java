package com.ti2cc;

import java.util.*;

public class Principal {

	public static void main(String[] args) {
		DAO dao = new DAO();
        dao.conectar();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1) Inserir");
            System.out.println("2) Listar");
            System.out.println("3) Atualizar");
            System.out.println("4) Excluir");
            System.out.println("5) Sair");
            System.out.print("Escolha uma op��o: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n=== Inserir ===");
                    System.out.print("Digite o c�digo: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o login: ");
                    String login = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Digite o sexo: ");
                    char sexo = scanner.nextLine().charAt(0);

                    Usuario usuario = new Usuario(codigo, login, senha, sexo);
                    if (dao.inserirUsuario(usuario)) {
                        System.out.println("Inser��o com sucesso: " + usuario);
                    } else {
                        System.out.println("Erro ao inserir usu�rio.");
                    }
                    break;
                case 2:
                    System.out.println("\n=== Listar ===");
                    Usuario[] usuarios = dao.getUsuarios();
                    for (Usuario u : usuarios) {
                        System.out.println(u);
                    }
                    break;
                case 3:
                    System.out.println("\n=== Atualizar ===");
                    System.out.print("Digite o c�digo do usu�rio a ser atualizado: ");
                    int codigoAtualizacao = scanner.nextInt();
                    scanner.nextLine();
                    
                    Usuario usuarioAtualizar = dao.getUsuarioByCodigo(codigoAtualizacao);

                    if (usuarioAtualizar != null) {
                        System.out.print("Digite a nova senha: ");
                        String novaSenha = scanner.nextLine();
                        usuarioAtualizar.setSenha(novaSenha);

                        if (dao.atualizarUsuario(usuarioAtualizar)) {
                            System.out.println("Atualiza��o realizada com sucesso.");
                        } else {
                            System.out.println("Erro ao atualizar usu�rio.");
                        }
                    } else {
                        System.out.println("Usu�rio n�o encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("\n=== Excluir ===");
                    System.out.print("Digite o c�digo do usu�rio a ser exclu�do: ");
                    int codigoExclusao = scanner.nextInt();
                    if (dao.excluirUsuario(codigoExclusao)) {
                        System.out.println("Usu�rio exclu�do com sucesso.");
                    } else {
                        System.out.println("Erro ao excluir usu�rio.");
                    }
                    break;
                case 5:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    System.out.println("\nOp��o inv�lida. Escolha novamente.");
            }

        } while (opcao != 5);

        dao.close();
        scanner.close();
	}

}
