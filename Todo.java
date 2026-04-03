package todolist;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// J'avoue c'est Claude qui a fait les commentaires...
// Par contre, la structure et le restant du code c'est bibi

/**
 * La classe <code>Todo</code> représente l'interface graphique principale d'une application de gestion de tâches.
 * Elle permet d'ajouter, de supprimer et de marquer des tâches comme terminées.
 * * @author VotreNom
 * @version 1.0
 */
public class Todo {
    /** Bouton pour ajouter une nouvelle tâche. */
    private JButton btnAdd;
    /** Panneau principal contenant les composants de l'interface. */
    private JPanel panel1;
    /** Champ de texte pour saisir le titre d'une nouvelle tâche. */
    private JTextField txtTitleAction;
    /** Bouton pour supprimer la tâche sélectionnée. */
    private JButton btnDelete;
    /** Liste affichant l'ensemble des tâches. */
    private JList lstToDo;
    /** Champ de texte pour saisir la description d'une nouvelle tâche. */
    private JTextField txtDescAction;
    /** Barre de défilement pour la zone de description. */
    private JScrollBar scbDesc;
    /** Zone de texte affichant les détails de la tâche sélectionnée. */
    private JTextArea txaDesc;
    /** Bouton pour marquer une tâche comme terminée. */
    private JButton btnDone;
    /** Modèle de données stockant les objets {@link Action}. */
    private DefaultListModel<Action> listModel = new DefaultListModel<>();

    /**
     * Récupère les informations saisies dans les champs de texte, crée une nouvelle instance
     * de {@link Action} et l'ajoute au modèle de la liste.
     * Après l'ajout, les champs de saisie sont réinitialisés.
     */
    public void addTodo() {
        int index = listModel.size() + 1;
        // Je promets la structure c'est moi wallah
        Action newAction = new Action(
                txtTitleAction.getText(),
                txtDescAction.getText()
        );
        listModel.addElement(newAction);
        txtTitleAction.setText("");
        txtDescAction.setText("");
    }

    /**
     * Constructeur de la classe <code>Todo</code>.
     * Initialise la fenêtre principale (JFrame), configure les écouteurs d'événements (listeners)
     * pour les boutons et la liste, et rend l'interface visible.
     */
    public Todo() {
        JFrame f = new JFrame();
        f.setSize(640, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        lstToDo.setModel(listModel);

        // Écouteur pour mettre à jour la zone de texte lorsqu'une tâche est sélectionnée dans la liste
        lstToDo.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Action selected = (Action) lstToDo.getSelectedValue();
                if (selected != null) {
                    txaDesc.setText(selected.getDescription());
                    txaDesc.setCaretPosition(0);
                }
            }
        });

        // Action du bouton Ajouter
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTodo();
            }
        });

        // Action du bouton Supprimer
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = lstToDo.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                    txaDesc.setText("");
                }
            }
        });

        // Action du bouton Terminé
        btnDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = lstToDo.getSelectedIndex();
                if (selectedIndex != -1) {
                    Action selectedAction = listModel.get(selectedIndex);
                    selectedAction.setCompleted(true);
                    lstToDo.repaint();
                }
            }
        });

        f.getContentPane().add(panel1);
        f.setVisible(true);
    }

    /**
     * Point d'entrée principal de l'application.
     * Crée une nouvelle instance de l'interface {@link Todo}.
     * * @param args Les arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        new Todo();
    }

    /**
     * Méthode d'initialisation personnalisée pour les composants de l'interface (souvent appelée par l'IDE).
     * Ici, elle instancie spécifiquement la zone de texte de description.
     */
    private void createUIComponents() {
        txaDesc = new JTextArea();
    }
}