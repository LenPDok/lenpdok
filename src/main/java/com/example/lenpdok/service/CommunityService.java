package com.example.lenpdok.service;

import com.example.lenpdok.mapper.CommunityMapper;
import com.example.lenpdok.model.CommunityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityService {
    private final CommunityMapper communityMapper;

    public List<CommunityDto> getCommunityList() {
        return communityMapper.getCommunityList();
    }

    public void writeCommunity(CommunityDto community) {
        communityMapper.writeCommunity(community);
    }

    public void updateCommunity(CommunityDto community) {
        communityMapper.updateCommunity(community);
    }

    public void deleteCommunity(int id) {
        communityMapper.deleteCommunity(id);
    }

    public CommunityDto getCommunity(int id) {
        return communityMapper.getCommunity(id);
    }
}
