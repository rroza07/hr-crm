CREATE TABLE candidates
(
    id           BIGSERIAL NOT NULL PRIMARY KEY,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    email        VARCHAR(255),
    phone_number VARCHAR(255),
    skype        VARCHAR(255),
    file_data    BYTEA,
    cv           VARCHAR(255),
    file_name    VARCHAR(255),
    department   VARCHAR(255),
    degree       VARCHAR(255),
    is_deleted   BOOLEAN DEFAULT FALSE,
    notes        VARCHAR(255),
    vacancy_id   BIGINT REFERENCES vacancies (id),
    user_id      BIGINT REFERENCES users (id)
);