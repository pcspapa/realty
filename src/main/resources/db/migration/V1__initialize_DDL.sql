create table realty_user (
    username varchar(255) not null,
    password varchar(255),
    enabled boolean not null,
    primary key (username)
);

create table realty_authority (
    id bigint generated by default as identity,
    username varchar(255) not null,
    role varchar(255),
    primary key (id)
);
alter table realty_authority add constraint FK_REALTY_USER_USERNAME foreign key (username) references realty_user;
alter table realty_authority add constraint UIX_REALTY_AUTHORITY_USERNAME_ROLE unique (username, role);

create table building (
    id bigint generated by default as identity,
    address_building_name varchar(255),
    address_city varchar(255),
    address_street varchar(255),
    address_zipcode varchar(255),
    basement_floor integer,
    ground_floor integer,
    primary key (id)
);
alter table building
    add constraint UIX_BUILDING_ADDRESS unique (address_zipcode, address_street, address_city, address_building_name);


create table contact (
    id bigint generated by default as identity,
    company varchar(255),
    mobile varchar(255),
    name varchar(255),
    primary key (id)
);
alter table contact
    add constraint UIX_CONTACT_NAME_MOBILE unique (name, mobile);


create table building_contact (
    building_id bigint not null,
    contact_id bigint not null,
    director varchar(255),
    primary key (building_id, contact_id)
);
alter table building_contact
    add constraint FK_CONTACT_ID
    foreign key (contact_id)
    references contact;
alter table building_contact
    add constraint FK_BUILDING_ID
    foreign key (building_id)
    references building;


create table office (
    id bigint generated by default as identity,
    building_id bigint not null,
    item_deal varchar(255),
    item_type varchar(255),
    floor_from integer,
    floor_to integer,
    floor_note varchar(255),
    area_py integer,
    area_m2 integer,
    deposit integer,
    monthly_rent integer,
    maintenance_fee integer,
    primary key (id)
);

create table proposal (
    id bigint generated by default as identity,
    contact_id bigint not null,
    item_deal varchar(255),
    item_type varchar(255),
    area_from integer,
    area_to integer,
    state varchar(255),
    created_date timestamp not null,
    updated_date timestamp not null,
    primary key (id)
);

create table consulting (
    proposal_id bigint not null,
    office_id bigint not null,
    state varchar(255),
    primary key (proposal_id, office_id)
);

alter table consulting add constraint FK_OFFICE_ID foreign key (office_id) references office;
alter table consulting add constraint FK_PROPOSAL_ID foreign key (proposal_id) references proposal;



insert into realty_user(username, password, enabled) values('pcspapa', '1111', true);

insert into realty_authority(id, username, role) values(1, 'pcspapa', 'ROLE_CONSULTANT');

insert into building(id, address_building_name, address_city, address_street, address_zipcode, basement_floor, ground_floor) values(1, '국회도서관', '서울시', '서울특별시 영등포구 의사당대로 1', '12345', 5, 1);
insert into building(id, address_building_name, address_city, address_street, address_zipcode, basement_floor, ground_floor) values(2, '카카오 스페이스닷원', '서울시', '제주특별자치도 제주시 첨단로 242', '63309', 3, 0);

insert into contact(id, company, mobile, name) values(1, 'google', '010-1234-5678', '구글');
insert into contact(id, company, mobile, name) values(2, 'facebook', '010-2345-6789', '페이스북');
insert into contact(id, company, mobile, name) values(3, 'twitter', '010-3456-7890', '트위터');
insert into contact(id, company, mobile, name) values(4, 'daum', '010-4567-8901', '다음');


insert into building_contact(building_id, contact_id, director) values(1, 1, '건물주');
insert into building_contact(building_id, contact_id, director) values(1, 2, '임대인');
insert into building_contact(building_id, contact_id, director) values(2, 3, '건물주');

insert into office (id, building_id, item_deal, item_type, floor_from, floor_to, floor_note, deposit, maintenance_fee, monthly_rent) values(1, 1, '임대', '사무실', 1,  null, '전체', 3000, 300, 30);
insert into office (id, building_id, item_deal, item_type, floor_from, floor_to, floor_note, deposit, maintenance_fee, monthly_rent) values(2, 1, '임대', '사무실', 2,  null, '전체', 4000, 400, 40);
insert into office (id, building_id, item_deal, item_type, floor_from, floor_to, floor_note, deposit, maintenance_fee, monthly_rent) values(3, 1, '임대', '사무실', 3,  null, '전체', 5000, 500, 50);
insert into office (id, building_id, item_deal, item_type, floor_from, floor_to, floor_note, deposit, maintenance_fee, monthly_rent) values(4, 1, '임대', '사무실', 4,  null, '전체', 6000, 600, 60);

insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values( 1, 1, '사무실', '임대', 100, 110, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values( 2, 2, '사무실', '임대', 110, 120, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values( 3, 3, '사무실', '임대', 120, 130, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values( 4, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values( 5, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values( 6, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values( 7, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values( 8, 2, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values( 9, 3, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(10, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(11, 1, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(12, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(13, 2, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(14, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(15, 3, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(16, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(17, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(18, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(19, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(20, 3, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(21, 4, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(22, 2, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, item_type, item_deal, area_from, area_to, state, created_date, updated_date) values(23, 1, '사무실', '임대', 130, 140, '진행', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into consulting(proposal_id, office_id, state) values(1, 1, '진행');
insert into consulting(proposal_id, office_id, state) values(1, 2, '완결');
insert into consulting(proposal_id, office_id, state) values(1, 3, '진행');

insert into consulting(proposal_id, office_id, state) values(2, 1, '진행');
insert into consulting(proposal_id, office_id, state) values(3, 1, '진행');