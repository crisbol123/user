package com.pragma.user.adapters.driving.http.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorizationResponse
{
    private boolean isPresent;
    private Long id;
    private String role;

}
