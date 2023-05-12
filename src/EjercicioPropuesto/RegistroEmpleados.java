
package EjercicioPropuesto;

import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/*
Construya un programa que guarde en una lista simple enlazada, 
la siguiente información de los empleados de una Compañía: 

Código, Nombre, Tipo de Contrato (A plazo fijo, Servicios No personales y Service),  
sueldo, monto asignado por movilidad y minutos de tardanza y permita mostrar la siguiente información:

    > Número de empleados con más de una 15 minutos de tardanzas.

    > Nombre del empleado con el mayor tiempo de tardanzas y que tenga 
      un tipo de contrato por Services.

    > El mayor monto de movilidad asignado a un empleado con contrato a
      Plazo Fijo con un sueldo menor a 1500 soles.
*/

public class RegistroEmpleados extends javax.swing.JFrame {
    
    // Declaramos Variables Locales
    public Nodo ini, fin;
    public Nodo pFound;
    int num = 0;
    
    public class Nodo {
        String codigo;
        String nombre;
        String contrato;
        String sueldo;
        String monMovilidad;
        String minTardanza;
        
        Nodo sig;
        
        public Nodo(String cod, String nom, String cont, String suel, String movi, String tard){
            codigo = cod;
            nombre = nom;
            contrato = cont;
            sueldo = suel;
            monMovilidad = movi;
            minTardanza = tard;
        }
    }
    
    Nodo Buscar (Nodo inicio, String cod) {
        Nodo pos = inicio;
            //Recorremos la lista para encontrar la informacion
            while (pos != null && !cod.equalsIgnoreCase(pos.codigo))
                pos = pos.sig;
        return pos;
    }
    
    int ContMayorQ (Nodo inicio, int nume, int cond) {
        int result = 0;
        Nodo pos = inicio;
        
        // Condiciones -> 1: Sueldo, 2: monMovilidad, 3: minTardanza
        
         if (cond == 1){
            while (pos != null) {
                int nums = Integer.parseInt(pos.codigo);
                if (nums > nume) {
                    result+= 1;
                }
            
                pos = pos.sig;
            }
        }
        
        if (cond == 2) {
            while (pos != null) {
                int nums = Integer.parseInt(pos.monMovilidad);
                if (nums > nume)
                    result+= 1;
                pos = pos.sig;
            }
        }
         
        if (cond == 3) {
            while (pos != null) {
                int nums = Integer.parseInt(pos.minTardanza);
                if (nums > nume){
                    result+= 1;
                }
                pos = pos.sig;
            }
        }
 
        return result;
    }
    
    Nodo InsertarInicio(Nodo inicio, String cod, String nom, String cont, String suel, String movi, String tard){
        Nodo nuevo = new Nodo(cod, nom, cont, suel, movi, tard);
        //Realizamos los enlaces correspondientes
        nuevo.sig = inicio;
        inicio = nuevo;
        return inicio;
    }
    
    void Eliminar(Nodo actual){
        //Creamos un puntero el nodo anterior del actual
        Nodo anterior = ini;
        
        // 1. Buscamos el nodo anterior
        while (anterior.sig != actual && anterior.sig != null){
            anterior = anterior.sig;
        }
        
        // 2. Eliminar el nodo si existe
        if (actual != null){
            if (anterior == actual) 
                ini = actual.sig; // Borra el primero
            else
                anterior.sig = actual.sig; // Borrar en otro sitio
        }
    }
    
    void Encabezado(){
        jTextASalida.setFont(new Font("monospaced", Font.PLAIN, 12));
        jTextASalida.setText("");
        
        //Insertamos encabezado al TextArea
        jTextASalida.append("");
        jTextASalida.append("   N°  CODIGO      NOMBRE Y APELLIDOS      TIP. CONTRATO   SUELDO    MONT. MOVILIDAD   M. TARDANZA\n");
        jTextASalida.append("   ----------------------------------------------------------------------------------------------\n");
    }
    
    void Habilitar(){
        jBActualizar.setEnabled(true);
        jBEliminar.setEnabled(true);
        jBGuardar.setEnabled(false);
    }
    
    void Deshabilitar(){
        jBActualizar.setEnabled(false);
        jBEliminar.setEnabled(false);
        jBGuardar.setEnabled(true);
    }
    
    void LimpiarEntradas(){
        jTextCod.setText("");
        jTextNom.setText("");
        jComboBoxTipContrato.setSelectedIndex(0);
        jTextSueldo.setText("");
        jTextMontMovilidad.setText("");
        jTextMinTardanza.setText("");
        jTextCod.requestFocus();
    }
    
    void VerDatos(){
        //Variable para recorrer la lista
        String cod, nom, cont, s, mov, mintar;
        Nodo aux = ini;
        num = 0;
        //Colocando el Encabezado
        Encabezado();
        //Recorriendo la lista
        while(aux != null){
            cod = aux.codigo;
            nom = aux.nombre;
            cont = aux.contrato;
            s = aux.sueldo;
            mov = aux.monMovilidad;
            mintar = aux.minTardanza;
            
            num++;
            String numera = String.valueOf(num);
            //modificando el tamaÃ±o de la numeraciÃ³n con espacios en blanco a la izquierda
            for(int i = String.valueOf(num).length(); i < 5; i++){
                numera = " " + numera;
            }
            //modificando el tamaÃ±o de la cadena cÃ³digo con espacios en blanco a la derecha
            for(int i = cod.length(); i < 12; i++){
                cod = cod + " ";
            }
            //modificando el tamaÃ±o de la cadena nombre con espacios en blanco a la derecha
            for(int i = nom.length(); i < 25; i++){
                nom = nom + " ";
            }
            
            //modificando el tamaÃ±o de la cadena nombre con espacios en blanco a la derecha
            for(int i = cont.length(); i < 11; i++){
                cont = cont + " ";
            }
            
            // le damos formato al sueldo solo con dos decimales
            DecimalFormat df2 = new DecimalFormat("####.00");
            s = df2.format(Double.valueOf(s));
            //modificando el tamaÃ±o de la cadena sueldo con espacios en blanco a la izquierda
            for(int i = s.length(); i < 9; i++){
                s = s + "  " ;
            }
            
            mov = df2.format(Double.valueOf(mov));
            //modificando el tamaÃ±o de la cadena sueldo con espacios en blanco a la izquierda
            for(int i = mov.length(); i < 8; i++){
                mov = mov + "  " ;
            }
            
            //modificando el tamaÃ±o de la cadena nombre con espacios en blanco a la derecha
            for(int i = mintar.length(); i < 8; i++){
                mintar = " " + mintar;
            }
            
            
            
            //Colocando la informaciÃ³n en el TextArea
            jTextASalida.append(numera + "  " + cod + nom + cont + "S/. " + s + "S/. " + mov + mintar + "\n");
            aux = aux.sig;
        }
    }
    
    String VerificaCampos() 
    {
        if(jTextCod.getText().equals("") || jTextCod.getText().length() >=9)
            return "Codigo";
        else if(jTextNom.getText().equals(""))
            return "Apellidos y Nombres";
        else if (jComboBoxTipContrato.getSelectedItem().toString().equals("-------------- SELECCIONE --------------"))
            return "Tipo de Contrato";
        else if (jTextSueldo.getText().equals("") || Double.parseDouble(jTextSueldo.getText())<=0)
            return "Sueldo";
        
        else if (jTextMontMovilidad.getText().equals("") || Double.parseDouble(jTextMontMovilidad.getText())<=0)
            return "Monto de Movilidad";
        
        else if (jTextMinTardanza.getText().equals(""))
            return "Minitos de tardanza";
        
        //Si todos los jText estan con datos, retornamos un texto vacio.
        else
            return "";
    }
    
    public RegistroEmpleados() {
        initComponents();
        Encabezado();
        Deshabilitar();
        LimpiarEntradas();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextCod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextNom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jBGuardar = new javax.swing.JButton();
        jBConsultar = new javax.swing.JButton();
        jBRestaurar = new javax.swing.JButton();
        jBActualizar = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();
        jComboBoxTipContrato = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextSueldo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextMontMovilidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextMinTardanza = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextASalida = new javax.swing.JTextArea();
        jBMostrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Hack Nerd Font", 0, 14)); // NOI18N
        jLabel3.setText("CODIGO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));
        jPanel1.add(jTextCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 102, 150, 30));

        jLabel4.setFont(new java.awt.Font("Hack Nerd Font", 0, 14)); // NOI18N
        jLabel4.setText("CONTRATO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        jPanel1.add(jTextNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 250, 30));

        jLabel2.setFont(new java.awt.Font("Hack Nerd Font", 0, 14)); // NOI18N
        jLabel2.setText("SUELDO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("REGISTRO DE EMPLEADOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        jBGuardar.setText("GUARDAR");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(jBGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 100, 40));

        jBConsultar.setText("CONSULTAR");
        jBConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConsultarActionPerformed(evt);
            }
        });
        jPanel1.add(jBConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 100, 40));

        jBRestaurar.setText("RESTAURAR");
        jBRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRestaurarActionPerformed(evt);
            }
        });
        jPanel1.add(jBRestaurar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 100, 40));

        jBActualizar.setText("ACTUALIZAR");
        jBActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(jBActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 110, 40));

        jBEliminar.setText("ELIMINAR ");
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(jBEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 110, 40));

        jBSalir.setText("SALIR");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jBSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 110, 40));

        jComboBoxTipContrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------------- SELECCIONE --------------", "PLAZO FIJO", "SERVICIOS NO PERSONALES Y SERVICE" }));
        jComboBoxTipContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipContratoActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxTipContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 250, 30));

        jLabel5.setFont(new java.awt.Font("Hack Nerd Font", 0, 14)); // NOI18N
        jLabel5.setText("NOMBRE");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));
        jPanel1.add(jTextSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 130, 30));

        jLabel6.setFont(new java.awt.Font("Hack Nerd Font", 0, 14)); // NOI18N
        jLabel6.setText("M. MOVILIDAD");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, -1));
        jPanel1.add(jTextMontMovilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 130, 30));

        jLabel7.setFont(new java.awt.Font("Hack Nerd Font", 0, 14)); // NOI18N
        jLabel7.setText("MIN. TARDANZA");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 110, -1));
        jPanel1.add(jTextMinTardanza, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 130, 30));

        jTextASalida.setColumns(20);
        jTextASalida.setRows(5);
        jScrollPane1.setViewportView(jTextASalida);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 560, 200));

        jBMostrar.setText("MOSTRAR");
        jBMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMostrarActionPerformed(evt);
            }
        });
        jPanel1.add(jBMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 100, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        
        String campo;
        
        // Capturamos la informacion de los Objetos
        String cod = jTextCod.getText();
        String nom = jTextNom.getText().toUpperCase();
        String tipContrato = jComboBoxTipContrato.getSelectedItem().toString();
        
        if (tipContrato.equalsIgnoreCase("PLAZO FIJO"))
                tipContrato = "P. FIJO";
        else
            tipContrato = "SNP&S";
        
        String suel = jTextSueldo.getText();
        String montMovi = jTextMontMovilidad.getText();
        String minTardanza = jTextMinTardanza.getText();
        
        campo = VerificaCampos();
         
        if(campo.equals("")){
            //Creamos el nodo de la lista en memoria y colocamos la informacion
            ini = InsertarInicio(ini, cod, nom, tipContrato, suel, montMovi, minTardanza);
            LimpiarEntradas();
            VerDatos();
         } else JOptionPane.showMessageDialog(null, "Verifique los datos en el campo de "+campo);
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultarActionPerformed
        String cod = jTextCod.getText();
        if (cod.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ingrese un codigo por favor.");
        } else {
            //Limpiamos a la funcion que retorna la posicion del dato bueno.
            pFound = Buscar(ini, cod);
            
            if (pFound != null){
                String contrato;
                contrato = pFound.contrato;
                
                if (contrato.equalsIgnoreCase("P. FIJO"))
                    jComboBoxTipContrato.setSelectedIndex(1);
                else
                    jComboBoxTipContrato.setSelectedIndex(2);
                
                jTextNom.setText(pFound.nombre);
                jTextSueldo.setText(pFound.sueldo);
                jTextMontMovilidad.setText(pFound.monMovilidad);
                jTextMinTardanza.setText(pFound.minTardanza);
                // Habilitamos los objetos para eliminar y actualizar
                Habilitar();
            } else {
                JOptionPane.showMessageDialog(this,"El codigo: "+ cod + ", no esta en la lista.");
            }
        }
    }//GEN-LAST:event_jBConsultarActionPerformed

    private void jBRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRestaurarActionPerformed
        LimpiarEntradas();
        Deshabilitar();
    }//GEN-LAST:event_jBRestaurarActionPerformed

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed

        String campo;
        
        campo = VerificaCampos();
         
        if(campo.equals("")){
            
            pFound.codigo = jTextCod.getText();
            pFound.nombre = jTextNom.getText().toUpperCase();
            String contrato = jComboBoxTipContrato.getSelectedItem().toString();
            if (contrato.equalsIgnoreCase("PLAZO FIJO"))
                    contrato = "P. FIJO";
            else
                contrato = "SNP&S";
            pFound.contrato = contrato;
            pFound.sueldo = jTextSueldo.getText();
            pFound.monMovilidad = jTextMontMovilidad.getText();
            pFound.minTardanza = jTextMinTardanza.getText();
            LimpiarEntradas();
            Deshabilitar();
            VerDatos();
            
         } else JOptionPane.showMessageDialog(null, "Verifique los datos en el campo de "+campo);
    }//GEN-LAST:event_jBActualizarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        Eliminar(pFound);
        LimpiarEntradas();
        VerDatos();
        if(ini == null)
            JOptionPane.showMessageDialog(this,"La lista esta vacia.");
        
        Deshabilitar();
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jComboBoxTipContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipContratoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipContratoActionPerformed

    private void jBMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMostrarActionPerformed
        Nodo pos = ini;
        Nodo empleadoMayorTardanza = null;
        int mayorTardanza = 0;
        Nodo empleadoMayorMovilidad = null;
        double mayorMovilidad = 0;
        String mensajeSalida = "";
        
        /* Condiciones -> 1: Sueldo, 2: monMovilidad, 3: minTardanza */
        
        // Número de empleados con más de una 15 minutos de tardanzas.
        int numMayor15 = ContMayorQ(ini, 15, 3);
        
        if (numMayor15 > 0)
            mensajeSalida += "[-] Hay " + numMayor15 + " empleados con mas de 15 minutos de tardanaza.\n\n";
        else
            mensajeSalida += "[+] No hay ningun empleados con mas de 15 minutos de tardanaza.\n\n";
        
        // Nombre del empleado con el mayor tiempo de tardanzas y que tenga un tipo de contrato por Services.
        while (pos != null) {
            if (pos.contrato.equalsIgnoreCase("SNP&S")) {
                int tardanza = Integer.parseInt(pos.minTardanza);
                if (tardanza > mayorTardanza) {
                    mayorTardanza = tardanza;
                    empleadoMayorTardanza = pos;
                }
            }
            pos = pos.sig;
        }
        
        if (empleadoMayorTardanza != null) {
            String nombreEmpleado = empleadoMayorTardanza.nombre;
            
            if (mayorTardanza < 15)
                mensajeSalida += "[+] El Empleado " + nombreEmpleado + ", con contrato Services\n"
                        + "tiene el mayor numero de minutos de tardanzas.\n"
                        + "Con " + mayorTardanza + " minutos.\n\n";
            else
                mensajeSalida += "[-] El Empleado " + nombreEmpleado + ", con contrato Services\n"
                        + "tiene el mayor numero de minutos de tardanzas.\n"
                        + "Con " + mayorTardanza + " minutos.\n\n";
            
        } else {
            mensajeSalida += "[-] No se encontró ningún empleado con contrato por Services.\n\n";
        }
        
        // El mayor monto de movilidad asignado a un empleado con contrato a Plazo Fijo con un sueldo menor a 1500 soles.
        pos = ini;
        while (pos != null) {
            int sueldooo = Integer.parseInt(pos.sueldo);
            if (pos.contrato.equalsIgnoreCase("P. FIJO") && sueldooo < 1500) {
                int movilidad = Integer.parseInt(pos.monMovilidad);
                if (movilidad > mayorMovilidad) {
                    mayorMovilidad = movilidad;
                    empleadoMayorMovilidad = pos;
                }
            }
            pos = pos.sig;
        }
        
        if (empleadoMayorMovilidad != null) {
            double montoMovilidad = Double.parseDouble(empleadoMayorMovilidad.monMovilidad);
            String nombreMovilidad = empleadoMayorMovilidad.nombre;
            
            mensajeSalida += "[+] El Empleado " + nombreMovilidad + ", con contrato a Plazo Fijo y sueldo menor a S/. 1500\n"
                    + "tiene asignado el mayor monto de movilidad.\n"+ "Con S/. " + montoMovilidad + " asignados.\n\n";
        } else {
            mensajeSalida += "[-] No se encontró ningún empleado con contrato a Plazo Fijo y sueldo menor a 1500 soles.";
        }
        
        
        JOptionPane.showMessageDialog(null, mensajeSalida.toUpperCase());
    }//GEN-LAST:event_jBMostrarActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBConsultar;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBMostrar;
    private javax.swing.JButton jBRestaurar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JComboBox<String> jComboBoxTipContrato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextASalida;
    private javax.swing.JTextField jTextCod;
    private javax.swing.JTextField jTextMinTardanza;
    private javax.swing.JTextField jTextMontMovilidad;
    private javax.swing.JTextField jTextNom;
    private javax.swing.JTextField jTextSueldo;
    // End of variables declaration//GEN-END:variables
}
