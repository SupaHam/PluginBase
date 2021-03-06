package pluginbase.util.webpaste;

/**
 * URL-Shortener.
 */
public interface URLShortener {
    /**
     * Shorten an URL.
     * @param longUrl The long form.
     * @return The shortened URL.
     */
    String shorten(String longUrl);
}
