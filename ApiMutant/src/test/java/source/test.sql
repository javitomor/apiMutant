CREATE DATABASE IF NOT EXISTS `xmen_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `xmen_db`;

CREATE TABLE `adn` (
  `id_adn` bigint(20) NOT NULL,
  `cadena` varchar(255) DEFAULT NULL,
  `hash_code` int(11) DEFAULT NULL,
  `is_mutant` bit(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


INSERT INTO `adn` (`id_adn`, `cadena`, `hash_code`, `is_mutant`) VALUES
(1, '[\"TCACTG\",\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\"]', 863293009, b'1'),
(3, '[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]', 1229731815, b'1'),
(4, '[\"TTGCCA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CACCTA\",\"TCACTG\"]', 56443064, b'0');


CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


ALTER TABLE `adn`
  ADD PRIMARY KEY (`id_adn`);