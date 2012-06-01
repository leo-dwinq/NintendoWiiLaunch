DROP TABLE IF EXISTS `age_range`;
CREATE TABLE IF NOT EXISTS `age_range` (
  `id` int(11) NOT NULL auto_increment,
  `age_min` int(11),
  `age_max` int(11),
  `is_register_allowed` int(11),
  `is_chaperon_required` int(11),
  `range` varchar(32),
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Holds schedule block';


INSERT INTO age_range 
(id, age_min, age_max, is_register_allowed, is_chaperon_required, `range`)
VALUES
(1, null, 13, 0, 0, 'under 13'),
(2, 13, 17, 1, 1, '13-17'),
(3, 18, 24, 1, 0, '18-24'),
(4, 25, 24, 1, 0, '25-34'),
(5, 35, 24, 1, 0, '35-44'),
(6, 45, 24, 1, 0, '45-54'),
(7, 55, 0, 1, 0, '55+');
