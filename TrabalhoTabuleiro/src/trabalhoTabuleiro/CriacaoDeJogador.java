package trabalhoTabuleiro;

import java.util.Scanner;
import java.util.ArrayList;

public class CriacaoDeJogador {
    private Scanner scanner;
    private ArrayList<String> coresDisponiveis;
    private ArrayList<String> coresOriginais;

    public CriacaoDeJogador(Scanner scanner) {
        this.scanner = scanner;
        this.coresOriginais = new ArrayList<>();
        this.coresOriginais.add("Vermelho");
        this.coresOriginais.add("Azul");
        this.coresOriginais.add("Verde");
        this.coresOriginais.add("Amarelo");
        this.coresOriginais.add("Roxo");
        this.coresOriginais.add("Laranja");

        resetarCores();
    }

    private void resetarCores() {
        this.coresDisponiveis = new ArrayList<>(coresOriginais);
    }

    public Jogador criarJogador(int numeroJogador) {
        System.out.printf("Criação do Jogador %d\n", numeroJogador);
        System.out.println("Cores disponíveis:");

        for (int i = 0; i < coresDisponiveis.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, coresDisponiveis.get(i));
        }

        String corEscolhida = "";
        while (true) {
            System.out.printf("Escolha a cor do jogador: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            if (escolha > 0 && escolha <= coresDisponiveis.size()) {
                corEscolhida = coresDisponiveis.remove(escolha - 1);
                break;
            } else {
                System.out.println("Escolha inválida! Tente novamente.");
            }
        }

        int tipo = 0;
        while (tipo < 1 || tipo > 3) {
            System.out.printf("Escolha o tipo do jogador (1 - Azarado, 2 - Normal, 3 - Com Sorte): ");
            tipo = scanner.nextInt();
            scanner.nextLine();
        }

        Jogador jogador;
        switch (tipo) {
            case 1:
                jogador = new JogadorAzarado(corEscolhida);
                break;
            case 2:
                jogador = new JogadorNormal(corEscolhida);
                break;
            case 3:
                jogador = new JogadorComSorte(corEscolhida);
                break;
            default:
                throw new IllegalArgumentException("Tipo inválido de jogador");
        }

        return jogador;
    }

    public void resetCriacao() {
        resetarCores(); 
    }
}