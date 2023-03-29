CREATE SEQUENCE commune_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE district_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE province_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE region_id_seq START WITH 1 INCREMENT BY 1;

CREATE  TABLE province ( 
	id                   integer DEFAULT nextval('province_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(50)  NOT NULL  ,
	CONSTRAINT province_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE region ( 
	id                   integer DEFAULT nextval('region_id_seq'::regclass) NOT NULL  ,
	idprovince           integer  NOT NULL  ,
	nom                  varchar(50)  NOT NULL  ,
	CONSTRAINT region_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE district ( 
	id                   integer DEFAULT nextval('district_id_seq'::regclass) NOT NULL  ,
	idregion             integer  NOT NULL  ,
	nom                  varchar(50)  NOT NULL  ,
	CONSTRAINT district_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE commune ( 
	id                   integer DEFAULT nextval('commune_id_seq'::regclass) NOT NULL  ,
	id_district          integer  NOT NULL  ,
	nom                  varchar(50)  NOT NULL  ,
	CONSTRAINT commune_pkey PRIMARY KEY ( id )
 );

ALTER TABLE commune ADD CONSTRAINT commune_iddistrict_fkey FOREIGN KEY ( id_district ) REFERENCES district( id );

ALTER TABLE district ADD CONSTRAINT district_idregion_fkey FOREIGN KEY ( idregion ) REFERENCES region( id );

ALTER TABLE region ADD CONSTRAINT region_idprovince_fkey FOREIGN KEY ( idprovince ) REFERENCES province( id );

INSERT INTO province(nom ) VALUES ('ANTANANARIVO');
INSERT INTO province(nom ) VALUES ('ANTSIRANANA');
INSERT INTO province(nom ) VALUES ('FIANARANTSOA');
INSERT INTO region(idprovince, nom ) VALUES (1, 'ANALAMANGA');
INSERT INTO region(idprovince, nom ) VALUES (1, 'BONGOLAVA');
INSERT INTO region(idprovince, nom ) VALUES (1, 'ITASY');
INSERT INTO region(idprovince, nom ) VALUES (2, 'DIANA');
INSERT INTO region(idprovince, nom ) VALUES (2, 'SAVA');
INSERT INTO region(idprovince, nom ) VALUES (3, 'HAUTE MATSIATRA');
INSERT INTO region(idprovince, nom ) VALUES (3, 'IHOROMBE');
INSERT INTO region(idprovince, nom ) VALUES (3, 'VATOVAVY FITOVINANY');
INSERT INTO district(idregion, nom ) VALUES (1, 'Andramasina');
INSERT INTO district(idregion, nom ) VALUES (1, 'Ambohidratrimo');
INSERT INTO district(idregion, nom ) VALUES (2, 'Fenoarivobe');
INSERT INTO district(idregion, nom ) VALUES (2, 'Tsiroanomandidy');
INSERT INTO district(idregion, nom ) VALUES (3, 'Arivonimamo');
INSERT INTO district(idregion, nom ) VALUES (3, 'Miarinarivo');
INSERT INTO district(idregion, nom ) VALUES (4, 'Ambanja');
INSERT INTO district(idregion, nom ) VALUES (4, 'Ambilobe');
INSERT INTO district(idregion, nom ) VALUES (5, 'Andapa');
INSERT INTO district(idregion, nom ) VALUES ( 5, 'Antalaha');
INSERT INTO district(idregion, nom ) VALUES ( 6, 'Ambalavao');
INSERT INTO district(idregion, nom ) VALUES ( 6, 'Ambohimahasoa');
INSERT INTO district(idregion, nom ) VALUES ( 7, 'Iakora');
INSERT INTO district(idregion, nom ) VALUES ( 7, 'Ivohibe');
INSERT INTO district(idregion, nom ) VALUES ( 8, 'Manakara');
INSERT INTO district(idregion, nom ) VALUES ( 8, 'Ikongo');
INSERT INTO commune(id_district, nom ) VALUES (1, 'Vatosola');
INSERT INTO commune(id_district, nom ) VALUES (2, 'Ambato');
INSERT INTO commune(id_district, nom ) VALUES (2, 'Ambohidratrimo');
INSERT INTO commune(id_district, nom ) VALUES (3, 'Ambatomainty Sud');
INSERT INTO commune(id_district, nom ) VALUES (3, 'Firavahana');
INSERT INTO commune(id_district, nom ) VALUES (4, 'Amabalanirana');
INSERT INTO commune(id_district, nom ) VALUES (4, 'Ambaratabe');
INSERT INTO commune(id_district, nom ) VALUES (5, 'Alakamisikely');
INSERT INTO commune(id_district, nom ) VALUES ( 5, 'Ambatonga');
INSERT INTO commune(id_district, nom ) VALUES ( 6, 'Analavory');
INSERT INTO commune(id_district, nom ) VALUES ( 6, 'Andolofotsy');
INSERT INTO commune(id_district, nom ) VALUES ( 7, 'Ambalahonko');
INSERT INTO commune(id_district, nom ) VALUES ( 7, 'Ambohimarina');
INSERT INTO commune(id_district, nom ) VALUES ( 8, 'Anaborano Ifasy');
INSERT INTO commune(id_district, nom ) VALUES ( 8, 'Antsaravibe');
INSERT INTO commune(id_district, nom ) VALUES ( 9, 'Andapa');
INSERT INTO commune(id_district, nom ) VALUES ( 9, 'Andrakata');
INSERT INTO commune(id_district, nom ) VALUES ( 10, 'Ambalabe');
INSERT INTO commune(id_district, nom ) VALUES ( 10, 'Ambinanifaho');
INSERT INTO commune(id_district, nom ) VALUES ( 11, 'Ambalavao');
INSERT INTO commune(id_district, nom ) VALUES ( 11, 'Ambinaniroa');
INSERT INTO commune(id_district, nom ) VALUES ( 12, 'Ambohimahasoa');
INSERT INTO commune(id_district, nom ) VALUES ( 12, 'Ambatosoa');
INSERT INTO commune(id_district, nom ) VALUES ( 13, 'Iakoro');
INSERT INTO commune(id_district, nom ) VALUES ( 13, 'Begogo');
INSERT INTO commune(id_district, nom ) VALUES ( 14, 'Ivohibe');
INSERT INTO commune(id_district, nom ) VALUES ( 14, 'Ivongo');
INSERT INTO commune(id_district, nom ) VALUES ( 15, 'Ambahatrazo');
INSERT INTO commune(id_district, nom ) VALUES ( 15, 'Ambahive');
INSERT INTO commune(id_district, nom ) VALUES ( 16, 'Ambatofotsy');
INSERT INTO commune(id_district, nom ) VALUES ( 16, 'Ambinatromby');
INSERT INTO commune(id_district, nom ) VALUES ( 1, 'test hibernate 2');
INSERT INTO commune(id_district, nom ) VALUES ( 1, 'test hibernate 2');
INSERT INTO commune(id_district, nom ) VALUES ( 1, 'test hibernate DAO');
INSERT INTO commune(id_district, nom ) VALUES ( 1, 'test hibernate DAO');
INSERT INTO commune(id_district, nom ) VALUES ( 1, 'Hhhh');
INSERT INTO commune(id_district, nom ) VALUES ( 1, 'Hhhh');
INSERT INTO commune(id_district, nom ) VALUES ( 1, 'Model');
INSERT INTO commune(id_district, nom ) VALUES ( 5, 'Ain');
INSERT INTO commune(id_district, nom ) VALUES ( 1, 'aina');
INSERT INTO commune(id_district, nom ) VALUES ( 1, 'test1');
INSERT INTO commune(id_district, nom ) VALUES (1, 'vatosola');
INSERT INTO commune(id_district, nom ) VALUES ( 2, 'Jejo');
INSERT INTO commune(id_district, nom ) VALUES ( 3, 'Jika');
