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
                                .eventDescription("Ура, новый заказчик! Задача — внедрить «Единый клиент» в ХреньБреньБанке\uFF0E Нужно уложиться в сроки, получить прибыль, сохранить психику команды, свои нервы и лояльность заказчика\uFF0E")
                                .photo("Kost.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Посмотреть ресурсы\uFF0E")
                                                .nextStageName("3")
                                                .eventPrice(new ResourceBuilder().full().build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("3")
                                .eventDescription("Доступные ресурсы:\n" +
                                        "Резерв времени — 100 единиц\n" +
                                        "Резерв бюджета (риск-буфер) — 100 единиц\n" +
                                        "Лояльность заказчика — 100 единиц\n" +
                                        "Твое душевное состояние (нервы) — 100 единиц\n" +
                                        "Здоровье команды (психика) — 100 единиц")
                                .photo("Resources.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Начать проект Полный П")
                                                .nextStageName("5")
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Отменить проект")
                                                .nextStageName("4_1")
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("4_1")
                                .eventDescription("«Костя, ты ох%&л!!!»")
                                .photo("Bad_choice.jpg")
                                .eventAction(FINAL_ACTION)
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Продолжить проект")
                                                .nextStageName("5")
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("5")
                                .eventDescription("Этап 1\uFF0E Сделать модель данных:\n" +
                                        "Нет доступа к системам-источникам")
                                .photo("No_access.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Эскалировать проблему на бизнес-заказчика или высшее руководство")
                                                .nextStageName("6")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Согласовывать доступы по регламенту")
                                                .nextStageName("6")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 30).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Выпить с РП заказчика, наладить дружеские отношения и договориться о доступах в нештатном порядке")
                                                .nextStageName("6")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.MONEY, 10).minus(Resource.NERVES, 5).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("6")
                                .eventDescription("Отсутствует документация и экспертиза по системе-источнику")
                                .photo("No_expert.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Заняться реверс-инжинирингом силами команды")
                                                .nextStageName("7")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 20).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Ждать эксперта и документацию от заказчика")
                                                .nextStageName("7")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 20).minus(Resource.LOYALITY, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Нанять эксперта на своей стороне")
                                                .nextStageName("7")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.MONEY, 20).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("7")
                                .eventDescription("Затягивается согласование модели")
                                .photo("Waiting.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Эскалировать проблему на бизнес-заказчика или высшее руководство")
                                                .nextStageName("8")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).minus(Resource.NERVES, 20).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Дождаться согласования в штатном порядке")
                                                .nextStageName("8")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 20).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Убеждать заказчиков принять решение")
                                                .nextStageName("8")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 5).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("8")
                                .photo("More_Resources.jpg")
                                .eventDescription("Этап 2\uFF0E Сделать готовый билд:\n" +
                                        "Нужно сделать кастомный метод")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Запросить дополнительные ресурсы в команду у продакта Миши")
                                                .nextStageName("9")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.MONEY, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Заставить работать сврехурочно существующую команду")
                                                .nextStageName("9")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Отказаться от кастомного метода на этапе внедрения, перенести разработку на потом")
                                                .nextStageName("9")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("9")
                                .eventDescription("Поступили новые вводные по модели от заказчика")
                                .photo("New_data.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Пересмотреть модель и переделать")
                                                .nextStageName("10")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 20).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Послать заказчика и тыкать в согласованную модель")
                                                .nextStageName("10")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).minus(Resource.LOYALITY, 30).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Разбираться в целесообразности пересмотра модели, организовать дополнительные встречи и конфколлы")
                                                .nextStageName("10")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.NERVES, 5).minus(Resource.TEAM, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("10")
                                .eventDescription("Внедренец нанес добра, билд отличается от исходных требований к модели")
                                .photo("Fix_bug.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Собрать команду и принять решение о том, что мы будем переделывать")
                                                .nextStageName("11")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Заставить внедренца переделывать билд")
                                                .nextStageName("11")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).minus(Resource.NERVES, 15).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Согласовать с заказчиком новую модель и адаптировать требования")
                                                .nextStageName("11")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("8")
                                .photo("More_Resources.jpg")
                                .eventDescription("Этап 2\uFF0E Сделать готовый билд:\n" +
                                        "Нужно сделать кастомный метод")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Запросить дополнительные ресурсы в команду у продакта Миши")
                                                .nextStageName("9")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.MONEY, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Заставить работать сврехурочно существующую команду")
                                                .nextStageName("9")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Отказаться от кастомного метода на этапе внедрения, перенести разработку на потом")
                                                .nextStageName("9")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("9")
                                .eventDescription("Поступили новые вводные по модели от заказчика")
                                .photo("New_data.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Пересмотреть модель и переделать")
                                                .nextStageName("10")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 20).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Послать заказчика и тыкать в согласованную модель")
                                                .nextStageName("10")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).minus(Resource.LOYALITY, 30).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Разбираться в целесообразности пересмотра модели, организовать дополнительные встречи и конфколлы")
                                                .nextStageName("10")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.NERVES, 5).minus(Resource.TEAM, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("10")
                                .eventDescription("Внедренец нанес добра, билд отличается от исходных требований к модели")
                                .photo("Fix_bug.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Собрать команду и принять решение о том, что мы будем переделывать")
                                                .nextStageName("11")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Заставить внедренца переделывать билд")
                                                .nextStageName("11")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).minus(Resource.NERVES, 15).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Согласовать с заказчиком новую модель и адаптировать требования")
                                                .nextStageName("11")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("11")
                                .photo("Slow_pc.jpg")
                                .eventDescription("Этап 3\uFF0E Развернуть билд, загрузить источники и настроить интеграции:\n" +
                                        "На стенде херовое железо, не соответствует исходным требованиям")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Попробовать адаптировать билд под реалии железа")
                                                .nextStageName("12")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Остановить работы и ждать нормальное железо")
                                                .nextStageName("12")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.NERVES, 15).minus(Resource.LOYALITY, 20).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Временно установить свое железо")
                                                .nextStageName("12")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.MONEY, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("12")
                                .eventDescription("Данные загрузились криво")
                                .photo("etl.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Забить и проигнорировать")
                                                .nextStageName("13")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 30).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Разобраться в причинах ошибки и перезагрузить данные")
                                                .nextStageName("13")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Убедить заказчика, что проблема не на нашей стороне")
                                                .nextStageName("13")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.NERVES, 20).minus(Resource.LOYALITY, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("13")
                                .eventDescription("Потребители кастомного метода жалуются, что метод работает неправильно")
                                .photo("bad_method.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Настоять на отсутствии ошибки и отказаться от переделки")
                                                .nextStageName("14")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).minus(Resource.NERVES, 20).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Выявить истинную потребность и переделать")
                                                .nextStageName("14")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).minus(Resource.TEAM, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Согласиться разобраться с проблемой, но после внедрения")
                                                .nextStageName("14")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.MONEY, 15).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("14")
                                .photo("new_team.jpg")
                                .eventDescription("Этап 4\uFF0E Пройти приемку проекта:\n" +
                                        "Сменилась команда заказчика, приемку будут проводить новые люди")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Наладить отношения с новой командой, отложить приемку")
                                                .nextStageName("15")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 20).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Сдавать через голову новой команды, эскалировать на бизнес-заказчика")
                                                .nextStageName("15")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Познакомиться с новой командой в неформальной обстановке, провести приемку вовремя")
                                                .nextStageName("15")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.MONEY, 15).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("15")
                                .eventDescription("Заказчик считает, что часть требований не выполнено")
                                .photo("ungry_customer.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Исправить после в счет бюджета проекта")
                                                .nextStageName("16")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 15).minus(Resource.MONEY, 15).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Исправить после в счет бюджета проекта")
                                                .nextStageName("16")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Потребовать от команды срочного исправления ")
                                                .nextStageName("16")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TEAM, 30).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("16")
                                .eventDescription("В день приемки сломалась интеграция")
                                .photo("broke_integration.jpg.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Перенести приемку")
                                                .nextStageName("17")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 30).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Провести приемку с замечаниями")
                                                .nextStageName("17")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 15).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Сделать «потемкинские деревни» и сэмулировать нормальную работу интеграции")
                                                .nextStageName("17")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.NERVES, 10).minus(Resource.TEAM, 10).build())
                                                .build()
                                )
                                .build()


                );


        setStages(stages);

    }

    public static EventAction FINAL_ACTION = BaseEventAction.builder()
            .nextStageName("1_1")
            .eventActionDescription("Начать сначала")
            .reset(true)
            .build();
}
