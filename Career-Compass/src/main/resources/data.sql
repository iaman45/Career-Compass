INSERT INTO college_events
(name, type, description, website, application_start_date, application_end_date, exam_date)
VALUES
('Lovely Professional University', 'Private', 'LPU offers UG & PG courses with modern facilities.', 'https://lpu.in', '2025-06-01', '2025-07-31', '2025-08-15')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO college_events
(name, type, description, website, application_start_date, application_end_date, exam_date)
VALUES
('Galgotias University', 'Private', 'Galgotias University offers engineering, management & law courses.', 'https://galgotiasuniversity.edu.in', '2025-06-05', '2025-07-20', '2025-08-10')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO college_events
(name, type, description, website, application_start_date, application_end_date, exam_date)
VALUES
('GL Bajaj College', 'Private', 'GL Bajaj College provides technical & management programs.', 'https://glbajaj.edu.in', '2025-06-10', '2025-07-25', '2025-08-12')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO college_events
(name, type, description, website, application_start_date, application_end_date, exam_date)
VALUES
('Galgotias College', 'Private', 'Galgotias College offers diverse undergraduate programs.', 'https://galgotiascollege.edu.in', '2025-06-12', '2025-07-28', '2025-08-14')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO college_events
(name, type, description, website, application_start_date, application_end_date, exam_date)
VALUES
('Shiv Nadar University', 'Private', 'Shiv Nadar University offers liberal arts, engineering, and science courses.', 'https://snu.edu.in', '2025-06-15', '2025-07-30', '2025-08-18')
ON DUPLICATE KEY UPDATE name=name;
