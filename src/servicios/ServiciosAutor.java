package servicios;

import entidades.Autor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entidades.Libro;
import java.util.Scanner;

public class ServiciosAutor {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EggVideoJPA");
    EntityManager em = Persistence.createEntityManagerFactory("EggVideoJPA").createEntityManager();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public Autor crearAutor() {
        Autor autor1 = new Autor();
        System.out.println("Ingrese el Id del autor: ");
        autor1.setId(leer.nextInt());
        System.out.println("Ahora indique el nombre del autor: ");
        autor1.setNombre(leer.next());
        autor1.setAlta(true);
        em.getTransaction().begin();
        em.persist(autor1);
        em.getTransaction().commit();
        return autor1;
    }
    public Autor buscarAutorPorId() {
        System.out.println("Ingrese el Id del autor buscado: ");
        Autor autor1 = em.find(Autor.class, leer.nextInt());
        return autor1;
    }
    public void buscarAutorPorNombre(){
        System.out.println("Ingrese el nombre del autor a buscar: ");
        Autor autor1 = em.find(Autor.class,leer.next());
    }
    public void modificarAutor(){
        Autor autor1 = buscarAutorPorId();
        System.out.println("Ahora indique el nuevo nombre para el autor: ");
        autor1.setNombre(leer.next());
        em.getTransaction().begin();
        em.merge(autor1);
        em.getTransaction().commit();
    }
    public void removerAutor() {
        System.out.println("Ingrese el Id del autor a remover: ");
        Autor autor1 = em.find(Autor.class, leer.nextInt());
        em.getTransaction().begin();
        em.remove(autor1);
        em.getTransaction().commit();
    }
  
}
