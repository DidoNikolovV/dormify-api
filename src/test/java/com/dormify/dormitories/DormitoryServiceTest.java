package com.dormify.dormitories;

import com.dormify.common.PaginationRequest;
import com.dormify.common.PaginationUtils;
import com.dormify.common.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.dormify.dormitories.DormitoryTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
    void updateDormitory_whenDormitoryNotFound_thenThrowException() {
        var request = createUpdateRequest(700);
        when(dormitoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> underTest.updateDormitory(1L, request));

        verify(dormitoryRepository).findById(1L);
        verifyNoInteractions(dormitoryMapper);
    }

    @Test
    void updateDormitory_whenCapacityIsUpdate_thenUpdateDormitory() {
        var request = createUpdateRequest(700);
        var dormitory = createDormitory(1L, "Dormitory 1", 650);
        var dormitoryDto = createDormitoryDto(1L, "Dormitory 1", 700);

        when(dormitoryRepository.findById(1L)).thenReturn(Optional.of(dormitory));
        when(dormitoryMapper.toDto(dormitory)).thenReturn(dormitoryDto);

        var result = underTest.updateDormitory(1L, request);

        assertEquals(700, result.getCapacity());
    }

    @Test
    void deleteDormitory_whenDormitoryFound_thenDeleteDormitory() {
        var dormitory = createDormitory(1L, "Dormitory 1", 650);
        when(dormitoryRepository.findById(1L)).thenReturn(Optional.of(dormitory));

        underTest.deleteDormitory(1L);

        verify(dormitoryRepository).delete(dormitory);
    }

    @Test
    void deleteDormitory_whenDormitoryNotFound_thenThrowException() {
        when(dormitoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> underTest.deleteDormitory(1L));

        verify(dormitoryRepository).findById(1L);
        verifyNoMoreInteractions(dormitoryRepository);
    }

    @Test
    void getDormitories_whenDormitoriesFound_thenReturnPagingResult() {
        var request = new PaginationRequest(0, 5, "id", Sort.Direction.ASC);
        var dormitory1 = createDormitory(1L, "Dormitory 1", 650);
        var dormitory2 = createDormitory(2L, "Dormitory 2", 700);
        var dormitoryDto1 = createDormitoryDto(1L, "Dormitory 1", 650);
        var dormitoryDto2 = createDormitoryDto(2L, "Dormitory 2", 700);

        var pageable = PaginationUtils.getPageable(request);
        var page = new PageImpl<>(List.of(dormitory1, dormitory2), pageable, 2);

        when(dormitoryRepository.findAll(pageable)).thenReturn(page);
        when(dormitoryMapper.toDto(dormitory1)).thenReturn(dormitoryDto1);
        when(dormitoryMapper.toDto(dormitory2)).thenReturn(dormitoryDto2);

        var result = underTest.getDormitories(request);

        assertThat(result.getContent())
                .hasSize(2)
                .extracting(DormitoryDto::getName)
                .containsExactly("Dormitory 1", "Dormitory 2");

        assertEquals(2, result.getTotalElements());
    }
}