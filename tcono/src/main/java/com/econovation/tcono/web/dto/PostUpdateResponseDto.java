package com.econovation.tcono.web.dto;

import com.econovation.tcono.domain.post.Category;
import com.econovation.tcono.domain.post.Post;
import com.econovation.tcono.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class PostUpdateResponseDto {
    private User user;
    private String content;
    private String title;
    private String category; //해시태그
    private int views;
    private int hearts;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public PostUpdateResponseDto(Post post, List<Category> categoryListByPost) {
        this.user=post.getUser();
        this.content = post.getContent();
        this.title = post.getTitle();
        this.category = categoryListByPost.toString();
        this.views=post.getViews();
        this.hearts = post.getHearts();
        this.createdDate= post.getCreatedDate();
        this.modifiedDate=post.getModifiedDate();
    }
}