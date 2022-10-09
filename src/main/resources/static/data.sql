INSERT INTO roles VALUES (1, 'ROLE_USER'),
                         (2, 'ROLE_ADMIN');

INSERT INTO users VALUES (1, 10, 'user@mail.ru', 'User', '$2a$12$25IAoGtO2bVWHGplE8giK.uxG0UzQrTUfooIKwIBcLfw9K0UMSMF.', 'Userov'),
                         (2, 20, 'admin@mail.ru', 'Admin', '$2a$12$OvVCnRS0nlhQDJ0PLyDFZ.JZb9XelkpD6Kn3LHel0T2c.CaR3fpYq', 'Adminov');

INSERT INTO users_roles VALUES (1, 1),
                               (2, 1),
                               (2, 2);