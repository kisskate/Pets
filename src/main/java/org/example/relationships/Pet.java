package org.example.relationships;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(name = "birth_date")
    LocalDate birthDate;

    String breed;

    @Enumerated(EnumType.STRING)
    PetColor color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    Owner owner;

    @ManyToMany
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    List<Pet> friends = new ArrayList<>();

    public void addFriend(Pet friend) {
        friends.add(friend);
        friend.getFriends().add(this);
    }

    public void removeFriend(Pet friend) {
        friends.remove(friend);
        friend.getFriends().remove(this);
    }
    public Pet(String name, LocalDate birthDate, String breed, PetColor color){
        this.birthDate = birthDate;
        this.name = name;
        this.breed = breed;
        this.color = color;
    }
}