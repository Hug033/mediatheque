\c postgres
drop database bd_mediatheque;
create user user_media with password 'tux' login createdb;
create database bd_mediatheque;
\c bd_mediatheque

create table person (
    id serial not null,
    firstname varchar not null,
    lastname varchar not null,
    birthday varchar not null,

    primary key(id)
);

create table users (
    person_id int not null,
    profil bytea not null,
    login varchar not null,
    password varchar not null,
    telephone varchar not null,
    registration varchar not null,
    state int not null,

    foreign key(person_id) REFERENCES person(id)
);

create table media (
    ref varchar not null,
    title varchar not null,
    description varchar not null,
    image bytea not null,
    category int not null,

    primary key(ref)
);

create table category (
    id serial not null,
    name varchar not null,
    type varchar not null,

    primary key(id)
);

create table emprunt (
    id serial not null,
    startDate date not null,
    endDate date not null,
    state varchar not null,
    personId int not null,
    mediaRef int not null,

    primary key(id)
);

create table book (
    media_ref varchar not null,
    author int not null,
    nb_pages int not null,

    foreign key(media_ref) REFERENCES media(ref)
);

create table dvd (
    media_ref varchar not null,
    director int not null,
    duration float not null,
    minAge int not null,

    foreign key(media_ref) REFERENCES media(ref)
);

create table cd (
    media_ref varchar not null,
    composer int not null,
    track int not null,

    foreign key(media_ref) REFERENCES media(ref)
);

create table rate (
    media_ref varchar not null,
    rate int not null,

    foreign key(media_ref) REFERENCES media(ref)
);

create table token (
    id_user int not null,
    token varchar not null,
    validate varchar not null,

    foreign key(id_user) REFERENCES user(id)
);

GRANT ALL PRIVILEGES ON person TO user_media;
GRANT ALL PRIVILEGES ON person_id_seq TO user_media;
GRANT ALL PRIVILEGES ON users TO user_media;
GRANT ALL PRIVILEGES ON media TO user_media;
GRANT ALL PRIVILEGES ON category TO user_media;
GRANT ALL PRIVILEGES ON category_id_seq TO user_media;
GRANT ALL PRIVILEGES ON emprunt TO user_media;
GRANT ALL PRIVILEGES ON emprunt_id_seq TO user_media;
GRANT ALL PRIVILEGES ON book TO user_media;
GRANT ALL PRIVILEGES ON dvd TO user_media;
GRANT ALL PRIVILEGES ON cd TO user_media;
GRANT ALL PRIVILEGES ON rate TO user_media;
GRANT ALL PRIVILEGES ON token TO user_media;
