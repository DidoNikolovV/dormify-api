package com.dormify.floors;

import com.dormify.common.ResourceAlreadyExistsException;
import com.dormify.common.ResourceNotFoundException;
import com.dormify.dormitories.DormitoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.dormify.dormitories.DormitoryTestUtils.createDormitory;
import static com.dormify.floors.FloorTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FloorServiceTest {

    @Mock
    private FloorRepository floorRepository;
    @Mock
    private DormitoryService dormitoryService;
    @Mock
    private FloorMapper floorMapper;

    @InjectMocks
    private FloorService underTest;

    @Test
    void createFloor_whenFloorWithSameNumberDoesNotExistInDormitory_thenCreateFloorAndAddItToDormitory() {
        var request = createFloorCreateRequest(1, 20, 1L);
        var dormitory = createDormitory(1L, "Dormitory 1", 100);
        var floor = createFloor(1, dormitory);
        var floorDto = createFloorDto(1L, 1, 1, 1L, dormitory.getId());

        when(floorRepository.existsByNumberAndDormitoryId(1, dormitory.getId())).thenReturn(false);
        when(dormitoryService.getById(1L)).thenReturn(dormitory);
        when(floorMapper.toEntity(request)).thenReturn(floor);
        when(floorRepository.save(floor)).thenReturn(floor);
        when(floorMapper.toDto(floor)).thenReturn(floorDto);

        var actual = underTest.createFloor(1L, request);

        assertEquals(actual.getNumber(), request.getNumber());
        verify(floorRepository).save(floor);
    }

    @Test
    void createFloor_whenFloorWithSameNumberAlreadyExistInDormitory_thenThrowException() {
        var request = createFloorCreateRequest(1, 20, 1L);

        when(floorRepository.existsByNumberAndDormitoryId(1, 1L)).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class,
                () -> underTest.createFloor(1L, request));

        verifyNoMoreInteractions(floorRepository);
        verifyNoInteractions(dormitoryService);
        verifyNoInteractions(floorMapper);
    }

    @Test
    void getFloor() {
    }

    @Test
    void getFloorsByDormitory() {
    }

    @Test
    void updateFloor() {
    }
}