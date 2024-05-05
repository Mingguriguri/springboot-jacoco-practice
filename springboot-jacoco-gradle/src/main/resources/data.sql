-- data.sql 파일 내용
-- 테이블 생성
CREATE TABLE users (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255),
      email VARCHAR(255)
);


INSERT INTO users (name, email) VALUES ('Alice Johnson', 'alice.johnson@example.com');
INSERT INTO users (name, email) VALUES ('Bob Smith', 'bob.smith@example.com');
INSERT INTO users (name, email) VALUES ('Carol Williams', 'carol.williams@example.com');
INSERT INTO users (name, email) VALUES ('David Brown', 'david.brown@example.com');
