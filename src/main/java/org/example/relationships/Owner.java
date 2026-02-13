package org.example.relationships;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(name = "birth_date")
    LocalDate birthDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Pet> pets = new ArrayList<>();

    public Owner(String name, LocalDate now) {
        this.name = name;
        this.birthDate = now;
    }
    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }
}