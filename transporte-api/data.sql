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
INSERT INTO linhas (nome)
VALUES ('123-45');
INSERT INTO linhas (nome)
VALUES ('111-11');
INSERT INTO linhas (nome)
VALUES ('351-X');
INSERT INTO linhas (nome)
VALUES ('A4-A2-A1');
INSERT INTO linhas (nome)
VALUES ('1VA-B2');
INSERT INTO linhas (nome)
VALUES ('215-35');
INSERT INTO linhas (nome)
VALUES ('5510-10');
INSERT INTO linhas (nome)
VALUES ('00-01');
INSERT INTO linhas (nome)
VALUES ('00-02');
INSERT INTO linhas (nome)
VALUES ('00-03');

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
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua Santa Cecília', -23.536055, -46.657431);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Av. Pacaembu', -23.533812, -46.661792);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Av. Francisco Matarazzo', -23.531318, -46.664855);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Av. Antártica', -23.525445, -46.671518);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua Gustav Willi Borgov', -23.524702, -46.673063);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua Palestra Itália', -23.527525, -46.680858);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Av. Pompéia', -23.526836, -46.682580);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Rua Clélia', -23.525439, -46.682757);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Viaduto Pompéia', -23.525001, -46.681309);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Av. Marquês de São Vicente', -23.520250, -46.671240);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Av. Thomas Edison', -23.518887, -46.669084);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Av. Pres. Castelo Branco', -23.516069, -46.667496);
INSERT INTO paradas (nome, latitude, longitude)
VALUES ('Av. Marginal Tietê', -23.515459, -46.668869);

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
VALUES (1, 12);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (1, 13);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (2, 3);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (2, 4);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (2, 8);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (2, 9);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (2, 10);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (2, 11);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (3, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (3, 3);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (3, 5);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (3, 7);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (3, 11);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (4, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (4, 14);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (4, 13);
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
VALUES (5, 10);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (5, 11);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (5, 12);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (6, 15);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (6, 16);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (6, 17);
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
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (10, 17);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (10, 11);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (10, 15);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (10, 12);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (11, 2);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (11, 6);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (11, 10);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (11, 11);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (11, 13);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (11, 15);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (12, 6);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (12, 7);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (12, 8);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (12, 17);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (12, 4);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (12, 1);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (13, 4);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (13, 3);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (13, 14);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (13, 16);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (13, 17);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (13, 12);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (14, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (14, 6);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (14, 14);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (15, 8);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (15, 9);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (15, 12);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (15, 15);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (15, 17);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (15, 2);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (16, 17);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (16, 16);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (16, 15);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (16, 14);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (16, 5);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (16, 3);

INSERT INTO parada_linha (parada_id, linha_id)
VALUES (17, 1);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (17, 5);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (17, 14);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (17, 16);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (17, 17);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (17, 12);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (17, 7);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (17, 8);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (17, 9);
INSERT INTO parada_linha (parada_id, linha_id)
VALUES (17, 15);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IB4-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IB5-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IB6-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IB7-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IB8-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IB9-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IC1-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IC2-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IC3-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IC4-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IC5-1', 'BMW', 1);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IC6-1', 'BMW', 1);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-1', 'Volvo', 2);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-2', 'Volvo', 2);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-3', 'Volvo', 2);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-4', 'Volvo', 2);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-5', 'Volvo', 2);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-6', 'Volvo', 2);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-7', 'Volvo', 2);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-8', 'Volvo', 2);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('BXZ-9', 'Volvo', 2);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-1', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-2', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-3', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-4', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-5', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-6', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-7', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-8', 'Volkswagen', 3);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('W3C-9', 'Volkswagen', 3);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-1', 'Mercedes-Benz', 4);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-2', 'Mercedes-Benz', 4);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-3', 'Mercedes-Benz', 4);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-4', 'Mercedes-Benz', 4);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-5', 'Mercedes-Benz', 4);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-6', 'Mercedes-Benz', 4);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-7', 'Mercedes-Benz', 4);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-8', 'Mercedes-Benz', 4);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MEB-X-9', 'Mercedes-Benz', 4);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-1', 'Marcopolo', 5);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-2', 'Marcopolo', 5);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-3', 'Marcopolo', 5);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-4', 'Marcopolo', 5);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-5', 'Marcopolo', 5);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-6', 'Marcopolo', 5);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-7', 'Marcopolo', 5);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-8', 'Marcopolo', 5);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('MA-P-9', 'Marcopolo', 5);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IVX-11', 'Iveco', 6);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IVX-12', 'Iveco', 6);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IVX-13', 'Iveco', 6);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IVX-14', 'Iveco', 6);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IVX-15', 'Iveco', 6);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('IVX-16', 'Iveco', 6);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-1', 'Agrale', 7);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-2', 'Agrale', 7);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-3', 'Agrale', 7);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-4', 'Agrale', 7);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-5', 'Agrale', 7);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-6', 'Agrale', 7);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-7', 'Agrale', 7);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-8', 'Agrale', 7);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AGR-9', 'Agrale', 7);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('SCA-1', 'Scania', 8);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('SCA-2', 'Scania', 8);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('SCA-3', 'Scania', 8);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('SCA-4', 'Scania', 8);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('SCA-5', 'Scania', 8);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('SCA-6', 'Scania', 8);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('SCA-7', 'Scania', 8);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('SCA-8', 'Scania', 8);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('SCA-9', 'Scania', 8);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('JIM-1', 'Jimbei', 9);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('JIM-2', 'Jimbei', 9);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('JIM-3', 'Jimbei', 9);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('JIM-4', 'Jimbei', 9);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('JIM-5', 'Jimbei', 9);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('JIM-6', 'Jimbei', 9);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('JIM-7', 'Jimbei', 9);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('JIM-8', 'Jimbei', 9);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('JIM-9', 'Jimbei', 9);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('INT-1', 'International', 10);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('INT-2', 'International', 10);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('INT-3', 'International', 10);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('INT-4', 'International', 10);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('INT-5', 'International', 10);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('INT-6', 'International', 10);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('INT-7', 'International', 10);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('INT-8', 'International', 10);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('INT-9', 'International', 10);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VW-1', 'VW', 11);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VW-2', 'VW', 11);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VW-3', 'VW', 11);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VW-4', 'VW', 11);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VW-5', 'VW', 11);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VW-6', 'VW', 11);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VW-7', 'VW', 11);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VW-8', 'VW', 11);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VWXK-1', 'VWXK', 12);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VWXK-2', 'VWXK', 12);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VWXK-3', 'VWXK', 12);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VWXK-4', 'VWXK', 12);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VWXK-5', 'VWXK', 12);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VWXK-6', 'VWXK', 12);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VWXK-7', 'VWXK', 12);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VWXK-8', 'VWXK', 12);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('VWXK-9', 'VWXK', 12);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-1', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-2', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-3', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-4', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-5', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-6', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-7', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-8', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-9', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-10', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-11', 'Ford', 13);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('FOR-12', 'Ford', 13);

INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AU-1', 'Audi', 14);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AU-2', 'Audi', 14);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AU-3', 'Audi', 14);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AU-4', 'Audi', 14);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AU-5', 'Audi', 14);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AU-6', 'Audi', 14);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AU-7', 'Audi', 14);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AU-8', 'Audi', 14);
INSERT INTO veiculos(nome, modelo, linha_id)
VALUES ('AU-9', 'Audi', 14);

INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (1, -23.548668, -46.636499);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (2, -23.546253, -46.647709);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (3, -23.532774, -46.671458);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (4, -23.534786, -46.663328);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (5, -23.517184, -46.639647);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (6, -23.514316, -46.646785);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (7, -23.546818, -46.643860);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (8, -23.540643, -46.670171);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (9, -23.533626, -46.653709);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (10, -23.521284, -46.630249);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (11, -23.546831, -46.633018);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (12, -23.521312, -46.671013);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (13, -23.531216, -46.630632);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (14, -23.513447, -46.660682);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (15, -23.526060, -46.642799);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (16, -23.549178, -46.670960);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (17, -23.523280, -46.644230);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (18, -23.522330, -46.641500);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (19, -23.514160, -46.652167);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (20, -23.533144, -46.663053);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (21, -23.527754, -46.662216);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (22, -23.538763, -46.633911);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (23, -23.518197, -46.679367);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (24, -23.528310, -46.633856);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (25, -23.539486, -46.677334);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (26, -23.545345, -46.646478);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (27, -23.521080, -46.668133);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (28, -23.529491, -46.640737);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (29, -23.521468, -46.652349);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (30, -23.519033, -46.648083);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (31, -23.524637, -46.673905);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (32, -23.518073, -46.636987);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (33, -23.520472, -46.657807);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (34, -23.540833, -46.667909);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (35, -23.518256, -46.651399);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (36, -23.511381, -46.651739);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (37, -23.535664, -46.641595);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (38, -23.533623, -46.632335);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (39, -23.516492, -46.679196);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (40, -23.542105, -46.660885);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (41, -23.520579, -46.665365);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (42, -23.511877, -46.648370);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (43, -23.520218, -46.652328);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (44, -23.512488, -46.662886);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (45, -23.519460, -46.674871);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (46, -23.528620, -46.676400);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (47, -23.535896, -46.679961);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (48, -23.523896, -46.649481);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (49, -23.548272, -46.639200);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (50, -23.545186, -46.641595);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (51, -23.516238, -46.642502);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (52, -23.537005, -46.635882);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (53, -23.548791, -46.647719);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (54, -23.548393, -46.663390);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (55, -23.542972, -46.640451);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (56, -23.511259, -46.638007);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (57, -23.531033, -46.643364);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (58, -23.517516, -46.635887);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (59, -23.518675, -46.656402);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (60, -23.544417, -46.655782);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (61, -23.514826, -46.646628);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (62, -23.539958, -46.668945);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (63, -23.549565, -46.651495);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (64, -23.543856, -46.641370);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (65, -23.525712, -46.636270);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (66, -23.529805, -46.650065);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (67, -23.524863, -46.678546);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (68, -23.531005, -46.649531);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (69, -23.533409, -46.650385);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (70, -23.537465, -46.660952);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (71, -23.530817, -46.665882);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (72, -23.528638, -46.634053);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (73, -23.522942, -46.655016);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (74, -23.511357, -46.679863);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (75, -23.518648, -46.658697);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (76, -23.547927, -46.669365);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (77, -23.539095, -46.646950);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (78, -23.544170, -46.671489);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (79, -23.515691, -46.648425);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (80, -23.518039, -46.640097);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (81, -23.544208, -46.662808);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (82, -23.546433, -46.660225);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (83, -23.547669, -46.675265);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (84, -23.525728, -46.653382);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (85, -23.517399, -46.657843);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (86, -23.539228, -46.650200);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (87, -23.527528, -46.654600);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (88, -23.535755, -46.639213);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (89, -23.520160, -46.645195);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (90, -23.545581, -46.646421);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (91, -23.542466, -46.654512);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (92, -23.525115, -46.663091);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (93, -23.538826, -46.649552);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (94, -23.546649, -46.645761);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (95, -23.515732, -46.639104);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (96, -23.524976, -46.632589);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (97, -23.548775, -46.678380);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (98, -23.514056, -46.654408);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (99, -23.533519, -46.640684);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (100, -23.543840, -46.635238);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (101, -23.548304, -46.648928);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (102, -23.533766, -46.649618);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (103, -23.529685, -46.633213);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (104, -23.543885, -46.649436);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (105, -23.521944, -46.630336);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (106, -23.539670, -46.638376);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (107, -23.512308, -46.649687);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (108, -23.519542, -46.672711);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (109, -23.533585, -46.667081);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (110, -23.536552, -46.673696);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (111, -23.535976, -46.667303);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (112, -23.523301, -46.662837);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (113, -23.539095, -46.642977);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (114, -23.532417, -46.670901);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (115, -23.532487, -46.676420);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (116, -23.518382, -46.636842);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (117, -23.526231, -46.673760);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (118, -23.519141, -46.671232);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (119, -23.510042, -46.652396);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (120, -23.531273, -46.670945);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (121, -23.516438, -46.631115);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (122, -23.538219, -46.678634);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (123, -23.517265, -46.663380);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (124, -23.529330, -46.640899);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (125, -23.518766, -46.651707);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (126, -23.544880, -46.647428);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (127, -23.543316, -46.672114);
INSERT INTO posicao_veiculo (veiculo_id, latitude, longitude)
VALUES (128, -23.519505, -46.637094);

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
