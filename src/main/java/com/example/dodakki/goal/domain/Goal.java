package com.example.dodakki.goal.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(nullable = false)
    private Repetition repetition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "third_goal_id")
    @JsonIgnore
    private ThirdGoal thirdGoal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mandalart_id")
    @JsonIgnore
    private Mandalart mandalart;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<GoalDateMap> goalDateMapList = new ArrayList<GoalDateMap>();

    public Goal(final String name,final Mandalart mandalart, final ThirdGoal thirdGoal, final Repetition repetition) {
        this.name = name;
        this.mandalart = mandalart;
        this.thirdGoal = thirdGoal;
        this.repetition = repetition;
    }
}
