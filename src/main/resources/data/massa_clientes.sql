DROP TABLE IF EXISTS cliente


create table cliente(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    limite INTEGER,
    saldo_inicial INTEGER
);

INSERT INTO cliente (limite, saldo_inicial) VALUES (100000, 0);
INSERT INTO cliente (limite, saldo_inicial) VALUES (80000, 0);
INSERT INTO cliente (limite, saldo_inicial) VALUES (1000000, 0);
INSERT INTO cliente (limite, saldo_inicial) VALUES (10000000, 0);
INSERT INTO cliente (limite, saldo_inicial) VALUES (500000, 0);