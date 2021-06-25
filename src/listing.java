import java.util.ArrayList;
import java.util.Scanner;

public class listing {

    public static String liste_arbre(){
        Scanner sc = new Scanner(System.in);
        ArrayList<arbre> liste = stockage.liste_arbres();
        System.out.println("\t\t\t\t Liste des Familles");
        System.out.println();
        System.out.println("==================================================================");
        for (arbre a: liste ) {
            Personne p = stockage.recupererPersonne(a.racine);

            System.out.println(a.id+"\t"+"Famille "+a.label+"\t"+p.nom+" "+p.prenom);
        }
        System.out.println("==================================================================");
        System.out.println();
        System.out.print("Entrer l'ID de la famille : ");
        return  sc.nextLine();
    }

    public  static  void liste_personne(String id){
        Scanner sc = new Scanner(System.in);
        ArrayList<Personne> liste = stockage.liste_personne(id);
        System.out.println("\t\t\t\t\t\t Liste des membres de la familles");
        System.out.println();
        System.out.println("===========================================================");
        for (Personne p: liste ) {

            System.out.println(p.id+"\t"+p.nom+" "+p.prenom+"\t"+p.dateNaissance);
        }
        System.out.println("===========================================================");

    }


    public static void main(String[] args) {
        String a = liste_arbre();
    }
}
