
create table zerenety_user(
user_id smallint NOT NULL,
user_firstname varchar(25) not null,
user_lastname varchar(25) not null,
user_adresse varchar(50) not null,
user_city varchar(25) not null,
user_cp varchar(5) not null,
user_mail varchar(50) not null unique,
user_pwd varchar(255) not null,
is_admin boolean not null,
is_participant boolean not null,
is_member boolean not null,
is_manager boolean not null,
PRIMARY KEY (user_id)
);

create table notification(
notification_id smallint NOT NULL,
receiver smallint not null,
is_read boolean not null,
is_creation_demand boolean not null,
primary key (notification_id),
key FK_notification_receiver (receiver)
);
ALTER TABLE notification
	ADD CONSTRAINT FK_notification_receiver FOREIGN KEY (receiver) REFERENCES zerenety_user (user_id);

create table categoryProduct(
category_id smallint NOT NULL,
category_label varchar(50),
category_parent_id smallint not null,
primary key (category_id),
key FK_category_categoryParent(category_parent_id)
);
ALTER TABLE categoryProduct
	ADD CONSTRAINT FK_category_categoryParent FOREIGN KEY (category_parent_id) REFERENCES categoryProduct (category_id);
	
create table product (
product_id smallint NOT NULL ,
product_price float not null,
stock_quantity int not null,
category_id smallint not null ,
primary key (product_id),
key FK_product_category(category_id)
);
ALTER TABLE product
	ADD CONSTRAINT FK_product_category FOREIGN KEY (category_id) REFERENCES categoryProduct (category_id);

create table userOrder (
order_id smallint NOT NULL,
order_date date not null ,
user_id smallint not null,
primary key (order_id),
key FK_order_user(user_id)
);
ALTER TABLE userOrder
	ADD CONSTRAINT FK_order_user FOREIGN KEY (user_id) REFERENCES zerenety_user (user_id);
	
create table orderLine(
product_id smallint not null,
order_id smallint not null,
quantity smallint not null,
primary key (product_id, order_id),
key FK_commandLine_product(product_id),
key FK_commandLine_order(order_id)
);
ALTER TABLE orderLine
	ADD CONSTRAINT FK_commandLine_product FOREIGN KEY (product_id) REFERENCES product(product_id);
ALTER TABLE orderLine
	ADD CONSTRAINT FK_commandLine_order FOREIGN KEY (order_id) REFERENCES userOrder (order_id);

create table room (
room_id smallint NOT NULL,
room_area varchar(50),
room_type varchar(25),
capacity smallint,
primary key(room_id)
);

create table repetition (
repetition_id smallint NOT NULL,
repetition_label varchar(50),
primary key(repetition_id)
);

create table activity (
activity_id smallint NOT NULL ,
activity_name varchar(25) not null,
activity_short_desc varchar(250) not null,
activity_long_desc varchar(500),
manager smallint not null,
primary key (activity_id),
key FK_activity_manager(manager)
);
ALTER TABLE activity
	ADD CONSTRAINT FK_activity_manager FOREIGN KEY (manager) REFERENCES zerenety_user (user_id);

create table event (
event_id smallint NOT NULL,
event_name varchar(50),
event_start_date date not null,
event_end_date date not null,
event_price float,
room_id smallint not null,
participant smallint not null,
activity_id smallint default null,
repetition_id smallint,
primary key (event_id),
key FK_event_room (room_id),
key FK_event_participant(participant),
key FK_event_activity(activity_id),
key FK_event_repetition(repetition_id)
);
ALTER TABLE event
	ADD CONSTRAINT FK_event_room FOREIGN KEY (room_id) REFERENCES room (room_id);
ALTER TABLE event
	ADD CONSTRAINT FK_event_participant FOREIGN KEY (participant) REFERENCES zerenety_user (user_id);
ALTER TABLE event
	ADD CONSTRAINT FK_event_activity FOREIGN KEY (activity_id) REFERENCES activity (activity_id);
ALTER TABLE event
	ADD CONSTRAINT FK_event_repetition FOREIGN KEY (repetition_id) REFERENCES repetition (repetition_id);

create table inscription (
member_id smallint not null,
event_id smallint not null,
inscription_date date not null,
primary key(member_id, event_id),
key FK_inscription_user(member_id),
key FK_inscription_event(event_id)
);
ALTER TABLE inscription
	ADD CONSTRAINT FK_inscription_user FOREIGN KEY (member_id) REFERENCES zerenety_user (user_id);
ALTER TABLE inscription
	ADD CONSTRAINT FK_inscription_event FOREIGN KEY (event_id) REFERENCES event (event_id);

create table formule (
formule_id smallint NOT NULL,
formule_label varchar(50),
primary key(formule_id)
);

create table cotisation (
cotisation_id smallint NOT NULL,
formule_id smallint not null,
member_id smallint not null,
order_date date not null ,
primary key (cotisation_id),
key FK_cotisation_user(member_id),
key FK_cotisation_formule(formule_id)
);
ALTER TABLE cotisation
	ADD CONSTRAINT FK_cotisation_user FOREIGN KEY (member_id) REFERENCES zerenety_user (user_id);
ALTER TABLE cotisation
	ADD CONSTRAINT FK_cotisation_formule FOREIGN KEY (formule_id) REFERENCES formule (formule_id);


create table excludedDate (
excluded_date date not null,
event_id smallint not null,
primary key (excluded_date, event_id),
key FK_cotisation_user(event_id)
);
ALTER TABLE excludedDate
	ADD CONSTRAINT FK_excluded_date_event FOREIGN KEY (event_id) REFERENCES event (event_id);
