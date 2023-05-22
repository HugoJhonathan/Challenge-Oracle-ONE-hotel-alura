CREATE TABLE usuario(
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    login VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE hospede(
   id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   nome VARCHAR(255) NOT NULL,
   sobrenome VARCHAR(255) NOT NULL,
   data_nascimento DATE NOT NULL,
   nacionalidade VARCHAR(50) NOT NULL,
   telefone VARCHAR(20) NOT NULL
);

CREATE TABLE reserva(
   id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   data_entrada DATE NOT NULL,
   data_saida DATE NOT NULL,
   valor DECIMAL(10,2) NOT NULL,
   forma_pagamento INT NOT NULL,
   hospede_id BIGINT NULL,
   FOREIGN KEY (hospede_id) REFERENCES hospede(id)
);

START TRANSACTION;
INSERT INTO hospede (nome, sobrenome, data_nascimento, nacionalidade, telefone)
VALUES
    ("Jon",   "Doe",   "1991-01-01", "estadunidense", "99999999999"),
    ("Jane",  "Doe",   "1990-01-01", "estadunidense", "88888888888"),
    ("Alice", "Smith", "1994-01-01", "estadunidense", "77777777777");
INSERT INTO reserva (data_entrada, data_saida, valor, forma_pagamento, hospede_id)
VALUES
    (NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY), 250, 2, 1),
    (NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 500, 1, 2),
    (NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 750, 3, 3);
INSERT INTO usuario (login, senha)
VALUES
    ("admin", "$2a$10$ZLdtsnuXXRVrJWxtY5/K2ekQ7LnR0LA8ToPX.qQiYr6MuAQ6uy5hy");
COMMIT;