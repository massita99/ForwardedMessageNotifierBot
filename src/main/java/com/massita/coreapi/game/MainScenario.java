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
                                                .nextStageName("LOOSE_2")
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
                                .photo("AntonGameOver.PNG")
                                .eventDescription("Ты проиграл")
                                .eventAction(FINAL_ACTION)
                                .build(),
                        BaseStageEvent.builder()
                                .name("2_3")
                                .eventDescription("Я в Иране, в этот раз без меня")
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
                                        "\u23F0Резерв времени — 100 единиц\n" +
                                        "\uD83D\uDCB0Резерв бюджета (риск-буфер) — 100 единиц\n" +
                                        "\uD83D\uDE3BЛояльность заказчика — 100 единиц\n" +
                                        "\uD83E\uDD2FТвое душевное состояние (нервы) — 100 единиц\n" +
                                        "\uD83C\uDF08Здоровье команды (психика) — 100 единиц")
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
                                .eventDescription("«Костя, ты о#%&л?»")
                                .photo("elena.jpeg")
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
                                        "Проблема 1\uFF0E Нет доступа к системам-источникам\n" +
                                        "Что будешь делать?")
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
                                .eventDescription("Проблема 2\uFF0E Отсутствует документация и экспертиза по системе-источнику")
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
                                .eventDescription("Проблема 3\uFF0E Затягивается согласование модели")
                                .photo("Waiting.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Эскалировать проблему на высшее руководство")
                                                .nextStageName("end_1")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).minus(Resource.NERVES, 20).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Дождаться согласования в штатном порядке")
                                                .nextStageName("end_1")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 20).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Убеждать заказчиков принять решение")
                                                .nextStageName("end_1")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 5).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("end_1")
                                .eventDescription("Ура!!! Модель готова")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Начнем следующий этап")
                                                .nextStageName("8")
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("8")
                                .photo("More_Resources.jpg")
                                .eventDescription("Этап 2\uFF0EСделать готовый билд:\n" +
                                        "Проблема 1\uFF0EНужно сделать кастомный метод")
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
                                .eventDescription("Проблема 2\uFF0EПоступили новые вводные по модели от заказчика")
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
                                .eventDescription("Проблема 3\uFF0EВнедренец нанес добра, билд отличается от исходных требований к модели")
                                .photo("Fix_bug.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Собрать команду и принять решение о том, что мы будем переделывать")
                                                .nextStageName("end_2")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Заставить внедренца переделывать билд")
                                                .nextStageName("end_2")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).minus(Resource.NERVES, 15).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Согласовать с заказчиком новую модель и адаптировать требования")
                                                .nextStageName("end_2")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("end_2")
                                .eventDescription("Браво! Билд готов")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Продолжим")
                                                .nextStageName("11")
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("11")
                                .photo("Slow_pc.jpg")
                                .eventDescription("Этап 3\uFF0EРазвернуть билд, загрузить источники и настроить интеграции:\n" +
                                        "Проблема 1\uFF0EНа стенде херовое железо, не соответствует исходным требованиям")
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
                                .eventDescription("Проблема 2\uFF0EДанные загрузились криво")
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
                                .eventDescription("Проблема 3\uFF0EПотребители кастомного метода жалуются, что метод работает неправильно")
                                .photo("bad_method.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Настоять на отсутствии ошибки и отказаться от переделки")
                                                .nextStageName("end_3")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 20).minus(Resource.NERVES, 20).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Выявить истинную потребность и переделать")
                                                .nextStageName("end_3")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 10).minus(Resource.TEAM, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Согласиться разобраться с проблемой, но после внедрения")
                                                .nextStageName("end_3")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.MONEY, 15).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("end_3")
                                .eventDescription("Супер! Билд развернут")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Почти половина готова\uFF0EИдем дальше")
                                                .nextStageName("14")
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("14")
                                .photo("new_team.jpg")
                                .eventDescription("Этап 4\uFF0EПройти приемку проекта:\n" +
                                        "Проблема 1\uFF0EСменилась команда заказчика, приемку будут проводить новые люди")
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
                                .eventDescription("Проблема 2\uFF0EЗаказчик считает, что часть требований не выполнено")
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
                                                .eventActionDescription("Пойти на конфликт, настаивать, что требования выполнены, эскалировать")
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
                                .eventDescription("Проблема 3\uFF0EВ день приемки сломалась интеграция")
                                .photo("broke_integration.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Перенести приемку")
                                                .nextStageName("end_4")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 30).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Провести приемку с замечаниями")
                                                .nextStageName("end_4")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 15).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Сделать «потемкинские деревни» и сэмулировать нормальную работу интеграции")
                                                .nextStageName("end_4")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.NERVES, 10).minus(Resource.TEAM, 10).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("end_4")
                                .eventDescription("Вау! Приемка пройдена")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("И последний этап")
                                                .nextStageName("17")
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("17")
                                .photo("no_specs.jpg")
                                .eventDescription("Этап 5\uFF0EПеревести систему на поддержку:\n" +
                                        "Проблема 1\uFF0EУ заказчика нет квалифицированных ресурсов для администрирования приложения")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Поддерживаем своими силами, пока заказчик не найдет админов")
                                                .nextStageName("18")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.MONEY, 10).minus(Resource.TEAM, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Оставляем приложение бесхозным, настаиваем на выделении админов")
                                                .nextStageName("18")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 30).minus(Resource.NERVES, 10).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Обучаем неквалифицированные ресурсы со стороны заказчика")
                                                .nextStageName("18")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TEAM, 20).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("18")
                                .eventDescription("Проблема 2\uFF0EПоявились проблемы с производительностью приложения")
                                .photo("performance.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Забить, пусть админы заказчика разбираются")
                                                .nextStageName("19")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 30).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Разбираемся в причинах, анализируем, тюним настройки")
                                                .nextStageName("19")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 20).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Устанавливаем мощное железо")
                                                .nextStageName("19")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.MONEY, 20).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("19")
                                .eventDescription("Проблема 3\uFF0EКриворукие админы заказчика порублудили в базе данных")
                                .photo("good-job-admin.jpg")
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Не наша проблема, пусть сами разбираются")
                                                .nextStageName("WIN")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.LOYALITY, 50).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Оказываем теплую ламповую поддержку, реанимируем систему")
                                                .nextStageName("WIN")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 20).minus(Resource.NERVES, 20).build())
                                                .build()
                                )
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Обучам и повышаем квалификацию криворукого админа")
                                                .nextStageName("WIN")
                                                .eventPrice(new ResourceBuilder().empty().minus(Resource.TIME, 30).build())
                                                .build()
                                )
                                .build(),
                        BaseStageEvent.builder()
                                .name("WIN")
                                .eventDescription("Вы победили")
                                .photo("win.png")
                                .eventAction(FINAL_ACTION)
                                .eventAction(
                                        BaseEventAction.builder()
                                                .eventActionDescription("Выпить с командой")
                                                .nextStageName("DRINK")
                                                .build())
                                .build(),
                        BaseStageEvent.builder()
                                .name("LOOSE")
                                .eventDescription("Вы проиграли")
                                .photo("loose.jpg")
                                .eventAction(FINAL_ACTION)
                                .build(),
                        BaseStageEvent.builder()
                                .name("LOOSE_2")
                                .eventDescription("Вы проиграли")
                                .photo("joker.jpg")
                                .eventAction(FINAL_ACTION)
                                .build(),
                        BaseStageEvent.builder()
                                .name("DRINK")
                                .eventDescription("А Гинесс хоть там есть?")
                                .photo("guiness.jpg")
                                .eventAction(FINAL_ACTION)
                                .build()


                );


        setStages(stages);

    }

    public static EventAction FINAL_ACTION = BaseEventAction.builder()
            .nextStageName("1_1")
            .eventActionDescription("Начать сначала")
            .reset(true)
            .build();

    public static EventAction LOOSE_ACTION = BaseEventAction.builder()
            .nextStageName("LOOSE")
            .eventActionDescription("Начать сначала")
            .build();
}
