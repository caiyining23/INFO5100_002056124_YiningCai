import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.util.Map;

public class BookParser {

    public static void main(String[] args) throws Exception {
        // Part 1: XML Parsing
        System.out.println("XML Parsing:");
        String sampleXml = """
                <BookShelf>
                    <Book>
                        <title>Java Programming</title>
                        <publishedYear>2020</publishedYear>
                        <numberOfPages>450</numberOfPages>
                        <authors>
                            <author>John Doe</author>
                            <author>Jane Smith</author>
                        </authors>
                    </Book>
                    <Book>
                        <title>Python Essentials</title>
                        <publishedYear>2019</publishedYear>
                        <numberOfPages>300</numberOfPages>
                        <authors>
                            <author>Alex Green</author>
                        </authors>
                    </Book>
                    <Book>
                        <title>Web Development</title>
                        <publishedYear>2021</publishedYear>
                        <numberOfPages>500</numberOfPages>
                        <authors>
                            <author>Emily White</author>
                            <author>Mark Brown</author>
                        </authors>
                    </Book>
                </BookShelf>
                """;

        // Parse and manipulate XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDoc = builder.parse(new ByteArrayInputStream(sampleXml.getBytes()));
        addBookToXml(xmlDoc);

        // Print updated XML
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(xmlDoc), new StreamResult(System.out));

        // Part 2: JSON Parsing
        System.out.println("\n\nJSON Parsing:");
        String sampleJson = """
                {
                    "BookShelf": {
                        "Books": [
                            {
                                "title": "Java Programming",
                                "publishedYear": 2020,
                                "numberOfPages": 450,
                                "authors": ["John Doe", "Jane Smith"]
                            },
                            {
                                "title": "Python Essentials",
                                "publishedYear": 2019,
                                "numberOfPages": 300,
                                "authors": ["Alex Green"]
                            },
                            {
                                "title": "Web Development",
                                "publishedYear": 2021,
                                "numberOfPages": 500,
                                "authors": ["Emily White", "Mark Brown"]
                            }
                        ]
                    }
                }
                """;

        // Parse and manipulate JSON
        JsonReader reader = Json.createReader(new StringReader(sampleJson));
        JsonObject jsonObject = reader.readObject();
        JsonObject updatedJson = addBookToJson(jsonObject);

        // Format and print updated JSON
        System.out.println("\n\nJSON Output:");
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> config = Map.of(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(config);
        JsonWriter jsonWriter = writerFactory.createWriter(stringWriter);
        jsonWriter.writeObject(updatedJson);
        jsonWriter.close();

        // Print formatted JSON
        System.out.println(stringWriter.toString());
    }

    private static void addBookToXml(Document doc) {
        Element bookShelf = (Element) doc.getElementsByTagName("BookShelf").item(0);

        Element newBook = doc.createElement("Book");

        Element title = doc.createElement("title");
        title.setTextContent("New Book");
        newBook.appendChild(title);

        Element year = doc.createElement("publishedYear");
        year.setTextContent("2023");
        newBook.appendChild(year);

        Element pages = doc.createElement("numberOfPages");
        pages.setTextContent("250");
        newBook.appendChild(pages);

        Element authors = doc.createElement("authors");
        Element author = doc.createElement("author");
        author.setTextContent("New Author");
        authors.appendChild(author);
        newBook.appendChild(authors);

        bookShelf.appendChild(newBook);
    }

    private static JsonObject addBookToJson(JsonObject jsonObject) {
        JsonArray books = jsonObject.getJsonObject("BookShelf").getJsonArray("Books");

        JsonObject newBook = Json.createObjectBuilder()
                .add("title", "New Book")
                .add("publishedYear", 2023)
                .add("numberOfPages", 250)
                .add("authors", Json.createArrayBuilder().add("New Author"))
                .build();

        JsonArrayBuilder updatedBooks = Json.createArrayBuilder();
        books.forEach(updatedBooks::add);
        updatedBooks.add(newBook);

        return Json.createObjectBuilder()
                .add("BookShelf", Json.createObjectBuilder().add("Books", updatedBooks))
                .build();
    }
}
