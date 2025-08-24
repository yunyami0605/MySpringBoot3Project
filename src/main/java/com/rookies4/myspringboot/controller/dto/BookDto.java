package com.rookies4.myspringboot.controller.dto;

import com.rookies4.myspringboot.entity.Book;
import com.rookies4.myspringboot.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class BookDto {

    // 도서 생성 시 사용되는 DTO
    @Getter @Setter
    public static class BookCreateRequest{

        @NotBlank(message = "제목은 필수 입력사항입니다.")
        private String title;

        @NotBlank(message = "작가명은 필수 입력사항입니다.")
        private String author;

        @NotBlank(message = "책 isbn은 필수 입력사항입니다.")
        private String isbn;

        @NotNull(message = "가격은 필수 입력사항입니다.")
        private Integer price;

        @NotNull(message = "출판일은 필수 입력사항입니다.")
        private LocalDate publishDate;

        public Book toEntity() {
            Book _book = Book.builder()
                    .title(this.title)
                    .author(this.author)
                    .isbn(this.isbn)
                    .price(this.price)
                    .publishDate(this.publishDate)
                    .build();

            return _book;
        }
    }

    // 도서 정보 업데이트 시 사용되는 DTO
    @Getter @Setter
    public static class BookUpdateRequest{
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;
    }

    // 클라이언트에게 반환되는 도서 정보 DTO
    @Getter
    public static class BookResponse{
        private final Long id;
        private final String title;
        private final String author;
        private final String isbn;
        private final Integer price;
        private final LocalDate publishDate;

        public BookResponse(Book _book){
            this.id = _book.getId();
            this.title = _book.getTitle();
            this.author = _book.getAuthor();
            this.isbn = _book.getIsbn();
            this.price = _book.getPrice();
            this.publishDate = _book.getPublishDate();
        }
    }
}
