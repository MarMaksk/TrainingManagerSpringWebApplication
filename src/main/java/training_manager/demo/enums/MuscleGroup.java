package training_manager.demo.enums;

public enum MuscleGroup {
    BICEPS("БИЦЕПС"),
    TRICEPS("ТРИЦЕПС"),
    DELTOID("ДЕЛЬТА"),
    SHOULDERS("ПЛЕЧИ"),
    BACK("СПИНА"),
    CHEST("ГРУДЬ"),
    ABDOMINAL("ЖИВОТ"),
    LEG("НОГИ");

    private String name;

    MuscleGroup(String name) {
        this.name = name;
    }
}
