package trabalhoRobo;

import java.security.SecureRandom;

public class RoboInteligente extends Robo {
    private String ultimaDirecao;
    private String ultimaDirecaoInvalida; 

    public RoboInteligente(String cor) {
        super(cor);
        this.ultimaDirecao = "";
        this.ultimaDirecaoInvalida = null;
    }

    @Override
    public void mover(String direcao) throws MovimentoInvalidoException {
        try {
            super.mover(direcao);
            ultimaDirecao = direcao; 
            ultimaDirecaoInvalida = null; 
        } catch (MovimentoInvalidoException e) {
            incrementarMovimentoInvalido();
            ultimaDirecaoInvalida = direcao; 
            System.out.printf("Movimento inválido do Robô Inteligente: %s%n", direcao);
            throw e;
        }
    }

    public String gerarNovaDirecao() {
        SecureRandom random = new SecureRandom();
        String[] direcoes = {"up", "down", "left", "right"};
        String novaDirecao;

        do {
            novaDirecao = direcoes[random.nextInt(4)];
        } while (novaDirecao.equals(ultimaDirecaoInvalida)); 

        return novaDirecao;
    }
}
