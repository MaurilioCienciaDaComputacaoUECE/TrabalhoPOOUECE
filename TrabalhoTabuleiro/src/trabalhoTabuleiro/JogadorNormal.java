package trabalhoTabuleiro;

import java.security.SecureRandom;

public class JogadorNormal extends Jogador {
    private SecureRandom random;

    public JogadorNormal(String cor) {
        super(cor);
        random = new SecureRandom();
    }

    @Override
    public int jogar(boolean isDebug) {
        int dadoAzul = random.nextInt(6) + 1;
        int dadoVermelho = random.nextInt(6) + 1;

        setDados(dadoAzul, dadoVermelho);

        int somaDados = dadoAzul + dadoVermelho;

        if (isDebug) {
            return somaDados;
        }

        mover(somaDados);

        return somaDados;
    }
}