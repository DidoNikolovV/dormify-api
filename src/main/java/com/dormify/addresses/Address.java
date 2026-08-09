package com.dormify.addresses;

import com.dormify.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address extends BaseEntity {
    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "municipality")
    private String municipality;

    @Column(name = "entrance")
    private String entrance;

    @Column(name = "floor")
    private int floor;
}
