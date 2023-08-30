-- Cityテーブルへの初期データ投入
INSERT INTO city (name) VALUES ('東京都');
INSERT INTO city (name) VALUES ('千葉県');

-- Userテーブルへの初期データ投入
-- ここでは、Cityテーブルのidが1と2であると仮定しています。
INSERT INTO app_user (name, email, city_id) VALUES ('John Doe', 'john@example.com', 1);
INSERT INTO app_user (name, email, city_id) VALUES ('Jane Smith', 'jane@example.com', 2);


-- 上記のユーザーに関連するTodoテーブルへの初期データ投入
-- ここでは、Userテーブルのidが1と仮定しています。
INSERT INTO todo (title, description, user_id) VALUES ('Study Spring Boot', 'Learn the basics of Spring Boot.', 1);
INSERT INTO todo (title, description, user_id) VALUES ('Buy groceries', 'Get some vegetables and fruits.', 1);
