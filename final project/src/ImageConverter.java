import java.io.File;

public interface ImageConverter {
    File convert(File inputFile, String outputFileName);
}
