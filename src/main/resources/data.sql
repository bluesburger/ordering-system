insert into dish (created_time, name, price, updated_time) values (now(), 'Falafel', 10.00, now());
insert into dish (created_time, name, price, updated_time) values (now(), 'Fish', 10.00, now());
insert into dish (created_time, name, price, updated_time) values (now(), 'Gazpacho', 10.00, now());
insert into dish (created_time, name, price, updated_time) values (now(), 'Ramen', 10.00, now());
insert into dish (created_time, name, price, updated_time) values (now(), 'Rigatoni', 10.00, now());
insert into dish (created_time, name, price, updated_time) values (now(), 'Hamburguer', 10.00, now());

insert into drink (created_time, name, price, updated_time) values (now(), 'Margarita', 20.0, now());
insert into drink (created_time, name, price, updated_time) values (now(), 'Cosmopolitan', 20.0, now());
insert into drink (created_time, name, price, updated_time) values (now(), 'Daiquiri', 20.0, now());
insert into drink (created_time, name, price, updated_time) values (now(), 'Gimlet', 20.0, now());
insert into drink (created_time, name, price, updated_time) values (now(), 'Manhattan', 20.0, now());
insert into drink (created_time, name, price, updated_time) values (now(), 'Negroni', 20.0, now());
insert into drink (created_time, name, price, updated_time) values (now(), 'Old Fashioned', 20.0, now());
insert into drink (created_time, name, price, updated_time) values (now(), 'Expresso Martini', 20.0, now());
insert into drink (created_time, name, price, updated_time) values (now(), 'Passionfruit Martini', 20.0, now());
insert into drink (created_time, name, price, updated_time) values (now(), 'Mimosa', 20.0, now());

insert into dessert (created_time, name, price, updated_time) values (now(), 'Cake', 20.0, now());
insert into dessert (created_time, name, price, updated_time) values (now(), 'Cookie', 20.0, now());
insert into dessert (created_time, name, price, updated_time) values (now(), 'Biscuit', 20.0, now());
insert into dessert (created_time, name, price, updated_time) values (now(), 'Gelatin', 20.0, now());
insert into dessert (created_time, name, price, updated_time) values (now(), 'Ice Cream', 20.0, now());
insert into dessert (created_time, name, price, updated_time) values (now(), 'Pie', 20.0, now());
insert into dessert (created_time, name, price, updated_time) values (now(), 'Pudding', 20.0, now());
insert into dessert (created_time, name, price, updated_time) values (now(), 'Candy', 20.0, now());

-- TODO: REMOVER
insert into orders (cpf, created_time, status, updated_time) values ('787.641.759-01', now(), 'PEDIDO_REALIZADO', now());
insert into orders_desserts (orders_id, desserts_id) values (1, 1);
insert into orders_dishes (orders_id, dishes_id) values (1, 1);
insert into orders_drinks (orders_id, drinks_id) values (1, 1);

insert into orders (cpf, created_time, status, updated_time) values (null, now(), 'PEDIDO_REALIZADO', now());
insert into orders_desserts (orders_id, desserts_id) values (2, 1);
insert into orders_dishes (orders_id, dishes_id) values (2, 1);
insert into orders_drinks (orders_id, drinks_id) values (2, 1);

insert into orders (cpf, created_time, status, updated_time) values ('160.964.076-41', now(), 'PEDIDO_REALIZADO', now());
insert into orders_desserts (orders_id, desserts_id) values (3, 1);
insert into orders_dishes (orders_id, dishes_id) values (3, 1);
insert into orders_drinks (orders_id, drinks_id) values (3, 1);

insert into orders (cpf, created_time, status, updated_time) values ('659.296.788-98', now(), 'PEDIDO_REALIZADO', now());
insert into orders_desserts (orders_id, desserts_id) values (4, 1);
insert into orders_dishes (orders_id, dishes_id) values (4, 1);
insert into orders_drinks (orders_id, drinks_id) values (4, 1);