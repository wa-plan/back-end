package com.example.waplan.goal.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "goalDate", cascade = CascadeType.ALL)
    private List<Mandalart> mandalartList = new ArrayList<>();

    @OneToMany(mappedBy = "goalDate", cascade = CascadeType.ALL)
    private List<GoalDateMap> goalDateMapList = new ArrayList<GoalDateMap>();

    public GoalDate(final LocalDate date) {
        this.date = date;
    }
}
