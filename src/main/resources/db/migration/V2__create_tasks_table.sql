-- V2: Create tasks table

CREATE TABLE tasks (
    id              BIGSERIAL    PRIMARY KEY,
    title           VARCHAR(200) NOT NULL,
    description     TEXT,
    status          VARCHAR(20)  NOT NULL DEFAULT 'TODO',
    priority        VARCHAR(20)  NOT NULL DEFAULT 'MEDIUM',
    due_date        DATE,
    user_id         BIGINT       NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    created_at      TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP    NOT NULL DEFAULT NOW()
);

-- Supports fetching all tasks for a given user (primary access pattern).
CREATE INDEX idx_tasks_user_id ON tasks(user_id);

-- Supports filtering and sorting by status, priority, and due date.
CREATE INDEX idx_tasks_status   ON tasks(status);
CREATE INDEX idx_tasks_priority ON tasks(priority);
CREATE INDEX idx_tasks_due_date ON tasks(due_date);
