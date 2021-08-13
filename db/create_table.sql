-- Project Name : noname
-- Date/Time    : 2021/08/13 17:03:04
-- Author       : ssaku
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  << 注意！！ >>
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
  この機能は A5:SQL Mk-2でのみ有効であることに注意してください。
*/

--* RestoreFromTempTable
create table TASK_MANAGEMENT (
  ID character varying(12) not null
  , TITLE character varying(100) not null
  , START_DATE character varying(10) not null
  , LIMIT_DATE character varying(10) not null
  , MEMO character varying(300)
  , TASK_FLG BOOLEAN not null
  , CREATE_DATE timestamp
  , constraint TASK_MANAGEMENT_PKC primary key (ID)
) ;

