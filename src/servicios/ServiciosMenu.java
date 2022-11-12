
package servicios;
import java.util.Scanner;
import servicios.ServiciosLibro;


public class ServiciosMenu {
    public void menu(){
        ServiciosLibro servLibro = new ServiciosLibro();
        ServiciosAutor servAutor = new ServiciosAutor();
        ServiciosEditorial servEditorial = new ServiciosEditorial();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        int opcion =0;
        System.out.println("Indique qué desea realizar: ");
        System.out.println("Opcion 1: alta libro");
        System.out.println("Opcion 2: consultar libro por ISBN");
        System.out.println("Opcion 3: consultar libro por titulo");
        System.out.println("Opcion 4: consultar libros disponibles");
        System.out.println("Opcion 5: modificar libro");
        System.out.println("Opcion 6: remover libro");
        System.out.println("Opcion 7: remover autor");
        System.out.println("Opcion 8: remover editorial");
        opcion=leer.nextInt();
        switch (opcion) {
            case 1:
                servLibro.crearLibro();
                break;
            case 2:
                servLibro.buscarLibroPorIsbn();
                break;
            case 3:
                System.out.println("Ingrese el titulo del libro: ");
                servLibro.buscarLibroPorTitulo(leer.next());
                break;
            case 4:
                servLibro.consultarLibros();
                break;
            case 5:
                servLibro.actualizarLibro();
                break;
            case 6:
                servLibro.removerLibro();
                break;
            case 7:
                servAutor.removerAutor();
                break;
            case 8:
                servEditorial.removerEditorial();
                break;
            default:
                System.out.println("La opcion ingresada no es valida.");
        }
    }
}
