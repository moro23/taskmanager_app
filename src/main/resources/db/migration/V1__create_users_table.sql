-- V1: Create users table
--
-- Flyway convention: V{version}__{description}.sql (double underscore required).
-- Migrations are immutable once applied; all future schema changes require a new versioned script.

CREATE TABLE users (
    id              BIGSERIAL    PRIMARY KEY,
    email           VARCHAR(255) NOT NULL UNIQUE,
    password_hash   VARCHAR(255) NOT NULL,
    full_name       VARCHAR(100) NOT NULL,
    role            VARCHAR(20)  NOT NULL DEFAULT 'USER',
    created_at      TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP    NOT NULL DEFAULT NOW()
);

-- Supports login lookups by email (high-frequency, equality predicate).
CREATE INDEX idx_users_email ON users(email);
