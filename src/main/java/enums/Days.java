package enums;


/**
 * The {@code Days} enum represents the days of the week, providing their names in German, English, and Turkish.
 * <p>
 * Each enum constant corresponds to a day of the week and stores its name in three different languages.
 * </p>
 *
 * <ul>
 *   <li>{@link #MONDAY} - Montag, Monday, Pazartesi</li>
 *   <li>{@link #TUESDAY} - Dienstag, Tuesday, Salı</li>
 *   <li>{@link #WEDNESDAY} - Mittwoch, Wednesday, Çarşamba</li>
 *   <li>{@link #THURSDAY} - Donnerstag, Thursday, Perşembe</li>
 *   <li>{@link #FRIDAY} - Freitag, Friday, Cuma</li>
 *   <li>{@link #SATURDAY} - Samstag, Saturday, Cumartesi</li>
 *   <li>{@link #SUNDAY} - Sonntag, Sunday, Pazar</li>
 * </ul>
 *
 * <p>
 * Provides methods to retrieve the name of the day in German, English, and Turkish.
 * </p>
 *
 * @author Volkan Akkan
 */
public enum Days {
    MONDAY("Montag", "Monday", "Pazartesi"),
    TUESDAY("Dienstag", "Tuesday", "Salı"),
    WEDNESDAY("Mittwoch", "Wednesday", "Çarşamba"),
    THURSDAY("Donnerstag", "Thursday", "Perşembe"),
    FRIDAY("Freitag", "Friday", "Cuma"),
    SATURDAY("Samstag", "Saturday", "Cumartesi"),
    SUNDAY("Sonntag", "Sunday", "Pazar");

    private final String germanName;
    private final String englishName;
    private final String turkishName;

    Days(String germanName, String englishName, String turkishName) {
        this.germanName = germanName;
        this.englishName = englishName;
        this.turkishName = turkishName;
    }

    public String getDeutscheBezeichnung() {
        return germanName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getTurkishName() {
        return turkishName;
    }
}