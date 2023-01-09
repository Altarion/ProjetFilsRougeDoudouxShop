package co.simplon.Doudouxshop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class Commande {

	/**
	 * Cette méthode permet de passer une commande en récupérant les produits dans
	 * le panier et en vérifiant qu'il y a suffisamment de quantités disponibles
	 * pour chaque produit. 
	 * Si c'est le cas, les données de panier sont insérées
	 * dans la table de vente et la quantité de chaque produit est mise à jour.
	 * Enfin, le contenu du panier est vidé.
	 * 
	 */
	public void passerCommande() {
		EntityManager em = utils.JPA.getEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			// Récupérer la liste des produits dans le panier
			String query = "SELECT p FROM Panier p WHERE p.idproduit IS NOT NULL";
			TypedQuery<Panier> tq = em.createQuery(query, Panier.class);
			List<Panier> paniers = tq.getResultList();

			// Vérifier s'il y a assez de produit disponible pour chaque produit dans le
			// panier
			for (Panier panier : paniers) {
				Produit produit = panier.getIdproduit();
				if (produit.getQuantite() < 1) {
					System.out.println("\rIl n'y a pas assez de produit " + produit.getNom() + " disponible");
					;
				}
			}

			// passer les données de panier vers vente
			em.createNativeQuery("INSERT INTO vente (idproduit, dateachat) SELECT idproduit, CURRENT_DATE FROM panier")
					.executeUpdate();

			// Mettre a joue les quantite
			em.createNativeQuery(
					"UPDATE produit p SET quantite = quantite - v.count FROM (SELECT idproduit, COUNT(*) as count FROM panier GROUP BY idproduit) v WHERE v.idproduit = p.idproduit")
					.executeUpdate();

			// Truncate le table panier
			em.createNativeQuery("TRUNCATE TABLE panier").executeUpdate();

			et.commit();
			System.exit(0);
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			// em.close();
		}
	}

}
