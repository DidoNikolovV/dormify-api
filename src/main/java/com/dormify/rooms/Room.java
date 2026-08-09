package com.dormify.rooms;

import com.dormify.common.BaseEntity;
import com.dormify.floors.Floor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static com.dormify.rooms.OccupancyType.DOUBLE;
import static com.dormify.rooms.RoomStatus.NON_OCCUPIED;
import static com.dormify.rooms.RoomType.RESIDENTIAL;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {
    @Column(nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RoomStatus status = NON_OCCUPIED;

    @Enumerated(EnumType.STRING)
    @Column(name = "occupancy_type")
    private OccupancyType occupancyType = DOUBLE;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoomType type = RESIDENTIAL;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;
}
