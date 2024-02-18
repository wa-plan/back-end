package com.example.waplan.domain;

import com.example.waplan.domain.enums.Alarm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    private String email;

    @Column(nullable = false, length = 11)
    private String phoneNum;

    @Column(nullable = true, length = 80)
    private String introduce;

    private String role;


    @Column(nullable = false, length = 3)
    @ColumnDefault("'ON'")
    @Enumerated(EnumType.STRING)
    private Alarm morningAlarm;

    @Column(nullable = false, length = 3)
    @ColumnDefault("'ON'")
    @Enumerated(EnumType.STRING)
    private Alarm nightAlarm;


    @Column(nullable = true, length = 15)
    private String nickname;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WiseSaying> wiseSaying = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Photo> photo = new ArrayList<>();

}
