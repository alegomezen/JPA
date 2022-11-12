
package servicios;
import entidades.Editorial;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import entidades.Libro;

public class ServiciosEditorial {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EggVideoJPA");
    EntityManager em = Persistence.createEntityManagerFactory("EggVideoJPA").createEntityManager();
    
    public Editorial crearEditorial() {
        Editorial editorial1 = new Editorial();
        System.out.println("Ingrese el Id de la editorial: ");
        editorial1.setId(leer.nextInt());
        System.out.println("Indique el nombre de la editorial: ");
        editorial1.setNombre(leer.next());
        editorial1.setAlta(true);
        em.getTransaction().begin();
        em.persist(editorial1);
        em.getTransaction().commit();
        return editorial1;
    }
    public Editorial buscarEditorialPorId() {
        System.out.println("Ingrese el ID de la editorial buscada: ");
        Editorial editorial1 = em.find(Editorial.class, leer.nextInt());
        return editorial1;
    }
    public void buscarEditorialPorNombre(){
        System.out.println("Ingrese el nombre de la editorial buscada: ");
        Editorial editorial1 = em.find(Editorial.class,leer.next());
    }
    public void actualizarEditorial() {
        Editorial editorial1 = buscarEditorialPorId();
        System.out.println("Ahora, ingrese el nombre a modificar: ");
        editorial1.setNombre(leer.next());
        em.getTransaction().begin();
        em.merge(editorial1);
        em.getTransaction().commit();
    }
    public void removerEditorial() {
        System.out.println("Ingrese el Id de la editorial a remover: ");
        Editorial editorial1 = em.find(Editorial.class, leer.nextInt());
        em.getTransaction().begin();
        em.remove(editorial1);
        em.getTransaction().commit();
    }

}
