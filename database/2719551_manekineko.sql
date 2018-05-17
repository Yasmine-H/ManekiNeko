-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  fdb21.awardspace.net
-- Généré le :  Jeu 17 Mai 2018 à 21:10
-- Version du serveur :  5.7.20-log
-- Version de PHP :  5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `2719551_manekineko`
--

-- --------------------------------------------------------

--
-- Structure de la table `meals`
--

CREATE TABLE `meals` (
  `sno` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `price` decimal(6,2) NOT NULL,
  `description` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `totalVotes` int(1) DEFAULT NULL,
  `nbVotes` int(5) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `type` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `meals`
--

INSERT INTO `meals` (`sno`, `name`, `price`, `description`, `photo`, `totalVotes`, `nbVotes`, `created_at`, `type`) VALUES
(2, 'Ramune Fraise', 3.00, 'Limonade japonaise de 200 ml, goût fraise', 'drawable\\boisson_ramune_fraise', 0, 0, NULL, 'boisson'),
(3, 'Aloe Vera King', 3.00, 'Boisson de 350 ml à base d\'Aloe Vera', 'drawable\\boisson_aloe_vera_king', 0, 0, NULL, 'boisson'),
(4, 'Sirop de Yuzu', 3.00, 'Sirop de Yuzu (citron japonais)', 'drawable\\boisson_sirop_yuzu', 0, 0, NULL, 'boisson'),
(5, 'Thé Oolong', 3.00, 'Thé Oolong', 'drawable\\boisson_the_oolong', 0, 0, NULL, 'boisson'),
(6, 'Eau minérale', 2.00, 'Eau minérale', 'drawable\\boisson_eau_minerale', 0, 0, NULL, 'boisson'),
(7, 'Sake Kameizumi', 4.00, 'Sake de 72cl', 'drawable\\boisson_sake_kameizumi', 0, 0, NULL, 'boisson'),
(8, 'Bière Kirin', 4.00, 'Bière japonaise de 33cl', 'drawable\\boisson_biere_kirin', 0, 0, NULL, 'boisson'),
(9, 'Sake chaud', 4.00, 'Sake chaud', '', 0, 0, NULL, 'boisson'),
(10, 'Vin', 4.00, 'Vin au verre', '', 0, 0, NULL, 'boisson'),
(11, 'Edamame', 6.00, 'Fèves de soja cuites au sel', 'drawable\\apero_edamame', 0, 0, NULL, 'apéritif'),
(12, 'Wasabi Peas', 6.00, 'Petits pois enrobés de wasabi croustillant', 'drawable\\apero_wasabi_peas', 0, 0, NULL, 'apéritif'),
(13, 'Tsukune', 6.00, 'Brochettes de poulet au gingembre avec sa sauce', 'drawable\\apero_tsukune', 0, 0, NULL, 'apéritif'),
(14, 'Shake tataki', 6.00, 'Emincé de saumon mi-cuit, fromage frais, sauce kizami wasabi', 'drawable\\apero_shake_tataki', 0, 0, NULL, 'apéritif'),
(15, 'Hiyayakko', 10.00, 'Tofu, sauce soja, daikon(radis) & copeaux de bonite (thon)', 'drawable\\entree_hiyayakko', 0, 0, NULL, 'entrée'),
(16, 'Kimchi maison', 10.00, 'Chou chinois saumuré (a été mis dans de la saumure -préparation liquide salée- pour être conservé) pimenté', 'drawable\\entree_kimchi_maison', 0, 0, NULL, 'entrée'),
(17, 'Horenso', 10.00, 'Salade d’épinards frais, sauce sésame, un mariage de saveurs tout aussi simple qu\'appétissant !', 'drawable\\entree_horenso', 0, 0, NULL, 'entrée'),
(18, 'Gyoza', 10.00, '6 pièces - Raviolis à l’ail et aux légumes grillés sur teppan, une méthode de grillade japonaise', 'drawable\\entree_gyoza', 0, 0, NULL, 'entrée'),
(19, 'Karaage maison', 10.00, '7 pièces - Poulet mariné au gingembre et frit, croustillant à l\'extérieur et fondant à l\'intérieur', 'drawable\\karaage_maison', 0, 0, NULL, 'entrée'),
(20, 'Takoyaki classique', 10.00, 'Sauce Otafuku, mayonnaise, bonite & Aonori (algue japonaise)', 'drawable\\plat_takoyaki_classique', 0, 0, NULL, 'plat'),
(21, 'Takoyaki épicé', 10.00, 'Sauce pimenté, mayonnaise & Aonori (algue japonaise)', 'drawable\\plat_takoyaki_spicy', 0, 0, NULL, 'plat'),
(22, 'Takoyaki Yuzu', 10.00, 'Sauce Yuzu (citron japonais) & Aonori (algue japonaise)', 'drawable\\plat_takoyaki_yuzu', 0, 0, NULL, 'plat'),
(23, 'Takoyaki Teritama', 10.00, 'Sauce Otafuku (à base de plus de 20 épices), oeufs mimosa, mayonnaise, sauce teriyaki & Aonori (algue japonaise)', 'drawable\\plat_takoyaki_teritama', 0, 0, NULL, 'plat'),
(24, 'Ramune Kimura', 3.00, 'Limonade japonaise nature de 200 ml ', 'drawable\\boisson_ramune_kimura', 0, 0, NULL, 'boisson'),
(25, 'Ramune Pastèque', 3.00, 'Limonade japonaise de 200 ml, goût pastèque', 'drawable\\boisson_ramune_pasteque', 0, 0, NULL, 'boisson'),
(26, 'Sake Musashino Black', 10.00, 'Sake de 72cl', 'drawable\\boisson_sake_musashino_black', 0, 0, NULL, 'boisson'),
(27, 'Bière Sapporo', 4.00, 'Bière Japonaise de 33cl', 'drawable\\boisson_biere_sapporo', 0, 0, NULL, 'boisson'),
(28, 'Yakisoba Tamago - Oeuf', 12.00, 'Nouilles sautées avec chou, oignon, carotte, soja, cebette, gingembre & mayonnaise japonaise', 'drawable\\plat_yakisoba_tamago', 0, 0, NULL, 'plat'),
(29, 'Yakisoba Ebi - crevettes', 12.00, 'Nouilles sautées avec chou, oignon, carotte, soja, cebette, gingembre & mayonnaise japonaise', 'drawable\\plat_yakisoba_ebi', 0, 0, NULL, 'plat'),
(30, 'Okonomiyaki kansai-fu', 12.00, 'Galette de chou cuite sur plaque chauffante selon la recette traditionnelle, les ingrédients(assortiment de légumes et oignons frits) sont mélangés ensemble, puis étalés sur la plaque et cuits sur chaque face', 'drawable\\plat_okonomiyaki_kansai_fu', 0, 0, NULL, 'plat'),
(31, 'Sukiyaki Teishoku', 12.00, 'Bœuf, tôfu, shiitake(champignon), oeuf, vermicelles, riz et soupe', 'drawable\\plat_sukiyaki_teishoku', 0, 0, NULL, 'plat'),
(32, 'Teriyaki Teishoku', 12.00, 'Poulet à la sauce Teriyaki, riz, soupe et légumes', 'drawable\\plat_teriyaki_teishoku', 0, 0, NULL, 'plat'),
(33, 'Dorayaki', 12.00, 'Pancake (Nature ou Thé vert) à l’Azuki (haricots rouges japonais)', 'drawable\\dessert_dorayaki', 0, 0, NULL, 'dessert'),
(36, 'Tiramisu Matcha', 12.00, 'Tiramisu au thé vert', 'drawable\\dessert_tiramisu_the_vert', 0, 0, NULL, 'dessert'),
(37, 'Mochi', 12.00, 'Préparation à base de riz gluant avec un coeur fondant au choix (fraise ou fleur de sakura)', 'drawable\\dessert_mochi', 0, 0, NULL, 'dessert');

-- --------------------------------------------------------

--
-- Structure de la table `meal_tag`
--

CREATE TABLE `meal_tag` (
  `mealID` int(11) DEFAULT NULL,
  `tagID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `meal_tag`
--

INSERT INTO `meal_tag` (`mealID`, `tagID`) VALUES
(11, 2),
(11, 9),
(11, 10),
(12, 4),
(12, 3),
(12, 2),
(12, 11),
(13, 5),
(13, 2),
(13, 12),
(14, 6),
(14, NULL),
(15, 2),
(15, 7),
(15, 12),
(16, 4),
(16, 11),
(16, 9),
(16, 10),
(17, 4),
(17, 9),
(17, 10),
(18, 4),
(18, 9),
(19, 12),
(20, 6),
(20, 5),
(20, 7),
(20, 13),
(21, 6),
(21, 5),
(21, 11),
(21, 13),
(22, 13),
(23, 6),
(23, 5),
(23, 13),
(28, 2),
(28, 4),
(23, 5),
(23, 9),
(23, 14),
(NULL, 8),
(NULL, 14),
(30, 5),
(30, 9),
(31, 5),
(31, 12),
(32, 12),
(33, NULL),
(33, 9),
(36, 9),
(36, 6),
(37, 9),
(37, 10);

-- --------------------------------------------------------

--
-- Structure de la table `tags`
--

CREATE TABLE `tags` (
  `sno` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `tags`
--

INSERT INTO `tags` (`sno`, `name`, `description`, `created_at`) VALUES
(2, 'allergie : soja', '', NULL),
(3, 'allergie : gluten', '', NULL),
(4, 'allergie : arachide', '', NULL),
(5, 'allergie : oeuf', '', NULL),
(6, 'allergie : lait', '', NULL),
(7, 'allergie : poisson', '', NULL),
(8, 'allergie : crustacés', '', NULL),
(9, 'végétarien', '', NULL),
(10, 'végétalien', '', NULL),
(11, 'épicé', '', NULL),
(12, 'origine produit', '', NULL),
(13, 'takoyaki', '', NULL),
(14, 'yakisoba', '', NULL),
(15, 'alcool', '', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `userdata`
--

CREATE TABLE `userdata` (
  `id` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `email` text,
  `photo` text,
  `favoriteMeals` text
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `userdata`
--

INSERT INTO `userdata` (`id`, `username`, `password`, `email`, `photo`, `favoriteMeals`) VALUES
(1, 'a', '12345678', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `sno` int(11) NOT NULL,
  `unique_id` varchar(23) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `encrypted_password` varchar(256) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`sno`, `unique_id`, `name`, `email`, `encrypted_password`, `salt`, `created_at`) VALUES
(1, '5ae628a41d9192.68705468', 'kali', 'a@a.com', '$2y$10$GQ112SOUsxX59Twwl4hz2uTT3K3AzTSpAo6fxOcxOLrmluNa.gUBK', '7f4343d653', '2018-04-29 22:18:44'),
(4, '5af36eb6361362.47273652', 'Jein', 'hamdaniyasmine15@gmail.com', '$2y$10$HdsdkkUxeiIOYitsalgYHOYYX7Ws6LJe.pv.aQq8SvtxFowzKndt.', 'aa8fb709f5', '2018-05-09 21:57:10'),
(5, '5af36ff2e6c561.91619880', 'Yasmine', 'yasmine@gmail.com', '$2y$10$NlnvxGoFoIlFLsOPW.ExFuxeFtUX/VNSY.sDrYeDbQBMxELY0K3fO', 'ce26d17314', '2018-05-09 22:02:27'),
(6, '5af371212c4816.79158236', 'Diaa', 'a@g.c', '$2y$10$1uu4vAH4reuyRPGiKHd3TutJCc8K1rjxBHsH0UM20TNOH9FlqKb9i', 'b1a4f7c395', '2018-05-09 22:07:29'),
(8, '5af372c3566ac7.16699961', 'Zina', 'aaa@g.c', '$2y$10$BODSsno5tv7b5LYvrxeT3uUsEujCatqfktMmdgGcE8HMz1Giqrb0K', '929721c77b', '2018-05-09 22:14:27'),
(9, '5af37d1f3c6457.96820229', 'Loki', 'h@h.c', '$2y$10$.cxh0oe2nI3dG3nKfhbHqe1Ry/GFzke7TRMNJkBDxDTYkUVr9ncqG', '0e0b0b65fa', '2018-05-09 22:58:39'),
(10, '5af99f36003e42.07879775', 'Bouchra', 'douidibouchra@gmail.com', '$2y$10$k4VaOyegnIWOmzqLBJxffeJJxAP66.Y5rOoXvSnSf0m30jooHIdtu', '8474697913', '2018-05-14 14:37:42'),
(11, '5af9cc614986e7.74657388', 'perlinPinpin', 'sarah@hotmail.fr', '$2y$10$LJLB5GpSAfq0c79GD/qQFeHGbH0hqMhiV36YhO4AU6CS.WAkUXLJm', '9791a3ea00', '2018-05-14 17:50:25'),
(12, '5af9fc46958b89.52801482', 'ggg', 'douidi@gmail.com', '$2y$10$besfXW8ulugsUzy5TeLguOuyzhq2jlb1ZgZ.xIF9HPLy.e9h4ZOaC', '4f8727017b', '2018-05-14 21:14:46'),
(13, '5afa31355bce66.14247115', 'Zooey', 'mew1zoe@hotmail.com', '$2y$10$5zGrEQYdfLtO0t4zwR4nPunCrzq5lUUSbWFpESVE1hAqCew9w3oBG', '10e0d4171e', '2018-05-15 01:00:37');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `meals`
--
ALTER TABLE `meals`
  ADD PRIMARY KEY (`sno`);

--
-- Index pour la table `meal_tag`
--
ALTER TABLE `meal_tag`
  ADD KEY `MEAL_FK` (`mealID`),
  ADD KEY `TAG_FK` (`tagID`);

--
-- Index pour la table `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`sno`);

--
-- Index pour la table `userdata`
--
ALTER TABLE `userdata`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`sno`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `meals`
--
ALTER TABLE `meals`
  MODIFY `sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT pour la table `tags`
--
ALTER TABLE `tags`
  MODIFY `sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT pour la table `userdata`
--
ALTER TABLE `userdata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `meal_tag`
--
ALTER TABLE `meal_tag`
  ADD CONSTRAINT `MEAL_FK` FOREIGN KEY (`mealID`) REFERENCES `meals` (`sno`),
  ADD CONSTRAINT `TAG_FK` FOREIGN KEY (`tagID`) REFERENCES `tags` (`sno`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
