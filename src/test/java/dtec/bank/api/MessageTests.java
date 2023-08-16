package dtec.bank.api;

interface MessageTests {
    String agenciaObrigatorio = "ID da Agência é obrigatório";
    String usuarioObrigatorio = "ID do Usuário é obrigatório";
    String saldoObrigatorio = "Saldo é obrigatório";
    String tipoObrigatorio = "Tipo de Conta é obrigatório";
    String nomeObrigatorio = "Nome é obrigatório";
    String paisObrigatorio = "Pais é obrigatório";
    String emailObrigatorio = "Email é obrigatório";
    String loginObrigatorio = "Login é obrigatório";
    String senhaObrigatorio = "Senha é obrigatória";
    String bancoObrigatorio = "ID do Banco é obrigatório";
    String emailInvalido = "Formato do Email é inválido";
    String contaObrigatorio = "ID da Conta é obrigatório";
    String moedaObrigatorio = "Moeda é obrigatória";
    String valorTransferenciaObrigatorio = "Valor da Transferência é obrigatório";
    String contaNormalNotownCreditcard = "Contas do Tipo NORMAL não podem possuir Cartão de Crédito!";
    String contaNormalNotownLis = "Contas do Tipo NORMAL não podem possuir Cheque Especial (LIS)!";
    String contaEspecialNotownLis = "Contas do Tipo ESPECIAL não podem possuir Cheque Especial (LIS)!";
    String agenciaIdNotexist = "ID da Agência informada não existe!";
    String agenciaNomeTherealready = "Já existe uma Agência com esse Nome";
    String bancoIdNotexist = "ID do Banco informado não existe!";
    String bancoIdInvalid = "ID do Banco informado inválido!";
    String contaOrigemNotexist = "ID da Conta (Origem) informada não existe!";
    String contaDestinoNotexist = "ID da Conta (Destinatário) informada não existe!";
    String contaOrigemDestinoequals = "O ID da Conta (Destinatário) não pode ser o mesmo da Conta (Origem)!";
    String usuarioIdNotexist = "ID do Usuário informado não existe!";
    String usuarioEmailTherealready = "Já existe um Usuário com esse Email";
    String usuarioLoginTherealready = "Já existe um Usuário com esse Login";
    String saldoCreditcardUninformed = "Saldo do Cartão de Crédito deve ser informado quando a Conta possui Cartão de Crédito!";
    String saldoTransferenciaInsuficiente = "Saldos insuficientes para realizar a Transferência";
    String bancoNomeTherealready = "Já existe um Banco com esse Nome";
    String saldoCreditcardInformonlyhave = "Saldo do Cartão de Crédito somente deve ser informado quando a Conta possui Cartão de Crédito!";
    String saldoCreditcardInformwhenhave = "Saldo do Cartão de Crédito deve ser informado quando a Conta possui Cartão de Crédito!";
    String saldoLisInformonlyhave = "Saldo do Cheque Especial (LIS) somente deve ser informado quando a Conta possui Cheque Especial (LIS)!";
    String saldoLisInformwhenhave = "Saldo do Cheque Especial (LIS) deve ser informado quando a Conta possui Cheque Especial (LIS)!";
    String errorGeneratingTokenJwt = "Erro ao gerar token JWT";
    String errorInvalidTokenJwt = "Token JWT inválido ou expirado!";

}
