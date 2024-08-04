package com.example.waplan.goal.domain;

import com.example.waplan.Photo.domain.Photo;
import com.example.waplan.user.domain.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Mandalart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = true, length = 80)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Bookmark bookmark;

    @Column(nullable = false, length = 20)
    private String color;

    @OneToMany(mappedBy = "mandalart", cascade = CascadeType.ALL)
    private List<SecondGoal> secondGoalList = new ArrayList<SecondGoal>();

    @OneToOne
    @JoinColumn(name = "goal_date_id")
    private GoalDate goalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "mandalart", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Photo> photoList = new ArrayList<>();

    @Column(nullable = false)
    private Long attainmentCount;

    @Column(nullable = false)
    private Integer dDay;

    @Column(nullable = false)
    private Long goalCount;

    public Mandalart(final User user, final String name, final String description, final String color, final Long attainmentCount, final Integer dDay, final Long goalCount) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.color = color;
        this.status = Status.IN_PROGRESS;
        this.bookmark = Bookmark.UNBOOKMARK;
        this.attainmentCount = attainmentCount;
        this.dDay = dDay;
        this.goalCount = goalCount;
    }

    public void addPhoto(final Photo photo){
        photoList.add(photo);
        photo.setMandalart(this);
    }
}
