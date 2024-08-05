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
public class SecondGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mandalart_id")
    private Mandalart mandalart;

    @OneToMany(mappedBy = "secondGoal", cascade = CascadeType.ALL)
    private List<ThirdGoal> thridGoalList = new ArrayList<ThirdGoal>();

    public SecondGoal(final String name, final String color, final Mandalart mandalart) {
        this.name = name;
        this.color = color;
        this.mandalart = mandalart;
    }
}
