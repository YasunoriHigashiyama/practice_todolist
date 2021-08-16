-- Project Name : noname
-- Date/Time    : 2021/08/16 12:51:06
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
  ID bigserial not null
  , TITLE character varying(100) not null
  , START_DATE timestamp not null
  , LIMIT_DATE timestamp not null
  , MEMO character varying(300)
  , TASK_COMPLETE_DATE timestamp
  , CREATE_DATE timestamp not null
  , UPDATE_DATE timestamp
  , constraint TASK_MANAGEMENT_PKC primary key (ID)
) ;

