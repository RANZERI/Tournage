create table Acteur(
    id serial primary key,
    nom varchar(50)
);
create table Auteur(
    id serial primary,
    nom varchar(50)
);
create table Film(
    id serial primary,
    nom varchar(50)
);
create table Plateau(
    id serial primary key,
    nom varchar(50)
);
create table PlateauNonDisponible(
    id serial primary key,
    idPlateau int REFERENCES Plateau(id),   
    observation TEXT, 
    dates date
);
create table ActeurNonDisponible(
    id serial primary key,
    idActeur int REFERENCES Acteur(id),
    observation TEXT,
    dates date
);
create table Scene( 
    id serial primary key,
    idAuteur int REFERENCES Auteur(id),
    idFilm int REFERENCES Film(id),
    descriptions TEXT,
    idPlateau int REFERENCES Plateau(id),
    statut varchar(50)
);
create table Action(
    id serial primary key,
    idActeur int REFERENCES Acteur(id),
    idScene int REFERENCES Scene(id),
    durre time,
    descriptions TEXT,
    dialogue TEXT
);
create table Planning(
    id serial primary key,
    idScene int REFERENCES Scene(id),
    dates date
);

-- insert into Acteur(nom) values('Jean');
-- create table Planning(
--     id serial primary key,
--     idaction int REFERENCES Action (id),
--     jour varchar(50)
-- );
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(7,7,'00:45:00','Milalao ludo','Alefao manga');
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(8,7,'00:45:00','Milalao ludo','Alefao maintso');
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(9,7,'00:45:00','Milalao ludo','Bond izy zany');
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(7,8,'00:45:00','Milalao boalina','Alefao manga');
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(8,8,'00:45:00','Milalao boalina','Alefao maintso');
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(9,8,'00:45:00','Milalao boalina','Bond izy zany');
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(7,9,'00:45:00','Mijery Tele','Malahelo aho');
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(8,9,'00:45:00','Mijery Tele','Tsara izany sakafo');
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(9,9,'00:45:00','Mijery Tele','Mandresy tsika');
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(8,10,'00:45:00','Misakafo anaty Gargotte','Tsara izany sakafo');
insert into Action(idActeur,idScene,durre,descriptions,dialogue) values(9,10,'00:45:00','Misakafo anaty Gargotte','Tsara izany raha asina sira');




