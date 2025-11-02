
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProfileFrame extends javax.swing.JFrame {

    private HomeFrame homeFrameInstance;
    private String currentUserEmail;
    private int currentUserPoints;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProfileFrame.class.getName());
   
    public ProfileFrame(HomeFrame home, String email, int points) {
        initComponents();
        this.setResizable(false);
        this.homeFrameInstance = home;
        this.currentUserEmail = email;
        this.currentUserPoints = points;
        setPasswordViewVisible(false);
        loadProfileData();
    }

    private void loadProfileData() {
    Email.setText("Email: " + this.currentUserEmail);
    Points.setText("Points: " + this.currentUserPoints);
    String url = "jdbc:mysql://localhost:3306/user_database";
    String dbUsername = "root";
    String dbPassword = "";
    String query = "SELECT full_name, username, password FROM users WHERE email = ?";
    try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
         PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, this.currentUserEmail);
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                String fullName = rs.getString("full_name");
                String username = rs.getString("username");
                Fullname.setText("Full Name: " + fullName);
                Username.setText("Username: " + username);
                Password.setText("Password: ********");
            }
        }
    } catch (Exception ex) {
        logger.log(java.util.logging.Level.SEVERE, "Failed to load profile data", ex);
        JOptionPane.showMessageDialog(this, "Could not load profile data.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new GradientPanel();
        Fullname = new javax.swing.JLabel();
        Username = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        Password = new javax.swing.JLabel();
        changepassBtn = new RoundedButton();
        Points = new javax.swing.JLabel();
        backBtn = new RoundedButton();
        jLabel7 = new javax.swing.JLabel();
        submitPassBtn = new RoundedButton();
        cancelPassBtn = new RoundedButton();
        currentPassLabel = new javax.swing.JLabel();
        currentPassField = new javax.swing.JPasswordField();
        newPassLabel = new javax.swing.JLabel();
        newPassField = new javax.swing.JPasswordField();
        confirmPassLabel = new javax.swing.JLabel();
        confirmPassField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Fullname.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        Fullname.setText("Fullname: JanJan Borloloy");

        Username.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        Username.setText("Username: OnetYagal");

        Email.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        Email.setText("Email: JanJan@gmail.com");

        Password.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        Password.setText("Password: hoehoe");

        changepassBtn.setBackground(new java.awt.Color(88, 86, 214));
        changepassBtn.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        changepassBtn.setForeground(new java.awt.Color(255, 255, 255));
        changepassBtn.setText("Change Password");
        changepassBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        changepassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changepassBtnActionPerformed(evt);
            }
        });

        Points.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        Points.setText("Points: 0");

        backBtn.setBackground(new java.awt.Color(139, 32, 13));
        backBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\profile (3).png")); // NOI18N

        submitPassBtn.setBackground(new java.awt.Color(88, 86, 214));
        submitPassBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        submitPassBtn.setForeground(new java.awt.Color(255, 255, 255));
        submitPassBtn.setText("Submit");
        submitPassBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        submitPassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitPassBtnActionPerformed(evt);
            }
        });

        cancelPassBtn.setBackground(new java.awt.Color(139, 32, 13));
        cancelPassBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cancelPassBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelPassBtn.setText("Cancel");
        cancelPassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelPassBtnActionPerformed(evt);
            }
        });

        currentPassLabel.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        currentPassLabel.setText("Current Password:");

        newPassLabel.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        newPassLabel.setText("New Password:");

        confirmPassLabel.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        confirmPassLabel.setText("Confirm Password:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel7)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Fullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Points, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(changepassBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currentPassLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newPassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currentPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(confirmPassLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelPassBtn)
                                .addGap(22, 22, 22)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(submitPassBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Fullname)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Points)
                                        .addGap(10, 10, 10)
                                        .addComponent(Username)
                                        .addGap(21, 21, 21)
                                        .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7))
                                .addGap(13, 13, 13)
                                .addComponent(Password)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(changepassBtn)
                                .addGap(27, 27, 27)
                                .addComponent(backBtn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(currentPassLabel)
                                    .addComponent(currentPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(newPassLabel)
                                    .addComponent(newPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(confirmPassLabel)
                                    .addComponent(confirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(submitPassBtn)
                                    .addComponent(cancelPassBtn))))
                        .addContainerGap(99, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelPassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelPassBtnActionPerformed
        setPasswordViewVisible(false);
    }//GEN-LAST:event_cancelPassBtnActionPerformed

    private void submitPassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitPassBtnActionPerformed
        String currentPass = new String(currentPassField.getPassword());
        String newPass = new String(newPassField.getPassword());
        String confirmPass = new String(confirmPassField.getPassword());
        if (currentPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "New password and confirm password do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String checkQuery = "SELECT password FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement checkPst = con.prepareStatement(checkQuery)) {
                checkPst.setString(1, this.currentUserEmail);
                checkPst.setString(2, currentPass);
                try (ResultSet rs = checkPst.executeQuery()) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(this, "Incorrect current password.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            String updateQuery = "UPDATE users SET password = ? WHERE email = ?";
            try (PreparedStatement updatePst = con.prepareStatement(updateQuery)) {
                updatePst.setString(1, newPass);
                updatePst.setString(2, this.currentUserEmail);
                int rowsAffected = updatePst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Password updated successfully!");
                    setPasswordViewVisible(false); // Go back to profile view
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, "Failed to update password", ex);
            JOptionPane.showMessageDialog(this, "Could not connect to database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_submitPassBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        if (this.homeFrameInstance != null) {
            this.homeFrameInstance.setVisible(true); // Show the HomeFrame again
        }
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void changepassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changepassBtnActionPerformed
        setPasswordViewVisible(true);
    }//GEN-LAST:event_changepassBtnActionPerformed

   private void setPasswordViewVisible(boolean showPasswordView) {
        Fullname.setVisible(!showPasswordView);
        Username.setVisible(!showPasswordView);
        Email.setVisible(!showPasswordView);
        Password.setVisible(!showPasswordView);
        Points.setVisible(!showPasswordView);
        jLabel7.setVisible(!showPasswordView);
        changepassBtn.setVisible(!showPasswordView);
        backBtn.setVisible(!showPasswordView);
        currentPassLabel.setVisible(showPasswordView);
        currentPassField.setVisible(showPasswordView);
        newPassLabel.setVisible(showPasswordView);
        newPassField.setVisible(showPasswordView);
        confirmPassLabel.setVisible(showPasswordView);
        confirmPassField.setVisible(showPasswordView);
        submitPassBtn.setVisible(showPasswordView);
        cancelPassBtn.setVisible(showPasswordView);
        if (!showPasswordView) {
            currentPassField.setText("");
            newPassField.setText("");
            confirmPassField.setText("");
        }
    }
    
    public static void main(String args[]) {
        //java.awt.EventQueue.invokeLater(() -> new ProfileFrame().setVisible(true));
    }   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Fullname;
    private javax.swing.JLabel Password;
    private javax.swing.JLabel Points;
    private javax.swing.JLabel Username;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cancelPassBtn;
    private javax.swing.JButton changepassBtn;
    private javax.swing.JPasswordField confirmPassField;
    private javax.swing.JLabel confirmPassLabel;
    private javax.swing.JPasswordField currentPassField;
    private javax.swing.JLabel currentPassLabel;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField newPassField;
    private javax.swing.JLabel newPassLabel;
    private javax.swing.JButton submitPassBtn;
    // End of variables declaration//GEN-END:variables
}
