package com.dormify.dormitories;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/dormitories")
public class DormitoryController {

    private final DormitoryService dormitoryService;

    @PostMapping
    public ResponseEntity<DormitoryDto> createDormitory(
            @RequestBody @Valid CreateDormitoryRequest request,
            UriComponentsBuilder uriBuilder) {
        var dormitory = dormitoryService.createDormitory(request);
        var uri = uriBuilder.path("/dormitories/{id}").buildAndExpand(dormitory.getId()).toUri();
        return ResponseEntity.created(uri).body(dormitory);
    }
}
