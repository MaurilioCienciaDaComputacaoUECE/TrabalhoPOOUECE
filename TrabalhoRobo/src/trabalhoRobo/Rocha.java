package trabalhoRobo;

public class Rocha extends Obstaculo {
    public Rocha(int id) {
        super(id);
    }

    @Override
    public void bater(Robo robo, Tabuleiro tabuleiro) {
        System.out.println("O robô " + robo.getCor() + " bateu na rocha!");
        robo.voltarPosicao(); 
    }
}