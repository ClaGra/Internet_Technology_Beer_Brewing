# Internet_Technology_Beer_Brewing

[![License](https://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)


#### Contents:
- [Analysis](#analysis)
  - [Scenario](#scenario)
  - [User Stories](#user-stories)
  - [Use Case](#use-case)
- [Design](#design)
  - [Prototype Design](#prototype-design)
  - [Domain Design](#domain-design)
  - [Business Logic](#business-logic)
- [Implementation](#implementation)
  - [Backend Technology](#backend-technology)
  - [Frontend Technology](#frontend-technology)
- [Project Management](#project-management)
  - [Roles](#roles)
  - [Milestones](#milestones)

## Analysis 



### Scenario

The Brauverein Full is a brewing association based in Full. It was founded in June 2018 by five people who aimed to produce beer for themselves, friends, and families. The reason for the foundation of an association was that the beer of a private individual is legally not allowed to leave the property where it is brewed, which complicated the sharing with other people. Through the small size of the village, which has less than 1000 inhabitants, the word of a local brewery spread, and the association gained in popularity and customers.
The Brauverein Full has been managing its stock and sales using a self-made Excel sheet that has become increasingly time-consuming, error-prone, and difficult to manage as the association has grown in size and complexity. The association has decided to switch to a webbased application to streamline and automate its inventory management and sales tracking. The new application will enable the association to track its inventory levels in real-time and provide better visibility into its sales trends and stock turnover rates.
This project aims to improve the efficiency and accuracy of the Brauverein Full’s inventory management and sales tracking, reducing administrative overhead and enabling the association to make data-driven decisions to optimize their operations and meet the needs of their members and customers more effectively. 

The Excel sheet used for the stock and sales management was created by the head of administration of the Brauverein Full. Its purpose is to give the association members a better overview of the inventory levels and decrease the effort at the end of the year to create the report for the customs administration overseeing the taxation of sold beer.
Since the Excel sheet includes many complex formulas, the remainder of the association will not use the tool out of fear of deleting or breaking something. As a solution to this problem, the head of administration currently manages to fill the Excel sheet herself. The association members transmit the information about withdrawals from the inventory via a specific WhatsApp chat. They are then transferred into the Excel sheet by the head of administration, which leads to a double track and a high administrative effort for multiple members. Furthermore, since only one out of five people opened the Excel sheet, it lost its purpose.
The Excel sheet does not give any insights about specific customers or their orders since the administrative workload is tried to be held as small as possible for the head of administration. With the implementation of a web-based application, this information could be easily added and would provide better visibility into the sales trends.

### User Stories
> Note: Admins have the same access and permissions as regular Users, with the additional ability to delete data.
 
#### User Stories General
1. As a User, I want to use the web app on both mobile devices and desktop computers so that I can access it from anywhere.
2. As a User, I want a consistent visual design so that I can navigate the app easily.
3. As a User, I want to use list views to explore and read my brewery data.
4. As a User, I want to use edit and create views to maintain my brewery data.
5. As a User, I want to log in so that I can securely access the application.
6. As a User, I want to log out so that I can securely end my session.
7. As an Admin, I want to authenticate myself so that I can access the delete functionality, which is restricted to Admins.
 
#### User Stories Order
1. As a User, I want to add new orders so that I can record customer purchases.
2. As a User, I want to edit existing orders so that I can update order details if needed.
3. As an Admin, I want to delete orders so that I can remove cancelled or invalid transactions.
4. As a User, I want to view all orders so that I can track sales activity.
5. As a User, I want to view orders by ID so that I can check specific transactions.
 
#### User Stories Inventory
1. As a User, I want the system to automatically update inventory when bottling and orders are added, modified, or deleted so that stock levels remain accurate in real time.
2. As a User, I want to edit inventory items so that I can correct stock data.
3. As an Admin, I want to delete inventory items so that I can remove expired.
4. As a User, I want to view the full inventory so that I can monitor current stock levels.
5. As a User, I want to view an inventory summary so that I can get an overview by category.
6. As a User, I want to view inventory by ID so that I can check specific stock items.
 
#### User Stories Brewing
1. As a User, I want to add new brewing protocols so that brewing processes are documented.
2. As a User, I want to edit brewing protocols so that I can update brewing information.
3. As an Admin, I want to delete brewing protocols so that I can remove incorrect records.
4. As a User, I want to view brewing protocols so that I can monitor the production process.
5. As a User, I want to view brewing protocols by ID so that I can reference specific batches.
 
#### User Stories Bottling 
1. As a User, I want to add bottling records so that packaging is documented.
2. As a User, I want to edit bottling records so that I can update packaging information.
3. As an Admin, I want to delete bottling records so that I can remove invalid entries.
4. As a User, I want to view bottling records so that I can see packaging status.
5. As a User, I want to view bottling records by ID so that I can look up specific batches.
 
#### User Stories Customer 
1. As a User, I want to add new customers so that I can manage client information.
2. As a User, I want to edit customer data so that I can keep records accurate.
3. As an Admin, I want to delete customers so that I can remove incorrect entries.
4. As a User, I want to view all customers so that I can see our client base.
5. As a User, I want to view customers by ID so that I can look up specific information.
 
#### User Stories Recipe 
1. As a User, I want to add new recipes so that I can document different beer types and categories.
2. As a User, I want to edit recipes so that I can update information as needed.
3. As an Admin, I want to delete recipes so that I can remove invalid entries.
4. As a User, I want to view all recipes so that I can get an overview of available recipes.
5. As a User, I want to view recipes by ID so that I can access specific detailed information.
 
#### User Stories Alert 
1. As a User, I want the system to automatically create alerts when inventory is low so that I am notified in time.
2. As a User, I want to delete alerts so that I can clear notifications of categories no longer used.
3. As a User, I want the system to automatically remove alerts when inventory is replenished so that only current warnings remain.



### Use Case
 
> Note: In the following use case descriptions, the term user includes both regular users and admins.
 
#### Use Case 100 Order
![image](https://github.com/user-attachments/assets/44315eac-8888-4653-acae-cb5091f5952b)
 
- UC-101 [Add Order]: The system shall allow the user to add new orders through a form. The order data will be stored and reflected in the order list view.
- UC-102 [Edit Order]: The system shall allow the user to update existing orders. The user selects an order, modifies fields such as customer, order date, beer name and update the changes.
- UC-103 [Delete Order]: The system shall allow the admin to delete an order. This action permanently removes the record from the database after confirmation.
- UC-104 [View Orders]: The system shall allow the user to view a list of all orders. Each record includes an order ID, customer, and order date.
- UC-105 [View Orders by ID]: The system shall allow the user to view an order sorted by ID. The retrieved record includes the order ID, customer, and order date.
 
#### Use Case 200 Inventory
![image](https://github.com/user-attachments/assets/d7fbe49f-c05f-4ee8-8ac3-f3807c7155e3)
 
- UC-201 [Edit Inventory]: The system shall allow the user to edit existing inventory items. Additionally, the system shall automatically update inventory when bottling and orders are added, modified, or deleted. 
- UC-202 [Delete Inventory]: The system shall allow the admin to delete inventory items. This action permanently removes the item from the inventory after confirmation.
- UC-203 [View Inventory]: The system shall allow the user to view the inventory. The list displays inventory ID, anount, category name and expiration date.
- UC-204 [View Inventory by ID]: The system shall allow the user to view the inventory sorted by ID. The list includes fields such as amount and expiration date.
- UC-205 [View Inventory Summary]: The system shall allow the user to view the inventory summary. The summary displays beer names and amount.
 
#### Use Case 300 Brewing
![image](https://github.com/user-attachments/assets/6127b51d-4d0c-43bd-b8c3-e0c0c40267f2)
 
- UC-301 [Add Brewing Protocol]: The system shall allow the user to add a new brewing protocol. The user provides details such as recipe name, brewing date, and original gravity.
- UC-302 [Edit Brewing Protocol]: The system shall allow the user to edit an existing brewing protocol. The user can modify fields such as recipe name, brewing date, and original gravity.
- UC-303 [Delete Brewing Protocol]: The system shall allow the admin to delete a brewing protocol. This action permanently removes the brewing protocol from the system after confirmation.
- UC-304 [View Brewing Protocol]: The system shall allow the user to view brewing protocols. The list displays batch number, brewing date, and recipe name.
- UC-305 [View Brewing Protocol by ID]: The system shall allow the user to view brewing protocols sorted by ID. The list includes fields such as recipe name, brewing date, and original gravity. 
 
#### Use Case 400 Bottling
![image](https://github.com/user-attachments/assets/a712c184-6219-4a0b-9f11-ebb8302a0eb8)
 
- UC-401 [Add Bottling]: The system shall allow the user to add a new bottling record. The user provides details such as batch number, bottling date, amount.
- UC-402 [Edit Bottling]: The system shall allow thee user to edit an existing bottling record. The user can modify fields such as such as batch number, bottling date, amount.
- UC-403 [Delete Bottling]: The system shall allow the admin to delete a bottling record. This action permanently removes the record from the system after confirmation.
- UC-404 [View Bottling]: The system shall allow the user to view bottling record. The list displays fields such as brewing date, recipe name, and bottling date.
- UC-405 [View Bottling by ID]: The system shall allow the user to view bottling record sorted by ID. The list displays bottling dates, expiration date, and amount.
 
#### Use Case 500 Customer
![image](https://github.com/user-attachments/assets/83313a6c-a445-4b0c-ad12-8b92f0817cc6)
 
- UC-501 [Add Customer]: The system shall allow the user to add a new customer. The user provides details such as customer name, city, and date of birth.
- UC-502 [Edit Customer]: The system shall allow the user to edit an existing customer. The user can modify fields such as customer name, city, and date of birth.
- UC-503 [Delete Customer]: The system shall allow the admin to delete a customer. This action permanently removes the customer from the system after confirmation.
- UC-504 [View Customer]: The system shall allow the user to view customer information. The list displays customer name, city, and date of birth.
- UC-505 [View Customer by ID]: The system shall allow the user to view customer information sorted by ID. The list displays details such as customer name, city, and date of birth.
 
#### Use Case 600 Recipe
![image](https://github.com/user-attachments/assets/16f720fc-000a-4155-b753-057da7da24d2)
 
- UC-601 [Add Recipe]: The system shall allow the user to add a new recipe category. The user provides details such as name and category.
- UC-602 [Edit Recipe]: The system shall allow the user to edit an existing recipe category. The user can modify fields such as  name and category.
- UC-603 [Delete Recipe]: The system shall allow the admin to delete a recipe category. This action permanently removes the category from the system after confirmation.
- UC-604 [View Recipe]: The system shall allow the user to view recipe category. The list displays recipe ID, recipe name and and recipe category.
- UC-605 [View Recipe by ID]: The system shall allow the user to view recipe category sorted by ID. The list displays name and category.
#### Use Case 700 Alert
![image](https://github.com/user-attachments/assets/47d7bc24-12e3-4104-ac45-6988f391c187)
 
 
- UC-701 [Add Alert]: The system shall automatically add an alert when the inventory per category is below 72. The alert includes details such as the affected category and current inventory level.
- UC-702 [View Alert]: The system shall allow the user to view all current alerts with related inventory details. The list displays the category and the current quantity.
- UC-703 [Delete Alert]: The system shall allow the admin to delete an existing alert. Additionally, the system shall automatically delete an existing alert when the inventory per category rises above 72 units.


## Design

### Color Scheme:
The design uses a warm, inviting palette that reflects the local and traditional values of Brauverein Full:

- Primary: Mustard Yellow #E1C547 – used in the header, buttons, and table headers
- Accent: Amber and Brown tones – seen in the landing image and align with beer barrels and hops
- Neutral: White for backgrounds to ensure content readability
- Text: Black or dark gray for optimal contrast

### Typography:
A modern sans-serif font is used for both headers and body text to ensure clarity and consistency. Button and menu text are bold for emphasis and easy navigation.

### Graphics:
The image on the landing page supports the brand’s identity and creates a strong visual connection to the brewing theme.

## Layout & UX (User Experience)
- Simple and consistent layout across all views supports an intuitive user flow
- Top navigation offers quick access to main sections (e.g., Brewing, Bottling, Customers, Inventory)
- List/detail structure is applied:
	- Detail/edit options via view, edit, delete icons
- Highlights are used for:
	- Important system alerts (like deletion confirmation)
- Action buttons (e.g., “View,” “Edit,” “Delete”) are grouped for clear workflows and use icon support where possible

## Responsive & Accessible Design
- Interactive elements are large enough for touch devices and designed with contrast for accessibility
- Labels, forms, and dropdowns support efficient data input with reduced user error
  
### Wireframe

After logging in, users can navigate to different pages via the navigation page. Only the login page is publicly accessible.

![image](https://github.com/user-attachments/assets/f3f6e155-38d6-4c47-802f-01909e2c3294)

## Landing Page:
![image](https://github.com/user-attachments/assets/319f9ffd-2c1d-4a3f-8350-2527f28bcb35)

## Brewing and Bottling:
![image](https://github.com/user-attachments/assets/b29774cd-b88a-4695-8b99-8eecc00ff94d)
![image](https://github.com/user-attachments/assets/137b861a-4ea2-4e75-86c6-c52181f76d94)

## Brewing Protocol:
![image](https://github.com/user-attachments/assets/6df9ca2d-c625-4659-a9c6-e40bae0b60c6)
![image](https://github.com/user-attachments/assets/4fbba231-799c-4245-b29f-83231cee8805)
![image](https://github.com/user-attachments/assets/41fec6e1-b1c7-4bb0-831f-0268360ea265)

## Customer overview
![image](https://github.com/user-attachments/assets/86aeff53-199b-4ce0-b069-0a6a6e3bf01d)

## Inventory Overview
![image](https://github.com/user-attachments/assets/47c82f82-202d-4729-af1c-b43bba680e65)

## Landing Page with stock warning
![image](https://github.com/user-attachments/assets/0104036d-4fed-4b14-be50-84a388ee8648)


### Prototype
The image shows an initial draft of the platform’s user interface created in Budibase, serving as a visual concept for the system’s basic structure and layout.

![image](https://github.com/user-attachments/assets/900150f3-af71-411e-ba3c-9c2d4453c3f5)

### Domain Design
This domain model illustrates the core structure of the brewing application using a UML class diagram. It defines key entities such as User, Customer, Order, BrewingProtocol, Bottling, Inventory, Recipe, and Alert, and visualizes their relationships, attributes, and operations to support brewing process management.

![image](https://github.com/user-attachments/assets/488ea3de-2c68-4bca-8ffd-1cb54d8baf4b)


### Business Logic 
Our application follows four key business logic rules to ensure efficiency, legal compliance, and data integrity. First, automated inventory management updates stock levels in real-time based on events like bottling and orders, removing the need for manual tracking. Second, an alert system monitors inventory and triggers warnings when levels fall below a set threshold. Third, age verification checks are performed during order entry to ensure customers are at least 16 years old. Finally, deletion restrictions prevent critical data—like recipes or bottling records linked to brewing or orders—from being removed.

The full API documentation is available via Swagger at: [Swagger UI Documentation](https://internet-technology-beer-brewing.onrender.com/swagger-ui/index.html)


**Logic Description**

***1. Automated Inventory Management***
The inventory system automatically updates stock levels based on customer and bottling activities:

Orders (POST, PUT, DELETE /orders):
- When an order is added, the system checks if enough stock is available for each item. If so, the corresponding quantities are deducted from the inventory; otherwise, the order is rejected.
- When an order is updated, inventory levels are adjusted up or down based on the difference between the previous and new quantities.
- When an order is deleted, the previously deducted quantities are restored to the inventory.

Bottling (POST, PUT, DELETE /bottling):
- When a bottling record is added, the produced quantities are added to the inventory.
- When a bottling record is updated, inventory levels are adjusted up or down depending on the change in produced quantity or product.
- When a bottling record is deleted, the corresponding quantities are removed from the inventory.

***2. Alert System***
After each inventory change (POST, PUT, DELETE on /orders or PUT, DELETE on /bottling):
- The system automatically monitors inventory levels after any change caused by orders or bottling records. Exception: When a new bottling is added with amounts below the threshold, the system will not trigger an alert.
	- If stock falls below 72 units, it checks whether an alert already exists. If not, a new alert is created for the product.
	- If stock rises to 72 units or above, any existing alert for that product is automatically resolved.

***3. Age Verification***
During customer creationg and adjusting (POST, PUT /customers):
- When customer data is created or adjusted, the system checks the provided birthdate.
- If the customer is younger than 16, the request is rejected with an error.
- If the customer is 16 or older, the customer data is saved successfully.

***4. Security / Deletion Rules***
The system enforces multiple business rules to ensure data consistency, such as:

Recipe Deletion (DELETE /recipes):
- When a delete request is received, the system checks if the recipe is referenced by any brewing protocol.
- If referenced, deletion is blocked and an error is returned.
- If not referenced, the recipe is deleted.

Bottling Deletion (DELETE /bottling):
- When a delete request is received, the system checks if the bottling record is linked to any customer orders.
- If linked, deletion is blocked and an error is returned.
- If not linked, the bottling record is deleted.


## Implementation
This web application is built with Spring Boot and uses an H2 in-memory database for data persistence during runtime. The backend is designed as a RESTful API and integrated into a Budibase frontend.

### Backend Technology Stack
The backend uses the following technologies and libraries:

- Spring Boot – Application framework
- Spring Data JPA – ORM and data access
- H2 Database – In-memory database for fast and lightweight testing
- Spring Security – Basic authentication and role-based access control
- Springdoc OpenAPI (Swagger UI) – API documentation

The project was initialized using Spring Initializr, targeting Java 17 and Spring Boot 3.2.2.

### Key Dependencies
- Database:
```XML
<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
</dependency>
```

- SWAGGER:
```XML
   <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.3.0</version>
   </dependency>
```

- Security:
```XML
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
   </dependency>
```

Other essential dependencies include:
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-actuator
- jakarta.validation-api and hibernate-validator
- spring-boot-starter-test (for testing)

### Database Setup
The application uses an H2 in-memory database, meaning that all data is reset on every application restart. No external database configuration is required.

To populate the system with sample data for development and testing, an initPlaceholderData() method is executed at startup (@PostConstruct). This method creates:
- 3 customers
- 6 beer recipes
- 6 brewing protocols
- 6 bottlings
- 3 customer orders

All of this is managed entirely in memory and requires no manual setup.


### Frontend Technology
This web application was developed using Budibase, a low-code platform for internal tools. The frontend is composed of several views, each tied to backend APIs implemented in Java with Spring Boot. These views allow users to manage key features such as brewing protocols, inventory, and customer orders.

The application is available for preview at:
https://internet-technology-beer-brewing.onrender.com

| View                 | Purpose                                     | HTTP Methods Used      | API Endpoint(s)          | Entity/Entities Involved   |
| -------------------- | ------------------------------------------- | ---------------------- | ------------------------ | ---------------------------------------------------- |
| **Home**             | Welcome page, Display current system alerts | GET, DELETE            | `/alerts`                | `Alert`,`ErrorResponse`                     |
| **Login**            | Authenticate users using form-based login   | GET, POST              | `/login`                 | `ErrorResponse`		      |
| **Brewings**         | Create, edit, and delete brewins            | GET, POST, PUT, DELETE | `/brewing-protocols`     | `BrewingProtocol`, `Recipe`, `RecipeCategory`,`ErrorResponse`      |
| **Recipes**          | Manage beer recipes and categories          | GET, POST, PUT, DELETE | `/recipes`               | `Recipe`, `RecipeCategory`,`ErrorResponse`  |
| **Inventory**        | Monitor current stock and make adjustments  | GET, POST, PUT, DELETE | `/inventory`             | `Inventory`, `Bottling`, `BrewingProtocol`,`ErrorResponse`         |
| **Bottling**         | Register new batches and edit bottling data | GET, POST, PUT, DELETE | `/bottling`              | `Bottling`, `BrewingProtocol`, `Recipe`, `RecipeCategory`,`ErrorResponse`  |
| **Orders**           | Place and view customer orders              | GET, POST, PUT, DELETE | `/orders`                | `Order`, `OrderItem`, `Customer`, `Inventory`,`ErrorResponse`        |
| **Customers**        | Manage customer profiles and data           | GET, POST, PUT, DELETE | `/customers`             | `Customer`, `Gender`,`ErrorResponse`                  |
| **Log-out**          | Confirm log out intention    |                    |               |                    |


### Constraints in Budibase
As discussed, here is a summary of the identified constraints and issues encountered on the frontend:
- Button Display: In a table Budibase can only display a maximum of 3 buttons. (Resolved by applying a different approach.)
- Order Functionality: The ordering process does not work as intended. Budibase struggled with saving the items in an array and the repeater block did not display the added order items as intended.
- Loading Behavior: In forms like Bottling or Brewing Protocol, selecting an option from a dropdown (e.g., batch number or recipe) only updates related fields (such as recipe name and category) after a second interaction. Some fields may auto-fill, but not consistently on the first selection.
- Dropdown Filtering for Bottling: When adding a new bottling entry, only batches without existing bottling records should appear. A constraint was planned to filter the dropdown accordingly, which Budibase did not allow for.
- Brewing Protocol: The initial plan was to save and display both the brewing and bottling data together within the Brewing Protocol, as outlined in the mock-up and shown in the image below. However, since brewings and bottlings reference each other, this approach caused circular dependencies. As a result, we decided to separate them and display each one individually.

<img width="470" alt="New Brewing" src="https://github.com/user-attachments/assets/b4bf3146-2ade-4dc0-b3a1-b86dd3a4bc95" />

 
## Execution
To run the Beer Brewing platform, both the backend service (Render) and the Budibase frontend must be started and connected correctly.

1. Start the Render Application

Open the backend hosted on Render via the following URL:
https://internet-technology-beer-brewing.onrender.com

2. Open the Budibase Application

In the Budibase environment for the class, open the app named Brugg 1_BeerBrewing via following URL:
https://inttech.budibase.app

### Login
To access the Beer Brewing platform, two user accounts are available:

Admin Account:
- Username: myadmin
- Password: password

User Account:
- Username: myuser
- Password: password


### Deployment to a PaaS
To make the application accessible online without needing to restart a local server, we deployed the backend to a Platform-as-a-Service (PaaS) provider — Render. This allows users and testers to access the backend anytime via a consistent URL.

## Project Management
The project of the web application for the Brauverein Full was developed collaboratively by a team of four. Roles and responsibilities were  divided, and the team coordinated progress through regular meetings and shared documentation.

### Roles
- **Back-end developer: Claudia Graf (Leader), Soheyla Tofighi, Tuangporn Siwaboon, Yannik Stöckli**
 
  Implementation of the backend using Spring Boot. Definition of core entities in the domain layer. Handling of database operations through repositories using Spring Data JPA. Encapsulation of business logic in the service layer for an error free user workflow. Exposure of RESTful endpoints in the controller layer to support operations such as managing brewings, bottlings, orders, and alerts. Integration of global exception handling to ensure stability and a smooth user experience.
 
- **Front-end developer: Soheyla Tofighi (Leader), Claudia Graf, Tuangporn Siwaboon, Yannik Stöckli**
 
  Building of the user interface using Budibase. Design and connection of frontend views to the backend services, enabling intuitive interaction with the system for different user roles.
 
- **Documentation & Project Coordinator: Tuangporn Siwaboon (Leader), Yannik Stöckli, Claudia Graf, Soheyla Tofighi**
 
  Coordination of team meetings and responsibilities, ensuring timely progress, and creation of the documentation (e.g., user stories, use cases, and this README).
- **Testing: Yannik Stöckli (Leader), Claudia Graf, Soheyla Tofighi, Tuangporn Siwaboon**
 
  Testing the functionalities across the frontend and backend, verifying correct API behavior, UI consistency, and ensuring that all use cases were properly supported.
- **Presentation: Claudia Graf, Soheyla Tofighi, Tuangporn Siwaboon, Yannik Stöckli**
 
  Preparation of the presentation, including outlining the user workflow to show and highlights of the business logic to mention. Delivering and uploading presentation.


### Milestones
1. **Analysis**: Scenario ideation, use case analysis and user story writing.
2. **Prototype Design**: Creation of wireframe and prototype.
3. **Domain Design**: Definition of domain model.
4. **Business Logic and API Design**: Definition of business logic and API.
5. **Data and API Implementation**: Implementation of data access and business logic layers, and API.
6. **Security and Frontend Implementation**: Integration of security framework and frontend realisation.
7. **Deployment**: Deployment of Web application on cloud infrastructure.


#### Maintainer
- Claudia Graf
- Tuangporn Siwaboon
- Yannik Stöckli
- Soheyla Tofighi 

#### License
- [Apache License, Version 2.0](blob/master/LICENSE)
