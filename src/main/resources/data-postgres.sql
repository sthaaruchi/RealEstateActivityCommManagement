INSERT INTO re_user(active, email, firstname, lastname, password, phone, role, username)
	VALUES (true,'man@gmail.com', 'John', 'Doe', '$2y$12$ZnYPgceWGUEGyXxhQjGmbOX6Xmu6t5rGYx0rtws1vg5Zjlkm5nRza', '09-111111111', 'ROLE_MANAGER', 'Man');
INSERT INTO re_user(active, email, firstname, lastname, password, phone, role, username)
	VALUES (true,'sec1@gmail.com', 'Harry', 'Doe', '$2y$12$ZnYPgceWGUEGyXxhQjGmbOX6Xmu6t5rGYx0rtws1vg5Zjlkm5nRza', '09-111111111', 'ROLE_SECURITY', 'Sec1');
INSERT INTO re_user(active, email, firstname, lastname, password, phone, role, username)
	VALUES (true,'sec2@gmail.com', 'Harry', 'Doe', '$2y$12$ZnYPgceWGUEGyXxhQjGmbOX6Xmu6t5rGYx0rtws1vg5Zjlkm5nRza', '09-111111111', 'ROLE_SECURITY', 'Sec2');
INSERT INTO re_user(active, email, firstname, lastname, password, phone, role, username)
	VALUES (true,'tec1@gmail.com', 'Harry', 'Doe', '$2y$12$ZnYPgceWGUEGyXxhQjGmbOX6Xmu6t5rGYx0rtws1vg5Zjlkm5nRza', '09-111111111', 'ROLE_TECHNICIAN', 'Tec');
INSERT INTO re_user(active, email, firstname, lastname, password, phone, role, username)
	VALUES (true,'jen@gmail.com', 'Jenny', 'May', '$2y$12$ZnYPgceWGUEGyXxhQjGmbOX6Xmu6t5rGYx0rtws1vg5Zjlkm5nRza', '09-111111111', 'ROLE_RESIDENT', 'Res1');
INSERT INTO re_user(active, email, firstname, lastname, password, phone, role, username)
	VALUES (true,'jur@gmail.com', 'Jury', 'Doe', '$2y$12$ZnYPgceWGUEGyXxhQjGmbOX6Xmu6t5rGYx0rtws1vg5Zjlkm5nRza', '09-111111111', 'ROLE_RESIDENT', 'Res2');
INSERT INTO re_user(active, email, firstname, lastname, password, phone, role, username)
	VALUES (true,'man2@gmail.com', 'John', 'Doe', '$2y$12$ZnYPgceWGUEGyXxhQjGmbOX6Xmu6t5rGYx0rtws1vg5Zjlkm5nRza', '09-111111111', 'ROLE_MANAGER', 'Man2');

INSERT INTO public.re_building(building_id, building_name)
	VALUES ( 1, 'Student Village I');
INSERT INTO public.re_building(building_id, building_name)
	VALUES ( 2, 'Student Village II');
INSERT INTO public.re_building(building_id, building_name)
	VALUES ( 3, 'Student Village III');

INSERT INTO public.re_resident(resident_id, floor_no, room_no, live_in_building_id)
	VALUES (5, 'Third Floor', '201', 1);
INSERT INTO public.re_resident(resident_id, floor_no, room_no, live_in_building_id)
	VALUES (6, 'Third Floor', '31', 2);

INSERT INTO public.re_juristic(juristic_id, emergency_contact_no, position)
	VALUES (1, '09-1212121212', 'Manager');
INSERT INTO public.re_juristic(juristic_id, emergency_contact_no, position)
	VALUES (2, '09-1212121213', 'Assistant-Technician');
INSERT INTO public.re_juristic(juristic_id, emergency_contact_no, position)
	VALUES (3, '09-1212121213', 'Assistant-Technician');
INSERT INTO public.re_juristic(juristic_id, emergency_contact_no, position)
	VALUES (4, '09-1212121211', 'Security');
INSERT INTO public.re_juristic(juristic_id, emergency_contact_no, position)
	VALUES (7, '09-1212121212', 'Manager');

INSERT INTO public.responsible_for(juristic_id, building_id)
	VALUES (1, 1);
INSERT INTO public.responsible_for(juristic_id, building_id)
	VALUES (1, 2);
INSERT INTO public.responsible_for(juristic_id, building_id)
	VALUES (2, 1);
INSERT INTO public.responsible_for(juristic_id, building_id)
	VALUES (2, 2);
INSERT INTO public.responsible_for(juristic_id, building_id)
	VALUES (3, 3);
INSERT INTO public.responsible_for(juristic_id, building_id)
	VALUES (4, 1);
INSERT INTO public.responsible_for(juristic_id, building_id)
	VALUES (4, 3);
INSERT INTO public.responsible_for(juristic_id, building_id)
	VALUES (7, 3);


