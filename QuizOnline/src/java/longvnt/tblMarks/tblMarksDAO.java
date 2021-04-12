/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.tblMarks;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import longvnt.Utill.DBHelper;

/**
 *
 * @author PC
 */
public class tblMarksDAO implements Serializable{
    public boolean createHis (String email, String subjectID,float mark) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into tblmarks (email,subjectID,mark) values(?,?,?)";
                //tạo statement
                stm = con.prepareStatement(sql);
                stm.setString(1,email );
                stm.setString(2, subjectID);
                stm.setFloat(3,mark);
                
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
    
    
    
    public ArrayList<tblMarksDTO> searchMark(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<tblMarksDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select seq,email,subjectID,mark  from tblmarks where email=? ";
                stm = con.prepareStatement(sql);
                 stm.setString(1, email);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int  seq = rs.getInt("seq");
                    
                    String subjectID = rs.getString("subjectID");
                    float mark = rs.getFloat("mark");
                    tblMarksDTO dto = new tblMarksDTO(sql, email, subjectID, mark);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(dto);
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
        return list;
    }

}
