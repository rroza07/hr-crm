CREATE TABLE users
(
    id           BIGSERIAL NOT NULL PRIMARY KEY,
    email        VARCHAR(255),
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    password     VARCHAR(255),
    user_role    VARCHAR(255),
    username     VARCHAR(255),
    is_deleted   BOOLEAN DEFAULT FALSE,
    vacancy_id   BIGINT,
    candidate_id BIGINT
);