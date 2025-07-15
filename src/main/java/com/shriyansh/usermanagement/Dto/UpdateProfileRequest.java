package com.shriyansh.usermanagement.Dto;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String role;
    private String fullName;
    private String profilePictureBase64;
}
