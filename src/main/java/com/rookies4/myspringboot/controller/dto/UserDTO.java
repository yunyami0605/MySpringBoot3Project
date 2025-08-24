package com.rookies4.myspringboot.controller.dto;

import com.rookies4.myspringboot.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class UserDTO {

    //등록 입력
    @Getter
    @Setter
    public static class UserCreateRequest {
        @NotBlank(message = "Name 은 필수 입력항목입니다.")
        private String name;

        @NotBlank(message = "Email 은 필수 입력항목입니다.")
        private String email;

        public UserEntity toEntity() {
            UserEntity user = new UserEntity();
            user.setName(this.name);
            user.setEmail(this.email);
            return user;
        }
    }

    //수정 입력
    @Getter
    @Setter
    public static class UserUpdateRequest {
        @NotBlank(message = "Name 은 필수 입력항목입니다.")
        private String name;
    }

    //조회 출력
    @Getter
    public static class UserResponse {
        private Long id;
        private String name;
        private String email;
        private LocalDateTime createdAt;

        public UserResponse(UserEntity user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.createdAt = user.getCreatedAt();
        }

    }
}