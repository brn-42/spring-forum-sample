create table course(
    id serial not null,
    name varchar(50) not null,
    category varchar(50) not null,
    primary key(id)
);

create table forum_user(
    id serial not null,
    name varchar(50) not null,
    email varchar(50) not null,
    primary key(id)
);

create table topic(
    id serial not null,
    title varchar(50) not null,
    message varchar(255) not null,
    created_at timestamp not null,
    status varchar(25) not null,
    course_id bigint not null,
    author_id bigint not null,

    primary key(id),
    foreign key(course_id) references course(id),
    foreign key(author_id) references forum_user(id)
);

create table response(
    id serial not null,
    message varchar(255) not null,
    created_at timestamp not null,
    author_id bigint not null,
    topic_id bigint not null,
    resolved boolean not null,

    foreign key(topic_id) references topic(id),
    foreign key(author_id) references forum_user(id)
);