-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.2-alpha1
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.io
-- Model Author: ---

SET check_function_bodies = false;
-- ddl-end --

-- object: rest_user | type: ROLE --
-- DROP ROLE IF EXISTS rest_user;
CREATE ROLE rest_user WITH 
	LOGIN
	ENCRYPTED PASSWORD 'rest_user';
-- ddl-end --
COMMENT ON ROLE rest_user IS 'This user is used to connect to Rest Server.
Limited Access is provided for security concenrs';
-- ddl-end --

-- object: sense_admin | type: ROLE --
-- DROP ROLE IF EXISTS sense_admin;
CREATE ROLE sense_admin WITH 
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	LOGIN
	REPLICATION
	ENCRYPTED PASSWORD 'sense_admin';
-- ddl-end --
COMMENT ON ROLE sense_admin IS 'This is the super user of Auto Sense application';
-- ddl-end --

-- object: sense_user | type: ROLE --
-- DROP ROLE IF EXISTS sense_user;
CREATE ROLE sense_user WITH 
	LOGIN
	ENCRYPTED PASSWORD 'sense@123';
-- ddl-end --
COMMENT ON ROLE sense_user IS 'This user will be used to connect to JAVA APIs and front end.';
-- ddl-end --


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: autosense | type: DATABASE --
-- -- DROP DATABASE IF EXISTS autosense;
-- CREATE DATABASE autosense
-- 	ENCODING = 'UTF8'
-- 	LC_COLLATE = 'English_United States.1252'
-- 	LC_CTYPE = 'English_United States.1252'
-- 	TABLESPACE = pg_default
-- 	OWNER = sense_admin;
-- -- ddl-end --
-- 

-- object: appengine | type: SCHEMA --
-- DROP SCHEMA IF EXISTS appengine CASCADE;
CREATE SCHEMA appengine;
-- ddl-end --
ALTER SCHEMA appengine OWNER TO sense_admin;
-- ddl-end --
COMMENT ON SCHEMA appengine IS 'This schema contains all the application tables.';
-- ddl-end --

-- object: repository | type: SCHEMA --
-- DROP SCHEMA IF EXISTS repository CASCADE;
CREATE SCHEMA repository;
-- ddl-end --
ALTER SCHEMA repository OWNER TO sense_admin;
-- ddl-end --

SET search_path TO pg_catalog,public,appengine,repository;
-- ddl-end --

-- object: appengine.seq_object_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS appengine.seq_object_id CASCADE;
CREATE SEQUENCE appengine.seq_object_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE appengine.seq_object_id OWNER TO sense_admin;
-- ddl-end --
COMMENT ON SEQUENCE appengine.seq_object_id IS 'Generate number series for object_table';
-- ddl-end --

-- object: appengine.tb_objects | type: TABLE --
-- DROP TABLE IF EXISTS appengine.tb_objects;
CREATE TABLE appengine.tb_objects (
	obj_id bigint NOT NULL DEFAULT nextval('appengine.seq_object_id'::regclass),
	obj_name character varying(100) NOT NULL,
	obj_type integer NOT NULL,
	created_time timestamptz NOT NULL,
	is_deleted bool NOT NULL,
	CONSTRAINT tb_objects_pk PRIMARY KEY (obj_id),
	CONSTRAINT obj_name_unique UNIQUE (obj_name)

)
TABLESPACE pg_default;
-- ddl-end --
COMMENT ON TABLE appengine.tb_objects IS 'This table contains object details in the database.';
-- ddl-end --
ALTER TABLE appengine.tb_objects OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.seq_user_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS appengine.seq_user_id CASCADE;
CREATE SEQUENCE appengine.seq_user_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE appengine.seq_user_id OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.seq_api_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS appengine.seq_api_id CASCADE;
CREATE SEQUENCE appengine.seq_api_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE appengine.seq_api_id OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.seq_group_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS appengine.seq_group_id CASCADE;
CREATE SEQUENCE appengine.seq_group_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE appengine.seq_group_id OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.seq_type_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS appengine.seq_type_id CASCADE;
CREATE SEQUENCE appengine.seq_type_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE appengine.seq_type_id OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.seq_assign_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS appengine.seq_assign_id CASCADE;
CREATE SEQUENCE appengine.seq_assign_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE appengine.seq_assign_id OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.tb_api_details | type: TABLE --
-- DROP TABLE IF EXISTS appengine.tb_api_details CASCADE;
CREATE TABLE appengine.tb_api_details (
	api_id bigint NOT NULL DEFAULT nextval('appengine.seq_api_id'::regclass),
	api_key uuid NOT NULL,
	assigned_user bigint NOT NULL,
	expires_on timestamptz,
	is_active bool NOT NULL,
	CONSTRAINT tb_objects_uq UNIQUE (api_key),
	CONSTRAINT tb_api_details_pk PRIMARY KEY (api_id)

);
-- ddl-end --
ALTER TABLE appengine.tb_api_details OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.tb_user_groups | type: TABLE --
-- DROP TABLE IF EXISTS appengine.tb_user_groups CASCADE;
CREATE TABLE appengine.tb_user_groups (
	group_id bigint NOT NULL DEFAULT nextval('appengine.seq_group_id'::regclass),
	group_name character varying(50) NOT NULL,
	owner_id bigint NOT NULL,
	created_time timestamptz NOT NULL,
	is_active bool NOT NULL,
	CONSTRAINT tb_group_unique UNIQUE (group_name),
	CONSTRAINT tb_user_groups_pk PRIMARY KEY (group_id)

);
-- ddl-end --
ALTER TABLE appengine.tb_user_groups OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.tb_users | type: TABLE --
-- DROP TABLE IF EXISTS appengine.tb_users CASCADE;
CREATE TABLE appengine.tb_users (
	user_id bigint NOT NULL DEFAULT nextval('appengine.seq_user_id'::regclass),
	login_id character varying(50),
	email_id character varying(100),
	password character varying(100) NOT NULL,
	created_time timestamptz NOT NULL,
	is_active bool NOT NULL,
	expires_on timestamptz,
	first_name character varying(100),
	last_name character varying(100),
	contact_no character varying(100),
	token_verification character varying(100),
	CONSTRAINT tb_users_login_unique UNIQUE (login_id),
	CONSTRAINT tb_users_pk PRIMARY KEY (user_id),
	CONSTRAINT tb_users_email_uq UNIQUE (email_id)

);
-- ddl-end --
ALTER TABLE appengine.tb_users OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.tb_assigned_groups | type: TABLE --
-- DROP TABLE IF EXISTS appengine.tb_assigned_groups CASCADE;
CREATE TABLE appengine.tb_assigned_groups (
	assign_id bigint NOT NULL DEFAULT nextval('appengine.seq_assign_id'::regclass),
	user_id bigint NOT NULL,
	group_id bigint NOT NULL,
	created_time timestamptz NOT NULL,
	CONSTRAINT uq_group_users UNIQUE (user_id,group_id),
	CONSTRAINT tb_assigned_groups_pk PRIMARY KEY (assign_id)

);
-- ddl-end --
ALTER TABLE appengine.tb_assigned_groups OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.tb_obj_types | type: TABLE --
-- DROP TABLE IF EXISTS appengine.tb_obj_types CASCADE;
CREATE TABLE appengine.tb_obj_types (
	type_id bigint NOT NULL DEFAULT nextval('appengine.seq_type_id'::regclass),
	type_name character varying(50) NOT NULL,
	created_time timestamptz NOT NULL,
	CONSTRAINT tb_type_uq UNIQUE (type_name),
	CONSTRAINT tb_obj_types_pk PRIMARY KEY (type_id)

);
-- ddl-end --
ALTER TABLE appengine.tb_obj_types OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.tb_api_access_details | type: TABLE --
-- DROP TABLE IF EXISTS appengine.tb_api_access_details CASCADE;
CREATE TABLE appengine.tb_api_access_details (
	access_id bigint,
	api_id bigint NOT NULL,
	obj_id bigint NOT NULL,
	is_active bool NOT NULL,
	created_time timestamptz NOT NULL,
	expires_on timestamptz,
	CONSTRAINT uq_access_api_objects UNIQUE (api_id,obj_id)

);
-- ddl-end --
ALTER TABLE appengine.tb_api_access_details OWNER TO sense_admin;
-- ddl-end --

-- object: pgcrypto | type: EXTENSION --
-- DROP EXTENSION IF EXISTS pgcrypto CASCADE;
CREATE EXTENSION pgcrypto
      WITH SCHEMA public;
-- ddl-end --

-- object: appengine.seq_session_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS appengine.seq_session_id CASCADE;
CREATE SEQUENCE appengine.seq_session_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE appengine.seq_session_id OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.tb_user_sessions | type: TABLE --
-- DROP TABLE IF EXISTS appengine.tb_user_sessions CASCADE;
CREATE TABLE appengine.tb_user_sessions (
	session_id bigint NOT NULL DEFAULT nextval('appengine.seq_session_id'::regclass),
	user_id bigint,
	session_token character varying(100) NOT NULL,
	session_start timestamptz NOT NULL,
	session_end timestamptz,
	is_active boolean NOT NULL,
	client_ip character varying(30) NOT NULL,
	CONSTRAINT pk_session_id PRIMARY KEY (session_id)

);
-- ddl-end --
ALTER TABLE appengine.tb_user_sessions OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.f_encryptvalue | type: FUNCTION --
-- DROP FUNCTION IF EXISTS appengine.f_encryptvalue(character varying) CASCADE;
CREATE FUNCTION appengine.f_encryptvalue ( p_value character varying)
	RETURNS character varying(1)
	LANGUAGE plpgsql
	VOLATILE 
	CALLED ON NULL INPUT
	SECURITY INVOKER
	COST 100
	AS $$

DECLARE
v_value character varying;
BEGIN
if(p_value is null or p_value='')
then
return p_value;
end if;
SELECT cast(encrypt(p_value::bytea, 'magickey', 'aes') as character varying) into v_value;
return v_value;
END;

$$;
-- ddl-end --
ALTER FUNCTION appengine.f_encryptvalue(character varying) OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.f_decryptvalue | type: FUNCTION --
-- DROP FUNCTION IF EXISTS appengine.f_decryptvalue(character varying) CASCADE;
CREATE FUNCTION appengine.f_decryptvalue ( p_value character varying)
	RETURNS character varying(1)
	LANGUAGE plpgsql
	VOLATILE 
	CALLED ON NULL INPUT
	SECURITY INVOKER
	COST 100
	AS $$

DECLARE
v_value character varying;
BEGIN
if(p_value is null or p_value='')
then
return p_value;
end if;
SELECT CONVERT_FROM(decrypt(p_value::bytea, 'magickey', 'aes'),'SQL_ASCII') into v_value;
return v_value;
END;

$$;
-- ddl-end --
ALTER FUNCTION appengine.f_decryptvalue(character varying) OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.f_create_user | type: FUNCTION --
-- DROP FUNCTION IF EXISTS appengine.f_create_user(character varying,character varying,character varying,character varying,character varying,character varying) CASCADE;
CREATE FUNCTION appengine.f_create_user ( p_login_id character varying,  p_email_id character varying,  p_password character varying,  p_first_name character varying,  p_last_name character varying,  p_contact character varying)
	RETURNS boolean
	LANGUAGE plpgsql
	VOLATILE 
	CALLED ON NULL INPUT
	SECURITY INVOKER
	COST 100
	AS $$

DECLARE
v_token text;
BEGIN
v_token:=appengine.f_gen_uuid();



INSERT INTO appengine.tb_users
(login_id, email_id, password, created_time, is_active, expires_on, first_name, last_name, contact_no, token_verification)
values
(p_login_id,p_email_id,f_encryptvalue(p_password), current_timestamp,false,null,p_first_name,p_last_name,p_contact,v_token);

return true;

END;

$$;
-- ddl-end --
ALTER FUNCTION appengine.f_create_user(character varying,character varying,character varying,character varying,character varying,character varying) OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.f_gen_uuid | type: FUNCTION --
-- DROP FUNCTION IF EXISTS appengine.f_gen_uuid() CASCADE;
CREATE FUNCTION appengine.f_gen_uuid ()
	RETURNS text
	LANGUAGE plpgsql
	VOLATILE 
	CALLED ON NULL INPUT
	SECURITY INVOKER
	COST 100
	AS $$

BEGIN
return md5(random()::text || clock_timestamp()::text)::uuid::text;
END;

$$;
-- ddl-end --
ALTER FUNCTION appengine.f_gen_uuid() OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.f_user_login | type: FUNCTION --
-- DROP FUNCTION IF EXISTS appengine.f_user_login(character varying,character varying,character varying) CASCADE;
CREATE FUNCTION appengine.f_user_login ( p_login_id character varying,  p_password character varying,  p_ip_address character varying)
	RETURNS character varying(1)
	LANGUAGE plpgsql
	VOLATILE 
	CALLED ON NULL INPUT
	SECURITY INVOKER
	COST 100
	AS $$

DECLARE
v_token text;
v_user_id bigint;
v_active boolean;
v_return text;
BEGIN
v_token:=appengine.f_gen_uuid();

if(p_login_id is null or trim(p_login_id)='' or
   p_password is null or trim(p_password)='' or
   p_ip_address is null or trim(p_ip_address)='') then

  return appengine.f_return_msg(101);

else

select t.user_id,t.is_active into v_user_id,v_active  from appengine.tb_users t
  where (t.login_id =p_login_id or t.email_id=p_login_id)
    and appengine.f_decryptvalue(t.password)=p_password;

if (v_user_id is null) then

  RETURN appengine.f_return_msg(101);

elseif (v_active=false) then
  RETURN appengine.f_return_msg(105);
else
 INSERT INTO appengine.tb_user_sessions
  (user_id, session_token, session_start, session_end, is_active, client_ip) VALUES
  (v_user_id,v_token,current_timestamp,null,true,p_ip_address);

return
  json_build_object('message_id',101,'message_code','SUCCESS','value',
    json_build_object('session_token',v_token,'user_id',v_user_id))::text;

end if;
end if;
END;

$$;
-- ddl-end --
ALTER FUNCTION appengine.f_user_login(character varying,character varying,character varying) OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.f_return_msg | type: FUNCTION --
-- DROP FUNCTION IF EXISTS appengine.f_return_msg(bigint) CASCADE;
CREATE FUNCTION appengine.f_return_msg ( p_msg_id bigint)
	RETURNS text
	LANGUAGE plpgsql
	VOLATILE 
	CALLED ON NULL INPUT
	SECURITY INVOKER
	COST 100
	AS $$

DECLARE
v_msg_id bigint;
v_msg_type character varying(20);
v_message_value text;
v_return text;
BEGIN

if (p_msg_id is null ) then

select json_build_object
  ('message_id',000,'message_code',
  'DB_ERROR','value','Wrong inputs')::text into v_return;

return v_return;
else
  select t.message_id,t.message_code,t.message_value into
  v_msg_id,v_msg_type,v_message_value
  from appengine.tb_return_message t
  where t.message_id=p_msg_id;

  if (v_msg_id is null) then


    select json_build_object
  ('message_id',000,'message_code',
  'DB_ERROR','value','MESSAGE TYPE NOT FOUND')::text into v_return;

return v_return;

else
     select json_build_object
  ('message_id',v_msg_id,'message_code',
  v_msg_type,'message','value',v_message_value)::text into v_return;
return v_return;


end if;
end if ;
END;

$$;
-- ddl-end --
ALTER FUNCTION appengine.f_return_msg(bigint) OWNER TO sense_admin;
-- ddl-end --

-- object: appengine.tb_return_message | type: TABLE --
-- DROP TABLE IF EXISTS appengine.tb_return_message CASCADE;
CREATE TABLE appengine.tb_return_message (
	message_id bigint NOT NULL,
	message_code character varying(20),
	message_value text,
	CONSTRAINT tb_return_message_pkey PRIMARY KEY (message_id)

);
-- ddl-end --
ALTER TABLE appengine.tb_return_message OWNER TO sense_admin;
-- ddl-end --

-- object: fk_object_type | type: CONSTRAINT --
-- ALTER TABLE appengine.tb_objects DROP CONSTRAINT IF EXISTS fk_object_type CASCADE;
ALTER TABLE appengine.tb_objects ADD CONSTRAINT fk_object_type FOREIGN KEY (obj_type)
REFERENCES appengine.tb_obj_types (type_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_tb_users_user_id | type: CONSTRAINT --
-- ALTER TABLE appengine.tb_api_details DROP CONSTRAINT IF EXISTS fk_tb_users_user_id CASCADE;
ALTER TABLE appengine.tb_api_details ADD CONSTRAINT fk_tb_users_user_id FOREIGN KEY (assigned_user)
REFERENCES appengine.tb_users (user_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_user_id | type: CONSTRAINT --
-- ALTER TABLE appengine.tb_user_groups DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE appengine.tb_user_groups ADD CONSTRAINT fk_user_id FOREIGN KEY (owner_id)
REFERENCES appengine.tb_users (user_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_user_id | type: CONSTRAINT --
-- ALTER TABLE appengine.tb_assigned_groups DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE appengine.tb_assigned_groups ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
REFERENCES appengine.tb_users (user_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_group_id | type: CONSTRAINT --
-- ALTER TABLE appengine.tb_assigned_groups DROP CONSTRAINT IF EXISTS fk_group_id CASCADE;
ALTER TABLE appengine.tb_assigned_groups ADD CONSTRAINT fk_group_id FOREIGN KEY (group_id)
REFERENCES appengine.tb_user_groups (group_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_api_id | type: CONSTRAINT --
-- ALTER TABLE appengine.tb_api_access_details DROP CONSTRAINT IF EXISTS fk_api_id CASCADE;
ALTER TABLE appengine.tb_api_access_details ADD CONSTRAINT fk_api_id FOREIGN KEY (api_id)
REFERENCES appengine.tb_api_details (api_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_obj_id | type: CONSTRAINT --
-- ALTER TABLE appengine.tb_api_access_details DROP CONSTRAINT IF EXISTS fk_obj_id CASCADE;
ALTER TABLE appengine.tb_api_access_details ADD CONSTRAINT fk_obj_id FOREIGN KEY (obj_id)
REFERENCES appengine.tb_objects (obj_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_user_id | type: CONSTRAINT --
-- ALTER TABLE appengine.tb_user_sessions DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE appengine.tb_user_sessions ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
REFERENCES appengine.tb_users (user_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


