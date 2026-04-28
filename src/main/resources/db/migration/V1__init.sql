CREATE TABLE patient(
                    id bigint auto_increment primary key,
                    nom varchar(30) NOT NULL ,
                    prenom varchar(30)NOT NULL ,
                    email varchar(50) NOT NULL UNIQUE ,
                    telephone varchar(30) NOT NULL ,
                    date_naissance date not null

);

CREATE TABLE medecin(
                    id bigint auto_increment primary key,
                    nom varchar(50) not null ,
                    specialite varchar(50) NOT NULL ,
                    email varchar(50) UNIQUE  NOT NULL ,
                    telephone varchar(30) NOT NULL
);

CREATE TABLE rendez_vous(
                    id bigint auto_increment primary key,
                    date_rendez_vous DATETIME not null ,
                    statut enum('EN_ATTENTE','CONFIRME','ANNULE') not null
);

CREATE TABLE dossier_medical(
                    id bigint auto_increment primary key,
                    diagnostic varchar(255)NOT NULL ,
                    observation varchar(255) not null ,
                    date_creation date not null
);

