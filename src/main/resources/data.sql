--DATA FOR USER TABLE
insert into user (ID, NAME, birth_Date) values (1, 'John Doe', SYSDATE());
insert into user (ID, NAME, birth_Date) values (2, 'Karl Fiorenzo', SYSDATE());
insert into user (ID, NAME, birth_Date) values (3, 'Sansa Stark', SYSDATE());

--DATA FOR AUTHOR TABLE
insert into author (ID, FIRST_NAME, LAST_NAME, BIRTH_DATE, DEATH_DATE, LIKES) values (1, 'George', 'Orwell', SYSDATE(), SYSDATE(), 0);
insert into author (ID, FIRST_NAME, LAST_NAME, BIRTH_DATE, DEATH_DATE, LIKES) values (2, 'Jane', 'Austen', SYSDATE(), SYSDATE(), 0);
insert into author (ID, FIRST_NAME, LAST_NAME, BIRTH_DATE, DEATH_DATE, LIKES) values (3, 'Scott', 'Fitzgerald', SYSDATE(), SYSDATE(), 0);

--DATA FOR BOOK TABLE
insert into book (ID, TITLE, AUTHOR_Id, LIKES, COMMENTS, FIRST_PUBLICATION_DATE) values (1, '1948', 1, 30, 20, null);
insert into book (ID, TITLE, AUTHOR_Id, LIKES, COMMENTS, FIRST_PUBLICATION_DATE) values (2, 'Pride and Prejudice', 2, 3, 10, null);
insert into book (ID, TITLE, AUTHOR_Id, LIKES, COMMENTS, FIRST_PUBLICATION_DATE) values (3, 'The Great Gatsby', 3, 3, 10, TO_DATE('01/10/1925', 'MM/DD/YYYY'));