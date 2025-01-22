package trabalhoTabuleiro;

public class Regras {
	    public void exibirRegras() {
	        System.out.println("Regras do Jogo de Tabuleiro:");
	        System.out.println("1. O jogo permite de 2 a 6 participantes, e cada jogador tem uma cor que o identifica.");
	        System.out.println("2. Cada jogador começa na casa 0.");
	        System.out.println("3. O tabuleiro tem 40 casas.");
	        System.out.println("4. Cada movimento é determinado pela soma de dois dados.");
	        System.out.println("5. Tipos de jogadores:");
	        System.out.println("   - Jogador Com Sorte: Soma dos dados sempre >= 7.");
	        System.out.println("   - Jogador Azarado: Soma dos dados sempre <= 6.");
	        System.out.println("   - Jogador Normal: Soma dos dados pode ser qualquer valor.");
	        System.out.println("6. Casas Especiais:");
	        System.out.println("   - Casas 10, 25, 38: O jogador não joga na próxima rodada.");
	        System.out.println("   - Casa 13: O jogador muda de tipo.");
	        System.out.println("   - Casas 5, 15, 30: O jogador anda 3 casas à frente (exceto jogador Azarado).");
	        System.out.println("   - Casas 17 e 27: O jogador escolhe outro para voltar ao início.");
	        System.out.println("   - Casas 20 e 35: O jogador troca de lugar com o mais atrasado.");
	        System.out.println("7. O primeiro jogador a alcançar ou ultrapassar a casa 40 vence.");
	        System.out.println("8. Cada vez que um jogador tirar dois dados iguais, ele joga novamente.");
	        System.out.println("9. O jogo requer pelo menos dois jogadores de tipos diferentes.");
	    }
	}


