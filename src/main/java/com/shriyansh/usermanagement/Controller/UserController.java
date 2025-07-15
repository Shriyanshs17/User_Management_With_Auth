package com.shriyansh.usermanagement.Controller;

import com.shriyansh.usermanagement.Dto.UpdateProfileRequest;
import com.shriyansh.usermanagement.Repository.UserRepository;
import com.shriyansh.usermanagement.Service.UserService;
import com.shriyansh.usermanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<User>  getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.deleteUserById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request, Principal principal)
    {
return userService.updateProfile(principal.getName(),request);

    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(Principal principal)
    {
        return userService.getCurrentUser(principal.getName());
    }



}
