import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PNGConverter implements ImageConverter {
    @Override
    public File convert(File inputFile, String outputFileName) {
        try {
            BufferedImage image = ImageIO.read(inputFile);
            File outputFile = new File(removeExtension(outputFileName) + ".png");
            ImageIO.write(image, "png", outputFile);
            return outputFile;
        } catch (Exception e) {
            System.out.println("Error during PNG conversion: " + e.getMessage());
            return null;
        }
    }

    private String removeExtension(String fileName) {
        return fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
    }
}
