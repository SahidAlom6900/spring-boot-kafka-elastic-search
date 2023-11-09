package com.tekion.springbootelasticsearch.service;

import com.tekion.springbootelasticsearch.constants.KafkaConstants;
import com.tekion.springbootelasticsearch.dto.EmployeeDTO;
import com.tekion.springbootelasticsearch.entity.Employee;
import com.tekion.springbootelasticsearch.mongo.repository.EmployeeMongoRepository;
import com.tekion.springbootelasticsearch.repository.EmployeeElasticRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeElasticRepository employeeRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    /**
     * @param employeeDTO
     * @return
     */
    @Override
    public Employee create(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employeeRepository.save(employee);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Employee> findById(String id) {

        QueryBuilder queryBuilder =
                QueryBuilders
                        .matchQuery("id", id);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Employee> productHits =
                elasticsearchOperations
                        .search(searchQuery,
                                com.tekion.springbootelasticsearch.entity.Employee.class,
                                IndexCoordinates.of(KafkaConstants.INDEX_NAME));
        System.err.println("elasticsearchOperations +" + productHits.get().collect(Collectors.toList()));

        return employeeRepository.findById(id);
    }

    /**
     * @return
     */
    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    /**
     * @param id
     * @param employeeDTO
     * @return
     */
    @Override
    public Employee update(String id, EmployeeDTO employeeDTO) {
        return null;
    }

    String leftJson = "{\n" +
            "  \"name\": {\n" +
            "    \"first\": \"John\",\n" +
            "    \"last\": \"Doe\"\n" +
            "  },\n" +
            "  \"address\": null,\n" +
            "  \"birthday\": \"1980-01-01\",\n" +
            "  \"company\": \"Acme\",\n" +
            "  \"occupation\": \"Software engineer\",\n" +
            "  \"phones\": [\n" +
            "    {\n" +
            "      \"number\": \"000000000\",\n" +
            "      \"type\": \"home\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"number\": \"999999999\",\n" +
            "      \"type\": \"mobile\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    String rightJson = "{\n" +
            "            \"name\": {\n" +
            "        \"first\": \"Jane\",\n" +
            "                \"last\": \"Doe\",\n" +
            "                \"nickname\": \"Jenny\"\n" +
            "    },\n" +
            "            \"birthday\": \"1990-01-01\",\n" +
            "            \"occupation\": null,\n" +
            "            \"phones\": [\n" +
            "    {\n" +
            "        \"number\": \"111111111\",\n" +
            "            \"type\": \"mobile\"\n" +
            "    }\n" +
            "  ],\n" +
            "          \"favorite\": true,\n" +
            "          \"groups\": [\n" +
            "          \"close-friends\",\n" +
            "          \"gym\"\n" +
            "          ]\n" +
            "}";

    /**
     * @param id
     */
    @Override
    public void delete(String id) {

    }


    @Override
    public StringWriter jsonDiff() {
        JsonValue source = Json.createReader(new StringReader(leftJson)).readValue();
        JsonValue target = Json.createReader(new StringReader(rightJson)).readValue();
        return format(Json.createMergeDiff(source, target));
    }

    private StringWriter format(JsonMergePatch mergeDiff) {
        JsonWriterFactory writerFactory = Json.createWriterFactory(Map.of(JsonGenerator.PRETTY_PRINTING, true));

        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = writerFactory.createWriter(stringWriter)) {
            jsonWriter.write(mergeDiff.toJsonValue());
        }
        System.err.println("writerFactory "+writerFactory);
        return stringWriter;
    }
}
