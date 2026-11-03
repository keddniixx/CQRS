CREATE TABLE transaction_audit_logs (
    id UUID PRIMARY KEY,
    transaction_id UUID NOT NULL,
    action VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
