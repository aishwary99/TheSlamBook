create table friend_request
(
sent_by int,
sent_to int,
primary key(sent_by,sent_to),
foreign key(sent_by) references user(code),
foreign key(sent_to) references user(code)
)