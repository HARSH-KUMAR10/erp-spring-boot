package com.harshkumar093.erp.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
import java.util.List;

@Entity
public class AttendanceModel{

    public long getAttendanceId() {
        return attendanceId;
    }

    @Id
    @GeneratedValue
    long attendanceId;

    Date checkIn;

    Date checkOut;

    List<Date> breakIn;

    List<Date> breakOut;

    long totalHours;

    @ManyToOne
    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public void setBreakIn(List<Date> breakIn) {
        this.breakIn = breakIn;
    }

    public void setBreakOut(List<Date> breakOut) {
        this.breakOut = breakOut;
    }

    public void setTotalHours() {
        long time = 0;
        time += this.breakIn.get(0).getTime()-this.checkIn.getTime();
        int breakSize = this.breakOut.size();
        for(int i=0;i<breakSize-1 && breakSize>1;i++){
            time+=this.breakIn.get(i+1).getTime()-this.breakOut.get(i).getTime();
        }
        time+=new Date().getTime()-this.breakOut.get(breakSize-1).getTime();
        System.out.println(time);
        this.totalHours = time;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public List<Date> getBreakIn() {
        return breakIn;
    }

    public List<Date> getBreakOut() {
        return breakOut;
    }

    public long getTotalHours() {
        return totalHours;
    }
}
