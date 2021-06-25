import java.io.*;
import java.io.File;
import java.util.*;

/**
 * Classe Stockage
 */
public class stockage {

        public static void initialisation() {
        File rep = new File("data/arbres");
        File rep1 = new File("data/personnes");
        File dir = new File("data");
        if(!(dir.exists())){
            dir.mkdir();
            }
        if(!(rep.exists())) {
            rep.mkdir();
        }
        if(!(rep1.exists())) {
            rep1.mkdir();
        }

    }
    public static String search_file(String id){
        File dir = new File("data/personnes") ;
        String [] liste =  dir.list();
        String path="";
        for(String c:liste){
            if(c.contains("."+id+".dat")){
                path = "data/personnes/"+c;

            }
        }
        return path;
    }
    public static ArrayList<String> search_file_pers(String id){
        File dir = new File("data/personnes") ;
        String [] liste =  dir.list();

        ArrayList<String> l_path= new ArrayList<>();
        for(String c:liste){
            if(c.contains(id+".")){
                l_path.add("data/personnes/"+c) ;


            }
        }
        return l_path;
    }
    //lever les erreurs
    public static int stockerArbre(arbre a)  {
        try{
            String filename = "data/arbres/"+a.id+".dat";
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(a);
            oos.close();
            fos.close();
        }
        catch (Exception e){
            System.out.println("error!");
        }
     return 1;
    }
    public static int stockerPersonne(Personne personne) {
        try {
            String filename = "data/personnes/"+personne.arbre+"."+personne.id + ".dat";

            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(personne);

            fos.close();
            oos.close();

            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public static Personne recupererPersonne(String id) {

        String filename = search_file(id);
        Personne personne = new Personne();
        if (!(filename.equals(""))) {
            try {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                personne = ((Personne) ois.readObject());
                fis.close();
                ois.close();

            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        }
        return personne;
        }

    public static arbre recupererArbre(String id) {

        String filename = "data/arbres/"+id+".dat";
        arbre a = new arbre();
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            a = ((arbre) ois.readObject());
            fis.close();
            ois.close();

            } catch (Exception e) {
                System.out.println(e);

            }

        return a;
    }

    public int stockerPersonnes(Personne [] personnes) {
        int confirmation = 1;

        for (Personne personne : personnes) {
            int confirmation_locale = stockerPersonne(personne);

            if (confirmation_locale == 0) {
                confirmation = confirmation_locale;
                break;
            }
        }

        return confirmation;
    }

    public static Personne [] recupererPersonnes(List<String> ids) {
        Personne [] personnes = new Personne[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            Personne personne = recupererPersonne(ids.get(i));
            personnes[i] = personne;
        }
        return personnes;
    }
    public static ArrayList<arbre> liste_arbres(){
        ArrayList<arbre> liste = new ArrayList<>();
        File rep = new File("data/arbres");
        for(File fichier : rep.listFiles()){
            try {
                FileInputStream fich = new FileInputStream(fichier);
                ObjectInputStream ois = new ObjectInputStream(fich);
                liste.add((arbre)ois.readObject());
            }
            catch (Exception e){ System.out.println("error!");}

        }
        return liste;
    }

    public static ArrayList<Personne> liste_personne(String id){
        ArrayList<Personne> liste = new ArrayList<>();

        for(String path : search_file_pers(id)){
            try {
                FileInputStream fich = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fich);
                liste.add((Personne) ois.readObject());
            }
            catch (Exception e){ System.out.println("error!");}

        }
        return liste;
    }



    public static void main(String[] args) {
        //System.out.println(search_file("699"));
        Personne p = recupererPersonne("8859");
        System.out.println(stockage.recupererPersonne(p.fratrie.getFirst()).prenom);

    }
}

