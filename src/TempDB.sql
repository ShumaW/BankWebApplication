create table Account
(
    id            INT,
    client_id     INT,
    name          VARCHAR(50),
    type          VARCHAR(50),
    status        INT,
    balance       INT,
    currency_code VARCHAR(50),
    created_at    DATE,
    updated_at    DATE
);
insert into Account (id, client_id, name, type, status, balance, currency_code, created_at, updated_at)
values (1, 1, 'tz1UezFSFTL4ujkKSETNS6R7v7Pc1jJ4z9tn', 'mastercard', 1, 1, 'CNY', '2023-03-18', '2023-05-24');
insert into Account (id, client_id, name, type, status, balance, currency_code, created_at, updated_at)
values (2, 2, 'tz1gZMEd8j6g1ayHurWvgtepM6iLrFm5X7AU', 'jcb', 2, 2, 'CNY', '2023-01-31', '2023-06-06');
insert into Account (id, client_id, name, type, status, balance, currency_code, created_at, updated_at)
values (3, 3, 'tz1NkvgBDvdqJCE6ngeCTmpYYE4H8mZfUR1b', 'switch', 3, 3, 'RUB', '2023-05-25', '2022-10-22');
insert into Account (id, client_id, name, type, status, balance, currency_code, created_at, updated_at)
values (4, 4, 'tz1dP3wAXR26q7usLdDFf4zvhDnXrDe5i7Hn', 'mastercard', 4, 4, 'CNY', '2023-04-30', '2023-07-07');
insert into Account (id, client_id, name, type, status, balance, currency_code, created_at, updated_at)
values (5, 5, 'tz1fJmhL2bScsZ9i5yAGCG447RnoMrypmoZb', 'jcb', 5, 5, 'TRY', '2023-07-12', '2023-08-30');
insert into Account (id, client_id, name, type, status, balance, currency_code, created_at, updated_at)
values (6, 6, 'tz1WWsXkkDMPNFgQbhMi891op3cDdLzYAjWj', 'mastercard', 6, 6, 'EUR', '2023-01-12', '2023-05-17');
insert into Account (id, client_id, name, type, status, balance, currency_code, created_at, updated_at)
values (7, 7, 'tz1dXXYL28FPgg2dYNsJzrCpHR18p2EwCiDR', 'jcb', 7, 7, 'ARS', '2023-02-03', '2022-09-26');
insert into Account (id, client_id, name, type, status, balance, currency_code, created_at, updated_at)
values (8, 8, 'tz1PjtW4wCqp2gwBqH9HqEnNndWWHGgxF6R6', 'jcb', 8, 8, 'PAB', '2023-07-13', '2022-10-30');
insert into Account (id, client_id, name, type, status, balance, currency_code, created_at, updated_at)
values (9, 9, 'tz1de9s1uMPvz8W44nH2N3jN8AajpF1Bah8L', 'jcb', 9, 9, 'IDR', '2023-03-25', '2023-04-18');
insert into Account (id, client_id, name, type, status, balance, currency_code, created_at, updated_at)
values (10, 10, 'tz1YJKRwZjKR4qKgseUwMEED8BGg2pdKC43h', 'visa', 10, 10, 'KHR', '2023-04-27', '2023-04-03');

create table Agreement
(
    id            INT,
    account_id    INT,
    product_id    INT,
    interest_rate INT,
    status        INT,
    sum           INT,
    created_at    DATE,
    updated_at    DATE
);
insert into Agreement (id, account_id, product_id, interest_rate, status, sum, created_at, updated_at)
values (1, 1, 1, 1, 1, 1, '2023-02-19', '2022-11-27');
insert into Agreement (id, account_id, product_id, interest_rate, status, sum, created_at, updated_at)
values (2, 2, 2, 2, 2, 2, '2023-05-29', '2022-10-28');
insert into Agreement (id, account_id, product_id, interest_rate, status, sum, created_at, updated_at)
values (3, 3, 3, 3, 3, 3, '2023-08-28', '2023-02-27');
insert into Agreement (id, account_id, product_id, interest_rate, status, sum, created_at, updated_at)
values (4, 4, 4, 4, 4, 4, '2023-04-12', '2022-11-25');
insert into Agreement (id, account_id, product_id, interest_rate, status, sum, created_at, updated_at)
values (5, 5, 5, 5, 5, 5, '2022-10-10', '2022-10-21');
insert into Agreement (id, account_id, product_id, interest_rate, status, sum, created_at, updated_at)
values (6, 6, 6, 6, 6, 6, '2022-09-19', '2023-01-28');
insert into Agreement (id, account_id, product_id, interest_rate, status, sum, created_at, updated_at)
values (7, 7, 7, 7, 7, 7, '2023-08-04', '2022-11-02');
insert into Agreement (id, account_id, product_id, interest_rate, status, sum, created_at, updated_at)
values (8, 8, 8, 8, 8, 8, '2022-10-02', '2022-12-05');
insert into Agreement (id, account_id, product_id, interest_rate, status, sum, created_at, updated_at)
values (9, 9, 9, 9, 9, 9, '2023-02-24', '2023-02-01');
insert into Agreement (id, account_id, product_id, interest_rate, status, sum, created_at, updated_at)
values (10, 10, 10, 10, 10, 10, '2023-06-03', '2022-10-23');

create table Client
(
    id         INT,
    manager_id INT,
    status     INT,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    email      VARCHAR(50),
    address    VARCHAR(50),
    phone      VARCHAR(50),
    created_at DATE,
    updated_at DATE
);
insert into Client (id, manager_id, status, first_name, last_name, email, address, phone, created_at, updated_at)
values (1, 1, 1, 'Chandler', 'Spawforth', 'cspawforth0@jimdo.com', '1782 Artisan Pass', '495-203-2229', '2022-12-19',
        '2023-02-05');
insert into Client (id, manager_id, status, first_name, last_name, email, address, phone, created_at, updated_at)
values (2, 2, 2, 'Jimmie', 'Pinnere', 'jpinnere1@globo.com', '1143 Coleman Street', '393-236-6107', '2023-06-26',
        '2023-05-25');
insert into Client (id, manager_id, status, first_name, last_name, email, address, phone, created_at, updated_at)
values (3, 3, 3, 'Cecilla', 'Tapley', 'ctapley2@bigcartel.com', '94856 Village Green Plaza', '617-320-1724',
        '2023-05-15', '2023-03-26');
insert into Client (id, manager_id, status, first_name, last_name, email, address, phone, created_at, updated_at)
values (4, 4, 4, 'Gabey', 'Gallelli', 'ggallelli3@telegraph.co.uk', '27766 Declaration Parkway', '554-650-2593',
        '2023-05-31', '2022-12-11');
insert into Client (id, manager_id, status, first_name, last_name, email, address, phone, created_at, updated_at)
values (5, 5, 5, 'Elwin', 'Huikerby', 'ehuikerby4@pbs.org', '58722 Shasta Trail', '213-963-7709', '2023-05-01',
        '2023-06-23');
insert into Client (id, manager_id, status, first_name, last_name, email, address, phone, created_at, updated_at)
values (6, 6, 6, 'Janos', 'Wandless', 'jwandless5@github.io', '68614 Loftsgordon Avenue', '629-169-2677', '2023-03-07',
        '2023-01-17');
insert into Client (id, manager_id, status, first_name, last_name, email, address, phone, created_at, updated_at)
values (7, 7, 7, 'Hew', 'Foottit', 'hfoottit6@phpbb.com', '278 Sutherland Terrace', '359-900-2486', '2022-10-24',
        '2023-06-01');
insert into Client (id, manager_id, status, first_name, last_name, email, address, phone, created_at, updated_at)
values (8, 8, 8, 'Katharyn', 'Dunlop', 'kdunlop7@ning.com', '08470 Judy Street', '362-315-5286', '2023-04-05',
        '2022-12-08');
insert into Client (id, manager_id, status, first_name, last_name, email, address, phone, created_at, updated_at)
values (9, 9, 9, 'Datha', 'Undrill', 'dundrill8@buzzfeed.com', '9986 Kingsford Parkway', '978-991-8213', '2023-05-04',
        '2023-04-28');
insert into Client (id, manager_id, status, first_name, last_name, email, address, phone, created_at, updated_at)
values (10, 10, 10, 'Goddart', 'Collopy', 'gcollopy9@devhub.com', '1 Ryan Circle', '619-596-0826', '2023-01-24',
        '2022-11-30');

create table Manager
(
    id          INT,
    first_name  VARCHAR(50),
    last_name   VARCHAR(50),
    status      INT,
    description TEXT,
    created_at  DATE
);
insert into Manager (id, first_name, last_name, status, description, created_at)
values (1, 'Atalanta', 'Grewe', 1,
        'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.',
        '2022-09-13');
insert into Manager (id, first_name, last_name, status, description, created_at)
values (2, 'Dominga', 'Pattingson', 2,
        'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus.',
        '2023-04-09');
insert into Manager (id, first_name, last_name, status, description, created_at)
values (3, 'Mitchel', 'Gogie', 3,
        'Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus.',
        '2023-08-24');
insert into Manager (id, first_name, last_name, status, description, created_at)
values (4, 'Sadie', 'Attenborrow', 4,
        'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat.',
        '2023-07-13');
insert into Manager (id, first_name, last_name, status, description, created_at)
values (5, 'Robin', 'Snazel', 5,
        'Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus.',
        '2023-05-27');
insert into Manager (id, first_name, last_name, status, description, created_at)
values (6, 'Neall', 'Pieche', 6, 'Fusce consequat. Nulla nisl.', '2023-03-24');
insert into Manager (id, first_name, last_name, status, description, created_at)
values (7, 'Sayers', 'Durdy', 7,
        'Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis.',
        '2023-01-25');
insert into Manager (id, first_name, last_name, status, description, created_at)
values (8, 'Chas', 'Giamuzzo', 8,
        'Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis.',
        '2022-12-10');
insert into Manager (id, first_name, last_name, status, description, created_at)
values (9, 'Alexandros', 'Winpenny', 9,
        'Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum.',
        '2023-07-25');
insert into Manager (id, first_name, last_name, status, description, created_at)
values (10, 'Maje', 'Swaddle', 10,
        'Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo. Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros.',
        '2023-08-17');

create table Product
(
    id            INT,
    manager_id    INT,
    name          VARCHAR(50),
    status        INT,
    currency_code VARCHAR(50),
    limit         INT,
    created_at    DATE,
    updated_at    DATE
);
insert into Product (id, manager_id, name, status, currency_code, limit, created_at, updated_at)
values (1, 1, 'tz1VhfeJmpkwaZ5YKXEndj7RugRmfK48EMZc', 1, 'CNY', 1, '2022-10-18', '2023-01-26');
insert into Product (id, manager_id, name, status, currency_code, limit, created_at, updated_at)
values (2, 2, 'tz1eHgi24mtRqKPp9qRyeBSG8AZjKgdU52od', 2, 'EUR', 2, '2022-09-20', '2023-07-23');
insert into Product (id, manager_id, name, status, currency_code, limit, created_at, updated_at)
values (3, 3, 'tz1YZX7BDG2sNYFD4sNMbb37j372qp5Dw2hV', 3, 'ISK', 3, '2023-03-11', '2022-09-30');
insert into Product (id, manager_id, name, status, currency_code, limit, created_at, updated_at)
values (4, 4, 'tz1L4bZin1R3o9kAwAzyygLWtTHmAwfNB8ju', 4, 'RSD', 4, '2023-04-23', '2023-05-22');
insert into Product (id, manager_id, name, status, currency_code, limit, created_at, updated_at)
values (5, 5, 'tz1brnXSdM7hGbvVdjzMTLdqhKXNeqN9GgTC', 5, 'SEK', 5, '2023-01-07', '2023-04-22');
insert into Product (id, manager_id, name, status, currency_code, limit, created_at, updated_at)
values (6, 6, 'tz1WGnfa2Fm2adLAW7BSZsvDow37EBoU6Sc9', 6, 'IDR', 6, '2023-05-30', '2023-04-24');
insert into Product (id, manager_id, name, status, currency_code, limit, created_at, updated_at)
values (7, 7, 'tz1NwtpzcVXAmhgSDxjTcgfmd8imhuEjhkaG', 7, 'CNY', 7, '2023-04-25', '2022-09-21');
insert into Product (id, manager_id, name, status, currency_code, limit, created_at, updated_at)
values (8, 8, 'tz1ZJ5j8Bm1B3sPnTtjkqYojurtd2Edg6YZu', 8, 'UYU', 8, '2023-05-16', '2023-05-29');
insert into Product (id, manager_id, name, status, currency_code, limit, created_at, updated_at)
values (9, 9, 'tz1iYL7gUxFLJgTuQn5RB7YWvoxumK1JXw6Z', 9, 'LYD', 9, '2023-06-14', '2023-06-17');
insert into Product (id, manager_id, name, status, currency_code, limit, created_at, updated_at)
values (10, 10, 'tz1RMtvoKpcQGnxNAwwb9EGGxbHAaTCFiMRX', 10, 'BRL', 10, '2023-07-05', '2023-06-19');

create table Transaction (
                             id INT,
                             debit_account_id INT,
                             credit_account_id INT,
                             type INT,
                             amount INT,
                             description VARCHAR(50),
                             created_at DATE
);
insert into Transaction (id, debit_account_id, credit_account_id, type, amount, description, created_at) values (1, 1, 1, 1, 1, 'JVHJUVLUJVHbkbkgbuujBkjb-jbbBKjbkjbjbkjb.kgdktfipiü+io', '2022-10-13');
insert into Transaction (id, debit_account_id, credit_account_id, type, amount, description, created_at) values (2, 2, 2, 2, 2, 'JVHJUVLUJVHbkbkgbuujBkjb-jbbBKjbkjbjbkjb.kgdktfipiü+io', '2022-10-08');
insert into Transaction (id, debit_account_id, credit_account_id, type, amount, description, created_at) values (3, 3, 3, 3, 3, 'JVHJUVLUJVHbkbkgbuujBkjb-jbbBKjbkjbjbkjb.kgdktfipiü+io', '2023-04-05');
insert into Transaction (id, debit_account_id, credit_account_id, type, amount, description, created_at) values (4, 4, 4, 4, 4, 'JVHJUVLUJVHbkbkgbuujBkjb-jbbBKjbkjbjbkjb.kgdktfipiü+io', '2023-06-22');
insert into Transaction (id, debit_account_id, credit_account_id, type, amount, description, created_at) values (5, 5, 5, 5, 5, 'JVHJUVLUJVHbkbkgbuujBkjb-jbbBKjbkjbjbkjb.kgdktfipiü+io', '2023-04-10');
insert into Transaction (id, debit_account_id, credit_account_id, type, amount, description, created_at) values (6, 6, 6, 6, 6, 'JVHJUVLUJVHbkbkgbuujBkjb-jbbBKjbkjbjbkjb.kgdktfipiü+io', '2023-02-26');
insert into Transaction (id, debit_account_id, credit_account_id, type, amount, description, created_at) values (7, 7, 7, 7, 7, 'JVHJUVLUJVHbkbkgbuujBkjb-jbbBKjbkjbjbkjb.kgdktfipiü+io', '2023-02-18');
insert into Transaction (id, debit_account_id, credit_account_id, type, amount, description, created_at) values (8, 8, 8, 8, 8, 'JVHJUVLUJVHbkbkgbuujBkjb-jbbBKjbkjbjbkjb.kgdktfipiü+io', '2023-05-02');
insert into Transaction (id, debit_account_id, credit_account_id, type, amount, description, created_at) values (9, 9, 9, 9, 9, 'JVHJUVLUJVHbkbkgbuujBkjb-jbbBKjbkjbjbkjb.kgdktfipiü+io', '2023-03-03');
insert into Transaction (id, debit_account_id, credit_account_id, type, amount, description, created_at) values (10, 10, 10, 10, 10, 'JVHJUVLUJVHbkbkgbuujBkjb-jbbBKjbkjbjbkjb.kgdktfipiü+io', '2022-10-30');
