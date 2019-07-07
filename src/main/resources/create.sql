delete from student;
delete from college;

insert into college(id, name, city) values(1,'MIT','Cambridge');
insert into college(id, name, city) values(2,'Oxford','Oxford');
insert into college(id, name, city) values(3,'Shangda','Shanghai');

insert into student(id,name,address,college_id) values(1, 'William',null,1);
insert into student(id,name,address,college_id) values(2, 'Susan',null,1);
insert into student(id,name,address,college_id) values(3, 'George',null,2);
insert into student(id,name,address,college_id) values(4, 'Olivia',null,2);
insert into student(id,name,address,college_id) values(5, 'Chunhua',null,3);
insert into student(id,name,address,college_id) values(6, 'Zhang',null,3);