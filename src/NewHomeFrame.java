
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import javax.swing.Icon;
import java.util.HashSet;
import java.util.Arrays;
import javax.swing.Timer;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class NewHomeFrame extends javax.swing.JFrame {

    private String selectedEmotion = "";
    private String userBadge;
    private Timer countdownTimer;
    private int timeRemaining;
    private final Icon happyIconResource = new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\happy-1.png");
    private final Icon sadIconResource = new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\sad-face-1.png");
    private final Icon angryIconResource = new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\angry-1.png");
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewHomeFrame.class.getName());
    private String currentUserEmail;
    private final List<String> dailyVerses = Arrays.asList(
            "\"Cast all your anxiety on him because he cares for you.\" - 1 Peter 5:7",
            "\"I can do all things through Christ who strengthens me.\" - Philippians 4:13",
            "\"Come to me, all you who are weary and burdened, and I will give you rest.\" - Matthew 11:28",
            "\"The Lord is my shepherd, I lack nothing.\" - Psalm 23:1",
            "\"So do not fear, for I am with you; do not be dismayed, for I am your God.\" - Isaiah 41:10",
            "\"Trust in the Lord with all your heart and lean not on your own understanding.\" - Proverbs 3:5",
            "\"For God has not given us a spirit of fear, but of power and of love and of a sound mind.\" - 2 Timothy 1:7",
            "\"He heals the brokenhearted and binds up their wounds.\" - Psalm 147:3",
            "\"Be strong and courageous. Do not be afraid; do not be discouraged, for the Lord your God will be with you wherever you go.\" - Joshua 1:9",
            "\"And we know that in all things God works for the good of those who love him.\" - Romans 8:28",
            "\"Peace I leave with you; my peace I give you. I do not give to you as the world gives. Do not let your hearts be troubled and do not be afraid.\" - John 14:27",
            "\"The Lord is my strength and my shield; in him my heart trusts, and I am helped.\" - Psalm 28:7",
            "\"He gives strength to the weary and increases the power of the weak.\" - Isaiah 40:29",
            "\"For I know the plans I have for you,” declares the Lord, “plans to prosper you and not to harm you, plans to give you hope and a future.\" - Jeremiah 29:11",
            "\"When I am afraid, I put my trust in you.\" - Psalm 56:3",
            "\"Be still, and know that I am God.\" - Psalm 46:10",
            "\"In this world you will have trouble. But take heart! I have overcome the world.\" - John 16:33",
            "\"The Lord himself goes before you and will be with you; he will never leave you nor forsake you. Do not be afraid; do not be discouraged.\" - Deuteronomy 31:8",
            "\"Therefore do not worry about tomorrow, for tomorrow will worry about itself. Each day has enough trouble of its own.\" - Matthew 6:34",
            "\"God is our refuge and strength, an ever-present help in trouble.\" - Psalm 46:1"
    );

    public NewHomeFrame(String email, String initialEmotion, String badge) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.currentUserEmail = email;
        this.setResizable(false);
        this.userBadge = badge;
        bonusTask.setVisible(false);
        this.selectedEmotion = initialEmotion;
        happy.setVisible(false);
        badgeLabel.setText("Badge: " + this.userBadge);
        sad.setVisible(false);
        angry.setVisible(false);
        if ("happy".equalsIgnoreCase(initialEmotion)) {
            happyIcon.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
        } else if ("sad".equalsIgnoreCase(initialEmotion)) {
            sadIcon.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
        } else if ("angry".equalsIgnoreCase(initialEmotion)) {
            angryIcon.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
        }
        loadRecentEmotions();
        loadTaskStatus();
        displayDailyVerse();
        loadUserData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new GradientPanel();
        jPanel2 = new RoundedPanel(40);
        jLabel1 = new javax.swing.JLabel();
        happy = new javax.swing.JLabel();
        sad = new javax.swing.JLabel();
        angry = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        verseTextArea = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        sadIcon = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new RoundedPanel(40);
        jLabel13 = new javax.swing.JLabel();
        logMood = new javax.swing.JCheckBox();
        Meditate = new javax.swing.JCheckBox();
        readBook = new javax.swing.JCheckBox();
        stretching = new javax.swing.JCheckBox();
        squats = new javax.swing.JCheckBox();
        pushUps = new javax.swing.JCheckBox();
        bonusTask = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        happyIcon = new javax.swing.JLabel();
        angryIcon = new javax.swing.JLabel();
        sendBtn = new RoundedButton();
        jLabel11 = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();
        badgeLabel = new javax.swing.JLabel();
        logoutBtn = new RoundedButton();
        journalBtn = new RoundedButton();
        viewprofileBtn = new RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("Recent Emotions");

        happy.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\happy-1.png")); // NOI18N
        happy.setText("jLabel2");

        sad.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\sad-face-1.png")); // NOI18N

        angry.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\angry-1.png")); // NOI18N

        jLabel2.setText("2025-10-11");

        jLabel3.setText("Feeling peacful after meditation.");

        jLabel4.setText("2025-10-9");

        jLabel5.setText("Frustated with work problem.");

        jLabel6.setText("2025-10-5");

        jLabel7.setText("Angry due to traffic jam.");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        verseTextArea.setEditable(false);
        verseTextArea.setBackground(new java.awt.Color(236, 255, 236));
        verseTextArea.setColumns(20);
        verseTextArea.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        verseTextArea.setLineWrap(true);
        verseTextArea.setRows(5);
        verseTextArea.setWrapStyleWord(true);
        verseTextArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setViewportView(verseTextArea);

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Verse of the Day:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sad)
                            .addComponent(happy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(angry))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(happy)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sad)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(angry)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel8.setText("Log your mood today");

        sadIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\sad-face-1.png")); // NOI18N
        sadIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sadIconMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setText("Notes:");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel13.setText("Daily Tasks:");

        logMood.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        logMood.setText("Log your mood(core tasks): +20 XP");
        logMood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logMoodActionPerformed(evt);
            }
        });

        Meditate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Meditate.setText("Meditate for 5 minutes: +15 XP");
        Meditate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MeditateActionPerformed(evt);
            }
        });

        readBook.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        readBook.setText("Read a book for 15 minutes: +15 XP");
        readBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readBookActionPerformed(evt);
            }
        });

        stretching.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        stretching.setText("Do 10 minutes of stretching: +10 XP");
        stretching.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stretchingActionPerformed(evt);
            }
        });

        squats.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        squats.setText("Do 30 squats: +10 XP");
        squats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                squatsActionPerformed(evt);
            }
        });

        pushUps.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        pushUps.setText("Do 20 push-ups (can be on knees): +10 XP");
        pushUps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pushUpsActionPerformed(evt);
            }
        });

        bonusTask.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        bonusTask.setText("Bonus: +25 XP");
        bonusTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bonusTaskActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(logMood, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(204, 204, 204))
                            .addComponent(readBook, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bonusTask, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Meditate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(squats, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pushUps, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stretching, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logMood)
                .addGap(15, 15, 15)
                .addComponent(Meditate)
                .addGap(15, 15, 15)
                .addComponent(readBook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stretching)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(squats)
                .addGap(15, 15, 15)
                .addComponent(pushUps)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bonusTask)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jTextPane1);

        happyIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\happy-1.png")); // NOI18N
        happyIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                happyIconMouseClicked(evt);
            }
        });

        angryIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\angry-1.png")); // NOI18N
        angryIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                angryIconMouseClicked(evt);
            }
        });

        sendBtn.setBackground(new java.awt.Color(88, 86, 214));
        sendBtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        sendBtn.setForeground(new java.awt.Color(255, 255, 255));
        sendBtn.setText("Send");
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBtnActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Downloads\\profile (3).png")); // NOI18N

        welcomeLabel.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        welcomeLabel.setText("Welcome, User!");

        badgeLabel.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        badgeLabel.setText("Current Badge: Grateful Heart");

        logoutBtn.setBackground(new java.awt.Color(135, 22, 13));
        logoutBtn.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setText("Log Out");
        logoutBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        journalBtn.setBackground(new java.awt.Color(88, 86, 214));
        journalBtn.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        journalBtn.setForeground(new java.awt.Color(255, 255, 255));
        journalBtn.setText("Journal");
        journalBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        journalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                journalBtnActionPerformed(evt);
            }
        });

        viewprofileBtn.setBackground(new java.awt.Color(88, 86, 214));
        viewprofileBtn.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        viewprofileBtn.setForeground(new java.awt.Color(255, 255, 255));
        viewprofileBtn.setText("View Profile");
        viewprofileBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        viewprofileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewprofileBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(welcomeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(viewprofileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(badgeLabel)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addComponent(journalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 59, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(sadIcon)
                        .addGap(18, 18, 18)
                        .addComponent(happyIcon)
                        .addGap(18, 18, 18)
                        .addComponent(angryIcon)
                        .addGap(113, 113, 113))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendBtn)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(welcomeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(badgeLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sadIcon)
                                    .addComponent(happyIcon))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(sendBtn)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(angryIcon))
                        .addGap(40, 40, 40))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(logoutBtn)
                            .addComponent(journalBtn)
                            .addComponent(viewprofileBtn))
                        .addGap(44, 44, 44)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void sadIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sadIconMouseClicked
        sadIcon.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
        angryIcon.setBorder(null);
        happyIcon.setBorder(null);
        selectedEmotion = "sad";
    }//GEN-LAST:event_sadIconMouseClicked

    private void logMoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logMoodActionPerformed

    }//GEN-LAST:event_logMoodActionPerformed

    private void happyIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_happyIconMouseClicked
        happyIcon.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
        angryIcon.setBorder(null);
        sadIcon.setBorder(null);
        selectedEmotion = "happy";
    }//GEN-LAST:event_happyIconMouseClicked

    private void angryIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_angryIconMouseClicked
        angryIcon.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
        happyIcon.setBorder(null);
        sadIcon.setBorder(null);
        selectedEmotion = "angry";
    }//GEN-LAST:event_angryIconMouseClicked

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBtnActionPerformed
        String note = jTextPane1.getText();
        if (selectedEmotion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an emotion before sending.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String query = "INSERT INTO mood_logs (user_email, emotion, note) VALUES (?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, this.currentUserEmail);
            pst.setString(2, this.selectedEmotion);
            pst.setString(3, note);
            pst.executeUpdate();
            HashSet<String> completedTasks = getCompletedTasksForToday();
            boolean taskAlreadyDone = completedTasks.contains("logMood");
            if (taskAlreadyDone) {
                JOptionPane.showMessageDialog(this, "Mood logged successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                boolean xpSuccess = awardXP(20, "logMood");
                if (xpSuccess) {
                    JOptionPane.showMessageDialog(this, "Mood logged successfully! 20 core task XP added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Mood logged, but an error occurred adding XP.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
            loadTaskStatus();
            loadUserData();
            loadRecentEmotions();
            clearInputs();
            pst.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        int response = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void journalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_journalBtnActionPerformed
        JournalFrame journalFrame = new JournalFrame(this, currentUserEmail);
        journalFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_journalBtnActionPerformed

    private void viewprofileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewprofileBtnActionPerformed
        NewProfileFrame profileFrame = new NewProfileFrame(this.currentUserEmail);
        profileFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_viewprofileBtnActionPerformed

    private void MeditateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MeditateActionPerformed
        startTaskTimer(Meditate, 15, "Meditate", "Meditate for 5 minutes", 300);
    }//GEN-LAST:event_MeditateActionPerformed

    private void stretchingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stretchingActionPerformed
        startTaskTimer(stretching, 10, "Stretching", "Do 10 minutes of stretching", 600);
    }//GEN-LAST:event_stretchingActionPerformed

    private void squatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_squatsActionPerformed
        handleTaskCompletion(squats, 10, "Squats", "Do 30 squats");
    }//GEN-LAST:event_squatsActionPerformed

    private void pushUpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pushUpsActionPerformed
        handleTaskCompletion(pushUps, 10, "PushUps", "Do 20 push-ups (can be on knees)");
    }//GEN-LAST:event_pushUpsActionPerformed

    private void bonusTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bonusTaskActionPerformed
        handleTaskCompletion(bonusTask, 25, "BonusGrateful", "Write 3 things you're grateful for");
    }//GEN-LAST:event_bonusTaskActionPerformed

    private void readBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readBookActionPerformed
        String suggestion = getBookSuggestion(this.selectedEmotion);
        String message = suggestion + "\n\nThis task has a 15-minute timer."
                + "\nClick 'Yes' to start the timer and read your book.";
        int response = JOptionPane.showConfirmDialog(this,
                message,
                "Book Suggestion & Task Timer",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (response != JOptionPane.YES_OPTION) {
            readBook.setSelected(false);
            return;
        }
        final int xp = 15;
        final String taskName = "ReadBook";
        final String friendlyTaskName = "Read a book for 15 minutes";
        final int durationInSeconds = 900;
        timeRemaining = durationInSeconds;
        JDialog timerDialog = new JDialog(this, "Task in Progress", true);
        timerDialog.setSize(350, 120);
        timerDialog.setLocationRelativeTo(this);
        timerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        timerDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (countdownTimer != null) {
                    countdownTimer.stop();
                }
                readBook.setSelected(false);
                JOptionPane.showMessageDialog(NewHomeFrame.this, "Task cancelled.", "Timer Stopped", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JLabel timerLabel = new JLabel("Time remaining: 15:00", JLabel.CENTER);
        timerLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18));
        timerDialog.getContentPane().add(timerLabel, BorderLayout.CENTER);
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                int minutes = timeRemaining / 60;
                int seconds = timeRemaining % 60;
                timerLabel.setText(String.format("Time remaining: %02d:%02d", minutes, seconds));
                if (timeRemaining <= 0) {
                    countdownTimer.stop();
                    timerDialog.dispose();
                    JOptionPane.showMessageDialog(NewHomeFrame.this,
                            "Task '" + friendlyTaskName + "' complete!",
                            "Task Finished",
                            JOptionPane.INFORMATION_MESSAGE);
                    boolean success = awardXP(xp, taskName);
                    if (success) {
                        JOptionPane.showMessageDialog(NewHomeFrame.this, "Great job! " + xp + " xp added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadTaskStatus();
                        loadUserData();
                    } else {
                        JOptionPane.showMessageDialog(NewHomeFrame.this, "Could not add xp. You may have already completed this task today.", "Task Not Logged", JOptionPane.WARNING_MESSAGE);
                        loadTaskStatus();
                    }
                }
            }
        });
        countdownTimer.start();
        timerDialog.setVisible(true);
    }//GEN-LAST:event_readBookActionPerformed

    private void handleTaskCompletion(javax.swing.JCheckBox taskCheckbox, int xp, String taskName, String friendlyTaskName) {
        if (taskCheckbox.isSelected()) {
            int response = JOptionPane.showConfirmDialog(this,
                    "Please complete the task: do not cheat\n'" + friendlyTaskName + "'\n\nClick 'Yes' only after you have finished.",
                    "Confirm Task Completion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                boolean success = awardXP(xp, taskName);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Great job! " + xp + " xp added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    taskCheckbox.setSelected(true);
                    taskCheckbox.setEnabled(false);
                    loadTaskStatus();
                    loadUserData();
                } else {
                    JOptionPane.showMessageDialog(this, "Could not add points. You may have already completed this task today.", "Task Not Logged", JOptionPane.WARNING_MESSAGE);
                    taskCheckbox.setSelected(false);
                }
            } else {
                taskCheckbox.setSelected(false);
            }
        } else {
            taskCheckbox.setSelected(false);
        }
    }

    private void loadTaskStatus() {
        HashSet<String> completedTasks = getCompletedTasksForToday();
        if (completedTasks.contains("logMood")) {
            logMood.setSelected(true);
            logMood.setEnabled(false);
        }
        if (completedTasks.contains("Meditate")) {
            Meditate.setSelected(true);
            Meditate.setEnabled(false);
        }
        if (completedTasks.contains("ReadBook")) {
            readBook.setSelected(true);
            readBook.setEnabled(false);
        }
        if (completedTasks.contains("Stretching")) {
            stretching.setSelected(true);
            stretching.setEnabled(false);
        }
        if (completedTasks.contains("Squats")) {
            squats.setSelected(true);
            squats.setEnabled(false);
        }
        if (completedTasks.contains("PushUps")) {
            pushUps.setSelected(true);
            pushUps.setEnabled(false);
        }
        boolean allStandardTasksDone = logMood.isSelected()
                && Meditate.isSelected()
                && readBook.isSelected()
                && stretching.isSelected()
                && squats.isSelected()
                && pushUps.isSelected();
        String bonusTaskName = "BonusGrateful";
        if (completedTasks.contains(bonusTaskName)) {
            bonusTask.setText("Bonus: Write 3 things you're grateful for (+25 XP)");
            bonusTask.setSelected(true);
            bonusTask.setEnabled(false);
            bonusTask.setVisible(true);
        } else if (allStandardTasksDone) {
            bonusTask.setText("Bonus: Write 3 things you're grateful for (+25 XP)");
            bonusTask.setSelected(false);
            bonusTask.setEnabled(true);
            bonusTask.setVisible(true);
        } else {
            bonusTask.setVisible(false);
        }
    }

    private boolean awardXP(int XPToAdd, String taskName) {
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String taskQuery = "INSERT INTO task_completion (user_email, task_name, completion_date) VALUES (?, ?, CURDATE())";
        String xpQuery = "UPDATE users SET xp = xp + ? WHERE email = ?";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, dbUsername, dbPassword);
            con.setAutoCommit(false);
            try (PreparedStatement pstTask = con.prepareStatement(taskQuery)) {
                pstTask.setString(1, this.currentUserEmail);
                pstTask.setString(2, taskName);
                pstTask.executeUpdate();
            }
            try (PreparedStatement pstXp = con.prepareStatement(xpQuery)) {
                pstXp.setInt(1, XPToAdd);
                pstXp.setString(2, this.currentUserEmail);
                pstXp.executeUpdate();
            }
            con.commit();
            return true;
        } catch (Exception ex) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception e) {
                logger.log(java.util.logging.Level.SEVERE, "Rollback failed", e);
            }
            logger.log(java.util.logging.Level.WARNING, "Failed to award xp for task: " + taskName, ex);
            return false;
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (Exception e) {
                logger.log(java.util.logging.Level.SEVERE, "Failed to close connection", e);
            }
        }
    }

    private HashSet<String> getCompletedTasksForToday() {
        HashSet<String> completedTasks = new HashSet<>();
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String query = "SELECT task_name FROM task_completion WHERE user_email = ? AND completion_date = CURDATE()";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, this.currentUserEmail);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    completedTasks.add(rs.getString("task_name"));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Could not load task status: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        return completedTasks;
    }

    private void startTaskTimer(javax.swing.JCheckBox taskCheckbox, int xp, String taskName, String friendlyTaskName, int durationInSeconds) {
        int response = JOptionPane.showConfirmDialog(this,
                "This task has a " + (durationInSeconds / 60) + "-minute timer.\nClick 'Yes' to start the timer.",
                "Start Task?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (response != JOptionPane.YES_OPTION) {
            taskCheckbox.setSelected(false);
            return;
        }
        timeRemaining = durationInSeconds;
        JDialog timerDialog = new JDialog(this, "Task in Progress", true);
        timerDialog.setSize(300, 100);
        timerDialog.setLocationRelativeTo(this);
        timerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        timerDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (countdownTimer != null) {
                    countdownTimer.stop();
                }
                taskCheckbox.setSelected(false);
                JOptionPane.showMessageDialog(NewHomeFrame.this, "Task cancelled.", "Timer Stopped", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JLabel timerLabel = new JLabel("Time remaining: 00:00", JLabel.CENTER);
        timerLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18));
        timerDialog.getContentPane().add(timerLabel, BorderLayout.CENTER);
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                int minutes = timeRemaining / 60;
                int seconds = timeRemaining % 60;
                timerLabel.setText(String.format("Time remaining: %02d:%02d", minutes, seconds));
                if (timeRemaining <= 0) {
                    countdownTimer.stop();
                    timerDialog.dispose();
                    JOptionPane.showMessageDialog(NewHomeFrame.this,
                            "Task '" + friendlyTaskName + "' complete!",
                            "Task Finished",
                            JOptionPane.INFORMATION_MESSAGE);
                    boolean success = awardXP(xp, taskName);
                    if (success) {
                        JOptionPane.showMessageDialog(NewHomeFrame.this, "Great job! " + xp + " xp added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadTaskStatus();
                        loadUserData();
                    } else {
                        JOptionPane.showMessageDialog(NewHomeFrame.this, "Could not add xp. You may have already completed this task today.", "Task Not Logged", JOptionPane.WARNING_MESSAGE);
                        loadTaskStatus();
                    }
                }
            }
        });
        countdownTimer.start();
        timerDialog.setVisible(true);
    }

    private void clearInputs() {
        jTextPane1.setText("");
        selectedEmotion = "";
        happyIcon.setBorder(null);
        sadIcon.setBorder(null);
        angryIcon.setBorder(null);
    }

    private void loadRecentEmotions() {
        happy.setVisible(false);
        sad.setVisible(false);
        angry.setVisible(false);
        jLabel2.setText("");
        jLabel3.setText("");
        jLabel4.setText("");
        jLabel5.setText("");
        jLabel6.setText("");
        jLabel7.setText("");
        List<String> dates = new ArrayList<>();
        List<String> notes = new ArrayList<>();
        List<String> emotions = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String query = "SELECT emotion, note, DATE(log_date) as entry_date FROM mood_logs WHERE user_email = ? ORDER BY log_date DESC LIMIT 3";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, this.currentUserEmail);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    dates.add(rs.getString("entry_date"));
                    notes.add(rs.getString("note"));
                    emotions.add(rs.getString("emotion"));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Could not load recent emotions: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, null, ex);
            return;
        }
        if (emotions.size() >= 1) {
            jLabel2.setText(dates.get(0));
            jLabel3.setText("<html><p style=\"width:200px\">" + notes.get(0) + "</p></html>");
            setEmotionIcon(happy, emotions.get(0));
        }
        if (emotions.size() >= 2) {
            jLabel4.setText(dates.get(1));
            jLabel5.setText("<html><p style=\"width:200px\">" + notes.get(1) + "</p></html>");
            setEmotionIcon(sad, emotions.get(1));
        }
        if (emotions.size() >= 3) {
            jLabel6.setText(dates.get(2));
            jLabel7.setText("<html><p style=\"width:200px\">" + notes.get(2) + "</p></html>");
            setEmotionIcon(angry, emotions.get(2));
        }
    }

    private void setEmotionIcon(JLabel iconLabel, String emotion) {
        if ("happy".equalsIgnoreCase(emotion)) {
            iconLabel.setIcon(happyIconResource);
        } else if ("sad".equalsIgnoreCase(emotion)) {
            iconLabel.setIcon(sadIconResource);
        } else if ("angry".equalsIgnoreCase(emotion)) {
            iconLabel.setIcon(angryIconResource);
        }
        iconLabel.setVisible(true);
    }

    private String getBookSuggestion(String emotion) {
        String suggestion = "";
        String emotionCase = (emotion == null) ? "" : emotion.toLowerCase();
        switch (emotionCase) {
            case "happy":
                suggestion = "To ride that wave of positivity, maybe try an uplifting story!\n"
                        + "Suggestion: 'The Alchemist' by Paulo Coelho.\n"
                        + "(Note: You can read any book you like!)";
                break;
            case "sad":
                suggestion = "Reading can be a great comfort. A story about hope might help.\n"
                        + "Suggestion: 'The Midnight Library' by Matt Haig.\n"
                        + "(Note: You can read any book you like!)";
                break;
            case "angry":
                suggestion = "Sometimes understanding the 'why' behind anger is key. A book on emotional resilience could be good.\n"
                        + "Suggestion: 'Rising Strong' by Brené Brown.\n"
                        + "(Note: You can read any book you like!)";
                break;
        }
        return suggestion;
    }

    public void loadUserData() {
        String url = "jdbc:mysql://localhost:3306/user_database";
        String dbUsername = "root";
        String dbPassword = "";
        String query = "SELECT full_name, badge FROM users WHERE email = ?";
        try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, this.currentUserEmail);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String fullName = rs.getString("full_name");
                    String badge = rs.getString("badge");
                    this.userBadge = badge;
                    welcomeLabel.setText("Welcome, " + fullName + "!");
                    badgeLabel.setText("Current Badge: " + badge);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Could not load user data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void displayDailyVerse() {
        try {
            Calendar cal = Calendar.getInstance();
            int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
            int index = dayOfYear % dailyVerses.size();
            String verse = dailyVerses.get(index);
            verseTextArea.setText(verse);
        } catch (Exception e) {
            verseTextArea.setText("Could not load verse. Have a blessed day!");
            logger.log(java.util.logging.Level.SEVERE, "Failed to load daily verse", e);
        }
    }

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> new NewHomeFrame(this.currentUserEmail, "happy").setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Meditate;
    private javax.swing.JLabel angry;
    private javax.swing.JLabel angryIcon;
    private javax.swing.JLabel badgeLabel;
    private javax.swing.JCheckBox bonusTask;
    private javax.swing.JLabel happy;
    private javax.swing.JLabel happyIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton journalBtn;
    private javax.swing.JCheckBox logMood;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JCheckBox pushUps;
    private javax.swing.JCheckBox readBook;
    private javax.swing.JLabel sad;
    private javax.swing.JLabel sadIcon;
    private javax.swing.JButton sendBtn;
    private javax.swing.JCheckBox squats;
    private javax.swing.JCheckBox stretching;
    private javax.swing.JTextArea verseTextArea;
    private javax.swing.JButton viewprofileBtn;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
