INSERT INTO article(title, content) VALUES ('aaaa', '1111');
INSERT INTO article(title, content) VALUES ('bbbb', '2222');
INSERT INTO article(title, content) VALUES ('cccc', '3333');

-- article 더미 데이터
INSERT INTO article(title, content) VALUES ('인생 영화는?', '제곧내');
INSERT INTO article(title, content) VALUES ('소울 푸드는?', '댓 ㄱ');
INSERT INTO article(title, content) VALUES ('취미는?', '댓글');

-- comment dummy data
INSERT INTO COMMENT(article_id, nickname, body) VALUES (4, 'Cho', '너의 이름은');
INSERT INTO COMMENT(article_id, nickname, body) VALUES (4, 'Kim', '메이즈러너');
INSERT INTO COMMENT(article_id, nickname, body) VALUES (4, 'Park', '겨울왕국');
INSERT INTO COMMENT(article_id, nickname, body) VALUES (5, 'Cho', '치킨');
INSERT INTO COMMENT(article_id, nickname, body) VALUES (5, 'Kim', '피자');
INSERT INTO COMMENT(article_id, nickname, body) VALUES (5, 'Park', '햄버거');
INSERT INTO COMMENT(article_id, nickname, body) VALUES (6, 'Cho', '피아노');
INSERT INTO COMMENT(article_id, nickname, body) VALUES (6, 'Kim', '기타');
INSERT INTO COMMENT(article_id, nickname, body) VALUES (6, 'Park', '그림');