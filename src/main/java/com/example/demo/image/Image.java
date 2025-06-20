package com.example.demo.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.demo.user.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "images")
public class Image {
    @Id
    private String id;

    @Field(name = "filename")
    private String filename;

    @Field(name = "filetype")
    private String filetype;

    @Field(name = "filePath")
    private String filePath;

    @DBRef
    private User user;
}
