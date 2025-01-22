package trabalhoTabuleiro;

import java.util.Scanner;

public class CasaVoltarInicio implements EventoEspecial {
    @Override
    public void executar(Jogador jogador, Tabuleiro tabuleiro) {
        System.out.printf("%s caiu em uma Casa Voltar ao Início! Escolha um jogador para voltar ao início.\n", jogador.getCor());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("Digite a cor do jogador: ");
            String corEscolhida = scanner.nextLine();

            Jogador escolhido = tabuleiro.getJogadorPorCor(corEscolhida);
            if (escolhido != null && !escolhido.equals(jogador)) {
                escolhido.mover(-escolhido.getPosicao());
                System.out.printf("%s voltou para o início!\n", corEscolhida);
                break;
            } else {
                System.out.printf("Jogador inválido ou você não pode escolher seu próprio jogador.\n");
            }
        }
    }
}
