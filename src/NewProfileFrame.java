
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class NewProfileFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewProfileFrame.class.getName());
    private String currentUserEmail;
    private static final String[] BADGE_LEVELS = {
        "Grateful Heart", // Start
        "Hopeful Spark", // Lvl 2
        "Calm River", // Lvl 3
        "Joyful Sun", // Lvl 4
        "Serene Moon", // Lvl 5
        "Empathetic Soul" // Max Level
    };
    private static final int[] XP_THRESHOLDS = {
        0, // "Grateful Heart"
        100, // "Hopeful Spark"
        250, // "Calm River"
        500, // "Joyful Sun"
        1000, // "Serene Moon"
        2000 // "Empathetic Soul"
    };

    public NewProfileFrame(String email) {
        initComponents();
        this.currentUserEmail = email;
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordButtonActionPerformed(evt);
            }
        });
        acceptChallengeBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptChallengeBTNActionPerformed(evt);
            }
        });
        loadProfileData();
    }

    private void acceptChallengeBTNActionPerformed(java.awt.event.ActionEvent evt) {
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String query = "UPDATE users SET active_challenge = 'Grateful3Day', challenge_progress = 0, challenge_last_completed_date = NULL WHERE email = ?";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, this.currentUserEmail);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this,
                        "Challenge Accepted!\nGo to your Journal to make progress.",
                        "Challenge Started",
                        JOptionPane.INFORMATION_MESSAGE);
                loadProfileData();
            } else {
                JOptionPane.showMessageDialog(this, "Could not start challenge. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Database error starting challenge", ex);
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JPanel panel = new JPanel(new java.awt.GridLayout(3, 2, 5, 5)); // rows, cols, hgap, vgap
        JLabel oldPassLabel = new JLabel("Old Password:");
        JPasswordField oldPassField = new JPasswordField(20);
        JLabel newPassLabel = new JLabel("New Password:");
        JPasswordField newPassField = new JPasswordField(20);
        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPassField = new JPasswordField(20);
        panel.add(oldPassLabel);
        panel.add(oldPassField);
        panel.add(newPassLabel);
        panel.add(newPassField);
        panel.add(confirmPassLabel);
        panel.add(confirmPassField);
        int result = JOptionPane.showConfirmDialog(this, panel, "Change Password",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String oldPassword = new String(oldPassField.getPassword());
            String newPassword = new String(newPassField.getPassword());
            String confirmPassword = new String(confirmPassField.getPassword());
            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "New passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (newPassword.equals(oldPassword)) {
                JOptionPane.showMessageDialog(this, "New password cannot be the same as the old password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            updatePasswordInDatabase(oldPassword, newPassword);
        }
    }

    private void updatePasswordInDatabase(String oldPassword, String newPassword) {
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String selectQuery = "SELECT password FROM users WHERE email = ?";
        String updateQuery = "UPDATE users SET password = ? WHERE email = ? AND password = ?";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            try (PreparedStatement pstSelect = con.prepareStatement(selectQuery)) {
                pstSelect.setString(1, this.currentUserEmail);
                try (ResultSet rs = pstSelect.executeQuery()) {
                    if (rs.next()) {
                        String dbCurrentPassword = rs.getString("password");
                        if (dbCurrentPassword.equals(oldPassword)) {
                            try (PreparedStatement pstUpdate = con.prepareStatement(updateQuery)) {
                                pstUpdate.setString(1, newPassword);
                                pstUpdate.setString(2, this.currentUserEmail);
                                pstUpdate.setString(3, oldPassword);
                                int rowsAffected = pstUpdate.executeUpdate();
                                if (rowsAffected > 0) {
                                    JOptionPane.showMessageDialog(this, "Password updated successfully!");
                                    jLabel8.setText("Password: " + "********");
                                } else {
                                    JOptionPane.showMessageDialog(this, "Failed to update password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Incorrect old password.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "User not found. Could not update password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Database error changing password", ex);
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadProfileData() {
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String query = "SELECT full_name, username, email, password, badge, xp, "
                + "active_challenge, challenge_progress FROM users WHERE email = ?";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, this.currentUserEmail);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String fullName = rs.getString("full_name");
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String currentBadge = rs.getString("badge");
                    int xp = rs.getInt("xp");
                    String activeChallenge = rs.getString("active_challenge");
                    int challengeProgress = rs.getInt("challenge_progress");
                    if ("Grateful3Day".equals(activeChallenge)) {
                        jLabel13.setText("<html>For the next 3 days. Write down three <br>things you are grateful for each day.</html>");
                        jLabel15.setText(challengeProgress + "/3");
                        acceptChallengeBTN.setText("Challenge in Progress");
                        acceptChallengeBTN.setEnabled(false);
                    } else {
                        jLabel13.setText("<html>A new challenge is available: <br>The 3-Day Gratitude Streak!</html>");
                        jLabel15.setText("0/3");
                        acceptChallengeBTN.setText("Accept Challenge");
                        acceptChallengeBTN.setEnabled(true);
                    }
                    jLabel5.setText("Fullname: " + fullName);
                    jLabel6.setText("Username: " + username);
                    jLabel7.setText("Email: " + email);
                    jLabel8.setText("Password: " + "********");
                    int currentBadgeIndex = 0;
                    for (int i = 0; i < BADGE_LEVELS.length; i++) {
                        if (BADGE_LEVELS[i].equals(currentBadge)) {
                            currentBadgeIndex = i;
                            break;
                        }
                    }
                    String newBadge = currentBadge;
                    int newBadgeIndex = currentBadgeIndex;
                    for (int i = BADGE_LEVELS.length - 1; i > currentBadgeIndex; i--) {
                        if (xp >= XP_THRESHOLDS[i]) {
                            newBadge = BADGE_LEVELS[i];
                            newBadgeIndex = i;
                            break;
                        }
                    }
                    if (!newBadge.equals(currentBadge)) {
                        updateBadgeInDatabase(newBadge);
                        JOptionPane.showMessageDialog(this,
                                "Congratulations! You've earned a new badge: " + newBadge,
                                "Badge Promoted!",
                                JOptionPane.INFORMATION_MESSAGE);
                        currentBadge = newBadge;
                        currentBadgeIndex = newBadgeIndex;
                    }
                    int currentLevelXP;
                    int nextLevelXP;
                    String nextBadge;
                    if (currentBadgeIndex == BADGE_LEVELS.length - 1) {
                        currentLevelXP = XP_THRESHOLDS[currentBadgeIndex];
                        nextLevelXP = currentLevelXP;
                        nextBadge = "Max Level Reached!";
                    } else {
                        currentLevelXP = XP_THRESHOLDS[currentBadgeIndex];
                        nextLevelXP = XP_THRESHOLDS[currentBadgeIndex + 1];
                        nextBadge = BADGE_LEVELS[currentBadgeIndex + 1];
                    }
                    int xpInCurrentLevel = xp - currentLevelXP;
                    int xpForNextLevel = nextLevelXP - currentLevelXP;
                    int progress = 0;
                    if (xpForNextLevel > 0) {
                        progress = (int) (((double) xpInCurrentLevel / xpForNextLevel) * 100);
                        if (progress > 100) {
                            progress = 100;
                        }
                    } else {
                        progress = 100;
                        xpInCurrentLevel = xpForNextLevel;
                    }
                    jLabel10.setText("Badge: " + currentBadge);
                    jLabel2.setText("<html>Your Badge: <br>" + currentBadge + "</html>");
                    jLabel3.setText("Next Badge: " + nextBadge);
                    jProgressBar1.setMinimum(0);
                    jProgressBar1.setMaximum(100);
                    jProgressBar1.setValue(progress);
                    if (xpForNextLevel > 0) {
                        jProgressBar1.setString(xpInCurrentLevel + "/" + xpForNextLevel + " XP");
                    } else {
                        jProgressBar1.setString(xp + " XP (Max Level)");
                    }
                    jProgressBar1.setStringPainted(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Could not load user profile.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Database error loading profile", ex);
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBadgeInDatabase(String newBadge) {
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String query = "UPDATE users SET badge = ? WHERE email = ?";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, newBadge);
            pst.setString(2, this.currentUserEmail);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Badge updated successfully to " + newBadge);
            } else {
                logger.warning("Could not update badge for user " + this.currentUserEmail);
            }
        } catch (SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Database error updating badge", ex);
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new GradientPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanel(40);
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new RoundedPanel(40);
        jLabel12 = new javax.swing.JLabel();
        acceptChallengeBTN = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        notNow = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        BackBtn = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jProgressBar1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel1.setText("Your Badge Progress");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("<html>Your Badge: <br>Grateful Heart</html>");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Next Badge: The Spark");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Fullname: Jan Jan Borloloy");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setText("Username: Janjan");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setText("Email: Janjan@Gmail.com");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setText("Password: janjan123");

        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\profile (3).png")); // NOI18N

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setText("Change Password");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setText("Badge: Grateful Heart");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("Profile");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\BookPen2-removebg-preview.png")); // NOI18N

        acceptChallengeBTN.setText("Accept Challenge");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setText("<html>For the next 3 days. Write down the<br>things you are grateful for each day.</html>");

        notNow.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        notNow.setText("Not now");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel15.setText("0/3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(acceptChallengeBTN)
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(notNow)
                        .addGap(30, 30, 30))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addGap(27, 27, 27)))
                .addComponent(acceptChallengeBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(notNow)
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setText("Challenge");

        BackBtn.setBackground(new java.awt.Color(139, 32, 13));
        BackBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BackBtn.setForeground(new java.awt.Color(255, 255, 255));
        BackBtn.setText("Back");
        BackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(BackBtn))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(451, 451, 451)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(BackBtn)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        String lastEmotion = "happy";
        String badge = "Grateful Heart";
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String badgeQuery = "SELECT badge FROM users WHERE email = ?";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(badgeQuery)) {
            pst.setString(1, this.currentUserEmail);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    badge = rs.getString("badge");
                }
            }
        } catch (SQLException ex) {
            logger.log(java.util.logging.Level.WARNING, "Could not fetch badge for back button", ex);
        }
        String emotionQuery = "SELECT emotion FROM mood_logs WHERE user_email = ? ORDER BY log_date DESC LIMIT 1";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(emotionQuery)) {
            pst.setString(1, this.currentUserEmail);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    lastEmotion = rs.getString("emotion");
                }
            }
        } catch (SQLException ex) {
            logger.log(java.util.logging.Level.WARNING, "Could not fetch last emotion for back button", ex);
        }
        NewHomeFrame homeFrame = new NewHomeFrame(this.currentUserEmail, lastEmotion, badge);
        homeFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackBtnActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //java.awt.EventQueue.invokeLater(() -> new NewProfileFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBtn;
    private javax.swing.JButton acceptChallengeBTN;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel notNow;
    // End of variables declaration//GEN-END:variables
}
