--creer table produit:

create table produit(
        idproduit SERIAL primary key,
        nomproduit varchar(30),
        prix NUMERIC(9,2),
        quantite int);

--inserttest 

insert into produit (idproduit,nomproduit,prix,quantite) values (1,'le java de J-M',32,44);

--creer table achat:

CREATE TABLE achat (
    idachat SERIAL primary key,
    idproduit int REFERENCES produit(idproduit),
    fournisseur varchar(30),
    date Date DEFAULT current_timestamp,
    nbrachat int
);


--inserttest :

insert into achat (idproduit,fournisseur,date,nbrachat) values (1,'java.com','13-12-2022',23);

--test liaison:

select nomproduit,prix from achat inner join produit on produit.idproduit=achat.idproduit;

--test ajout prod :

UPDATE produit
SET quantite = quantite + achat.nbrachat
FROM achat
WHERE produit.idproduit = achat.idproduit

--afficher l historique des achat

select nomproduit,achat.idproduit,fournisseur,date,nbrachat from achat 
inner join produit on produit.idproduit = achat.idproduit
order by date;

--creer la table panier

create table panier (
idproduit int references produit(idproduit),
idpanier serial);

--insert:

insert into panier values (1);

--test affichage nomproduit prix du panier:

select nomproduit,prix from panier inner join produit on produit.idproduit=panier.idproduit; 

--creer table vente

create table vente (
idproduit int references produit (idproduit),
dateachat date);


--passer les données de panier vers vente

INSERT INTO vente (idproduit, dateachat)
SELECT idproduit, CURRENT_DATE
FROM panier;

--enlever les produit dans la table produit

UPDATE produit p
SET quantite = quantite - v.count
FROM (
    SELECT idproduit, COUNT(*) as count
    FROM panier
    GROUP BY idproduit
) v
WHERE v.idproduit = p.idproduit;

--vider le panier

TRUNCATE TABLE panier;

