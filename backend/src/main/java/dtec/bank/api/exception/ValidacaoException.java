package dtec.bank.api.exception;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException (String errorMessage) {
        super(errorMessage);
    }
}
