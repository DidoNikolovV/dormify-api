package com.dormify.floors;


import com.dormify.dormitories.Dormitory;
import org.hibernate.sql.Update;

public class FloorTestUtils {

    public static CreateFloorRequest createFloorCreateRequest(int number, int rooms, Long personid) {
        var request = new CreateFloorRequest();
        request.setNumber(number);
        request.setTotalNumberOfRooms(rooms);
        request.setRepresentativePersonId(personid);
        return request;
    }

    public static FloorDto createFloorDto(Long id, int number, int rooms, Long personid, Long dormitoryId) {
        var floorDto = new FloorDto();
        floorDto.setId(id);
        floorDto.setNumber(number);
        floorDto.setTotalNumberOfRooms(rooms);
        floorDto.setRepresentativePersonId(personid);
        floorDto.setDormitoryId(dormitoryId);
        return floorDto;
    }

    public static Floor createFloor(int number, Dormitory dormitory) {
        var floor = new Floor();
        floor.setNumber(number);
        dormitory.addFloor(floor);
        return floor;
    }

    public static Floor createFloor(Long id, int number, Dormitory dormitory) {
        var floor = createFloor(number, dormitory);
        floor.setId(id);
        return floor;
    }

    public static UpdateFloorRequest createUpdateRequest(Long personId) {
        var request = new UpdateFloorRequest();
        request.setRepresentativePersonId(personId);
        return request;
    }


}
