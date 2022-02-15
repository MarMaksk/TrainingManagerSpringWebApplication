package training_manager.demo.enums;

public enum Role {
    ROLE_USER("Пользователь"),
    ROLE_HELPER("Помощник"),
    ROLE_ADMIN("Администратор");

    private String name;

    Role(String name) {
        this.name = name;
    }
}
