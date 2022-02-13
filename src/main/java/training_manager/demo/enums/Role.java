package training_manager.demo.enums;

public enum Role {
    USER("Пользователь"),
    HELPER("Помощник"),
    ADMIN("Администратор");

    private String name;

    Role(String name) {
        this.name = name;
    }
}
