package aplication;

import java.beans.PersistenceDelegate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.management.Query;

import entities.Aluno;
import entities.Curso;

public class Aplication {

	public static void main(String[] args) throws ParseException {
		
		System.out.println("\n*** Versão 1 - Inicial ***");
		
		EntityManagerFactory emf = PersistenceDelegate.createEntityManagerFactory("aula");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
		Aluno a1 = new Aluno(null,"Gioavane", "Masculino", df.parse("01-01-2010") );
		
		Curso c1 = new Curso(null, "Programação Orientada a Objeto II");
		
		c1.adicionarAluno(a1);
		
		em.persist(a1);
		em.persist(c1);
		
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