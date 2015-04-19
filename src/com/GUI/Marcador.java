/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.GUI;
import com.Core.Partido;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/**
 *
 * @author Junior C
 */
public class Marcador extends javax.swing.JFrame {

    
    private DefaultComboBoxModel modelocombo;
    public static DefaultListModel modelo; // MODELO QUE CONTENDRA LOS EVENTOS QUE SUCEDAN EN EL PARTIDO 
    public static int ss,mm,hh;  // SS HH Y MM REPRESENTAN LAS VARAIBLES APRA LOS SEGUNDOS MINTOS Y HORA DEL PARTIDO
    private Partido p;
    public static Calendar can = Calendar.getInstance();
    public static Calendar cann = new GregorianCalendar();
    public static int h,m,s; // Representan la hora, minuto y segundo del sistema actual
    private int flagPausa;
    private boolean faltaE1,jugadaE1,faltaE2,jugadaE2; // varaibles apra controlar las selecciones de los Jcombobox, para saber cual esta seleccionado
    private int se1,se2;
    
   
    public Marcador(Partido p) {
        initComponents();
          this.p = p; // ASIGNA LA VARIABLE P (PARTIDO) DE LA CLASE HOME A LA VARIABLE PARTIDO DE ESTA CLASE
        
        faltaE1 = false;
        jugadaE1 = false;
        faltaE2 = false;
        jugadaE2 = false;
        flagPausa = 1;
        h = 0;
        s = 0; 
        m = 0;
        ss = 0;  
        mm = 0;
        hh = 0;
        
        modelocombo = new DefaultComboBoxModel();
        
        NjugadoresE1.setModel(modelocombo);
        
        NjugadoresE2.setModel(modelocombo);
        
        switch(p.getNumeroJugadores())  // añade al combobox los numeros del 1 al (numero de jugadores seleccionado acteriormente)
        {
            case 2 : {
                     for(int i = 1; i <= 2; i++) modelocombo.addElement(i);
                    
                  }
            break;
            case 7 : {
                     for(int i = 1; i <= 7; i++) modelocombo.addElement(i);
                    
                  }
            break;
            case 8 : {
                     for(int i = 1; i <= 8; i++) modelocombo.addElement(i);
                  
                  }
            break;
                case 9 : {
                     for(int i = 1; i <= 9; i++) modelocombo.addElement(i);
                   
                  }
            break;
                    case 10 : {
                     for(int i = 1; i <= 10; i++) modelocombo.addElement(i);
                     
                  }
            break;
                        case 11 : {
                     for(int i = 1; i <= 11; i++) modelocombo.addElement(i);
                     
                  }
            break;
                
               
        }
        
       
        equipo1.setText(p.getEquipo1().getNombre()); // SE SETEAN LAS NOMBRE Y PAIS DE LOS EQUIPOS EN EL MARCADOR
        equipo2.setText(p.getEquipo2().getNombre());
        pais1.setText(p.getEquipo1().getPais());
        pais2.setText(p.getEquipo2().getPais());
        modelo = new DefaultListModel(); 
        lista.setModel(modelo); // SE LE ENVIA A LA LISTA ESOS EVENTOS APRA MORTARLOS
        
        
         selectorFaltaE1.setBackground(Color.BLUE);
         selectorJugadaE1.setBackground(Color.BLUE);
         NjugadoresE1.setBackground(Color.BLUE);
         NjugadoresE2.setBackground(Color.GREEN);
         selectorFaltaE2.setBackground(Color.GREEN);
         selectorJugadaE2.setBackground(Color.GREEN);
        
        this.getContentPane().setBackground(Color.white); // PONE EL COLOR DEL FORMULARIO EN BLANCO
        setLocationRelativeTo(null);
        
    }
    
     public static String getHoraActual(){
            
              h = cann.get(Calendar.HOUR_OF_DAY);
              m = cann.get(Calendar.MINUTE);
              s = cann.get(Calendar.SECOND);
              
              return (h+":"+m+":"+s);
        }
        
        
        public String generateRandom() // Funcion que genera un codigo aleatorio de 3 digitos que tendra el nombre dle archivo txt
        {
            StringBuffer ss = new StringBuffer();
            for(int i = 0; i < 3; i++) ss.append(Math.round(Math.random() * 9));
            
            return ss.toString();
        }
        
        public static String getTiempoPartido(){
            
            if(ss > 9 && mm > 9) return ("0"+hh+":"+mm+":"+ss);
            
            else if(ss < 10 && mm < 10)return ("0"+hh+":"+"0"+mm+":"+"0"+ss);
            
            else if(ss > 9 && mm < 10) return ("0"+hh+":"+"0"+mm+":"+ss);
            
            else return ("0"+hh+":"+mm+":0"+ss);
        }
        
        public String tipoJugada(int n)
        {
            if(n != 0 && n!= 1 && n != 3 && n != 15)
            {
                if(n <= 3) return "//Jugada ANOTACION";
                else if(n >= 4 || n <= 14) return "//Jugada OFENSIVA";
                else return "//Jugada DEFENSIVA";
               
            }
           return null;
        }
        
        public String insertarFaltaJugadaE1(){
            
            int n = Integer.parseInt(NjugadoresE1.getSelectedItem().toString());
            int k = selectorJugadaE1.getSelectedIndex();
            
            if(faltaE1 == true){
                
                return ("** "+getTiempoPartido()+" "+p.getEquipo1().getNombreApellJugador(n)+" Equipo "+p.getEquipo1().getNombre()+" // Falta // "
                        +selectorFaltaE1.getSelectedItem().toString() );
            }
           else{
               
                 if(selectorJugadaE1.getSelectedIndex() == 2) p.getEquipo1().setScore(++se1);
                 if(p.getEquipo1().getScore()< 10)scoreE1.setText("0"+String.valueOf(p.getEquipo1().getScore()));
                 else scoreE1.setText(String.valueOf(p.getEquipo1().getScore()));
                
                return ("** "+getTiempoPartido()+" "+p.getEquipo1().getNombreApellJugador(n)+" Equipo "+p.getEquipo1().getNombre()+" "+tipoJugada(k)+" "
                        +selectorJugadaE1.getSelectedItem().toString() );
            }
        }
        
        public String insertarFaltaJugadaE2(){
            
            int n = Integer.parseInt(NjugadoresE2.getSelectedItem().toString());
             int k = selectorJugadaE2.getSelectedIndex();
            if(faltaE2 == true){
                
                return ("** "+getTiempoPartido()+" "+p.getEquipo2().getNombreApellJugador(n)+" Equipo "+p.getEquipo2().getNombre()+" // Falta // "
                        +selectorFaltaE2.getSelectedItem().toString() );
            }
           else{
               
                 if(selectorJugadaE2.getSelectedIndex() == 2) p.getEquipo1().setScore(++se2);
                 if(p.getEquipo2().getScore() < 10)scoreE2.setText("0"+String.valueOf(p.getEquipo2().getScore()));
                 else scoreE2.setText(String.valueOf(p.getEquipo2().getScore()));
                
                return ("** "+getTiempoPartido()+" "+p.getEquipo2().getNombreApellJugador(n)+" Equipo "+p.getEquipo2().getNombre()+" "+tipoJugada(k)+" "
                        +selectorJugadaE2.getSelectedItem().toString() );
            }
        }
        
        
        /*
        public String nombreJugador(int n,ArrayList<Jugador> jug)
        {
            
                for(Jugador j : jug)
                    if(j.getNumero() == n) return j.getNombre()+" "+j.getApellido();
        
                return null;
        }
        */
        public void crearArchivo(){
            
             String nombre;
             nombre = generateRandom()+" Partido "+p.getEquipo1().getNombre()+" VS "+p.getEquipo2().getNombre();
          
             
            File fi = new File(nombre+".txt");
           
            if(!fi.exists()){
                
                try{
                     fi.createNewFile();
                    FileWriter f = new FileWriter(fi);
                  
                 for(int i = 0; i < lista.getModel().getSize(); i++)
                {
               
                  f.write((String) lista.getModel().getElementAt(i)); 
                  f.write("\r\n");
                }
                f.close();
                   
                    
                }catch(IOException e){ JOptionPane.showMessageDialog(rootPane, "NO SE PUDO CREAR EL ARCHIVO");}
               
            }
             else 
            {
              try{
                     fi = new File(nombre+" "+generateRandom()+".txt");
                     fi.createNewFile();
                    FileWriter f = new FileWriter(fi);
                  
                 for(int i = 0; i < lista.getModel().getSize(); i++)
                {
               
                  f.write((String) lista.getModel().getElementAt(i)); 
                  f.write("\r\n");
                }
                f.close();
                   
                    
                }catch(IOException e){ JOptionPane.showMessageDialog(rootPane, "NO SE PUDO CREAR EL ARCHIVO");}
                
                
            }
            
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        tiempo = new javax.swing.JLabel();
        tiempoTexto = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        scoreE1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        equipo1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        pais1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        selectorJugadaE1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        insertar = new javax.swing.JButton();
        selectorFaltaE1 = new javax.swing.JComboBox();
        NjugadoresE1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        scoreE2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        equipo2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        pais2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        selectorJugadaE2 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        selectorFaltaE2 = new javax.swing.JComboBox();
        insertar2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        NjugadoresE2 = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        tiempo1 = new javax.swing.JLabel();
        tiempoTexto1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        scoreE3 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        equipo3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        selectorJugadaE3 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        insertar1 = new javax.swing.JButton();
        selectorFaltaE3 = new javax.swing.JComboBox();
        NjugadoresE3 = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        scoreE4 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        equipo4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        selectorJugadaE4 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        selectorFaltaE4 = new javax.swing.JComboBox();
        insertar3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        NjugadoresE4 = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        jPanel16 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jPanel7.setBackground(new java.awt.Color(0, 0, 255));

        tiempo.setFont(new java.awt.Font("RussellSquare", 0, 24)); // NOI18N
        tiempo.setForeground(new java.awt.Color(255, 255, 255));
        tiempo.setText("00:00:00");

        tiempoTexto.setFont(new java.awt.Font("RussellSquare", 0, 24)); // NOI18N
        tiempoTexto.setForeground(new java.awt.Color(255, 255, 255));
        tiempoTexto.setText("Tiempo:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addComponent(tiempoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tiempo)
                    .addComponent(tiempoTexto))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scoreE1.setEditable(false);
        scoreE1.setBackground(new java.awt.Color(102, 0, 0));
        scoreE1.setFont(new java.awt.Font("RussellSquare", 0, 72)); // NOI18N
        scoreE1.setForeground(new java.awt.Color(255, 51, 0));
        scoreE1.setText("00");
        jPanel1.add(scoreE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 100, 70));

        jPanel4.setBackground(new java.awt.Color(0, 0, 255));

        equipo1.setFont(new java.awt.Font("RussellSquare", 0, 14)); // NOI18N
        equipo1.setForeground(new java.awt.Color(255, 255, 255));
        equipo1.setText("jLabel5");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(equipo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(equipo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 280, -1));

        jLabel3.setFont(new java.awt.Font("RussellSquare", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Score");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        jPanel5.setBackground(new java.awt.Color(0, 0, 255));

        pais1.setFont(new java.awt.Font("RussellSquare", 0, 14)); // NOI18N
        pais1.setForeground(new java.awt.Color(255, 255, 255));
        pais1.setText("jLabel5");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(pais1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pais1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 280, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("falta");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("jugada");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        selectorJugadaE1.setBackground(new java.awt.Color(0, 0, 255));
        selectorJugadaE1.setForeground(new java.awt.Color(255, 255, 255));
        selectorJugadaE1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Anotacion----------------", "GOAL!!!!!!", "Ofensivas----------------", "Despeje", "Saques de banda", "Saques de portería", "Saques de esquina", "Romper el fuera de juego", "Cambiar el juego", "Pase y movimiento", "Agujero defensive", "Triangulacion", "Intercambio de bandas", "Hombre clave", "Defensiva--------------------", "Posiciones adelantadas", "Tiros libres de corto alcance", "Saques de esquina y otros cruces", "Penaltis", "Juego defensivo hombre a hombre", " lanzamiento cruzado", " " }));
        selectorJugadaE1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorJugadaE1ItemStateChanged(evt);
            }
        });
        selectorJugadaE1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectorJugadaE1MouseClicked(evt);
            }
        });
        selectorJugadaE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorJugadaE1ActionPerformed(evt);
            }
        });
        selectorJugadaE1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                selectorJugadaE1PropertyChange(evt);
            }
        });
        jPanel1.add(selectorJugadaE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 210, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("por jugador #");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        insertar.setBackground(new java.awt.Color(0, 0, 255));
        insertar.setForeground(new java.awt.Color(255, 255, 255));
        insertar.setText("Insertar");
        insertar.setBorderPainted(false);
        insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActionPerformed(evt);
            }
        });
        jPanel1.add(insertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, -1, 20));

        selectorFaltaE1.setBackground(new java.awt.Color(0, 0, 255));
        selectorFaltaE1.setForeground(new java.awt.Color(255, 255, 255));
        selectorFaltaE1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Patada a adversario", " zancadilla a un adversario", "salto sobre un adversario", "cargar contra un adversario", " golpear a un adversario", "empujar a un adversario", "sujetar a un adversario", "escupir a un adversario", "tocar el balón deliberadamente con las manos" }));
        selectorFaltaE1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorFaltaE1ItemStateChanged(evt);
            }
        });
        selectorFaltaE1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                selectorFaltaE1PopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        selectorFaltaE1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectorFaltaE1MouseClicked(evt);
            }
        });
        selectorFaltaE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorFaltaE1ActionPerformed(evt);
            }
        });
        selectorFaltaE1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                selectorFaltaE1PropertyChange(evt);
            }
        });
        jPanel1.add(selectorFaltaE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 210, -1));

        NjugadoresE1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(NjugadoresE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 60, -1));

        jPanel2.setBackground(new java.awt.Color(0, 153, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scoreE2.setEditable(false);
        scoreE2.setBackground(new java.awt.Color(102, 0, 0));
        scoreE2.setFont(new java.awt.Font("RussellSquare", 0, 72)); // NOI18N
        scoreE2.setForeground(new java.awt.Color(255, 51, 0));
        scoreE2.setText("00");
        jPanel2.add(scoreE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 100, 70));

        jPanel3.setBackground(new java.awt.Color(153, 255, 102));

        equipo2.setFont(new java.awt.Font("RussellSquare", 0, 14)); // NOI18N
        equipo2.setText("jLabel5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(equipo2)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(equipo2)
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 280, -1));

        jLabel2.setFont(new java.awt.Font("RussellSquare", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Score");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jPanel6.setBackground(new java.awt.Color(153, 255, 102));

        pais2.setFont(new java.awt.Font("RussellSquare", 0, 14)); // NOI18N
        pais2.setText("jLabel5");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pais2)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pais2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 280, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("falta");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        selectorJugadaE2.setBackground(new java.awt.Color(0, 102, 0));
        selectorJugadaE2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Anotacion----------------", "GOAL!!", "Ofensivas----------------", "Despeje", "Saques de banda", "Saques de portería", "Saques de esquina", "Romper el fuera de juego", "Cambiar el juego", "Pase y movimiento", "Agujero defensive", "Triangulacion", "Intercambio de bandas", "Hombre clave", "Defensiva--------------------", "Posiciones adelantadas", "Tiros libres de corto alcance", "Saques de esquina y otros cruces", "Penaltis", "Juego defensivo hombre a hombre", " lanzamiento cruzado", " " }));
        selectorJugadaE2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorJugadaE2ItemStateChanged(evt);
            }
        });
        jPanel2.add(selectorJugadaE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 210, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("jugada");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        selectorFaltaE2.setBackground(new java.awt.Color(0, 102, 51));
        selectorFaltaE2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Patada a adversario", " zancadilla a un adversario", "salto sobre un adversario", "cargar contra un adversario", " golpear a un adversario", "empujar a un adversario", "sujetar a un adversario", "escupir a un adversario", "tocar el balón deliberadamente con las manos" }));
        selectorFaltaE2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorFaltaE2ItemStateChanged(evt);
            }
        });
        selectorFaltaE2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectorFaltaE2MouseClicked(evt);
            }
        });
        jPanel2.add(selectorFaltaE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 210, -1));

        insertar2.setBackground(new java.awt.Color(0, 102, 51));
        insertar2.setForeground(new java.awt.Color(255, 255, 255));
        insertar2.setText("Insertar");
        insertar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertar2ActionPerformed(evt);
            }
        });
        jPanel2.add(insertar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, -1, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("por jugador #");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        NjugadoresE2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(NjugadoresE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 60, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(0, 0, 255));

        tiempo1.setFont(new java.awt.Font("RussellSquare", 0, 24)); // NOI18N
        tiempo1.setForeground(new java.awt.Color(255, 255, 255));
        tiempo1.setText("00:00:00");

        tiempoTexto1.setFont(new java.awt.Font("RussellSquare", 0, 24)); // NOI18N
        tiempoTexto1.setForeground(new java.awt.Color(255, 255, 255));
        tiempoTexto1.setText("Tiempo:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tiempoTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tiempo1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tiempo1)
                    .addComponent(tiempoTexto1))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scoreE3.setEditable(false);
        scoreE3.setBackground(new java.awt.Color(0, 0, 0));
        scoreE3.setFont(new java.awt.Font("RussellSquare", 0, 72)); // NOI18N
        scoreE3.setForeground(new java.awt.Color(255, 51, 0));
        scoreE3.setText("00");
        scoreE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreE3ActionPerformed(evt);
            }
        });
        jPanel9.add(scoreE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 210, 70));

        jPanel10.setBackground(new java.awt.Color(0, 0, 255));

        equipo3.setFont(new java.awt.Font("RussellSquare", 0, 14)); // NOI18N
        equipo3.setForeground(new java.awt.Color(255, 255, 255));
        equipo3.setText("jLabel5");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(equipo3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(equipo3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 280, -1));

        jLabel7.setFont(new java.awt.Font("RussellSquare", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 0, 255));
        jLabel7.setText("Score");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("falta");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("jugada");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        selectorJugadaE3.setBackground(new java.awt.Color(0, 0, 255));
        selectorJugadaE3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Anotacion----------------", "GOAL!!!!!!", "Ofensivas----------------", "Despeje", "Saques de banda", "Saques de portería", "Saques de esquina", "Romper el fuera de juego", "Cambiar el juego", "Pase y movimiento", "Agujero defensive", "Triangulacion", "Intercambio de bandas", "Hombre clave", "Defensiva--------------------", "Posiciones adelantadas", "Tiros libres de corto alcance", "Saques de esquina y otros cruces", "Penaltis", "Juego defensivo hombre a hombre", " lanzamiento cruzado", " " }));
        selectorJugadaE3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorJugadaE3ItemStateChanged(evt);
            }
        });
        selectorJugadaE3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectorJugadaE3MouseClicked(evt);
            }
        });
        selectorJugadaE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorJugadaE3ActionPerformed(evt);
            }
        });
        selectorJugadaE3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                selectorJugadaE3PropertyChange(evt);
            }
        });
        jPanel9.add(selectorJugadaE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 210, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("por jugador #");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        insertar1.setBackground(new java.awt.Color(255, 255, 255));
        insertar1.setForeground(new java.awt.Color(255, 255, 255));
        insertar1.setText("Insertar");
        insertar1.setBorderPainted(false);
        insertar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertar1ActionPerformed(evt);
            }
        });
        jPanel9.add(insertar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, -1, 20));

        selectorFaltaE3.setBackground(new java.awt.Color(0, 0, 255));
        selectorFaltaE3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Patada a adversario", " zancadilla a un adversario", "salto sobre un adversario", "cargar contra un adversario", " golpear a un adversario", "empujar a un adversario", "sujetar a un adversario", "escupir a un adversario", "tocar el balón deliberadamente con las manos" }));
        selectorFaltaE3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorFaltaE3ItemStateChanged(evt);
            }
        });
        selectorFaltaE3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                selectorFaltaE3PopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        selectorFaltaE3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectorFaltaE3MouseClicked(evt);
            }
        });
        selectorFaltaE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorFaltaE3ActionPerformed(evt);
            }
        });
        selectorFaltaE3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                selectorFaltaE3PropertyChange(evt);
            }
        });
        jPanel9.add(selectorFaltaE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 210, -1));

        jPanel9.add(NjugadoresE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 60, -1));

        jPanel12.setBackground(new java.awt.Color(0, 0, 255));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scoreE4.setEditable(false);
        scoreE4.setBackground(new java.awt.Color(0, 0, 0));
        scoreE4.setFont(new java.awt.Font("RussellSquare", 0, 72)); // NOI18N
        scoreE4.setForeground(new java.awt.Color(255, 51, 0));
        scoreE4.setText("00");
        jPanel12.add(scoreE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 210, 70));

        jPanel13.setBackground(new java.awt.Color(102, 102, 255));

        equipo4.setFont(new java.awt.Font("RussellSquare", 0, 14)); // NOI18N
        equipo4.setForeground(new java.awt.Color(255, 255, 255));
        equipo4.setText("jLabel5");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(equipo4)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(equipo4)
                .addContainerGap())
        );

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 280, 30));

        jLabel14.setFont(new java.awt.Font("RussellSquare", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Score");
        jPanel12.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("falta");
        jPanel12.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        selectorJugadaE4.setBackground(new java.awt.Color(0, 102, 0));
        selectorJugadaE4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Anotacion----------------", "GOAL!!", "Ofensivas----------------", "Despeje", "Saques de banda", "Saques de portería", "Saques de esquina", "Romper el fuera de juego", "Cambiar el juego", "Pase y movimiento", "Agujero defensive", "Triangulacion", "Intercambio de bandas", "Hombre clave", "Defensiva--------------------", "Posiciones adelantadas", "Tiros libres de corto alcance", "Saques de esquina y otros cruces", "Penaltis", "Juego defensivo hombre a hombre", " lanzamiento cruzado", " " }));
        selectorJugadaE4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorJugadaE4ItemStateChanged(evt);
            }
        });
        selectorJugadaE4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorJugadaE4ActionPerformed(evt);
            }
        });
        jPanel12.add(selectorJugadaE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 210, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("jugada");
        jPanel12.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        selectorFaltaE4.setBackground(new java.awt.Color(0, 102, 51));
        selectorFaltaE4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Patada a adversario", " zancadilla a un adversario", "salto sobre un adversario", "cargar contra un adversario", " golpear a un adversario", "empujar a un adversario", "sujetar a un adversario", "escupir a un adversario", "tocar el balón deliberadamente con las manos" }));
        selectorFaltaE4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorFaltaE4ItemStateChanged(evt);
            }
        });
        selectorFaltaE4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectorFaltaE4MouseClicked(evt);
            }
        });
        selectorFaltaE4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectorFaltaE4ActionPerformed(evt);
            }
        });
        jPanel12.add(selectorFaltaE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 210, -1));

        insertar3.setBackground(new java.awt.Color(0, 102, 51));
        insertar3.setForeground(new java.awt.Color(255, 255, 255));
        insertar3.setText("Insertar");
        insertar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertar3ActionPerformed(evt);
            }
        });
        jPanel12.add(insertar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, -1, 20));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("por jugador #");
        jPanel12.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        NjugadoresE4.setForeground(new java.awt.Color(255, 255, 255));
        NjugadoresE4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NjugadoresE4ActionPerformed(evt);
            }
        });
        jPanel12.add(NjugadoresE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 60, -1));

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lista.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(lista);

        jPanel16.setBackground(new java.awt.Color(0, 0, 255));

        jButton1.setBackground(new java.awt.Color(0, 0, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Iniciar partido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Pausar partido");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Eliminar evento");

        jButton4.setBackground(new java.awt.Color(0, 0, 255));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Evento por descripcion");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 0, 255));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Exportar");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectorJugadaE1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorJugadaE1ItemStateChanged

    }//GEN-LAST:event_selectorJugadaE1ItemStateChanged

    private void selectorJugadaE1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectorJugadaE1MouseClicked

    }//GEN-LAST:event_selectorJugadaE1MouseClicked

    private void selectorJugadaE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorJugadaE1ActionPerformed

    }//GEN-LAST:event_selectorJugadaE1ActionPerformed

    private void selectorJugadaE1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_selectorJugadaE1PropertyChange

    }//GEN-LAST:event_selectorJugadaE1PropertyChange

    private void insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarActionPerformed
     

    }//GEN-LAST:event_insertarActionPerformed

    private void selectorFaltaE1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorFaltaE1ItemStateChanged


    }//GEN-LAST:event_selectorFaltaE1ItemStateChanged

    private void selectorFaltaE1PopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_selectorFaltaE1PopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_selectorFaltaE1PopupMenuCanceled

    private void selectorFaltaE1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectorFaltaE1MouseClicked

    }//GEN-LAST:event_selectorFaltaE1MouseClicked

    private void selectorFaltaE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorFaltaE1ActionPerformed

    }//GEN-LAST:event_selectorFaltaE1ActionPerformed

    private void selectorFaltaE1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_selectorFaltaE1PropertyChange

    }//GEN-LAST:event_selectorFaltaE1PropertyChange

    private void selectorJugadaE2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorJugadaE2ItemStateChanged
   

    }//GEN-LAST:event_selectorJugadaE2ItemStateChanged

    private void selectorFaltaE2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorFaltaE2ItemStateChanged
     
    }//GEN-LAST:event_selectorFaltaE2ItemStateChanged

    private void selectorFaltaE2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectorFaltaE2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_selectorFaltaE2MouseClicked

    private void insertar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertar2ActionPerformed
      
    }//GEN-LAST:event_insertar2ActionPerformed

    private void selectorJugadaE3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorJugadaE3ItemStateChanged

    
    }//GEN-LAST:event_selectorJugadaE3ItemStateChanged

    private void selectorJugadaE3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectorJugadaE3MouseClicked

    }//GEN-LAST:event_selectorJugadaE3MouseClicked

    private void selectorJugadaE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorJugadaE3ActionPerformed

    }//GEN-LAST:event_selectorJugadaE3ActionPerformed

    private void selectorJugadaE3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_selectorJugadaE3PropertyChange

    }//GEN-LAST:event_selectorJugadaE3PropertyChange

    private void insertar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertar1ActionPerformed
      

    }//GEN-LAST:event_insertar1ActionPerformed

    private void selectorFaltaE3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorFaltaE3ItemStateChanged

    

    }//GEN-LAST:event_selectorFaltaE3ItemStateChanged

    private void selectorFaltaE3PopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_selectorFaltaE3PopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_selectorFaltaE3PopupMenuCanceled

    private void selectorFaltaE3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectorFaltaE3MouseClicked

    }//GEN-LAST:event_selectorFaltaE3MouseClicked

    private void selectorFaltaE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorFaltaE3ActionPerformed

    }//GEN-LAST:event_selectorFaltaE3ActionPerformed

    private void selectorFaltaE3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_selectorFaltaE3PropertyChange

    }//GEN-LAST:event_selectorFaltaE3PropertyChange

    private void selectorJugadaE4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorJugadaE4ItemStateChanged
   
    }//GEN-LAST:event_selectorJugadaE4ItemStateChanged

    private void selectorFaltaE4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorFaltaE4ItemStateChanged
    
    }//GEN-LAST:event_selectorFaltaE4ItemStateChanged

    private void selectorFaltaE4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectorFaltaE4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_selectorFaltaE4MouseClicked

    private void insertar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertar3ActionPerformed
      
    }//GEN-LAST:event_insertar3ActionPerformed

    private void NjugadoresE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NjugadoresE4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NjugadoresE4ActionPerformed

    private void selectorJugadaE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorJugadaE4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectorJugadaE4ActionPerformed

    private void selectorFaltaE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectorFaltaE4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectorFaltaE4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void scoreE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scoreE3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       DescripcionEvento d = new DescripcionEvento();
       d.setVisible(true);
       dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox NjugadoresE1;
    private javax.swing.JComboBox NjugadoresE2;
    private javax.swing.JComboBox NjugadoresE3;
    private javax.swing.JComboBox NjugadoresE4;
    private javax.swing.JLabel equipo1;
    private javax.swing.JLabel equipo2;
    private javax.swing.JLabel equipo3;
    private javax.swing.JLabel equipo4;
    private javax.swing.JButton insertar;
    private javax.swing.JButton insertar1;
    private javax.swing.JButton insertar2;
    private javax.swing.JButton insertar3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lista;
    private javax.swing.JLabel pais1;
    private javax.swing.JLabel pais2;
    private javax.swing.JTextField scoreE1;
    private javax.swing.JTextField scoreE2;
    private javax.swing.JTextField scoreE3;
    private javax.swing.JTextField scoreE4;
    private javax.swing.JComboBox selectorFaltaE1;
    private javax.swing.JComboBox selectorFaltaE2;
    private javax.swing.JComboBox selectorFaltaE3;
    private javax.swing.JComboBox selectorFaltaE4;
    private javax.swing.JComboBox selectorJugadaE1;
    private javax.swing.JComboBox selectorJugadaE2;
    private javax.swing.JComboBox selectorJugadaE3;
    private javax.swing.JComboBox selectorJugadaE4;
    private javax.swing.JLabel tiempo;
    private javax.swing.JLabel tiempo1;
    private javax.swing.JLabel tiempoTexto;
    private javax.swing.JLabel tiempoTexto1;
    // End of variables declaration//GEN-END:variables
}
