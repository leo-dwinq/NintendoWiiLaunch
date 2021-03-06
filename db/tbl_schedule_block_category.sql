DROP TABLE IF EXISTS `schedule_block_category`;
CREATE TABLE IF NOT EXISTS `schedule_block_category` (
  `id` int(11) NOT NULL,
  `schedule_block_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `is_access` int(11) NOT NULL,
  `max_reservations` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Holds guest categories';




INSERT INTO schedule_block_category
(id, schedule_block_id, category_id, is_access, max_reservations)
VALUES
(1, 1, 1, 0, 0),
(2, 1, 2, 0, 0),
(3, 1, 3, 0, 0),
(4, 1, 4, 0, 0),
(5, 1, 5, 1, 100),
(6, 1, 6, 0, 0),
(7, 2, 1, 1, 8),
(8, 2, 2, 0, 0),
(9, 2, 3, 1, 18),
(10, 2, 4, 0, 0),
(11, 2, 5, 0, 0),
(12, 2, 6, 0, 0),
(13, 3, 1, 1, 8),
(14, 3, 2, 0, 0),
(15, 3, 3, 1, 18),
(16, 3, 4, 0, 0),
(17, 3, 5, 0, 0),
(18, 3, 6, 0, 0),
(19, 4, 1, 0, 0),
(20, 4, 2, 1, 10),
(21, 4, 3, 0, 0),
(22, 4, 4, 1, 23),
(23, 4, 5, 0, 0),
(24, 4, 6, 0, 0),
(25, 5, 1, 0, 0),
(26, 5, 2, 1, 5),
(27, 5, 3, 0, 0),
(28, 5, 4, 1, 12),
(29, 5, 5, 1, 50),
(30, 5, 6, 0, 0),
(31, 6, 1, 0, 0),
(32, 6, 2, 0, 0),
(33, 6, 3, 0, 0),
(34, 6, 4, 0, 0),
(35, 6, 5, 0, 0),
(36, 6, 6, 1, 100),
(37, 7, 1, 1, 8),
(38, 7, 2, 0, 0),
(39, 7, 3, 1, 18),
(40, 7, 4, 0, 0),
(41, 7, 5, 0, 0),
(42, 7, 6, 0, 0),
(43, 8, 1, 1, 8),
(44, 8, 2, 0, 0),
(45, 8, 3, 1, 18),
(46, 8, 4, 0, 0),
(47, 8, 5, 0, 0),
(48, 8, 6, 0, 0),
(49, 9, 1, 0, 0),
(50, 9, 2, 1, 10),
(51, 9, 3, 0, 0),
(52, 9, 4, 1, 23),
(53, 9, 5, 0, 0),
(54, 9, 6, 0, 0),
(55, 10, 1, 0, 0),
(56, 10, 2, 1, 10),
(57, 10, 3, 0, 0),
(58, 10, 4, 1, 23),
(59, 10, 5, 0, 0),
(60, 10, 6, 0, 0);





update schedule_block_category set max_reservations=1 where max_reservations>0; 