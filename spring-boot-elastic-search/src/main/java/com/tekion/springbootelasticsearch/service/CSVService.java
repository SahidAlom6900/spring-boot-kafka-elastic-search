package com.tekion.springbootelasticsearch.service;

import com.tekion.springbootelasticsearch.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CSVService {
    public List<UserDTO> saveData(MultipartFile multipartFile);
}
