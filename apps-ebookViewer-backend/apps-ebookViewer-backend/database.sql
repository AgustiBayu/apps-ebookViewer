select * from users;
select * from bibliographic;
select * from roles;
select * from user_role_name;

INSERT INTO roles(name) VALUES
('ADMIN'),
('DOSEN'),
('MAHASISWA');

drop database ebookviewer;
drop table roles