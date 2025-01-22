package trabalhoTabuleiro;

public class CasaPerdeVez implements EventoEspecial {
    @Override
    public void executar(Jogador jogador, Tabuleiro tabuleiro) {
        System.out.printf("%s perdeu a próxima rodada!\n", jogador.getCor());
        jogador.setPulaRodada(true);
    }
}