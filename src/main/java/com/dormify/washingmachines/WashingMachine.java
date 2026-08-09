package com.dormify.washingmachines;


import com.dormify.common.BaseEntity;
import com.dormify.rooms.Room;
import com.dormify.timeslots.TimeSlot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "washing_machines")
@Getter
@Setter
public class WashingMachine extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "limit_per_person_per_week")
    private int limitPerPersonPerWeek;

    @OneToMany(mappedBy = "washingMachine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeSlot> timeSlots = new ArrayList<>();
}
