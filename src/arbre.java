import java.io.Serializable;
import java.util.*;
public class arbre implements Serializable {

    public String id;
    public String label;
    public String racine;


    arbre(){}
    arbre(String  label, String racine){
        id = genererId();
        this.label = label;
        this.racine = racine;

    }
    arbre(String label){
        id = genererId();
        this.label = label;
    }


    // fonction de genération d'un code unique pour l'arbre
    //public String genererId(int binf, int bsup){
      //  Random random = new Random();
      //  id = String.valueOf(random.nextInt(bsup-binf));
      //  return id;
    //}
    public String genererId() {
        Random rand = new Random(); //instance of random class
        int lowerbound = 100;
        int upperbound = 10000;

        String generatedString = String.valueOf(rand.nextInt(upperbound - lowerbound)+1);
        double double_random=rand.nextDouble();
        float float_random=rand.nextFloat();


        return generatedString;
    }
    public void affichageArbre(Personne p, int n){
        p.afficher_personne();
       if(p.enfants.size()>0){
           Personne personnes[] = stockage.recupererPersonnes(p.enfants);


           for (Personne pers : personnes){
               for (int i=0;i<n;i++){
                   System.out.print("\t\t");
               }
               affichageArbre(pers,n+1);
           }
       }
    }
    public void affichageAbreAsc(Personne p, int n){

        Stack<Personne> pile = new Stack<>();
         while (p.parent != null){

             pile.add(stockage.recupererPersonne(p.parent));

         }
         for (Personne pers:pile){
             for(int i=0;i<pile.size();i++){
                 System.out.println("\t\t"+pers.id+"\t"+pers.nom+" "+pers.prenom+"\t"+pers.dateNaissance);
             }
         }

    }


    public static void main(String[] args) {

    }

}
