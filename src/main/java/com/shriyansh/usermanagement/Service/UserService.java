package com.shriyansh.usermanagement.Service;

import com.shriyansh.usermanagement.Dto.UpdateProfileRequest;
import com.shriyansh.usermanagement.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    ResponseEntity<?> getUserById(Long id);

    ResponseEntity<?> deleteUserById(Long id);

    ResponseEntity<?> updateProfile(String name, UpdateProfileRequest request);

    ResponseEntity<?> getCurrentUser(String name);
}
