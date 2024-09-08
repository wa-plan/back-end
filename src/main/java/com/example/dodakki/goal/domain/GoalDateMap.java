package com.example.dodakki.goal.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalDateMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_date_id")
    private GoalDate goalDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status attainment;

    public GoalDateMap(final Goal goal, final GoalDate goalDate, final Status attainment) {
        this.goal = goal;
        this.goalDate = goalDate;
        this.attainment = attainment;
    }
}
