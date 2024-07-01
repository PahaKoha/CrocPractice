-- Insert data into department table
INSERT INTO department (id, name, higher_department_id) VALUES
                                                            ('11111111-1111-1111-1111-111111111111', 'Head Office', NULL),
                                                            ('22222222-2222-2222-2222-222222222222', 'IT Department', '11111111-1111-1111-1111-111111111111'),
                                                            ('33333333-3333-3333-3333-333333333333', 'Finance Department', '11111111-1111-1111-1111-111111111111'),
                                                            ('44444444-4444-4444-4444-444444444444', 'HR Department', '11111111-1111-1111-1111-111111111111');

-- Insert data into employee table
INSERT INTO employee (id, first_name, last_name, phone, email, department_id, manager_id, position, tg_id) VALUES
                                                                                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'John', 'Doe', '1234567890', 'john.doe@example.com', '11111111-1111-1111-1111-111111111111', NULL, 'CEO', 'johndoe_tg'),
                                                                                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Jane', 'Smith', '0987654321', 'jane.smith@example.com', '22222222-2222-2222-2222-222222222222', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'HEAD_OF_DEPARTMENT', 'janesmith_tg'),
                                                                                                               ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Jim', 'Beam', '1112223334', 'jim.beam@example.com', '22222222-2222-2222-2222-222222222222', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'DEVELOPER', 'jimbeam_tg'),
                                                                                                               ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Emma', 'Brown', '5556667778', 'emma.brown@example.com', '33333333-3333-3333-3333-333333333333', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'HEAD_OF_DEPARTMENT', 'emmabrown_tg'),
                                                                                                               ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'William', 'Johnson', '9998887776', 'william.johnson@example.com', '44444444-4444-4444-4444-444444444444', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'HEAD_OF_DEPARTMENT', 'williamjohnson_tg'),
                                                                                                               ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'Sophia', 'Williams', '4443332221', 'sophia.williams@example.com', '44444444-4444-4444-4444-444444444444', 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'HR_MANAGER', 'sophiawilliams_tg');
