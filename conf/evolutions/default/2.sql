# --- !Ups
create unique index groups_name_uidx on groups(name);

# --- !Downs
drop index group_name_uidx;