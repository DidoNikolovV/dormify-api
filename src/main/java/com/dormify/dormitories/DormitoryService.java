package com.dormify.dormitories;

import com.dormify.common.PaginationRequest;
import com.dormify.common.PaginationUtils;
import com.dormify.common.PagingResult;
import com.dormify.common.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dormify.common.ErrorMessage.DORMITORY_NOT_FOUND;

@AllArgsConstructor
@Service
public class DormitoryService {
    private final DormitoryRepository dormitoryRepository;
    private final DormitoryMapper dormitoryMapper;

    public DormitoryDto createDormitory(CreateDormitoryRequest request) {
        var dormitory = dormitoryMapper.toEntity(request);

        dormitoryRepository.save(dormitory);

        return dormitoryMapper.toDto(dormitory);
    }

    public DormitoryDto getDormitoryById(Long id) {
        var dormitory = getById(id);
        return dormitoryMapper.toDto(dormitory);
    }

    @Transactional
    public DormitoryDto updateDormitory(Long id, UpdateDormitoryRequest request) {
        var dormitory = getById(id);

        if (request.getCapacity() != null) {
            dormitory.setCapacity(request.getCapacity());
        }

        dormitoryRepository.save(dormitory);
        return dormitoryMapper.toDto(dormitory);
    }

    @Transactional
    public void deleteDormitory(Long id) {
        var dormitory = getById(id);
        dormitory.setManager(null);
        dormitoryRepository.delete(dormitory);
    }

    public Dormitory getById(Long id) {
        return dormitoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DORMITORY_NOT_FOUND.getMessage(id)));
    }

    public PagingResult<DormitoryDto> getDormitories(PaginationRequest request) {
        var pageable = PaginationUtils.getPageable(request);
        var page = dormitoryRepository.findAll(pageable);
        return PaginationUtils.createPagingResult(page, dormitoryMapper::toDto);
    }
}
