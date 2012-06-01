DROP TABLE IF EXISTS `guest`;
CREATE TABLE IF NOT EXISTS `guest` (
  `id` int(11) NOT NULL auto_increment,
  `influencer_id` int(11),
  `category_id` int(11) NOT NULL,
  `schedule_block_id` int(11),
  `age_range_id` int(11),
  `venue_id` int(11),
  `access_code` varchar(16) NOT NULL,
  `first_name` varchar(32),
  `last_name` varchar(32),
  `gender` varchar(1),
  `email` varchar(64),
  `address` varchar(64),
  `city` varchar(32),
  `state` varchar(32),
  `postal_code` varchar(16),
  `chaperoning_count_under_13` int(11),
  `is_invitation_accepted` int(11),
  `is_declined` int(11),
  `is_future_communication` int(11),
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Holds guest';


INSERT INTO guest
(id, category_id, venue_id, access_code, first_name, last_name, email)
VALUES
(1, 1,  1, '10001', 'Paul', 'Vitti', 'leo@dwinq.com'),
(2, 1,  1, '10002', 'Vinni', 'Barborino', 'leo@dwinq.com'),
(3, 1,  1, '10003', 'Ray', 'Barboni', 'leo@dwinq.com'),
(4, 2,  1, '10004', 'Frank', 'Zello', 'leo@dwinq.com'),
(5, 2,  1, '10005', 'Chilli', 'Palmer', 'leo@dwinq.com'),
(6, 2,  1, '10006', 'Nick', 'Santoro', 'leo@dwinq.com'),
(7, 3,  1, '10007', 'Primo', 'Sidone', 'leo@dwinq.com'),
(8, 3,  1, '10008', 'Carlo', 'Mangano', 'leo@dwinq.com'),
(9, 3,  1, '10009', 'Theresa', 'Vitti', 'leo@dwinq.com'),
(10, 4,  1, '10010', 'Salvatore', 'Masiello', 'leo@dwinq.com'),
(11, 4,  1, '10011', 'Frank', 'Marino', 'leo@dwinq.com'),
(12, 4,  1, '10012', 'Vincent', 'Borelli', 'leo@dwinq.com'),
(13, 5,  1, '10013', 'Jennifer', 'Santoro', 'leo@dwinq.com'),
(14, 5,  1, '10014', 'John', 'Nance', 'leo@dwinq.com'),
(15, 5,  1, '10015', 'Vinny', 'Forlano', 'leo@dwinq.com'),
(16, 6,  1, '10016', 'Jack', 'Byrns', 'leo@dwinq.com'),
(17, 6,  1, '10017', 'Jason', 'Bourne', 'leo@dwinq.com'),
(18, 6,  1, '10018', 'Pamela', 'Landy', 'leo@dwinq.com');


