
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    limite INTEGER,
    saldo_inicial INTEGER
);

CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    valor INTEGER,
    tipo CHAR(1),
    descricao TEXT,
    realizada_em TIMESTAMP NOT NULL,
    cliente_id INTEGER REFERENCES cliente(id) ON DELETE CASCADE
);

INSERT INTO cliente (limite, saldo_inicial) VALUES (100000, 0);
INSERT INTO cliente (limite, saldo_inicial) VALUES (80000, 0);
INSERT INTO cliente (limite, saldo_inicial) VALUES (1000000, 0);
INSERT INTO cliente (limite, saldo_inicial) VALUES (10000000, 0);
INSERT INTO cliente (limite, saldo_inicial) VALUES (500000, 0);