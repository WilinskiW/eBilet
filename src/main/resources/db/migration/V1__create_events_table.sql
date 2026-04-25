CREATE TABLE IF NOT EXISTS events
(
    id          UUID        DEFAULT uuidv7() PRIMARY KEY,
    name        VARCHAR(255)                 NOT NULL,
    description TEXT,
    start_date  TIMESTAMPTZ                  NOT NULL,
    end_date    TIMESTAMPTZ                  NOT NULL,
    city        VARCHAR(100)                 NOT NULL,
    location    VARCHAR(255)                 NOT NULL,
    price       DECIMAL(19, 2)               NOT NULL,
    version     BIGINT      DEFAULT 0        NOT NULL,
    created_at  TIMESTAMPTZ DEFAULT now()    NOT NULL,
    updated_at  TIMESTAMPTZ DEFAULT now()    NOT NULL
);