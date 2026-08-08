package com.dormify.users;

import com.dormify.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "national_id", unique = true)
    private String nationalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_type")
    private IdType idType;

    @Column(unique = true)
    private String facultyNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    public String getName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
