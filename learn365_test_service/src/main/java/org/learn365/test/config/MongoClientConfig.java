package org.learn365.test.config;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile("cloud")
@EnableMongoRepositories(basePackages = "org.learn365.test.repository")
public class MongoClientConfig extends AbstractMongoClientConfiguration {

    @Value("${datasource.mongo.database}")
    private String database;
    @Value("${datasource.mongo.host}")
    private String host;
    @Value("${datasource.mongo.port}")
    private String port;
    @Value("${datasource.mongo.username}")
    private String username;
    @Value("${datasource.mongo.password}")
    private String password;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    @ConditionalOnExpression("'${mongo.transactions}'=='enabled'")
    @Qualifier("mongoTransactionManager")
    MongoTransactionManager transactionManager(MongoDatabaseFactory factory) {
        return new MongoTransactionManager(factory);
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        String connString = String.format("mongodb://%s:%s", host, port);
        MongoClientSettings settings = MongoClientSettings.builder()
                .writeConcern(WriteConcern.MAJORITY)
                .retryWrites(true)
                .credential(MongoCredential
                        .createCredential(username, database, password.toCharArray()))
                .applyConnectionString(new ConnectionString(connString))
                .build();
        return MongoClients.create(settings);
    }
}
