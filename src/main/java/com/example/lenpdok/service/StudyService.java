package com.example.lenpdok.service;

import com.example.lenpdok.mapper.StudyMapper;
import com.example.lenpdok.model.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
