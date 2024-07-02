package com.example.waplan.goal.domain;

import com.example.waplan.user.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "제1목표")
@Getter
@Setter
public class FirstGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "목표", nullable = false, length = 50)
    private String goal;

    @Column(name = "내용", nullable = true, length = 100)
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "goal_date_id", referencedColumnName = "id")
    private GoalDate goalDate;

    @Column(name = "색깔", nullable = false, length = 20)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "달성여부", nullable = false)
    private AchievementStatus achievementStatus;

    @Column(name = "쓰러뜨린 개수", nullable = false)
    private Integer streakCount;

    @Column(name = "즐겨찾기", nullable = false)
    private boolean favoriteStatus;

    @OneToMany(mappedBy = "firstGoal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SecondGoal> secondGoals;

    @Column(name="사진", nullable=true)
    private String picture;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
