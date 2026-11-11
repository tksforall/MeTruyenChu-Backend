package com.example.mtc.dto;

public class ReviewLikeStatusResponse {

    private boolean liked;

    public ReviewLikeStatusResponse(boolean liked) {
        this.liked = liked;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}