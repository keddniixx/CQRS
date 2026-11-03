CREATE INDEX idx_cards_number_date ON cards (number, date);
CREATE INDEX idx_clients_username ON clients (username);
CREATE INDEX idx_transactions_from_id ON transactions (from_id);
CREATE INDEX idx_transactions_to_id ON transactions (to_id);
