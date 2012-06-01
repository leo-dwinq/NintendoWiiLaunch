DROP TABLE IF EXISTS `venue`;
CREATE TABLE IF NOT EXISTS `venue` (
  `id` int(11) NOT NULL,
  `address` varchar(16),
  `city` varchar(32) NOT NULL,
  `state` varchar(32) NOT NULL,
  `postal_code` varchar(16)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Holds venue info';



INSERT INTO venue
(id, address, city, state, postal_code)
VALUES
(1, null, 'New York','New York',null);