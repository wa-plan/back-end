package com.example.waplan.goal.domain;


import com.example.waplan.goal.domain.Mandalart;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne(mappedBy = "goalDate")
    private Mandalart mandalart;

    @OneToMany(mappedBy = "goalDate", cascade = CascadeType.ALL)
    private List<GoalDateMap> goalDateMapList = new ArrayList<GoalDateMap>();

    public GoalDate(final LocalDate date) {
        this.date = date;
    }
}
