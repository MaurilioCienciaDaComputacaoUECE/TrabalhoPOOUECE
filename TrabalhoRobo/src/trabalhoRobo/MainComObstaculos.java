package trabalhoRobo;

import java.util.Scanner;
import java.security.SecureRandom;

public class MainComObstaculos {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        System.out.printf("Escolha a cor do Robô Normal: ");
        String corRoboNormal = Cor.escolherCor();
        Robo roboNormal = new Robo(corRoboNormal);

        System.out.printf("Escolha a cor do Robô Inteligente: ");
        String corRoboInteligente = Cor.escolherCor();
        RoboInteligente roboInteligente = new RoboInteligente(corRoboInteligente);

        Tabuleiro tabuleiro = new Tabuleiro();

        System.out.printf("Digite a posição do alimento (valores de 0 a 3):\n");
        int alimentoX, alimentoY;
        do {
            System.out.printf("Posição X: ");
            alimentoX = scanner.nextInt();
        } while (alimentoX < 0 || alimentoX > 3);

        do {
            System.out.printf("Posição Y: ");
            alimentoY = scanner.nextInt();
        } while (alimentoY < 0 || alimentoY > 3);

        tabuleiro.adicionarAlimento(alimentoX, alimentoY);

        int quantidadeBombas, quantidadeRochas;

        System.out.printf("Quantas Bombas você deseja adicionar? (máximo 13): ");
        do {
            quantidadeBombas = scanner.nextInt();
            if (quantidadeBombas < 0 || quantidadeBombas > 13) {
                System.out.printf("Número inválido de Bombas. O máximo permitido é 13.\n");
            }
        } while (quantidadeBombas < 0 || quantidadeBombas > 13);


        System.out.printf("Adicionando as Bombas:\n");
        for (int i = 0; i < quantidadeBombas; i++) {
            int x, y;
            System.out.printf("Digite a posição da Bomba %d:\n", (i + 1));
            do {
                System.out.printf("Posição X (valores de 0 a 3): ");
                x = scanner.nextInt();
                System.out.printf("Posição Y (valores de 0 a 3): ");
                y = scanner.nextInt();

                if (!tabuleiro.posicaoValida(x, y)) {
                    System.out.printf("Posição inválida! A posição está fora dos limites ou já ocupada. Tente novamente.\n");
                }
            } while (!tabuleiro.posicaoValida(x, y));

            tabuleiro.adicionarObstaculo(new Bomba(i + 1), x, y);
        }

        System.out.printf("Quantas Rochas você deseja adicionar? (máximo %d, considerando as Bombas já adicionadas): ", 13 - quantidadeBombas);
        do {
            quantidadeRochas = scanner.nextInt();
            if (quantidadeRochas < 0 || quantidadeRochas > (13 - quantidadeBombas)) {
                System.out.printf("Número inválido de Rochas. O máximo permitido é %d.\n", (13 - quantidadeBombas));
            }
        } while (quantidadeRochas < 0 || quantidadeRochas > (13 - quantidadeBombas));


        System.out.printf("Adicionando as Rochas:\n");
        for (int i = 0; i < quantidadeRochas; i++) {
            int x, y;
            System.out.printf("Digite a posição da Rocha %d:\n", (i + 1));
            do {
                System.out.printf("Posição X (valores de 0 a 3): ");
                x = scanner.nextInt();
                System.out.printf("Posição Y (valores de 0 a 3): ");
                y = scanner.nextInt();

                if (!tabuleiro.posicaoValida(x, y)) {
                    System.out.printf("Posição inválida! A posição está fora dos limites ou já ocupada. Tente novamente.\n");
                }
            } while (!tabuleiro.posicaoValida(x, y));

            tabuleiro.adicionarObstaculo(new Rocha(i + 1), x, y);
        }

        tabuleiro.mostrarTabuleiro();

        boolean encontrouRoboNormal = false;
        boolean encontrouRoboInteligente = false;
        boolean destruidoRoboNormal = false;
        boolean destruidoRoboInteligente = false;

        int movimentosRoboNormal = 0;
        int movimentosRoboInteligente = 0;

        while ((encontrouRoboNormal ? 1 : 0) + (encontrouRoboInteligente ? 1 : 0) + (destruidoRoboNormal ? 1 : 0) + (destruidoRoboInteligente ? 1 : 0) < 2)  {
            String[] direcoes = {"up", "down", "left", "right"};

            if (!encontrouRoboNormal && !destruidoRoboNormal) { 
                String direcaoNormal = direcoes[random.nextInt(4)];
                try {
                    roboNormal.mover(direcaoNormal);
                    movimentosRoboNormal++;
                    tabuleiro.moverRobo(roboNormal);
                    tabuleiro.mostrarTabuleiro();
                    if (roboNormal.encontrouAlimento(alimentoX, alimentoY)) {
                        encontrouRoboNormal = true;
                    }
                    if (tabuleiro.verificarColisao(roboNormal)) {
                        if (roboNormal.getX() == -1 && roboNormal.getY() == -1) {
                            destruidoRoboNormal = true;
                        }
                    }
                } catch (MovimentoInvalidoException e) {
                    roboNormal.incrementarMovimentoInvalido();
                    System.out.printf("Movimento inválido do Robô Normal: %s%n", direcaoNormal);
                }
            }

            if (!encontrouRoboInteligente && !destruidoRoboInteligente) {
                String direcaoInteligente = roboInteligente.gerarNovaDirecao();
                try {
                    roboInteligente.mover(direcaoInteligente);
                    movimentosRoboInteligente++;
                    tabuleiro.moverRobo(roboInteligente);
                    tabuleiro.mostrarTabuleiro();
                    if (roboInteligente.encontrouAlimento(alimentoX, alimentoY)) {
                        encontrouRoboInteligente = true;
                        
                    }
                    if (tabuleiro.verificarColisao(roboInteligente)) {
                        if (roboInteligente.getX() == -1 && roboInteligente.getY() == -1) {
                            destruidoRoboInteligente = true;
                        }
                    }
                } catch (MovimentoInvalidoException e) {
                }
            }

            Thread.sleep(100);
        }

        if (!destruidoRoboNormal) {
            System.out.printf("O robô %s Normal encontrou o alimento em %d movimentos, %d inválidos.\n",
                    roboNormal.getCor(), movimentosRoboNormal, roboNormal.getMovimentosInvalidos());
        }

        if (!destruidoRoboInteligente) {
            System.out.printf("O robô %s Inteligente encontrou o alimento em %d movimentos, %d inválidos.\n",
                    roboInteligente.getCor(), movimentosRoboInteligente, roboInteligente.getMovimentosInvalidos());
        }
        
        if (destruidoRoboNormal && destruidoRoboInteligente) {
            System.out.printf("Ambos os robôs explodiram antes de encontrar o alimento.\n");
        }
    }
}