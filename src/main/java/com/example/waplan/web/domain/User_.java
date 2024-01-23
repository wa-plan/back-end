package com.example.waplan.web.domain;

import com.example.waplan.web.domain.enums.Alarm;
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

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Member")
public class User_ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 16)
    private String password;

    private String email;

    @Column(nullable = false, length = 11)
    private String phoneNum;

    @Column(nullable = true, length = 80)
    private String introduce;

    @Column(columnDefinition = "VARCHAR(3) DEFAULT 'OFF'")
    @Enumerated(EnumType.STRING)
    private Alarm dominoAlarm;

    @Column(columnDefinition = "VARCHAR(3) DEFAULT 'OFF'")
    @Enumerated(EnumType.STRING)
    private Alarm morningAlarm;

    @Column(columnDefinition = "VARCHAR(3) DEFAULT 'OFF'")
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
