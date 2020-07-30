package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import com.daniellsantiago.fooddeliveryapi.core.validation.FileContentType;
import com.daniellsantiago.fooddeliveryapi.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class PhotoProductInput {
    @NotNull
    @FileSize(max = "500KB", message = "The photo must have a maximum size of {max}")
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE },
                    message = "The photo type must be {allowed}")
    private MultipartFile file;

    @NotBlank
    private String description;
}
