package com.example.lenpdok.mapper;

import com.example.lenpdok.model.Plan;
import com.example.lenpdok.model.StudyTimeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface StudyMapper {
    List<StudyTimeDto> getStudyTimeList(@Param("username") String username, @Param("monday") String monday, @Param("sunday") String sunday);
    StudyTimeDto getStudyTime(@Param("username") String username, @Param("date") String date);

    Plan getPlan(@Param("id") int id);
    List<Plan> getPlanList(@Param("username") String username);

    void addPlan(@Param("plan") Plan plan);
    void checkPlan(@Param("activate") int activate, @Param("id") int id);
    void deletePlan(@Param("id") int id);

}
