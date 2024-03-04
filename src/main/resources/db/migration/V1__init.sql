
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    limite INTEGER,
    saldoInicial INTEGER
);

CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    valor INTEGER,
    tipo CHAR(1),
    descricao TEXT,
    realizadaEm TIMESTAMP NOT NULL,
    clienteId INTEGER REFERENCES cliente(id) ON DELETE CASCADE
);

INSERT INTO cliente (limite, saldoInicial) VALUES (100000, 0);
INSERT INTO cliente (limite, saldoInicial) VALUES (80000, 0);
INSERT INTO cliente (limite, saldoInicial) VALUES (1000000, 0);
INSERT INTO cliente (limite, saldoInicial) VALUES (10000000, 0);
INSERT INTO cliente (limite, saldoInicial) VALUES (500000, 0);