import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class lien {


    public static void pere_fils(Personne p, String id) {
        Personne pers = stockage.recupererPersonne(id);
        LinkedList<String> liste_enfant = pers.enfants;
        for (String i : liste_enfant) {
            p.ajouterFratrie(i);
        }
        p.ajouterParent(id);
        pers.ajouterEnfant1(p.id);

        stockage.stockerPersonne(p);
        stockage.stockerPersonne(pers);
    }

    public static void fils_pere(Personne p, String id){
        p.ajouterEnfant1(id);
        Personne pers = stockage.recupererPersonne(id);
        pers.ajouterParent(p.id);
        LinkedList<String> liste_enfant = p.enfants;
        for (String i : liste_enfant){
            if(!(pers.fratrie.contains(i))){
                pers.ajouterFratrie(i);
            }
        }
        stockage.stockerPersonne(p);
        stockage.stockerPersonne(pers);
    }

    public  static void frere(Personne p, String id){
        if(!(p.fratrie.contains(id))){
            p.ajouterFratrie(id);
        }

        Personne pers = stockage.recupererPersonne(id);
        if(!(pers.fratrie.contains(p.id))){
            pers.ajouterFratrie(p.id);
        }
        stockage.stockerPersonne(p);
        stockage.stockerPersonne(pers);
    }
    public static void edit_lien(String id ){ //id arbre
        Scanner sc = new Scanner(System.in);
        listing.liste_personne(id);
        String [] type = {"1. lien pere-fils", "2. lien frere-frere"};
        for(String i: type){
            System.out.print(i+"\t");
        }
        System.out.println("");
        int c = MenuClass.choix();
        switch (c){
            case 1: {
                System.out.println("Entrer l'ID du fils: ");
                String filsid = sc.nextLine();
                System.out.println("Entrer l'ID du pere: ");
                String pereid = sc.nextLine();
                Personne pere = stockage.recupererPersonne(pereid);
                fils_pere(pere, filsid);
                break;
            }
            case 2:{
                System.out.println("Entrer l'ID de la personne 1: ");
                String frere1id = sc.nextLine();
                System.out.println("Entrer l'ID de la personne 2: ");
                String frere2id = sc.nextLine();
                Personne frere1 = stockage.recupererPersonne(frere1id);
                pere_fils(frere1, frere2id);
                break;
            }
        }


    }

}