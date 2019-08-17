package com.massita.coreapi.game;

import java.util.List;

public class MainScenario extends BaseScenario {

    public MainScenario() {

        List<StageEvent> stages =
                List.of(
                        BaseStageEvent.builder()
                                .name("START")
                                .eventDescription("Начать Проект Полный П")
                                .photo("START.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Да, конечно!")
                                                .nextStageName("1_1")
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Нет, я слабак")
                                                .nextStageName("START")
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("1_1")
                                .eventDescription("Выбери героя")
                                .photo("Heroes.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Лена")
                                                .nextStageName("2_1")
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Антон")
                                                .nextStageName("2_2")
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Миша")
                                                .nextStageName("2_3")
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Костя")
                                                .nextStageName("2_4")
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("2_1")
                                .eventDescription("Выбери кого-нибудь другого")
                                .photo("Lena.jpg")
                                .eventAction(FINAL_ACTION)
                                .build(),
                        BaseStageEvent.builder()
                                .name("2_2")
                                .eventDescription("Ты проиграл")
                                .eventAction(FINAL_ACTION)
                                .build(),
                        BaseStageEvent.builder()
                                .name("2_3")
                                .eventDescription("Ты проиграл")
                                .photo("Mike.jpg")
                                .eventAction(FINAL_ACTION)
                                .build(),
                        BaseStageEvent.builder()
                                .name("2_4")
                                .eventDescription("Ура, новый заказчик! Задача — внедрить «Единый клиент» в ХреньБреньБанке\uFF0E  Нужно уложиться в сроки, получить прибыль, сохранить психику команды, свои нервы и лояльность заказчика\uFF0E")
                                .photo("Kost.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Начать проект")
                                                .nextStageName("3")
                                                .eventPrice(new ResourceBuilder().full().build())
                                                .build()
                                )
                                .build()
                );


        setStages(stages);

    }

    private static EventAction FINAL_ACTION = BaseEventAction.builder()
            .nextStageName("1_1")
            .eventActionDescription("Начать сначала")
            .build();
}
