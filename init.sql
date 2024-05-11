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
    ('John', 100000, 5000),
    ('Alice', 80000, 8000),
    ('Bob', 1000000, 25000),
    ('Emily', 10000000, 7850),
    ('Michael', 500000, 9870);

INSERT INTO transacoes(cliente_id, valor, tipo, realizada_em)
VALUES
    (1, 100, 'Compra', CURRENT_TIMESTAMP),
    (2, 150, 'Venda', CURRENT_TIMESTAMP),
    (3, 200, 'Compra', CURRENT_TIMESTAMP),
    (4, 250, 'Venda', CURRENT_TIMESTAMP),
    (5, 300, 'Compra', CURRENT_TIMESTAMP),
    (1, 200, 'Venda', CURRENT_TIMESTAMP),
    (2, 250, 'Compra', CURRENT_TIMESTAMP),
    (3, 300, 'Venda', CURRENT_TIMESTAMP),
    (4, 350, 'Compra', CURRENT_TIMESTAMP),
    (5, 400, 'Venda', CURRENT_TIMESTAMP);