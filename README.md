# IPL-auction-
small project of dedicated  webapplication  on servlet 

## change init-param 
in url - dbname
username of mysql 
password of mysql

## also create table using following query

#teams : id , name, abbrevation,owner,max_age,batting_avg,wickets_taken	
#table
create table teams (team_id int primary key auto_increment,name varchar(100),abbrevation varchar(10),owner varchar(20),max_age int,
batting_avg double,wickets_taken int);


#DML
insert into teams values(default,'CHENNAI SUPER KINGS','CSK','owner1',30,50,10);
insert into teams values(default,'GUJARAT TITANS','GT','owner2',32,55,2);
insert into teams values(default,'KOLKATA KNIGHT RIDERS','KKR','owner3',28,30,40);

#players : id , first_name,last_name, dob,batting_avg,wickets_taken....
#+team_id 

create table players(player_id int primary key auto_increment,first_name varchar(20),last_name varchar(20),dob date,batting_avg double,wickets_taken int,team_id int ,constraint fk_teams foreign key (team_id) references teams(team_id));
