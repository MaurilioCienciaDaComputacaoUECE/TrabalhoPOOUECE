package trabalhoTabuleiro;

import java.util.ArrayList;

public class GerenciadorDeJogadores {
    private ArrayList<Jogador> jogadores;

    public GerenciadorDeJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Jogador getJogadorMaisAtras(Jogador jogador) {
        Jogador maisAtrasado = null;
        for (Jogador j : jogadores) {
            if (j != jogador && (maisAtrasado == null || j.getPosicao() < maisAtrasado.getPosicao())) {
                maisAtrasado = j;
            }
        }
        return maisAtrasado;
    }

    public Jogador getJogadorPorCor(String cor) {
        for (Jogador jogador : jogadores) {
            if (jogador.getCor().equals(cor)) {
                return jogador;
            }
        }
        return null;
    }

    public void substituirJogador(Jogador antigo, Jogador novo) {
        int index = jogadores.indexOf(antigo);
        if (index != -1) {
            jogadores.set(index, novo);
        }
    }
}