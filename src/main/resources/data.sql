INSERT INTO my_groups (name) VALUES
('Admins'),
('Editors'),
('Viewers');

INSERT INTO my_roles (name) VALUES
('Admin Role'),
('Editor Role'),
('Viewer Role');

INSERT INTO my_users (username) VALUES
('john_doe'),
('jane_smith'),
('alice_jones');

INSERT INTO my_users_groups (user_id, group_id) VALUES
(1, 1),  -- john_doe is in the Admins group
(2, 2),  -- jane_smith is in the Editors group
(3, 3);  -- alice_jones is in the Viewers group

INSERT INTO my_users_roles (user_id, role_id) VALUES
(1, 1),  -- john_doe has Admin Role
(2, 2),  -- jane_smith has Editor Role
(3, 3);  -- alice_jones has Viewer Role
