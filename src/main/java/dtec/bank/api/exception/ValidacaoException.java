package dtec.bank.api.exception;

import dtec.bank.api.utils.ErrorMessage;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException (ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
