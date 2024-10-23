package com.example.corzello.Service;

import com.example.corzello.Entity.Vote;

public interface VoteService {
    boolean upvotePublication(Long publicationId);
    boolean downvotePublication(Long publicationId);
}
