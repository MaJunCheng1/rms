create table dept(
	deptID  number(2) primary key,	
	deptName varchar2(20) not null,
	deptLoc varchar2(30) 
);
create table emp(
       empID number(2) primary key,
       empName varchar2(20) not null unique,
       empSex char(2) default '男' check(empSex in ('男','女')),
       empBir date not null,
       empSal number(9,2) check(empSal>=0),
   -- emp表中的字段     引用外键  表名(字段名)
       deptID number(2) references dept(deptID) 
);
--插入销售部
insert into dept values(1,'销售部','纽约');
--插入人事部
insert into dept(deptName,deptLoc,deptID)values('人事部','北京',2);

--插入技术部
insert into dept(deptID,deptName) values (3,'技术部');