public class FormatConverterFactory {
    public static ImageConverter getConverter(String format) {
        switch (format.toLowerCase()) {
            case "jpeg":
                return new JPEGConverter();
            case "png":
                return new PNGConverter();
            case "bmp":
                return new BMPConverter();
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
