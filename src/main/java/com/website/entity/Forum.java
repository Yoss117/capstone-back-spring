package com.website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "forum")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_code")
    private Long forumCode;
    @Column(nullable = false)
    private String title;
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private String content;
    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete;

    @JoinColumn(name = "user_code", nullable = false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "amalysis_code")
    @ManyToOne
    private AnalysisResult analysisResult;

    @OneToMany(mappedBy = "commentCode")
    private List<Comment> comments;
    @OneToMany(mappedBy = "forumRecommendCode")
    private List<ForumRecommend> forumRecommends;
}
