package com.dormify.timeslots;

import com.dormify.common.BaseEntity;
import com.dormify.users.User;
import com.dormify.washingmachines.WashingMachine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "time_slots")
public class TimeSlot extends BaseEntity {
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "type")
    private TimeSlotType type;

    @Column(name = "recurrence_rule")
    private String recurrenceRule;

    @Column(name = "created_at", insertable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "washing_machine_id")
    private WashingMachine washingMachine;

    @ManyToOne
    @JoinColumn(name = "booked_by")
    private User user;
}
