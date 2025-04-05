package com.example.element_managment;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "albums")
public class Album implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    public Album() {}

    public Album(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
