package dtec.bank.api.exception;

import dtec.bank.api.utils.ErrorMessage;

public class TransferenciaException extends RuntimeException {
    public TransferenciaException (ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public TransferenciaException (String errorMessage) {
        super(errorMessage);
    }
}
