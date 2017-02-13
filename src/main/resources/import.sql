insert into building(id, building_name, city, street, zipcode, basement_floor, ground_floor) values(1, '국회도서관', '서울시', '서울특별시 영등포구 의사당대로 1', '12345', 5, 1);
insert into building(id, building_name, city, street, zipcode, basement_floor, ground_floor) values(2, '카카오 스페이스닷원', '서울시', '제주특별자치도 제주시 첨단로 242', '63309', 3, 0);

insert into contact(id, company, mobile, name) values(1, 'google', '010-1234-5678', '구글');
insert into contact(id, company, mobile, name) values(2, 'facebook', '010-2345-6789', '페이스북');
insert into contact(id, company, mobile, name) values(3, 'twitter', '010-3456-7890', '트위터');
insert into contact(id, company, mobile, name) values(4, 'daum', '010-4567-8901', '다음');


insert into building_contact(building_id, contact_id, director) values(1, 1, '건물주');
insert into building_contact(building_id, contact_id, director) values(1, 2, '임대인');
insert into building_contact(building_id, contact_id, director) values(2, 3, '건물주');

insert into office (id, building_id, item_deal, item_type, target_floor_from, target_floor_to, target_floor_note) values(1, 1, '임대', '사무실', 1,  null, '전체');
insert into office (id, building_id, item_deal, item_type, target_floor_from, target_floor_to, target_floor_note) values(2, 1, '임대', '사무실', 2,  null, '전체');
insert into office (id, building_id, item_deal, item_type, target_floor_from, target_floor_to, target_floor_note) values(3, 1, '임대', '사무실', 3,  null, '전체');

insert into proposal(id, contact_id, item_type, item_deal, target_area_from, target_area_to) values(1, 1, '사무실', '임대', 100, 110);
insert into proposal(id, contact_id, item_type, item_deal, target_area_from, target_area_to) values(2, 2, '사무실', '임대', 110, 120);
insert into proposal(id, contact_id, item_type, item_deal, target_area_from, target_area_to) values(3, 3, '사무실', '임대', 120, 130);