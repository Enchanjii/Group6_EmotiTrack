
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.sql.SQLException;
import java.time.LocalDate;

public class JournalFrame extends javax.swing.JFrame {

    private NewHomeFrame homeFrame;
    private String currentUserEmail;
    private javax.swing.ButtonGroup promptGroup;
    private javax.swing.JTextArea recentJournalsArea;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JournalFrame.class.getName());

    public JournalFrame(NewHomeFrame home, String email) {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.homeFrame = home;
        this.currentUserEmail = email;
        this.setLocationRelativeTo(home);
        ButtonGroup moodGroup = new ButtonGroup();
        happyRadio.setBorderPainted(true);
        sadRadio.setBorderPainted(true);
        angryRadio.setBorderPainted(true);
        moodGroup.add(happyRadio);
        moodGroup.add(sadRadio);
        moodGroup.add(angryRadio);
        java.awt.event.ActionListener moodListener = evt -> updateMoodBorders();
        happyRadio.addActionListener(moodListener);
        sadRadio.addActionListener(moodListener);
        angryRadio.addActionListener(moodListener);
        updateMoodBorders();
        moodCalendarTable.setDefaultRenderer(Object.class, new MoodCalendarRenderer());
        moodCalendarTable.setIntercellSpacing(new java.awt.Dimension(2, 2));
        setupRecentJournalsArea();
        loadRecentJournals();
        loadMoodCalendar();
    }

    public JournalFrame() {
        throw new UnsupportedOperationException("Not supported yet. Use constructor with HomeFrame and email.");
    }

    private void setupRecentJournalsArea() {
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 270));
        recentJournalsArea = new javax.swing.JTextArea();
        recentJournalsArea.setEditable(false);
        recentJournalsArea.setLineWrap(true);
        recentJournalsArea.setWrapStyleWord(true);
        recentJournalsArea.setFont(new java.awt.Font("SansSerif", 0, 12));
        recentJournalsArea.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(recentJournalsArea);
    }

    private java.util.Map<Integer, String> fetchMoodsForCurrentMonth() {
        java.util.Map<Integer, String> moodMap = new java.util.HashMap<>();
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int month = cal.get(java.util.Calendar.MONTH) + 1;
        int year = cal.get(java.util.Calendar.YEAR);
        String query = "SELECT DAY(entry_date) as day, mood FROM journal_entries "
                + "WHERE user_email = ? AND MONTH(entry_date) = ? AND YEAR(entry_date) = ? AND mood IS NOT NULL";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, this.currentUserEmail);
            pst.setInt(2, month);
            pst.setInt(3, year);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    moodMap.put(rs.getInt("day"), rs.getString("mood"));
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, "Error fetching moods", ex);
        }
        return moodMap;
    }

    private String getSelectedMood() {
        if (happyRadio.isSelected()) {
            return "Happy";
        }
        if (sadRadio.isSelected()) {
            return "Sad"; 
        }
        if (angryRadio.isSelected()) {
            return "Angry"; 
        }
        return null;
    }

    private void loadMoodCalendar() {
        java.util.Map<Integer, String> moodMap = fetchMoodsForCurrentMonth();
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) moodCalendarTable.getModel();
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                model.setValueAt(new CalendarCell(0, null), r, c);
            }
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
        monthYearLabel.setText(sdf.format(java.util.Calendar.getInstance().getTime()));
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
        int firstDayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int maxDaysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        int day = 1;
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                if (r == 0 && c < firstDayOfWeek - 1) {
                    model.setValueAt(new CalendarCell(0, null), r, c);
                } else if (day <= maxDaysInMonth) {
                    String mood = moodMap.get(day);
                    model.setValueAt(new CalendarCell(day, mood), r, c);
                    day++;
                } else {
                    model.setValueAt(new CalendarCell(0, null), r, c);
                }
            }
        }
    }

    private void updateMoodBorders() {
        if (happyRadio.isSelected()) {
            happyRadio.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
        } else {
            happyRadio.setBorder(null); 
        }
        if (sadRadio.isSelected()) {
            sadRadio.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
        } else {
            sadRadio.setBorder(null);
        }
        if (angryRadio.isSelected()) {
            angryRadio.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
        } else {
            angryRadio.setBorder(null);
        }
    }

    private void loadRecentJournals() {
        StringBuilder journalText = new StringBuilder();
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String query = "SELECT prompt, entry_text, DATE_FORMAT(entry_date, '%Y-%m-%d %h:%i %p') as formatted_date "
                + "FROM journal_entries WHERE user_email = ? ORDER BY entry_date DESC LIMIT 3";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, this.currentUserEmail);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        journalText.append("--- " + rs.getString("formatted_date") + " ---\n");
                        journalText.append("Prompt: " + rs.getString("prompt") + "\n\n");
                        journalText.append(rs.getString("entry_text") + "\n\n");
                    }
                }
            }
        } catch (Exception ex) {
            recentJournalsArea.setText("Error loading journal entries:\n" + ex.getMessage());
            logger.log(java.util.logging.Level.SEVERE, null, ex);
            return;
        }
        if (journalText.length() == 0) {
            recentJournalsArea.setText("No journal entries found. Write your first one!");
        } else {
            recentJournalsArea.setText(journalText.toString());
            recentJournalsArea.setCaretPosition(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new GradientPanel();
        submitBtn = new RoundedButton();
        backBtn = new RoundedButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanel(40);
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        happyRadio = new javax.swing.JRadioButton();
        sadRadio = new javax.swing.JRadioButton();
        angryRadio = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        moodCalendarTable = new javax.swing.JTable();
        monthYearLabel = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        submitBtn.setBackground(new java.awt.Color(88, 86, 214));
        submitBtn.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        submitBtn.setForeground(new java.awt.Color(255, 255, 255));
        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        backBtn.setBackground(new java.awt.Color(139, 32, 13));
        backBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("Recent Journal:");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "How are your feeling today?", "Who made your day better?", "What challenged you today?", "What's a goal for tomorrow?", "What did you do today that you're proud of?", "What's one thing you learned today?", "Describe a small victory.", "What's on your mind right now?", "What was the best part of your day?" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Your Mood: ");

        happyRadio.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\EmotiTrack\\src\\happyIcon (1).png")); // NOI18N
        happyRadio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                happyRadioMouseClicked(evt);
            }
        });

        sadRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sadIcon (1).png"))); // NOI18N
        sadRadio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sadRadioMouseClicked(evt);
            }
        });

        angryRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/angryIcon (1).png"))); // NOI18N
        angryRadio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                angryRadioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(happyRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sadRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(angryRadio))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sadRadio)
                    .addComponent(happyRadio)
                    .addComponent(angryRadio))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel1.setText("Journal");

        moodCalendarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"
            }
        ));
        moodCalendarTable.setRowHeight(40);
        jScrollPane3.setViewportView(moodCalendarTable);

        monthYearLabel.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        monthYearLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monthYearLabel.setText("October 2025");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(submitBtn)))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(monthYearLabel)
                        .addGap(359, 359, 359))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(backBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(monthYearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
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

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        String prompt = (String) jComboBox1.getSelectedItem();
        String entryText = jTextArea1.getText();
        String mood = getSelectedMood();
        if (entryText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please write something in your journal.", "Empty Entry", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (mood == null) {
            JOptionPane.showMessageDialog(this, "Please select your mood.", "No Mood Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String query = "INSERT INTO journal_entries (user_email, prompt, entry_text, mood, entry_date) VALUES (?, ?, ?, ?, NOW())";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, this.currentUserEmail);
                pst.setString(2, prompt);
                pst.setString(3, entryText);
                pst.setString(4, mood);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Journal entry saved!");
                    jTextArea1.setText("");
                    checkChallengeProgress();
                    loadRecentJournals();
                    loadMoodCalendar();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to save entry.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving entry:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    private void checkChallengeProgress() {
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String selectQuery = "SELECT active_challenge, challenge_progress, challenge_last_completed_date "
                + "FROM users WHERE email = ?";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pstSelect = con.prepareStatement(selectQuery)) {
            pstSelect.setString(1, this.currentUserEmail);
            try (ResultSet rs = pstSelect.executeQuery()) {
                if (rs.next()) {
                    String activeChallenge = rs.getString("active_challenge");
                    int progress = rs.getInt("challenge_progress");
                    java.sql.Date lastDate = rs.getDate("challenge_last_completed_date");
                    if ("Grateful3Day".equals(activeChallenge)) {
                        LocalDate today = LocalDate.now();
                        boolean alreadyDoneToday = (lastDate != null && lastDate.toLocalDate().isEqual(today));
                        if (!alreadyDoneToday) {
                            progress++;
                            if (progress >= 3) {
                                String updateQuery = "UPDATE users SET active_challenge = NULL, challenge_progress = 0, "
                                        + "xp = xp + 50, challenge_last_completed_date = CURDATE() WHERE email = ?";
                                try (PreparedStatement pstUpdate = con.prepareStatement(updateQuery)) {
                                    pstUpdate.setString(1, this.currentUserEmail);
                                    pstUpdate.executeUpdate();
                                }
                                JOptionPane.showMessageDialog(this,
                                        "Challenge Complete! You earned 50 XP!",
                                        "Challenge Complete!",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                String updateQuery = "UPDATE users SET challenge_progress = ?, "
                                        + "challenge_last_completed_date = CURDATE() WHERE email = ?";
                                try (PreparedStatement pstUpdate = con.prepareStatement(updateQuery)) {
                                    pstUpdate.setInt(1, progress);
                                    pstUpdate.setString(2, this.currentUserEmail);
                                    pstUpdate.executeUpdate();
                                }
                                JOptionPane.showMessageDialog(this,
                                        "Great job! Challenge progress: " + progress + "/3",
                                        "Challenge Progress",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Error checking challenge progress", ex);
        }
    }

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        this.homeFrame.loadUserData();
        this.homeFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void happyRadioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_happyRadioMouseClicked

    }//GEN-LAST:event_happyRadioMouseClicked

    private void sadRadioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sadRadioMouseClicked

    }//GEN-LAST:event_sadRadioMouseClicked

    private void angryRadioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_angryRadioMouseClicked

    }//GEN-LAST:event_angryRadioMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JournalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JournalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JournalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JournalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            System.out.println("JournalFrame cannot be run directly. It must be opened from HomeFrame.");
        });
        //java.awt.EventQueue.invokeLater(() -> new JournalFrame().setVisible(true));
    }
    private class CalendarCell {

        int day;
        String mood;
        public CalendarCell(int day, String mood) {
            this.day = day;
            this.mood = mood;
        }

        @Override
        public String toString() {
            return (day > 0) ? String.valueOf(day) : "";
        }
    }

    private class MoodCalendarRenderer extends javax.swing.table.DefaultTableCellRenderer {

        private final ImageIcon happyIcon;
        private final ImageIcon angryIcon;
        private final ImageIcon sadIcon;
        public MoodCalendarRenderer() {
            try {
                happyIcon = new ImageIcon(getClass().getResource("/happyIcon (1).png"));
                sadIcon = new ImageIcon(getClass().getResource("/sadIcon (1).png"));
                angryIcon = new ImageIcon(getClass().getResource("/angryIcon (1).png"));
                if (happyIcon.getIconWidth() == -1
                        || sadIcon.getIconWidth() == -1
                        || angryIcon.getIconWidth() == -1) {
                    throw new RuntimeException("Could not load icons! Make sure they are in the 'src' folder.");
                }
                happyIcon.setImage(happyIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
                angryIcon.setImage(angryIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
                sadIcon.setImage(sadIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
            } catch (Exception e) {
                String errorMsg = "Could not load icons! Make sure they are in your 'src' folder.\nError: " + e.getMessage();
                logger.log(java.util.logging.Level.SEVERE, errorMsg, e);
                throw new RuntimeException(errorMsg, e);
            }
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof CalendarCell) {
                CalendarCell cellData = (CalendarCell) value;
                setText(cellData.toString());
                if ("Happy".equals(cellData.mood)) {
                    setIcon(happyIcon);
                } else if ("Angry".equals(cellData.mood)) {
                    setIcon(angryIcon);
                } else if ("Sad".equals(cellData.mood)) {
                    setIcon(sadIcon);
                } else {
                    setIcon(null);
                }
                if (cellData.day > 0) {
                    c.setBackground(java.awt.Color.WHITE);
                } else {
                    c.setBackground(new java.awt.Color(240, 240, 240));
                }

            } else {
                setText("");
                setIcon(null);
                c.setBackground(new java.awt.Color(240, 240, 240));
            }
            return c;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton angryRadio;
    private javax.swing.JButton backBtn;
    private javax.swing.JRadioButton happyRadio;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel monthYearLabel;
    private javax.swing.JTable moodCalendarTable;
    private javax.swing.JRadioButton sadRadio;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
