import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
public class Main{

    public static void main(String[] args){
        BinarySearchTree<ComparableAssociation<String, String[]>> espanol = new BinarySearchTree<>();
        BinarySearchTree<ComparableAssociation<String, String[]>> ingles = new BinarySearchTree<>();
        BinarySearchTree<ComparableAssociation<String, String[]>> frances = new BinarySearchTree<>();
        ArrayList<String[]> words = new ArrayList<>();
        try {
            File myObj = new File("diccionario.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] listado = data.split(",");
                words.add(listado);

            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for(String[] palabras : words){
            String ing = palabras[0];
            String esp = palabras[1];
            String fran = palabras[2];
            String[] paraeng = {palabras[1], palabras[2]};
            String[] paraesp = {palabras[0], palabras[2]};
            String[] parafran = {palabras[0], palabras[1]};

            ComparableAssociation<String, String[]>  eng = new ComparableAssociation(ing, paraeng);
            ComparableAssociation<String, String[]>  espa = new ComparableAssociation(esp, paraesp);
            ComparableAssociation<String, String[]>  franc = new ComparableAssociation(fran, parafran);

            espanol.add(espa);
            ingles.add(eng);
            frances.add(franc);
        }

    }
}
