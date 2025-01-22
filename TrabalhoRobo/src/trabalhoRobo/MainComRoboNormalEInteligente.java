package trabalhoRobo;

import java.security.SecureRandom;
import java.util.Scanner;

public class MainComRoboNormalEInteligente {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        System.out.println("Escolha a cor do Robô Normal:");
        String corRoboNormal = Cor.escolherCor();
        Robo roboNormal = new Robo(corRoboNormal);

        System.out.println("Escolha a cor do Robô Inteligente:");
        String corRoboInteligente = Cor.escolherCor();
        RoboInteligente roboInteligente = new RoboInteligente(corRoboInteligente);

        int movimentosRoboNormal = 0;
        int movimentosRoboInteligente = 0;

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

        boolean encontrouRoboNormal = false;
        boolean encontrouRoboInteligente = false;

        while (!encontrouRoboNormal || !encontrouRoboInteligente) {
            String[] direcoes = {"up", "down", "left", "right"};

            if (!encontrouRoboNormal) {
                String direcaoNormal = direcoes[random.nextInt(4)];
                try {
                    roboNormal.mover(direcaoNormal);
                    movimentosRoboNormal++;
                    tabuleiro.moverRobo(roboNormal);
                    tabuleiro.mostrarTabuleiro();
                    if (roboNormal.encontrouAlimento(alimentoX, alimentoY)) {
                        encontrouRoboNormal = true;
                    }
                } catch (MovimentoInvalidoException e) {
                    roboNormal.incrementarMovimentoInvalido();
                    System.out.printf("Movimento inválido do Robô Normal: %s%n", direcaoNormal);
                }
            }

            if (!encontrouRoboInteligente) {
                String direcaoInteligente = roboInteligente.gerarNovaDirecao();
                try {
                    roboInteligente.mover(direcaoInteligente);
                    movimentosRoboInteligente++;
                    tabuleiro.moverRobo(roboInteligente);
                    tabuleiro.mostrarTabuleiro();
                    if (roboInteligente.encontrouAlimento(alimentoX, alimentoY)) {
                        encontrouRoboInteligente = true;
                    }
                } catch (MovimentoInvalidoException e) {
                }
            }
            Thread.sleep(100); 
        }

        System.out.printf("O robô %s Normal encontrou o alimento em %d movimentos, %d inválidos.%n", 
                roboNormal.getCor(), movimentosRoboNormal, roboNormal.getMovimentosInvalidos());
        System.out.printf("O robô %s Inteligente encontrou o alimento em %d movimentos, %d inválidos.%n", 
                roboInteligente.getCor(), movimentosRoboInteligente, roboInteligente.getMovimentosInvalidos());
    }
}