package training_manager.application.enums;

public enum MuscleGroupEnum {
    BICEPS("Бицепс"),
    TRICEPS("Трицепс"),
    DELTOID("Дельта"),
    SHOULDERS("Плечи"),
    BACK("Спина"),
    CHEST("Грудь"),
    ABDOMINAL("Пресс"),
    LEG("Ноги");

    private final String name;

    MuscleGroupEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
