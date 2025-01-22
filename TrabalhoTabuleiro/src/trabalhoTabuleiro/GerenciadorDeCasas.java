package trabalhoTabuleiro;

import java.util.ArrayList;

public class GerenciadorDeCasas {

    public void executarEventoCasa(Jogador jogador, Tabuleiro tabuleiro) {
        switch (jogador.getPosicao()) {
            case 10:
            case 25:
            case 38:
                new CasaPerdeVez().executar(jogador, tabuleiro);
                break;
            case 13:
                new CasaSurpresa().executar(jogador, tabuleiro);
                break;
            case 5:
            case 15:
            case 30:
                new CasaSorte().executar(jogador, tabuleiro);
                break;
            case 17:
            case 27:
                new CasaVoltarInicio().executar(jogador, tabuleiro);
                break;
            case 20:
            case 35:
                new CasaTrocaLugar().executar(jogador, tabuleiro);
                break;
            default:
                break;
        }
    }
}