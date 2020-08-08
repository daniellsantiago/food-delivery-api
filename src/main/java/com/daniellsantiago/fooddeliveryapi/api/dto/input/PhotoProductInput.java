package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import com.daniellsantiago.fooddeliveryapi.core.validation.FileContentType;
import com.daniellsantiago.fooddeliveryapi.core.validation.FileSize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ApiModel("Photo Product Input Model")
public class PhotoProductInput {

    @ApiModelProperty(hidden = true)
    @NotNull
    @FileSize(max = "500KB", message = "The photo must have a maximum size of {max}")
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE },
                    message = "The photo type must be {allowed}")
    private MultipartFile file;

    @ApiModelProperty(value = "Product photo description", required = true)
    @NotBlank
    private String description;
}
