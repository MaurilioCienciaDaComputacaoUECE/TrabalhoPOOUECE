package trabalhoRobo;

public class Robo {
    private int x;
    private int y;
    private String cor;
    private int posXAnterior;
    private int posYAnterior;
    private int movimentosInvalidos = 0;

    public Robo(String cor) {
        this.cor = cor;
        this.x = 0;
        this.y = 0; 
        this.posXAnterior = -1;
        this.posYAnterior = -1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void mover(String direcao) throws MovimentoInvalidoException {
        posXAnterior = x;
        posYAnterior = y;

        switch (direcao) {
            case "up":
                if (y + 1 >= 4) throw new MovimentoInvalidoException("up");
                y++;
                break;
            case "down":
                if (y - 1 < 0) throw new MovimentoInvalidoException("down");
                y--;
                break;
            case "right":
                if (x + 1 >= 4) throw new MovimentoInvalidoException("right");
                x++;
                break;
            case "left":
                if (x - 1 < 0) throw new MovimentoInvalidoException("left");
                x--;
                break;
            default:
                throw new IllegalArgumentException("Direção desconhecida: " + direcao);
        }

        System.out.printf("Posição atual: (%d, %d)\n", x, y);
    }

    public void mover(int direcao) throws MovimentoInvalidoException {
        posXAnterior = x;
        posYAnterior = y;

        switch (direcao) {
            case 1:
                if (y + 1 >= 4) throw new MovimentoInvalidoException("up");
                y++;
                break;
            case 2:
                if (y - 1 < 0) throw new MovimentoInvalidoException("down");
                y--;
                break;
            case 3:
                if (x + 1 >= 4) throw new MovimentoInvalidoException("right");
                x++;
                break;
            case 4:
                if (x - 1 < 0) throw new MovimentoInvalidoException("left");
                x--;
                break;
            default:
                throw new IllegalArgumentException("Direção inválida. Use 1, 2, 3 ou 4.");
        }

        System.out.printf("Posição atual: (%d, %d)\n", x, y);
    }

    public void incrementarMovimentoInvalido() {
        this.movimentosInvalidos++;
    }

    public boolean encontrouAlimento(int alimentoX, int alimentoY) {
        return this.x == alimentoX && this.y == alimentoY;
    }

    public void voltarPosicao() {
        this.x = posXAnterior;
        this.y = posYAnterior;
        System.out.printf("Robô voltou para a posição anterior: (%d, %d)\n", x, y);
    }

    public int getMovimentosInvalidos() {
        return this.movimentosInvalidos;
        
      
    }
}
