CREATE TABLE vacancies
(
    id           BIGSERIAL NOT NULL PRIMARY KEY,
    position     VARCHAR(255),
    title        VARCHAR(255),
    description  VARCHAR(255),
    is_deleted   BOOLEAN DEFAULT FALSE,
    created_at   TIMESTAMP,
    modified_at  TIMESTAMP,
    candidate_id BIGINT,
    user_id      BIGINT REFERENCES users (id)
);