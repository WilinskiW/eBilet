CREATE TABLE IF NOT EXISTS events
(
    id          UUID        DEFAULT uuidv7() PRIMARY KEY,
    name        VARCHAR(255)                 NOT NULL,
    description TEXT,
    start_date  TIMESTAMPTZ                  NOT NULL,
    end_date    TIMESTAMPTZ                  NOT NULL,
    city        VARCHAR(100)                 NOT NULL,
    location    VARCHAR(255)                 NOT NULL,
    version     BIGINT      DEFAULT 0        NOT NULL,
    created_at  TIMESTAMPTZ DEFAULT now()    NOT NULL,
    updated_at  TIMESTAMPTZ DEFAULT now()    NOT NULL
);

CREATE TABLE IF NOT EXISTS seats
(
    id         UUID        DEFAULT uuidv7()    PRIMARY KEY,
    event_id   UUID                            NOT NULL,
    category   VARCHAR(50)                     NOT NULL,
    row_num    VARCHAR(10)                     NOT NULL,
    seat_num   VARCHAR(10)                     NOT NULL,
    status     VARCHAR(20) DEFAULT 'AVAILABLE' NOT NULL,
    price      DECIMAL(19, 2)                  NOT NULL,
    version    BIGINT      DEFAULT 0           NOT NULL,
    created_at TIMESTAMPTZ DEFAULT now()       NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT now()       NOT NULL
);

CREATE TABLE IF NOT EXISTS reservations
(
    id           UUID        DEFAULT uuidv7() PRIMARY KEY,
    first_name   VARCHAR(64)                   NOT NULL,
    last_name    VARCHAR(255)                  NOT NULL,
    email        VARCHAR(255)                  NOT NULL,
    phone_number VARCHAR(25)                   NOT NULL,
    seat_id      UUID                          NOT NULL UNIQUE,
    status       VARCHAR(20) DEFAULT 'PENDING' NOT NULL,
    version      BIGINT      DEFAULT 0         NOT NULL,
    created_at   TIMESTAMPTZ DEFAULT now()     NOT NULL,
    updated_at   TIMESTAMPTZ DEFAULT now()     NOT NULL
);

ALTER TABLE reservations
    ADD CONSTRAINT FK_RESERVATIONS_ON_SEAT FOREIGN KEY (seat_id) REFERENCES seats (id);

ALTER TABLE seats
    ADD CONSTRAINT FK_SEATS_ON_EVENT FOREIGN KEY (event_id) REFERENCES events (id);