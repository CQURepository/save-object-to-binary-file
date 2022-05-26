
package com.savetofile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class App extends javax.swing.JFrame {
    
    private final static String FILENAME = "selection.dat";
    private boolean[] selection = new boolean[3];

    /** Creates new form App */
    public App() {
        initComponents();
        
        //Load meal selection status from the binary file
        loadFromFile();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saveButton = new javax.swing.JButton();
        pizza = new javax.swing.JCheckBox();
        burgerAndChips = new javax.swing.JCheckBox();
        pasta = new javax.swing.JCheckBox();
        headingLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Meal Chooser");

        saveButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        pizza.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pizza.setText("Pizza");

        burgerAndChips.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        burgerAndChips.setText("Buger & Chips");

        pasta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pasta.setText("Pasta");

        headingLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        headingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headingLabel.setText("Choose one or more meals");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saveButton)
                    .addComponent(pizza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(burgerAndChips, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(pasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pizza)
                .addGap(5, 5, 5)
                .addComponent(burgerAndChips)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pasta)
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(414, 307));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Capture the checkbox state in the Boolean array then write the array to file
     * @param evt 
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        
        //Capture the status of each checkbox and write it to file
        selection[0] = pizza.isSelected();
        selection[1] = burgerAndChips.isSelected();
        selection[2] = pasta.isSelected();
        writeToFile(selection);
        
    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * Loads the Boolean array from the binary file and reconstructs it
     */
    private void loadFromFile(){
        //Create a new file if it doesn't already exist before attempting any read/write actions
        try{
            var file = new File(FILENAME);
            if(file.createNewFile()){
                writeToFile(selection);
                System.out.println("File has been created");
            }
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
        
        try(FileInputStream fis = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis)){

            //Get the array object
            var obj = ois.readObject();
            if(obj instanceof boolean[])
                selection = (boolean[]) obj;
            
            //Set the checkboxes according to the array status
            pizza.setSelected(selection[0]);
            burgerAndChips.setSelected(selection[1]);
            pasta.setSelected(selection[2]);

        } catch (IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Simply writes whatever you pass it to the binary file
     * @param object The item to be written to file
     */
    private void writeToFile(Object object){
        try(FileOutputStream fos = new FileOutputStream(FILENAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeObject(object);

        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new App().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox burgerAndChips;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JCheckBox pasta;
    private javax.swing.JCheckBox pizza;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables


}
