import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMgmtApp {

    public static void main(String[] args) {
        Product[] products = {
                new Product(3128874119L, "Banana",new Date(2023, 1, 24), 124,0.55),
                new Product(2927458265L, "Apple", new Date(2022, 12, 9), 18, 1.09),
                new Product(9189927460L, "Carrot", new Date(2023, 3, 31), 89,2.99)
        };

        printProducts(products);
    }

    public static void printProducts(Product[] products) {
        List<Product> sortedProducts = Arrays.stream(products)
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        printInJson(sortedProducts);
     printInXml(sortedProducts);
        printInCsv(sortedProducts);
    }

    private static void printInJson(List<Product> products) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(products);
            System.out.println("JSON Format:");
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void printInXml(List<Product> products) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(products);
            System.out.println("\nXML Format:");
            System.out.println(xml);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void printInCsv(List<Product> products) {
        System.out.println("Product Id,Name,Date Supplied,Quantity In Stock,Unit Price ($)");
        for (Product product : products) {
            System.out.printf("%d,%s,%s,%d,%.2f%n",
                    product.getProductId(),
                    product.getName(),
                    product.getDateSupplied(),
                    product.getQuantityInStock(),
                    product.getUnitPrice());
        }
    }
}
