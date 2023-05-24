package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void addBrowar(EntityManagerFactory emf, String name, long wartosc) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Piwo> piwa = new ArrayList<>();
        Browar browar = new Browar(name, wartosc, piwa);
        em.persist(browar);
        em.getTransaction().commit();
        em.close();
    }

    public static void addPiwo(EntityManagerFactory emf, String name, long cena, String nazwaBrowaru) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Browar browar = em.find(Browar.class, nazwaBrowaru);
        Piwo piwo = new Piwo(name, cena, browar);
        em.persist(piwo);
        em.getTransaction().commit();
        em.close();
    }

    public static void deleteBrowar(EntityManagerFactory emf, String name) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Browar.deleteName");
        query.setParameter("name", name);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public static void deleteAll(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Browar> browary = em.createQuery("SELECT b FROM Browar b", Browar.class).getResultList();
        List<Piwo> piwa = em.createQuery("SELECT m FROM Piwo m", Piwo.class).getResultList();
        for (Piwo p :
                piwa) {
            em.remove(p);
        }

        for (Browar b:
             browary) {
            em.remove(b);
        }

        em.getTransaction().commit();
        em.close();
    }

    public static void deletePiwo(EntityManagerFactory emf, String name) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Piwo piwo = em.find(Piwo.class, name);
        em.remove(piwo);
        em.getTransaction().commit();
        em.close();
    }

    public static void printAll(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Browar> browary = em.createQuery("SELECT b FROM Browar b", Browar.class).getResultList();
        // List<Piwo> piwa = em.createQuery("SELECT m FROM Piwo m", Piwo.class).getResultList();


        for (Browar b : browary) {
            System.out.println("Browar: " + b.getName() + ", wartosc:  " + b.getWartosc());
            System.out.println("Piwa w browarze: ");
            for (Piwo p : b.getPiwa()) {
                System.out.println("- Piwo: " + p.getName() + ", cena: " + p.getCena());
            }
            System.out.println();
        }
        em.close();
    }

    public static void printBrowaryZPiwamiTanszymiNiz(EntityManagerFactory emf, int cena) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Browar.piwaTanszeNiz");
        query.setParameter("cena", cena);
        List<Browar> browary = query.getResultList();
        System.out.println("Browary z piwami tanszymi niz : " + cena);
        for (Browar b :
                browary) {
            System.out.println(b.getName());
        }
    }

    public static void printPiwaTanszeNiz(EntityManagerFactory emf, int cena) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Piwo.tanszeNiz");
        query.setParameter("cena", cena);
        List<Piwo> piwa = query.getResultList();
        System.out.println("Piwa tansze niz : " + cena);
        for (Piwo p :
                piwa) {
            System.out.println(p.getName());
        }
    }

    public static void printPiwaZBrowaruDrozszeNiz(EntityManagerFactory emf, String nazwaBrowaru, int cena) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Piwo.piwaZBrowaruZCenaWiekszaNiz");
        query.setParameter("cena", cena);
        query.setParameter("name", nazwaBrowaru);
        List<Piwo> piwa = query.getResultList();
        System.out.println("Piwa z browaru " + nazwaBrowaru + " drozsze niz : " + cena);
        for (Piwo p :
                piwa) {
            System.out.println(p.getName());
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgPu");
        addBrowar(emf, "Browar1", 1000);
        addBrowar(emf, "Browar2", 2000);
        addBrowar(emf, "Browar3", 3000);

        addPiwo(emf, "Piwo1", 10, "Browar1");
        addPiwo(emf, "Piwo2", 20, "Browar1");
        addPiwo(emf, "Piwo3", 30, "Browar2");
        addPiwo(emf, "Piwo4", 40, "Browar2");
        addPiwo(emf, "Piwo5", 50, "Browar3");
        addPiwo(emf, "Piwo6", 60, "Browar3");

        printAll(emf);

        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Enter Command:");
            String command = input.nextLine();

            if (command.equals("exit")) {
                break;
            } else if (command.equals("addPiwo")) {
                System.out.println("Enter name:");
                String name = input.nextLine();
                System.out.println("Enter cena:");
                int cena = input.nextInt();
                input.nextLine();
                System.out.println("Enter browar:");
                String browar = input.nextLine();
                addPiwo(emf, name, cena, browar);
            } else if (command.equals("addBrowar")) {
                System.out.println("Enter name:");
                String name = input.nextLine();
                System.out.println("Enter wartosc:");
                int wartosc = input.nextInt();
                input.nextLine();
                addBrowar(emf, name, wartosc);
            } else if (command.equals("deletePiwo")) {
                System.out.println("Enter name:");
                String name = input.nextLine();
                deletePiwo(emf, name);
            } else if (command.equals("deleteBrowar")) {
                System.out.println("Enter name:");
                String name = input.nextLine();
                deleteBrowar(emf, name);
            } else if (command.equals("deleteAll")) {
                deleteAll(emf);
            } else if (command.equals("printAll")) {
                printAll(emf);
            } else if (command.equals("printBrowaryZPiwamiTanszymiNiz")) {
                System.out.println("Enter cena:");
                int cena = input.nextInt();
                input.nextLine();

                printBrowaryZPiwamiTanszymiNiz(emf, cena);
            } else if (command.equals("printPiwaTanszeNiz")) {
                System.out.println("Enter cena:");
                int cena = input.nextInt();
                input.nextLine();

                printPiwaTanszeNiz(emf, cena);
            } else if (command.equals("printPiwaZBrowaruDrozszeNiz")) {
                System.out.println("Enter browar:");
                String browar = input.nextLine();
                System.out.println("Enter cena:");
                input.nextLine();

                int cena = input.nextInt();
                printPiwaZBrowaruDrozszeNiz(emf, browar, cena);
            } else {
                System.out.println("Wrong command");
            }
        }

    }
}