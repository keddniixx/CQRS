CREATE TABLE accounts (
    id UUID PRIMARY KEY,
    number VARCHAR(255) NOT NULL,
    balance DECIMAL NOT NULL
);

CREATE TABLE cards (
    id UUID PRIMARY KEY,
    number VARCHAR(16) NOT NULL,
    date VARCHAR(5) NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    account_id UUID REFERENCES accounts(id)
);

CREATE TABLE clients (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    account_id UUID REFERENCES accounts(id)
);

CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    from_id UUID REFERENCES cards(id),
    to_id UUID REFERENCES cards(id),
    amount DECIMAL NOT NULL
);

CREATE TABLE clients_cards (
    client_id UUID REFERENCES clients(id),
    card_id UUID REFERENCES cards(id),
    PRIMARY KEY (client_id, card_id)
);

CREATE TABLE cards_transactions (
    card_id UUID REFERENCES cards(id),
    transaction_id UUID REFERENCES transactions(id),
    PRIMARY KEY (card_id, transaction_id)
);

CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    dtype VARCHAR(255),
    aggregate_id VARCHAR(36),
    type VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP,
    payload TEXT NOT NULL
);
