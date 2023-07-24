package com.harshkumar093.erp.service;

import com.harshkumar093.erp.model.AttendanceModel;
import com.harshkumar093.erp.repository.AttendanceRepository;
import com.harshkumar093.erp.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService implements ServiceOps<AttendanceModel> {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Response<Long> create(AttendanceModel attendanceModel) {
        try {
            AttendanceModel attendance = attendanceRepository.save(attendanceModel);
            return new Response<Long>(200, "Saved attendance successfully", attendance.getAttendanceId());
        }catch (Exception exception){
            return new Response<>(400, exception.getMessage(),null);
        }
    }

    @Override
    public Response<List<AttendanceModel>> readAll() {
        return new Response<List<AttendanceModel>>(200, "fetched all attendance successfully",attendanceRepository.findAll());
    }

    @Override
    public Response<AttendanceModel> read(long id) {
        return new Response<AttendanceModel>(200, "fetched attendance successfully",attendanceRepository.findById(id).orElse(null));
    }

    @Override
    public Response<AttendanceModel> update(AttendanceModel attendanceModel) {
        return new Response<AttendanceModel>(200, "attendance updated successfully",attendanceRepository.save(attendanceModel));
    }

    @Override
    public Response<AttendanceModel> delete(long id) {
        Optional<AttendanceModel> attendance = attendanceRepository.findById(id);
        if(attendance.isEmpty()){
            return new Response<>(400, "failed to delete attendance",null);
        }else{
            attendanceRepository.deleteById(id);
            return new Response<AttendanceModel>(200,"attendance deleted successfully",attendance.orElse(null));
        }
    }

    private AttendanceModel checkAttendance(long id){
        Optional<AttendanceModel> attendanceModel = attendanceRepository.findById(id);
        System.out.println("-->"+attendanceModel.orElse(null)+id);
        return attendanceModel.orElse(null);
    }

    public Response<AttendanceModel> updateBreakIn(long id){
        if(this.checkAttendance(id)!=null) {
            Optional<AttendanceModel> attendanceModel = attendanceRepository.findById(id);
            if(attendanceModel.isEmpty()){
                System.out.println(attendanceModel);
                System.out.println(id);
                return new Response<>(200,"failed to update attendance",null);
            }
            List<Date> breaks = attendanceModel.get().getBreakIn();
            if(breaks==null){
                breaks = new ArrayList<Date>();
            }
            breaks.add(new Date());
            attendanceRepository.setBreakInTime(breaks, id);
            return new Response<>(200, "updated break-in successfully",null);
        }

        return new Response<>(400,"Failed to update break-in",null);
    }

    public Response<AttendanceModel> updateBreakOut(long id){
        if(this.checkAttendance(id)!=null) {
            Optional<AttendanceModel> attendanceModel = attendanceRepository.findById(id);
            if(attendanceModel.isEmpty()){
                System.out.println(attendanceModel);
                System.out.println(id);
                return new Response<>(200,"failed to update attendance",null);
            }
            List<Date> breaks = attendanceModel.get().getBreakOut();
            if(breaks==null){
                breaks = new ArrayList<Date>();
            }
            breaks.add(new Date());
            attendanceRepository.setBreakOutTime(breaks, id);
            return new Response<>(200, "updated break-out successfully", null);
        }
        return new Response<>(400,"Failed to update break-out",null);
    }

    public Response<AttendanceModel> updateCheckOut(long id){
        AttendanceModel attendanceModel = this.checkAttendance(id);
        if(attendanceModel!=null){
            attendanceRepository.setCheckOutTime(new Date(), id);
            Response<AttendanceModel> response =  new Response<AttendanceModel>(200, "updated check-out successfully",null);
            attendanceModel.setTotalHours();
            return response;
        }
        return new Response<>(400,"Failed to update check-out",null);
    }

    public Response<List<AttendanceModel>> getAttendanceByUserId(long id){
        return new Response<List<AttendanceModel>>(200, "Successfully fetched attendance for "+id, attendanceRepository.getAttendanceByUserId(id));
    }

}
