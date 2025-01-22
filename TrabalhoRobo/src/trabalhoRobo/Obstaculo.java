package trabalhoRobo;

public abstract class Obstaculo {
    private final int id;

    public Obstaculo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract void bater(Robo robo, Tabuleiro tabuleiro);  
}
