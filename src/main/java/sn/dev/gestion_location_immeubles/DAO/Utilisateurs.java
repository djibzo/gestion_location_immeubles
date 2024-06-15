package sn.dev.gestion_location_immeubles.DAO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Utilisateurs {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUser")
    private int idUser;
    @Basic
    @Column(name = "prenomUser")
    private String prenomUser;
    @Basic
    @Column(name = "nomUser")
    private String nomUser;
    @Basic
    @Column(name = "emailUser")
    private String emailUser;
    @Basic
    @Column(name = "mdpUser")
    private String mdpUser;
    @Basic
    @Column(name = "profilUser")
    private int profilUser;
    @Basic
    @Column(name = "tokenResetMdp")
    private String tokenResetMdp;
    @Basic
    @Column(name = "datedeCreation")
    private Date datedeCreation;
    @OneToMany(mappedBy = "utilisateursByIdLocataire")
    private Collection<Contratsdelocations> contratsdelocationsByIdUser;
    @OneToMany(mappedBy = "utilisateursByIdProprietaire")
    private Collection<Immeubles> immeublesByIdUser;
    @OneToMany(mappedBy = "utilisateursByIdLocataire")
    private Collection<Paiements> paiementsByIdUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getMdpUser() {
        return mdpUser;
    }

    public void setMdpUser(String mdpUser) {
        this.mdpUser = mdpUser;
    }

    public int getProfilUser() {
        return profilUser;
    }

    public void setProfilUser(int profilUser) {
        this.profilUser = profilUser;
    }

    public String getTokenResetMdp() {
        return tokenResetMdp;
    }

    public void setTokenResetMdp(String tokenResetMdp) {
        this.tokenResetMdp = tokenResetMdp;
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

        Utilisateurs that = (Utilisateurs) o;

        if (idUser != that.idUser) return false;
        if (profilUser != that.profilUser) return false;
        if (prenomUser != null ? !prenomUser.equals(that.prenomUser) : that.prenomUser != null) return false;
        if (nomUser != null ? !nomUser.equals(that.nomUser) : that.nomUser != null) return false;
        if (emailUser != null ? !emailUser.equals(that.emailUser) : that.emailUser != null) return false;
        if (mdpUser != null ? !mdpUser.equals(that.mdpUser) : that.mdpUser != null) return false;
        if (tokenResetMdp != null ? !tokenResetMdp.equals(that.tokenResetMdp) : that.tokenResetMdp != null)
            return false;
        if (datedeCreation != null ? !datedeCreation.equals(that.datedeCreation) : that.datedeCreation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (prenomUser != null ? prenomUser.hashCode() : 0);
        result = 31 * result + (nomUser != null ? nomUser.hashCode() : 0);
        result = 31 * result + (emailUser != null ? emailUser.hashCode() : 0);
        result = 31 * result + (mdpUser != null ? mdpUser.hashCode() : 0);
        result = 31 * result + profilUser;
        result = 31 * result + (tokenResetMdp != null ? tokenResetMdp.hashCode() : 0);
        result = 31 * result + (datedeCreation != null ? datedeCreation.hashCode() : 0);
        return result;
    }

    public Collection<Contratsdelocations> getContratsdelocationsByIdUser() {
        return contratsdelocationsByIdUser;
    }

    public void setContratsdelocationsByIdUser(Collection<Contratsdelocations> contratsdelocationsByIdUser) {
        this.contratsdelocationsByIdUser = contratsdelocationsByIdUser;
    }

    public Collection<Immeubles> getImmeublesByIdUser() {
        return immeublesByIdUser;
    }

    public void setImmeublesByIdUser(Collection<Immeubles> immeublesByIdUser) {
        this.immeublesByIdUser = immeublesByIdUser;
    }

    public Collection<Paiements> getPaiementsByIdUser() {
        return paiementsByIdUser;
    }

    public void setPaiementsByIdUser(Collection<Paiements> paiementsByIdUser) {
        this.paiementsByIdUser = paiementsByIdUser;
    }
}
