package co.simplon.Doudouxshop;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table(name = "produit")

public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproduit", unique = true)
	private int idproduit;

	@Column(name = "nomproduit", length = 30, nullable = false)
	private String nom;

	@Column(name = "prix", nullable = false)
	private double prix;

	@Column(name = "quantite", nullable = false)
	private int quantite;

	/**
	 * @param nom
	 * @param prix
	 * @param quantite
	 */
	public Produit(String nom, double prix, int quantite) {
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
	}

	public Produit(int id) {

	}

	public Produit() {

	}

	public void ajoutProduit() {

		// The EntityManager class allows operations such as create, read, update,
		// delete
		EntityManager em = utils.JPA.getEntityManager();
		// Used to issue transactions on the EntityManager
		EntityTransaction et = null;

		try {
			// Get transaction and start
			et = em.getTransaction();
			et.begin();

			// Create and set values
			Produit produit = new Produit(this.nom, this.prix, this.quantite);

			// Save the customer object
			em.persist(produit);
			et.commit();
			
			System.out.println("Le produit a bien été ajouté");
		} catch (Exception ex) {
			// If there is an exception rollback changes
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			// Close EntityManager
			//em.close();
		}
	}

	public void getProduit(String nom) {
	    EntityManager em = utils.JPA.getEntityManager();

	    // the lowercase c refers to the object
	    String query = "SELECT c FROM Produit c WHERE LOWER(TRIM(c.nom)) = LOWER(TRIM(:nomproduit))";

	    // Issue the query and get a matching Customer
	    TypedQuery<Produit> tq = em.createQuery(query, Produit.class);
	    tq.setParameter("nomproduit", nom);

	    List<Produit> produit = null;
	    try {
	        // Get matching customer object and output
	        produit = tq.getResultList();
	    } catch (NoResultException ex) {
	        ex.printStackTrace();
	        System.out.println("Le produit n'existe pas ou une erreur est survenu");
	    } finally {
	        //em.close();
	    }
	    
	    //si aucun produit n'est trouvé
	    if (produit == null || produit.isEmpty()) {
	        System.out.println("Aucun produit trouvé");
	    //sinon afficher le produit
	    } else {
	        produit.forEach(produitS -> System.out.println("\rNom: " + produitS.getNom() + "\rPrix: "
	                + produitS.getPrix() +"€" + "\rDisponible: " + produitS.getQuantite()));
	    }
	}
	
	public void getProduitNull(String nom) {
	    EntityManager em = utils.JPA.getEntityManager();

	    // the lowercase c refers to the object
	    String query = "SELECT c FROM Produit c WHERE LOWER(TRIM(c.nom)) = LOWER(TRIM(:nomproduit))";

	    // Issue the query and get a matching Customer
	    TypedQuery<Produit> tq = em.createQuery(query, Produit.class);
	    tq.setParameter("nomproduit", nom);

	    List<Produit> produit = null;
	    try {
	        // Get matching customer object and output
	        produit = tq.getResultList();
	    } catch (NoResultException ex) {
	        ex.printStackTrace();
	        System.out.println("Le produit n'existe pas ou une erreur est survenu");
	    } finally {
	        //em.close();
	    }
	    
	    //si aucun produit n'est trouvé
	    if (produit == null || produit.isEmpty()) {
	        System.out.println("Aucun produit trouvé");
	        Main.main(null);
	    //sinon afficher le produit
	    } else {
	        produit.forEach(produitS -> System.out.println("\rNom: " + produitS.getNom() + "\rPrix: "
	                + produitS.getPrix() +"€" + "\rDisponible: " + produitS.getQuantite()));
	    }
	}
	
	
	public void getProduitParId(int idproduit) {
	    EntityManager em = utils.JPA.getEntityManager();

	    // the lowercase c refers to the object
	    String query = "SELECT p FROM Produit p WHERE p.idproduit = :idproduit";

	    // Issue the query and get a matching Customer
	    TypedQuery<Produit> tq = em.createQuery(query, Produit.class);
	    tq.setParameter("idproduit", idproduit);

	    List<Produit> produit = null;
	    try {
	        // Get matching customer object and output
	        produit = tq.getResultList();
	    } catch (NoResultException ex) {
	        ex.printStackTrace();
	        System.out.println("Le produit n'existe pas ou une erreur est survenu");
	    } finally {
	        //em.close();
	    }
	    
	    //si aucun produit n'est trouvé
	    if (produit == null || produit.isEmpty()) {
	        System.out.println("Aucun produit trouvé");
	    //sinon afficher le produit
	    } else {
	        produit.forEach(produitS -> System.out.println("\rNom: " + produitS.getNom() + "\rPrix: "
	                + produitS.getPrix() +"€" + "\rDisponible: " + produitS.getQuantite()+"\rId du produit: "+produitS.getId()));
	    }
	}
	
	public int getProduitId(String nom) {
	    EntityManager em = utils.JPA.getEntityManager();

	    // Modify the query to only select the id field of the product
	    String query = "SELECT c.id FROM Produit c WHERE LOWER(TRIM(c.nom)) = LOWER(TRIM(:nomproduit))";

	    // Issue the query and get a list of product ids
	    TypedQuery<Integer> tq = em.createQuery(query, Integer.class);
	    tq.setParameter("nomproduit", nom);

	    List<Integer> produitIds = null;
	    try {
	        // Get list of product ids
	        produitIds = tq.getResultList();
	    } catch (NoResultException ex) {
	        ex.printStackTrace();
	        System.out.println("Le produit n'existe pas ou une erreur est survenu");
	    } finally {
	        //em.close();
	    }
	    
	    // If no product was found, return -1
	    if (produitIds == null || produitIds.isEmpty()) {
	        return -1;
	    }
	    // Otherwise, return the id of the first product in the list
	    else {
	        return produitIds.get(0);
	    }
	}



	// afficher les produit disponibles
	public void getProduits() {
		EntityManager em = utils.JPA.getEntityManager();

		// the lowercase p refers to the object
		String strQuery = "SELECT p FROM Produit p WHERE p.id IS NOT NULL";

		// Issue the query and get a matching Produit
		TypedQuery<Produit> tq = em.createQuery(strQuery, Produit.class);
		List<Produit> produit;
		try {
			// Get matching Produit object and output
			produit = tq.getResultList();
			produit.forEach(prod -> System.out.println(
					"\rNom: " + prod.getNom() + "\rPrix: " + prod.getPrix() +"€"+ "\rDisponible: " + prod.getQuantite()));
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			//em.close();
		}
	}

	public void changerNom(String nom, String nvnom) {

		EntityManager em = utils.JPA.getEntityManager();
		EntityTransaction et = null;
		String query = "SELECT p FROM Produit p WHERE LOWER(TRIM(p.nom)) = LOWER(TRIM(:nom))";
		TypedQuery<Produit> tq = em.createQuery(query, Produit.class);
		Produit produit = null;
		tq.setParameter("nom", nom);

		try {
			produit = tq.getSingleResult();
			et = em.getTransaction();
			et.begin();
			produit = em.find(Produit.class, produit.getId());
			produit.setNom(nvnom);
			em.persist(produit);
			et.commit();
			System.out.println("Le nom du produit a bien été changé");
		} catch (NoResultException e) {
			System.out.println("le produit n'a pas pu etre trouver");
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		}
	}

	public void changerPrix(String nom, double nvprix) {

	    EntityManager em = utils.JPA.getEntityManager();
	    EntityTransaction et = null;
	    String query = "SELECT p FROM Produit p WHERE LOWER(TRIM(p.nom)) = LOWER(TRIM(:nom))";
	    TypedQuery<Produit> tq = em.createQuery(query, Produit.class);
	    Produit produit = null;
	    tq.setParameter("nom", nom);

	    try {
	        produit = tq.getSingleResult();
	        et = em.getTransaction();
	        et.begin();
	        produit = em.find(Produit.class, produit.getId());
	        produit.setPrix(nvprix);
	        em.persist(produit);
	        et.commit();
	        System.out.println("Le prix du produit a bien été changé");
	    } catch (NoResultException e) {
	        System.out.println("le produit n'a pas pu être trouvé");
	    } catch (Exception ex) {
	        if (et != null) {
	            et.rollback();
	        }
	        ex.printStackTrace();
	    }
	}

	
	public void changerQuantite(String nom, int nvquantite) {
	    EntityManager em = utils.JPA.getEntityManager();
	    EntityTransaction et = null;
	    String query = "SELECT p FROM Produit p WHERE LOWER(TRIM(p.nom)) = LOWER(TRIM(:nom))";
	    TypedQuery<Produit> tq = em.createQuery(query, Produit.class);
	    Produit produit = null;
	    tq.setParameter("nom", nom);

	    try {
	        produit = tq.getSingleResult();
	        et = em.getTransaction();
	        et.begin();
	        produit = em.find(Produit.class, produit.getId());
	        produit.setQuantite(nvquantite);
	        em.persist(produit);
	        et.commit();
	        System.out.println("La quantité du produit a bien été changée");
	    } catch (NoResultException e) {
	        System.out.println("le produit n'a pas pu etre trouver");
	    } catch (Exception ex) {
	        if (et != null) {
	            et.rollback();
	        }
	        ex.printStackTrace();
	    }
	}

	
	/**
	 * @param nom
	 * 
	 */
	public void supprimerProduit(String nom) {
	    EntityManager em = utils.JPA.getEntityManager();
	    EntityTransaction et = null;
	    String query = "SELECT p FROM Produit p WHERE LOWER(TRIM(p.nom)) = LOWER(TRIM(:nomproduit))";
	    TypedQuery<Produit> tq = em.createQuery(query, Produit.class);
	    Produit produit = null;
	    tq.setParameter("nomproduit", nom);

	    try {
	        produit = tq.getSingleResult();
	        et = em.getTransaction();
	        et.begin();

	        // Delete rows from the achat table that reference the produit row
	        em.createNativeQuery("DELETE FROM achat WHERE idproduit = :idproduit")
	            .setParameter("idproduit", produit.getId())
	            .executeUpdate();

	        // Delete rows from the vente table that reference the produit row
	        em.createNativeQuery("DELETE FROM vente WHERE idproduit = :idproduit")
	            .setParameter("idproduit", produit.getId())
	            .executeUpdate();

	        // Delete rows from the panier table that reference the produit row
	        em.createNativeQuery("DELETE FROM panier WHERE idproduit = :idproduit")
	            .setParameter("idproduit", produit.getId())
	            .executeUpdate();

	        // Delete the produit row
	        produit = em.find(Produit.class, produit.getId());
	        em.remove(produit);

	        et.commit();
	        System.out.println("Le produit a bien été supprimé");
	    } catch (NoResultException e) {
	        System.out.println("Ce produit n'existe pas ou ne peut pas etre trouvé");
	    } catch (Exception ex) {
	        if (et != null) {
	            et.rollback();
	        }
	        ex.printStackTrace();
	    }
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return idproduit;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.idproduit = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
