package trabalhoTabuleiro;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;
    private ControleDeJogo controleDeJogo;
    private Regras regras;

    public MenuPrincipal() {
        scanner = new Scanner(System.in);
        controleDeJogo = new ControleDeJogo();
        regras = new Regras();  
    }

    public void exibirMenu() {
        System.out.println("Bem-vindo ao Jogo de Tabuleiro!");
        System.out.println("1. Iniciar Jogo");
        System.out.println("2. Ler Regras");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = scanner.nextInt();
        scanner.nextLine(); 
        
        switch(opcao) {
            case 1:
                controleDeJogo.iniciar();  
                break;
            case 2:
                regras.exibirRegras(); 
                exibirMenu();  
                break;
            case 0:
                System.out.println("Saindo do jogo...");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                exibirMenu();  
                break;
        }
    }
}
