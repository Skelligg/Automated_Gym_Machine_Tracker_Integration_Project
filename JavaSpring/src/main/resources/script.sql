insert into public.user_credential (date_created, user_id, email, username, password) values ('2024-12-05', 0, 'easyrep@student.kdg.be', 'easyrep', 'easyrep123');

insert into public.gym_staff (user_id) values (0);

insert into public.gym (gym_id, gym_staff_user_id, opened_on, name, location) values (0, 0, '2024-12-05 13:54:48.000000', 'easyrep', 'antwerpen');

insert into public.arduino ( ip_address) values ( '000');
insert into public.arduino (ip_address) values ( '001');
insert into public.arduino (ip_address) values ( '002');
insert into public.arduino (ip_address) values ( '003');
insert into public.arduino (ip_address) values ( '004');
insert into public.arduino (ip_address) values ( '005');
insert into public.arduino (ip_address) values ( '006');
insert into public.arduino (ip_address) values ( '007');
insert into public.arduino (ip_address) values ( '008');
insert into public.arduino (ip_address) values ( '009');
insert into public.arduino (ip_address) values ( '010');
insert into public.arduino (ip_address) values ( '011');
insert into public.arduino (ip_address) values ( '012');
insert into public.arduino (ip_address) values ( '013');
insert into public.arduino (ip_address) values ( '014');
insert into public.arduino (ip_address) values ( '015');
insert into public.arduino (ip_address) values ( '016');
insert into public.arduino (ip_address) values ( '017');
insert into public.arduino (ip_address) values ( '018');
insert into public.arduino (ip_address) values ('019');


insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (1, 0,  null, 'Back Extension');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (2, 0,  null, 'Bench Press');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (3, 0,  null, 'Butterfly');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (4, 0,  null, 'Cable Bicep Curl');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (5, 0,  null, 'Calf Press');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (6, 0,  null, 'Chest Press');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (7, 0,  null, 'Glute kickback');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (8, 0,  null, 'Hip Abduction');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (9, 0,  null, 'Hip Adduction');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (10, 0, null, 'Iso-lateral Row');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (11, 0, null, 'Lat Pulldown');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (12, 0, null, 'Leg Extension');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (13, 0, null, 'Leg Press');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (14, 0, null, 'Pull Up');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (15, 0, null, 'Rowing Machine');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (16, 0,  null, 'Seated Leg Curl');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (17, 0,  null, 'Seated Row');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (18, 0,  null, 'Shrug');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (19, 0,  null, 'Squat');
insert into public.machine (arduino_id, gym_id, last_time_checked, name) values (20, 0,  null, 'Triceps Dip');

