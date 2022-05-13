

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
)