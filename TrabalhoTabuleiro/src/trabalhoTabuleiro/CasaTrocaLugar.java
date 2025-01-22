package trabalhoTabuleiro;

public class CasaTrocaLugar implements EventoEspecial {
    @Override
    public void executar(Jogador jogador, Tabuleiro tabuleiro) {
        Jogador maisAtrasado = tabuleiro.getJogadorMaisAtras(jogador);
        if (maisAtrasado != null && jogador.getPosicao() > maisAtrasado.getPosicao()) {
            int posicaoAtual = jogador.getPosicao();
            jogador.mover(maisAtrasado.getPosicao() - posicaoAtual);
            maisAtrasado.mover(posicaoAtual - maisAtrasado.getPosicao());
            System.out.printf("%s trocou de lugar com %s!\n", jogador.getCor(), maisAtrasado.getCor());
        } else {
            System.out.printf("%s está na última posição e não pode trocar de lugar.\n", jogador.getCor());
        }
    }
}
