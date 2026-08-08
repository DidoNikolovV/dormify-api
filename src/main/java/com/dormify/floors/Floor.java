package com.dormify.floors;

import com.dormify.common.BaseEntity;
import com.dormify.dormitories.Dormitory;
import com.dormify.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
