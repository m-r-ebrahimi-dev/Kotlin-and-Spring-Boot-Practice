create table if not exists tasks
(
    id
    varchar
(
    60
) default UUID
(
) primary key,
    title varchar
(
    60
) not null
    );