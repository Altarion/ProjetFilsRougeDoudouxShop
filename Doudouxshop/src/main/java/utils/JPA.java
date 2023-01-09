package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Cette classe fournit une EntityManagerFactory et un EntityManager pour une application Java Persistence API (JPA).
 */
public class JPA {
  /**
   * Le nom de l'unité de persistance.
   */
  private static final String PERSISTENCE_UNIT_NAME = "Doudouxshop";
  
  /**
   * L'EntityManagerFactory responsable de la création d'instances EntityManager.
   */
  private static EntityManagerFactory factory;
  
  /**
   * L'EntityManager utilisé pour interagir avec le contexte de persistance d'une base de données.
   */
  private static EntityManager em;

  /**
   * Renvoie l'EntityManagerFactory.
   * Si l'EntityManagerFactory n'a pas été créé, il sera créé en utilisant la méthode `Persistence.createEntityManagerFactory`.
   * 
   * @return l'EntityManagerFactory
   */
  static EntityManagerFactory getEntityManagerFactory() {
    if (factory == null) {
      factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    return factory;
  }

  /**
   * Ferme l'EntityManagerFactory s'il n'est pas nul.
   */
  public static void closeFactory() {
    if (factory != null) {
      factory.close();
    }
  }

  /**
   * Renvoie l'EntityManager.
   * Si l'EntityManager n'a pas été créé, il sera créé en utilisant l'EntityManagerFactory.
   * 
   * @return l'EntityManager
   */
  public static EntityManager getEntityManager() {
    if (em == null) {
      factory = getEntityManagerFactory();
      em = factory.createEntityManager();
    }
    return em;
  }

  /**
   * Ferme l'EntityManager s'il n'est pas nul.
   */
  public static void shutdownEntityManager() {
    if (em != null) {
      em.close();
    }
  }
}
