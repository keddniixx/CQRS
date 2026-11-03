CREATE VIEW active_cards_summary AS
SELECT c.id, c.number, a.balance
FROM cards c
JOIN accounts a ON c.account_id = a.id;
