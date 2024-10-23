package com.example.corzello.Service;

// CommunityServiceImpl.java

import com.example.corzello.Entity.Community;
import com.example.corzello.Entity.UserEntity;
import com.example.corzello.Repository.CommunityRepository;
import com.example.corzello.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    public CommunityServiceImpl(CommunityRepository communityRepository, UserRepo userRepository) {
        this.communityRepository = communityRepository;
        this.userRepository = userRepository;
    }
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private UserRepo userRepository;


    @Override
    public List<Community> getRecommendedCommunities() {
        // Implement logic to retrieve recommended communities
        return communityRepository.findAll(); // For simplicity, returning all communities
    }

    @Override
    public void joinCommunity(Long communityId, Long userId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("Community not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Associate the user with the community
        community.setUser(user);

        // Save the community with the updated user association
        communityRepository.save(community);
    }
    @Override
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

}