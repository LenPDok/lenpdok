package com.example.lenpdok.mapper;

import com.example.lenpdok.model.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface StudyMapper {
    Plan getPlan(@Param("id") int id);
    List<Plan> getPlanList(@Param("username") String username);

    void addPlan(@Param("plan") Plan plan);
    void checkPlan(@Param("activate") int activate, @Param("id") int id);
    void deletePlan(@Param("id") int id);

}
