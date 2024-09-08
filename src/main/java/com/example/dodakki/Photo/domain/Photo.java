package com.example.dodakki.Photo.domain;

import com.example.dodakki.goal.domain.Mandalart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mandalart_id")
    @JsonIgnore
    private Mandalart mandalart;

    public Photo(final String path) {
        this.path = path;
    }
}
