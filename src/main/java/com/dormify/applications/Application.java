package com.dormify.applications;

import com.dormify.common.BaseEntity;
import com.dormify.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.dormify.applications.ApplicationStatus.INITIALIZING;

@Getter
@Setter
@Table(name = "applications")
@Entity
public class Application extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status = INITIALIZING;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ApplicationType type;

    @Column(name = "relocation_reason")
    private String relocationReason;

    @CreationTimestamp
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_government_funded")
    private boolean isGovernmentFunded;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_form")
    private StudyForm studyForm;

    @Column(name = "gpa")
    private Double gpa;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "faculty")
    private String faculty;
    @Column(name = "course")
    private String course;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "spouse_first_name")
    private String spouseFirstName;

    @Column(name = "spouse_middle_name")
    private String spouseMiddleName;

    @Column(name = "spouse_last_name")
    private String spouseLastName;

    @Column(name = "current_dormitory")
    private String currentDormitory;

    @Column(name = "current_room")
    private String currentRoom;
}
