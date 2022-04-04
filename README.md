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
6. Разработана страница регистрации и авторизации
7. Настроена миграция таблиц и необходимых данных в эти таблицы с помощью flyway
8. Для шифроввания используется BCryptoEncoder
9. Добавлен фукционал администратора
10. Начата разработа сервиса для TelegramBotApi
11. 
Запланировано:
1) Добавить специфический функционал (советы, рекомендации и проч.)
2) Продолжить разработку и интегрирование отдельного приложения сервиса для TelegramBotApi
