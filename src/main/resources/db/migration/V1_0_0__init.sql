create table IF NOT EXISTS   body_measurement
(
    id        bigserial not null,
    calves    int4,
    chest     int4,
    date      date,
    hips      int4,
    shoulder  int4,
    thigh     int4,
    waist     int4,
    user_id   int8,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);
create table IF NOT EXISTS  muscles
(
    id           bigserial not null,
    muscle_group varchar(255),
    created_at timestamp not null default now()::timestamp,
    updated_at timestamp,
    primary key (id)
);
create table IF NOT EXISTS  person_weigth
(
    id      bigserial not null,
    date    date,
    weight  float8    not null,
    user_id int8,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);
create table IF NOT EXISTS  roles
(
    id   bigserial    not null,
    role varchar(255) not null,
    created_at timestamp not null default now()::timestamp,
    updated_at timestamp,
    primary key (id)
);
create table IF NOT EXISTS  roles_users
(
    roles_id int8 not null,
    users_id int8 not null,
    primary key (roles_id, users_id)
);
create table IF NOT EXISTS  training_days
(
    id                    bigserial    not null,
    day                   int4         not null,
    description_exercises varchar(255) not null,
    last_approaches       int4         not null,
    last_date             date,
    last_repeats          int4         not null,
    last_weight           int4         not null,
    muscle_id             int8,
    user_id               int8,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);
create table IF NOT EXISTS  users
(
    id                bigserial    not null,
    first_weight      float8       not null,
    height            float8       not null,
    password          varchar(255) not null,
    registration_date date,
    telegram_id       int8,
    training_type     int4,
    username          varchar(255) not null,
    deleted           bool default false,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);
create table IF NOT EXISTS  users_statistic
(
    id         bigserial not null,
    approaches int4      not null,
    date       date,
    repeats    int4      not null,
    weight     int4      not null,
    muscle_id  int8,
    user_id    int8,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);

alter table body_measurement
    add constraint body_date unique (date);

alter table muscles
    add constraint muscles_group unique (muscle_group);

alter table person_weigth
    add constraint weight_date unique (date);

alter table users
    add constraint statistic_name unique (username);

alter table users_statistic
    add constraint statistic_date unique (date);
alter table body_measurement
    add constraint body_users foreign key (user_id) references users;
alter table person_weigth
    add constraint person_user foreign key (user_id) references users;
alter table roles_users
    add constraint roles_users foreign key (users_id) references users;
alter table roles_users
    add constraint training_roles foreign key (roles_id) references roles;
alter table training_days
    add constraint training_muscles foreign key (muscle_id) references muscles;
alter table training_days
    add constraint trainings_users foreign key (user_id) references users;
alter table users_statistic
    add constraint statistic_muscles foreign key (muscle_id) references muscles;
alter table users_statistic
    add constraint statistic_users foreign key (user_id) references users;

create or replace function set_def_role()
    returns trigger
    language PLPGSQL
as
$$
begin
    insert into roles_users(roles_id, users_id)
    values (1, NEW.id);
    return NEW;
end;
$$;

create trigger set_def_role_trigger
    after insert
    on users
    for each row
execute procedure set_def_role();