package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "meetings")
public class Meeting {
    @Id
    private String id;

    @Field
    private String title;

    @Field
    private String meetingLink;

    @Field
    private String hostEmail;

    @Field
    private LocalDateTime startTime;

    @Field
    private LocalDateTime endTime;

    @DBRef
    private Set<User> users = new HashSet<>();
}
