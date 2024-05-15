CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    limits INTEGER NOT NULL,
    balance INTEGER NOT NULL
);

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customers(id),
    amount INTEGER NOT NULL,
    type VARCHAR(1) not null,
    created_at TIMESTAMP NOT NULL
);

INSERT INTO customers (name, limits, balance)
VALUES
    ('John', 100000, 5000),
    ('Alice', 80000, 8000),
    ('Bob', 1000000, 25000),
    ('Emily', 10000000, 7850),
    ('Michael', 500000, 9870);

INSERT INTO transactions(customer_id, amount, type, created_at)
VALUES
    (1, 100, 'C', CURRENT_TIMESTAMP),
    (2, 150, 'D', CURRENT_TIMESTAMP),
    (3, 200, 'C', CURRENT_TIMESTAMP),
    (4, 250, 'D', CURRENT_TIMESTAMP),
    (5, 300, 'C', CURRENT_TIMESTAMP),
    (1, 200, 'D', CURRENT_TIMESTAMP),
    (2, 250, 'C', CURRENT_TIMESTAMP),
    (3, 300, 'D', CURRENT_TIMESTAMP),
    (4, 350, 'C', CURRENT_TIMESTAMP),
    (5, 400, 'D', CURRENT_TIMESTAMP);