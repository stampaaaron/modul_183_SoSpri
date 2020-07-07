INSERT INTO channel (id, name)
VALUES
(1, 'Webframeworks'),
(2, 'Technotrends'),
(3, 'Fun');

DELETE
FROM message;
INSERT INTO message (id, content, author, origin, channel_id)
VALUES (1,
        'Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.',
        'Albert Einstein', '2020-03-10 10:30:40', 1),
       (2,
        'Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.',
        'Albert Einstein', '2020-03-10 10:31:22', 1),
       (3,
        'Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.',
        'Mac Afee', '2020-03-10 10:38:11', 1),
       (4,
        'Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.',
        'Tony Stark', '2020-03-10 10:42:57', 1);

/* encrypted password for id 1..4 is 1234* */
DELETE
FROM user;
INSERT INTO user (id, prename, lastname, password, username, role)
VALUES (1, 'Albert', 'Einstein', '$2a$10$fdivNJC5meS7mOM.QVKqZeVDHtn46arLIIgik6xbUg2w3eACiazCC', 'albert.einstein', 'ADMIN'),
       (2, 'Mac', 'Afee', '$2a$10$Bf6.b0FT30EibG1s.BZmL.14vSyWrQzFNI6q4UXkNmvmkUd3trcfO', 'mac.afee', 'USER'),
       (3, 'Tony', 'Stark', '$2a$10$Y5WfhqTlN91E0j88FXVOduxHiwCnSSfgGCrcivzPhzhM889f02.u6', 'toni.stark', 'USER'),
       (4, 'Wilhelm', 'Tell', '$2a$10$4VrJkrAmhq/sPVnBgZ6Gm.S7ctGTHmo.f9QS3jQ2.c8cf1uH4CSfK', 'wilhelm.tell', 'USER'),
       (5, 'test', 'test', '$2a$10$BY7i3aGW0EKpOiRZCjObyuMbQXUOvY3zzHsCOP0NtPWcauU2BgIee', 'test.test', 'ADMIN');
