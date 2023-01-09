package co.simplon.Doudouxshop;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "vente")
public class Vente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idproduit")
	private Produit idproduit;

	@Column(name = "dateachat")
	@Temporal(TemporalType.DATE)
	private Date dateachat;

	/**
	 * @param idproduit
	 * @param dateachat
	 */
	public Vente(Produit idproduit, Date dateachat) {
		super();
		this.idproduit = idproduit;
		this.dateachat = dateachat;
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

	/**
	 * @return the dateachat
	 */
	public Date getDateachat() {
		return dateachat;
	}

	/**
	 * @param dateachat the dateachat to set
	 */
	public void setDateachat(Date dateachat) {
		this.dateachat = dateachat;
	}

}
