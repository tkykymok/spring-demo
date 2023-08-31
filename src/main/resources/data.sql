-- Cityテーブルへの初期データ投入
INSERT INTO city (name) VALUES ('東京都');
INSERT INTO city (name) VALUES ('千葉県');
INSERT INTO city (name) VALUES ('神奈川県');
INSERT INTO city (name) VALUES ('大阪府');
INSERT INTO city (name) VALUES ('福岡県');


-- Userテーブルへの初期データ投入
INSERT INTO app_user (name, email, city_id) VALUES ('John Doe', 'john@example.com', 1);
INSERT INTO app_user (name, email, city_id) VALUES ('Jane Smith', 'jane@example.com', 2);
INSERT INTO app_user (name, email, city_id) VALUES ('Hiroshi Tanaka', 'hiroshi@example.com', 3);
INSERT INTO app_user (name, email, city_id) VALUES ('Yuki Sato', 'yuki@example.com', 4);
INSERT INTO app_user (name, email, city_id) VALUES ('Kenta Suzuki', 'kenta@example.com', 5);


-- 上記のユーザーに関連するTodoテーブルへの初期データ投入
INSERT INTO todo (title, description, user_id) VALUES ('Study Spring Boot', 'Learn the basics of Spring Boot.', 1);
INSERT INTO todo (title, description, user_id) VALUES ('Buy groceries', 'Get some vegetables and fruits.', 1);
INSERT INTO todo (title, description, user_id) VALUES ('Visit Kyoto', 'Plan a trip to visit Kyoto temples.', 2);
INSERT INTO todo (title, description, user_id) VALUES ('Learn Vue.js', 'Understand the basics of Vue.js for frontend development.', 3);
INSERT INTO todo (title, description, user_id) VALUES ('Watch a movie', 'Watch the latest action movie in cinema.', 4);
INSERT INTO todo (title, description, user_id) VALUES ('Dinner reservation', 'Book a table at the new Italian restaurant.', 5);