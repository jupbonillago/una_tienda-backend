-- ---------
-- Role data
-- ---------
INSERT INTO "public"."role" (role_id, name)
    VALUES (1, 'User'), (2, 'Admin');
ALTER SEQUENCE role_role_id_seq RESTART WITH 3;

-- ---------
-- Person data
-- ---------
INSERT INTO "public"."person" (person_id, role_id, name, username, email, password, photo, location, paypal_id)
VALUES
    (1, 1, 'Person 1', 'test1', 'test1@gmail.com', '$2a$10$vQnoULaXgehJeBnn4qgC8.zsVUJr88KcAFeoJp44b9p5qIRjFxhza', 'photo1', 'address1', 'paypal_id1'),
    (2, 1, 'Person 2', 'test2', 'test2@gmail.com', '$2a$10$vQnoULaXgehJeBnn4qgC8.zsVUJr88KcAFeoJp44b9p5qIRjFxhza', 'photo2', 'address2', 'paypal_id2'),
    (3, 2, 'Person 3', 'admin', 'admin@gmail.com', '$2a$10$xejAg0msCKRqPs/nHCtn2eINOZcB7XTAeTIeAZrOcaYgqX6R5Lwsy', 'photo3', 'address3', 'paypal_id3');
ALTER SEQUENCE person_person_id_seq RESTART WITH 4;



-- ---------
-- Category data
-- ---------
INSERT INTO "public"."category" (category_id, name, image)
VALUES
    (1, 'Tecnología', 'tecnologia_img'),
    (2, 'Deportes', 'deportes_img'),
    (3, 'Muebles', 'muebles_img'),
    (4, 'Accesorios', 'accesorios_img'),
    (5, 'Belleza', 'belleza_img');
ALTER SEQUENCE category_category_id_seq RESTART WITH 6;



-- ---------
-- Post data
-- ---------
INSERT INTO "public"."post" (post_id, person_id, category_id, title, product_name, image, 
                            description, total_review, price, stock, state)
VALUES
    (1, 1, 1, 'titulo1', 'producto1', 'https://i.imgur.com/U9vwWso.png', 'description1', 1, 10000, 1,true),
    (2, 2, 2, 'titulo2', 'producto2', 'https://i.imgur.com/BQyzgjk.jpg', 'description2', 2, 25000, 2,true),
    (3, 1, 1, 'titulo3', 'producto3', 'https://i.imgur.com/DOhU4yz.jpg', 'description3', 3, 20000, 3,true),
    (4, 1, 2, 'titulo4', 'producto4', 'https://i.imgur.com/tPXbK56.jpg', 'description4', 3, 15000, 2,true),
    (5, 1, 3, 'titulo5', 'producto5', 'https://i.imgur.com/ThtoviB.jpg', 'description5', 3, 20000, 2,true),
    (6, 1, 4, 'titulo6', 'producto6', 'https://i.imgur.com/jI2MfWj.jpg', 'description6', 2, 10000, 3,true),
    (7, 1, 3, 'titulo7', 'producto7', 'https://i.imgur.com/U9vwWso.png', 'description7', 1, 10000, 1,true),
    (8, 2, 4, 'titulo8', 'producto8', 'https://i.imgur.com/BQyzgjk.jpg', 'description8', 2, 25000, 2,true),
    (9, 1, 3, 'titulo9', 'producto9', 'https://i.imgur.com/DOhU4yz.jpg', 'description9', 3, 20000, 3,true),
    (10, 1, 4, 'titulo10', 'producto10', 'https://i.imgur.com/tPXbK56.jpg', 'description10', 3, 15000, 2,true),
    (11, 1, 5, 'titulo11', 'producto11', 'https://i.imgur.com/ThtoviB.jpg', 'description11', 3, 20000, 2,true),
    (12, 1, 5, 'titulo12', 'producto12', 'https://i.imgur.com/jI2MfWj.jpg', 'description12', 2, 10000, 3,true),
    (13, 1, 5, 'titulo11', 'producto13', 'https://i.imgur.com/DOhU4yz.jpg', 'description13', 3, 20000, 2,true),
    (14, 1, 5, 'titulo12', 'producto14', 'https://i.imgur.com/U9vwWso.png', 'description14', 2, 10000, 3,true);
    ALTER SEQUENCE post_post_id_seq RESTART WITH 15;




-- ---------
-- Transaction data
-- ---------
INSERT INTO "public"."transaction" (transaction_id, person_id, post_id, stock_price, quantity)
VALUES
    (1, 2, 1, 1, 1),
    (2, 1, 3, 1, 1),
    (3, 1, 2, 1, 1),
    (4, 3, 2, 1, 1);
ALTER SEQUENCE transaction_transaction_id_seq RESTART WITH 5;



-- ---------
-- review data
-- ---------
-- Aqui van los inserts de la tabla review, dependiendo de la cantidad ingresada
-- variar el question_question_id_seq (default 1)
ALTER SEQUENCE review_review_id_seq RESTART WITH 1;



-- ---------
-- question data
-- ---------
-- Aqui van los inserts de la tabla question, dependiendo de la cantidad ingresada
-- variar el question_question_id_seq (default 1)
ALTER SEQUENCE question_question_id_seq RESTART WITH 1;




-- ---------
-- answer data
-- ---------
-- Aqui van los inserts de la tabla Answer, dependiendo de la cantidad ingresada
-- variar el answer_answer_id_seq (default 1)
ALTER SEQUENCE answer_answer_id_seq RESTART WITH 1;



-- ---------
-- cartshop data
-- ---------
INSERT INTO "public"."cartshop" (cartshop_id, person_id, total, state)
VALUES
    (1, 1, 2000, 'active');
ALTER SEQUENCE cartshop_cartshop_id_seq RESTART WITH 2;



-- ---------
-- cartshop_item data
-- ---------
INSERT INTO "public"."cartshop_item" (cartshop_item_id, cartshop_id, post_id, quantity)
VALUES
    (1, 1, 1, 1),
    (2, 1, 2, 2),
    (3, 1, 3, 2);
ALTER SEQUENCE cartshop_item_cartshop_item_id_seq RESTART WITH 4;


-- ---------
-- coupon data
-- ---------
-- Aqui van los inserts de la tabla coupon, dependiendo de la cantidad ingresada
-- variar el coupon_coupon_id_seq (default 1)
ALTER SEQUENCE coupon_coupon_id_seq RESTART WITH 1;



