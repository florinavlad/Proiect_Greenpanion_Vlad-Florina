package com.florina.greenpanion.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointsRequest {
    private String email;
    private Integer points;

    public Integer getUserPoints() {
        return points;
    }
    public String getEmail() {
        return email;
    }
}
