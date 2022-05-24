package com.example.lenpdok.mapper;

import com.example.lenpdok.model.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface StudyMapper {
    void addPlan(@Param("title") String title);

    List<Plan> getPlanList(int user_id);

    void deletePlan(@Param("id") int id);
}
