package org.example;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
  private static final Path CSV_PATH = Path.of("/home/jack-scales/Downloads/x");

  public static void main(String[] args) {
    ArrayList<Map<String, String>> productIdsCSV = CsvUtil.readCsv(Path.of("/home/jack-scales/Downloads/product_ids.csv"));
    ArrayList<Map<String, String>> relatedProductsCSV = CsvUtil.readCsv(Path.of("/home/jack-scales/Downloads/related_products.csv"));

    HashMap<String, String> relatedProductsMap = new HashMap<>();
    for (Map<String, String> product : relatedProductsCSV) {
      if (!"Add Product".equals(product.get("Action"))) continue;

      relatedProductsMap.put(product.get("Name"), product.get("Related Products"));
    }

    HashMap<String, String> nameToIdMap = new HashMap<>();
    for (Map<String, String> product : productIdsCSV) {
      if (!"Product".equals(product.get("Action"))) continue;

      System.out.println(product.get("Name") + " : " + product.get("ID"));
      nameToIdMap.put(product.get("Name"), product.get("ID"));
    }

    for (Map<String, String> row : productIdsCSV) {
      if (!"Product".equals(row.get("Action"))) continue;

      String relatedProductIds = relatedProductsMap.get(row.get("Name"));
      if (relatedProductIds == null) continue;

      String[] relatedProducts = relatedProductIds.split("(?<! ),(?! )");

      System.out.println(row.get("Name") + " : " + Arrays.toString(relatedProducts));

      row.put("RelatedProducts",
              Arrays.stream(relatedProducts)
                      .filter(nameToIdMap::containsKey)
                      .map(nameToIdMap::get)
                      .collect(Collectors.joining(","))
      );
    }

    CsvUtil.writeCsv(CSV_PATH.getParent().resolve("OUTPUT.csv"), productIdsCSV);
  }
}