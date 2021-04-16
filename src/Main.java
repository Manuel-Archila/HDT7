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
        String oracion = "";
        try {
            File myObj = new File(ruta);
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()) {
                oracion = myReader.nextLine();
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Scanner separador = new Scanner(oracion);
        ArrayList<String> palabrassent = new ArrayList<>();
        while(separador.hasNext()){
            String tempy = separador.next();
            palabrassent.add(tempy);
        }

        String sentence = "";
        System.out.println("En que idioma esta el archivo leido");
        System.out.println("1.Ingles\n2.Espanol\n3.Frances");
        int idiomaorigen = 0;
        while(true){
            try{
                idiomaorigen = scan.nextInt();
                if(idiomaorigen<4 && idiomaorigen>0){
                    break;
                }else{
                    System.out.println("Ingrese una opcion valida");
                }
            }catch(Exception e){
                System.out.println("Ingrese una opcion numerica");
                scan.nextLine();
            }
        }
        System.out.println("A que idioma desea traducirlo");
        if(idiomaorigen == 1){
            System.out.println("1.Espanol\n2.Frances");
            int idioma = 0;
            while(true){
                try{
                    idioma = scan.nextInt();
                    if(idioma<3 && idioma>0){
                        break;
                    }else{
                        System.out.println("Ingrese una opcion valida");
                    }
                }catch(Exception e){
                    System.out.println("Ingrese una opcion numerica");
                    scan.nextLine();
                }
            }
            //Espanol
            if(idioma==1){
                //InOrderIterator<ComparableAssociation<String, String[]>> ordenadoigles = ingles.iterator();
                for(int i = 0; i< palabrassent.size(); i++){
                    System.out.println(ingles.size());
                    for(int j = 0; j<ingles.size(); j++){
                        InOrderIterator<ComparableAssociation<String, String[]>> ordenadoigles = ingles.iterator();
                        ComparableAssociation<String, String[]> temporal = ordenadoigles.next();
                        String key  = temporal.getKey();
                        String[] pals = temporal.getValue();
                        String palespanol = pals[0];
                        System.out.println(palabrassent.get(i));
                        if(palabrassent.get(i).compareTo(key) == 0){
                            sentence += palespanol;
                        }else{
                            sentence += " *" + palabrassent.get(i) +"* ";
                        }
                    }
                }
             //Frances
            }else{
                System.out.println("eu");
            }
        }else if(idiomaorigen == 2){
            System.out.println("1. Ingles\n2.Frances");
            int idioma = 0;
            while(true){
                try{
                    idioma = scan.nextInt();
                    if(idioma<3 && idioma>0){
                        break;
                    }else{
                        System.out.println("Ingrese una opcion valida");
                    }
                }catch(Exception e){
                    System.out.println("Ingrese una opcion numerica");
                    scan.nextLine();
                }
            }
            //Ingles
            if(idioma==1){
                System.out.println("eu");
             //Frances
            }else{
                System.out.println("eu");
            }
        }else{
            System.out.println("1. Ingles\n2.Espanol");
            int idioma = 0;
            while(true){
                try{
                    idioma = scan.nextInt();
                    if(idioma<3 && idioma>0){
                        break;
                    }else{
                        System.out.println("Ingrese una opcion valida");
                    }
                }catch(Exception e){
                    System.out.println("Ingrese una opcion numerica");
                    scan.nextLine();
                }
            }
            //Ingles
            if(idioma==1){
                System.out.println("eu");
             //Espanol
            }else{
                System.out.println("eu");
            }
        }

        System.out.println(sentence);

    }
}
