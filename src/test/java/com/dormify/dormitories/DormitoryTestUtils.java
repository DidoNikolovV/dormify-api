package com.dormify.dormitories;

public class DormitoryTestUtils {

    public static CreateDormitoryRequest createDormitoryCreateRequest(String name, Integer capacity) {
        var request = new CreateDormitoryRequest();
        request.setName(name);
        request.setCapacity(capacity);
        return request;
    }

    public static Dormitory createDormitory(String name, Integer capacity) {
        var dormitory = new Dormitory();
        dormitory.setName(name);
        dormitory.setCapacity(capacity);
        return dormitory;
    }

    public static Dormitory createDormitory(Long id, String name, Integer capacity) {
        var dormitory = createDormitory(name, capacity);
        dormitory.setId(id);
        return dormitory;
    }

    public static DormitoryDto createDormitoryDto(String name, Integer capacity) {
        var dto = new DormitoryDto();
        dto.setName(name);
        dto.setCapacity(capacity);
        return dto;
    }

    public static DormitoryDto createDormitoryDto(Long id, String name, Integer capacity) {
        var dto = createDormitoryDto(name, capacity);
        dto.setId(id);
        return dto;
    }
}
