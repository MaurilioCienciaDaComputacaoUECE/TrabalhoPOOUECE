package trabalhoRobo;

public class Bomba extends Obstaculo {
    public Bomba(int id) {
        super(id);
    }

    @Override
    public void bater(Robo robo, Tabuleiro tabuleiro) {
        System.out.println("O robô " + robo.getCor() + " explodiu na bomba!");
        tabuleiro.removerRobo(robo);
        robo.setX(-1); 
        robo.setY(-1);
    }
}
