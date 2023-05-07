package com.pinapp.challenge.model;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @NotNull
    @Column (nullable = false)
    private LocalDate birthday;
    private Integer age;
    private LocalDate potentialDateOfDeath;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getPotentialDateOfDeath() {
        return potentialDateOfDeath;
    }

    public void setPotentialDateOfDeath(LocalDate potentialDateOfDeath) {
        this.potentialDateOfDeath = potentialDateOfDeath;
    }

    

}
