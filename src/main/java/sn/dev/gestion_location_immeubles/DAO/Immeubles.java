package sn.dev.gestion_location_immeubles.DAO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Immeubles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idImmeuble")
    private int idImmeuble;
    @Basic
    @Column(name = "nomImmeuble")
    private String nomImmeuble;
    @Basic
    @Column(name = "adresseImmeuble")
    private String adresseImmeuble;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "idProprietaire")
    private int idProprietaire;
    @Basic
    @Column(name = "datedeCreation")
    private Date datedeCreation;
    @ManyToOne
    @JoinColumn(name = "idProprietaire", referencedColumnName = "idUser", nullable = false,insertable = false, updatable = false)
    private Utilisateurs utilisateursByIdProprietaire;
    @OneToMany(mappedBy = "immeublesByIdImmeuble",cascade = CascadeType.ALL,orphanRemoval = true)
    private Collection<Unitesdelocations> unitesdelocationsByIdImmeuble;

    public int getIdImmeuble() {
        return idImmeuble;
    }

    public void setIdImmeuble(int idImmeuble) {
        this.idImmeuble = idImmeuble;
    }

    public String getNomImmeuble() {
        return nomImmeuble;
    }

    public void setNomImmeuble(String nomImmeuble) {
        this.nomImmeuble = nomImmeuble;
    }

    public String getAdresseImmeuble() {
        return adresseImmeuble;
    }

    public void setAdresseImmeuble(String adresseImmeuble) {
        this.adresseImmeuble = adresseImmeuble;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(int idProprietaire) {
        this.idProprietaire = idProprietaire;
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

        Immeubles immeubles = (Immeubles) o;

        if (idImmeuble != immeubles.idImmeuble) return false;
        if (idProprietaire != immeubles.idProprietaire) return false;
        if (nomImmeuble != null ? !nomImmeuble.equals(immeubles.nomImmeuble) : immeubles.nomImmeuble != null)
            return false;
        if (adresseImmeuble != null ? !adresseImmeuble.equals(immeubles.adresseImmeuble) : immeubles.adresseImmeuble != null)
            return false;
        if (description != null ? !description.equals(immeubles.description) : immeubles.description != null)
            return false;
        if (datedeCreation != null ? !datedeCreation.equals(immeubles.datedeCreation) : immeubles.datedeCreation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idImmeuble;
        result = 31 * result + (nomImmeuble != null ? nomImmeuble.hashCode() : 0);
        result = 31 * result + (adresseImmeuble != null ? adresseImmeuble.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + idProprietaire;
        result = 31 * result + (datedeCreation != null ? datedeCreation.hashCode() : 0);
        return result;
    }

    public Utilisateurs getUtilisateursByIdProprietaire() {
        return utilisateursByIdProprietaire;
    }

    public void setUtilisateursByIdProprietaire(Utilisateurs utilisateursByIdProprietaire) {
        this.utilisateursByIdProprietaire = utilisateursByIdProprietaire;
    }

    public Collection<Unitesdelocations> getUnitesdelocationsByIdImmeuble() {
        return unitesdelocationsByIdImmeuble;
    }

    public void setUnitesdelocationsByIdImmeuble(Collection<Unitesdelocations> unitesdelocationsByIdImmeuble) {
        this.unitesdelocationsByIdImmeuble = unitesdelocationsByIdImmeuble;
    }
}
