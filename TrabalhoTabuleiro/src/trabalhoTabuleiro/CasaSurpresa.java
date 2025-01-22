package trabalhoTabuleiro;

import java.security.SecureRandom;
import java.util.Scanner;

public class CasaSurpresa implements EventoEspecial {
    @Override
    public void executar(Jogador jogador, Tabuleiro tabuleiro) {
        System.out.printf("%s caiu na Casa Surpresa! Sorteando novo tipo...\n", jogador.getCor());
        SecureRandom random = new SecureRandom();
        int tipo = random.nextInt(3);
        
        int escolha = -1;
        Scanner scanner = new Scanner(System.in);

        while (escolha < 1 || escolha > 3) {
            System.out.println("1. Carta Esquerda");
            System.out.println("2. Carta Meio");
            System.out.println("3. Carta Direita");

            escolha = scanner.nextInt();

            if (escolha < 1 || escolha > 3) {
                System.out.println("Opção inválida! Por favor, escolha entre 1, 2 ou 3.");
            }
        }

        Jogador novoJogador;
        switch (tipo) {
            case 0:
                novoJogador = new JogadorAzarado(jogador.getCor());
                break;
            case 1:
                novoJogador = new JogadorNormal(jogador.getCor());
                break;
            case 2:
                novoJogador = new JogadorComSorte(jogador.getCor());
                break;
            default:
                throw new IllegalStateException("Erro ao sortear o tipo do jogador");
        }

        novoJogador.mover(jogador.getPosicao() - novoJogador.getPosicao());
        novoJogador.setJogadas(jogador.getJogadas());
        tabuleiro.substituirJogador(jogador, novoJogador);
        System.out.printf("%s virou %s!\n", novoJogador.getCor(), novoJogador.getClass().getSimpleName().replace("Jogador", ""));
    }
}
