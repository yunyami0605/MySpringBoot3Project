package com.rookies4.myspringboot.controller;

import com.rookies4.myspringboot.entity.UserEntity;
import com.rookies4.myspringboot.exception.BusinessException;
import com.rookies4.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users1")
public class UserRestController {
    private final UserRepository userRepository;

    @PostMapping
    public UserEntity create(@RequestBody UserEntity user){
        return userRepository.save(user);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public UserEntity getUser(@PathVariable Long id){
        UserEntity existUser = getExistUser(id);

        return existUser;
    }

    @PatchMapping("/{email}/")
    public UserEntity updateUser(@PathVariable String email, @RequestBody UserEntity userDetail){
        // 서비스 로직과 같이 담당
        UserEntity existUser = userRepository.findByEmail(email) //Optional<UserEntity>
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));

        existUser.setName(userDetail.getName());

        UserEntity updateUser = userRepository.save(existUser);
        return updateUser;
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        UserEntity existUser = getExistUser(id);
        //DB에 삭제요청
        userRepository.delete(existUser);
        return ResponseEntity.ok("User가 삭제 되었습니다.");
    }

    private UserEntity getExistUser(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        //orElseThrow(Supplier) Supplier의 추상메서드 T get()
        UserEntity existUser = optionalUser
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        return existUser;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
}