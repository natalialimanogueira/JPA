package controller;


import entites.Aluno;
import entites.Curso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Programa {

    public static void main(String[] args) throws ParseException {

        System.out.println("\n*** Versão 1 - Inicial ***");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aulajpa");
        EntityManager em = emf.createEntityManager();

        //Inicia o controle de transação com o banco através do EntityManager
        em.getTransaction().begin();

        DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        Aluno a1 = new Aluno(null,"Isabelle", "Femino", df.parse("01-01-2023") );

        Curso c1 = new Curso(null, "POO II");

        c1.adicionarAluno(a1);

        //Grava os objetos no banco - cada objeto vira uma linha da respectiva tabela
        em.persist(a1);
        em.persist(c1);

        //Finaliza a transação dando commit no banco
        em.getTransaction().commit();

        Query query1 = em.createQuery("SELECT c FROM Curso c");

        List<Curso> cursos = query1.getResultList();
        for (Curso c : cursos) {
            System.out.println("\n *** [" + c.getIdcurso() + " | "+ c.getNomecurso() + "] ***");
            for (Aluno a : c.getAlunos()) {
                System.out.println("\tAluno: " + a.getIdaluno() + " | "+ a.getNome() );
            }

        }


        Query query2 = em.createQuery("SELECT a FROM Aluno a");

        List<Aluno> alunos = query2.getResultList();
        for (Aluno a : alunos) {
            System.out.println("\n *** [" + a.getIdaluno() + " | "+ a.getNome() + "] ***");
            for (Curso c : a.getCursos()) {
                System.out.println("\tCurso: " + c.getIdcurso() + " | "+ c.getNomecurso() );
            }
        }

        em.close();
        emf.close();

    }

}