package com.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "forum_recommend")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class ForumRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_recommend_code")
    private Long forumRecommendCode;
    @JoinColumn(name = "user_code", nullable = false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "forum_code", nullable = false)
    @ManyToOne
    @JsonIgnore
    private Forum forum;
}
