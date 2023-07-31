package dtec.bank.api.utils;

public enum ErrorMessage {
    /**
     * Contas do Tipo NORMAL não podem possuir Cartão de Crédito
     */
    accountNormalCantHaveCreditCard("Contas do Tipo NORMAL não podem possuir Cartão de Crédito!"),
    /**
     * Contas do Tipo NORMAL não podem possuir Cheque Especial (LIS)!
     */
    accountNormalCantHaveLIS("Contas do Tipo NORMAL não podem possuir Cheque Especial (LIS)!"),
    /**
     * Contas do Tipo ESPECIAL não podem possuir Cheque Especial (LIS)!
     */
    accountEspecialCantHaveLIS("Contas do Tipo ESPECIAL não podem possuir Cheque Especial (LIS)!"),
    /**
     * ID da Agência não existe no banco de dados
     */
    idAgenciaNotExist("ID da Agência informada não existe!"),
    /**
     * ID do Banco não existe no banco de dados
     */
    idBancoNotExist("ID do Banco informado não existe!"),
    /**
     * ID do Banco da Origem da Transferência não existe no banco de dados
     */
    idBancoOrigemNotExist("ID do Banco (Origem) informado não existe!"),
    /**
     * ID do Banco do Destinatário da Transferência não existe no banco de dados
     */
    idBancoDestinoNotExist("ID do Banco (Destinatário) informado não existe!"),
    /**
     * ID do Usuário não existe no banco de dados
     */
    idUsuarioNotExist("ID do Usuário informado não existe!"),
    /**
     * ID do Banco é inválido
     */
    idBancoInvalid("ID do Banco informado inválido!"),
    /**
     * Já existe uma Agência com o mesmo nome passado
     */
    nameAgenciaNameExists("Já existe uma Agência com esse Nome"),
    /**
     * Conta possuiu Cartão de Crédito, mas não informou o Saldo
     */
    balanceCreditCardUninformed("Saldo do Cartão de Crédito deve ser informado quando a Conta possui Cartão de Crédito!");

    private final String message;

    ErrorMessage (String message) {
        this.message = message;
    }

    public String getMessage () {
        return message;
    }
}
