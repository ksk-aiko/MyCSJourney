CREATE TABLE items (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE purchases (
    id INT PRIMARY KEY,
    user_id INT,
    item_id INT,
    FOREIGN KEY user_fk(user_id) REFERENCES users(id),
    FOREIGN KEY item_fk(item_id) REFERENCES items(id)
);

INSERT INTO items (id, name) VALUES (1, '川スプレイ'), (2, 'カメラ'), (3, 'エアコン'), (4, 'テレビ'), (5, '掃除ロボット');

INSERT INTO purchases (id, user_id, item_id) VALUES (1, 1, 1), (2, 2, 4), (3, 5, 1), (4, 2, 5), (5, 11, 4), (6, 12, 3), (7, 6, 3), (8, 1, 5), (9, 4, 5), (10, 3, 2), (11, 7, 4), (12, 7, 1), (13, 2, 5), (14, 6, 4);

WITH user_purchases AS (
    SELECT
        users.id,
        COUNT(purchases.id)  purchases_per_user
    FROM
        users
    LEFT JOIN purchases ON users.id = purchases.user_id
    GROUP BY
        users.id
)

SELECT
    MAX(purchases_per_user) 最多購入回数,
    MIN(purchases_per_user) 最少購入回数,
    AVG(purchases_per_user) 平均購入回数
FROM
    user_purchases;