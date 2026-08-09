package com.dormify.dormitories;

import com.dormify.common.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        var dormitory = dormitoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DORMITORY_NOT_FOUND.getMessage(id)));
        return dormitoryMapper.toDto(dormitory);
    }

    public DormitoryDto updateDormitory(Long id, UpdateDormitoryRequest request) {
        var dormitory = dormitoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DORMITORY_NOT_FOUND.getMessage(id)));

        if (request.getCapacity() != null) {
            dormitory.setCapacity(request.getCapacity());
        }

        dormitoryRepository.save(dormitory);
        return dormitoryMapper.toDto(dormitory);
    }
}
