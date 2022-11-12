package servicios;

import entidades.Autor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entidades.Libro;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import servicios.ServiciosAutor;
import servicios.ServiciosEditorial;
import java.util.Scanner;

public class ServiciosLibro {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EggVideoJPA");
    EntityManager em = Persistence.createEntityManagerFactory("EggVideoJPA").createEntityManager();
    ServiciosAutor servAutor = new ServiciosAutor();
    ServiciosEditorial servEditorial = new ServiciosEditorial();

    public Libro crearLibro() {
        Libro libro1 = new Libro();
        try {
            System.out.println("Ingrese el ISBN del libro: ");
            libro1.setIsbn(leer.nextLong());
            System.out.println("Ingrese el titulo del libro: ");
            libro1.setTitulo(leer.next());
            System.out.println("Ingrese el año de publicacion del libro: ");
            libro1.setAnio(leer.nextInt());
            System.out.println("*****      *****");
            libro1.setAutor(servAutor.crearAutor());
            System.out.println("Introduzca el numero de ejemplares disponibles: ");
            libro1.setEjemplares(leer.nextInt());
            System.out.println("Indique cuantas unidades fueron prestadas: ");
            libro1.setEjemplaresPrestados(leer.nextInt());
            libro1.setEjemplaresRestantes(libro1.getEjemplares() - libro1.getEjemplaresPrestados());
            System.out.println("*****      *****");
            libro1.setEditorial(servEditorial.crearEditorial());
            libro1.setAlta(true);
            System.out.println("Datos del libro: " + libro1.toString());
            em.getTransaction().begin();
            em.persist(libro1);
            em.getTransaction().commit();

        } catch (InputMismatchException e) {
            System.out.println("Ha ocurrido un error. Revise los datos ingresados.");
        }
        return libro1;
    }

    public Collection buscarLibroPorTitulo(String titulo) {
        Collection<Libro> libros = em.createQuery("SELECT l "
                + " FROM Libro l "
                + " WHERE l.titulo = :titulo").
                setParameter("titulo", titulo).
                getResultList();
        return libros;
    }

    public Libro buscarLibroPorIsbn() {
        System.out.println("Ingrese el Isbn del libro a buscar: ");
        Libro libro1 = em.find(Libro.class, leer.nextLong());
        return libro1;
    }

    public String buscarLibroPorEditorial(int id) {
        Collection<Libro> libro = em.createQuery("Select l "
                + "FROM Libro l "
                + "WHERE l.editorial = (SELECT e from Editorial e WHERE e.id= :id").
                setParameter("id", id).
                getResultList();
        String libro1 = "";
        if (!libro.isEmpty()) {
            for (Libro l : libro) {
                libro1 = l.toString();
            }
        } else {
            libro1 = "No figuran libros de esa editorial";
        }
        em.close();
        return libro1;
    }

    public void actualizarLibro() {
        int opcion = 0;
        Libro libro1 = buscarLibroPorIsbn();
        System.out.println("Para modificar el titulo del libro presione 1");
        System.out.println("Para modificar el Isbn del libro presione 2");
        System.out.println("Para modificar el año de publicacion del libro presione 3");
        System.out.println("Para modificar el numero de ejemplares del libro presione 4");
        System.out.println("Para modificar el numero de ejemplares prestados del libro presione 5");
        System.out.println("Para modificar el autor del libro presione 6");
        System.out.println("Para modificar la editorial del libro presione 7");
        opcion = leer.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nuevo titulo para el libro: ");
                libro1.setTitulo(leer.next());
                break;
            case 2:
                System.out.println("Ingrese el nuevo Isbn para el libro: ");
                libro1.setIsbn(leer.nextLong());
                break;
            case 3:
                System.out.println("Ingrese el nuevo año de publicacion para el libro: ");
                libro1.setAnio(leer.nextInt());
                break;
            case 4:
                System.out.println("Ingrese el numero de ejemplares del libro: ");
                libro1.setEjemplares(leer.nextInt());
                break;
            case 5:
                System.out.println("Ingrese el numero de ejemplares prestados: ");
                libro1.setEjemplaresPrestados(leer.nextInt());
                break;
            case 6:
                System.out.println("Ingrese nuevamente los datos del autor: ");
                servAutor.crearAutor();
                break;
            case 7:
                System.out.println("Ingrese nuevamente los datos de la editorial: ");
                servEditorial.crearEditorial();
                break;
            default:
                System.out.println("El numero ingresado no corresponde a ninguna modificacion posible.");
        }
        em.getTransaction().begin();
        em.merge(libro1);
        em.getTransaction().commit();
    }

    public void consultarLibros() {
        List<Libro> libros = em.createQuery("SELECT a FROM Libro a").getResultList();
        System.out.println("Los libros cargados son los siguientes: ");
        libros.forEach((libro) -> {
            System.out.println(libro);
        });
    }

    public void removerLibro() {
        System.out.println("Ingrese el Isbn del libro a remover: ");
        Libro libro1 = em.find(Libro.class, leer.nextLong());
        em.getTransaction().begin();
        em.remove(libro1);
        em.getTransaction().commit();
    }
/*
    public Libro crearLibros() {
        int opcion = 0;
        Libro libro1 = new Libro();
        
        do {
            System.out.println("Para dar de alta un ejemplar presione 1: ");
            System.out.println("Si no desea agregar nuevos libros presione 2: ");
            opcion = leer.nextInt();
            try {
                System.out.println("Ingrese el ISBN del libro: ");
                libro1.setIsbn(leer.nextLong());
                System.out.println("Ingrese el titulo del libro: ");
                libro1.setTitulo(leer.next());
                System.out.println("Ingrese el año de publicacion del libro: ");
                libro1.setAnio(leer.nextInt());
                System.out.println("*****      *****");
                libro1.setAutor(servAutor.crearAutor());
                System.out.println("Introduzca el numero de ejemplares disponibles: ");
                libro1.setEjemplares(leer.nextInt());
                System.out.println("Indique cuantas unidades fueron prestadas: ");
                libro1.setEjemplaresPrestados(leer.nextInt());
                libro1.setEjemplaresRestantes(libro1.getEjemplares() - libro1.getEjemplaresPrestados());
                System.out.println("*****      *****");
                libro1.setEditorial(servEditorial.crearEditorial());
                libro1.setAlta(true);
                System.out.println("Datos del libro: " + libro1.toString());

            } catch (InputMismatchException e) {
                System.out.println("Ha ocurrido un error. Revise los datos ingresados.");
            }
        }while(opcion==1);
        em.getTransaction().begin();
        em.persist(libro1);
        em.getTransaction().commit();
        return libro1;

    }
*/
}
