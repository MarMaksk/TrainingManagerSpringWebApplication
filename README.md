# TrainingManagerSpringWebApplication

Java Spring Веб-приложение. Сервис контроля личных тренировок
Использованы технологии: Java, Spring (JPA, Validation, Web MVC), postgresql, flyway, modelmapper
Реализовано:
1. Основные сущности представлены в postgresql с помощью Hibernate (Spring Data)
2. Для каждой сущности реализованы:
- Репозитории (Spring JPA)
- DTO (также маппинг из entity to DTO and DTO to entity)
- Сервисы
- REST Контроллеры (частично, для тех сущностей которым они необходимые)
- Валидация
3. Exception (А также минимальный функционал Exception Advice)
4. Сконфигурирован Spring Security (авторизация, аутентификация, для определения какой пользователь делает запрос используется Principal)
5. В этом же проекте реализовано html представление функционала и базовый функционал написанный полностью на чистом JavaScript

Запланировано:
1) Разработать страницу регистрации и авторизации
2) Перейти к использованию BCryptoEncoder
3) Создать функционал администратора
4) Добавить специфический функционал (советы, рекомендации и проч.)
5) Дополнить обработку исключений
6) Реализовать логгирование
7) Разработать и интегрировать отдельное приложение сервиса для TelegramBotApi
