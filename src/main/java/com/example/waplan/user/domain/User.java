package com.example.waplan.user.domain;

import com.example.waplan.base.BaseEntity;
import com.example.waplan.goal.domain.GoalDate;
import com.example.waplan.goal.domain.Mandalart;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Member")
@DynamicInsert
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 70)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 11)
    private String phoneNum;

    @Column(nullable = false, length = 80)
    private String description;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 3)
    @ColumnDefault("'ON'")
    @Enumerated(EnumType.STRING)
    private AlarmType morningAlarm;

    @Column(length = 3)
    @ColumnDefault("'ON'")
    @Enumerated(EnumType.STRING)
    private AlarmType nightAlarm;

    @Column(length = 15)
    private String nickname;

    @Column(nullable = false)
    private String profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GoalDate> goalDates = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Mandalart> mandalartList = new ArrayList<>();

    public User(final Long id, final  String userId, final String password, final String email, final String phoneNum, final String description, final String nickname, final Role role, final AlarmType morningAlarm, final AlarmType nightAlarm){
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.description = description;
        this.nickname = nickname;
        this.password = password;
        this.phoneNum = phoneNum;
        this.role = role;
        this.morningAlarm = morningAlarm;
        this.nightAlarm = nightAlarm;
    }
    public User(final String userId, final String password, final String email, final String phoneNum, final String description, final String nickname, final Role role, final String profile){
        this.userId = userId;
        this.email = email;
        this.description = description;
        this.nickname = nickname;
        this.password = password;
        this.phoneNum = phoneNum;
        this.role = role;
        this.profile = profile;
    }

    public void updateMorningAlarm(final AlarmType morningAlarm){
        this.morningAlarm = morningAlarm;
    }

    public void updatePassword(String newPassword){
        this.password = newPassword;
    }

    public void updateNightAlarm(final AlarmType nightAlarm){
        this.nightAlarm = nightAlarm;
    }
    public String roleName() {
        return role.name();
    }

    public void addMandalart(final Mandalart mandalart){
        this.mandalartList.add(mandalart);
    }
}
