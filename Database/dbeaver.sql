CREATE TABLE companies (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url TEXT,
    zip_code VARCHAR(7) NOT NULL,
    established_date DATE NOT NULL,
    memo TEXT
);

-- stuffテーブルの作成
CREATE TABLE stuff(
    id INT PRIMARY KEY,
    company_id INT NOT NULL, CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES companies(id),
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(11),
    salary INT, CONSTRAINT salary_check CHECK (salary >= 0)
);

INSERT INTO employees(id, name, email) VALUES
(1, '本田', 'example1@test.com'),
(2, '鈴木', 'example2@test.com'),
(3, '佐藤', 'exampale3@test.com');