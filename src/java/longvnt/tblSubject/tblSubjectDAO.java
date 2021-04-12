/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.tblSubject;

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
public class tblSubjectDAO implements Serializable{
     List<tblSubjectDTO> list;

    public List<tblSubjectDTO> getList() {
        return list;
    }
    public void getSubject() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select SubjectID,SubjectName from tblsubject ";
                stm = con.prepareStatement(sql);
                // stm.setString(1, "%"+name+"%");

                rs = stm.executeQuery();
                while (rs.next()) {
                   String id = rs.getString("SubjectID");
                   String name = rs.getString("SubjectName");
                    tblSubjectDTO dto = new tblSubjectDTO(id, name);
                    if (this.list == null) {
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
    }

}
