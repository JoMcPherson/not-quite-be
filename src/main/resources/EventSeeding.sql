DROP TABLE IF EXISTS event_attendees;

DROP TABLE IF EXISTS events;

CREATE TABLE events(
                       id serial PRIMARY KEY,
                       title varchar(255) NOT NULL,
                       street varchar(255),
                       city varchar(255),
                       state varchar(255),
                       zip varchar(255),
                       description text,
                       cognito_user_id varchar(255),
                       date timestamp,
                       image varchar(255),
                       created_at timestamp DEFAULT CURRENT_TIMESTAMP,
                       last_updated timestamp DEFAULT CURRENT_TIMESTAMP,
                       cancelled boolean DEFAULT FALSE,
                       max_attendees int NOT NULL,
                       sport varchar(255) NOT NULL
);

CREATE TABLE event_attendees(
                                id serial PRIMARY KEY,
                                event_id int NOT NULL,
                                cognito_user_id varchar(255) NOT NULL,
                                joined_at timestamp DEFAULT CURRENT_TIMESTAMP,
                                created_at timestamp DEFAULT CURRENT_TIMESTAMP,
                                last_updated timestamp DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
                                CONSTRAINT unique_event_user UNIQUE (event_id, cognito_user_id)
);


INSERT INTO events(title, street, city, state, zip, description, cognito_user_id, date, image, created_at, last_updated, cancelled, max_attendees, sport)
VALUES ('Acrobatics Performance', '123 Gymnasium St', 'Denver', 'CO', '80204', 'Join us for an exciting acrobatics performance.', '912bb5b0-7071-70e2-1603-a3e91f04b712', '2024-09-01 10:00:00', '/images/acrobatics.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 30, 'Acrobatics'),
       ('Archery Competition', '456 Archery Rd', 'Boise', 'ID', '83702', 'Test your archery skills in this competition.', 'd10be5a0-3071-7055-a6ab-98e979a68ff9', '2024-09-02 11:00:00', '/images/archery.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 25, 'Archery'),
       ('Artistic Swimming Showcase', '789 Aquatic Ave', 'Orlando', 'FL', '32801', 'Watch amazing routines in artistic swimming.', '01ebd5e0-1081-7080-dc57-64201cf3db4c', '2024-09-03 12:00:00', '/images/artistic_swimming.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Artistic Swimming'),
       ('Track Meet', '101 Track Blvd', 'Eugene', 'OR', '97401', 'Compete in various track and field events.', '410b65b0-d0b1-7011-9d3d-8db6854218ca', '2024-09-04 09:00:00', '/images/track.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 40, 'Track and Field'),
       ('Badminton Tournament', '123 Sports Hall Rd', 'San Diego', 'CA', '92103', 'Join the badminton tournament and showcase your skills.', '015b3580-0091-701f-2101-0263411f0ff3', '2024-09-05 14:00:00', '/images/badminton.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 15, 'Badminton'),
       ('Baseball/Softball Game', '456 Baseball Field St', 'St. Louis', 'MO', '63103', 'Enjoy a thrilling baseball game.', 'd13b6510-0011-7096-3d19-24099bd9e211', '2024-09-06 16:00:00', '/images/baseball.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 50, 'Baseball'),
       ('Basketball Pick-Up Game', '789 Basketball Ct', 'Chicago', 'IL', '60605', 'Come join a casual pick-up basketball game.', '914b1540-d021-7097-0bc1-9b79d3ae5897', '2024-09-07 18:00:00', '/images/basketball.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 30, 'Basketball'),
       ('Handball Match', '101 Indoor Arena Ln', 'Phoenix', 'AZ', '85003', 'Participate in an exciting handball match.', '912b75b0-e081-7095-bc0a-ffeedbaf0617', '2024-09-08 19:00:00', '/images/handball.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Handball'),
       ('Volleyball Tournament', '123 Beach Volleyball Blvd', 'Miami', 'FL', '33139', 'Join the volleyball tournament at the beach.', '416b75b0-80d1-70d7-51d6-547ad4fd2477', '2024-09-09 10:00:00', '/images/volleyball.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 25, 'Volleyball'),
       ('Biathlon Challenge', '456 Ski Resort Ln', 'Aspen', 'CO', '81611', 'Take part in a biathlon challenge combining skiing and shooting.', '415b7580-1001-7081-466a-cc57cb454339', '2024-09-10 07:00:00', '/images/biathlon.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 15, 'Biathlon'),
       ('Bobsleigh Race', '789 Bobsleigh Track Rd', 'Lake Placid', 'NY', '12946', 'Experience the thrill of bobsleigh racing.', '912bb5b0-7071-70e2-1603-a3e91f04b712', '2024-09-11 13:00:00', '/images/bobsleigh.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 40, 'Bobsleigh'),
       ('Boxing Tournament', '101 Boxing Gym St', 'Las Vegas', 'NV', '89101', 'Watch intense boxing matches in the tournament.', 'd10be5a0-3071-7055-a6ab-98e979a68ff9', '2024-09-12 15:00:00', '/images/boxing.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Boxing'),
       ('Break Dancing Battle', '123 Dance Studio Rd', 'Brooklyn', 'NY', '11201', 'Show off your break dancing skills in this battle.', '01ebd5e0-1081-7080-dc57-64201cf3db4c', '2024-09-13 17:00:00', '/images/break_dancing.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 25, 'Break Dancing'),
       ('Canoe Regatta', '456 Lake Dr', 'Seattle', 'WA', '98101', 'Participate in an exciting canoe regatta.', '410b65b0-d0b1-7011-9d3d-8db6854218ca', '2024-09-14 09:00:00', '/images/canoe.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 10, 'Canoe'),
       ('Cricket Match', '789 Cricket Ground Rd', 'Houston', 'TX', '77002', 'Join the cricket match and enjoy the game.', '015b3580-0091-701f-2101-0263411f0ff3', '2024-09-15 12:00:00', '/images/cricket.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 30, 'Cricket'),
       ('Curling Event', '101 Curling Rink Ln', 'Minneapolis', 'MN', '55401', 'Test your skills in this curling event.', 'd13b6510-0011-7096-3d19-24099bd9e211', '2024-09-16 14:00:00', '/images/curling.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 15, 'Curling'),
       ('Cycling Race', '123 Cycling Track Rd', 'Portland', 'OR', '97201', 'Race in a thrilling cycling competition.', '914b1540-d021-7097-0bc1-9b79d3ae5897', '2024-09-17 08:00:00', '/images/cycling.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 45, 'Cycling'),
       ('Diving Competition', '456 Diving Pool Rd', 'Los Angeles', 'CA', '90015', 'Compete in a diving competition.', '912b75b0-e081-7095-bc0a-ffeedbaf0617', '2024-09-18 10:00:00', '/images/diving.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Diving'),
       ('Equestrian Show', '789 Equestrian Center Dr', 'Lexington', 'KY', '40507', 'Enjoy a beautiful equestrian show.', '416b75b0-80d1-70d7-51d6-547ad4fd2477', '2024-09-19 11:00:00', '/images/equestrian.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 35, 'Equestrian'),
       ('Fencing Tournament', '101 Fencing Hall Rd', 'Philadelphia', 'PA', '19103', 'Participate in a fencing tournament.', '415b7580-1001-7081-466a-cc57cb454339', '2024-09-20 13:00:00', '/images/fencing.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 10, 'Fencing'),
       ('Figure Skating Gala', '123 Ice Rink Dr', 'Detroit', 'MI', '48226', 'Watch stunning figure skating performances.', '912bb5b0-7071-70e2-1603-a3e91f04b712', '2024-09-21 14:00:00', '/images/figure_skating.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 30, 'Figure Skating'),
       ('Flag Football Game', '456 Football Field Rd', 'Dallas', 'TX', '75201', 'Join a fun flag football game.', 'd10be5a0-3071-7055-a6ab-98e979a68ff9', '2024-09-22 15:00:00', '/images/flag_football.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 25, 'Flag Football'),
       ('Soccer Match', '789 Soccer Field Rd', 'Kansas City', 'MO', '64106', 'Participate in an exciting soccer match.', '01ebd5e0-1081-7080-dc57-64201cf3db4c', '2024-09-23 16:00:00', '/images/soccer.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 50, 'Soccer'),
       ('Golf Tournament', '101 Golf Course Dr', 'Augusta', 'GA', '30904', 'Join the golf tournament and showcase your skills.', '410b65b0-d0b1-7011-9d3d-8db6854218ca', '2024-09-24 17:00:00', '/images/golf.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 15, 'Golf'),
       ('Hockey Game', '123 Ice Rink Dr', 'Boston', 'MA', '02215', 'Enjoy a thrilling hockey game.', '015b3580-0091-701f-2101-0263411f0ff3', '2024-09-25 18:00:00', '/images/hockey.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 40, 'Hockey'),
       ('Judo Contest', '456 Judo Arena Dr', 'San Francisco', 'CA', '94103', 'Compete in a judo contest.', 'd13b6510-0011-7096-3d19-24099bd9e211', '2024-09-26 19:00:00', '/images/judo.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 15, 'Judo'),
       ('Karate Championship', '789 Karate Dojo Dr', 'Honolulu', 'HI', '96814', 'Show your karate skills in this championship.', '914b1540-d021-7097-0bc1-9b79d3ae5897', '2024-09-27 20:00:00', '/images/karate.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Karate'),
       ('Lacrosse Game', 'Philadelphia', 'PA', '19104', 'University of Pennsylvania Lacrosse Field', 'Join the lacrosse game and have fun.', '912b75b0-e081-7095-bc0a-ffeedbaf0617', '2024-09-28 21:00:00', '/images/lacrosse.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 25, 'Lacrosse'),
       ('Luge Race', 'Lake Placid', 'NY', '12946', 'Olympic Sports Complex', 'Experience the thrill of luge racing.', '416b75b0-80d1-70d7-51d6-547ad4fd2477', '2024-09-29 22:00:00', '/images/bobsleigh.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 35, 'Luge'),
       ('Pentathlon Event', 'San Diego', 'CA', '92109', 'Mission Bay Sports Complex', 'Participate in an exciting pentathlon event.', '415b7580-1001-7081-466a-cc57cb454339', '2024-09-30 23:00:00', '/images/triathlon.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 50, 'Pentathlon'),
       ('Roller Skating Competition', 'Chicago', 'IL', '60614', 'Lincoln Park Roller Rink', 'Enjoy a roller skating competition.', '912bb5b0-7071-70e2-1603-a3e91f04b712', '2024-10-01 09:00:00', '/images/roller_skate.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 15, 'Roller Skating'),
       ('Rowing Regatta', 'Seattle', 'WA', '98109', 'Greenwood Rowing Club', 'Compete in a thrilling rowing regatta.', 'd10be5a0-3071-7055-a6ab-98e979a68ff9', '2024-10-02 10:00:00', '/images/rowing.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 45, 'Rowing'),
       ('Mountain Biking Event', '101 Mountain Trail Rd', 'Boulder', 'CO', '80302', 'Ride through challenging trails in this mountain biking event.', '410b65b0-d0b1-7011-9d3d-8db6854218ca', '2024-09-28 07:00:00', '/images/mountain_biking.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 30, 'Mountain Biking'),
       ('Rhythmic Gymnastics Showcase', '123 Gymnastics Hall Dr', 'Atlanta', 'GA', '30303', 'Watch amazing performances in rhythmic gymnastics.', '015b3580-0091-701f-2101-0263411f0ff3', '2024-09-29 09:00:00', '/images/rhythmic_gymnastics.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Rhythmic Gymnastics'),
       ('Rugby Match', '456 Rugby Field Rd', 'Salt Lake City', 'UT', '84101', 'Join a rugby match and enjoy the game.', 'd13b6510-0011-7096-3d19-24099bd9e211', '2024-09-30 11:00:00', '/images/rugby.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 35, 'Rugby'),
       ('Sailing Regatta', '789 Marina Dr', 'Newport', 'RI', '02840', 'Sail in a competitive regatta.', '914b1540-d021-7097-0bc1-9b79d3ae5897', '2024-10-01 12:00:00', '/images/sailing.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 10, 'Sailing'),
       ('Shooting Competition', '101 Shooting Range Dr', 'Houston', 'TX', '77007', 'Compete in a shooting competition.', '912b75b0-e081-7095-bc0a-ffeedbaf0617', '2024-10-02 14:00:00', '/images/shooting.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Shooting'),
       ('Skateboarding Jam', '123 Skate Park Rd', 'Venice', 'CA', '90291', 'Show off your skateboarding skills in this jam.', '416b75b0-80d1-70d7-51d6-547ad4fd2477', '2024-10-03 16:00:00', '/images/skateboarding.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 25, 'Skateboarding'),
       ('Sport Climbing Event', '456 Climbing Gym Dr', 'Salt Lake City', 'UT', '84111', 'Test your climbing skills in this sport climbing event.', '415b7580-1001-7081-466a-cc57cb454339', '2024-10-04 18:00:00', '/images/sport_climbing.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Sport Climbing'),
       ('Surfing Competition', '789 Surf Beach Rd', 'Huntington Beach', 'CA', '92648', 'Ride the waves in this exciting surfing competition.', '912bb5b0-7071-70e2-1603-a3e91f04b712', '2024-10-05 07:00:00', '/images/surfing.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 15, 'Surfing'),
       ('Taekwondo Tournament', '101 Martial Arts Dojo Dr', 'Austin', 'TX', '78701', 'Compete in a taekwondo tournament.', 'd10be5a0-3071-7055-a6ab-98e979a68ff9', '2024-10-06 09:00:00', '/images/taekwondo.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Taekwondo'),
       ('Tennis Open', '123 Tennis Court Dr', 'Wimbledon', 'MA', '02457', 'Join the tennis open and compete for the title.', '01ebd5e0-1081-7080-dc57-64201cf3db4c', '2024-10-07 11:00:00', '/images/tennis.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 30, 'Tennis'),
       ('Table Tennis Tournament', '234 Ping Pong St', 'Las Vegas', 'NV', '89109', 'Join our competitive table tennis tournament and showcase your skills.', '410b65b0-d0b1-7011-9d3d-8db6854218ca', '2024-10-12 10:00:00', '/images/table_tennis.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Table Tennis'),
       ('Triathlon', '456 Race Course Rd', 'San Diego', 'CA', '92109', 'Participate in a challenging triathlon.', '015b3580-0091-701f-2101-0263411f0ff3', '2024-10-08 13:00:00', '/images/triathlon.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 50, 'Triathlon'),
       ('Water Polo Match', '789 Water Arena Dr', 'Fort Lauderdale', 'FL', '33301', 'Join a water polo match and have fun in the water.', 'd13b6510-0011-7096-3d19-24099bd9e211', '2024-10-09 15:00:00', '/images/water_polo.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Water Polo'),
('Weightlifting Competition', '101 Fitness Center Dr', 'Columbus', 'OH', '43215', 'Compete in a weightlifting competition.', '912bb5b0-7071-70e2-1603-a3e91f04b712', '2024-10-10 17:00:00', '/images/weightlifting.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 15, 'Weightlifting'),
('Wrestling Match', '123 Wrestling Arena Rd', 'Des Moines', 'IA', '50309', 'Join a wrestling match and showcase your strength.', 'd10be5a0-3071-7055-a6ab-98e979a68ff9', '2024-10-11 19:00:00', '/images/wrestling.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 20, 'Wrestling'),
('Wushu Event', '456 Martial Arts St', 'San Francisco', 'CA', '94102', 'Experience the art of wushu in this event.', '912bb5b0-7071-70e2-1603-a3e91f04b712', '2024-10-22 15:00:00', '/images/wushu.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 10, 'Wushu'),
('Iron Giant Challenge', '123 Strength Training Ave', 'New York', 'NY', '10012', 'Conquer the Iron Giant in this grueling weightlifting competition. Test your limits with a series of challenging lifts, timed sets, and endurance tests. Compete against the best and prove your strength and stamina.', '415b7580-1001-7081-466a-cc57cb454339', '2024-10-25 12:00:00', '/images/iron_giant.jpeg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 10, 'Weightlifting');

INSERT INTO event_attendees(event_id, cognito_user_id)
VALUES (49, '416b75b0-80d1-70d7-51d6-547ad4fd2477'),
       (49, '912bb5b0-7071-70e2-1603-a3e91f04b712'),
       (49, 'd10be5a0-3071-7055-a6ab-98e979a68ff9'),
       (49, '01ebd5e0-1081-7080-dc57-64201cf3db4c'),
       (49, '410b65b0-d0b1-7011-9d3d-8db6854218ca'),
       (49, '015b3580-0091-701f-2101-0263411f0ff3'),
       (49, 'd13b6510-0011-7096-3d19-24099bd9e211'),
       (49, '914b1540-d021-7097-0bc1-9b79d3ae5897'),
       (49, '912b75b0-e081-7095-bc0a-ffeedbaf0617');


