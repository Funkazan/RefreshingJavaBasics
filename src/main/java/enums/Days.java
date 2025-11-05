package enums;


/**
 * The {@code Days} enum represents the days of the week, providing their names in German, English, and Turkish.
 * 
 * Each enum constant corresponds to a day of the week and stores its name in three different languages.
 * 
 *
 * 
 *   {@link #MONDAY} - Montag, Monday, Pazartesi
 *   {@link #TUESDAY} - Dienstag, Tuesday, Salı
 *   {@link #WEDNESDAY} - Mittwoch, Wednesday, Çarşamba
 *   {@link #THURSDAY} - Donnerstag, Thursday, Perşembe
 *   {@link #FRIDAY} - Freitag, Friday, Cuma
 *   {@link #SATURDAY} - Samstag, Saturday, Cumartesi
 *   {@link #SUNDAY} - Sonntag, Sunday, Pazar
 * 
 *
 * 
 * Provides methods to retrieve the name of the day in German, English, and Turkish.
 * 
 *
 * @author Volkan Akkan
 */
public enum Days {
    MONDAY("Montag", "Monday", "Pazartesi", false),
    TUESDAY("Dienstag", "Tuesday", "Salı", false),
    WEDNESDAY("Mittwoch", "Wednesday", "Çarşamba", false),
    THURSDAY("Donnerstag", "Thursday", "Perşembe", false),
    FRIDAY("Freitag", "Friday", "Cuma", false),
    SATURDAY("Samstag", "Saturday", "Cumartesi", true),
    SUNDAY("Sonntag", "Sunday", "Pazar", true);

    private final String germanName;
    private final String englishName;
    private final String turkishName;
    private final boolean isWeekend;

    /**
     * Constructor for the Days enum. Initializes the names in different languages and weekend status.
     * Available languages: German, English, Turkish.
     * Weekend days are Saturday and Sunday.
     * @param germanName
     * @param englishName
     * @param turkishName
     * @param isWeekend
     */
    Days(String germanName, String englishName, String turkishName, boolean isWeekend) {
        this.germanName = germanName;
        this.englishName = englishName;
        this.turkishName = turkishName;
        this.isWeekend = isWeekend;
    }

    public String getGermanName() {
        return germanName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getTurkishName() {
        return turkishName;
    }

    public boolean isWeekend() {
        return this.isWeekend;
    }

    public boolean isWeekday() {
        return !this.isWeekend;
    }
}