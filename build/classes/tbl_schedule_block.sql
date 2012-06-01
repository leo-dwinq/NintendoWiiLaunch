DROP TABLE IF EXISTS `schedule_block`;
CREATE TABLE IF NOT EXISTS `schedule_block` (
  `id` int(11) NOT NULL,
  `venue_id` int(11) NOT NULL,
  `day_of_week` smallint(6) NOT NULL,
  `block_date` date not NULL,
  `start_time` int(11) NOT NULL,
  `end_time` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Holds schedule blocks';



INSERT INTO guest_category
(id, venue_id, day_of_week, block_date, start_time, end_time)
VALUES
(1, 1, 5, '2012-06-28', 1900, 2100),
(1, 1, 6, '2012-06-29', 1000, 1200),
(1, 1, 6, '2012-06-29', 1200, 1400),
(1, 1, 6, '2012-06-29', 1500, 1700),
(1, 1, 6, '2012-06-29', 1700, 1900),
(1, 1, 6, '2012-06-29', 1900, 2100),
(1, 1, 7, '2012-06-30', 1000, 1200),
(1, 1, 7, '2012-06-30', 1200, 1400),
(1, 1, 7, '2012-06-30', 1500, 1700),
(1, 1, 7, '2012-06-30', 1700, 1900);