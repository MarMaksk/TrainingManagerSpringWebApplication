package training_manager.demo.enums;

public enum RoleEnum {
    ROLE_USER("Пользователь"),
    ROLE_HELPER("Помощник"),
    ROLE_ADMIN("Администратор");

    private String name;

    RoleEnum(String name) {
        this.name = name;
    }
}
