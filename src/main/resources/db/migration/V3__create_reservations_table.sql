CREATE TABLE IF NOT EXISTS reservations
(
    id           UUID        DEFAULT uuidv7() PRIMARY KEY,
    first_name   VARCHAR(64)                  NOT NULL,
    last_name    VARCHAR(255)                 NOT NULL,
    email        VARCHAR(255)                 NOT NULL,
    phone_number VARCHAR(25)                  NOT NULL,
    seat_id      UUID                         NOT NULL,
    status       VARCHAR(20)                  NOT NULL,
    version      BIGINT      DEFAULT 0        NOT NULL,
    created_at   TIMESTAMPTZ DEFAULT now()    NOT NULL,
    updated_at   TIMESTAMPTZ DEFAULT now()    NOT NULL
);

ALTER TABLE reservations
    ADD CONSTRAINT FK_RESERVATIONS_ON_SEAT FOREIGN KEY (seat_id) REFERENCES seats (id);