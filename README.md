# Cursos e projetos formativos (DTEC - Desenvolvimento)

<br/>

sistema bancario
deve possuir as entidades
- banco
  -- numero do banco (id)
  -- nome do banco

- usuário
  -- numero do usuário (id)
  -- nome do usuário
  -- email
  -- usuario
  -- senha

- agencia
  -- numero da agencia (id)
  -- numero do banco
  -- nome da agencia

- conta
  -- numero da conta (id)
  -- numero da agencia
  -- numero do usuário
  -- saldo da conta
  -- podem existir 3 tipos de conta
  --conta normal
  - não possui nada em especial
  -- conta especial
  - possui cartão de credito (sim / não)
  - saldo do cartão de credito
  -- conta premium
  - possui cartão de credito (sim / não)
  - saldo do cartão de credito
  - possui LIS (sim / não)
  - saldo do LIS

o exercicio vai estar dividido em algumas etapas

etapa 1
O usuario vai informar seus dados, os dados do banco, agencia, conta, tipo de conta e adicionais da conta e ter suas informações salvas
- passo 1 - cadastro de banco
- passo 2 - cadastro de agencia
- passo 3 - cadastro de usuario
- passo 4 - cadastro de conta
- passo 5 - no cadastro de usuario emails devem estar formatados como email para passar. Caso contrario impedir o avanço
- passo 6 - no cadastro de conta, o usuario deve informar qual tipo de conta ele quer e, com base no tipo de conta
ser solicitado as informações pertinentes.

etapa 2
com usuários previamente cadastrados deve ser possivel transacionar valores entre contas
- passo 1 - cadastrar informações de diversas contas (min 3)
- passo 2 - criar função que realize transferencia de saldo entre contas ao especificar o numero do usuario de origem, destino e
valor

etapa 3
um usuário pode fazer login informando seu usuario e senha e realizar pagamentos para outras contas previamente cadastradas
- passo 1 - cadastrar informações de diversas contas (min 3)
- passo 2 - criar forma de login utilizando usuario e senha previamente cadastrados
- passo 3 - criar forma de login utilizando usuario ou email e senha previamente cadastrados

etapa 4
criar log de transações realizadas
- passo 1 - criar nova entidade log
- numero do banco de origem
- numero da agencia de origem
- numero da conta de origem
- numero do banco de destino
- numero da agencia de destino
- numero da conta de destino
- valor
- horario da transação
- passo 2 - sempre que houver uma transação devem ser criados logs para identificar as transações ocorridas


etapa 5
organizando seu código
- passo 1 - todas as listas utilizadas no projeto devem seguir o padrão fifo (first in, first out)
- passo 2 - todas as informações referentes a dinheiro ou saldo devem estar salvas em centavos e exibidas em real
- passo 3 - estudar modelo mvc (model, view, controller).
https://www.lewagon.com/pt-BR/blog/o-que-e-padrao-mvc

	- passo 4 - utilizar camada model para criar todos os modelos de classes utilizadas no projeto
		  - utilizar camada controller para gerenciar todas as operações logicas do projeto
		  - utilizar camada view para gerenciar o que e como aparecem todas as infos na tela do usuario


etapa 6
plus ultra - seu projeto agora vai estar sendo usado em outro país todos os textos devem ser disponibilizados em inglês
o usuário escolhe em qual lingua vai utilizar


By: Daneil Andrade Ripardo