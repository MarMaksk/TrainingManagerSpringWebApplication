package training_manager.demo.enums;

public enum MuscleGroup {
    BICEPS("Бицепс>"),
    TRICEPS("Трицепс"),
    DELTOID("Дельта"),
    SHOULDERS("Плечи"),
    BACK("Спина"),
    CHEST("Грудь"),
    ABDOMINAL("Живот"),
    LEG("Ноги");

    private String name;

    MuscleGroup(String name) {
        this.name = name;
    }
}
