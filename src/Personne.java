import java.io.*;
import  java.text.SimpleDateFormat;

import java.util.*;

/**
 * Classe Personne
 */
public class Personne implements Serializable{
    public String id;
    public String arbre;
    public String nom;
    public String prenom;
    public String sexe;
    public Long dateNaissance;
    public String parent;
    public LinkedList<String> enfants;
    public LinkedList<String> fratrie;

    Personne() {
        id = null;
        arbre = null;
        nom = null;
        prenom = null;
        sexe = null;
        dateNaissance = null;
        //parent = "";
        enfants = new LinkedList<>();
        fratrie = new LinkedList<>();
    }

    Personne(String nom, String prenom, String sexe,String arbre, Long dateNaissance, String parent){
        this.id = genererId();
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance ;
        this.parent = parent;
        this.arbre = arbre;
        enfants = new LinkedList<>();
        fratrie = new LinkedList<>();
    }


    Personne(String nom, String prenom, String arbre, String sexe, Long dateNaissance){

        this.id = genererId();

        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.arbre = arbre;
        this.dateNaissance = dateNaissance;
        enfants = new LinkedList<>();
        fratrie = new LinkedList<>();
        //parent="";
    }

    public void ajouterParent(String idParent){
        if(this.parent==null){
            if (dateNaissance - stockage.recupererPersonne(idParent).dateNaissance  <12){
                System.out.println("Le parent doit avoir plus de 12 ans.");
                // return -1;
            }
            this.parent = idParent;
        }
        else{
            System.out.println("Cette personne a déjà un parent");
        }
        //return 1;
    }

   
    public int ajouterEnfant1(String id){
        if (enfants.isEmpty()){
            enfants.add(id);
        }
        else {
            String aine_id = enfants.getFirst();
            Personne enfantadd = stockage.recupererPersonne(id);
            
            //Personne aine = stockage.recupererPersonne(aine_id);
            int ids = 1;
            Personne enf = stockage.recupererPersonne(enfants.getFirst());
            while ((ids < enfants.size() ) && (enfantadd.dateNaissance > enf.dateNaissance)){
                 enf = stockage.recupererPersonne(enfants.get(ids));
                 ids += 1;
            }
            
            if(enf.dateNaissance.longValue() <= enfantadd.dateNaissance.longValue()){
                enfants.add(ids,id);
            }
            else enfants.add(id);


        }
        return 1;
    }

    public int ajouterEnfant(String id){
        int reponse = 0;
        int n = this.enfants.size();
        System.out.println(n);
        if (enfants.isEmpty()){
            this.enfants.add(id);
            reponse = 1;
        }else {
           String[] ides = new String[enfants.size()];
            for (int i = 0; i < n; i++) {
                ides[i] = enfants.get(i);
            }

            Long [] annees = new Long[ides.length];
            Personne [] personnes = new Personne[ides.length];
            for(int ids=0;ids<ides.length;ids++){
                personnes[ids]=stockage.recupererPersonne(ides[ids]);
                annees[ids] = personnes[ids].dateNaissance;
            }

            List<Long> lAnnees = Arrays.asList(annees);
            List<Personne> lPersonnes = Arrays.asList(personnes);
            LinkedList<String> enfts = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                Long [] tAnnees =  (Long []) lAnnees.toArray();
                Personne [] tPersonnes = (Personne []) lPersonnes.toArray();
                int anneeMin = min(tAnnees);
                String idper = searchPersonneByAnnee(tPersonnes, anneeMin);
                enfts.add(idper);
                lAnnees.remove(i);
                lPersonnes.remove(i);
            }

            this.enfants.clear();
            this.enfants = enfts;
            reponse = 1;
        }
        return reponse;
    }

    public int min(Long[] integers){
        int minVal = Integer.MIN_VALUE;
        for (Long valeur : integers) {
            if(valeur > minVal)
                minVal = Math.toIntExact((Long) valeur);
        }
        return minVal;
    }

    public String searchPersonneByAnnee(Personne [] personnes , int annee){
        String idPers = "";
        for (Personne p : personnes) {
            if (p.dateNaissance == annee) {
                idPers = p.id;
            }
        }
        return idPers;
    }

    public void ajouterFratrie(String id){
        if (fratrie.indexOf(id)==-1) this.fratrie.add(id);
        Personne p = stockage.recupererPersonne(id);
        if (p.fratrie.indexOf(this.id)==-1) p.fratrie.add(id);
    }
    /*
    public int genererIdentifiant(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf+random.nextInt(borneSup-borneInf);
        return nb;
    }*/

    public String genererId() {
        Random rand = new Random(); //instance of random class
        int lowerbound = 100;
        int upperbound = 10000;

        String generatedString = String.valueOf(rand.nextInt(upperbound-lowerbound));
        return generatedString;
    }

    public void update(String nom, String prenom, String sexe){
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
    }

    public void afficher_personne(){

        System.out.println(id + "\t" + nom + " " + prenom + "\t" + sexe + "\t" + dateNaissance);
    }

    public  void afficherFratrie() {
        if (fratrie.size() > 0) {
            for (String frereid : fratrie) {
                Personne frere = stockage.recupererPersonne(frereid);
                frere.afficher_personne();
            }
        } else {
            System.out.println(nom + " " + prenom + " n'a pas de frères connus");
        }
    }

    public void afficherEnfant(){
        if(enfants.size()>0){
            for(String enfid : enfants){
                Personne enfant = stockage.recupererPersonne(enfid);
                enfant.afficher_personne();
            }
        }else {
            System.out.println(nom + " " + prenom + " n'a pas d'enfants connus");
        }
    }
    public  void afficherCousin(){
        Personne par = stockage.recupererPersonne(parent);
        if(par.fratrie.size() > 0){
            for(String id : par.fratrie){
                Personne f = stockage.recupererPersonne(id);
                f.afficherEnfant();
            }
        }else {
            System.out.println(nom + " " + prenom + " n'a pas de cousins+ connus");
        }

    }

    public static void main(String[] args) {
        Personne p =new Personne("yasmine", "kd","410" ,"F", Long.parseLong("1970"));
        Personne p2 = new Personne("st", "kd","410" ,"F", Long.parseLong("1996"));
        Personne p3 = new Personne("wil", "kd","410" ,"M", Long.parseLong("2001"));
        Personne p4 = new Personne("wilia", "d","410" ,"M", Long.parseLong("2015"));
        p.ajouterEnfant1(p2.id);
        p.ajouterEnfant1(p3.id);
        p.ajouterEnfant1(p4.id);


    }

}