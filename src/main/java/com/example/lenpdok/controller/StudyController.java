package com.example.lenpdok.controller;

import com.example.lenpdok.model.Plan;
import com.example.lenpdok.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class StudyController {
    @Autowired
    StudyService studyService;

    @GetMapping("/write_plan")
    public ModelAndView plan() {
        ModelAndView modelAndView = new ModelAndView("/popup/08_2_popup");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/save_plan")
    public String savePlan(HttpServletRequest request) {
        Plan plan = new Plan();
        Enumeration e = request.getParameterNames();
        while ( e.hasMoreElements() ){
            String name = (String) e.nextElement();
            String[] values = request.getParameterValues(name);
            for (String value : values) {
                if(!value.equals(""))
                {
                    plan.setTitle(value);
                    studyService.addPlan(plan);
                }
            }
        }
        String resultmsg="<script>alert('저장되었습니다.');self.close();opener.location.reload();</script>";
        return resultmsg;
    }

    @GetMapping("/community")
    public ModelAndView community() {
        ModelAndView modelAndView = new ModelAndView("/community/list");
        return modelAndView;
    }
}
