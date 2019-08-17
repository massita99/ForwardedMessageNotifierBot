package com.massita.query.process.gamePath;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GamePathRepository extends MongoRepository<GamePath, String> {
}
