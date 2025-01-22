package trabalhoRobo;

import java.util.Scanner;

public class MainComUmRobo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String cor = Cor.escolherCor();
        Robo robo = new Robo(cor);
        Tabuleiro tabuleiro = new Tabuleiro();
        int alimentoX = -1, alimentoY = -1;
        boolean posicaoValida = false;
        
        while (!posicaoValida) {
            System.out.print("Informe a posição X do alimento (0 a 3): ");
            alimentoX = scanner.nextInt();
            System.out.print("Informe a posição Y do alimento (0 a 3): ");
            alimentoY = scanner.nextInt();

            if (alimentoX >= 0 && alimentoX < 4 && alimentoY >= 0 && alimentoY < 4) {
                    tabuleiro.adicionarAlimento(alimentoX, alimentoY);
                    posicaoValida = true;
            } else {
                System.out.println("Posição inválida! A posição deve estar entre 0 e 3.");
            }
        }
        
        tabuleiro.adicionarAlimento(alimentoX, alimentoY);
        tabuleiro.moverRobo(robo);
        tabuleiro.mostrarTabuleiro();

        while (!tabuleiro.temAlimento(robo.getX(), robo.getY())) {
            System.out.println("\nMovimentação do robô: ");
            System.out.println("1. Up");
            System.out.println("2. Down");
            System.out.println("3. Right");
            System.out.println("4. Left");
            System.out.print("Escolha o movimento (1-4) ou digite a direção (up, down, right, left): ");
            String direcao = scanner.next();

            try {
                if (direcao.equals("up") || direcao.equals("down") || direcao.equals("right") || direcao.equals("left")) {
                    robo.mover(direcao);
                } else {
                    int direcaoInt = Integer.parseInt(direcao);
                    robo.mover(direcaoInt);
                }
                tabuleiro.moverRobo(robo);
                tabuleiro.mostrarTabuleiro();
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Tente novamente.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Parabéns! O robô encontrou o alimento.");
    }
}