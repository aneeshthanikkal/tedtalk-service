

CREATE SCHEMA IF NOT EXISTS io; 
CREATE TABLE IF NOT EXISTS io.t_tedtalk
(
    tedtalk_id character varying NOT NULL,
    title character varying,
    date character varying,
    author character varying,
    link character varying,
    likes numeric,
    views numeric,
    CONSTRAINT t_tedtalk_pkey PRIMARY KEY (tedtalk_id)
);
CREATE TABLE IF NOT EXISTS io.t_roles
(
    id character varying NOT NULL,
    name character varying NOT NULL,
    CONSTRAINT t_roles_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS io.t_user
(
    id character varying NOT NULL,
    user_name character varying NOT NULL,
    password character varying NOT NULL,
    CONSTRAINT t_user_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS io.t_user_roles
(
    user_id character varying NOT NULL,
    role_id character varying NOT NULL,
    CONSTRAINT t_user_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES io.t_roles (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES io.t_user (id) 
);

CREATE TABLE IF NOT EXISTS io.t_favourite_tedtalk
(
    user_id character varying(36) NOT NULL,
    tedtalk_id character varying(36) NOT NULL,
    CONSTRAINT t_favourite_tedtalk_pkey PRIMARY KEY (user_id, tedtalk_id),
    CONSTRAINT fk_tadtalk_id FOREIGN KEY (tedtalk_id)
        REFERENCES io.t_tedtalk (tedtalk_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES io.t_user (id)
);

INSERT INTO io.t_roles(
	id, name)
	VALUES ('21f629d6-aa6d-11ec-b909-0242ac120002', 'ROLE_READ');
	
INSERT INTO io.t_roles(
	id, name)
	VALUES ('5de7cdc8-aa6d-11ec-b909-0242ac120002', 'ROLE_WRITE');

INSERT INTO io.t_user(
	id, user_name, password)
	VALUES ('21f629d6-aa6d-11ec-b909-0242ac120002', 'iOAdmin', '$2a$10$flwZ6i.1EbQt/skSCqf4Juct1yeGw9fK/dSfzWwJaoKbY1eS8dxJ.');

INSERT INTO io.t_user_roles(
	user_id, role_id)
	VALUES ('21f629d6-aa6d-11ec-b909-0242ac120002', '21f629d6-aa6d-11ec-b909-0242ac120002');
INSERT INTO io.t_user_roles(
	user_id, role_id)
	VALUES ('21f629d6-aa6d-11ec-b909-0242ac120002', '5de7cdc8-aa6d-11ec-b909-0242ac120002');


