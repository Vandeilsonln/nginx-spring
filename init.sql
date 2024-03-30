CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL,
    limite INTEGER NOT NULL,
    saldo INTEGER NOT NULL
);

CREATE TABLE transacoes (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER REFERENCES clientes(id),
    valor INTEGER NOT NULL,
    tipo VARCHAR(10) not null,
    realizada_em TIMESTAMP NOT NULL
);

INSERT INTO clientes (nome, limite, saldo)
VALUES
    ('John', 100000, 0),
    ('Alice', 80000, 0),
    ('Bob', 1000000, 0),
    ('Emily', 10000000, 0),
    ('Michael', 500000, 0);