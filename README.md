# Java Test
Your task, should you choose to accept it, is to refactor this application.

### Prerequisites
- JDK 8 and above
- Maven 3.6 and above

### Setup
In addition to cloning this repository, also clone, build and run https://github.com/talma/ebay-test-server to have a catalog-to-categories mapping service runnung locally.

### Background
This application generates a report based on a catalog-to-categories mapping service.  
It iterates over a fixed number of catalogs for specific site IDs and (1) prints out which categories are mapped for each catalog.  (2) Print the total number of unique categories. 

**Service Call Example:**<br>
http://localhost:8080/catalogmapping/getcatalogmapping/json/{catalogId}/{siteId}<br>
http://localhost:8080/catalogmapping/getcatalogmapping/json/1/0

**Report Example**<br>
*Site 0 - Catalog 4 is mapped to category "England" (121491)*<br>
*Site 0 - Catalog 5 is mapped to category "Bike" (121492)*<br>
*Total Unique categories: 2*


### Current issues
- Bad design / architecture
- Bad performance 
- Nothing is configurable
- No tests

### Requirements
- Refactor the application so all the current issues are resolved
- Sort the report by category ID (ascending) per site, per catalog
- Print total number of categories
- The application needs to serve the current clients - so make sure nothing is broken
- You can assume the list of sites is predefined

### Highlights
- The application should be well designed and written
- The application should run as efficiently as possible, in terms of execution time
- The report should be sorted by category ID (ascending) per site, per catalog
- The application should be fully covered with tests
- Any framework or external library can be used

Good luck!
