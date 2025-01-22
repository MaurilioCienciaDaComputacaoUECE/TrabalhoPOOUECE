package trabalhoTabuleiro;

import java.security.SecureRandom;

public class JogadorComSorte extends Jogador {
    private SecureRandom random;

    public JogadorComSorte(String cor) {
        super(cor);
        random = new SecureRandom();
    }

    @Override
    public int jogar(boolean isDebug) {
        int dadoAzul = random.nextInt(3) + 4;
        int dadoVermelho = random.nextInt(3) + 4;

        setDados(dadoAzul, dadoVermelho);

        int somaDados = dadoAzul + dadoVermelho;

        if (isDebug) {
            return somaDados;
        }

        mover(somaDados);

        return somaDados;
    }
}