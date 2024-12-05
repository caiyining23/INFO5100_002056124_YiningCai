import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;

public class ImageHandler {
    public static String extractMetadata(File file) {
        StringBuilder metadataInfo = new StringBuilder();
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            metadata.getDirectories().forEach(directory ->
                    directory.getTags().forEach(tag ->
                            metadataInfo.append(tag.getTagName()).append(": ").append(tag.getDescription()).append("\n")
                    )
            );
        } catch (Exception e) {
            metadataInfo.append("Failed to extract metadata: ").append(e.getMessage());
        }
        return metadataInfo.toString();
    }
}
