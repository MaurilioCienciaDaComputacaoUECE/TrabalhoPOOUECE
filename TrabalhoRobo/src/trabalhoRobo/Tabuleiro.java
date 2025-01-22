package trabalhoRobo;

import java.util.Scanner;


public class Tabuleiro {
    private final int tamanho = 4;
    private final String[][] matriz;
    private int alimentoX, alimentoY;
    private Obstaculo[][] obstaculos;

    private static final String RESET = "\u001B[0m";
    private static final String VERMELHO = "\u001B[31m";
    private static final String MARROM = "\u001B[38;5;94m";
    private static final String AZUL = "\u001B[34m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String PRETO = "\u001B[30m";
    private static final String BRANCO = "\u001B[37m";

    public Tabuleiro() {
        matriz = new String[tamanho][tamanho];
        obstaculos = new Obstaculo[tamanho][tamanho];
        inicializarTabuleiro();
    }

    public void inicializarTabuleiro() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matriz[i][j] = ".";
            }
        }
    }

    public void adicionarAlimento(int x, int y) {
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho && matriz[y][x].equals(".")) {
            alimentoX = x;
            alimentoY = y;
            matriz[y][x] = "A";
        } else {
            System.out.println("Posição inválida para o alimento.");
        }
    }
    
    public boolean temAlimento(int x, int y) {
        return x == alimentoX && y == alimentoY;
    }
    
    public void adicionarObstaculo(Obstaculo obstaculo, int x, int y) {
        if (posicaoValida(x, y)) {
            if (obstaculo instanceof Bomba) {
                matriz[y][x] = VERMELHO + "B" + RESET;
            } else if (obstaculo instanceof Rocha) {
                matriz[y][x] = MARROM + "R" + RESET; 
            }
            obstaculos[y][x] = obstaculo;
        } else {
            System.out.println("Posição inválida ou já ocupada. Tente novamente.");
        }
    }

    
    public void removerRobo(Robo robo) {
        if (robo.getX() >= 0 && robo.getY() >= 0 && robo.getX() < tamanho && robo.getY() < tamanho) {
            matriz[robo.getY()][robo.getX()] = ".";  
        }
    }

    public boolean posicaoValida(int x, int y) {
        if (x < 0 || x >= tamanho || y < 0 || y >= tamanho) {
            return false;
        }
        if (x == 0 && y == 0) {
            return false;  
        }
        if (!matriz[y][x].equals(".")) {
            return false;
        }
        if (x == alimentoX && y == alimentoY) {
            return false;
        }
        return true;
    }

    public Obstaculo obterObstaculo(int x, int y) {
        if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
            return obstaculos[y][x];
        }
        return null;
    }

    public void moverRobo(Robo robo) {
        inicializarTabuleiro();

        matriz[alimentoY][alimentoX] = "A";

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (obstaculos[i][j] != null) {
                    if (obstaculos[i][j] instanceof Bomba) {
                        matriz[i][j] = VERMELHO + "B" + RESET;
                    } else if (obstaculos[i][j] instanceof Rocha) {
                        matriz[i][j] = MARROM + "R" + RESET;
                    }
                }
            }
        }

        String corAnsi = corParaAnsi(robo.getCor());
        String tipo = robo instanceof RoboInteligente ? "RI" : "RN";

        if (robo.getX() >= 0 && robo.getY() >= 0 && robo.getX() < tamanho && robo.getY() < tamanho) {
            matriz[robo.getY()][robo.getX()] = corAnsi + tipo + RESET;
        }
    }

    public void mostrarTabuleiro() {
        for (int i = tamanho - 1; i >= 0; i--) {
            for (int j = 0; j < tamanho; j++) {
                System.out.printf("%s ", matriz[i][j]);
            }
            System.out.println();
        }
    }

    private String corParaAnsi(String cor) {
        switch (cor.toLowerCase()) {
            case "vermelho":
                return VERMELHO;
            case "azul":
                return AZUL;
            case "verde":
                return VERDE;
            case "amarelo":
                return AMARELO;
            case "preto":
                return PRETO;
            case "branco":
                return BRANCO;
            default:
                return RESET;
        }
    }

    public boolean verificarColisao(Robo robo) {
        Obstaculo obstaculo = obterObstaculo(robo.getX(), robo.getY());
        if (obstaculo != null) {
            if (obstaculo instanceof Bomba) {
                obstaculo.bater(robo, this);
                removerObstaculo(obstaculo);
                return true;
            } else if (obstaculo instanceof Rocha) {
                obstaculo.bater(robo, this);
                return true;
            }
        }
        return false;
    }
    
    public void removerObstaculo(Obstaculo obstaculo) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (obstaculos[i][j] == obstaculo) {
                    obstaculos[i][j] = null;  
                    return;
                }
            }
        }
    }
}