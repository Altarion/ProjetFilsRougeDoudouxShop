package co.simplon.Doudouxshop;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table(name = "achat")
public class Achat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idachat")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idproduit")
	private Produit idproduit;

	@Column(name = "fournisseur")
	private String fournisseur;

	@Column(name = "date")
	private Calendar date;

	@Column(name = "nbrachat")
	private Integer nbrAchat;

	/**
	 * @param produit
	 * @param fournisseur
	 * @param date
	 * @param nbrAchat
	 */
	public Achat(Produit produit, String fournisseur, Calendar date, Integer nbrAchat) {
		super();
		this.idproduit = produit;
		this.fournisseur = fournisseur;
		this.date = date;
		this.nbrAchat = nbrAchat;
	}

	/**
	 * @param fournisseur
	 * @param date
	 * @param nbrAchat
	 */
	public Achat(String fournisseur, Calendar date, Integer nbrAchat) {
		super();
		this.fournisseur = fournisseur;
		this.date = date;
		this.nbrAchat = nbrAchat;
	}
	
/**
	 * 
	 */
	public Achat() {
		super();
	}

//ajouter le stock dans  la table produit
	public void ajouterStock(int idproduit, String fournisseur, int nbrachat) {

		try {
			EntityManager em = utils.JPA.getEntityManager();

			em.getTransaction().begin();

			Produit produitMaj = em.find(Produit.class, idproduit);
			produitMaj.setQuantite(produitMaj.getQuantite() + nbrachat);

			em.getTransaction().commit();
			// em.close();
		} catch (NoResultException e) {
			e.printStackTrace();
			System.out.println("Le produit n'existe pas");
		}
	}

//ajouter les valeurs a la base de donnée
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
			Achat achat = new Achat(this.idproduit, this.fournisseur, this.date, this.nbrAchat);

			// Save the customer object
			em.persist(achat);
			et.commit();
		}catch (PersistenceException e) {
			System.out.println("Le nom du fournisseur n'est pas valide");
		}catch (Exception ex) {
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

	
	
	
	// afficher l'historique des achats
	public void afficherHistorique() {
		EntityManager em = utils.JPA.getEntityManager();
    // the lowercase a refers to the Achat object, p refers to the Produit object
    String strQuery = "SELECT a, p FROM Achat a INNER JOIN Produit p ON a.idproduit = p.idproduit ORDER BY a.date DESC";

    // Issue the query and get a list of Achat objects
    TypedQuery<Object[]> tq = em.createQuery(strQuery, Object[].class);
    List<Object[]> achats;
    try {
        // Get the list of Achat objects and output the details of each purchase
        achats = tq.getResultList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        achats.forEach(achat -> {
        	Achat a = (Achat) achat[0];
        	Produit p = (Produit) achat[1];
        	System.out.println("\rNom produit: " + p.getNom() 
        			+ "\rFournisseur: " + a.getFournisseur() 
        			+ "\rNbrAchat: " + a.getNbrAchat()
        			+"\rDate: " + dateFormat.format(a.getDate().getTime()));
        });
    } catch (NoResultException ex) {
        ex.printStackTrace();
    } finally {
        //em.close();
    }
}

	

	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the produit
	 */
	public Produit getProduit() {
		return idproduit;
	}

	/**
	 * @param produit the produit to set
	 */
	public void setProduit(Produit produit) {
		this.idproduit = produit;
	}

	/**
	 * @return the fournisseur
	 */
	public String getFournisseur() {
		return fournisseur;
	}

	/**
	 * @param fournisseur the fournisseur to set
	 */
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @return the nbrAchat
	 */
	public Integer getNbrAchat() {
		return nbrAchat;
	}

	/**
	 * @param nbrAchat the nbrAchat to set
	 */
	public void setNbrAchat(Integer nbrAchat) {
		this.nbrAchat = nbrAchat;
	}

}
