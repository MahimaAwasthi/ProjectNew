package com.placementsystem.PlacementManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDetail {

    private String firstName;
    private String lastName;
    private String password;
}
