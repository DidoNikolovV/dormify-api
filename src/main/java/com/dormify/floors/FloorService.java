package com.dormify.floors;

import com.dormify.common.*;
import com.dormify.dormitories.DormitoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dormify.common.ErrorMessage.FLOOR_ALREADY_EXISTS_IN_DORMITORY;
import static com.dormify.common.ErrorMessage.FLOOR_NOT_FOUND;
import static com.dormify.common.PaginationUtils.createPagingResult;

@AllArgsConstructor
@Service
public class FloorService {

    private final FloorRepository floorRepository;
    private final DormitoryService dormitoryService;
    private final FloorMapper floorMapper;

    @Transactional
    public FloorDto createFloor(Long dormitoryId, CreateFloorRequest request) {
        if (floorRepository.existsByNumberAndDormitoryId(request.getNumber(), dormitoryId)) {
            throw new ResourceAlreadyExistsException(FLOOR_ALREADY_EXISTS_IN_DORMITORY.getMessage(request.getNumber(), dormitoryId));
        }

        var dormitory = dormitoryService.getById(dormitoryId);
        var floor = floorMapper.toEntity(request);
        dormitory.addFloor(floor);

        floorRepository.save(floor);
        return floorMapper.toDto(floor);
    }

    public FloorDto getFloor(Long id) {
        var floor = getFloorById(id);
        return floorMapper.toDto(floor);
    }

    private Floor getFloorById(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(FLOOR_NOT_FOUND.getMessage(id)));
    }

    public PagingResult<FloorDto> getFloorsByDormitory(Long dormitoryId, PaginationRequest request) {
        var pageable = PaginationUtils.getPageable(request);
        var page = floorRepository.findByDormitoryId(dormitoryId, pageable);
        return createPagingResult(page, floorMapper::toDto);
    }

    @Transactional
    public FloorDto updateFloor(Long floorId, UpdateFloorRequest request) {
        Floor floor = getFloorById(floorId);
        floorRepository.save(floor);
        return floorMapper.toDto(floor);
    }
}
