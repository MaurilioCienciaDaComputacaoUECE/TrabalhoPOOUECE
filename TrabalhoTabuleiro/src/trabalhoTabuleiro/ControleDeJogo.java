package trabalhoTabuleiro;

import java.util.Scanner;
import java.util.ArrayList;

public class ControleDeJogo {
    private ArrayList<Jogador> jogadores;
    private Tabuleiro tabuleiro;
    private Scanner scanner;
    private boolean debugMode;

    public ControleDeJogo() {
        jogadores = new ArrayList<>();
        tabuleiro = null;
        scanner = new Scanner(System.in);
        debugMode = false;
    }

    public void iniciar() {
        int numJogadores = solicitarNumeroJogadores();

        CriacaoDeJogador criacaoDeJogador = new CriacaoDeJogador(scanner);

        while (true) {
            jogadores.clear();
            criacaoDeJogador.resetCriacao();

            for (int i = 0; i < numJogadores; i++) {
                jogadores.add(criacaoDeJogador.criarJogador(i + 1));
            }

            if (validarJogadoresDiferentes()) {
                break;
            } else {
                System.out.println("É necessário ter pelo menos dois jogadores de tipos diferentes.");
            }
        }

        tabuleiro = new Tabuleiro(jogadores);
        escolherModoDebug();
        jogar();
    }

    private int solicitarNumeroJogadores() {
        System.out.printf("Bem-vindo ao jogo de tabuleiro!\n");
        System.out.printf("Há a necessidade de pelo menos 2 tipos de jogadores.\n");
        int numJogadores;
        do {
            System.out.printf("Quantos jogadores participarão (2-6)? ");
            numJogadores = scanner.nextInt();
            scanner.nextLine();
        } while (numJogadores < 2 || numJogadores > 6);

        return numJogadores;
    }

    private void escolherModoDebug() {
        System.out.printf("Você deseja ativar o modo Debug (sim/não)? ");
        String resposta = scanner.nextLine().toLowerCase();
        debugMode = resposta.equals("sim");

        if (debugMode) {
            System.out.printf("Modo Debug ativado! Você poderá inserir o número da casa diretamente.\n");
        } else {
            System.out.printf("Modo Debug desativado.\n");
        }
    }

    public void jogar() {
        boolean jogoRodando = true;
        int rodada = 1;

        while (jogoRodando) {
            System.out.printf("\nRodada %d\n", rodada);

            for (Jogador jogador : jogadores) {
                if (!jogador.isPulaRodada()) {
                    System.out.printf("%s, pressione ENTER para continuar...\n", jogador.getCor());
                    scanner.nextLine();

                    if (debugMode) {
                        System.out.printf("%s, insira o número da casa para onde deseja ir:\n ", jogador.getCor());
                        int destino = scanner.nextInt();
                        scanner.nextLine();
                        jogador.setPosicao(destino);
                        System.out.printf("%s está na casa %d.\n", jogador.getCor(), jogador.getPosicao());

                        tabuleiro.checarCasasEspeciais(jogador);
                    } else {
                        int somaDados = jogador.jogar(false);

                        System.out.printf("Dados do jogador %s: Dado Azul = %d, Dado Vermelho = %d\n", jogador.getCor(), jogador.getResultadoDadoAzul(), jogador.getResultadoDadoVermelho());

                        while (jogador.getResultadoDadoAzul() == jogador.getResultadoDadoVermelho()) {
                            if (jogador.getPosicao() >= 40) {
                                break; 
                              
                            }

                            System.out.printf("%s tirou dois dados iguais (%d)! Joga novamente...\n", jogador.getCor(), jogador.getResultadoDadoAzul());
                            
                            System.out.printf("Posição atual de %s: %d.\n Pressione ENTER para jogar novamente...\n", jogador.getCor(), jogador.getPosicao());
                            scanner.nextLine();  

                            jogador.setJogadas(jogador.getJogadas() + 1);  

                            System.out.printf("%s está na casa %d.\n", jogador.getCor(), jogador.getPosicao());
                            tabuleiro.checarCasasEspeciais(jogador);

                            somaDados += jogador.jogar(false);

                            System.out.printf("Dados do jogador %s: Dado Azul = %d, Dado Vermelho = %d\n", jogador.getCor(), jogador.getResultadoDadoAzul(), jogador.getResultadoDadoVermelho());
                        }

                        System.out.printf("%s está na casa %d.\n", jogador.getCor(), jogador.getPosicao());
                        tabuleiro.checarCasasEspeciais(jogador);
                    }

                    if (jogador.getPosicao() >= 40) {
                        System.out.printf("%s venceu o jogo!\n", jogador.getCor());
                        jogoRodando = false;
                        break;
                    }
                } else {
                    System.out.printf("%s está pulando a rodada.\n", jogador.getCor());
                    jogador.setPulaRodada(false);
                }
            }
            rodada++;
        }
        tabuleiro.exibirPosicoes();
    }

    public boolean validarJogadoresDiferentes() {
        boolean temAzarado = false;
        boolean temComSorte = false;
        boolean temNormal = false;

        for (Jogador jogador : jogadores) {
            if (jogador instanceof JogadorAzarado) temAzarado = true;
            if (jogador instanceof JogadorComSorte) temComSorte = true;
            if (jogador instanceof JogadorNormal) temNormal = true;
        }

        return (temAzarado && temComSorte) || (temAzarado && temNormal) || (temComSorte && temNormal);
    }
}