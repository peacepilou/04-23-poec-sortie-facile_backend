package com.poec.projet_backend.domain.profile;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.booking.Booking;
import com.poec.projet_backend.domain.category.Category;
import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.department.Department;
import com.poec.projet_backend.domain.region.Region;
import com.poec.projet_backend.user_app.UserApp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", columnDefinition = "VARCHAR(100)", nullable = false)
    private String firstname;

    @Column(name = "lastname", columnDefinition = "VARCHAR(100)", nullable = false)
    private String lastname;

    @Column(name = "street_number", columnDefinition = "VARCHAR(100)")
    private String streetNumber;

    @Column(name = "street", columnDefinition = "VARCHAR(100)")
    private String street;

    @Column(name = "postal_code", columnDefinition = "INT(5)", nullable = false)
    private Long postalCode;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "avatar", columnDefinition = "VARCHAR(255)")
    private String avatar;

    @Column(name = "phone", columnDefinition = "VARCHAR(14)")
    private String phone;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private List<Activity> activities;

    @OneToMany(mappedBy = "profile")
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserApp user;
}






