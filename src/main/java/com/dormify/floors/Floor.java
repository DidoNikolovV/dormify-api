package com.dormify.floors;

import com.dormify.common.BaseEntity;
import com.dormify.dormitories.Dormitory;
import com.dormify.rooms.Room;
import com.dormify.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "floors")
public class Floor extends BaseEntity {
    @Column(nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "representative_id")
    private User representative;

    @ManyToOne
    @JoinColumn(name = "dormitory_id")
    private Dormitory dormitory;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();
}
