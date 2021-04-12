/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.tblMarks;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class tblMarksDTO implements Serializable{
    private String seq;
    private String email;
    private String subjectID;
    private float mark;

    public tblMarksDTO() {
    }

    public tblMarksDTO(String seq, String email, String subjectID, float mark) {
        this.seq = seq;
        this.email = email;
        this.subjectID = subjectID;
        this.mark = mark;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
    
    
}
