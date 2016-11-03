package fr.futurskill.formation.jpa.annuaireHibernate;

import org.hibernate.Session;



public class TestAnnuaire {

	public static void main(String[] args) {
		
		System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Personne p = new Personne(1, "toto", "titi");
        session.save(p);
        session.getTransaction().commit();

	}

}
