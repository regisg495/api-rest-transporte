DROP DATABASE IF EXISTS transporte_api;

CREATE DATABASE IF NOT EXISTS transporte_api CHARACTER SET UTF8;

USE transporte_api;

DROP TABLE IF EXISTS linhas;

CREATE TABLE IF NOT EXISTS linhas
(
  linha_id BIGINT AUTO_INCREMENT NOT NULL,
  nome     VARCHAR(150) UNIQUE   NOT NULL,
  CONSTRAINT linhas_pk PRIMARY KEY (linha_id)
);

DROP TABLE IF EXISTS paradas;

CREATE TABLE IF NOT EXISTS paradas
(
  parada_id BIGINT AUTO_INCREMENT NOT NULL,
  nome      VARCHAR(150) UNIQUE   NOT NULL,
  latitude  DECIMAL(10, 8)        NOT NULL,
  longitude DECIMAL(11, 8)        NOT NULL,
  CONSTRAINT paradas_pk PRIMARY KEY (parada_id)
);

DROP TABLE IF EXISTS parada_linha;

CREATE TABLE IF NOT EXISTS parada_linha
(
  parada_id BIGINT NOT NULL,
  linha_id  BIGINT NOT NULL,
  CONSTRAINT parada_linhas_pk PRIMARY KEY (parada_id, linha_id),
  CONSTRAINT parada_linha_id_fk FOREIGN KEY (linha_id) REFERENCES linhas (linha_id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT parada_parada_id_fk FOREIGN KEY (parada_id) REFERENCES paradas (parada_id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS veiculos;

CREATE TABLE IF NOT EXISTS veiculos
(
  veiculo_id BIGINT AUTO_INCREMENT NOT NULL,
  nome       VARCHAR(100)          NOT NULL,
  modelo     VARCHAR(100) DEFAULT NULL,
  linha_id   BIGINT                DEFAULT NULL,
  CONSTRAINT veiculos_pk PRIMARY KEY (veiculo_id),
  CONSTRAINT veiculos_linha_id_fk FOREIGN KEY (linha_id) REFERENCES linhas (linha_id) ON UPDATE CASCADE ON DELETE SET NULL
);

DROP TABLE IF EXISTS posicao_veiculo;

CREATE TABLE IF NOT EXISTS posicao_veiculo
(
  veiculo_id BIGINT         NOT NULL,
  latitude   DECIMAL(10, 8) NOT NULL,
  longitude  DECIMAL(11, 8) NOT NULL,
  CONSTRAINT posicao_veiculo_pk PRIMARY KEY (veiculo_id),
  CONSTRAINT posicao_veiculo_veiculo_id_fk FOREIGN KEY (veiculo_id) REFERENCES veiculos (veiculo_id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS usuarios;

CREATE TABLE IF NOT EXISTS usuarios
(
  usuario_id      BIGINT AUTO_INCREMENT NOT NULL,
  nome            VARCHAR(150)          NOT NULL,
  email           VARCHAR(60) UNIQUE    NOT NULL,
  cpf             CHAR(11) UNIQUE       NOT NULL,
  telefone        CHAR(11) DEFAULT NULL,
  senha           VARCHAR(74)           NOT NULL,
  data_nascimento DATE     DEFAULT NULL,
  ativo           BOOLEAN  DEFAULT TRUE,
  CONSTRAINT usuarios_pk PRIMARY KEY (usuario_id)
);

DROP TABLE IF EXISTS permissoes;

CREATE TABLE IF NOT EXISTS permissoes
(
  permissao_nome VARCHAR(30) NOT NULL,
  CONSTRAINT permissoes_pk PRIMARY KEY (permissao_nome)
);

DROP TABLE IF EXISTS usuario_permissao;

CREATE TABLE IF NOT EXISTS usuario_permissao
(
  usuario_id     BIGINT      NOT NULL,
  permissao_nome VARCHAR(30) NOT NULL,
  CONSTRAINT usuario_permissao_pk PRIMARY KEY (usuario_id, permissao_nome),
  CONSTRAINT usuario_permissao_usuario_id_fk FOREIGN KEY (usuario_id) REFERENCES usuarios (usuario_id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT usuario_permissao_permissao_nome_fk FOREIGN KEY (permissao_nome) REFERENCES permissoes (permissao_nome) ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO linhas (nome)
VALUES ('6820-31');
INSERT INTO linhas (nome)
VALUES ('5193-B34');
INSERT INTO linhas (nome)
VALUES ('5193-A1');
INSERT INTO linhas (nome)
VALUES ('5193-A2');
INSERT INTO linhas (nome)
VALUES ('61-AX');
INSERT INTO linhas (nome)
VALUES ('412-31');
INSERT INTO linhas (nome)
VALUES ('591');


INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Praça Pérola Byington', -23.556346, -46.639942);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua João Passalaqua', -23.554084, -46.645832);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Viaduto Dr. Plínio de Queirós', -23.553125, -46.647731);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua Piauí', -23.545404, -46.657564);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua Sergipe', -23.548276, -46.656952);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua da Consolação', -23.549978, -46.653841);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua Dona Antônia de Queirós', -23.551217, -46.652639);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua Martinico Prado', -23.542635, -46.655381);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua Dr. Brasílio Machado', -23.537781, -46.659115);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua Baronesa de Ítu', -23.538440, -46.659893);


INSERT INTO parada_linha (parada_id, linha_id)
VALUES (1, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (1, 2);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (1, 3);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (1, 4);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (1, 5);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (1, 6);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (2, 3);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (2, 4);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (3, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (3, 3);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (3, 5);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (3, 7);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (4, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (4, 5);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (4, 6);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (4, 7);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (5, 7);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (5, 6);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (5, 5);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (5, 3);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (5, 1);


INSERT INTO parada_linha (parada_id, linha_id)
VALUES (6, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (6, 6);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (6, 7);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (7, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (7, 2);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (7, 3);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (7, 5);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (7, 7);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (8, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (8, 2);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (8, 6);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (9, 3);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (9, 5);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (9, 7);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (10, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (10, 2);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IB4-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IB5-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IB6-1', 'BMW', 1);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-1', 'Volvo', 2);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-2', 'Volvo', 2);


INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-1', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-2', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-3', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-4', 'Volkswagen', 3);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-1', 'Mercedes-Benz', 4);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-2', 'Mercedes-Benz', 4);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-1', 'Marcopolo', 5);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-2', 'Marcopolo', 5);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-3', 'Marcopolo', 5);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IVX-11', 'Iveco', 6);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-1', 'Agrale', 7);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-2', 'Agrale', 7);


INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (1, -23.548668, -46.636499);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (2, -23.546253, -46.647709);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (3, -23.532774, -46.671458);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (4, -23.534786, -46.663328);

INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (6, -23.514316, -46.646785);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (7, -23.546818, -46.643860);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (8, -23.540643, -46.670171);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (9, -23.533626, -46.653709);


INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (16, -23.549178, -46.670960);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (17, -23.523280, -46.644230);

# 12345
INSERT INTO usuarios (nome, email, cpf, telefone, senha, data_nascimento)
VALUES ('Régis', 'regisg495@gmail.com', '11122233344', '53991111111',
        '$2y$12$a/TkmZEf38H0fiMymdrYcO3Ha7tTdjitTh/9Q8bs1vzs8prDWEcqG', '1993-11-12');

# 54321
INSERT INTO usuarios (nome, email, cpf, telefone, senha, data_nascimento)
VALUES ('Camila', 'camila123@gmail.com', '22222233355',
        '53999991111', '$2y$12$tEzP5wjVGt0M3abkIh4Kz.B3CBNCmrcgqibHnRvSWUrQ0yq32.6bm', '1991-02-02');

# 123456
INSERT INTO usuarios (nome, email, cpf, telefone, senha, data_nascimento)
VALUES ('Rafael', 'rafael@hotmail.com', '56821345551',
        '53981942455', '$2y$12$BlFnFnwLkw1wr8cpEmI2HewTpqUfYm.E7IkGgjfjlxKaqYMoXBydO', '1987-12-12');

# abcde
INSERT INTO usuarios (nome, email, cpf, telefone, senha, data_nascimento)
VALUES ('Igor', 'igor123@outlook.com', '51000111110',
        '53981052312', '$2y$12$G34XSlhXZp6rRvjWBkqLSuNBRlTMb06MCy8jzD4Y5CJhbPvm5w27K', '1976-02-06');

# edcba
INSERT INTO usuarios (nome, email, cpf, telefone, senha, data_nascimento)
VALUES ('Mikaela', 'mikaela@outlook.com', '50012351233',
        '53981035111', '$2y$12$NBRbsaJp.eIN2gKcGaxjleTIUEmlJkOGGx0aKrVEXS49GsqqdQyce', '1992-01-01');

# aiko
INSERT INTO usuarios (nome, email, cpf, telefone, senha, data_nascimento)
VALUES ('aiko', 'aiko', '00112243123', '53912349999',
        '$2y$12$vVq5otFP1vFmQ0pKXrxrAeNgyFeF3422k7xMRNRcxixSPddMeOGdi', '2000-02-02');

INSERT INTO permissoes (permissao_nome)
VALUES ('usuario');
INSERT INTO permissoes (permissao_nome)
VALUES ('dba');
INSERT INTO permissoes (permissao_nome)
VALUES ('programador');
INSERT INTO permissoes (permissao_nome)
VALUES ('admin');

INSERT INTO usuario_permissao (usuario_id, permissao_nome)
VALUES (1, 'admin');
INSERT INTO usuario_permissao (usuario_id, permissao_nome)
VALUES (1, 'dba');
INSERT INTO usuario_permissao (usuario_id, permissao_nome)
VALUES (2, 'usuario');
INSERT INTO usuario_permissao (usuario_id, permissao_nome)
VALUES (3, 'programador');
INSERT INTO usuario_permissao (usuario_id, permissao_nome)
VALUES (3, 'dba');
INSERT INTO usuario_permissao (usuario_id, permissao_nome)
VALUES (4, 'usuario');
INSERT INTO usuario_permissao(usuario_id, permissao_nome)
VALUES (5, 'programador');
INSERT INTO usuario_permissao(usuario_id, permissao_nome)
VALUES (6, 'admin');
