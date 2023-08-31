SELECT t.id   AS todo_id,
       t.title AS todo_title,
       u.id   AS user_id,
       u.name AS user_name,
       c.id   AS city_id,
       c.name AS city_name
FROM Todo t
         INNER JOIN APP_USER u ON t.user_id = u.id
         INNER JOIN City c ON u.city_id = c.id
WHERE u.id = :userIdParam