package com.example.waplan.goal.domain;


import com.example.waplan.user.domain.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "goalDate", cascade = CascadeType.ALL)
    private List<Mandalart> mandalartList = new ArrayList<>();

    @OneToMany(mappedBy = "goalDate", cascade = CascadeType.ALL)
    private List<GoalDateMap> goalDateMapList = new ArrayList<GoalDateMap>();

    public GoalDate(final User user, final LocalDate date) {
        this.user = user;
        this.date = date;
    }
}
