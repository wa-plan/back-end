package com.example.waplan.user.domain.goal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "제목표")
public class SecondGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 15)
    private String title;

    @Column(name = "field", nullable = false, length = 15)
    private String field;

    @ManyToOne
    @JoinColumn(name = "first_goal_id", referencedColumnName = "id")
    private FirstGoal firstGoal;

    @OneToMany(mappedBy= "secondGoal", cascade = CascadeType.ALL, orphanRemoval= true)
    private List<ThirdGoal> thirdGoals;
}
