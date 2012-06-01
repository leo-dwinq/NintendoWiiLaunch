DROP TABLE IF EXISTS `guest_category`;
CREATE TABLE IF NOT EXISTS `guest_category` (
  `id` int(11) NOT NULL auto_increment,
  `category_code` varchar(16) NOT NULL,
  `description` varchar(256) NOT NULL,
  `max_influencer_guests` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Holds guest categories';


INSERT INTO guest_category
(id, category_code, description, max_influencer_guests)
VALUES
(1, 'FSF', 'Fanscape Families', 3),
(2, 'FSA', 'Fanscape Adult Influencers', 2),
(3, 'DCCF', 'Club Nintendo Family Fans', 3),
(4, 'DCCA', 'Club Nintendo Adult Super Fans', 2),
(5, 'GS', 'Retail Game Stop', 0),
(6, 'BBY', 'Retail Best Buy', 0),
(7, 'GUEST', 'Guest', 0);
