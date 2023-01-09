package co.simplon.Doudouxshop;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("\r\r--- Connexion ---" + "\r1. Compte employé" + "\r2. Compte client" + "\r3. Quitter");

		int selection = getSelection(1, 3);

		switch (selection) {
		case 1:// CONNEXION EMPLOYE
			/*
			 * achat // produit //
			 */

			System.out.println(
					"Que voulez vous faire?" + "\r1. Afficher les produit disponible" + "\r2. Rechercher un produit"
							+ "\r3. Ajouter un produit" + "\r4. Modifier un produit" + "\r5. Supprimer un produit"
							+ "\r6. Ajouter du stock" + "\r7. Afficher l'historique des livraisons de stock "
							+ "\r8. Retour au menu de connexion" + "\r9. Quitter");

			selection = getSelection(1, 9);

			switch (selection) {

			case 1:// Afficher les produit disponible // marche

				Produit list = new Produit();
				list.getProduits();
				main(null);
				break;

			case 2:// chercher un produit en particulier // marche

				System.out.println("\rQuel produit rechercher vous ?");
				Produit prod = new Produit();
				String nomprod = scan.nextLine();
				prod.getProduit(nomprod);
				main(null);
				break;

			case 3:// Ajouter un produit // marche

				System.out.println("Nom du produit: ");
				String nom = scan.nextLine();

				double prix = 0;
				while (prix == 0) {
					try {
						System.out.print("\rLe prix: ");
						prix = scan.nextDouble();
						scan.nextLine();
					} catch (InputMismatchException e) {
						System.out.println("Entrée invalide , entrez un nombre ex(1,99)");
						scan.nextLine();
					}
				}

				int quantite = 0;
				while (quantite == 0) {
					try {
						System.out.print("\rQuantité disponible: ");
						quantite = scan.nextInt();
						scan.nextLine();
					} catch (InputMismatchException e) {
						System.out.println("Entrée invalide , entrez un nombre");
						scan.nextLine();
					}
				}

				Produit produit = new Produit(nom, prix, quantite);
				produit.ajoutProduit();
				main(null);
				break;

			case 4:// modifier un produit // marche

				System.out.println("Nom du produit: ");
				String nommodif = scan.nextLine();

				Produit modif = new Produit();
				modif.getProduitNull(nommodif);

				System.out.println("\rQue voulez vous modifier ?" + "\r1. Le nom du produit" + "\r2. Le prix du produit"
						+ "\r3. La quantité disponible"+"\r4.Retourner au menu");

				selection = getSelection(1, 4);

				switch (selection) {
				case 1:
					System.out.println("Nouveau nom du produit: ");
					String nvnom = scan.nextLine();

					modif.changerNom(nommodif, nvnom);
					break;

				case 2:

					double nvprix = 0;
					while (nvprix == 0) {
						try {
							System.out.print("Nouveau prix du produit:");
							nvprix = scan.nextDouble();
							scan.nextLine();
						} catch (InputMismatchException e) {
							System.out.println("Entrée invalide , entrez un nombre ex(1,99)");
							scan.nextLine();
						}
					}
					modif.changerPrix(nommodif, nvprix);
					break;
				case 3:

					int quant = getSelectionMin(0, "Nouvelle quantité du produit:");
					modif.changerQuantite(nommodif, quant);
					
					break;
				case 4:
					main(null);
					break;
				}

				main(null);
				break;

			case 5:// Supprimer un produit // marche

				System.out.println("Quel produit souhaitez vous supprimer ?");
				String nomsup = scan.nextLine();

				Produit sup = new Produit();
				sup.supprimerProduit(nomsup);
				main(null);
				break;

			case 6:// Ajouter du stock à un produit // marche

				System.out.println("Ajouter du stock :" + "\r\r1. Par nom" + "\r2. Par Id" + "\r3. Annuler");

				selection = getSelection(1, 3);

				Produit prodId = new Produit();
				int idproduit = 0;
				switch (selection) {
				case 1:
					System.out.println("Entrez le nom du produit: ");
					String nomproduit = scan.nextLine();
					idproduit = prodId.getProduitId(nomproduit);
					break;

				case 2:
					idproduit = getSelectionMin(1, "Entrez l'id du produit");
					break;
				case 3:

					System.out.println("OK! Retour au menu");
					main(null);
					break;
				}

				Calendar date = Calendar.getInstance();
				prodId.setId(idproduit);

				System.out.println("Entrez le nom du fournisseur du produit");
				String nomfour = scan.nextLine();

				int nbrprod = getSelectionMin(1, "Entrez le nombre de produit livré");

				try {
					Achat achat = new Achat(prodId, nomfour, date, nbrprod);

					achat.ajouterStock(idproduit, nomfour, nbrprod);

					achat.ajoutProduit();
					System.out.println("Le stock a été ajouté");
				} catch (NullPointerException e) {
					System.out.println("Vous essayer d'ajouter du stock à un produit qui n'existe pas ou plus \n"
							+ "RETOUR AU MENU");
				}
				main(null);
				break;

			case 7:// Afficher l'historique des achat // marche
				Achat achat = new Achat();
				achat.afficherHistorique();

				main(null);
				break;

			case 8:// Retour au menu de connexion

				main(null);
				break;

			case 9:// Quitter

				System.out.println("Au revoir !");
				System.exit(0);
				main(null);
				break;

			}

			break;

		case 2:// CONNEXION CLIENT
			/*
			 * vente // produit // panier
			 */

			System.out.println(
					"Que voulez vous faire?" + "\r1. Afficher les produit disponible" + "\r2. Rechercher un produit"
							+ "\r3. Ajouter un produit au panier" + "\r4. Supprimer un produit du panier"
							+ "\r5. Afficher le panier" + "\r6. Supprimer le contenu du panier" + "\r7. Passer commande"
							+ "\r8. Retour au menu de connexion" + "\r9. Quitter");

			selection = getSelection(1, 9);

			switch (selection) {

			case 1:

				// afficher la liste de tout les produit // marche
				Produit list = new Produit();
				list.getProduits();
				main(null);
				break;

			case 2:

				// rechercher un produit en particulier // marche
				System.out.println("\rQuel produit rechercher vous ?");
				Produit prod = new Produit();
				String nomprod = scan.nextLine();
				prod.getProduit(nomprod);
				main(null);
				break;

			case 3:

				// ajouter un produit au panier // marche
				Produit panierprod = new Produit();
				panierprod.getProduits();
				System.out.println("\rQuel produit voulez vous ajouter au panier ?");
				String nompanierprod = scan.nextLine();
				panierprod.getProduitNull(nompanierprod);

				System.out.println("\rConfirmer ajout au panier" + "\r1. Oui" + "\r2. Non");

				selection = getSelection(1, 2);

				if (selection == 1) {
					Produit cartprod = new Produit();
					cartprod.getProduit(nompanierprod);

					Panier cart = new Panier();
					// récuperer l'id du produit
					cartprod.setId(cartprod.getProduitId(nompanierprod));
					// Ajouter le produit au panier
					cart.ajouterPanier(cartprod);
				}
				System.out.println("Retour au menu");
				main(null);

				break;

			case 4:

				// supprimer un produit du panier //marche
				System.out.println("Supprimer :" + "\r\r1. Par nom" + "\r2. Par Id" + "\r3. Annuler");

				selection = getSelection(1, 3);

				Panier supppanier = new Panier();
				Produit suppprod = new Produit();
				switch (selection) {
				case 1:// Supprimer par nom //marche plus
					supppanier.AfficherPanier();
					System.out.println("\rEntrez le nom de l'article que vous voulez supprimer");
					String nomsupp = scan.nextLine();
					supppanier.getPanier(nomsupp);
					System.out.println("\rSupprimer cet article du panier ?" + "\r1. Oui" + "\r2. Non");

					selection = getSelection(1, 2);

					switch (selection) {
					case 1:
						int id = suppprod.getProduitId(nomsupp);
						supppanier.supprimerArticle(id);
						main(null);
						break;

					case 2:// Retour menu
						System.out.println("OK! Retour au menu");
						main(null);
						break;
					}

					break;
				case 2:// Supprimer par ID //marche
					supppanier.AfficherPanierAvecId();
					selection = getSelectionMin(1, "\rEntrez l'Id de l'article que vous voulez supprimer");
					int idsupp = selection;
					suppprod.getProduitParId(idsupp);
					System.out.println("\rSupprimer cet article du panier ?" + "\r1. Oui" + "\r2. Non");

					selection = getSelection(1, 2);
					switch (selection) {
					case 1:
						supppanier.supprimerArticle(idsupp);
						main(null);
						break;

					case 2:// Retour menu
						System.out.println("OK! Retour au menu");
						main(null);
						break;
					}
					break;
				case 3:// Annuler
					System.out.println("OK! Retour au menu");
					main(null);
					break;

				}
				break;

			case 5:

				// afficher le panier // à corriger
				Panier afficherpanier = new Panier();
				afficherpanier.AfficherPanier();
				main(null);

				break;
			case 6:

				// Supprimer le contenu du panier
				System.out.println("Supprimer le contenu du panier ?" + "\r1. Oui" + "\r2. Non");

				selection = getSelection(1, 2);

				if (selection == 1) {
					Panier panier = new Panier();
					panier.viderPanier();
				}

				System.out.println("Retour au menu");
				main(null);

				break;

			case 7:

				// passer commande //marche
				System.out.println("Passer commande ?"
						+ "\r1. Oui"
						+ "\r2. Non");
				selection = getSelection(1, 2);
				if (selection == 2 ) {
					main(null);
				}
				Panier cmdpanier = new Panier();
				cmdpanier.AfficherPanierMenu();
				Commande cmd = new Commande();
				cmd.passerCommande();
				main(null);

				break;
			case 8:// Retour au menu de connexion

				main(null);
				break;

			case 9:// Quitter

				System.out.println("Au revoir !");
				System.exit(0);
				main(null);
				break;
			}

			break;

		case 3:

			System.out.println("Au revoir !");
			// quitter
			System.exit(0);
			break;
		}

		scan.close();
	}

	public static int getSelection(int min, int max) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		int selection = 0;
		while (selection == 0 || selection < min || selection > max) {
			try {
				System.out.print("\rIndiquez votre choix : ");
				selection = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Entrée invalide , entrez un chiffre entre " + min + " et " + max);
				scan.nextLine();
			}
		}

		return selection;
	}

	public static int getSelectionMin(int min, String message) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		int selection = 0;
		while (selection == 0 || selection < min) {
			try {
				System.out.print(message);
				selection = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Entrée invalide , entrez un chiffre");
				scan.nextLine();
			}
		}

		return selection;
	}

}
