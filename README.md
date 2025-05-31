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
> 🚧: You can reuse the analysis (you made) from other projects (e.g., requirement engineering), but it must be submitted according to the following template. 



### Scenario

The Brauverein Full is a brewing association based in Full. It was founded in June 2018 by five people who aimed to produce beer for themselves, friends, and families. The reason for the foundation of an association was that the beer of a private individual is legally not allowed to leave the property where it is brewed, which complicated the sharing with other people. Through the small size of the village, which has less than 1000 inhabitants, the word of a local brewery spread, and the association gained in popularity and customers.
The Brauverein Full has been managing its stock and sales using a self-made Excel sheet that has become increasingly time-consuming, error-prone, and difficult to manage as the association has grown in size and complexity. The association has decided to switch to a webbased application to streamline and automate its inventory management, sales tracking, and reporting processes. The new application will enable the association to track its inventory levels in real-time, automatically generate sales reports, and provide better visibility into its sales trends and stock turnover rates.
This project aims to improve the efficiency and accuracy of the Brauverein Full’s inventory management and sales tracking, reducing administrative overhead and enabling the association to make data-driven decisions to optimize their operations and meet the needs of their members and customers more effectively. 

The Excel sheet used for the stock and sales management was created by the head of administration of the Brauverein Full. Its purpose is to give the association members a better overview of the inventory levels and decrease the effort at the end of the year to create the report for the customs administration overseeing the taxation of sold beer.
Since the Excel sheet includes many complex formulas, the remainder of the association will not use the tool out of fear of deleting or breaking something. As a solution to this problem, the head of administration currently manages to fill the Excel sheet herself. The association members transmit the information about withdrawals from the inventory via a specific WhatsApp chat. They are then transferred into the Excel sheet by the head of administration, which leads to a double track and a high administrative effort for multiple members. Furthermore, since only one out of five people opened the Excel sheet, it lost its purpose.
The Excel sheet does not give any insights about specific customers or their orders since the administrative workload is tried to be held as small as possible for the head of administration. With the implementation of a web-based application, this information could be easily added and would provide better visibility into the sales trends.

### User Stories

#### User Stories General
1. As a User, I want to have a Web app to use on different mobile devices and desktop computers.
2. As a User, I want to see a consistent visual appearance to navigate easily, and it should look consistent.
3. As a User, I want to use list views to explore and read my business data.
4. As a User, I want to use edit and create views to maintain my business data.
5. As a User, I want to log-in so that I can authenticate myself.
6. As an Admin, I want to authenticate myself so that I can delete data.

#### User Stories Administration
1. As a Head of Administration, I want to add user accounts so that I can grant access to new users.
2. As a User, I want to edit my user account so that I can update user information when necessary.
3. As a Head of Administration, I want to delete user accounts so that I can remove access when needed.
4. As a User, I want to log in so that I can access the system securely.
5. As a User, I want to log out so that I can securely end my session.

#### User Stories Inventory
1. As a User, I want to manually add new inventory items so that I can track stock entries.
2. As a System, I want to automatically add inventory after brewing or bottling so that the stock stays accurate.
3. As a User, I want to edit inventory items so that I can correct stock data.
4. As a System, I want to automatically adjust inventory after an order so that real-time stock levels are maintained.
5. As an Admin, I want to delete inventory items so that I can clean up invalid entries.
6. As a user, I want to view the full inventory so that I understand current stock levels.
7. As a user, I want to view inventory by ID so that I can find specific items quickly.
8. As a user, I want to view an inventory summary so that I can get an overview of stock categories

#### User Stories Brewing 
1. As an Admin, I want to add new brewing protocols so that brewing processes are documented.
2. As a User, I want to edit brewing protocols so that I can update brewing information.
3. As an Admin, I want to delete brewing protocols so that I can remove outdated data.
4. As a User, I want to view brewing protocols so that I can understand the production process.
5. As a User, I want to view brewing protocols by ID so that I can reference specific batches.


#### User Stories Bottoling 
1. As a User, I want to add bottling records so that packaging is documented.
2. As a User, I want to edit bottling records so that I can update packaging information.
3. As an Admin, I want to delete bottling records so that I can clean up errors.
4. As a User, I want to view bottling records so that I can see packaging status.
5. As a User, I want to view bottling records by ID so that I can look up specific batches.

#### User Stories Customer 
1. As an Admin, I want to add new customers so that I can manage client information.
2. As a User, I want to edit customer data so that I can keep records accurate.
3. As an Admin, I want to delete customers so that I can remove outdated contacts.
4. As a User, I want to view all customers so that I can see our client base.
5. As a User, I want to view customers by ID so that I can quickly locate someone.

#### User Stories Recipe 
1. As an Admin, I want to add new recipes so that we can document brewing formulas.
2. As a User, I want to edit recipes so that I can update brewing instructions.
3. As an Admin, I want to delete recipes so that I can remove unused ones.
4. As a User, I want to view all recipes so that I can follow brewing guidelines.
5. As a User, I want to view recipes by ID so that I can access specific details.

#### User Stories Alert 
1. As a System, I want to automatically add an alert when inventory is low so that admins are notified in time.
2. As an Admin, I want to delete alerts so that I can manage and clear notifications.
3. As a System, I want to automatically delete alerts when inventory is replenished so that only relevant alerts remain.


### Use Case

#### Use Case 100 General
![image](https://github.com/user-attachments/assets/02af40b1-dbaa-4138-a27a-914c35b16af3)

- UC-101 [Login]: The system shall allow both the admin and the user to log in.
- UC-102 [Create Views]: The system shall allow both the admin and the user to create new views.
- UC-103 [Edit Views]: The system shall allow both the admin and the user to edit existing views.
- UC-104 [Read Views]: The system shall allow both the admin and the user to read views.
- UC-105 [Delete Views Logout]: The system shall allow the admin to delete views.

#### Use Case 200 Inventory
![image](https://github.com/user-attachments/assets/a71acacd-768a-4acd-bd23-ee32c32ede8e)

- UC-201 [Add Inventory]: The system shall allow both the admin and the user to manually add new inventory items. Additionally, the system shall automatically update the inventory based on newly created brewing protocols and bottling records.
- UC-202 [Edit Inventory]: The system shall allow both the admin and the user to edit existing inventory items. Additionally, the system shall automatically edit the inventory based on newly created orders. 
- UC-203 [Delete Inventory]: The system shall allow the admin to delete inventory items.
- UC-204 [View Inventory]: The system shall allow both the admin and the user to view the inventory.
- UC-205 [View Inventory by ID]: The system shall allow both the admin and the user to view the inventory sorted by ID.
- UC-206 [View Inventory Summary]: The system shall allow both the admin and the user to view the inventory summary.

#### Use Case 300 Brewing
![image](https://github.com/user-attachments/assets/bb617d79-2072-4987-a8fa-5fc7c40e35be)

- UC-301 [Add Brewing Protocol]: The system shall allow the admin to add a new brewing protocol.
- UC-302 [Edit Brewing Protocol]: The system shall allow both the admin and the user to edit an existing brewing protocol.
- UC-303 [Delete Brewing Protocol]: The system shall allow the admin to delete a brewing protocol.
- UC-304 [View Brewing Protocol]: The system shall allow both the admin and the user to view brewing protocols.
- UC-305 [View Brewing Protocol by ID]: The system shall allow both the admin and the user to view brewing protocols sorted by ID.

#### Use Case 400 Bottoling
![image](https://github.com/user-attachments/assets/eb40a4e3-d665-4c8d-86f9-0fead5d03b04)

- UC-401 [Add Bottling]: The system shall allow both the admin and the user to add a new bottling record.
- UC-402 [Edit Bottling]: The system shall allow both the admin and the user to edit an existing bottling record.
- UC-403 [Delete Bottling]: The system shall allow the admin to delete a bottling record.
- UC-404 [View Bottling]: The system shall allow both the admin and the user to view bottling information.
- UC-405 [View Bottling by ID]: The system shall allow both the admin and the user to view bottling information sorted by ID.

#### Use Case 500 Customerg
![image](https://github.com/user-attachments/assets/b3757710-eba2-4cf8-858b-9916f562a3f4)

- UC-501 [Add Customer]: The system shall allow the admin to add a new customer.
- UC-502 [Edit Customer]: The system shall allow both the admin and the user to edit an existing customer.
- UC-503 [Delete Customer]: The system shall allow the admin to delete a customer.
- UC-504 [View Customer]: The system shall allow both the admin and the user to view customer information.
- UC-505 [View Customer by ID]: The system shall allow both the admin and the user to view customer information sorted by ID.

#### Use Case 600 Recipe
![image](https://github.com/user-attachments/assets/534a3e3f-0072-4254-aac4-b410271269b7)

- UC-601 [Add Recipe]: The system shall allow the admin to add a new recipe.
- UC-602 [Edit Recipe]: The system shall allow both the admin and the user to edit an existing recipe.
- UC-603 [Delete Recipe]: The system shall allow the admin to delete a recipe.
- UC-604 [View Recipe]: The system shall allow both the admin and the user to view recipe information.
- UC-605 [View Recipe by ID]: The system shall allow both the admin and the user to view recipe information sorted by ID.

#### Use Case 700 Alert
![image](https://github.com/user-attachments/assets/7f038159-f956-49dc-9781-147108920a54)

- UC-701 [Add Alert]: The system shall automatically add an alert when the inventory per category is below 72.
- UC-702 [Delete Alert]: The system shall allow the admin to delete an existing alert. Additionally, the system shall automatically delete an existing alert when the inventory per category is above 72. 


## Design
> 🚧: Keep in mind the Corporate Identity (CI); you shall decide appropriately the color schema, graphics, typography, layout, User Experience (UX), and so on.

### Wireframe
> 🚧: It is suggested to start with a wireframe. The wireframe focuses on the website structure (Sitemap planning), sketching the pages using Wireframe components (e.g., header, menu, footer) and UX. You can create a wireframe already with draw.io or similar tools. 

Starting from the home page, we can visit different pages. Available public pages are visible in the menu...

## Landing Page:
![image](https://github.com/user-attachments/assets/319f9ffd-2c1d-4a3f-8350-2527f28bcb35)

## Brewing and Bottling:
![image](https://github.com/user-attachments/assets/b29774cd-b88a-4695-8b99-8eecc00ff94d)
![image](https://github.com/user-attachments/assets/137b861a-4ea2-4e75-86c6-c52181f76d94)

## Brewing Protocol:
![image](https://github.com/user-attachments/assets/b61edacb-2d51-4ece-a70a-5c7004eba6c9)
![image](https://github.com/user-attachments/assets/b50af2fe-b741-449f-b4e9-509324c873e2)
![image](https://github.com/user-attachments/assets/43823a7e-8e50-4515-ac1f-f6911f54eceb)

## Customer overview
![image](https://github.com/user-attachments/assets/86aeff53-199b-4ce0-b069-0a6a6e3bf01d)

## Inventory Overview
![image](https://github.com/user-attachments/assets/47c82f82-202d-4729-af1c-b43bba680e65)

## Landing Page with stock warning
![image](https://github.com/user-attachments/assets/0104036d-4fed-4b14-be50-84a388ee8648)


### Prototype
> 🚧: A prototype can be designed using placeholder text/figures in Budibase. You don't need to connect the front-end to back-end in the early stages of the project development.

### Domain Design
> 🚧: Provide a picture and describe your domain model; you may use Entity-Relationship Model or UML class diagram. Both can be created in Visual Paradigm - we have an academic license for it.

![image](https://github.com/user-attachments/assets/b8cebc80-d842-4b4f-9394-fb6e54898480)
hmm
### Business Logic 
> 🚧: Describe the business logic for **at least one business service** in detail. If available, show the expected path and HTPP method. The remaining documentation of APIs shall be made available in the swagger endpoint. The default Swagger UI page is available at /swagger-ui.html.

Based on the UC-4, there will be two offers and a standard offer. Given a location, a message is shown accordingly:

- If the location is "Basel", the message is "10% off on all large pizzas!!!"
- If the location is "Brugg", the message is "two for the price of One on all small pizzas!!!"
- Otherwise, the message is "No special offer".

**Path**: [`/api/menu/?location="Basel"`] 

**Param**: `value="location"` Admitted value: "Basel","Brugg".

**Method:** `GET`

### Business Logic 
A key part of our application is the automatic adjustment of inventory and alert management, which ensures that stock levels are always monitored and visible in real-time — without manual tracking. The system reacts to business events such as orders and bottling, updating inventory levels accordingly and triggering or clearing alerts based on stock thresholds.

**Logic Description**
1. Orders reduce inventory: When a customer places an order, the system reduces the inventory quantity of each ordered item.

2. Bottling increases inventory: When a batch is bottled, the finished product is added to the inventory, increasing its quantity.

3. Alerts are automatically triggered or resolved: After each inventory update, the system checks whether the current quantity is below the critical threshold of 72 units (per category):
- If the quantity falls below 72, an alert is automatically triggered.
- If the quantity rises back to 72 or above, the corresponding alert is automatically resolved (deleted or marked inactive).

Relevant API Endpoints
| Action          | HTTP Method | Endpoint        | Triggered Logic                          |
| --------------- | ----------- | --------------- | ---------------------------------------- |
| Place Order     | POST        | `/api/orders`   | Decrease inventory, trigger alert if <72 |
| Finish Bottling | POST        | `/api/bottling` | Increase inventory, clear alert if ≥72   |
| View Alerts     | GET         | `/api/alerts`   | Display all active low-stock alerts      |


## Implementation
> 🚧: Briefly describe your technology stack, which apps were used and for what.

### Backend Technology
> 🚧: It is suggested to clone this repository, but you are free to start from fresh with a Spring Initializr. If so, describe if there are any changes to the PizzaRP e.g., different dependencies, versions & etc... Please, also describe how your database is set up. If you want a persistent or in-memory H2 database check [link](https://github.com/FHNW-INT/Pizzeria_Reference_Project/blob/main/pizza/src/main/resources/application.properties). If you have placeholder data to initialize at the app, you may use a variation of the method **initPlaceholderData()** available at [link](https://github.com/FHNW-INT/Pizzeria_Reference_Project/blob/main/pizza/src/main/java/ch/fhnw/pizza/PizzaApplication.java).

This Web application is relying on [Spring Boot](https://projects.spring.io/spring-boot) and the following dependencies:

- [Spring Boot](https://projects.spring.io/spring-boot)
- [Spring Data](https://projects.spring.io/spring-data)
- [Java Persistence API (JPA)](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)
- [H2 Database Engine](https://www.h2database.com)

To bootstrap the application, the [Spring Initializr](https://start.spring.io/) has been used.

Then, the following further dependencies have been added to the project `pom.xml`:

- DB:
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

### Frontend Technology
> 🚧: Describe your views and what APIs is used on which view. If you don't have access to the Internet Technology class Budibase environment(https://inttech.budibase.app/), please write to Devid on MS teams.

This Web application was developed using Budibase and it is available for preview at https://inttech.budibase.app/app/pizzeria. 

This web application was developed using Budibase, a low-code platform for building internal tools. The frontend consists of multiple views, each connected to specific backend RESTful APIs implemented in Java using Spring Boot. These views allow users to interact with core features like brewing protocols, inventory management, and customer orders.

The application is available for preview at:
https://xxxx

| View                 | Purpose                                    | HTTP Methods Used      | API Endpoint(s)              | Entity Used                |
| -------------------- | ------------------------------------------ | ---------------------- | ---------------------------- | -------------------------- |
| **Dashboard**        | Overview with recent alerts and orders     | GET                    | `/api/alerts`, `/api/orders` | `Alert`, `Order`           |
| **Brewing Protocol** | Manage brewing procedures and steps        | GET, POST, PUT, DELETE | `/api/brewing-protocols`     | `BrewingProtocol`          |
| **Recipes**          | Manage beer recipes                        | GET, POST, PUT, DELETE | `/api/recipes`               | `Recipe`, `RecipeCategory` |
| **Inventory**        | Monitor and update stock levels            | GET, PUT               | `/api/inventory`             | `Inventory`                |
| **Bottling**         | Document bottling steps and manage batches | POST, PUT              | `/api/bottling`              | `Bottling`                 |
| **Orders**           | Create and view customer orders            | GET, POST              | `/api/orders`                | `Order`, `OrderItem`       |
| **Customers**        | View and manage customer information       | GET, POST              | `/api/customers`             | `Customer`                 |
| **Users**            | Manage user accounts and roles             | GET, POST, PUT         | `/api/users`                 | `User`, `Role`             |
| **Alerts**           | View active system alerts                  | GET                    | `/api/alerts`                | `Alert`                    |


## Execution
> 🚧: Please describe how to execute your app and what configurations must be changed to run it. 

**The codespace URL of this Repo is subject to change.** Therefore, the Budibase PizzaRP webapp is not going to show any data in the view, when the URL is not updated or the codespace is offline. Follow these steps to start the webservice and reconnect the webapp to the new webservice url. 

> 🚧: This is a shortened description for example purposes. A complete tutorial will be provided in a dedicated lecture.

1. Clone PizzaRP in a new repository.
2. Start your codespace (see video guide at: [link](https://www.youtube.com/watch?v=_W9B7qc9lVc&ab_channel=GitHub))
3. Run the PizzaRP main available at PizzaApplication.java on your own codespace.
4. Set your app with a public port, see the guide at [link](https://docs.github.com/en/codespaces/developing-in-a-codespace/forwarding-ports-in-your-codespace).
5. Create an own Budibase app, you can export/import the existing Pizzeria app. Guide available at [link](https://docs.budibase.com/docs/export-and-import-apps).
6. Update the pizzeria URL in the datasource and publish your app.

### Deployment to a PaaS
> 🚧: Deployment to PaaS is optional but recommended as it will make your application (backend) accessible without server restart and through a unique, constantly available link.  

Alternatively, you can deploy your application to a free PaaS like [Render](https://dashboard.render.com/register).
1. Refer to the Dockerfile inside the application root (FHNW-INT/Pizzeria_Reference_Project/pizza).
2. Adapt line 13 to the name of your jar file. The jar name should be derived from the details in the pom.xml as follows:<br>
`{artifactId}-{version}.jar` 
2. Login to Render using your GitHub credentials.
3. Create a new Web Service and choose Build and deploy from a Git repository.
4. Enter the link to your (public) GitHub repository and click Continue.
5. Enter the Root Directory (name of the folder where pom.xml resides).
6. Choose the Instance Type as Free/Hobby. All other details are default.
7. Click on Create Web Service. Your app will undergo automatic build and deployment. Monitor the logs to view the progress or error messages. The entire process of Build+Deploy might take several minutes.
8. After successful deployment, you can access your backend using the generated unique URL (visible on top left under the name of your web service).
9. This unique URL will remain unchanged as long as your web service is deployed on Render. You can now integrate it in Budibase to make API calls to your custom endpoints.

## Project Management
> 🚧: Include all the participants and briefly describe each of their **individual** contribution and/or roles. Screenshots/descriptions of your Kanban board or similar project management tools are welcome.

The project of the web application for the Brauverein Full was developed collaboratively by a team of four. Roles and responsibilities were  divided, and the team coordinated progress through regular meetings and shared documentation.

### Roles
- **Back-end developer: Claudia Graf, Soheyla Tofighi** 

They implemented the backend logic using Spring Boot. They defined the core entities (e.g., BrewingProtocol, Recipe, Inventory, User) and developed RESTful endpoints to support the platform’s functionalities, such as managing brewing steps, orders, and alerts.

- **Front-end developer: Soheyla Tofighi, Claudia Graf**

They built the user interface using Budibase. They designed and connected frontend views to the backend services, enabling intuitive interaction with the system for different user roles.

- **Documentation, Presentation & Project Coordinator: Tuangporn Siwaboon, Yannik Stöckli**

They coordinated team meetings and responsibilities, ensured timely progress, and created the documentation (e.g., user stories, use cases, and this README). They also prepared and delivered the final presentation.

- **Testing: Claudia Graf, Soheyla Tofighi, Tuangporn Siwaboon, Yannik Stöckli**

The entire team contributed to testing by checking functionalities across the frontend and backend, verifying correct API behavior, UI consistency, and ensuring that all use cases were properly supported.


### Task Overview

| Task Area            | Responsible Person(s)             | Notes                                       |
|----------------------|-----------------------------------|---------------------------------------------|
| Backend endpoints     | Claudia, Soheyla                   | Defined core entities and logic             |
| Frontend (UI)         | Soheyla, Claudia                   | Budibase implementation and testing         |
| Documentation         | Yannik, Tuangporn                  | Use cases, user stories, README             |
| Presentation slides   | Tuangporn, Yannik                  | Structure, content, and delivery            |
| Manual Testing        | Everyone                           | Testing features during and after development |
| Coordination          | Yannik, Tuangporn                  | Organized tasks, meetings, and deadlines    |


### Milestones
1. **Analysis**: Scenario ideation, use case analysis and user story writing.
2. **Prototype Design**: Creation of wireframe and prototype.
3. **Domain Design**: Definition of domain model.
4. **Business Logic and API Design**: Definition of business logic and API.
5. **Data and API Implementation**: Implementation of data access and business logic layers, and API.
6. **Security and Frontend Implementation**: Integration of security framework and frontend realisation.
7. (optional) **Deployment**: Deployment of Web application on cloud infrastructure.


#### Maintainer
- Claudia Graf
- Tuangporn Siwaboon
- Yannik Stöckli
- Soheyla Tofighi 

#### License
- [Apache License, Version 2.0](blob/master/LICENSE)
