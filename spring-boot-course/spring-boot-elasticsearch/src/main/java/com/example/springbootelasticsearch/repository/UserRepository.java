package com.example.springbootelasticsearch.repository;

import com.example.springbootelasticsearch.models.User;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, String> {

    @Query(
            "{\"bool\":" +
                    "{\"should\":" +
                        "[" +
                            "{\"match\":" +
                                "{\"name\":\"?0\"}" +
                            "}," +
                            "{\"match\":" +
                                "{\"surname\":\"?0\"}" +
                            "}," +
                            "{\"match\":" +
                                "{\"address\":\"?0\"}" +
                            "}" +
//                            "{\"range\":" +
//                                "{\"birthDate\":{" +
//                                    "\"gte\":\"?0\"," +
//                                    "\"lt\":\"now/M\"" +
//                                    "}" +
//                                "}" +
//                            "}" +
                        "]" +
                    "}" +
            "}"
    )
    List<User> findAllByParam(String search);


}
