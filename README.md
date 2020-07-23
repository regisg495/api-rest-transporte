# programa-estagio-aiko

# 1 - Importando o Projeto

Primeiro, importe o projeto usando sua IDE de preferência. 
Uma vez com o projeto no seu computador, é hora de realizar a configuração do projeto para rodar na sua máquina. 
Feito isso, execute o script que está no arquivo data.sql, que está na raiz do nosso projeto. Ele contém a criação de nossas tabelas e o preenchimento das mesmas.

Uma vez terminado o dump do banco de dados. É hora de alterar as configurações do Hibernate, vá na arquivo \src\main\resources\application.properties e altere as seguintes configurações:

 datasource
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/transporte_api
spring.datasource.username=seuuser
spring.datasource.password=suasenha

Nos dados acima, coloque os dados do seu usuário e sua senha. O database transporte_api eu deixei como padrão ao criar o banco de dados. Caso você queira alterar, basta mudar o arquivo data.sql

Uma vez realizados os procedimentos acima, você já pode executar o projeto. Basta executar a classe chamada TransporteApiApplication, que esta em src/main/java/com/br/transporteapi.
Caso queira executar via Maven, basta rodar o seguinte comando: mvn springboot:run

# 2 - Padrão do Sistema

Há um parametro de contexto chamado aikoapi nas urls, logo todas as requisições devem ser enviadas para http://localhost:8080/aikoapi

Por padrão, o swagger está funcionando na URL http://localhost:8080/aikoapi/swagger-ui.html

Como é necessária autenticação de usuário, você precisará do login e senha dos usuários. Eles estão no arquivo data.sql. Na parte da inserção dos usuários.
Onde deixei a senha comentada logo acima da inserção de cada usuário.

Exemplo:

54321
INSERT INTO usuarios (nome, email, cpf, telefone, senha, data_nascimento)
VALUES ('Camila', 'camila123@gmail.com', '22222233355',
        '53999991111', '$2y$12$tEzP5wjVGt0M3abkIh4Kz.B3CBNCmrcgqibHnRvSWUrQ0yq32.6bm', '1991-02-02');
        
A senha neste caso é 54321

# 2 - Autenticando-se no Sistema

Para realizar a autenticação no Sistema, com o projeto rodando, dispare uma requisição post para http://localhost:8080/aikoapi/login, com algum dos usuários que estão salvos.
Você receberá um token de acesso.
Exemplo:

{
  "token": {
    "acessKey": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaWtvIiwiaXNzIjoidHJhbnNwb3J0ZS1hcGkiLCJpYXQiOjE1OTU0ODg3ODAsImV4cCI6MTU5NTQ5OTU4MH0.8_YjD8y8-s0NkF4eH4Bl_v50oMYDS51EC6NA4BuDT9w",
    "authenticationType": "Bearer"
  }
}

O tipo de autorização refere-se ao tipo do token, já a chave é a sua chave de acesso.
Copie a chave. Com ela, caso esteja no swagger, clique no cadeado, em que está escrito "Authorize". Digite o tipo de chave (Bearer), espaço e a sua chave.
Exemplo:

Bearer AWHWIEIQ34820OEPWQKDCNVDNIJ3JUR932UR8JFC

Caso realize a autenticação via postman, não será necessário escrever Bearer, basta enviar a chave direto.

# 3 Endpoints
Os endpoints são:
/linha - refere-se às linhas
/parada - refere-se às paradas
/veiculo - refere´se aos veículos
/veiculo/posicao - refere-se à posição dos veícuslo
/login - para logar

Todas as requisições do tipo GET estão permitidas, já as requisições de tipo POST e PUT requerem permissão de dba, ou programador, ou admin. Qualquer uma das três.
As requisições do tipo DELETE requerem permissão de admin




