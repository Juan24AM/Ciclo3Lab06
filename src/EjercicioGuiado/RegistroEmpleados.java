
package EjercicioGuiado;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;


    /*
        Construya una aplicación que permita realizar el registro 
        de empleados donde se podrá guardar, mostrar, consultar, 
        actualizar y eliminar el registro de empleado. 
        Para todas estas operaciones considere el 
        ingreso del código del empleado
    */

public class RegistroEmpleados extends javax.swing.JFrame {
    
    
    public class Nodo {
        String codigo;
        String nombre;
        String sueldo;
        Nodo sig;
        
        public Nodo(String cod, String nom, String suel){
            codigo = cod;
            nombre = nom;
            sueldo = suel;
        }
        
    }
    
    Nodo Buscar (Nodo inicio, String cod) {
        Nodo pos = inicio;
            //Recorremos la lista para encontrar la informacion
            while (pos != null && !cod.equalsIgnoreCase(pos.codigo))
                pos = pos.sig;
        return pos;
    }
    
    Nodo InsertarInicio(Nodo inicio, String cod, String nom, String suel){
        Nodo nuevo = new Nodo(cod, nom, suel);
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
    
    // Declaramos Variables Locales
    public Nodo ini, fin;
    public Nodo pFound;
    int num = 0;
    
    
    public RegistroEmpleados() {
        initComponents();
        // Colocamos el encabezado
        Encabezado();
        
        // Deshabilitamos los objetos
        Deshabilitar();
    }
    
    void Encabezado(){
        jTextReporte.setFont(new Font("monospaced", Font.PLAIN, 12));
        jTextReporte.setText("");
        
        //Insertamos encabezado al TextArea
        jTextReporte.append("");
        jTextReporte.append("   N°      CODIGO          NOMBRE Y APELLIDOS          SUELDO\n");
        jTextReporte.append("   ------------------------------------------------------------\n");
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
        jTextSueldo.setText("");
        jTextCod.requestFocus();
    }
    
    String VerificaCampos() 
    {
        if(jTextCod.getText().equals("") || jTextCod.getText().length() >=9)
            return "Codigo";
        else if(jTextNom.getText().equals(""))
            return "Apellidos y Nombres";
        else if (jTextSueldo.getText().equals("") || Double.parseDouble(jTextSueldo.getText())<=0)
            return "Sueldo";
        //Si todos los jText estan con datos, retornamos un texto vacio.
        else
            return "";
    }
    
    void VerDatos(){
        //Variable para recorrer la lista
        String cod, nom, s;
        Nodo aux = ini;
        num = 0;
        //Colocando el Encabezado
        Encabezado();
        //Recorriendo la lista
        while(aux != null){
            cod=aux.codigo;
            nom=aux.nombre;
            s=aux.sueldo;
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
            for(int i = nom.length(); i < 28; i++){
                nom = nom + " ";
            }
            // le damos formato al sueldo solo con dos decimales
            DecimalFormat df2 = new DecimalFormat("####.00");
            s = df2.format(Double.valueOf(s));
            //modificando el tamaÃ±o de la cadena sueldo con espacios en blanco a la izquierda
            for(int i = s.length(); i < 12; i++){
                s = "  " + s;
            }
            //Colocando la informaciÃ³n en el TextArea
            jTextReporte.append(numera + "  " + cod + nom + s + "\n");
            aux = aux.sig;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextSueldo = new javax.swing.JTextField();
        jTextCod = new javax.swing.JTextField();
        jTextNom = new javax.swing.JTextField();
        jBEliminar = new javax.swing.JButton();
        jBGuardar = new javax.swing.JButton();
        jBConsultar = new javax.swing.JButton();
        jBRestaurar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();
        jBActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextReporte = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("REGISTRO DE EMPLEADOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Hack Nerd Font", 0, 14)); // NOI18N
        jLabel2.setText("SUELDO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel3.setFont(new java.awt.Font("Hack Nerd Font", 0, 14)); // NOI18N
        jLabel3.setText("CODIGO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Hack Nerd Font", 0, 14)); // NOI18N
        jLabel4.setText("NOMBRE");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));
        jPanel1.add(jTextSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 130, 30));
        jPanel1.add(jTextCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 102, 130, 30));
        jPanel1.add(jTextNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 200, 30));

        jBEliminar.setText("ELIMINAR ");
        jBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(jBEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 110, 40));

        jBGuardar.setText("GUARDAR");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(jBGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 100, 40));

        jBConsultar.setText("CONSULTAR");
        jBConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConsultarActionPerformed(evt);
            }
        });
        jPanel1.add(jBConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 100, 40));

        jBRestaurar.setText("RESTAURAR");
        jBRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRestaurarActionPerformed(evt);
            }
        });
        jPanel1.add(jBRestaurar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 100, 40));

        jBSalir.setText("SALIR");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jBSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 110, 40));

        jBActualizar.setText("ACTUALIZAR");
        jBActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(jBActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 110, 40));

        jTextReporte.setColumns(20);
        jTextReporte.setRows(5);
        jScrollPane1.setViewportView(jTextReporte);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 520, 220));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        String campo;
        
        // Capturamos la informacion de los Objetos
        String cod = jTextCod.getText();
        String nom = jTextNom.getText().toUpperCase();
        String suel = jTextSueldo.getText();
        
        campo = VerificaCampos();
         
         if(campo.equals("")){
            //Creamos el nodo de la lista en memoria y colocamos la informacion
            ini = InsertarInicio(ini, cod, nom, suel);
            LimpiarEntradas();
            VerDatos();
         } else JOptionPane.showMessageDialog(null, "Verifique los datos en el campo de "+campo);
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        pFound.codigo = jTextCod.getText();
        pFound.nombre = jTextNom.getText().toUpperCase();
        pFound.sueldo = jTextSueldo.getText();
        LimpiarEntradas();
        Deshabilitar();
        VerDatos();
    }//GEN-LAST:event_jBActualizarActionPerformed

    private void jBConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultarActionPerformed
        String cod = jTextCod.getText();
        if (cod.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ingrese un codigo por favor.");
        } else {
            //Limpiamos a la funcion que retorna la posicion del dato bueno.
            pFound = Buscar(ini, cod);
            
            if (pFound != null){
                jTextNom.setText(pFound.nombre);
                jTextSueldo.setText(pFound.sueldo);
                
                // Habilitamos los objetos para eliminar y actualizar
                Habilitar();
            } else {
                JOptionPane.showMessageDialog(this,"El codigo: "+ cod + ", no esta en la lista.");
            }
        }
    }//GEN-LAST:event_jBConsultarActionPerformed

    private void jBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarActionPerformed
        Eliminar(pFound);
        LimpiarEntradas();
        VerDatos();
        if(ini == null)
            JOptionPane.showMessageDialog(this,"La lista esta vacia.");
        
        Deshabilitar();
    }//GEN-LAST:event_jBEliminarActionPerformed

    private void jBRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRestaurarActionPerformed
        LimpiarEntradas();
        Deshabilitar();
    }//GEN-LAST:event_jBRestaurarActionPerformed


    public static void main(String args[]) {

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
    private javax.swing.JButton jBRestaurar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextCod;
    private javax.swing.JTextField jTextNom;
    private javax.swing.JTextArea jTextReporte;
    private javax.swing.JTextField jTextSueldo;
    // End of variables declaration//GEN-END:variables
}
