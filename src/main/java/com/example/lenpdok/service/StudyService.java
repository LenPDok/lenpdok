package com.example.lenpdok.service;

import com.example.lenpdok.mapper.StudyMapper;
import com.example.lenpdok.model.Plan;
import com.example.lenpdok.model.StudyTime;
import com.example.lenpdok.model.StudyTimeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudyService {
    private final StudyMapper studyMapper;

    public void addPlan(Plan plan) {
        studyMapper.addPlan(plan);
    }

    public Plan getPlan(Integer id) {
        return studyMapper.getPlan(id);
    }

    public List<Plan> getPlanList(String username) {
        return studyMapper.getPlanList(username);
    }

    public void checkPlan(Integer id) {
        if(getPlan(id).isActivate()) {
            studyMapper.checkPlan(0, id);
        } else {
            studyMapper.checkPlan(1, id);
        }

    }


    public void deletePlan(Integer id) {
        studyMapper.deletePlan(id);
    }

    public List<StudyTimeDto> getStudyTimeList(String username) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = formatter.format(c.getTime());
        c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        c.add(c.DATE,7);
        String sunday =  formatter.format(c.getTime());
        return studyMapper.getStudyTimeList(username, monday, sunday);
    }

    public StudyTimeDto getStudyTime(String username, String date) {
        return studyMapper.getStudyTime(username, date);
    }
}
