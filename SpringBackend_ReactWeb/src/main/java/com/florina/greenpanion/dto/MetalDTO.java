package com.florina.greenpanion.dto;

import com.florina.greenpanion.model.EMetal;
import com.florina.greenpanion.model.EPlastic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetalDTO {
    private long id;
    private EMetal metalType;
}
