create table produit(
        idproduit SERIAL primary key,
        nomproduit varchar(30),
        prix NUMERIC(9,2),
        quantite int);
		
CREATE TABLE achat (
    idachat SERIAL primary key,
    idproduit int REFERENCES produit(idproduit),
    fournisseur varchar(30),
    date Date DEFAULT current_timestamp,
    nbrachat int);

