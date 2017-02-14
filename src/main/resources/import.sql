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

insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values( 1, 1, 'pcspapa', '사무실', '임대', 100, 110, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values( 2, 2, 'pcspapa', '사무실', '임대', 110, 120, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values( 3, 3, 'pcspapa', '사무실', '임대', 120, 130, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values( 4, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values( 5, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values( 6, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values( 7, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values( 8, 2, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values( 9, 3, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(10, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(11, 1, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(12, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(13, 2, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(14, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(15, 3, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(16, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(17, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(18, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(19, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(20, 3, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(21, 4, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(22, 2, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());
insert into proposal(id, contact_id, consultant_username, item_type, item_deal, area_from, area_to, state, created_username, created_date, updated_username, updated_date) values(23, 1, 'pcspapa', '사무실', '임대', 130, 140, '진행', 'pcspapa', CURRENT_TIMESTAMP(), 'pcspapa', CURRENT_TIMESTAMP());

insert into consulting(proposal_id, office_id, state) values(1, 1, '진행');
insert into consulting(proposal_id, office_id, state) values(1, 2, '완결');
insert into consulting(proposal_id, office_id, state) values(1, 3, '진행');

insert into consulting(proposal_id, office_id, state) values(2, 1, '진행');
insert into consulting(proposal_id, office_id, state) values(3, 1, '진행');