/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.tblAccount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longvnt.Utill.DBHelper;

/**
 *
 * @author PC
 */
public class tblAccountDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select email "
                        + "From tblaccounts "
                        + "Where email =? and password =? "; 
                //tạo statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //execute
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
     public String getFullname (String username,String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select name "
                        + "From tblaccounts "
                        + "Where email =? and password =? ";
                //tạo statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //execute
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return "";
    }
    public boolean getRole (String username,String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select role "
                        + "From tblaccounts "
                        + "Where email =? and password =? ";
                //tạo statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //execute
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getBoolean("role");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    public boolean createAccount (tblAccountDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into tblaccounts values(?,?,?,?,?)";
                //tạo statement
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                stm.setString(3, dto.getPassword());
                stm.setString(2, dto.getName());
                stm.setBoolean(4, dto.isIsadmin());
                stm.setBoolean(5, dto.isStatus());
                //execute
                int result = stm.executeUpdate();
                if (result>0){
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
     public boolean searchID(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select email from tblaccounts where email=?";
                stm = con.prepareStatement(sql);
                 stm.setString(1, email);

                rs = stm.executeQuery();
                if (rs.next()) {
                   return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
