create table friend
(
member_code int,
friend_code int,
foreign key(member_code) references user(code),
foreign key(friend_code) references user(code),
primary key(member_code,friend_code)
);