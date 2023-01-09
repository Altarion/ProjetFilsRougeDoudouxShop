select nomproduit,prix from achat inner join produit on produit.idproduit=achat.idproduit;

UPDATE produit
SET quantite = quantite + achat.nbrachat
FROM achat
WHERE produit.idproduit = achat.idproduit;

insert into achat (idproduit,fournisseur,date,nbrachat) values (1,'java.com','13-12-2022',23);

Create table panier (
idproduit int references produit(idproduit));

insert into panier values (1);

select nomproduit,prix from panier inner join produit on produit.idproduit=panier.idproduit; 

create table vente (
idproduit int references produit (idproduit),
dateachat date);

INSERT INTO vente (idproduit, dateachat)
SELECT idproduit, CURRENT_DATE
FROM panier;

TRUNCATE TABLE panier;