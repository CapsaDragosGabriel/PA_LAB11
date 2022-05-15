package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    private Long id;
    @NotNull
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch=FetchType.EAGER)
    @JoinTable(
            name = "friends",
            joinColumns = { @JoinColumn( name = "id1" ) },
            inverseJoinColumns = { @JoinColumn( name = "id2" ) }
    )
    private List<User> friends=new ArrayList<>();
    public User(@JsonProperty("id") Long id,
                @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
    /*public User(@JsonProperty("id") Long id,
                @JsonProperty("name") String name,
                @JsonProperty("friends") List<User> friends) {
        this.id = id;
        this.name = name;
        this.friends=friends;
    }*/
}
