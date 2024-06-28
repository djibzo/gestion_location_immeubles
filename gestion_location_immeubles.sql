-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 28 juin 2024 à 13:38
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_location_immeubles`
--

-- --------------------------------------------------------

--
-- Structure de la table `contratsdelocations`
--

CREATE TABLE `contratsdelocations` (
  `idContrat` int(11) NOT NULL,
  `idUnite` int(11) NOT NULL,
  `idLocataire` int(11) NOT NULL,
  `datedeCreation` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `contratsdelocations`
--

INSERT INTO `contratsdelocations` (`idContrat`, `idUnite`, `idLocataire`, `datedeCreation`) VALUES
(1, 14, 3, '2024-06-25'),
(3, 15, 10, '2024-06-26'),
(4, 14, 13, '2024-06-26');

-- --------------------------------------------------------

--
-- Structure de la table `demandes`
--

CREATE TABLE `demandes` (
  `idDemande` int(11) NOT NULL,
  `idOffre` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `etat` int(11) NOT NULL COMMENT '0 pending\r\n1 accepte\r\n-1 rejet\r\n',
  `datedecreation` date DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `demandes`
--

INSERT INTO `demandes` (`idDemande`, `idOffre`, `idUser`, `etat`, `datedecreation`) VALUES
(14, 9, 3, 1, '2024-06-25'),
(15, 10, 10, 2, '2024-06-25'),
(16, 9, 10, 2, '2024-06-26'),
(17, 10, 10, 1, '2024-06-26'),
(18, 9, 13, 1, '2024-06-26');

-- --------------------------------------------------------

--
-- Structure de la table `immeubles`
--

CREATE TABLE `immeubles` (
  `idImmeuble` int(11) NOT NULL,
  `nomImmeuble` varchar(100) NOT NULL,
  `adresseImmeuble` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `idProprietaire` int(11) NOT NULL,
  `datedeCreation` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `immeubles`
--

INSERT INTO `immeubles` (`idImmeuble`, `nomImmeuble`, `adresseImmeuble`, `description`, `idProprietaire`, `datedeCreation`) VALUES
(5, 'Immeuble AM FALL ', 'HLM Grand Médine', 'Tres calme', 5, '2024-06-25'),
(6, 'Immeuble Gassama 3', 'POINT E', 'Tres calme', 2, '2024-06-24'),
(7, 'Immeuble Amina', 'PIKINE', 'Normal', 11, '2024-06-26');

-- --------------------------------------------------------

--
-- Structure de la table `offres`
--

CREATE TABLE `offres` (
  `idOffre` int(11) NOT NULL,
  `idUnite` int(11) NOT NULL,
  `statut` int(3) NOT NULL,
  `datedeCreation` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `offres`
--

INSERT INTO `offres` (`idOffre`, `idUnite`, `statut`, `datedeCreation`) VALUES
(9, 14, 1, '2024-06-24'),
(10, 15, 1, '2024-06-25'),
(11, 16, 0, '2024-06-26');

-- --------------------------------------------------------

--
-- Structure de la table `paiements`
--

CREATE TABLE `paiements` (
  `idPaiement` int(11) NOT NULL,
  `idLocataire` int(11) NOT NULL,
  `idContratLocation` int(11) NOT NULL,
  `datedeCreation` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `unitesdelocations`
--

CREATE TABLE `unitesdelocations` (
  `idUnite` int(11) NOT NULL,
  `nomUnite` varchar(20) NOT NULL,
  `nombrePieces` int(11) NOT NULL,
  `superficie` float NOT NULL,
  `prixLoyer` float DEFAULT NULL,
  `datedeCreation` date NOT NULL DEFAULT current_timestamp(),
  `idImmeuble` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `unitesdelocations`
--

INSERT INTO `unitesdelocations` (`idUnite`, `nomUnite`, `nombrePieces`, `superficie`, `prixLoyer`, `datedeCreation`, `idImmeuble`) VALUES
(14, 'GPE1', 3, 200, 200000, '2024-06-24', 6),
(15, 'AF1', 4, 250, 200000, '2024-06-25', 5),
(16, 'AMY1', 3, 200, 200000, '2024-06-26', 7);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `idUser` int(11) NOT NULL,
  `prenomUser` varchar(70) NOT NULL,
  `nomUser` varchar(30) NOT NULL,
  `emailUser` varchar(50) NOT NULL,
  `mdpUser` varchar(255) NOT NULL,
  `profilUser` int(11) NOT NULL,
  `tokenResetMdp` varchar(255) DEFAULT NULL,
  `datedeCreation` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`idUser`, `prenomUser`, `nomUser`, `emailUser`, `mdpUser`, `profilUser`, `tokenResetMdp`, `datedeCreation`) VALUES
(2, 'Samba', 'Gassama', 'gas@gmail.com', '$2a$10$jSux46ULr4PgjXHYw80uX.bsFQo1RQ0N9zDF3Ul7NVFcpPyD00FKu', 2, NULL, '2024-06-15'),
(3, 'Djibril', 'Fall', 'falld632@gmail.com', '$2a$10$71ya5.aQdkIKTGdqYhPhbOqeZN0HePXf12cU9elxzOtZPBGGqdJP2', 3, NULL, '2024-06-15'),
(4, 'Djibril', 'Fall', 'djibzo90@gmail.com', '$2a$10$JnO3pO7wfXdYOOGiCDRuouXG83xFmPzLybvi2a0IbkCS4usG65YiC', 7, NULL, '2024-06-15'),
(5, 'Amadou', 'Fall', 'amfall@gmail.com', '$2a$10$LaSjjktRFsfBHaHLoKso0.ISrAkOQWsI3..7wavupHfbCcsMckllW', 2, NULL, '2024-06-16'),
(10, 'Maco', 'FALL', 'max@gmail.com', '$2a$10$Q8VGUvEO305q9oIOGKVmIemGtcor9xLEo7FbGDN1251LUIuZUXN2u', 3, NULL, '2024-06-23'),
(11, 'Amina', 'FALL', 'aminafall@gmail.com', '$2a$10$o7.gMFa2WpUuLFMkoEGGvOe.wlw.vJyyuT44iduXWgK469b30IjnW', 2, NULL, '2024-06-26'),
(12, 'Diarra', 'FALL', 'diarra@gmail.com', '$2a$10$vGqW/0ux9SGyjuuT8E8FK.FtO1vB51pFLlKASc8TeMoR2iu5zFc9.', 3, NULL, '2024-06-26'),
(13, 'test', 'test', 'layegaye001@gmail.com', '$2a$10$7mz/KYuZryX7.fRap9mD7upodR6aplB6xYH4kjmJ0AXVIM7VczgOa', 3, NULL, '2024-06-26');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `contratsdelocations`
--
ALTER TABLE `contratsdelocations`
  ADD PRIMARY KEY (`idContrat`),
  ADD KEY `contratsdelocations_ibfk_1` (`idUnite`),
  ADD KEY `contratsdelocations_ibfk_2` (`idLocataire`);

--
-- Index pour la table `demandes`
--
ALTER TABLE `demandes`
  ADD PRIMARY KEY (`idDemande`),
  ADD KEY `demandes_ibfk_1` (`idOffre`),
  ADD KEY `demandes_ibfk_2` (`idUser`);

--
-- Index pour la table `immeubles`
--
ALTER TABLE `immeubles`
  ADD PRIMARY KEY (`idImmeuble`),
  ADD KEY `idProprietaire` (`idProprietaire`);

--
-- Index pour la table `offres`
--
ALTER TABLE `offres`
  ADD PRIMARY KEY (`idOffre`),
  ADD KEY `idUnite` (`idUnite`);

--
-- Index pour la table `paiements`
--
ALTER TABLE `paiements`
  ADD PRIMARY KEY (`idPaiement`),
  ADD KEY `idLocataire` (`idLocataire`),
  ADD KEY `idContratLocation` (`idContratLocation`);

--
-- Index pour la table `unitesdelocations`
--
ALTER TABLE `unitesdelocations`
  ADD PRIMARY KEY (`idUnite`),
  ADD KEY `unitesdelocations_ibfk_1` (`idImmeuble`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `contratsdelocations`
--
ALTER TABLE `contratsdelocations`
  MODIFY `idContrat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `demandes`
--
ALTER TABLE `demandes`
  MODIFY `idDemande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `immeubles`
--
ALTER TABLE `immeubles`
  MODIFY `idImmeuble` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `offres`
--
ALTER TABLE `offres`
  MODIFY `idOffre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `paiements`
--
ALTER TABLE `paiements`
  MODIFY `idPaiement` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `unitesdelocations`
--
ALTER TABLE `unitesdelocations`
  MODIFY `idUnite` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `contratsdelocations`
--
ALTER TABLE `contratsdelocations`
  ADD CONSTRAINT `contratsdelocations_ibfk_1` FOREIGN KEY (`idUnite`) REFERENCES `unitesdelocations` (`idUnite`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `contratsdelocations_ibfk_2` FOREIGN KEY (`idLocataire`) REFERENCES `utilisateurs` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `demandes`
--
ALTER TABLE `demandes`
  ADD CONSTRAINT `demandes_ibfk_1` FOREIGN KEY (`idOffre`) REFERENCES `offres` (`idOffre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `demandes_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `utilisateurs` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `immeubles`
--
ALTER TABLE `immeubles`
  ADD CONSTRAINT `immeubles_ibfk_1` FOREIGN KEY (`idProprietaire`) REFERENCES `utilisateurs` (`idUser`);

--
-- Contraintes pour la table `offres`
--
ALTER TABLE `offres`
  ADD CONSTRAINT `offres_ibfk_1` FOREIGN KEY (`idUnite`) REFERENCES `unitesdelocations` (`idUnite`);

--
-- Contraintes pour la table `paiements`
--
ALTER TABLE `paiements`
  ADD CONSTRAINT `paiements_ibfk_1` FOREIGN KEY (`idLocataire`) REFERENCES `utilisateurs` (`idUser`),
  ADD CONSTRAINT `paiements_ibfk_2` FOREIGN KEY (`idContratLocation`) REFERENCES `contratsdelocations` (`idContrat`);

--
-- Contraintes pour la table `unitesdelocations`
--
ALTER TABLE `unitesdelocations`
  ADD CONSTRAINT `unitesdelocations_ibfk_1` FOREIGN KEY (`idImmeuble`) REFERENCES `immeubles` (`idImmeuble`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
