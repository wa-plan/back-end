package com.example.waplan.goal.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThirdGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_goal_id")
    private SecondGoal secondGoal;

    public ThirdGoal(final String name, final SecondGoal secondGoal) {
        this.name = name;
        this.secondGoal = secondGoal;
    }
}
