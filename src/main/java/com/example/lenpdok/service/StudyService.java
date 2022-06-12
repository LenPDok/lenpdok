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

    public void addPlan(Plan plan, String username) {
        studyMapper.addPlan(plan.getTitle(), username);
    }

    public List<Plan> getPlanList(String username) {
        return studyMapper.getPlanList(username);
    }

    public void delete(int plan_id) {
        studyMapper.deletePlan(plan_id);
    }
}
