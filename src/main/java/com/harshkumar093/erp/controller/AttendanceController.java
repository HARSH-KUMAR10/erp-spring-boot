package com.harshkumar093.erp.controller;

import com.harshkumar093.erp.model.AttendanceModel;
import com.harshkumar093.erp.service.AttendanceService;
import com.harshkumar093.erp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @PostMapping()
    public Response<Long> createAttendance(@RequestBody AttendanceModel attendanceModel){
        attendanceModel.setCheckIn(new Date());
        return attendanceService.create(attendanceModel);
    }

    @GetMapping
    public Response<List<AttendanceModel>> getAllAttendance(){
        return attendanceService.readAll();
    }

    @GetMapping("{id}")
    public Response<AttendanceModel> getAttendanceById(@PathVariable long id){
        return attendanceService.read(id);
    }

    @GetMapping("/user/{id}")
    public Response<List<AttendanceModel>> getAttendanceByUserId(@PathVariable long id){
        return attendanceService.getAttendanceByUserId(id);
    }

    @PatchMapping()
    public Response<AttendanceModel> updateAttendanceDetails(@RequestBody AttendanceModel attendanceModel){
        return attendanceService.update(attendanceModel);
    }

    @PatchMapping("/break-in/{id}")
    public Response<AttendanceModel> updateAttendanceBreakIn(@PathVariable long id){
        return attendanceService.updateBreakIn(id);
    }

    @PatchMapping("/break-out/{id}")
    public Response<AttendanceModel> updateAttendanceBreakOut(@PathVariable long id){
        return attendanceService.updateBreakOut(id);
    }

    @PatchMapping("/check-out/{id}")
    public Response<AttendanceModel> updateAttendanceCheckOut(@PathVariable long id){
        return attendanceService.updateCheckOut(id);
    }

    @DeleteMapping("{id}")
    public Response<AttendanceModel> deleteAttendance(@PathVariable long id){
        return  attendanceService.delete(id);
    }
}
