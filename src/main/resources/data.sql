INSERT INTO company (name, country, region) VALUES ('원티드랩', '한국', '서울');
INSERT INTO company (name, country, region) VALUES ('네이버', '한국', '판교');
INSERT INTO company (name, country, region) VALUES ('카카오', '한국', '판교');

INSERT INTO users (username) VALUES ('user1');
INSERT INTO users (username) VALUES ('user2');

INSERT INTO post (company_id, position, compensation, description, skills)
VALUES (1, '백엔드 주니어 개발자', 1500000, '원티드랩에서 백엔드 주니어 개발자를 채용합니다.', 'Python');

INSERT INTO post (company_id, position, compensation, description, skills)
VALUES (2, 'Django 백엔드 개발자', 1000000, '네이버에서 Django 백엔드 개발자를 채용합니다.', 'Django');

INSERT INTO post (company_id, position, compensation, description, skills)
VALUES (3, '프론트엔드 개발자', 500000, '카카오에서 프론트엔드 개발자를 채용합니다.', 'JavaScript');