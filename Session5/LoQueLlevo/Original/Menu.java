
package Vistas;

import Clases.Platillo;
import BD.ConexionBD;
import BD.ServiciosBD;
import Clases.Bebida;
import Clases.Consumible;
import Clases.Cuenta;
import java.awt.Component;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Guillermo Naranjo Ferrara
 */
public class Menu extends javax.swing.JFrame {

    //Se establece conexión con la BD y se crea un objeto de ServiciosBD para hacer las consultas a la BD
    Connection db=ConexionBD.obtenerConexion();
    ServiciosBD sbd=new ServiciosBD();
    Platillo platillo;
    Bebida bebida;
    Clases.Comensal comensal;
    int contador=0;
    
    /**
     * Constructor del objeto Menu(JFrame)
     * @throws SQLException En caso de que no se establezca la conexión con la Base de Datos
     * @throws ClassNotFoundException En caso de que no se encuentra la definición de la Clase 
     */
    public Menu() throws SQLException, ClassNotFoundException {
        initComponents();
        JOptionPane.showMessageDialog(null, "Puede comenzar a ordenar comensal "+1+". Cuando termine presione el botón finalizar.");
        //Se abre una nueva cuenta
        Comensal.cuenta=new Cuenta(Inicio.mesa);
        //Se ocultan algunos componentes del layout        
        jLabel22.setVisible(false);
        jButton7.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jButton12.setVisible(false);
        jButton17.setVisible(false);
        jButton18.setVisible(false);
        jButton19.setVisible(false);
        jButton14.setVisible(false);
        jButton15.setVisible(false);
        jButton16.setVisible(false);
        
        //Se consultan los datos de cada platillo en la BD y se imprimen
        platillo=sbd.consultarPlatillo(db, "Champiñones shan");
        jLabel3.setText(platillo.getnombre());
        jLabel9.setText(platillo.getDescripcion());
        jLabel4.setText("$ "+Integer.toString(platillo.getCosto()));
        
        platillo=sbd.consultarPlatillo(db, "Choriqueso");
        jLabel10.setText(platillo.getnombre());
        jLabel5.setText(platillo.getDescripcion());
        jLabel6.setText("$ "+Integer.toString(platillo.getCosto()));
        
        platillo=sbd.consultarPlatillo(db, "Croquetas de pollo y jamón");
        jLabel11.setText(platillo.getnombre());
        jLabel7.setText(platillo.getDescripcion());
        jLabel8.setText("$ "+Integer.toString(platillo.getCosto()));
        
        platillo=sbd.consultarPlatillo(db, "Frijoles charros estilo Jalisco");
        jLabel13.setText(platillo.getnombre());
        jLabel14.setText(platillo.getDescripcion());
        jLabel15.setText("$ "+Integer.toString(platillo.getCosto()));
        
        platillo=sbd.consultarPlatillo(db, "Sopa de elote con rajas");
        jLabel17.setText(platillo.getnombre());
        jLabel16.setText(platillo.getDescripcion());
        jLabel18.setText("$ "+Integer.toString(platillo.getCosto()));
        
        platillo=sbd.consultarPlatillo(db, "Sopa de pechuga");
        jLabel19.setText(platillo.getnombre());
        jLabel20.setText(platillo.getDescripcion());
        jLabel21.setText("$ "+Integer.toString(platillo.getCosto()));
        
        //Se cera un nuevo objeto comensal
        comensal=new Clases.Comensal(Inicio.mesa.getMesa());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("La Fogata");
        setMinimumSize(new java.awt.Dimension(1100, 725));
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Vivaldi", 0, 36)); // NOI18N
        jLabel1.setText("Menú");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(510, 20, 96, 45);

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnSiguiente);
        btnSiguiente.setBounds(580, 650, 100, 30);

        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        getContentPane().add(btnAnterior);
        btnAnterior.setBounds(460, 650, 90, 30);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Vivaldi", 0, 36)); // NOI18N
        jLabel2.setText("Entradas");
        jLayeredPane2.add(jLabel2);
        jLabel2.setBounds(180, 0, 150, 60);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo5.jpg"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton2);
        jButton2.setBounds(270, 230, 239, 140);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo3.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton1);
        jButton1.setBounds(30, 60, 214, 143);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo9.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton3);
        jButton3.setBounds(40, 399, 206, 140);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Nombre");
        jLayeredPane2.add(jLabel3);
        jLabel3.setBounds(290, 60, 200, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Costo");
        jLayeredPane2.add(jLabel4);
        jLabel4.setBounds(290, 170, 80, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Descripcion");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLayeredPane2.add(jLabel5);
        jLabel5.setBounds(40, 260, 200, 60);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Costo");
        jLayeredPane2.add(jLabel6);
        jLabel6.setBounds(178, 324, 60, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Descripcion");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLayeredPane2.add(jLabel7);
        jLabel7.setBounds(290, 430, 190, 80);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Costo");
        jLayeredPane2.add(jLabel8);
        jLabel8.setBounds(290, 510, 70, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Descripcion");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLayeredPane2.add(jLabel9);
        jLabel9.setBounds(290, 94, 210, 70);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Nombre");
        jLayeredPane2.add(jLabel10);
        jLabel10.setBounds(40, 230, 200, 29);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("Nombre");
        jLayeredPane2.add(jLabel11);
        jLabel11.setBounds(290, 400, 210, 29);

        jLabel12.setFont(new java.awt.Font("Vivaldi", 0, 36)); // NOI18N
        jLabel12.setText("Sopas");
        jLayeredPane2.add(jLabel12);
        jLabel12.setBounds(730, 0, 180, 60);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo7.jpg"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton4);
        jButton4.setBounds(550, 70, 210, 140);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo10.jpg"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton5);
        jButton5.setBounds(800, 230, 210, 140);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo11.jpg"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton6);
        jButton6.setBounds(560, 400, 210, 140);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("Nombre");
        jLayeredPane2.add(jLabel13);
        jLabel13.setBounds(790, 70, 200, 30);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Descripcion");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLayeredPane2.add(jLabel14);
        jLabel14.setBounds(790, 100, 210, 70);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Costo");
        jLayeredPane2.add(jLabel15);
        jLabel15.setBounds(790, 180, 80, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Descripcion");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLayeredPane2.add(jLabel16);
        jLabel16.setBounds(570, 260, 200, 60);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Nombre");
        jLayeredPane2.add(jLabel17);
        jLabel17.setBounds(570, 230, 200, 29);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Costo");
        jLayeredPane2.add(jLabel18);
        jLabel18.setBounds(710, 320, 60, 30);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setText("Nombre");
        jLayeredPane2.add(jLabel19);
        jLabel19.setBounds(800, 400, 210, 29);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Descripcion");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLayeredPane2.add(jLabel20);
        jLabel20.setBounds(800, 430, 190, 80);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Costo");
        jLayeredPane2.add(jLabel21);
        jLabel21.setBounds(800, 510, 70, 30);

        jLabel22.setFont(new java.awt.Font("Vivaldi", 0, 36)); // NOI18N
        jLabel22.setText("Comidas");
        jLayeredPane2.add(jLabel22);
        jLabel22.setBounds(470, 10, 110, 40);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo1.jpg"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton7);
        jButton7.setBounds(30, 60, 210, 140);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo2.jpg"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton8);
        jButton8.setBounds(290, 230, 210, 140);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo4.jpg"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton9);
        jButton9.setBounds(40, 400, 210, 140);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo6.jpg"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton10);
        jButton10.setBounds(550, 60, 210, 160);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo8.jpg"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton11);
        jButton11.setBounds(800, 230, 210, 140);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo12.jpg"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton12);
        jButton12.setBounds(560, 390, 210, 149);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo13.jpg"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton14);
        jButton14.setBounds(30, 60, 210, 140);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo14.jpg"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton15);
        jButton15.setBounds(320, 220, 140, 170);

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Platillo15.jpg"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton16);
        jButton16.setBounds(40, 400, 210, 140);

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Bebida1.jpg"))); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton18);
        jButton18.setBounds(550, 70, 210, 140);

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Bebida2.jpg"))); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton17);
        jButton17.setBounds(800, 230, 210, 140);

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Bebida3.jpg"))); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton19);
        jButton19.setBounds(560, 400, 210, 140);

        getContentPane().add(jLayeredPane2);
        jLayeredPane2.setBounds(30, 70, 1030, 560);

        jButton13.setText("Finalizar Orden");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13);
        jButton13.setBounds(940, 650, 120, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método para realizar la acción del botón siguiente para pasar a la siguiente página del menú
     * @param evt El evento que realiza el botón al ser clickeado
     */
    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        //En caso de que se encuentre en la primer página
        if(jLabel2.isVisible() && jLabel12.isVisible()){
            //Se quita la visibilidad de los platillos de la primera página
            jButton1.setVisible(false);
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            jButton4.setVisible(false);
            jButton5.setVisible(false);
            jButton6.setVisible(false);
            jLabel2.setVisible(false);
            jLabel12.setVisible(false);
            //Se hacen visibles los platillos de la segunda página
            jLabel22.setVisible(true);
            jButton7.setVisible(true);
            jButton8.setVisible(true);
            jButton9.setVisible(true);
            jButton10.setVisible(true);
            jButton11.setVisible(true);
            jButton12.setVisible(true);
            
            //Se consultan los datos de cada platillo en la BD y se imprimen
            try {
                platillo=sbd.consultarPlatillo(db, "Costillas de cerdo glaseadas");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel3.setText(platillo.getnombre());
            jLabel9.setText(platillo.getDescripcion());
            jLabel4.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Pechugas rellenas de queso crema y espinacas");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel10.setText(platillo.getnombre());
            jLabel5.setText(platillo.getDescripcion());
            jLabel6.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Pechuga de pollo marinada");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel11.setText(platillo.getnombre());
            jLabel7.setText(platillo.getDescripcion());
            jLabel8.setText("$ "+Integer.toString(platillo.getCosto()));
            
            try {
                platillo=sbd.consultarPlatillo(db, "Brochetas de pollo y piña");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel13.setText(platillo.getnombre());
            jLabel14.setText(platillo.getDescripcion());
            jLabel15.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Filete Mignon con salsa Bernesa");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel17.setText(platillo.getnombre());
            jLabel16.setText(platillo.getDescripcion());
            jLabel18.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Pechuga a la Cordon Blue");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel19.setText(platillo.getnombre());
            jLabel20.setText(platillo.getDescripcion());
            jLabel21.setText("$ "+Integer.toString(platillo.getCosto()));
        }
        
       //En caso de que se encuentre en la segunda página
        else if(jLabel22.isVisible() && !jLabel2.isVisible() && !jLabel12.isVisible()){
            //Se hacen visibles los botones de platillos de la página 3 y se cambian los títulos
            jLabel2.setVisible(true);
            jLabel12.setVisible(true);
            jLabel22.setVisible(false);
            jLabel2.setText("Postres");
            jLabel12.setText("Bebidas");
            jButton16.setVisible(true);
            jButton15.setVisible(true);
            jButton14.setVisible(true);
            jButton17.setVisible(true);
            jButton18.setVisible(true);
            jButton19.setVisible(true);
            //Se ocultan los botones de los platillos de la página 2
            jButton7.setVisible(false);
            jButton8.setVisible(false);
            jButton9.setVisible(false);
            jButton10.setVisible(false);
            jButton11.setVisible(false);
            jButton12.setVisible(false);
            
            //Se consultan los datos de cada platillo en la BD y se imprimen
            try {
                platillo=sbd.consultarPlatillo(db, "Pastel de almendra");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel3.setText(platillo.getnombre());
            jLabel9.setText(platillo.getDescripcion());
            jLabel4.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Souffle de queso");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel10.setText(platillo.getnombre());
            jLabel5.setText(platillo.getDescripcion());
            jLabel6.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Helado de chocomenta");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel11.setText(platillo.getnombre());
            jLabel7.setText(platillo.getDescripcion());
            jLabel8.setText("$ "+Integer.toString(platillo.getCosto()));
            
            try {
                bebida=sbd.consultarBebida(db, "Frapuccino de cajeta");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel13.setText(bebida.getnombre());
            jLabel14.setText(bebida.getDescripcion()+"\n"+bebida.getTamaño());
            jLabel15.setText("$ "+Integer.toString(bebida.getCosto()));

            try {
                bebida=sbd.consultarBebida(db, "Agua de Horchata");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel17.setText(bebida.getnombre());
            jLabel16.setText(bebida.getDescripcion()+"\n"+bebida.getTamaño());
            jLabel18.setText("$ "+Integer.toString(bebida.getCosto()));

            try {
                bebida=sbd.consultarBebida(db, "Frappé de zarzamora con lavanda y té negro");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel19.setText(bebida.getnombre());
            jLabel20.setText(bebida.getDescripcion()+"\n"+bebida.getTamaño());
            jLabel21.setText("$ "+Integer.toString(bebida.getCosto()));
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Champiñones shan");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Champiñones shan' se ha agregado a su orden");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Choriqueso");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Choriqueso' se ha agregado a su orden");
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Croquetas de pollo y jamón");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Croquetas de pollo y jamón' se ha agregado a su orden");
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Frijoles charros estilo Jalisco");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Frijoles charros estilo Jalisco' se ha agregado a su orden");
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Sopa de elote con rajas");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Sopa de elote con rajas' se ha agregado a su orden");
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Sopa de pechuga");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Sopa de pechuga' se ha agregado a su orden");
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Pechuga de pollo marinada");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Pechuga de pollo marinada' se ha agregado a su orden");
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * Método para realizar la acción del botón anterior para pasar a la página anterior del menú
     * @param evt El evento que realiza el botón al ser clickeado
     */
    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        //En caso de que el menú se encuentre en la página 2
        if(jLabel22.isVisible()){
            //Se hacen visibles botones de los platillos de la página 1, así como los títulos
            jButton1.setVisible(true);
            jButton2.setVisible(true);
            jButton3.setVisible(true);
            jButton4.setVisible(true);
            jButton5.setVisible(true);
            jButton6.setVisible(true);
            jLabel2.setText("Entradas");
            jLabel12.setText("Sopas");
            jLabel2.setVisible(true);
            jLabel12.setVisible(true);
            jLabel22.setVisible(false);
            //Se ocultan los botones de los platillos de la página 2
            jButton7.setVisible(false);
            jButton8.setVisible(false);
            jButton9.setVisible(false);
            jButton10.setVisible(false);
            jButton11.setVisible(false);
            jButton12.setVisible(false);
            
            //Se consultan los datos de cada platillo en la BD y se imprimen
            try {
                platillo=sbd.consultarPlatillo(db, "Champiñones shan");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel3.setText(platillo.getnombre());
            jLabel9.setText(platillo.getDescripcion());
            jLabel4.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Choriqueso");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel10.setText(platillo.getnombre());
            jLabel5.setText(platillo.getDescripcion());
            jLabel6.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Croquetas de pollo y jamón");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel11.setText(platillo.getnombre());
            jLabel7.setText(platillo.getDescripcion());
            jLabel8.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Frijoles charros estilo Jalisco");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel13.setText(platillo.getnombre());
            jLabel14.setText(platillo.getDescripcion());
            jLabel15.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Sopa de elote con rajas");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel17.setText(platillo.getnombre());
            jLabel16.setText(platillo.getDescripcion());
            jLabel18.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Sopa de pechuga");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel19.setText(platillo.getnombre());
            jLabel20.setText(platillo.getDescripcion());
            jLabel21.setText("$ "+Integer.toString(platillo.getCosto()));
        }
        
        //En caso de que el menú se encuentre en la página 3
        else if(jLabel2.isVisible() && jLabel12.isVisible()){
            //Se ocultan los botones de los platillos de la página 3
            jButton17.setVisible(false);
            jButton18.setVisible(false);
            jButton19.setVisible(false);
            jButton14.setVisible(false);
            jButton15.setVisible(false);
            jButton16.setVisible(false);
            jLabel2.setVisible(false);
            jLabel12.setVisible(false);
            //Se hacen visibles los platillos de la segunda página
            jLabel22.setVisible(true);
            jButton7.setVisible(true);
            jButton8.setVisible(true);
            jButton9.setVisible(true);
            jButton10.setVisible(true);
            jButton11.setVisible(true);
            jButton12.setVisible(true);
            
            //Se consultan los datos de cada platillo en la BD y se imprimen
            try {
                platillo=sbd.consultarPlatillo(db, "Costillas de cerdo glaseadas");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel3.setText(platillo.getnombre());
            jLabel9.setText(platillo.getDescripcion());
            jLabel4.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Pechugas rellenas de queso crema y espinacas");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel10.setText(platillo.getnombre());
            jLabel5.setText(platillo.getDescripcion());
            jLabel6.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Pechuga de pollo marinada");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel11.setText(platillo.getnombre());
            jLabel7.setText(platillo.getDescripcion());
            jLabel8.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Brochetas de pollo y piña");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel13.setText(platillo.getnombre());
            jLabel14.setText(platillo.getDescripcion());
            jLabel15.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Filete Mignon con salsa Bernesa");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel17.setText(platillo.getnombre());
            jLabel16.setText(platillo.getDescripcion());
            jLabel18.setText("$ "+Integer.toString(platillo.getCosto()));

            try {
                platillo=sbd.consultarPlatillo(db, "Pechuga a la Cordon Blue");
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel19.setText(platillo.getnombre());
            jLabel20.setText(platillo.getDescripcion());
            jLabel21.setText("$ "+Integer.toString(platillo.getCosto()));
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentAdded

    /**
     * Método para realizar la acción del botón que finaliza la orden de un cliente 
     * @param evt El evento que realiza el botón al ser clickeado
     */
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Inicio.mesa.setComensal(comensal);
        try {
            sbd.ingresarComensal(db, comensal);
            Comensal.cuenta.setSubtotal(comensal);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        contador++;
        if(contador==Inicio.mesa.getComensales()){
            try {
                jButton13.setEnabled(false);
                Comensal.bandera=1;
                this.dispose();
                Comensal comen=new Comensal();
                comen.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                comensal=new Clases.Comensal(Inicio.mesa.getMesa());
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Puede comenzar a ordenar comensal "+(contador+1)+". Cuando termine presione el botón finalizar.");
        }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Pastel de almendra");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Pastel de almendra' se ha agregado a su orden");
    }//GEN-LAST:event_jButton14ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado
     */
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Helado de chocomenta");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Helado de chocomenta' se ha agregado a su orden");
    }//GEN-LAST:event_jButton16ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Costillas de cerdo glaseadas");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Costillas de cerdo glaseadas' se ha agregado a su orden");
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Pechugas rellenas de queso crema y espinacas");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Pechugas rellenas de queso crema y espinacas' se ha agregado a su orden");
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Brochetas de pollo y piña");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Brochetas de pollo y piña' se ha agregado a su orden");
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Filete Mignon con salsa Bernesa");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Filete Mignon con salsa Bernesa' se ha agregado a su orden");
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Pechuga a la Cordon Blue");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Pechuga a la Cordon Blue' se ha agregado a su orden");
    }//GEN-LAST:event_jButton12ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa un nuevo platillo a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        try {
            platillo=sbd.consultarPlatillo(db, "Souffle de queso");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Platillo(platillo.getnombre(), platillo.getDescripcion(),  
                platillo.getCosto(), platillo.getId(), platillo.getTipo());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "El platillo 'Souffle de queso' se ha agregado a su orden");
    }//GEN-LAST:event_jButton15ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa una nueva bebida a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        try {
            bebida=sbd.consultarBebida(db, "Frapuccino de cajeta");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Bebida(bebida.getnombre(), bebida.getDescripcion(),  
                bebida.getCosto(), bebida.getId(), bebida.getTamaño());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "La bebida 'Frapuccino de cajeta' se ha agregado a su orden");
    }//GEN-LAST:event_jButton18ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa una nueva bebida a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try {
            bebida=sbd.consultarBebida(db, "Agua de Horchata");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Bebida(bebida.getnombre(), bebida.getDescripcion(),  
                bebida.getCosto(), bebida.getId(), bebida.getTamaño());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "La bebida 'Agua de Horchata' se ha agregado a su orden");
    }//GEN-LAST:event_jButton17ActionPerformed

    /**
     * Método para realizar acción del boton que ingresa una nueva bebida a la orden del comensal
     * @param evt El evento que realiza el botón al ser clickeado 
     */
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        try {
            bebida=sbd.consultarBebida(db, "Frappé de zarzamora con lavanda y té negro");
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumible consum=new Bebida(bebida.getnombre(), bebida.getDescripcion(),  
                bebida.getCosto(), bebida.getId(), bebida.getTamaño());
        comensal.setConsumible(consum);
        JOptionPane.showMessageDialog(null, "La bebida 'Frappé de zarzamora con lavanda y té negro' se ha agregado a su orden");
    }//GEN-LAST:event_jButton19ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Menu().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane2;
    // End of variables declaration//GEN-END:variables
}
