DROP TABLE IF EXISTS `guest_block`;
CREATE TABLE IF NOT EXISTS `guest_block` (
  `id` int(11) NOT NULL auto_increment,
  `access_code` varchar(16) NOT NULL,
  `first_name` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Holds schedule block';