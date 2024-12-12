SELECT setval('machine_machine_id_seq', COALESCE((SELECT MAX(machine_id)+1 FROM machine), 1), false);
SELECT setval('exercise_exercise_id_seq', COALESCE((SELECT MAX(exercise_id)+1 FROM exercise), 1), false);
SELECT setval('exercise_set_set_id_seq', COALESCE((SELECT MAX(set_id)+1 FROM exercise_set), 1), false);
