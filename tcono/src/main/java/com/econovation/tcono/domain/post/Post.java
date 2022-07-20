package com.econovation.tcono.domain.post;

import com.econovation.tcono.domain.BaseTimeEntity;
import com.econovation.tcono.domain.heart.Heart;
import com.econovation.tcono.domain.user.User;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "POST_ID")
    private Long id;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID")
//    private User user;
    @Column(updatable = false)
    private Long userId;
    private String content;
    private String title;
    @Column(insertable = false, updatable = false,nullable = true)
    @ColumnDefault("false")
    private Boolean official; //인기글

    @Column(insertable = false, updatable = false)
    @ColumnDefault("0")
    private int views; //조회수
    @Column(insertable = false, updatable = false)
    @ColumnDefault("0")
    private int hearts; //좋아요

//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//    private List<Heart> Heart = new ArrayList<>();

//    @OneToMany(mappedBy = "post", orphanRemoval = true)
//    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MainCategory mainCategory; //대분류

    @Builder
    public Post(Long userId, String content, String title, MainCategory mainCategory) {
        this.userId = userId;
        this.content = content;
        this.title = title;
        this.mainCategory = mainCategory;
    }


    public int decreaseHearts() {
        this.hearts -= 1;
        return hearts;
    }

    public int increaseHearts() {
        this.hearts += 1;
        return hearts;
    }

    public int increaseViews() {
        this.views += 1;
        return views;
    }

    public void updatePost(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public void updateOfficial() {
        this.official = true;
    }
}