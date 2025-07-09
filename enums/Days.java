package enums;


public enum Days {
    MONDAY("Montag", "Monday", "Pazartesi"),
    TUESTAY("Dienstag", "Tuesday", "Salı"),
    WEDNESDAY("Mittwoch", "Wednesday", "Çarşamba"),
    THURSTAY("Donnerstag", "Thursday", "Perşembe"),
    FRIDAY("Freitag", "Friday", "Cuma"),
    SATURDAY("Samstag", "Saturday", "Cumartesi"),
    SUNDAY("Sonntag", "Sunday", "Pazar");

    private final String deutscheBezeichnung;
    private final String englishName;
    private final String turkishName;

    Days(String deutscheBezeichnung, String englishName, String turkishName) {
        this.deutscheBezeichnung = deutscheBezeichnung;
        this.englishName = englishName;
        this.turkishName = turkishName;
    }

    public String getDeutscheBezeichnung() {
        return deutscheBezeichnung;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getTurkishName() {
        return turkishName;
    }
}