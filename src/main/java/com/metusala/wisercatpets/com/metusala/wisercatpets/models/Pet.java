package com.metusala.wisercatpets.com.metusala.wisercatpets.models;

import com.metusala.wisercatpets.com.metusala.wisercatpets.enums.CountryOfOrigin;
import com.metusala.wisercatpets.com.metusala.wisercatpets.enums.PetFurColor;
import com.metusala.wisercatpets.com.metusala.wisercatpets.enums.PetType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long code;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PetType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PetFurColor furColor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CountryOfOrigin countryOfOrigin;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Pet() {
    }

    public Pet(long code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public PetFurColor getFurColor() {
        return furColor;
    }

    public void setFurColor(PetFurColor furColor) {
        this.furColor = furColor;
    }

    public CountryOfOrigin getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(CountryOfOrigin countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
