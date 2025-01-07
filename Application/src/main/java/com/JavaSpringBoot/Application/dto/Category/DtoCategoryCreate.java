package com.JavaSpringBoot.Application.dto.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DtoCategoryCreate {
    @NotBlank(message = "A category must have a tag!")
    @Size(max = 10)
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
