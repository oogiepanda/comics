create database comics;

use comics;

create table credentials (id int not null auto_increment, username varchar(6) not null, 
password varchar(6) not null, affiliation int, primary key (id));

create table comiclist (id int not null auto_increment, comicname varchar(20) not null, 
issue int, writer varchar(20), artist varchar(20), publisher varchar(20), year int, genre varchar(10), 
primary key(id));