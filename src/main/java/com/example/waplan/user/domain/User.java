package com.example.waplan.user.domain;

import com.example.waplan.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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

    public User(final Long id, final  String userId, final String password, final String email, final String phoneNum, final String description, final String nickname){
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.description = description;
        this.nickname = nickname;
        this.password = password;
        this.phoneNum = phoneNum;
    }
    public User(final String userId, final String password, final String email, final String phoneNum, final String description, final String nickname, final Role role){
        this.userId = userId;
        this.email = email;
        this.description = description;
        this.nickname = nickname;
        this.password = password;
        this.phoneNum = phoneNum;
        this.role = role;
    }

    public void updateMorningAlarm(final AlarmType morningAlarm){
        this.morningAlarm = morningAlarm;
    }
    public String roleName() {
        return role.name();
    }
}
