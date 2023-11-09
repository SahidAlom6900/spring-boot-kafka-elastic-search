package com.tekion.springbootelasticsearch.helper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.tekion.springbootelasticsearch.dto.UserDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERS = {"Index", "User Id", "First Name", "Last Name", "Sex","Email", "Phone", "Date of birth", "Job Title"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) return false;
        return true;
    }

    public static List<UserDTO> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<UserDTO> userDTOS = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (CSVRecord csvRecord : csvRecords) {
                System.err.println(csvRecord.get(HEADERS[1]));
                UserDTO userDTO = UserDTO.builder()
                        .index(csvRecord.get(HEADERS[0]))
                        .userId(csvRecord.get(HEADERS[1]))
                        .firstName(csvRecord.get(HEADERS[2]))
                        .lastName(csvRecord.get(HEADERS[3]))
                        .sex(csvRecord.get(HEADERS[4]))
                        .email(csvRecord.get(HEADERS[5]))
                        .phone(csvRecord.get(HEADERS[6]))
                        .dob(LocalDate.parse(csvRecord.get(HEADERS[7]), formatter))
                        .jobTitle(csvRecord.get(HEADERS[8])).build();
                userDTOS.add(userDTO);
            }
            return userDTOS;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}