package com.jiangwensi.busarrival.domain.dto;

import lombok.Data;

/**
 * Created by Jiang Wensi on 17/7/2020
 */
@Data
public class IconAttribute {
    String imagePath;
    String attributeText;

    public IconAttribute(String imagePath, String attributeText) {
        this.imagePath = imagePath;
        this.attributeText = attributeText;
    }
}
