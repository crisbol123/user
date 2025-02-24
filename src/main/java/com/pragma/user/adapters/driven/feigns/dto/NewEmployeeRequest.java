package com.pragma.user.adapters.driven.feigns.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NewEmployeeRequest {
    private long restaurantId;
    private long employeeId;
}
