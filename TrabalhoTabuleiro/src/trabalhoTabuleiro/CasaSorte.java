package trabalhoTabuleiro;

public class CasaSorte implements EventoEspecial {
    @Override
    public void executar(Jogador jogador, Tabuleiro tabuleiro) {
        if (!(jogador instanceof JogadorAzarado)) {
            System.out.printf("%s caiu em uma casa da sorte e avançou 3 casas!\n", jogador.getCor());
            jogador.mover(3);
        }
    }
}