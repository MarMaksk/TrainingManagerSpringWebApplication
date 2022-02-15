package training_manager.demo.enums;

public enum MuscleGroupEnum {
    BICEPS("Бицепс>"),
    TRICEPS("Трицепс"),
    DELTOID("Дельта"),
    SHOULDERS("Плечи"),
    BACK("Спина"),
    CHEST("Грудь"),
    ABDOMINAL("Живот"),
    LEG("Ноги");

    private String name;

    MuscleGroupEnum(String name) {
        this.name = name;
    }
}
