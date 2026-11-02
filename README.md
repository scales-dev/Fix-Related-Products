# Related Products Matching
I have the names of related products as strings, EKM wants them as ID's, this checks across the 2 worksheets and matches.
In hindsight this can be done in LibreCalc or excel, but this is easier...
Example inputs are in [resources](src/main/resources)

## What it does
- Given a worksheet A with headers Name|Related eg. "Miyazaki Mango"|"Apple mango,Banana mango"
- Given worksheet B with headers Name|ID eg. "Miyazaki Mango"|"389", "Apple mango"|"350", "Banana mango"|"589"
- Creates a map of related products by name from worksheet A
- Creates a map of product ids by name from worksheet B
- Loops each product, gets the related products, splits them by ","
  - Loops each related product, maps them to product ID
  - Returns a string of related products in the format "389"|"350,589"