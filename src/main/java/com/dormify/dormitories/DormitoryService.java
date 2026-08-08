package com.dormify.dormitories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
