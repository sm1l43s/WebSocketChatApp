package by.tsvetkov.service;

import by.tsvetkov.model.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final MongoTemplate mongoTemplate;

    @Transactional
    public long getNextSequence(String sequenceName) {
        Counter counter = mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(sequenceName)),
                new Update().inc("sequence", 1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                Counter.class);
        return counter.getSequence();
    }
}