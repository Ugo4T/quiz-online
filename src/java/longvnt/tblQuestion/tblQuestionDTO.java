/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.tblQuestion;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author PC
 */
public class tblQuestionDTO implements Serializable{
    private String id;
    private String questioncontent;
    private String A;
    private String B;
    private String C;
    private String D;
    private String answer_correct;
    private Date date;
    private String subjectID;
    private boolean status;

    public tblQuestionDTO() {
    }

    public tblQuestionDTO(String id, String questioncontent, String A, String B, String C, String D, String answer_correct, Date date, String subjectID, boolean status) {
        this.id = id;
        this.questioncontent = questioncontent;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.answer_correct = answer_correct;
        this.date = date;
        this.subjectID = subjectID;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestioncontent() {
        return questioncontent;
    }

    public void setQuestioncontent(String questioncontent) {
        this.questioncontent = questioncontent;
    }

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getB() {
        return B;
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getC() {
        return C;
    }

    public void setC(String C) {
        this.C = C;
    }

    public String getD() {
        return D;
    }

    public void setD(String D) {
        this.D = D;
    }

    public String getAnswer_correct() {
        return answer_correct;
    }

    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
