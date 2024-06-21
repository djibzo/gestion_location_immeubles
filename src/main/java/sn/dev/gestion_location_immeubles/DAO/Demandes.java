package sn.dev.gestion_location_immeubles.DAO;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Demandes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idDemande")
    private int idDemande;
    @Basic
    @Column(name = "idOffre")
    private int idOffre;
    @Basic
    @Column(name = "idUser")
    private int idUser;
    @Basic
    @Column(name = "etat")
    private int etat;
    @Basic
    @Column(name = "datedecreation")
    private Date datedecreation;

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Date getDatedecreation() {
        return datedecreation;
    }

    public void setDatedecreation(Date datedecreation) {
        this.datedecreation = datedecreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Demandes demandes = (Demandes) o;

        if (idDemande != demandes.idDemande) return false;
        if (idOffre != demandes.idOffre) return false;
        if (idUser != demandes.idUser) return false;
        if (etat != demandes.etat) return false;
        if (datedecreation != null ? !datedecreation.equals(demandes.datedecreation) : demandes.datedecreation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDemande;
        result = 31 * result + idOffre;
        result = 31 * result + idUser;
        result = 31 * result + etat;
        result = 31 * result + (datedecreation != null ? datedecreation.hashCode() : 0);
        return result;
    }
}
