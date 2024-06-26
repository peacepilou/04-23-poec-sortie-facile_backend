package com.poec.projet_backend.domain.department;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.profile.Profile;
import com.poec.projet_backend.domain.region.Region;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Profile> profiles = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<City> cities = new ArrayList<>();
}
