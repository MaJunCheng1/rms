create table dept(
	deptID  number(2) primary key,	
	deptName varchar2(20) not null,
	deptLoc varchar2(30) 
);
create table emp(
       empID number(2) primary key,
       empName varchar2(20) not null unique,
       empSex char(2) default '��' check(empSex in ('��','Ů')),
       empBir date not null,
       empSal number(9,2) check(empSal>=0),
   -- emp���е��ֶ�     �������  ����(�ֶ���)
       deptID number(2) references dept(deptID) 
);
--�������۲�
insert into dept values(1,'���۲�','ŦԼ');
--�������²�
insert into dept(deptName,deptLoc,deptID)values('���²�','����',2);

--���뼼����
insert into dept(deptID,deptName) values (3,'������');