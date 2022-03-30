create or replace function set_def_role()
    returns trigger
    language PLPGSQL
as
$$
begin
insert into roles_users(role_id, users_id)
values (1, NEW.id);
return NEW;
end;
$$;

create trigger set_def_role_trigger
    after insert
    on users
    for each row
    execute procedure set_def_role();