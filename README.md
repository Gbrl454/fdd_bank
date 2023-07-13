# Cursos e projetos formativos (DTEC - Desenvolvimento)

<br/>

### Sistema bancario deve possuir as entidades:
- Banco:
  - Número do Banco (id)
  - Nome do Banco
  
<p></p>

- Usuário:
  - Número do Usuário (id)
  - Nome do Usuário
  - Email
  - Login
  - Senha

<p></p>

- Agência:
  - Número da Agência (id)
  - Número do Banco
  - Nome da Agência

<p></p>

- Conta:
  - Número da Conta (id)
  - Número da Agência
  - Número do Usuário
  - Saldo da Conta
  - Podem existir 3 tipos de Contas:
    - Normal:
      - Possui nada em especial
    - Especial:
      - Pode possuir cartão de crédito
      - Saldo do cartão de crédito
    - Premium:
      - Pode possuir cartão de crédito
      - Saldo do cartão de crédito
      - Pode possuir LIS
      - Saldo do LIS

<p></p>

### O exercicio vai estar dividido em algumas etapas

- Etapa 1 -> O usuario vai informar seus dados, os dados do banco, agencia, conta, tipo de conta e adicionais da conta e ter suas informações salvas
  - Passo 1 -> Cadastro de Banco
  - Passo 2 -> Cadastro de Agência
  - Passo 3 -> Cadastro de Usuário
  - Passo 4 -> Cadastro de Conta
  - Passo 5 -> No cadastro de Usuário Emails devem estar formatados como email para passar. Caso contrario impedir o avanço
  - Passo 6 -> No cadastro de conta, o Usuário deve informar qual tipo de conta ele quer e, com base no tipo de conta
  ser solicitado as informações pertinentes.


- Etapa 2 -> com usuários previamente cadastrados deve ser possivel transacionar valores entre contas
  - Passo 1 -> Cadastrar informações de diversas contas (min 3)
  - Passo 2 -> Criar função que realize transferencia de saldo entre contas ao especificar o número do usuario de origem, destino e valor


- Etapa 3 -> Um usuário pode fazer login informando seu usuario e senha e realizar pagamentos para outras contas previamente cadastradas
  - Passo 1 -> Cadastrar informações de diversas contas (min 3)
  - Passo 2 -> Criar forma de login utilizando usuario e senha previamente cadastrados
  - Passo 3 -> Criar forma de login utilizando usuario ou email e senha previamente cadastrados


- Etapa 4 -> Criar log de transações realizadas
  - Passo 1 -> Criar entidade log possuindo:
    - Número do Banco de origem
    - Número da Agência de origem
    - Número da Conta de origem
    - Número do Banco de destino
    - Número da Agência de destino
    - Número da Conta de destino
    - Valor
    - Horario da transação
  - Passo 2 -> Sempre que houver uma transação devem ser criados logs para identificar as transações ocorridas


- Etapa 5 -> Organizando seu código
  - Passo 1 -> Todas as listas utilizadas no projeto devem seguir o padrão fifo (first in, first out)
  - Passo 2 -> Todas as informações referentes a dinheiro ou saldo devem estar salvas em centavos e exibidas em real
  - Passo 3 -> Estudar modelo MVC (model, view, controller).
  https://www.lewagon.com/pt-BR/blog/o-que-e-padrao-mvc
  - Passo 4:
    - Utilizar camada model para criar todos os modelos de classes utilizadas no projeto
    - Utilizar camada controller para gerenciar todas as operações logicas do projeto
    - Utilizar camada view para gerenciar o que e como aparecem todas as infos na tela do usuario


- Etapa 6
  - Plus Ultra -> Seu projeto agora vai estar sendo usado em outro país todos os textos devem ser disponibilizados em inglês, o usuário escolhe em qual lingua vai utilizar

By: Daneil Andrade Ripardo