package com.example.waplan.goal.domain;

import com.example.waplan.goal.domain.mapping.DateMapping;
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
@Table(name = "제3목표")
@Getter
@Setter
public class ThirdGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 15)
    private String title;

    @Column(name = "newTitle", nullable = true, length = 15)
    private String newTitle;

    @Column(name = "color", nullable = false, length = 15)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "성취도", nullable = false)
    private AchievementLevel achievementLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_goal_id", referencedColumnName = "id")
    private SecondGoal secondGoal;

    @OneToMany(mappedBy = "thirdGoal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DateMapping> dateMappings;
}
