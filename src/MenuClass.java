import java.io.IOException;
import java.util.Scanner;

public class MenuClass {

	static void afficherAcc()
	{
		System.out.println("\t--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t|  ****************      *****************   **                     **    *****************             **                |");
		System.out.println("\t|  **                    **                  **  *                  **    **                           **  **             |");
		System.out.println("\t|  **                    **                  **    *                **    **                          **    **            |");
		System.out.println("\t|  **                    **                  **       *             **    **                         **      **           |");
		System.out.println("\t|  **                    **                  **         *           **    **                        **         **         |");
		System.out.println("\t|  **         *******    ****************    **           *         **    ****************         ***************        |");
		System.out.println("\t|  **         *     *    **                  **             *       **    **                      **             **       |");
		System.out.println("\t|  **               *    **                  **                *    **    **                     **                **     |");
		System.out.println("\t|  **               *    **                  **                  *  **    **                    **                  **    |");
		System.out.println("\t|  *****************     ******************  **                     **    ******************   **                    **   |");
		System.out.println("\t--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t==========================================================================================================================");
		System.out.print("                       1.Creer un arbre  ");
		System.out.print("  2.Modifier un arbre  ");
		System.out.print("  3.Consulter un arbre  ");
		System.out.println("  4.Quitter  ");
		System.out.println("\t==========================================================================================================================");
	}

	// fonction choix
	static int choix()

	{
		System.out.print("Votre choix  : ");
		Scanner sc = new Scanner(System.in);

		int choix = sc.nextInt();


		return choix;
	}
	// fonction modifier
	static void afficheModif()
	{
		int cpt =1;
		int val;


		while(true)

		{	int monChoix1=0;
			if(cpt==1)
			{

				String id  = listing.liste_arbre();
				System.out.println("__________________________________________________________________________________________________________________________________");
				System.out.print("                1. Ajouter une personne");
				System.out.print(" 2. Editer un lien de parenter");
				System.out.print(" 3. Modifier une personne");
				System.out.print(" 4. Retour");
				System.out.println(" 5. Quiter");
				System.out.println("__________________________________________________________________________________________________________________________________");
				monChoix1 =  MenuClass.choix();
				cpt = cpt+1;
				switch(monChoix1)
				{
					case 1 : {

						creation.creation_personne(id);
						break;
					}
					case 2 : {
						lien.edit_lien(id);
						break;
					}

					case 3 : {
						Scanner sc = new Scanner(System.in);
						listing.liste_personne(id);
						System.out.println("Entrer l'id: ");
						String id_pers = sc.nextLine();
						Personne p = stockage.recupererPersonne(id_pers);
						System.out.println("Entrer * pour les champs qui ne changent pas.");
						System.out.println("nom: ");
						String nom = sc.nextLine();
						if(nom.equals("*")){
							nom = p.nom;
						}

						System.out.println("prenoms: ");
						String prenom = sc.nextLine();
						if(prenom.equals("*")){
							prenom = p.prenom;
						}

						System.out.println("Sexe: ");
						String sexe = sc.nextLine();
						if(sexe.equals("*")){
							sexe = p.sexe;
						}
						p.update(nom, prenom, sexe);
						stockage.stockerPersonne(p);
						break;
					}
					case 4: break;
					case 5 :
						System.exit(0);
						break;
					default:
						System.out.println("Veuillez entrez un nombre entre 1-5");
						;
				}
			}
			else
			{
				System.out.println("==========================================================================");
				System.out.print("   1- Voulez vous continuer  ");
				System.out.print("  2- Revenir menu principal  ");
				System.out.println("3- Quitter  ");
				System.out.println("===========================================================================");
				val = MenuClass.choix();

				while(val != 1 & val != 2 & val != 3) {

					System.out.println("Entrez un choix correcte");
					System.out.print("   1- Voulez vous continuer  ");
					System.out.print("  2- Revenir menu principal  ");
					System.out.println("3- Quitter  ");
					val = MenuClass.choix();
				}

				if(val==1)
				{
					cpt = 1;

				}
				if (val ==2)
				{   cpt = 1;

					MenuClass.afficherAcc();

					break;
				}
				if (val ==3)
				{
					System.exit(0);
				}

			}


		}
	}

	// fonction afficher consulter genealogie
	static void afficherCons()
	{
		int cpt =1;
		int val ;

		// ============================= Demande choix =============================================

		while(true)
		{
			int monChoix1=0 ;
			if (cpt==1)
			{
				String id  = listing.liste_arbre();
				System.out.println("__________________________________________________________________________________________________________________________________");
				System.out.println("1. Afficher Un Arbre Complet");
				System.out.println("2. Afficher descendance d'une personne");
				System.out.println("3. Consulter la fratrie");
				System.out.println("4. Consulter les cousins");
				System.out.println("5. Consulter les enfants");
				//System.out.println("6. Retour");
				System.out.println("6. Quiter");
				System.out.println("__________________________________________________________________________________________________________________________________");
				monChoix1 = MenuClass.choix();
				cpt = cpt+1;
				arbre a = stockage.recupererArbre(id);
				switch(monChoix1)
				{
					case 1 : {

						a.affichageArbre(stockage.recupererPersonne(a.racine),1);
						break;
					}
					case 2 : {
						Scanner sc = new Scanner(System.in);
						listing.liste_personne(id);
						System.out.println("Entrer l'ID :");
						String id_p = sc.nextLine();
						Personne p = stockage.recupererPersonne(id_p);
						a.affichageArbre(p,1);
						break;
					}
					case 3 : {
						Scanner sc = new Scanner(System.in);
						listing.liste_personne(id);
						System.out.println("Entrer l'id: ");
						String id_pers = sc.nextLine();
						Personne p = stockage.recupererPersonne(id_pers);
						p.afficherFratrie();
						break;
					}
					case 4: {
						Scanner sc = new Scanner(System.in);
						listing.liste_personne(id);
						System.out.println("Entrer l'id: ");
						String id_pers = sc.nextLine();
						Personne p = stockage.recupererPersonne(id_pers);
						p.afficherCousin();
						break;
					}
					case 5 : {

						Scanner sc = new Scanner(System.in);
						listing.liste_personne(id);
						System.out.println("Entrer l'id: ");
						String id_pers = sc.nextLine();
						Personne p = stockage.recupererPersonne(id_pers);
						p.afficherEnfant();
						break;
					}
					case 6 :
						System.exit(0);
						break;
					default:
						System.out.println("Choix incorrect");

				}
			}
			else
			{  System.out.println("==========================================================================");
				System.out.print("   1- Voulez vous continuer  ");
				System.out.print("  2- Revenir menu principal  ");
				System.out.println("3- Quitter  ");
				System.out.println("==========================================================================");
				val = MenuClass.choix();

				while(val != 1 & val != 2 & val != 3) {

					System.out.println("Entrez un choix correcte");
					System.out.print("   1- Voulez vous continuer  ");
					System.out.print("  2- Revenir menu principal  ");
					System.out.println("3- Quitter  ");
					val = MenuClass.choix();
				}

				if(val==1)
				{
					cpt = 1;

				}
				if (val ==2)
				{   cpt = 1;

					MenuClass.afficherAcc();

					break;
				}
				if (val ==3)
				{
					System.exit(0);
				}

			}

		}
	}

	static void retour_princ(){
		System.out.println("0. retour");
		int choix = choix();
		if(choix == 0){
			afficherAcc();
		}
	}
	/*static void effacer(){
		try {
			Runtime.getRuntime().exec("clear");
		}
		catch (Exception e){
			System.out.println("error!");
		}
	}*/
	static void effacer(){
		//Clears Screen in java
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		}
		catch(InterruptedException ex ) {}
		catch (IOException ex) {System.out.println("error!");}
	}
}
