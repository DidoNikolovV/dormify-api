package com.dormify.dormitories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.dormify.dormitories.DormitoryTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void getDormitoryById() {
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