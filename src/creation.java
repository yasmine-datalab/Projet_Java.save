import java.io.Console;
import java.net.InetAddress;
import java.util.Scanner;
import java.lang.String;

public class creation {

    public static void creation_arbre(){
        Scanner sc = new Scanner(System.in);

        System.out.println("\t\t\t\t\t\t CREATION D'UN ARBRE GENEALOGIQUE");
        System.out.print("Famille: ");
        String label = sc.nextLine();


        arbre a = new arbre(label);


        System.out.println();
        System.out.println("\n");
        System.out.println("\t\t\t\t\t\t CREATION DE LA PERSONNE RACINE");
        System.out.print("nom: ");
        String nom = sc.nextLine();
        System.out.print("prenom: ");
        String prenom = sc.nextLine();
        System.out.print("Sexe: ");
        String  sexe = sc.nextLine();
        System.out.print("Annee de naissance: ");
        Long naissance = sc.nextLong();
        Personne p = new Personne(nom, prenom, a.id, sexe, naissance);
        System.out.println(p.nom+""+p.prenom);

        a.racine = p.id;
        System.out.println(a.id);
        System.out.println(a.racine);
        System.out.println(p.id);
        stockage.stockerArbre(a);
        stockage.stockerPersonne(p);
        //return a.id;
    }
    public static void creation_personne(String id){
        arbre a = stockage.recupererArbre(id);
        Personne p = stockage.recupererPersonne(a.racine);
        Scanner sc = new Scanner(System.in);
        System.out.println("\t\t\t\t\t\tCREATION D'UNE PERSONNE");
        System.out.println();
        System.out.println("Choix du parent ");

        listing.liste_personne(id);
        System.out.print("Entrer l'ID du parent : ");
        String id_p = sc.nextLine();
        System.out.print("nom: ");
        String nom = sc.nextLine();
        System.out.print("prenom: ");
        String prenom = sc.nextLine();
        System.out.print("Sexe: ");
        String  sexe = sc.nextLine();
        System.out.print("Annee de naissance: ");
        String naiss= sc.nextLine();
        while (Long.parseLong(naiss) <= p.dateNaissance){
            System.out.println("Erreur! Cette personne est plus ag??e que son anc??tre");
            System.out.print("Entrez une ann??e inf??rieure ?? "+p.dateNaissance);
            naiss = sc.nextLine();
        }
        Long naissance = Long.parseLong(naiss);
        Personne pers = new Personne(nom, prenom, id, sexe, naissance);
        stockage.stockerPersonne(pers);
        lien.pere_fils(pers,id_p);
        //System.out.println("ok");
        stockage.stockerPersonne(pers);
        //return p.id;

    }

    public static void main(String[] args) {
        stockage ss = new stockage();
        creation_arbre();


    }
}