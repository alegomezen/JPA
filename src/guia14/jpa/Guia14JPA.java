package guia14.jpa;

import servicios.ServiciosLibro;
import servicios.ServiciosMenu;
import entidades.Autor;
import java.util.Scanner;
public class Guia14JPA {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        ServiciosMenu servMenu = new ServiciosMenu();
        servMenu.menu();
        //ServiciosLibro servLibro = new ServiciosLibro();
        //servLibro.crearLibro();
        //servLibro.consultarLibros();
        //servLibro.buscarLibroPorTitulo(leer.next());
        //servLibro.buscarLibroPorEditorial(123456654);
        //servLibro.removerLibro();
        //servLibro.actualizarLibro();
        //servLibro.crearLibros();
    }

}
