package trabalhoTabuleiro;

public abstract class Jogador {
    private String cor;
    private int posicao;
    private int jogadas;
    private boolean pulaRodada;
    private int dadoAzul;
    private int dadoVermelho;

    public Jogador(String cor) {
        this.cor = cor;
        this.posicao = 0;
        this.jogadas = 0;
        this.pulaRodada = false;
        this.dadoAzul = 0;
        this.dadoVermelho = 0;
    }

    public String getCor() {
        return cor;
    }

    public int getPosicao() {
        return posicao;
    }
    
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getResultadoDadoAzul() {
        return dadoAzul;
    }

    public int getResultadoDadoVermelho() {
        return dadoVermelho;
    }

    public int getJogadas() {
        return jogadas;
    }

    public void setJogadas(int jogadas) {
        this.jogadas = jogadas;
    }

    public boolean isPulaRodada() {
        return pulaRodada;
    }

    public void setPulaRodada(boolean pulaRodada) {
        this.pulaRodada = pulaRodada;
    }

    public void mover(int casas) {
        posicao += casas;
        if (posicao > 40) {
            posicao = 40;
        }
        jogadas++;
    }

    public abstract int jogar(boolean isDebug);

    public void setDados(int dadoAzul, int dadoVermelho) {
        this.dadoAzul = dadoAzul;
        this.dadoVermelho = dadoVermelho;
    }
}
