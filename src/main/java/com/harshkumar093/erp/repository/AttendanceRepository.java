package com.harshkumar093.erp.repository;

import com.harshkumar093.erp.model.AttendanceModel;
import com.harshkumar093.erp.util.Response;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceModel,Long> {

    @Query("SELECT a from AttendanceModel a where a.userModel.userId=:userId")
    List<AttendanceModel> getAttendanceByUserId(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query("Update AttendanceModel Set breakIn=:breakInTime where attendanceId=:attendanceIdInput")
    void setBreakInTime(@Param("breakInTime") List<Date> date, @Param("attendanceIdInput") Long attendanceIdInput);

    @Modifying
    @Transactional
    @Query("Update AttendanceModel Set breakOut=:breakOutTime where attendanceId=:attendanceIdInput")
    void setBreakOutTime(@Param("breakOutTime") List<Date> date, @Param("attendanceIdInput") Long attendanceIdInput);

    @Modifying
    @Transactional
    @Query("Update AttendanceModel Set checkOut=:checkOutTime where attendanceId=:attendanceIdInput")
    void setCheckOutTime(@Param("checkOutTime") Date date, @Param("attendanceIdInput") Long attendanceIdInput);
}
