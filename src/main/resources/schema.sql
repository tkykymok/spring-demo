CREATE TABLE city (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE app_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    city_id BIGINT,
    FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE todo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES app_user(id)
);
