package com.dormify.dormitories;

import com.dormify.common.BaseEntity;
import com.dormify.floors.Floor;
import com.dormify.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dormitories")
@Getter
@Setter
public class Dormitory extends BaseEntity {

    @Column(unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Floor> floors = new ArrayList<>();

    public void addFloor(Floor floor) {
        this.floors.add(floor);
        floor.setDormitory(this);
    }
}
