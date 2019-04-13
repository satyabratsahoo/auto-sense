--Functions for Autosense
--1. encrypt string

CREATE OR REPLACE FUNCTION appengine.f_encryptvalue(p_value character varying)  RETURNS character varying
LANGUAGE plpgsql
AS $BODY$
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
$BODY$;

--END

--2. decrypt string
CREATE OR REPLACE FUNCTION appengine.f_decryptvalue(p_value character varying)  RETURNS character varying
LANGUAGE plpgsql
AS $BODY$
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
$BODY$;

--END

-- 3. Generate UUID()::text

CREATE OR REPLACE FUNCTION appengine.f_gen_uuid ()
	RETURNS text
	LANGUAGE plpgsql
	AS $BODY$
BEGIN
return md5(random()::text || clock_timestamp()::text)::uuid::text;
END;
$BODY$;
--END


--4. Return Message JSON


create or replace function appengine.f_return_msg(p_msg_id bigint) returns text
language plpgsql
as $BODY$
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
  v_msg_type,'value',v_message_value)::text into v_return;
return v_return;


end if;
end if ;
END;
$BODY$;
--END

--5. user_login module
create or replace function f_user_login(p_login_id character varying, p_password character varying, p_ip_address character varying) returns character varying
	language plpgsql
as $$
DECLARE
v_token text;
v_user_id bigint;
v_active boolean;
v_login_id text;
v_email text;
v_first_name text;
v_last_name text;
v_role_id bigint;
v_user_role text;
BEGIN
v_token:=appengine.f_gen_uuid();

if(p_login_id is null or trim(p_login_id)='' or
   p_password is null or trim(p_password)='' or
   p_ip_address is null or trim(p_ip_address)='') then

  return appengine.f_return_msg(101);

else

select t.user_id,t.is_active,t.email_id, t.login_id,t.first_name,t.last_name
       into v_user_id,v_active ,v_email,v_login_id,v_first_name,v_last_name

from appengine.tb_users t
  where (t.login_id =p_login_id or t.email_id=p_login_id)
    and appengine.f_decryptvalue(t.password)=p_password;


    select ug.group_id,ug.group_name into v_role_id,v_user_role
           from  appengine.tb_user_groups ug,appengine.tb_assigned_groups ag
  where ag.user_id=v_user_id and
        ag.group_id=ug.group_id and
        ug.is_active=true;

if (v_user_id is null) then

  RETURN appengine.f_return_msg(101);

elseif (v_active=false) then
  RETURN appengine.f_return_msg(105);
else
 INSERT INTO appengine.tb_user_sessions
  (user_id, session_token, session_start, session_end, is_active, client_ip) VALUES
  (v_user_id,v_token,current_timestamp,null,true,p_ip_address);

return
  json_build_object('message_id',100,'message_code','SUCCESS','value',
    json_build_object('session_token',v_token,'user_id',v_user_id,'role_id',v_role_id,'user_role',v_user_role,'username',v_login_id,
      'email_id',v_email,'first_name',v_first_name,'last_name',v_last_name))::text;

end if;
end if;
END;
$$;

--END

-- 6. session token retrieve

CREATE OR REPLACE FUNCTION appengine.f_session_retrieve
(p_session_token character varying)
RETURNS character varying
LANGUAGE plpgsql
AS $BODY$
DECLARE
v_session_token text;
v_token text;
v_user_id bigint;
v_active boolean;
v_login_id text;
v_email text;
v_first_name text;
v_last_name text;
v_role_id bigint;
v_user_role text;
BEGIN
v_session_token:=p_session_token;

if(p_session_token is null or trim(p_session_token)='') then

  return appengine.f_return_msg(106);

else

select us.session_token,t.user_id,t.is_active,t.email_id, t.login_id,t.first_name,t.last_name
       into v_token,v_user_id,v_active ,v_email,v_login_id,v_first_name,v_last_name

from appengine.tb_users t,appengine.tb_user_sessions us
  where us.session_token = v_session_token
  and us.user_id=t.user_id
  and us.is_active = true;

select ug.group_id,ug.group_name into v_role_id,v_user_role
           from  appengine.tb_user_groups ug,appengine.tb_assigned_groups ag
  where ag.user_id=v_user_id and
        ag.group_id=ug.group_id and
        ug.is_active=true;


if (v_token is null) then

  RETURN appengine.f_return_msg(107);

else

return
  json_build_object('message_id',100,'message_code','SUCCESS','value',
    json_build_object('session_token',v_token,'user_id',v_user_id,'role_id',v_role_id,'user_role',v_user_role,'username',v_login_id,
      'email_id',v_email,'first_name',v_first_name,'last_name',v_last_name))::text;

end if;
end if;
END;
$BODY$;

--END

--7. create user

--Encrypt Value Scripts for passwords
CREATE OR REPLACE FUNCTION appengine.f_create_user
(p_login_id character varying,
p_email_id character varying,
p_password character varying,
p_first_name character varying,
p_last_name character varying,
p_contact character varying)
RETURNS boolean
LANGUAGE plpgsql
AS $BODY$
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
$BODY$;

--END