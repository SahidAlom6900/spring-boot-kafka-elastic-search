package com.tekion.springbootelasticsearch.service;

import com.tekion.springbootelasticsearch.dto.UserDTO;
import com.tekion.springbootelasticsearch.helper.CSVHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CSVServiceImpl implements CSVService{
    /**
     * @param multipartFile
     */
    @Override
    public List<UserDTO>  saveData(MultipartFile multipartFile) {
        try {
            List<UserDTO> userDTOS = CSVHelper.csvToTutorials(multipartFile.getInputStream());
            return userDTOS;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
