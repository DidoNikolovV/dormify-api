package com.dormify.dormitories;

import com.dormify.common.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.dormify.dormitories.DormitoryTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DormitoryServiceTest {

    @Mock
    private DormitoryRepository dormitoryRepository;
    @Mock
    private DormitoryMapper dormitoryMapper;

    @InjectMocks
    private DormitoryService underTest;

    @Test
    void createDormitory_thenCreateDormitory() {
        var request = createDormitoryCreateRequest("Dormitory 1", 650);
        var dormitory = createDormitory("Dormitory 1", 650);
        var dto = createDormitoryDto("Dormitory 1", 650);
        when(dormitoryMapper.toEntity(request)).thenReturn(dormitory);
        when(dormitoryRepository.save(dormitory)).thenReturn(dormitory);
        when(dormitoryMapper.toDto(dormitory)).thenReturn(dto);

        var result = underTest.createDormitory(request);

        assertEquals(request.getName(), result.getName());
        assertEquals(request.getCapacity(), result.getCapacity());
        verify(dormitoryMapper).toEntity(request);
        verify(dormitoryRepository).save(dormitory);
    }

    @Test
    void getDormitoryById_whenDormitoryNotFound_thenThrowException() {
        when(dormitoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> underTest.getDormitoryById(1L));
    }

    @Test
    void getDormitoryById_whenDormitoryFound_thenReturnDormitory() {
        var dormitory = createDormitory(1L, "Dormitory 1", 650);
        var dormitoryDto = createDormitoryDto(1L, "Dormitory 1", 650);
        when(dormitoryRepository.findById(1L)).thenReturn(Optional.of(dormitory));
        when(dormitoryMapper.toDto(dormitory)).thenReturn(dormitoryDto);

        var result = underTest.getDormitoryById(1L);

        assertEquals(dormitoryDto.getName(), result.getName());
        assertEquals(dormitoryDto.getId(), result.getId());
    }

    @Test
    void updateDormitory() {
    }

    @Test
    void deleteDormitory() {
    }

    @Test
    void getDormitories() {
    }
}