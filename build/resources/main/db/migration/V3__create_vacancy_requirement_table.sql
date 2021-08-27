CREATE TABLE vacancy_requirement
(
    vacancy_req_FK BIGINT REFERENCES vacancies (id),
    title          VARCHAR(255)
);