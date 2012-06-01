DROP TABLE IF EXISTS `schedule_block`;
CREATE TABLE IF NOT EXISTS `schedule_block` (
  `id` int(11) NOT NULL auto_increment,
  `venue_id` int(11) NOT NULL,
  `day_of_week` smallint(6) NOT NULL,
  `block_date` date not NULL,
  `start_time` int(11) NOT NULL,
  `end_time` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Holds schedule blocks';



INSERT INTO schedule_block
(id, venue_id, day_of_week, block_date, start_time, end_time)
VALUES
(1, 1, 5, '2012-06-28', 1900, 2100),
(2, 1, 6, '2012-06-29', 1000, 1200),
(3, 1, 6, '2012-06-29', 1200, 1400),
(4, 1, 6, '2012-06-29', 1500, 1700),
(5, 1, 6, '2012-06-29', 1700, 1900),
(6, 1, 6, '2012-06-29', 1900, 2100),
(7, 1, 7, '2012-06-30', 1000, 1200),
(8, 1, 7, '2012-06-30', 1200, 1400),
(9, 1, 7, '2012-06-30', 1500, 1700),
(10, 1, 7, '2012-06-30', 1700, 1900);