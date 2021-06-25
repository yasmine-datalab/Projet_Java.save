import java.util.Scanner;

public class Menu {
	

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int monChoix;
	// Menu Accueil

	MenuClass.afficherAcc();
	
	   while(true)
	   {
		   stockage.initialisation();
		   monChoix = MenuClass.choix();
		   switch(monChoix)
		   {
				case 1 :{
					MenuClass.effacer();
					creation.creation_arbre();
					System.out.println(ConsoleColors.GREEN_UNDERLINED+"SUCCES"+ConsoleColors.RESET);
					MenuClass.retour_princ();
					break;
				}
				case 2 : 
				{	// Menu Modifier Arbre
					MenuClass.afficheModif();
					
				}
				
				case 3 : 
					// Menu afficher Arbre
					MenuClass.afficherCons();
					
				break;
				case 4: 
					System.exit(0);
					break;
				default :
					System.out.println("Veuillez entrez un nombre entre 1-4");	
		   }  
	   }
	   
	}
	
	}


