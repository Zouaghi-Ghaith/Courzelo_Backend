package com.example.corzello.Controller;



// CommunityController.java

import com.example.corzello.Entity.Community;
import com.example.corzello.Entity.UserEntity;
import com.example.corzello.Service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/communities")

public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/recommended")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Community> getRecommendedCommunities() {
        return communityService.getRecommendedCommunities();
    }

    @PostMapping("/{communityId}/join/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void joinCommunity(@PathVariable Long communityId, @PathVariable Long userId) {
        communityService.joinCommunity(communityId, userId);
    }
}