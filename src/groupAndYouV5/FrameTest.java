package groupAndYouV5;


import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Pedro Cardoso Rdz
 */
public class FrameTest extends javax.swing.JFrame {
    
    /**
     * Creates new form FrameTest
     */
    public FrameTest() {
        initComponents();
       
        showJOvalButton();
        setSize(new Dimension(500,380));
        setLocationRelativeTo(null);
        setVisible(true);
        validate();
    }


    private void showJOvalButton(){
        JOvalBtn jbe1=new JOvalBtn();
        jbe1.setToolTipText("JOvalButton in BorderLayout.Center without Preferrered Diameter");
        jPanel1.add(jbe1,java.awt.BorderLayout.CENTER);
        JOvalBtn jbe2=new JOvalBtn(43,43);
        jbe2.setToolTipText("JOvalButton in BorderLayout.Center with Preferrered Diameter = 43,43");
        jPanel2.add(jbe2,java.awt.BorderLayout.CENTER);
        JOvalBtn jbe11=new JOvalBtn();
        jbe11.setToolTipText("JOvalButton with Preferrered Diameter = 21,21");
        jbe11.setPreferredDiameter(21,21);
        jPanel11.add(jbe11);
        JOvalBtn jbe12=new JOvalBtn(43,43);
        jbe12.setToolTipText("JOvalButton with Preferrered Diameter = 43,43");
        jPanel12.add(jbe12);
        JPanel jPanelGD=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                Color color1=new Color(74,103,109); Color color2=new Color(181,217,237); Color color3=new Color(108,119,135);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                Paint oldPaint = g2.getPaint();
                g2.clip(new java.awt.Rectangle(0,0,getWidth(),getHeight()));
                g2.setPaint((Paint) new LinearGradientPaint(0.0f,0.0f,getWidth(),getHeight()*1.73f,
                        new float[]{0.0f,0.20f,0.49f,0.62f,1.0f},
                        new Color[]{color3,color2,color3,color1,color2} ));
                g2.fillRect(0,0,getWidth(),getHeight());
                g2.setStroke(new BasicStroke(4f));
                g2.setPaint(new GradientPaint(0.0f, 0.0f,color1,0.0f, getHeight(), color3));
                g2.drawRect(0, 0, getWidth()-2 , getHeight() -2);
                g2.setPaint(oldPaint);
                super.paintComponent(g);
            }};
        jPanelGD.setOpaque(false);
        jPanelGD.setLayout(new java.awt.GridBagLayout());
        jTabbedPane3.addTab("GradientPanel", jPanelGD);
        JOvalBtn jbe15=new JOvalBtn(43,43);
        jbe15.setToolTipText("JOvalButton on GradientPanel with Preferrered Diameter = 43,43");
        jPanelGD.add(jbe15);
        JPanel jPanelIMG=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                java.awt.Dimension dim = getSize();
                int x, y;
                javax.swing.ImageIcon imagen=new javax.swing.ImageIcon(getClass().getResource("iconov2.png"));
                x=(dim.width>600?(int)((dim.width-600)/2):0);
                y=(dim.height>450?(int)((dim.height-450)/2):0);
                g.drawImage(imagen.getImage(),x,y,600,450,null);
                this.setOpaque(false);
                super.paintComponent(g);
            }};
        jPanelIMG.setLayout(new java.awt.BorderLayout());
        jTabbedPane3.addTab("TransparentPanel", jPanelIMG);
        JPanel jPanelTC=new JPanel();
        jPanelTC.setLayout(new java.awt.GridBagLayout());
        jPanelTC.setOpaque(true);
        jPanelTC.setBackground(new Color(243,121,124,129));
        jPanelTC.setPreferredSize(new java.awt.Dimension(230, 240));
        JPanel jPanelTC2=new JPanel();
        jPanelTC2.setLayout(new java.awt.GridBagLayout());
        jPanelTC2.setOpaque(true);
        jPanelTC2.setBackground(new Color(124,121,234,129));
        jPanelTC2.setPreferredSize(new java.awt.Dimension(230, 240));
        jPanelIMG.add(jPanelTC,java.awt.BorderLayout.WEST);
        jPanelIMG.add(jPanelTC2,java.awt.BorderLayout.EAST);
        JOvalBtn jbe16=new JOvalBtn(43,43);
        jbe16.setToolTipText("JOvalButton on TransparentPanel with Preferrered Diameter = 43,43");
        jPanelTC.add(jbe16);
        JOvalBtn jbe17=new JOvalBtn(43,43);
        jbe17.setToolTipText("JOvalButton on TransparentPanel with Preferrered Diameter = 43,43");
        jPanelTC2.add(jbe17);
        JOvalBtn jbe20=new JOvalBtn(53,53); jbe20.setText("20");
        jbe20.setToolTipText("JOvalButton with text and Preferrered Diameter = 53,53");
        JOvalBtn jbe21=new JOvalBtn(53,53); jbe21.setText("21");
        jbe21.setToolTipText("JOvalButton with text and Preferrered Diameter = 53,53");
        JOvalBtn jbe22=new JOvalBtn(53,53); jbe22.setText("22");
        jbe22.setToolTipText("JOvalButton with text and Preferrered Diameter = 53,53");
        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jbe21))
                            .addComponent(jbe20)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jbe22)))
                .addContainerGap(422, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbe20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbe21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbe22)
                .addContainerGap(244, Short.MAX_VALUE))
        );
        JOvalBtn jbe25=new JOvalBtn(57,57);
        jbe25.setToolTipText("JOvalButton with icon and Preferrered Diameter = 57,57");
        jbe25.setIcon(new javax.swing.ImageIcon(getClass().getResource("iconov2.png")));
        jPanel25.add(jbe25);
        JOvalBtn jbe26=new JOvalBtn(57,57);
        jbe26.setToolTipText("JOvalButton with icon and Preferrered Diameter = 57,57");
        jbe26.setIcon(new javax.swing.ImageIcon(getClass().getResource("iconov2.png")));
        jPanel25.add(jbe26);
        JOvalBtn jbe27=new JOvalBtn();
        jbe27.setToolTipText("JOvalButton with text and without Preferrered Diameter");
        jbe27.setText("Text");
        jPanel25.add(jbe27);
        JOvalBtn jbe28=new JOvalBtn(67,67);
        jbe28.setToolTipText("JOvalButton with text and Preferrered Diameter = 67,67");
        jbe28.setText("Text");
        jPanel25.add(jbe28);
        JOvalBtn jbe29=new JOvalBtn(109,89);
        jbe29.setToolTipText("JOvalButton with idon and text and Preferrered Diameter = 109,89");
        jbe29.setIcon(new javax.swing.ImageIcon(getClass().getResource("iconov2.png")));
        jbe29.setText("Email");
        jPanel25.add(jbe29);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBtnns = new javax.swing.JPanel();
        btnCal = new javax.swing.JButton();
        btnJob = new javax.swing.JButton();
        btnLicense = new javax.swing.JButton();
        pnlShow = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlCalendar = new javax.swing.JPanel();
        pnlCalDay = new javax.swing.JPanel();
        pnlCalWeek = new javax.swing.JPanel();
        pnlCalFortnight = new javax.swing.JPanel();
        pnlCalNoChange = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("View com.coatldev.beans @version 0.23 @licence LGPL");

        pnlBtnns.setLayout(new javax.swing.BoxLayout(pnlBtnns, javax.swing.BoxLayout.Y_AXIS));

        btnCal.setText("CalendarSel");
        btnCal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalActionPerformed(evt);
            }
        });
        pnlBtnns.add(btnCal);

        btnJob.setText("JOvalButton");
        btnJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJobActionPerformed(evt);
            }
        });
        pnlBtnns.add(btnJob);

        btnLicense.setText("License");
        btnLicense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLicenseActionPerformed(evt);
            }
        });
        pnlBtnns.add(btnLicense);

        getContentPane().add(pnlBtnns, java.awt.BorderLayout.EAST);

        pnlShow.setLayout(new java.awt.CardLayout());

        pnlCalendar.setLayout(new java.awt.GridLayout(4, 0));

        pnlCalDay.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlCalendar.add(pnlCalDay);

        pnlCalWeek.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlCalendar.add(pnlCalWeek);

        pnlCalFortnight.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlCalendar.add(pnlCalFortnight);
        pnlCalendar.add(pnlCalNoChange);

        jTabbedPane1.addTab("CalendarSel", pnlCalendar);

        pnlShow.add(jTabbedPane1, "c-CalendarSel");

        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(39, 255, 46));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel16.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(39, 255, 46));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel16.add(jPanel2);

        jTabbedPane3.addTab("BorderLayout", jPanel16);

        jPanel21.setLayout(new java.awt.GridLayout(1, 0));

        jPanel11.setBackground(new java.awt.Color(90, 100, 240));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.add(jPanel11);

        jPanel12.setBackground(new java.awt.Color(90, 100, 240));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.add(jPanel12);

        jTabbedPane3.addTab("FlowLayout", jPanel21);

        jPanel23.setBackground(new java.awt.Color(128, 149, 113));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("GroupLayout", jPanel23);
        jTabbedPane3.addTab("Image & Text", jPanel25);

        pnlShow.add(jTabbedPane3, "c-JOvalButton");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("                   GNU LESSER GENERAL PUBLIC LICENSE\n                       Version 3, 29 June 2007\n\n Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>\n Everyone is permitted to copy and distribute verbatim copies\n of this license document, but changing it is not allowed.\n\n\n  This version of the GNU Lesser General Public License incorporates\nthe terms and conditions of version 3 of the GNU General Public\nLicense, supplemented by the additional permissions listed below.\n\n  0. Additional Definitions.\n\n  As used herein, \"this License\" refers to version 3 of the GNU Lesser\nGeneral Public License, and the \"GNU GPL\" refers to version 3 of the GNU\nGeneral Public License.\n\n  \"The Library\" refers to a covered work governed by this License,\nother than an Application or a Combined Work as defined below.\n\n  An \"Application\" is any work that makes use of an interface provided\nby the Library, but which is not otherwise based on the Library.\nDefining a subclass of a class defined by the Library is deemed a mode\nof using an interface provided by the Library.\n\n  A \"Combined Work\" is a work produced by combining or linking an\nApplication with the Library.  The particular version of the Library\nwith which the Combined Work was made is also called the \"Linked\nVersion\".\n\n  The \"Minimal Corresponding Source\" for a Combined Work means the\nCorresponding Source for the Combined Work, excluding any source code\nfor portions of the Combined Work that, considered in isolation, are\nbased on the Application, and not on the Linked Version.\n\n  The \"Corresponding Application Code\" for a Combined Work means the\nobject code and/or source code for the Application, including any data\nand utility programs needed for reproducing the Combined Work from the\nApplication, but excluding the System Libraries of the Combined Work.\n\n  1. Exception to Section 3 of the GNU GPL.\n\n  You may convey a covered work under sections 3 and 4 of this License\nwithout being bound by section 3 of the GNU GPL.\n\n  2. Conveying Modified Versions.\n\n  If you modify a copy of the Library, and, in your modifications, a\nfacility refers to a function or data to be supplied by an Application\nthat uses the facility (other than as an argument passed when the\nfacility is invoked), then you may convey a copy of the modified\nversion:\n\n   a) under this License, provided that you make a good faith effort to\n   ensure that, in the event an Application does not supply the\n   function or data, the facility still operates, and performs\n   whatever part of its purpose remains meaningful, or\n\n   b) under the GNU GPL, with none of the additional permissions of\n   this License applicable to that copy.\n\n  3. Object Code Incorporating Material from Library Header Files.\n\n  The object code form of an Application may incorporate material from\na header file that is part of the Library.  You may convey such object\ncode under terms of your choice, provided that, if the incorporated\nmaterial is not limited to numerical parameters, data structure\nlayouts and accessors, or small macros, inline functions and templates\n(ten or fewer lines in length), you do both of the following:\n\n   a) Give prominent notice with each copy of the object code that the\n   Library is used in it and that the Library and its use are\n   covered by this License.\n\n   b) Accompany the object code with a copy of the GNU GPL and this license\n   document.\n\n  4. Combined Works.\n\n  You may convey a Combined Work under terms of your choice that,\ntaken together, effectively do not restrict modification of the\nportions of the Library contained in the Combined Work and reverse\nengineering for debugging such modifications, if you also do each of\nthe following:\n\n   a) Give prominent notice with each copy of the Combined Work that\n   the Library is used in it and that the Library and its use are\n   covered by this License.\n\n   b) Accompany the Combined Work with a copy of the GNU GPL and this license\n   document.\n\n   c) For a Combined Work that displays copyright notices during\n   execution, include the copyright notice for the Library among\n   these notices, as well as a reference directing the user to the\n   copies of the GNU GPL and this license document.\n\n   d) Do one of the following:\n\n       0) Convey the Minimal Corresponding Source under the terms of this\n       License, and the Corresponding Application Code in a form\n       suitable for, and under terms that permit, the user to\n       recombine or relink the Application with a modified version of\n       the Linked Version to produce a modified Combined Work, in the\n       manner specified by section 6 of the GNU GPL for conveying\n       Corresponding Source.\n\n       1) Use a suitable shared library mechanism for linking with the\n       Library.  A suitable mechanism is one that (a) uses at run time\n       a copy of the Library already present on the user's computer\n       system, and (b) will operate properly with a modified version\n       of the Library that is interface-compatible with the Linked\n       Version.\n\n   e) Provide Installation Information, but only if you would otherwise\n   be required to provide such information under section 6 of the\n   GNU GPL, and only to the extent that such information is\n   necessary to install and execute a modified version of the\n   Combined Work produced by recombining or relinking the\n   Application with a modified version of the Linked Version. (If\n   you use option 4d0, the Installation Information must accompany\n   the Minimal Corresponding Source and Corresponding Application\n   Code. If you use option 4d1, you must provide the Installation\n   Information in the manner specified by section 6 of the GNU GPL\n   for conveying Corresponding Source.)\n\n  5. Combined Libraries.\n\n  You may place library facilities that are a work based on the\nLibrary side by side in a single library together with other library\nfacilities that are not Applications and are not covered by this\nLicense, and convey such a combined library under terms of your\nchoice, if you do both of the following:\n\n   a) Accompany the combined library with a copy of the same work based\n   on the Library, uncombined with any other library facilities,\n   conveyed under the terms of this License.\n\n   b) Give prominent notice with the combined library that part of it\n   is a work based on the Library, and explaining where to find the\n   accompanying uncombined form of the same work.\n\n  6. Revised Versions of the GNU Lesser General Public License.\n\n  The Free Software Foundation may publish revised and/or new versions\nof the GNU Lesser General Public License from time to time. Such new\nversions will be similar in spirit to the present version, but may\ndiffer in detail to address new problems or concerns.\n\n  Each version is given a distinguishing version number. If the\nLibrary as you received it specifies that a certain numbered version\nof the GNU Lesser General Public License \"or any later version\"\napplies to it, you have the option of following the terms and\nconditions either of that published version or of any later version\npublished by the Free Software Foundation. If the Library as you\nreceived it does not specify a version number of the GNU Lesser\nGeneral Public License, you may choose any version of the GNU Lesser\nGeneral Public License ever published by the Free Software Foundation.\n\n  If the Library as you received it specifies that a proxy can decide\nwhether future versions of the GNU Lesser General Public License shall\napply, that proxy's public statement of acceptance of any version is\npermanent authorization for you to choose that version for the\nLibrary.");
        jScrollPane1.setViewportView(jTextArea1);

        pnlShow.add(jScrollPane1, "c-License");

        getContentPane().add(pnlShow, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalActionPerformed
        CardLayout crdl=(CardLayout)pnlShow.getLayout();
        crdl.show(pnlShow, "c-CalendarSel");
    }//GEN-LAST:event_btnCalActionPerformed

    private void btnJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJobActionPerformed
        CardLayout crdl=(CardLayout)pnlShow.getLayout();
        crdl.show(pnlShow, "c-JOvalButton");
    }//GEN-LAST:event_btnJobActionPerformed

    private void btnLicenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLicenseActionPerformed
        CardLayout crdl=(CardLayout)pnlShow.getLayout();
        crdl.show(pnlShow, "c-License");
    }//GEN-LAST:event_btnLicenseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrameTest().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCal;
    private javax.swing.JButton btnJob;
    private javax.swing.JButton btnLicense;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel pnlBtnns;
    private javax.swing.JPanel pnlCalDay;
    private javax.swing.JPanel pnlCalFortnight;
    private javax.swing.JPanel pnlCalNoChange;
    private javax.swing.JPanel pnlCalWeek;
    private javax.swing.JPanel pnlCalendar;
    private javax.swing.JPanel pnlShow;
    // End of variables declaration//GEN-END:variables
}