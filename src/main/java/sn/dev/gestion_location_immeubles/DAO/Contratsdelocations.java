package sn.dev.gestion_location_immeubles.DAO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Contratsdelocations {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idContrat")
    private int idContrat;
    @Basic
    @Column(name = "idUnite")
    private int idUnite;
    @Basic
    @Column(name = "idLocataire")
    private int idLocataire;
    @Basic
    @Column(name = "datedeCreation")
    private Date datedeCreation;
    @ManyToOne
    @JoinColumn(name = "idUnite", referencedColumnName = "idUnite", nullable = false,insertable = false, updatable = false)
    private Unitesdelocations unitesdelocationsByIdUnite;
    @ManyToOne
    @JoinColumn(name = "idLocataire", referencedColumnName = "idUser", nullable = false,insertable = false, updatable = false)
    private Utilisateurs utilisateursByIdLocataire;
    @OneToMany(mappedBy = "contratsdelocationsByIdContratLocation")
    private Collection<Paiements> paiementsByIdContrat;

    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public int getIdLocataire() {
        return idLocataire;
    }

    public void setIdLocataire(int idLocataire) {
        this.idLocataire = idLocataire;
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

        Contratsdelocations that = (Contratsdelocations) o;

        if (idContrat != that.idContrat) return false;
        if (idUnite != that.idUnite) return false;
        if (idLocataire != that.idLocataire) return false;
        if (datedeCreation != null ? !datedeCreation.equals(that.datedeCreation) : that.datedeCreation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContrat;
        result = 31 * result + idUnite;
        result = 31 * result + idLocataire;
        result = 31 * result + (datedeCreation != null ? datedeCreation.hashCode() : 0);
        return result;
    }

    public Unitesdelocations getUnitesdelocationsByIdUnite() {
        return unitesdelocationsByIdUnite;
    }

    public void setUnitesdelocationsByIdUnite(Unitesdelocations unitesdelocationsByIdUnite) {
        this.unitesdelocationsByIdUnite = unitesdelocationsByIdUnite;
    }

    public Utilisateurs getUtilisateursByIdLocataire() {
        return utilisateursByIdLocataire;
    }

    public void setUtilisateursByIdLocataire(Utilisateurs utilisateursByIdLocataire) {
        this.utilisateursByIdLocataire = utilisateursByIdLocataire;
    }

    public Collection<Paiements> getPaiementsByIdContrat() {
        return paiementsByIdContrat;
    }

    public void setPaiementsByIdContrat(Collection<Paiements> paiementsByIdContrat) {
        this.paiementsByIdContrat = paiementsByIdContrat;
    }
}
