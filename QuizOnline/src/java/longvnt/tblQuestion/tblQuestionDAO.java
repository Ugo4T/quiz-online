/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.tblQuestion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import longvnt.Utill.DBHelper;

/**
 *
 * @author PC
 */
public class tblQuestionDAO implements Serializable {

    List<tblQuestionDTO> list;

    public List<tblQuestionDTO> getList() {
        return list;
    }

    public void searchName() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select id,question_content,A,B,C,D,answer_correct,createDate,subjectID,status  from tblquestion ";
                stm = con.prepareStatement(sql);
                // stm.setString(1, "%"+name+"%");

                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String question_content = rs.getString("question_content");
                    String A = rs.getString("A");
                    String B = rs.getString("B");
                    String C = rs.getString("C");
                    String D = rs.getString("D");
                    String answer_correct = rs.getString("answer_correct");
                    Date createDate = rs.getDate("createDate");
                    String subjectID = rs.getString("subjectID");

                    boolean status = rs.getBoolean("status");
                    tblQuestionDTO dto = new tblQuestionDTO(id, question_content, A, B, C, D, answer_correct, createDate, subjectID, status);
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

    public boolean delete(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update tblquestion "
                        + "Set  status=0 "
                        + "where id=?";
                stm = con.prepareStatement(sql);

                stm.setString(1, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean update(String id, String question_content, String a, String b, String c, String d, String answer_correct, Date date, String subjectID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update tblquestion "
                        + "Set  question_content=? , a=? , b=? , c=? , d=? "
                        + " ,answer_correct=? ,createDate=? , subjectID=?,  status=? "
                        + "where id=?";
                stm = con.prepareStatement(sql);

                stm.setString(1, question_content);
                //    System.out.println(question_content);
                stm.setString(2, a);
                //    System.out.println(a+"  "+b+"  "+c+"  "+d+"  ");
                stm.setString(3, b);
                stm.setString(4, c);
                stm.setString(5, d);
                stm.setString(6, answer_correct);
                //    System.out.println(answer_correct);
                stm.setDate(7, new java.sql.Date(date.getTime()));
                //   System.out.println(new java.sql.Date (date.getTime()));
                stm.setString(8, subjectID);
                //    System.out.println(subjectID);
                stm.setBoolean(9, status);
                //   System.out.println(status);
                stm.setString(10, id);
                //  System.out.println(id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean add(String id, String question_content, String a, String b, String c, String d, String answer_correct, Date date, String subjectID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into tblquestion "
                        + " values (?,?,?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);

                stm.setString(1, id);
                stm.setString(2, question_content);
                //    System.out.println(question_content);
                stm.setString(3, a);
                //    System.out.println(a+"  "+b+"  "+c+"  "+d+"  ");
                stm.setString(4, b);
                stm.setString(5, c);
                stm.setString(6, d);
                stm.setString(7, answer_correct);
                //    System.out.println(answer_correct);
                stm.setDate(8, new java.sql.Date(date.getTime()));
                //   System.out.println(new java.sql.Date (date.getTime()));
                stm.setString(9, subjectID);
                //    System.out.println(subjectID);
                stm.setBoolean(10, status);
                //   System.out.println(status);

                //  System.out.println(id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
     public boolean searchID(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select id from tblquestion where id=?";
                stm = con.prepareStatement(sql);
                 stm.setString(1, id);

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
