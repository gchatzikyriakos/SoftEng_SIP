package net.java.sip.communicator.extensions;

import net.java.sip.communicator.common.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yandall on 22/3/2016.
 */
public class BillingUI extends JFrame {

    private ConnectToDB database;

    public static void main(String[] args) {
        new BillingUI();
    }

    public BillingUI(){
        database = new ConnectToDB();
        float cost = 0;
        JFrame frame = new JFrame("Billing Information");
        frame.setVisible(true);
        frame.setSize(400, 100);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        JPanel panel = new JPanel();
        frame.add(panel);

        String user = Utils.getProperty("net.java.sip.communicator.sip.USER_NAME");
        Connection conn = database.start();
        PreparedStatement stmt = null;
        String sql = "SELECT total_cost FROM billing WHERE user=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()){
                cost = rs.getFloat("total_cost");
            }
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        database.close(conn);

        JLabel label = new JLabel("Your total billing amount is " + cost + " $.");
        panel.add(label);

        JButton okbtn = new JButton("OK");
        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(okbtn);

        pack();

    }
}
