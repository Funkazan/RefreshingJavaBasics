package enums;


public enum Days {
    MONDAY("Montag", "Monday"),
    TUESTAY("Dienstag", "Tuesday"),
    WEDNESDAY("Mittwoch", "Wednesday"),
    THURSTAY("Donnerstag", "Thursday"),
    FRIDAY("Freitag", "Friday"),
    SATURDAY("Samstag", "Saturday"),
    SUNDAY("Sonntag", "Sunday");

    private final String deutscheBezeichnung;
    private final String englishName;

    Days(String deutscheBezeichnung, String englishName) {
        this.deutscheBezeichnung = deutscheBezeichnung;
        this.englishName = englishName;
    }

    public String getDeutscheBezeichnung() {
        return deutscheBezeichnung;
    }

    public String getEnglishName() {
        return englishName;
    }
}