package com.example.lenpdok.service;

import com.example.lenpdok.mapper.CommunityMapper;
import com.example.lenpdok.model.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityService {
    private final CommunityMapper communityMapper;

    public List<Community> getCommunityList() {
        return communityMapper.getCommunityList();
    }

    public void writeCommunity(Community community) {
        communityMapper.writeCommunity(community);
    }

    public void updateCommunity(Community community) {
        communityMapper.updateCommunity(community);
    }

    public void deleteCommunity(int id) {
        communityMapper.deleteCommunity(id);
    }
}
