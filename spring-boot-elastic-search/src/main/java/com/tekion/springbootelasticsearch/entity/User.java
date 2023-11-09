package com.tekion.springbootelasticsearch.entity;


import com.tekion.springbootelasticsearch.constants.KafkaConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = KafkaConstants.INDEX_NAME)
public class User {
    private String index;
    private String userId;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
    private String phone;
    private LocalDate dob;
    private String jobTitle;
}