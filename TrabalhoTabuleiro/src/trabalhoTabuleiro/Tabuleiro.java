package trabalhoTabuleiro;

import java.util.ArrayList;

public class Tabuleiro {
    private ArrayList<Jogador> jogadores;
    private GerenciadorDeCasas gerenciadorDeCasas;
    private GerenciadorDeJogadores gerenciadorDeJogadores;

    public Tabuleiro(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
        this.gerenciadorDeCasas = new GerenciadorDeCasas();
        this.gerenciadorDeJogadores = new GerenciadorDeJogadores(jogadores);
    }

    public void checarCasasEspeciais(Jogador jogador) {
        gerenciadorDeCasas.executarEventoCasa(jogador, this);
    }

    public void exibirPosicoes() {
        for (Jogador jogador : jogadores) {
            System.out.printf("%s está na casa %d.\n", jogador.getCor(), jogador.getPosicao());
            System.out.printf("Número de jogadas de %s: %d\n", jogador.getCor(), jogador.getJogadas());
        }
    }

    public Jogador getJogadorMaisAtras(Jogador jogador) {
        return gerenciadorDeJogadores.getJogadorMaisAtras(jogador);
    }

    public Jogador getJogadorPorCor(String cor) {
        return gerenciadorDeJogadores.getJogadorPorCor(cor);
    }

    public void substituirJogador(Jogador antigo, Jogador novo) {
        gerenciadorDeJogadores.substituirJogador(antigo, novo);
    }
}