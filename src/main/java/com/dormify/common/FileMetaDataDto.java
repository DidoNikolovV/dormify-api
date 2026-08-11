package com.dormify.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileMetaDataDto {
    @Schema(description = "Unique identifier of the file", example = "10")
    private Long id;

    @Schema(description = "Original file name uploaded by the user", example = "document.pdf")
    private String originalName;

    @Schema(description = "Size of the file in bytes", example = "204800")
    private Long fileSize;

    @Schema(description = "MIME type of the file", example = "application/pdf")
    private String contentType;

    @Schema(
            description = "Logical type of the file",
            example = "PROFILE_PICTURE"
    )
    private String fileType;
}
