package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto;
import com.example.demo.services.UserServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register/admin")
    public UserResDto registerAdmin(@RequestBody UserReqDto dto) {
        return userService.registerAdmin(dto);
    }

    @PostMapping("/register/toko")
    public UserResDto registerToko(@RequestBody UserReqDto dto) {
        return userService.registerToko(dto);
    }

    @PostMapping("/register/pembeli")
    public UserResDto registerPembeli(@RequestBody UserReqDto dto) {
        return userService.registerPembeli(dto);
    }

    @PostMapping("/login")
    public UserResDto login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }

    @PostMapping("create")
    public UserResDto create(@RequestBody UserReqDto dto) {
        return userService.create(dto);
    }

    @PutMapping("update/{id}")
    public UserResDto update(@PathVariable Long id, @RequestBody UserReqDto dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("findById/{id}")
    public UserResDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/all")
    public List<UserResDto> findAll() {
        return userService.findAll();
    }

    
}

// package com.example.demo.controllers;

// import com.example.demo.dto.UserReqDto;
// import com.example.demo.dto.UserResDto;
// import com.example.demo.services.UserService;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.validation.Valid;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/v1/users")
// public class UserController {

//     private final UserService userService;

//     public UserController(UserService userService) {
//         this.userService = userService;
//     }

//     @PostMapping("/register/admin")
//     @ResponseStatus(HttpStatus.CREATED)
//     public ResponseEntity<?> registerAdmin(
//             @RequestHeader("Authorization") String authHeader,
//             @Valid @RequestBody UserReqDto userReqDto) {
        
//         // Manual admin authorization check
//         if (!isSuperAdmin(authHeader)) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only super admins can create admin users");
//         }
        
//         try {
//             UserResDto createdUser = userService.registerAdmin(userReqDto);
//             return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//         } catch (RuntimeException e) {
//             return handleException(e);
//         }
//     }

//     @PostMapping("/register/toko")
//     @ResponseStatus(HttpStatus.CREATED)
//     public ResponseEntity<?> registerToko(@Valid @RequestBody UserReqDto userReqDto) {
//         try {
//             UserResDto createdUser = userService.registerToko(userReqDto);
//             return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//         } catch (RuntimeException e) {
//             return handleException(e);
//         }
//     }

//     @PostMapping("/register/pembeli")
//     @ResponseStatus(HttpStatus.CREATED)
//     public ResponseEntity<?> registerPembeli(@Valid @RequestBody UserReqDto userReqDto) {
//         try {
//             UserResDto createdUser = userService.registerPembeli(userReqDto);
//             return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//         } catch (RuntimeException e) {
//             return handleException(e);
//         }
//     }

//     @PostMapping("/login")
//     public ResponseEntity<?> login(
//             @RequestParam String email,
//             @RequestParam String password,
//             HttpServletRequest request) {
        
//         try {
//             UserResDto user = userService.login(email, password);
            
//             // In a real implementation, you would generate and return a token here
//             request.getSession().setAttribute("currentUser", user);
            
//             return ResponseEntity.ok(user);
//         } catch (RuntimeException e) {
//             return handleException(e);
//         }
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<?> getUserById(
//             @RequestHeader("Authorization") String authHeader,
//             @PathVariable Long id) {
        
//         try {
//             // Manual authorization - user can access their own data or admin can access any
//             UserResDto currentUser = getCurrentUser(authHeader);
//             if (!currentUser.getRole().equals("ADMIN") && !currentUser.getRole().equals("SUPER_ADMIN") && !id.equals(currentUser.getId())) {
//                 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
//             }
            
//             UserResDto user = userService.findById(id);
//             return ResponseEntity.ok(user);
//         } catch (RuntimeException e) {
//             return handleException(e);
//         }
//     }

//     @GetMapping
//     public ResponseEntity<?> getAllUsers(
//             @RequestHeader("Authorization") String authHeader,
//             @RequestParam(defaultValue = "0") int page,
//             @RequestParam(defaultValue = "10") int size,
//             @RequestParam(defaultValue = "email") String sortBy,
//             @RequestParam(defaultValue = "asc") String direction,
//             @RequestParam(required = false) String role,
//             @RequestParam(required = false) Boolean status) {
        
//         // Only admins can list all users
//         if (!isAdmin(authHeader)) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can list all users");
//         }
        
//         try {
//             Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
//             Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

//             // Simplified pagination - in real implementation you would implement this in service
//             Page<UserResDto> userPage = Page.empty(); // Replace with actual implementation

//             Map<String, Object> response = new HashMap<>();
//             response.put("users", userPage.getContent());
//             response.put("currentPage", userPage.getNumber());
//             response.put("totalItems", userPage.getTotalElements());
//             response.put("totalPages", userPage.getTotalPages());

//             return ResponseEntity.ok(response);
//         } catch (RuntimeException e) {
//             return handleException(e);
//         }
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<?> updateUser(
//             @RequestHeader("Authorization") String authHeader,
//             @PathVariable Long id,
//             @Valid @RequestBody UserReqDto userReqDto) {
        
//         try {
//             // Manual authorization - user can update their own data or admin can update any
//             UserResDto currentUser = getCurrentUser(authHeader);
//             if (!currentUser.getRole().equals("ADMIN") && !currentUser.getRole().equals("SUPER_ADMIN") && !id.equals(currentUser.getId())) {
//                 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
//             }
            
//             UserResDto updatedUser = userService.update(id, userReqDto);
//             return ResponseEntity.ok(updatedUser);
//         } catch (RuntimeException e) {
//             return handleException(e);
//         }
//     }

//     @DeleteMapping("/{id}")
//     @ResponseStatus(HttpStatus.NO_CONTENT)
//     public ResponseEntity<?> deleteUser(
//             @RequestHeader("Authorization") String authHeader,
//             @PathVariable Long id) {
        
//         try {
//             // Only super admins can delete, and cannot delete themselves
//             UserResDto currentUser = getCurrentUser(authHeader);
//             if (!currentUser.getRole().equals("SUPER_ADMIN") || id.equals(currentUser.getId())) {
//                 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
//             }
            
//             userService.delete(id);
//             return ResponseEntity.noContent().build();
//         } catch (RuntimeException e) {
//             return handleException(e);
//         }
//     }

//     @PatchMapping("/{id}/status")
//     public ResponseEntity<?> updateUserStatus(
//             @RequestHeader("Authorization") String authHeader,
//             @PathVariable Long id,
//             @RequestParam boolean status) {
        
//         try {
//             // Only admins can change status
//             if (!isAdmin(authHeader)) {
//                 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can change user status");
//             }
            
//             UserReqDto dto = new UserReqDto();
//             dto.setStatus(status);
//             UserResDto updatedUser = userService.update(id, dto);
//             return ResponseEntity.ok(updatedUser);
//         } catch (RuntimeException e) {
//             return handleException(e);
//         }
//     }

//     @PatchMapping("/{id}/role")
//     public ResponseEntity<?> updateUserRole(
//             @RequestHeader("Authorization") String authHeader,
//             @PathVariable Long id,
//             @RequestParam String role) {
        
//         try {
//             // Only super admins can change roles
//             if (!isSuperAdmin(authHeader)) {
//                 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only super admins can change user roles");
//             }
            
//             UserReqDto dto = new UserReqDto();
//             dto.setRole(role);
//             UserResDto updatedUser = userService.update(id, dto);
//             return ResponseEntity.ok(updatedUser);
//         } catch (RuntimeException e) {
//             return handleException(e);
//         }
//     }

//     // Helper methods for manual authorization
//     private boolean isAdmin(String authHeader) {
//         // Implement your logic to check if user is admin
//         // This would typically decode a token or check session
//         return false;
//     }

//     private boolean isSuperAdmin(String authHeader) {
//         // Implement your logic to check if user is super admin
//         return false;
//     }

//     private UserResDto getCurrentUser(String authHeader) {
//         // Implement your logic to get current user from auth header
//         // This would typically decode a token or check session
//         return null;
//     }

//     private ResponseEntity<?> handleException(RuntimeException e) {
//         if (e.getMessage().contains("not found")) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//         } else if (e.getMessage().contains("already exists") || e.getMessage().contains("already registered")) {
//             return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//         } else if (e.getMessage().contains("Incorrect password") || e.getMessage().contains("not active")) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//         } else {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//         }
//     }
// }