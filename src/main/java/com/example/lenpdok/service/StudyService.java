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
        studyMapper.addPlan(plan.getTitle());
    }

    public List<Plan> getPlanList(int user_id) {
        return studyMapper.getPlanList(user_id);
    }

    public void delete(int plan_id) {
        studyMapper.deletePlan(plan_id);
    }
}
