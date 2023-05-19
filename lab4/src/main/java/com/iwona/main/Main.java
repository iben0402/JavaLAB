package com.iwona.main;

import com.iwona.mage.Mage;
import com.iwona.tower.Tower;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void addMage(EntityManagerFactory emf, String name, int level, Tower tower) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Mage(name, level, tower));
        em.getTransaction().commit();
        em.close();
    }

    public static void addTower(EntityManagerFactory emf, String name, int height) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Tower(name, height));
        em.getTransaction().commit();
        em.close();
    }

    public static void printTowers(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.createQuery("SELECT t FROM Tower t", Tower.class).getResultList().forEach(System.out::println);
        em.close();
    }

    public static void printMages(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.createQuery("SELECT m FROM Mage m", Mage.class).getResultList().forEach(System.out::println);
        em.close();
    }

    public static void printBoth(EntityManagerFactory emf) {
        printTowers(emf);
        printMages(emf);
    }

    public static void printAll(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        List<Tower> towers = em.createQuery("SELECT t FROM Tower t", Tower.class).getResultList();
        List<Mage> mages = em.createQuery("SELECT m FROM Mage m", Mage.class).getResultList();
        em.getTransaction().commit();

        for (Tower t : towers) {
            System.out.println("Tower: " + t.getName() + ", Height: " + t.getHeight());
            System.out.println("Mages in the tower:");
            for (Mage m : t.getMages()) {
                System.out.println("- Mage: " + m.getName() + ", Level: " + m.getLevel());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

        addTower(emf, "Tower of the Moon", 100);
        addTower(emf, "Tower of the Sun", 200);
        addTower(emf, "Tower of the Stars", 300);
        addTower(emf, "Tower of the Sky", 420);

        addMage(emf, "Mage of the Moon", 1, emf.createEntityManager().find(Tower.class, "Tower of the Moon"));
        addMage(emf, "Mage of the Sun", 2, emf.createEntityManager().find(Tower.class, "Tower of the Sun"));
        addMage(emf, "Mage of the Stars", 3, emf.createEntityManager().find(Tower.class, "Tower of the Stars"));
        addMage(emf, "Mage of the Sky", 4, emf.createEntityManager().find(Tower.class, "Tower of the Sky"));

        printAll(emf);

        emf.close();


    }
}
