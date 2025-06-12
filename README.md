# 📌 Ponto de Partida

Você foi contratado por uma pequena empresa para desenvolver uma API REST em Java, com foco em clareza, manutenibilidade e qualidade de código. A empresa deseja um sistema simples, mas bem testado, e que possa ser consumido posteriormente por outros serviços.

Seu desafio é criar uma aplicação com o framework Javalin, testá-la com JUnit e consumi-la usando HttpURLConnection. A entrega desse AT consiste nos prints das 4 telas da Rubrica 1 e 2 projetos Maven para as demais Rubricas: um com todos os endpoints (serviços) e outro com os clientes HttpURLConnection acessando os endpoints.

O serviço base (endpoints e requisitos) será definido pelo professor da disciplina. Para fins deste AT, considere que o endpoint inicial é /hello e retorna uma mensagem simples. A partir disso, o professor indicará o caso de uso principal (como por exemplo: sistema de cadastro de alunos, produtos, tarefas, etc.) e os atributos necessários.

O caso de uso principal envolve o sistema de Folha de Pagamento criado nas aulas.

---

# 📊 Rubricas

## Rubrica 1 - Preparar o ambiente para desenvolvimento local com Java

- Criar um projeto Maven com Javalin e implementar os endpoints:
  - `GET /hello` → retorna `"Hello, Javalin!"`.
  - `GET /status` → retorna JSON contendo `status: ok` e `timestamp` no formato ISO-8601.
  - `POST /echo` → recebe JSON com a chave `mensagem` e retorna o mesmo conteúdo.
  - `GET /saudacao/{nome}` → retorna: `{ "mensagem": "Olá, <nome>!" }`

## Rubrica 2 - Desenvolver testes unitários usando JUnit

- Escrever testes para:
  - `GET /hello` → validar status 200 e resposta.
  - `POST` de criação de Mensalista → validar status 201.
  - `GET` de busca por matrícula → verificar recuperação correta.
  - `GET` de listagem de Mensalistas → garantir retorno não vazio após criação.

## Rubrica 3 - Consumir serviços web com Java (HttpURLConnection)

- Criar um novo projeto Maven para implementar clientes para os endpoints criados:
  - Cliente para envio de `POST` de criação de Mensalista.
  - Cliente para `GET` de listagem de Mensalistas.
  - Cliente para `GET` de busca por matrícula.
  - Cliente para `GET /status`.

## Rubrica 4 - Desenvolver serviços web RESTful com Javalin

- Implementação completa dos endpoints REST:
  - `GET` listagem de todos Mensalistas.
  - `GET` busca de Mensalista por matrícula.
  - `POST` criação de Mensalista com validação de dados.

---

# 📄 README DO PROJETO

Este repositório contém o desenvolvimento das etapas **1, 2 e 4** do AT proposto.

---

# 📦 Entregáveis

- Código-fonte em projeto Maven com todos os endpoints e testes unitários (Rubricas 1, 2 e 4).
- As implementações da Rubrica 3 (clientes HttpURLConnection) estão em projeto separado.

---

# 🔒 Observações de segurança

- **As credenciais de conexão com o Firebase foram removidas do repositório por segurança.**


