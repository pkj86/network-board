create table t86_board_file
(
	file_no number primary key,
	no number(6),
	ori_file_name varchar2(200) not null,
	real_file_name varchar2(200) not null,
	file_path varchar2(100) not null,
	file_size number not null
);
create sequence s86_board_file_no;
select * from t86_board_file;
delete from t86_board_file where no = 124;

create table t86_board_reply
(
	reply_no number primary key,
	no number(6),
	reply_writer varchar2(30) not null,
	reply_content varchar2(200) not null,
	reply_regdate date default sysdate
);
create sequence s86_board_reply_no;
select * from t86_board_reply;
delete from t86_board_reply where reply_writer = '므마';