package com.dormify.floors;

import com.dormify.common.PaginationRequest;
import com.dormify.common.ResourceAlreadyExistsException;
import com.dormify.common.ResourceNotFoundException;
import com.dormify.dormitories.DormitoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static com.dormify.dormitories.DormitoryTestUtils.createDormitory;
import static com.dormify.floors.FloorTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
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

        var result = underTest.createFloor(1L, request);

        assertEquals(result.getNumber(), request.getNumber());
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
    void getFloor_whenFloorFound_thenReturnFloor() {
        var dormitory = createDormitory(1L, "Dormitory 1", 100);
        var floor = createFloor(1L, 1, dormitory);
        var floorDto = createFloorDto(1L, 1, 20, 1L, 1L);

        when(floorRepository.findById(1L)).thenReturn(Optional.of(floor));
        when(floorMapper.toDto(floor)).thenReturn(floorDto);

        var result = underTest.getFloor(1L);

        assertEquals(1L, result.getId());
        assertEquals(1L, result.getDormitoryId());
    }

    @Test
    void getFloor_whenFloorNotFound_thenThrowException() {
        when(floorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> underTest.getFloor(1L));
    }

    @Test
    void getFloorsByDormitory_whenFloorsFound_thenReturnPagingResult() {
        Long dormitoryId = 1L;
        var dormitory = createDormitory(1L, "Dormitory 1", 100);
        var request = new PaginationRequest(0, 5, "number", Sort.Direction.ASC);

        var floor1 = createFloor(1L, 1, dormitory);
        var floor2 = createFloor(2L, 2, dormitory);
        var floorDto1 = createFloorDto(1L, 1, 1, 1L, dormitoryId);
        var floorDto2 = createFloorDto(2L, 2, 1, 1L, dormitoryId);

        var pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "number"));
        var page = new PageImpl<>(List.of(floor1, floor2), pageable, 2);

        when(floorRepository.findByDormitoryId(dormitoryId, pageable)).thenReturn(page);
        when(floorMapper.toDto(floor1)).thenReturn(floorDto1);
        when(floorMapper.toDto(floor2)).thenReturn(floorDto2);

        var result = underTest.getFloorsByDormitory(dormitoryId, request);

        assertThat(result.getContent())
                .extracting(FloorDto::getNumber)
                .containsExactlyInAnyOrder(1, 2);

        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(result.getSize()).isEqualTo(5);
        assertThat(result.isEmpty()).isFalse();

        verify(floorRepository).findByDormitoryId(dormitoryId, pageable);
    }

    @Test
    void getFloorsByDormitory_whenNoFloorsFound_thenReturnEmptyPagingResult() {
        Long dormitoryId = 1L;
        var request = new PaginationRequest(0, 5, "number", Sort.Direction.ASC);
        var pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "number"));
        var emptyPage = new PageImpl<Floor>(List.of(), pageable, 0);

        when(floorRepository.findByDormitoryId(dormitoryId, pageable)).thenReturn(emptyPage);

        var result = underTest.getFloorsByDormitory(dormitoryId, request);

        assertThat(result.getContent()).isEmpty();
        assertThat(result.getTotalElements()).isEqualTo(0);
        assertThat(result.isEmpty()).isTrue();
        verifyNoInteractions(floorMapper);
    }

    @Test
    void updateFloor_whenFloorNotFound_thenThrowException() {
        var request = createUpdateRequest(3L);
        when(floorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> underTest.updateFloor(1L, request));

        verify(floorRepository, never()).save(any(Floor.class));
        verifyNoInteractions(floorMapper);
    }
}