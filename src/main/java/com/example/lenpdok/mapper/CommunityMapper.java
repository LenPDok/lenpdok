package com.example.lenpdok.mapper;

import com.example.lenpdok.model.CommunityDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface CommunityMapper {
    List<CommunityDto> getCommunityList();
    void writeCommunity(@Param("community") CommunityDto community);
    void updateCommunity(@Param("community") CommunityDto community);
    void deleteCommunity(@Param("id") int id);
    CommunityDto getCommunity(@Param("id") int id);
}
