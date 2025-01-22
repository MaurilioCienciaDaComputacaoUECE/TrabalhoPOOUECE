package trabalhoRobo;

public class MovimentoInvalidoException extends Exception {
    public MovimentoInvalidoException(String movimento) {
        super(String.format("Movimento inválido: %s", movimento));
    }
}