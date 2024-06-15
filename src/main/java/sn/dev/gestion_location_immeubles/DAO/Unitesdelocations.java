package sn.dev.gestion_location_immeubles.DAO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Unitesdelocations {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUnite")
    private int idUnite;
    @Basic
    @Column(name = "nomUnite")
    private String nomUnite;
    @Basic
    @Column(name = "nombrePieces")
    private int nombrePieces;
    @Basic
    @Column(name = "superficie")
    private double superficie;
    @Basic
    @Column(name = "prixLoyer")
    private Double prixLoyer;
    @Basic
    @Column(name = "datedeCreation")
    private Date datedeCreation;
    @Basic
    @Column(name = "idImmeuble")
    private int idImmeuble;
    @OneToMany(mappedBy = "unitesdelocationsByIdUnite")
    private Collection<Contratsdelocations> contratsdelocationsByIdUnite;
    @OneToMany(mappedBy = "unitesdelocationsByIdUnite")
    private Collection<Offres> offresByIdUnite;
    @ManyToOne
    @JoinColumn(name = "idImmeuble", referencedColumnName = "idImmeuble", nullable = false,insertable = false, updatable = false)
    private Immeubles immeublesByIdImmeuble;

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public int getNombrePieces() {
        return nombrePieces;
    }

    public void setNombrePieces(int nombrePieces) {
        this.nombrePieces = nombrePieces;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public Double getPrixLoyer() {
        return prixLoyer;
    }

    public void setPrixLoyer(Double prixLoyer) {
        this.prixLoyer = prixLoyer;
    }

    public Date getDatedeCreation() {
        return datedeCreation;
    }

    public void setDatedeCreation(Date datedeCreation) {
        this.datedeCreation = datedeCreation;
    }

    public int getIdImmeuble() {
        return idImmeuble;
    }

    public void setIdImmeuble(int idImmeuble) {
        this.idImmeuble = idImmeuble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unitesdelocations that = (Unitesdelocations) o;

        if (idUnite != that.idUnite) return false;
        if (nombrePieces != that.nombrePieces) return false;
        if (Double.compare(superficie, that.superficie) != 0) return false;
        if (idImmeuble != that.idImmeuble) return false;
        if (nomUnite != null ? !nomUnite.equals(that.nomUnite) : that.nomUnite != null) return false;
        if (prixLoyer != null ? !prixLoyer.equals(that.prixLoyer) : that.prixLoyer != null) return false;
        if (datedeCreation != null ? !datedeCreation.equals(that.datedeCreation) : that.datedeCreation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idUnite;
        result = 31 * result + (nomUnite != null ? nomUnite.hashCode() : 0);
        result = 31 * result + nombrePieces;
        temp = Double.doubleToLongBits(superficie);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (prixLoyer != null ? prixLoyer.hashCode() : 0);
        result = 31 * result + (datedeCreation != null ? datedeCreation.hashCode() : 0);
        result = 31 * result + idImmeuble;
        return result;
    }

    public Collection<Contratsdelocations> getContratsdelocationsByIdUnite() {
        return contratsdelocationsByIdUnite;
    }

    public void setContratsdelocationsByIdUnite(Collection<Contratsdelocations> contratsdelocationsByIdUnite) {
        this.contratsdelocationsByIdUnite = contratsdelocationsByIdUnite;
    }

    public Collection<Offres> getOffresByIdUnite() {
        return offresByIdUnite;
    }

    public void setOffresByIdUnite(Collection<Offres> offresByIdUnite) {
        this.offresByIdUnite = offresByIdUnite;
    }

    public Immeubles getImmeublesByIdImmeuble() {
        return immeublesByIdImmeuble;
    }

    public void setImmeublesByIdImmeuble(Immeubles immeublesByIdImmeuble) {
        this.immeublesByIdImmeuble = immeublesByIdImmeuble;
    }
}
