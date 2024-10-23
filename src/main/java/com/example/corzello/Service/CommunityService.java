package com.example.corzello.Service;


// CommunityService.java

import com.example.corzello.Entity.Community;
import com.example.corzello.Entity.Publication;

import java.util.List;

public interface CommunityService {
    List<Community> getRecommendedCommunities();
    void joinCommunity(Long communityId, Long user);
    public List<Community> getAllCommunities();

}