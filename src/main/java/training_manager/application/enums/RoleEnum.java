package training_manager.application.enums;

public enum RoleEnum {
    USER("Пользователь"),
    HELPER("Помощник"),
    ADMIN("Администратор");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }
}
