package com.florina.greenpanion.dto;

import com.florina.greenpanion.model.EPlastic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlasticDTO {
    private long id;
    private EPlastic plasticType;
}
