package com.br.unifacef.notification.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;	

@Configuration
@EnableMongoRepositories(
        basePackages = {"com.br.unifacef.notification.domains.repositories.mongo"}
)
public class MgDBConfig extends AbstractMongoClientConfiguration  {
	
	@Value("${spring.datasource.mg.host}")
    private String mongoHost;

    @Value("${spring.datasource.mg.port}")
    private String mongoPort;

    @Value("${spring.datasource.mg.database}")
    private String mongoDB;
    
    @Value("${spring.datasource.mg.username}")
    private String mongoUser;
    
    @Value("${spring.datasource.mg.password}")
    private String mongoPass;

	@Override
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://" + mongoHost + ":" + mongoPort + "/" + mongoDB + "");
	}

	@Override
	protected String getDatabaseName() {
		return mongoDB;
	}

 

}
