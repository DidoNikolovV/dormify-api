package com.dormify.dormitory;

import com.dormify.common.BaseEntity;
import com.dormify.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
