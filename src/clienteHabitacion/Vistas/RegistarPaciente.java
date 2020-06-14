/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteHabitacion.Vistas;

import clienteHabitacion.ClienteMedico;
import clienteHabitacion.sop_rmi.HabitacionInt;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import servidorAlertas.dto.UsuarioDTO;
import servidorAlertas.sop_rmi.GestionPacienteInt;

/**
 *
 * @author usuario
 */
public class RegistarPaciente extends javax.swing.JFrame {

    public static GestionPacienteInt objRemoto;
    public static ClienteMedico cm;
    public static HabitacionInt objHabitacion;
    public static MenuMedico guiMenuMedico;
    int rol = 1;//1-->admin 0-->medico
    // public static UsuarioDTO objUsuario;

    /**
     * Creates new form prueba
     */
    public RegistarPaciente(GestionPacienteInt objRemoto, ClienteMedico cm, HabitacionInt objHabitacion, MenuMedico guiMenuMedico) {
        this.objRemoto = objRemoto;
        this.cm = cm;
        this.objHabitacion = objHabitacion;
        this.guiMenuMedico = guiMenuMedico;
        initComponents();
        rbtn_cc.setSelected(true);
    }

    public boolean verificar(int n, String nombres, String apellidos, String direccion){
            //String id = txf_id.getText();//Validar tipo de dato
            
            if(n==-1 || nombres.equals("") || apellidos.equals("") || direccion.equals("")){
                return false;
            }else{
                return true;
            }
    }
    public RegistarPaciente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btng_tiposDeId = new javax.swing.ButtonGroup();
        btn_crear = new javax.swing.JButton();
        lb_id = new javax.swing.JLabel();
        lb_tipoId = new javax.swing.JLabel();
        lb_nombres = new javax.swing.JLabel();
        lb_apellidos = new javax.swing.JLabel();
        lb_direccion = new javax.swing.JLabel();
        txf_id = new javax.swing.JTextField();
        txf_nombres = new javax.swing.JTextField();
        txf_apellidos = new javax.swing.JTextField();
        txf_direccion = new javax.swing.JTextField();
        bnt_volver = new javax.swing.JButton();
        rbtn_cc = new javax.swing.JRadioButton();
        rbtn_ti = new javax.swing.JRadioButton();
        rbtn_pp = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_crear.setText("Crear");
        btn_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearActionPerformed(evt);
            }
        });

        lb_id.setText("ID");

        lb_tipoId.setText("Tipo ID");

        lb_nombres.setText("Nombres");

        lb_apellidos.setText("Apellidos");

        lb_direccion.setText("Dirección");

        bnt_volver.setText("Volver al menú");
        bnt_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_volverActionPerformed(evt);
            }
        });

        btng_tiposDeId.add(rbtn_cc);
        rbtn_cc.setText("CC");

        btng_tiposDeId.add(rbtn_ti);
        rbtn_ti.setText("TI");

        btng_tiposDeId.add(rbtn_pp);
        rbtn_pp.setText("PP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_crear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bnt_volver))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_tipoId)
                            .addComponent(lb_id)
                            .addComponent(lb_direccion)
                            .addComponent(lb_nombres)
                            .addComponent(lb_apellidos))
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txf_apellidos)
                            .addComponent(txf_nombres)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbtn_cc)
                                .addGap(18, 18, 18)
                                .addComponent(rbtn_ti)
                                .addGap(18, 18, 18)
                                .addComponent(rbtn_pp))
                            .addComponent(txf_direccion))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txf_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_id))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_tipoId)
                    .addComponent(rbtn_cc)
                    .addComponent(rbtn_ti)
                    .addComponent(rbtn_pp))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txf_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_nombres))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txf_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_apellidos))
                        .addGap(21, 21, 21)
                        .addComponent(txf_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_direccion, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnt_volver)
                    .addComponent(btn_crear))
                .addGap(111, 111, 111))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearActionPerformed

        //String mensaje = "El tipo de ID seleccionado es: ";
        try {
            // TODO add your handling code here:
            int id;
            if(!txf_id.getText().equals("")){
                id = Integer.parseInt(txf_id.getText());
                if(id<0){
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un numero positivo.");
                    id=-1;
                }
            }else{
                id=-1;//Con el valor de -1 se especifica al sistema que el campo esta vacio
                
            }
            
            //String tipoId = txf_tipoId.getText();
            String tipoId = " ";
            if (rbtn_cc.isSelected()) {
                tipoId = "CC";
            }
            if (rbtn_ti.isSelected()) {
                tipoId = "TI";
            }
            if (rbtn_pp.isSelected()) {
               tipoId = "PP";
            }
            //lb_menajeTipoId.setText(mensaje+tipoId);
            String nombres = txf_nombres.getText();
            String apellidos = txf_apellidos.getText();
            String direccion = txf_direccion.getText();
            
            
            if(!verificar(id,nombres,apellidos,direccion)){
                JOptionPane.showMessageDialog(null,"Por favor diligenciar todos los campos");
                
            }else{
                UsuarioDTO paciente = new UsuarioDTO(id, tipoId, nombres, apellidos, direccion,objHabitacion);
                String respuesta = objRemoto.registrarPaciente(paciente);
                if(respuesta.equals("id_existente")){
                JOptionPane.showMessageDialog(null, "El id ya existe");
            }else if(respuesta.equals("Limite_superado")){
                JOptionPane.showMessageDialog(null, "Se ha superado el limite de usuarios registrados.");
            }else if(respuesta.equals("registrado")){
                JOptionPane.showMessageDialog(null, "Se ha registrado el paciente con exito.");
                
                guiMenuMedico.objusuario = paciente;
                guiMenuMedico.darVisibilidad();
                guiMenuMedico.objusuario.setNombres(paciente.getNombres());
                guiMenuMedico.objusuario.setApellidos(paciente.getApellidos());
                guiMenuMedico.objusuario.setTipo_id(paciente.getTipo_id());
                guiMenuMedico.objusuario.setDireccion(paciente.getDireccion());
                guiMenuMedico.cargarInfoUusuario();
                guiMenuMedico.setVisible(true);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error.");
            }
            }
            
          
        } catch (RemoteException ex) {
            Logger.getLogger(RegistarPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_crearActionPerformed

    private void bnt_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_volverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        MenuMedico menu;
        if (rol == 1) {
            menu = new MenuMedico(objRemoto);
        } else {
            menu = new MenuMedico(objRemoto, cm);
        }

        menu.setVisible(true);
    }//GEN-LAST:event_bnt_volverActionPerformed

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
            java.util.logging.Logger.getLogger(RegistarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistarPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnt_volver;
    private javax.swing.JButton btn_crear;
    private javax.swing.ButtonGroup btng_tiposDeId;
    private javax.swing.JLabel lb_apellidos;
    private javax.swing.JLabel lb_direccion;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_nombres;
    private javax.swing.JLabel lb_tipoId;
    private javax.swing.JRadioButton rbtn_cc;
    private javax.swing.JRadioButton rbtn_pp;
    private javax.swing.JRadioButton rbtn_ti;
    private javax.swing.JTextField txf_apellidos;
    private javax.swing.JTextField txf_direccion;
    private javax.swing.JTextField txf_id;
    private javax.swing.JTextField txf_nombres;
    // End of variables declaration//GEN-END:variables
}
