package trabalhoRobo;

import java.util.Scanner;

public class Cor {
    private static final String[] CORES = {"vermelho", "azul", "verde", "amarelo", "preto", "branco"};
    
    public static String escolherCor() {
        Scanner scanner = new Scanner(System.in);
        int corEscolhidaNumero = 0;
        boolean corValida = false;

        while (!corValida) {
            System.out.println("Escolha uma cor entre as opções: ");
            for (int i = 0; i < CORES.length; i++) {
                System.out.printf("%d. %s\n", i + 1, CORES[i]);
            }

            corEscolhidaNumero = scanner.nextInt();  
            
            if (corEscolhidaNumero >= 1 && corEscolhidaNumero <= CORES.length) {
                corValida = true;
            } else {
                System.out.println("Número inválido. Tente novamente.");
            }
        }

        return CORES[corEscolhidaNumero - 1];
    }
}

    
