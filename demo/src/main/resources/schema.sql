create table if not exists user(
id int primary key auto_increment,
name varchar(16) not null,
phone_number varchar(16) not null unique,
pin_code varchar(6) not null ,
user_type varchar(16) not null,
covid_result varchar(16),
travel_history varchar(5),
contact_with_user varchar(5)
);