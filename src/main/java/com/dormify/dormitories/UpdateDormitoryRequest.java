package com.dormify.dormitories;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDormitoryRequest {
    private Long managerId;
    private Integer capacity;
}
