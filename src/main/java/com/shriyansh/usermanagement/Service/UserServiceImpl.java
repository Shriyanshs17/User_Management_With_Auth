package com.shriyansh.usermanagement.Service;

import com.shriyansh.usermanagement.Dto.UpdateProfileRequest;
import com.shriyansh.usermanagement.Repository.UserRepository;
import com.shriyansh.usermanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteUserById(Long id) {
        if(!userRepository.existsById(id))
            return ResponseEntity.notFound().build();
        userRepository.deleteById(id);
        return ResponseEntity.ok("user deleted");
    }

    @Override
    public ResponseEntity<?> updateProfile(String name, UpdateProfileRequest request) {
        User user=userRepository.findByUsername(name);
        if(user==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        user.setRole(request.getRole());
        user.setFullName(request.getFullName());

        if(request.getProfilePictureBase64()!=null){
            user.setProfilePictureBase64(request.getProfilePictureBase64());
        }
         userRepository.save(user);

        return ResponseEntity.ok("Profile Updated Successfully");
    }

    @Override
    public ResponseEntity<?> getCurrentUser(String name) {
        User user =userRepository.findByUsername(name);
        return ResponseEntity.ok(user);
    }

}
