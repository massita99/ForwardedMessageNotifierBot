package com.massita.query.process.player;

import com.massita.coreapi.game.Resource;
import com.massita.coreapi.game.ResourceBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@NoArgsConstructor
@Getter
public class Player {

    @Id
    private String id;

    @Setter
    private Map<Resource, Integer> resources = new ResourceBuilder().empty().build();

    public Player(String id) {
        this.id = id;
    }


}
