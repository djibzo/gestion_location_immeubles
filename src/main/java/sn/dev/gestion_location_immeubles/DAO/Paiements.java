package sn.dev.gestion_location_immeubles.DAO;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Paiements {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPaiement")
    private int idPaiement;
    @Basic
    @Column(name = "idLocataire")
    private int idLocataire;
    @Basic
    @Column(name = "idContratLocation")
    private int idContratLocation;
    @Basic
    @Column(name = "datedeCreation")
    private Date datedeCreation;
    @ManyToOne
    @JoinColumn(name = "idLocataire", referencedColumnName = "idUser", nullable = false,insertable = false, updatable = false)
    private Utilisateurs utilisateursByIdLocataire;
    @ManyToOne
    @JoinColumn(name = "idContratLocation", referencedColumnName = "idContrat", nullable = false,insertable = false, updatable = false)
    private Contratsdelocations contratsdelocationsByIdContratLocation;

    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }

    public int getIdLocataire() {
        return idLocataire;
    }

    public void setIdLocataire(int idLocataire) {
        this.idLocataire = idLocataire;
    }

    public int getIdContratLocation() {
        return idContratLocation;
    }

    public void setIdContratLocation(int idContratLocation) {
        this.idContratLocation = idContratLocation;
    }

    public Date getDatedeCreation() {
        return datedeCreation;
    }

    public void setDatedeCreation(Date datedeCreation) {
        this.datedeCreation = datedeCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paiements paiements = (Paiements) o;

        if (idPaiement != paiements.idPaiement) return false;
        if (idLocataire != paiements.idLocataire) return false;
        if (idContratLocation != paiements.idContratLocation) return false;
        if (datedeCreation != null ? !datedeCreation.equals(paiements.datedeCreation) : paiements.datedeCreation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPaiement;
        result = 31 * result + idLocataire;
        result = 31 * result + idContratLocation;
        result = 31 * result + (datedeCreation != null ? datedeCreation.hashCode() : 0);
        return result;
    }

    public Utilisateurs getUtilisateursByIdLocataire() {
        return utilisateursByIdLocataire;
    }

    public void setUtilisateursByIdLocataire(Utilisateurs utilisateursByIdLocataire) {
        this.utilisateursByIdLocataire = utilisateursByIdLocataire;
    }

    public Contratsdelocations getContratsdelocationsByIdContratLocation() {
        return contratsdelocationsByIdContratLocation;
    }

    public void setContratsdelocationsByIdContratLocation(Contratsdelocations contratsdelocationsByIdContratLocation) {
        this.contratsdelocationsByIdContratLocation = contratsdelocationsByIdContratLocation;
    }
}
