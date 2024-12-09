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


INSERT INTO public.exercise (exercise_id, machine_id, session_id, exercise_name ) VALUES (1, null, 2, 'Calf Press' );
INSERT INTO public.exercise (exercise_id, machine_id, session_id, exercise_name) VALUES (2, null, 2, 'Cable Bicep Curl');
INSERT INTO public.exercise (exercise_id, machine_id, session_id, exercise_name) VALUES (3, null, 3, 'Calf Press');
INSERT INTO public.exercise (exercise_id, machine_id, session_id, exercise_name) VALUES (4, null, 3, 'Cable Bicep Curl');
INSERT INTO public.exercise (exercise_id, machine_id, session_id, exercise_name) VALUES (5, null, 4, 'Calf Press');
INSERT INTO public.exercise (exercise_id, machine_id, session_id, exercise_name) VALUES (6, null, 4, 'Cable Bicep Curl');
INSERT INTO public.exercise (exercise_id, machine_id, session_id, exercise_name) VALUES (7, null, 5, 'Calf Press');
INSERT INTO public.exercise (exercise_id, machine_id, session_id, exercise_name) VALUES (8, null, 5, 'Cable Bicep Curl');

INSERT INTO public.exercise_set (exercise_id, set_id, weight_count, end_time, start_time, previous_set, repetition_count) VALUES (1, 1, 50, '20:41:25', '20:31:15', '0',5);
INSERT INTO public.exercise_set (exercise_id, set_id, weight_count, end_time, start_time, previous_set, repetition_count) VALUES (1, 2, 80, '20:55:05', '20:41:06', '1',6);
INSERT INTO public.exercise_set (exercise_id, set_id, weight_count, end_time, start_time, previous_set, repetition_count) VALUES (1, 3, 100, '21:12:37', '20:59:29', '2',7);

INSERT INTO public.exercise_set (exercise_id, set_id, weight_count, end_time, start_time, previous_set, repetition_count, set_number) VALUES (2, 4, 100, '21:30:10', '21:20:17', null,1);
INSERT INTO exercise_set (end_time, exercise_id, repetition_count, set_number, start_time, weight_count, previous_set) VALUES (2,null,5,1,null,78,null);

INSERT INTO public.exercise_set (end_time, exercise_id, repetition_count, set_id, set_number, start_time, weight_count, previous_set) VALUES ('10:08:15', 5, 6, 13, 7, '09:58:19', 77, '1');
INSERT INTO public.exercise_set (end_time, exercise_id, repetition_count, set_id, set_number, start_time, weight_count, previous_set) VALUES ('10:29:15', 5, 7, 14, 8, '10:12:19', 80, '1');
INSERT INTO public.exercise_set (end_time, exercise_id, repetition_count, set_id, set_number, start_time, weight_count, previous_set) VALUES ('10:34:15', 5, 4, 15, 4, '10:31:15', 75, '2');

INSERT INTO public.exercise_set (end_time, exercise_id, repetition_count, set_id, set_number, start_time, weight_count, previous_set) VALUES ('10:41:15', 6, 6, 16, 9, '10:31:15', 83, '0');
INSERT INTO public.exercise_set (end_time, exercise_id, repetition_count, set_id, set_number, start_time, weight_count, previous_set) VALUES ('10:51:15', 6, 5, 17, 7, '10:45:15', 72, '1');
INSERT INTO public.exercise_set (end_time, exercise_id, repetition_count, set_id, set_number, start_time, weight_count, previous_set) VALUES ('11:02:15', 6, 7, 18, 8, '10:55:15', 82, '2');




