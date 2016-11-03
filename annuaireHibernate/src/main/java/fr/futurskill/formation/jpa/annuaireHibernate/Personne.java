package fr.futurskill.formation.jpa.annuaireHibernate;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Personne.java
 *
 * Represente une personne d�crite par :
 * - son nom
 * - son prenom
 * - sa civilite.
 * @param <T>
 */

@Entity
@Table(name = "personne")
public class Personne implements Comparable<Personne>, Serializable{

  // des constantes definissant les valeurs possiblses pour
  // la civilit�
  public static final int INCONNU = 0;
  public static final int MR = 1;
  public static final int MME = 2;
  public static final int MLLE = 3;
  
  // un tableau de String pour faire correspondre une cha�ne � la civilit�
  private static final String[] civil_ = { "", "Mr", "Mme", "Mlle"}; 

  //------------------------------------------------------
  // les attributs d'un objet personne
  //------------------------------------------------------
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idPersonne;
  
  @Column(name = "nom")
  private String nom_;
  
  @Column(name = "prenom")
  private String prenom_;
  
  @Column(name = "civilite")
  private int civilite_ = INCONNU;
  
  
  @OneToMany(mappedBy = "p")
  private Set<NumTel> listeNumTel;

  //------------------------------------------------------
  // les constructeurs
  //------------------------------------------------------
  
  public Personne() {
		super();
	}


  /**
   * cr�e un personne en indiquant son nom et son prenom, sa civilit� n'est pas
   * specifi�e et est initialis�e � INCONNU
   * @param nom le nom de la personne
   * @param prenom le prenom de la personne
   */
  public Personne (String nom, String prenom) {
    this(INCONNU,nom,prenom);
  }

 
  /**
   * cr�e un personne en indiquant sa civilit�, son nom et son prenom.
   * @param civilite la civilit� de la personne. Seules quatre valeurs sont
   *        admises pour la civilit� d�finies par les constantes INCONNU, 
   *        MR, MME et MLLE. Si une valeur de civilit� incorrecte est fournie
   *        en param�tre, la personne est alors cr��e avec une civilit� fix�e
   *        � INCONNU.
   * @param nom le nom de la personne
   * @param prenom le prenom de la personne
   */
  public Personne (int civilite, String nom, String prenom) {
    nom_ = nom;
    prenom_ = prenom;
    if (civiliteCorrecte(civilite)) 
        civilite_ = civilite;
    else
        civilite_ = INCONNU;
  }
  
  

  //------------------------------------------------------
  // les m�thodes
  //------------------------------------------------------  

  

//---- accesseurs --------------------------------------
  /**
   * retourne le nom de la personne
   * @return le nom de la personne
   */
  public String getNom() {
    return nom_;
  }

  /**
   * retourne le prenom de la personne
   * @return le prenom de la personne
   */
  public String getPrenom() {
    return prenom_;
  }

  /**
   * retourne la civilite de la personne
   * @return la civilite de la personne
   */
  public int getCivilite() {
    return civilite_;
  }

  //---- modifieurs ---------------------------------------
  
  /**
   * modifie la civilite de la personne. Seules les modification suivantes
   * sont autoris�es :<BR>
   * <UL>
   *   <LI>INCONNU --> MR, MME ou MLLE</LI>
   *   <LI>MLLE --> MME</LI>
   * </UL>
   * Toute autre modification est sans effet sur la personne 
   */
  public void setCivilite(int civilite) {
      if (civiliteCorrecte(civilite)) {
          switch (civilite_) {
              case INCONNU:   civilite_ = civilite;
                              break;
              case MLLE:      if (civilite == MME)
                                 civilite_ = MME;
                              break;
              default:        // on ne fait rien
                              break;
          }
      }
  }
  //-------------------------------------------------------
  // m�thodes h�rit�es de la classe Object et red�finies
  //-------------------------------------------------------

  public String toString() {
    return civil_[civilite_] + " " + prenom_ + " " + nom_;
  }

  //--------------------------------------------------------
  // m�thodes priv�es utilitaires
  //--------------------------------------------------------
  
  /**
   * Teste si une valeur de civilit� est correcte.
   * @param civilite la valeur de civilit� � tester
   * @return true si <code>civilite</code> d�finit une
   *         valeur correcte, false sinon
   */
  private boolean civiliteCorrecte(int civilite) {
      return (INCONNU <= civilite && civilite <= MLLE);
  }


  
  public int compareTo(Personne o) {
		
	  if (! (o instanceof Personne) )
          throw new ClassCastException("impossible de comparer un " + 
             o.getClass().getName() + " � une Personne");
      
      Personne p = (Personne) o;
      String s = this.nom_ + this.prenom_ + this.civilite_ ;
      return s.compareTo(p.nom_ + p.prenom_ + p.civilite_);
  }
	
  }// Personne





