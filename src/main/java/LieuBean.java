package beans;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@Named("lieuBean")
@RequestScoped
public class LieuBean {
    private String nom;
    private String description;
    private double latitude;
    private double longitude;
    private int indexToEdit = -1; // Index pour modifier un lieu
    private static List<Lieu> listeLieux = new ArrayList<>();

    // Getters et Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public int getIndexToEdit() { return indexToEdit; }
    public void setIndexToEdit(int indexToEdit) { this.indexToEdit = indexToEdit; }

    public List<Lieu> getListeLieux() { return listeLieux; }

    // Méthode pour ajouter/modifier un lieu
    public void ajouterLieu() {
        Lieu lieu = new Lieu(nom, description, latitude, longitude);
        if (indexToEdit >= 0) {
            listeLieux.set(indexToEdit, lieu); // Modifier un lieu existant
            indexToEdit = -1; // Réinitialiser l'index
        } else {
            listeLieux.add(lieu); // Ajouter un nouveau lieu
        }
        resetFields();
    }

    // Préparer la modification d'un lieu
    public void prepareEdit(int index) {
        Lieu lieu = listeLieux.get(index);
        this.nom = lieu.getNom();
        this.description = lieu.getDescription();
        this.latitude = lieu.getLatitude();
        this.longitude = lieu.getLongitude();
        this.indexToEdit = index;
    }

    // Supprimer un lieu
    public void supprimerLieu(int index) {
        listeLieux.remove(index);
    }

    // Réinitialiser les champs du formulaire
    private void resetFields() {
        this.nom = "";
        this.description = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    // Classe interne pour stocker les lieux
    public static class Lieu {
        private String nom;
        private String description;
        private double latitude;
        private double longitude;

        public Lieu(String nom, String description, double latitude, double longitude) {
            this.nom = nom;
            this.description = description;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getNom() { return nom; }
        public String getDescription() { return description; }
        public double getLatitude() { return latitude; }
        public double getLongitude() { return longitude; }
    }
}
