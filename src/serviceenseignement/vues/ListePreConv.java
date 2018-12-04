package serviceenseignement.vues;

import donnes.preconvention.PreConvention;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;
import serviceenseignement.ServiceEnseignement;

/**
 * Fenêtre générale du service enseignement permettant d'afficher la liste
 * des préconventions à valider
 * @author marieroca
 */
public class ListePreConv extends javax.swing.JFrame {
    private ServiceEnseignement s;
    private HashMap<Long, PreConvention> conv;
    private HashMap<Long, PreConvention> convTraitees;

    /**
     * Creates new form Enseignement
     * @throws javax.naming.NamingException
     */
    public ListePreConv() throws NamingException {
        initComponents();
    }

    /**
     * Getter du service enseignement lié à la fenêtre
     * @return service enseignement
     */
    public ServiceEnseignement getS() {
        return s;
    }

    /**
     * Setter du service enseignement lié à la fenêtre
     * @param s nouveau service enseignement
     */
    public void setS(ServiceEnseignement s) {
        this.s = s;
    }

    /**
     * Getter de la map des preconventions traitées (validées ou refusées)
     * @return la map(id, preconventions) des conventions traitées
     */
    public HashMap<Long, PreConvention> getConvTraitees() {
        return convTraitees;
    }

    /**
     * Setter de la map des preconventions traitées (validées ou refusées)
     * @param convTraitees map(id, preconventions) des conventions traitées
     */
    public void setConvTraitees(HashMap<Long, PreConvention> convTraitees) {
        this.convTraitees = convTraitees;
    }
    
    /**
     * Getter de la map des preconventions à traiter
     * @return la map(id, preconventions) des conventions à traiter
     */
    public HashMap<Long, PreConvention> getConv() {
        return s.getConv();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        puSelectionUnique = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        bPuOk = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tabNavigation = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        bOuvrir = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabAValider = new javax.swing.JTable();
        bMaj = new javax.swing.JButton();

        puSelectionUnique.setTitle("Selection");
        puSelectionUnique.setMinimumSize(new java.awt.Dimension(363, 100));

        jLabel2.setText("Vous devez sélectionner qu'un seul élément");

        bPuOk.setText("OK");
        bPuOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPuOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout puSelectionUniqueLayout = new javax.swing.GroupLayout(puSelectionUnique.getContentPane());
        puSelectionUnique.getContentPane().setLayout(puSelectionUniqueLayout);
        puSelectionUniqueLayout.setHorizontalGroup(
            puSelectionUniqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(puSelectionUniqueLayout.createSequentialGroup()
                .addGroup(puSelectionUniqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(puSelectionUniqueLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(puSelectionUniqueLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(bPuOk)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        puSelectionUniqueLayout.setVerticalGroup(
            puSelectionUniqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(puSelectionUniqueLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bPuOk))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Service Enseignement - Préconventions");

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Liste des pré-conventions à valider :");

        tabNavigation.setToolTipText("");
        tabNavigation.setName(""); // NOI18N

        bOuvrir.setText("Ouvrir");
        bOuvrir.setActionCommand("bOuvrir");
        bOuvrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOuvrirActionPerformed(evt);
            }
        });

        tabAValider.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id Convention", "Stage", "Diplôme"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tabAValider);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 315, Short.MAX_VALUE)
                .addComponent(bOuvrir))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bOuvrir))
        );

        tabNavigation.addTab("À Valider", jPanel3);

        bMaj.setText("maj");
        bMaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bMaj)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(tabNavigation)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bMaj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabNavigation))
        );

        tabNavigation.getAccessibleContext().setAccessibleName("AValider");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Évènement généré lors du clic sur "Ouvrir"
     * Ouverture de la fenêtre de détails de la préconvention sélectionnée
     * @see DetailsPreConv
     * @param evt 
     */
    private void bOuvrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOuvrirActionPerformed
        int[] lignes = tabAValider.getSelectedRows();
        //on ne peut modifier qu'une seule ligne
        //on teste donc combien de lignes sont sélectionnées et on retourne une pop-up d'info
        //si plusieurs le sont
        if(lignes.length > 1){
            this.puSelectionUnique.setVisible(true);
        } else {
            Long key = (Long) tabAValider.getValueAt(tabAValider.getSelectedRow(), 0);
            //on ouvre la fenêtre des détails de la conv sélectionnée pour vérifier et valider/refuser
            DetailsPreConv fen = new DetailsPreConv(this, key);
            fen.setVisible(true);
        }
    }//GEN-LAST:event_bOuvrirActionPerformed

    /**
     * Fermeture de la popup d'information "Selectionner qu'une seule ligne"
     * @param evt 
     */
    private void bPuOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPuOkActionPerformed
        this.puSelectionUnique.dispose();
    }//GEN-LAST:event_bPuOkActionPerformed

    /**
     * Évènement généré lors du clic sur "Maj"
     * Mise à jour de la liste des préconventions à traiter
     * @param evt 
     */
    private void bMajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMajActionPerformed
        s = new ServiceEnseignement(conv, convTraitees);
        
        convTraitees = s.getConvTraitees();
        conv = s.getConv();
        
        // On supprime toutes les lignes du tableau pour le mettre à jour avec les nouvelles valeurs
        DefaultTableModel model = (DefaultTableModel) this.tabAValider.getModel();
        for(int j = 0; j < model.getRowCount(); j++){
            model.removeRow(j);
        }
        
        ((DefaultTableModel) this.tabAValider.getModel()).setRowCount(conv.size());
        
        this.tabAValider.getColumn(tabAValider.getColumnName(0)).setMinWidth(45);
        this.tabAValider.getColumn(tabAValider.getColumnName(0)).setMaxWidth(60);
        System.out.println("HashMap : " + conv);
        int i = 0;
        for(PreConvention c : conv.values()){
            this.tabAValider.setValueAt(c.getId(), i, 0);
            this.tabAValider.setValueAt(c.getStage(), i, 1);
            this.tabAValider.setValueAt(c.getEtudiant().getDiplome().getNiveau() + c.getEtudiant().getDiplome().getIntitule(), i, 2);
            i++;
        }
    }//GEN-LAST:event_bMajActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListePreConv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListePreConv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListePreConv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListePreConv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            //Lancement de la connexion au serveur pour réceptionner et envoyer les JMS (préconventions)
            ServiceEnseignement.init();
        } catch (NamingException ex) {
            Logger.getLogger(ListePreConv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(ListePreConv.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ListePreConv().setVisible(true);
                } catch (NamingException ex) {
                    Logger.getLogger(ListePreConv.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bMaj;
    private javax.swing.JButton bOuvrir;
    private javax.swing.JButton bPuOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JDialog puSelectionUnique;
    private javax.swing.JTable tabAValider;
    private javax.swing.JTabbedPane tabNavigation;
    // End of variables declaration//GEN-END:variables
}
