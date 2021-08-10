create table book
(
member_code int,
friend_code int,
data varchar(5000) not null,
primary key(member_code,friend_code),
foreign key(member_code) references user(code),
foreign key(friend_code) references user(code)
)