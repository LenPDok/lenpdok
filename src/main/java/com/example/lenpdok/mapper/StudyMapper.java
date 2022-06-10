package com.example.lenpdok.mapper;

import com.example.lenpdok.model.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface StudyMapper {
    void addPlan(@Param("title") String title);

    List<Plan> getPlanList(@Param("username") String username);

    void deletePlan(@Param("id") int id);
}
