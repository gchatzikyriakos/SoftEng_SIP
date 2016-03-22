package net.java.sip.communicator.extensions;

import java.sql.*;

import net.java.sip.communicator.sip.security.SecurityAuthority;
import net.java.sip.communicator.sip.security.UserCredentials;

public class RegistrationToDB {

    ConnectToDB database = new ConnectToDB();


    public UserCredentials obtainCreds (SecurityAuthority securityAuthority,String realm, UserCredentials defaultCredentials)
    {
        UserCredentials initialCredentials = new UserCredentials();
        boolean reg_flag = false;
        while(!reg_flag){
            initialCredentials = securityAuthority.obtainCredentials(realm,defaultCredentials);
            //System.out.println(String.valueOf(initialCredentials.getPassword()));
            reg_flag = this.checkCreds(initialCredentials.getUserName(),String.valueOf(initialCredentials.getPassword()));
            if (!reg_flag){
                InvalidCredUI invui = new InvalidCredUI();
                invui.waitForOK();
            }

        }
        return initialCredentials;
    }

    private boolean checkCreds(String name, String password){
        Connection conn =  database.start();
        if (name.equals("") || (password.equals(""))){
            return false;
        }

        String sql = "SELECT * FROM user_info WHERE username=? ";
        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                System.out.println("einai diko mas");
                sql = "SELECT * FROM user_info WHERE username=? AND password=SHA1(?) ";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, password);
                rs = stmt.executeQuery();
                if (rs.first()) {
                    System.out.println("einai diko mas2");
                    return true;
                }
                else {
                    System.out.println("einai diko mas");
                    return false;
                }
            }
            else {
                sql = "INSERT INTO user_info VALUES (?,SHA1(?))";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, password);
                stmt.executeUpdate();
            }
            stmt.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        return true;
    }

}

