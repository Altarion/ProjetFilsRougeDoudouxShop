package co.simplon.Doudouxshop;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table(name = "panier")
public class Panier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idpanier;

	@ManyToOne
	@JoinColumn(name = "idproduit")
	private Produit idproduit;

	/**
	 * @param idproduit
	 */
	public Panier(Produit idproduit) {
		super();
		this.idproduit = idproduit;
	}

	public Panier() {
		super();
	}

	
	public void ajouterPanier(Produit prod) {
	    EntityManager em = utils.JPA.getEntityManager();
	    EntityTransaction et = null;

	    try {
	        et = em.getTransaction();
	        et.begin();

	        // Récupérer l'objet Produit à partir de la base de données
	        Produit produit = em.find(Produit.class, prod.getId());

	        // Créer un nouvel objet Panier et définir son idproduit
	        Panier panier = new Panier();
	        panier.setIdproduit(produit);

	        // Enregistrer l'objet Panier
	        em.persist(panier);
	        et.commit();

	        System.out.println("Le produit a bien été ajouté au panier");
	    } catch (Exception ex) {
	        if (et != null) {
	            et.rollback();
	        }
	        ex.printStackTrace();
	    } finally {
	        // Fermer l'EntityManager
	        // em.close();
	    }
	}



	

	public void supprimerArticle(int idproduit) {
		EntityManager em = utils.JPA.getEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			// Find the Panier object with the given idproduit
			TypedQuery<Panier> query = em.createQuery("SELECT p FROM Panier p WHERE p.idproduit.idproduit = :idproduit",
					Panier.class);
			query.setParameter("idproduit", idproduit);
			List<Panier> paniers = query.getResultList();

			if (!paniers.isEmpty()) {
				Panier panier = paniers.get(0);
				em.remove(panier);
			}

			et.commit();

			System.out.println("Le produit a bien été supprimé du panier");
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			Main.main(null);
			// em.close();
		}
	}

	public void AfficherPanier() {
		EntityManager em = utils.JPA.getEntityManager();
		// the lowercase a refers to the Achat object
		String strQuery = "SELECT a FROM Panier a WHERE a.idproduit IS NOT NULL";

		// Issue the query and get a list of Achat objects
		TypedQuery<Panier> tq = em.createQuery(strQuery, Panier.class);
		List<Panier> paniers;
		try {
			// Get the list of Achat objects and output the details of each purchase
			paniers = tq.getResultList();
			if (paniers.isEmpty()) {
				System.out.println("Le panier est vide");
			} else {
				paniers.forEach(panier -> System.out.println("\rNom du produit: " + panier.getIdproduit().getNom()
						+ "\rPrix du produit: " + panier.getIdproduit().getPrix() + "€" + "\rProduit disponible: "
						+ panier.getIdproduit().getQuantite()));
			}
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			// em.close();
		}
	}

	public void AfficherPanierMenu() {
		EntityManager em = utils.JPA.getEntityManager();
		// the lowercase a refers to the Achat object
		String strQuery = "SELECT a FROM Panier a WHERE a.idproduit IS NOT NULL";

		// Issue the query and get a list of Achat objects
		TypedQuery<Panier> tq = em.createQuery(strQuery, Panier.class);
		List<Panier> paniers;
		try {
			// Get the list of Achat objects and output the details of each purchase
			paniers = tq.getResultList();
			if (paniers.isEmpty()) {
				System.out.println("Le panier est vide");
				Main.main(null);
			} else {
				paniers.forEach(panier -> System.out.println("\rNom du produit: " + panier.getIdproduit().getNom()
						+ "\rPrix du produit: " + panier.getIdproduit().getPrix() + "€" + "\rProduit disponible: "
						+ panier.getIdproduit().getQuantite()));
			}
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			// em.close();
		}
	}
	
	
	
	public void AfficherPanierAvecId() {
		EntityManager em = utils.JPA.getEntityManager();

		// the lowercase a refers to the Achat object
		String strQuery = "SELECT a FROM Panier a WHERE a.idproduit IS NOT NULL";

		// Issue the query and get a list of Achat objects
		TypedQuery<Panier> tq = em.createQuery(strQuery, Panier.class);
		List<Panier> achats;
		try {
			// Get the list of Achat objects and output the details of each purchase
			achats = tq.getResultList();
			achats.forEach(achat -> System.out.println("\rNom du produit: " + achat.getIdproduit().getNom()
					+ "\rPrix du produit: " + achat.getIdproduit().getPrix() + "€" + "\rProduit disponible: "
					+ achat.getIdproduit().getQuantite() + "\rId du produit: " + achat.getIdproduit().getId()));
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			// em.close();
		}
	}

	public void viderPanier() {
		EntityManager em = utils.JPA.getEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();

			// Truncate la table panier
			em.createNativeQuery("TRUNCATE TABLE panier").executeUpdate();

			et.commit();
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			//em.close();
		}
	}

	public void getPanier(String nom) {
		EntityManager em = utils.JPA.getEntityManager();

		String query = "SELECT p FROM Produit p INNER JOIN Panier pan ON p.id = pan.idproduit WHERE LOWER(TRIM(p.nom)) = LOWER(TRIM(:nom))";
		TypedQuery<Produit> tq = em.createQuery(query, Produit.class);
		tq.setParameter("nom", nom);

		List<Produit> produits = null;
		try {
			produits = tq.getResultList();
		} catch (NoResultException ex) {
			ex.printStackTrace();
			System.out.println("Le produit n'existe pas ou une erreur est survenue");
		} finally {
			// em.close();
		}

		if (produits == null || produits.isEmpty()) {
			System.out.println("Aucun produit trouvé dans le panier");
			Main.main(null);
		} else {
			produits.forEach(produit -> System.out.println("\rNom: " + produit.getNom() + "\rPrix: " + produit.getPrix()
					+ "€" + "\rDisponible: " + produit.getQuantite()));
		}
	}

	

	
	/**
	 * @return the id
	 */
	public Long getId() {
		return idpanier;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.idpanier = id;
	}

	/**
	 * @return the idproduit
	 */
	public Produit getIdproduit() {
		return idproduit;
	}

	/**
	 * @param idproduit the idproduit to set
	 */
	public void setIdproduit(Produit idproduit) {
		this.idproduit = idproduit;
	}

}