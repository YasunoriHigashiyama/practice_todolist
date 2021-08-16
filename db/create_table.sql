-- Project Name : noname
-- Date/Time    : 2021/08/16 15:47:52
-- Author       : ssaku
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

create table TASK_MANAGEMENT (
  ID bigserial not null
  , TITLE character varying(100) not null
  , START_DATE timestamp not null
  , LIMIT_DATE timestamp not null
  , MEMO character varying(300)
  , TASK_COMPLETE_DATE timestamp
  , CREATE_DATE timestamp not null
  , UPDATE_DATE timestamp
  , constraint TASK_MANAGEMENT_PKC primary key (ID)
);

