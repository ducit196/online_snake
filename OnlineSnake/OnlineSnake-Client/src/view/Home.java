/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.User;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ducba
 */
public class Home extends javax.swing.JFrame {

    private Vector<User> listUserOnline;
    private User currentUser;

    /**
     * Creates new form OnlineClientFrm
     */
    public Home() {
        initComponents();
        if (listUserOnline == null) {
            listUserOnline = new Vector<>();
        }
        if (currentUser == null) {
            currentUser = new User();
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public int showConfirmDialog(User user) {
        int response = JOptionPane.showConfirmDialog(this, user.getUsername() + " muốn đấu với bạn.", "Bạn có muốn đấu không?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return response;
        /*
        Yes is 0
        No is 1
         */
    }

    public JTable getJtbOnlineClient() {
        return jtbOnlineClient;
    }

    public Vector<User> getListUserOnline() {
        return listUserOnline;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setListUserOnline(Vector<User> listUserOnline) {
        this.listUserOnline = listUserOnline;
    }

    public void showOnlineUser(Vector<User> listUserOnline) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers("Username,".split(","));
        for (User user : listUserOnline) {
                dtm.addRow(user.getObject());
        }
        jtbOnlineClient.setModel(dtm);
    }

    public void logoutListener(ActionListener log) {
        jbtLogout.addActionListener(log);
    }

    public void challengeListener(ActionListener log) {
        jbtChallenge.addActionListener(log);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jbtChallenge = new javax.swing.JButton();
        jbtLogout = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbOnlineClient = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Danh sách người chơi online");

        jbtChallenge.setText("Thách đấu");

        jbtLogout.setText("Đăng xuất");

        jtbOnlineClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtbOnlineClient);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtChallenge)
                        .addGap(75, 75, 75)
                        .addComponent(jbtLogout)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtLogout)
                    .addComponent(jbtChallenge))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtChallenge;
    private javax.swing.JButton jbtLogout;
    private javax.swing.JTable jtbOnlineClient;
    // End of variables declaration//GEN-END:variables
}
