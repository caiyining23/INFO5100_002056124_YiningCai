import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Main layout
        VBox root = new VBox();
        root.setSpacing(10);

        // File chooser button
        Button uploadButton = new Button("Upload Images");
        Label infoLabel = new Label("No files selected.");
        ScrollPane scrollPane = new ScrollPane();
        VBox imageContainer = new VBox();
        imageContainer.setSpacing(10);
        scrollPane.setContent(imageContainer);

        // Format selector
        ComboBox<String> formatSelector = new ComboBox<>();
        formatSelector.getItems().addAll("JPEG", "PNG", "BMP");
        formatSelector.setValue("JPEG");

        // Convert button
        Button convertButton = new Button("Convert Selected Images");

        // File chooser configuration
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
        );

        // Store selected files
        List<File> selectedFiles = new ArrayList<>();

        // Upload button logic
        uploadButton.setOnAction(e -> {
            List<File> files = fileChooser.showOpenMultipleDialog(primaryStage);
            if (files != null && !files.isEmpty()) {
                selectedFiles.clear();
                selectedFiles.addAll(files); // Save selected files
                infoLabel.setText("Selected " + files.size() + " files.");

                // Clear previous images and display the new ones
                imageContainer.getChildren().clear();
                for (File file : files) {
                    HBox fileEntry = new HBox();
                    fileEntry.setSpacing(10);

                    // Display thumbnail
                    Image image = new Image(file.toURI().toString(), 100, 100, true, true);
                    ImageView thumbnailView = new ImageView(image);

                    // Display metadata
                    String metadata = ImageHandler.extractMetadata(file);
                    Label metadataLabel = new Label(metadata);
                    metadataLabel.setWrapText(true);

                    fileEntry.getChildren().addAll(thumbnailView, metadataLabel);
                    imageContainer.getChildren().add(fileEntry);
                }
            }
        });

        // Convert button logic
        convertButton.setOnAction(e -> {
            if (selectedFiles.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "No images selected for conversion.");
                alert.showAndWait();
                return;
            }

            String selectedFormat = formatSelector.getValue().toLowerCase();
            for (File file : selectedFiles) {
                try {
                    ImageConverter converter = FormatConverterFactory.getConverter(selectedFormat);
                    String outputFileName = file.getParent() + "/converted_" + file.getName();
                    File convertedFile = converter.convert(file, outputFileName);
                    if (convertedFile != null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                                "Conversion successful: " + convertedFile.getPath());
                        alert.showAndWait();
                    }
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR,
                            "Failed to convert file: " + file.getName() + "\nError: " + ex.getMessage());
                    alert.showAndWait();
                }
            }
        });

        // Add components to the layout
        root.getChildren().addAll(uploadButton, infoLabel, scrollPane, formatSelector, convertButton);

        // Set up the scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Image Management Tool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
