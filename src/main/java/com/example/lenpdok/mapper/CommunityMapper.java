package com.example.lenpdok.mapper;

import com.example.lenpdok.model.Community;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface CommunityMapper {
    List<Community> getCommunityList();
    void writeCommunity(Community community);
    void updateCommunity(Community community);
    void deleteCommunity(int id);
}
