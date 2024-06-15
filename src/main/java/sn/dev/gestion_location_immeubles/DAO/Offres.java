package sn.dev.gestion_location_immeubles.DAO;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Offres {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idOffre")
    private int idOffre;
    @Basic
    @Column(name = "idUnite")
    private int idUnite;
    @Basic
    @Column(name = "statut")
    private int statut;
    @Basic
    @Column(name = "datedeCreation")
    private Date datedeCreation;
    @ManyToOne
    @JoinColumn(name = "idUnite", referencedColumnName = "idUnite", nullable = false,insertable = false, updatable = false)
    private Unitesdelocations unitesdelocationsByIdUnite;

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
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

        Offres offres = (Offres) o;

        if (idOffre != offres.idOffre) return false;
        if (idUnite != offres.idUnite) return false;
        if (statut != offres.statut) return false;
        if (datedeCreation != null ? !datedeCreation.equals(offres.datedeCreation) : offres.datedeCreation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOffre;
        result = 31 * result + idUnite;
        result = 31 * result + statut;
        result = 31 * result + (datedeCreation != null ? datedeCreation.hashCode() : 0);
        return result;
    }

    public Unitesdelocations getUnitesdelocationsByIdUnite() {
        return unitesdelocationsByIdUnite;
    }

    public void setUnitesdelocationsByIdUnite(Unitesdelocations unitesdelocationsByIdUnite) {
        this.unitesdelocationsByIdUnite = unitesdelocationsByIdUnite;
    }
}
