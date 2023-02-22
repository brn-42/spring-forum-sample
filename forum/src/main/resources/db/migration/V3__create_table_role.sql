create table role(
    id serial not null,
    name varchar(50) not null,
    primary key(id)
);

create table forum_user_role(
    id serial not null,
    forum_user_id bigint not null,
    role_id bigint not null,

    primary key(id),
    foreign key(forum_user_id) references forum_user(id),
    foreign key(role_id) references role(id)
);

insert into role (id, name) values (1, 'READ_WRITE');
insert into role (id, name) values (2, 'READ_ONLY');
insert into role (id, name) values (3, 'ADMIN');
