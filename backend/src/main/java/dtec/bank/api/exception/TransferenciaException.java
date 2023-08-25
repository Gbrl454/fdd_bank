package dtec.bank.api.exception;

public class TransferenciaException extends RuntimeException {
    public TransferenciaException(String errorMessage) {
        super(errorMessage);
    }
}
