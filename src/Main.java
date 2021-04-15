import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class Main{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        BinarySearchTree<ComparableAssociation<String, String[]>> espanol = new BinarySearchTree<>();
        BinarySearchTree<ComparableAssociation<String, String[]>> ingles = new BinarySearchTree<>();
        BinarySearchTree<ComparableAssociation<String, String[]>> frances = new BinarySearchTree<>();
        ArrayList<String[]> words = new ArrayList<>();
        System.out.println("Bienvenido, estamos procesando la informacion.");
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
            System.out.println("Ocurrio un error.");
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
        InOrderIterator<ComparableAssociation<String, String[]>> inOderIngles = ingles.iterator();
        InOrderIterator<ComparableAssociation<String, String[]>> inOderEspanol = espanol.iterator();
        InOrderIterator<ComparableAssociation<String, String[]>> inOderFrances = frances.iterator();

        System.out.println("El diccionario ordenado es:");
        for(int i = 0; i<ingles.size(); i++){
            ComparableAssociation<String, String[]> temp = inOderIngles.next();
            String palabraing = temp.getKey();
            String[] traducciones = temp.getValue();
            String palabraesp = traducciones[0];
            String palabrafran = traducciones[1];
            System.out.print("(" + palabraing + "," + palabraesp + "," + palabrafran + ") ");
        }
        System.out.println("\nIngrese la ruta del archivo que desea leer");
        String ruta = scan.nextLine();
        try {
            File myObj = new File(ruta);
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);


            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
