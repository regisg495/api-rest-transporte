# programa-estagio-aiko

O link de explicação dos testes do projeto está em https://www.youtube.com/watch?v=RqmUmxVg904
O link com a explicação do código do projeto está em https://www.youtube.com/watch?v=bscYY5VZSx4&t=6s

# 1 Entidades

As Entidades do projeto são:
Linhas, Paradas, Veiculos, PosicaoVeiculo, Usuario, Permissão.

Cada linha passa de 0 a n paradas.
Cada parada tem de 0 a n linhas que passam por ela
Cada veiculo pertence a uma linha.
Cada veículo tem 0 a 1 posição cadastrada.
Cada usuário pode ter muitas permissões.
Muitos usuários podem ter muitas permissões.

O diagrama que melhor representa o modelo físico do banco de dados está abaixo.

![Screenshot_7](https://user-images.githubusercontent.com/41974237/88271605-c2ede300-ccad-11ea-95f4-590dbdd13387.png)

# 1 - Importando o Projeto

Primeiro, importe o projeto usando sua IDE de preferência. 
Uma vez com o projeto no seu computador, é hora de realizar a configuração do projeto para rodar na sua máquina. 
Feito isso, execute o script que está no arquivo data.sql, que está na raiz do nosso projeto. Ele contém a criação de nossas tabelas e o preenchimento das mesmas.

Uma vez terminado o dump do banco de dados. É hora de alterar as configurações do Hibernate, vá na arquivo \src\main\resources\application.properties e altere as seguintes configurações:

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver\
spring.datasource.url=jdbc:mysql://localhost:3306/transporte_api\
spring.datasource.username=seuuser\
spring.datasource.password=suasenha

Imagem do arquivo\
![Screenshot_2](https://user-images.githubusercontent.com/41974237/88269018-b1a2d780-cca9-11ea-8322-f6f3d3d96952.png)

Nos dados acima, coloque os dados do seu usuário e sua senha. O database transporte_api eu deixei como padrão ao criar o banco de dados. Caso você queira alterar, basta mudar o arquivo data.sql

Uma vez realizados os procedimentos acima, você já pode executar o projeto. Basta executar a classe chamada TransporteApiApplication, que esta em src/main/java/com/br/transporteapi.
Caso queira executar via Maven, basta rodar o seguinte comando: mvn springboot:run

# 2 - Padrão do Sistema

Há um parametro de contexto chamado aikoapi nas urls, logo todas as requisições devem ser enviadas para http://localhost:8080/aikoapi

Por padrão, o swagger está funcionando na URL http://localhost:8080/aikoapi/swagger-ui.html

Exemplo:

![Screenshot_1](https://user-images.githubusercontent.com/41974237/88270093-7e614800-ccab-11ea-844d-d66fe89d83e5.png)

Como é necessária autenticação de usuário, você precisará do login e senha dos usuários. Eles estão no arquivo data.sql. Na parte da inserção dos usuários.
Onde deixei a senha comentada logo acima da inserção de cada usuário.

Exemplo:

![Screenshot_3](https://user-images.githubusercontent.com/41974237/88270235-b1a3d700-ccab-11ea-8abb-03fa36dac225.png)

# 2 - Autenticando-se no Sistema

Para realizar a autenticação no Sistema, com o projeto rodando, dispare uma requisição post para http://localhost:8080/aikoapi/login, com algum dos usuários que estão salvos.
Você receberá um token de acesso.
Exemplo:

![Screenshot_4](https://user-images.githubusercontent.com/41974237/88270310-d304c300-ccab-11ea-8a00-832adbb40194.png)


O tipo de autorização refere-se ao tipo do token, já a chave é a sua chave de acesso.
Copie a chave. Com ela, caso esteja no swagger, clique no cadeado, em que está escrito "Authorize". Digite o tipo de chave (Bearer), espaço e a sua chave.
Exemplo:

Sua chave é:
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaWtvIiwiaXNzIjoidHJhbnNwb3J0ZS1hcGkiLCJpYXQiOjE1OTU0OTU2NjEsImV4cCI6MTU5NTUwNjQ2MX0.LaszQq4uGNsVMxs5OHY3bsDfL_haCxSOmDbq3JJyOY4

Foto de exemplo:

![Screenshot_6](https://user-images.githubusercontent.com/41974237/88270627-4c9cb100-ccac-11ea-83a0-8d9a6cd35e66.png)

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

# 4 Autenticação via Postman


