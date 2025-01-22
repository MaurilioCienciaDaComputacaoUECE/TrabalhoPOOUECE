package trabalhoRobo;

import java.security.SecureRandom;
import java.util.Scanner;

public class MainComDoisRobos{
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        System.out.println("Escolha a cor do Robô 1:");
        String corRobo1 = Cor.escolherCor();
        Robo robo1 = new Robo(corRobo1);

        System.out.println("Escolha a cor do Robô 2:");
        String corRobo2 = Cor.escolherCor();
        Robo robo2 = new Robo(corRobo2);

        int movimentosRobo1 = 0;
        int movimentosRobo2 = 0;

        System.out.println("Digite a posição do alimento (valores de 0 a 3):");
        int alimentoX, alimentoY;

        do {
            System.out.print("Posição X: ");
            alimentoX = scanner.nextInt();
        } while (alimentoX < 0 || alimentoX > 3);

        do {
            System.out.print("Posição Y: ");
            alimentoY = scanner.nextInt();
        } while (alimentoY < 0 || alimentoY > 3);

        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.adicionarAlimento(alimentoX, alimentoY);
        tabuleiro.mostrarTabuleiro();

        boolean encontrouRobo1 = false;
        boolean encontrouRobo2 = false;

        while (!encontrouRobo1 || !encontrouRobo2) {
            String[] direcoes = {"up", "down", "left", "right"};

            if (!encontrouRobo1) {
                String direcaoRobo1 = direcoes[random.nextInt(4)];
                try {
                    robo1.mover(direcaoRobo1);
                    movimentosRobo1++;
                    tabuleiro.moverRobo(robo1);
                    tabuleiro.mostrarTabuleiro();
                    if (robo1.encontrouAlimento(alimentoX, alimentoY)) {
                        encontrouRobo1 = true;
                    }
                } catch (MovimentoInvalidoException e) {
                    robo1.incrementarMovimentoInvalido();
                    System.out.printf("Movimento inválido do Robô %s: %s%n", robo1.getCor(), direcaoRobo1);
                }
            }

            if (!encontrouRobo2) {
                String direcaoRobo2 = direcoes[random.nextInt(4)];
                try {
                    robo2.mover(direcaoRobo2);
                    movimentosRobo2++;
                    tabuleiro.moverRobo(robo2);
                    tabuleiro.mostrarTabuleiro();
                    if (robo2.encontrouAlimento(alimentoX, alimentoY)) {
                        encontrouRobo2 = true;
                    }
                } catch (MovimentoInvalidoException e) {
                    robo2.incrementarMovimentoInvalido();
                    System.out.printf("Movimento inválido do Robô %s: %s%n", robo2.getCor(), direcaoRobo2);
                }
            }

            Thread.sleep(100); 
        }

        System.out.printf("O robô %s encontrou o alimento em %d movimentos válidos, %d inválidos.%n", 
                robo1.getCor(), movimentosRobo1, robo1.getMovimentosInvalidos());
        System.out.printf("O robô %s encontrou o alimento em %d movimentos válidos, %d inválidos.%n", 
                robo2.getCor(), movimentosRobo2, robo2.getMovimentosInvalidos());
    }
}