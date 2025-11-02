
public class SelectFrame extends javax.swing.JFrame {

    private String currentUserEmail;
    private String userBadge;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SelectFrame.class.getName());

    public SelectFrame(String email) {
        initComponents();
        this.setResizable(false);
        this.currentUserEmail = email;
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new GradientPanel();
        happyIcon = new javax.swing.JLabel();
        sadIcon = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        angryIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        happyIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\happy-1 (1).png")); // NOI18N
        happyIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                happyIconMouseClicked(evt);
            }
        });

        sadIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\sad-face (1).png")); // NOI18N
        sadIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sadIconMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel4.setText("How's your feeling today?");

        angryIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\angry (1).png")); // NOI18N
        angryIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                angryIconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(happyIcon)
                        .addGap(68, 68, 68)
                        .addComponent(sadIcon)
                        .addGap(69, 69, 69)
                        .addComponent(angryIcon))
                    .addComponent(jLabel4))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel4)
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sadIcon)
                    .addComponent(angryIcon)
                    .addComponent(happyIcon))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void happyIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_happyIconMouseClicked
        NewHomeFrame nextFrame = new NewHomeFrame(this.currentUserEmail, "happy", this.userBadge);
        nextFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_happyIconMouseClicked

    private void sadIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sadIconMouseClicked
        NewHomeFrame nextFrame = new NewHomeFrame(this.currentUserEmail, "sad", this.userBadge);
        nextFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_sadIconMouseClicked

    private void angryIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_angryIconMouseClicked
        NewHomeFrame nextFrame = new NewHomeFrame(this.currentUserEmail, "angry", this.userBadge);
        nextFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_angryIconMouseClicked

    public static void main(String args[]) {
        //  java.awt.EventQueue.invokeLater(() -> new SelectFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel angryIcon;
    private javax.swing.JLabel happyIcon;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel sadIcon;
    // End of variables declaration//GEN-END:variables
}
