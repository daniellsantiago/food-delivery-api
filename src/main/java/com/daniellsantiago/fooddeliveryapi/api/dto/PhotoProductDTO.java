package com.daniellsantiago.fooddeliveryapi.api.dto;

import com.daniellsantiago.fooddeliveryapi.core.validation.FileContentType;
import com.daniellsantiago.fooddeliveryapi.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PhotoProductDTO {

    private String filename;
    private String description;
    private String contentType;
    private Long size;

}
