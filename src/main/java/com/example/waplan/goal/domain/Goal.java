package com.example.waplan.goal.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    private Status attainment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "third_goal_id")
    private ThirdGoal thirdGoal;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<GoalDateMap> goalDateMapList = new ArrayList<GoalDateMap>();

    public Goal(final String name, final Status attainment, final ThirdGoal thirdGoal) {
        this.name = name;
        this.attainment = attainment;
        this.thirdGoal = thirdGoal;
    }
}
