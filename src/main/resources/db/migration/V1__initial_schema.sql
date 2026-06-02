CREATE TABLE IF NOT EXISTS locations
(
    id         UUID        DEFAULT uuidv7() PRIMARY KEY,
    name       VARCHAR(255)              NOT NULL,
    address    VARCHAR(255)              NOT NULL,
    city       VARCHAR(255)              NOT NULL,
    country    VARCHAR(255)              NOT NULL,
    version    BIGINT      DEFAULT 0     NOT NULL,
    created_at TIMESTAMPTZ DEFAULT now() NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT now() NOT NULL,
    CONSTRAINT uc_locations_name UNIQUE (name),
    CONSTRAINT uc_locations_address UNIQUE (address)
);

CREATE TABLE IF NOT EXISTS sectors
(
    id            UUID        DEFAULT uuidv7() PRIMARY KEY,
    location_id   UUID                      NOT NULL,
    name          VARCHAR(255)              NOT NULL,
    sector_type   VARCHAR(255)              NOT NULL,
    rows_count    INTEGER,
    seats_per_row INTEGER,
    max_capacity  INTEGER,
    tags          VARCHAR(255)              NOT NULL,
    version       BIGINT      DEFAULT 0     NOT NULL,
    created_at    TIMESTAMPTZ DEFAULT now() NOT NULL,
    updated_at    TIMESTAMPTZ DEFAULT now() NOT NULL,
    CONSTRAINT FK_SECTORS_ON_LOCATION FOREIGN KEY (location_id) REFERENCES locations (id)
);

-- 2. LOGIKA WYDARZEŃ (EVENTS & EVENT SECTORS)
CREATE TABLE IF NOT EXISTS events
(
    id          UUID        DEFAULT uuidv7() PRIMARY KEY,
    location_id UUID                      NOT NULL,
    name        VARCHAR(255)              NOT NULL,
    description VARCHAR(255),
    start_date  TIMESTAMPTZ               NOT NULL,
    end_date    TIMESTAMPTZ               NOT NULL,
    version     BIGINT      DEFAULT 0     NOT NULL,
    created_at  TIMESTAMPTZ DEFAULT now() NOT NULL,
    updated_at  TIMESTAMPTZ DEFAULT now() NOT NULL,
    CONSTRAINT FK_EVENTS_ON_LOCATION FOREIGN KEY (location_id) REFERENCES locations (id)
);

CREATE TABLE IF NOT EXISTS event_sectors
(
    id         UUID        DEFAULT uuidv7() PRIMARY KEY,
    event_id   UUID                      NOT NULL,
    sector_id  UUID                      NOT NULL,
    price      DECIMAL(10, 2)            NOT NULL,
    version    BIGINT      DEFAULT 0     NOT NULL,
    created_at TIMESTAMPTZ DEFAULT now() NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT now() NOT NULL,
    CONSTRAINT FK_EVENT_SECTORS_ON_EVENT FOREIGN KEY (event_id) REFERENCES events (id),
    CONSTRAINT FK_EVENT_SECTORS_ON_SECTOR FOREIGN KEY (sector_id) REFERENCES sectors (id)
);

-- 3. TRANSAKCYJNOŚĆ I SPRZEDAŻ (SEATS & RESERVATIONS)
CREATE TABLE IF NOT EXISTS seats
(
    id               UUID        DEFAULT uuidv7() PRIMARY KEY,
    event_sector_id  UUID                            NOT NULL,
    status           VARCHAR(255)                    NOT NULL,
    row_number       INTEGER,
    seat_number      INTEGER,
    available_count  INTEGER,
    version          BIGINT      DEFAULT 0           NOT NULL,
    created_at       TIMESTAMPTZ DEFAULT now()       NOT NULL,
    updated_at       TIMESTAMPTZ DEFAULT now()       NOT NULL,
    CONSTRAINT FK_SEATS_ON_EVENT_SECTOR FOREIGN KEY (event_sector_id) REFERENCES event_sectors (id)
);

CREATE TABLE IF NOT EXISTS reservations
(
    id           UUID        DEFAULT uuidv7() PRIMARY KEY,
    seat_id      UUID                          NOT NULL,
    first_name   VARCHAR(255)                  NOT NULL,
    last_name    VARCHAR(255)                  NOT NULL,
    email        VARCHAR(255)                  NOT NULL,
    phone_number VARCHAR(255)                  NOT NULL,
    status       VARCHAR(255)                  NOT NULL,
    version      BIGINT      DEFAULT 0         NOT NULL,
    created_at   TIMESTAMPTZ DEFAULT now()     NOT NULL,
    updated_at   TIMESTAMPTZ DEFAULT now()     NOT NULL,
    CONSTRAINT FK_RESERVATIONS_ON_SEAT FOREIGN KEY (seat_id) REFERENCES seats (id),
    CONSTRAINT uc_reservations_seat UNIQUE (seat_id)
);