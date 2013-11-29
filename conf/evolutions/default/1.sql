# --- !Ups
create table groups (
  id bigserial primary key,
  name varchar not null
);

create table tasks (
  id bigserial primary key,
  group_id bigint not null references groups(id),
  name varchar,
  done boolean not null default false
);

# --- !Downs
drop table tasks;

drop table groups;