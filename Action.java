package todolist;

/**
 * La classe <code>Action</code> représente une tâche individuelle dans la liste de choses à faire.
 * Elle contient un identifiant unique, un nom, une description et un état d'avancement.
 * * @author VotreNom
 * @version 1.0
 */
public class Action {
    /** Compteur statique pour générer des identifiants uniques. */
    private static int cpt = 0;
    
    /** Identifiant unique de l'action. */
    private int id;
    
    /** Nom ou titre de l'action. */
    private String name;
    
    /** Description détaillée de l'action. */
    private String description;
    
    /** État de l'action (vrai si terminée, faux sinon). */
    private boolean isCompleted;

    /**
     * Construit une nouvelle instance d'Action avec un nom et une description.
     * L'identifiant est incrémenté automatiquement et l'état est défini sur non terminé par défaut.
     * * @param name Le nom de la tâche. Si vide, le nom sera "Unknown".
     * @param description La description de la tâche. Si vide, la description sera "No description".
     */
    public Action(String name, String description) {
        this.id = cpt++;
        
        // Gestion sécurisée du nom
        try {
            if(name.isEmpty()) {
                throw new RuntimeException("Name cannot be empty");
            }
            this.name = name;
        } catch (Exception e) {
            this.name = "Unknown";
        }

        // Gestion sécurisée de la description
        try {
            if(description.isEmpty()) {
                throw new RuntimeException("Description cannot be empty");
            }
            this.description = description;
        } catch (Exception e) {
            this.description = "No description";
        }

        this.isCompleted = false;
    }

    /**
     * Retourne le nom (titre) de l'action.
     * @return Le nom de l'action.
     */
    public String getTitle() {
        return name;
    }

    /**
     * Retourne la description détaillée de l'action.
     * @return La description de l'action.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description de l'action.
     * @param description La nouvelle description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Indique si l'action est terminée.
     * @return <code>true</code> si l'action est complétée, <code>false</code> sinon.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Définit l'état d'avancement de l'action.
     * @param completed <code>true</code> pour marquer comme terminé, <code>false</code> sinon.
     */
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    /**
     * Retourne une représentation textuelle de l'action, incluant son nom 
     * et une case à cocher visuelle indiquant son état.
     * * @return Une chaîne de caractères formatée (ex: "Acheter du pain [X]").
     */
    @Override
    public String toString() {
        return this.name + " " + (isCompleted ? "[X]" : "[ ]");
    }
}