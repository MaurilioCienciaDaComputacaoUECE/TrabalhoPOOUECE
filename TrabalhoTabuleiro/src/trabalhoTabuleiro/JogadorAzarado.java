package trabalhoTabuleiro;

import java.security.SecureRandom;

public class JogadorAzarado extends Jogador {
    private SecureRandom random;

    public JogadorAzarado(String cor) {
        super(cor);
        random = new SecureRandom();
    }

    @Override
    public int jogar(boolean isDebug) {
        int dadoAzul = random.nextInt(3) + 1;
        int dadoVermelho = random.nextInt(3) + 1;

        setDados(dadoAzul, dadoVermelho);

        int somaDados = dadoAzul + dadoVermelho;

        if (isDebug) {
            return somaDados;
        }

        mover(somaDados);

        return somaDados;
    }
}