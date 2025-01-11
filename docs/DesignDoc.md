---
geometry: margin=1in
---
# PROJECT Design Documentation

> _The following template provides the headings for your Design
> Documentation.  As you edit each section make sure you remove these
> commentary 'blockquotes'; the lines that start with a > character
> and appear in the generated PDF in italics but do so only **after** all team members agree that the requirements for that section and current Sprint have been met. **Do not** delete future Sprint expectations._

## Team Information
* Team name: FUNd Team
* Team members
  * Kyrsten Guerrero
  * Noah Rousseau
  * Nathan Stankus
  * Jatin Jain

## Executive Summary

The project is a web application designed to support Aspiration Cottage, a fictional non-profit organization that provides temporary housing for cancer patients in need. This application connects volunteers with the organization by facilitating the management and scheduling of volunteer opportunities, referred to as "needs." The system adheres to a RESTful architecture, utilizing Angular for the front-end and Java Spring for the back-end.

The platform serves two main user types: Admin and Helpers. The Admin account, representing the organization, has the capability to create, edit, and delete needs, detailing the location, time, and volunteer requirements. Helper accounts allow volunteers to browse, search for, and sign up for these needs. The workflow is streamlined through a "basket" system, enabling Helpers to add multiple needs, review them, and commit to their schedule via a checkout process.

Key features include:
> Admin tools for managing volunteer opportunities.
> A Helper dashboard for tracking commitments and upcoming events.
> Enhanced functionalities, such as viewing a 14-day calendar of scheduled commitments and the ability to make donations directly through the platform.

The project aims to ensure seamless communication between the organization and its volunteers while fostering consistent volunteer engagement.


### Purpose
>  _**[Sprint 2 & 4]** Provide a very brief statement about the project and the most 
> important user group and user goals._

This project aims to connect potential volunteers with Aspiration Cottage, a fictional national non-profit organization that provides facilities for cancer patients in need of temporary housing. 

Only one account is available for the Manager or Admin. This account can create "needs" or volunteer opportunities. These volunteer opportunities include the following information: name, description, facility location, date and time, and required number of volunteers. All of this information can be edited by the Admin account. These needs can also be deleted by the admin account. 

Potential volunteers can create as many "Helper" accounts as they want. These helper accounts can see, search through, and vounteer for the needs that the admin account manages. To volunteer for a need, a Helper must first add the need to their basket then check the need out from their basket. When a Helper has checked out a need, it will show up in their schedule and the Admin can see the Helper in the list of volunteers for that need.

The most important user group is the volunteer user group, since they contribute the most to user count and keep Aspiration Cottage running. 

The primary user goal is to be consistently volunteering for needs. The secondary user goal, brought about by one of two website enhancements, is to be consistently donating money to Aspiration Cottage. 

The project is a web application for Aspiration Cottage, a fictional non-profit, enabling seamless management of volunteer opportunities. The most important user group is the Helpers (volunteers), whose primary goal is to consistently engage in volunteering by signing up for needs. Admins facilitate this by creating and managing these opportunities. The system promotes efficient volunteer participation and supports organizational needs.

### Glossary and Acronyms
> _**[Sprint 2 & 4]** Provide a table of terms and acronyms._

| Term | Definition                        |
|------|------------                       |
| SPA  | Single Page                       |
| REST | REpresentational State Transfer   |
| API  | Application Programming Interface |

## Requirements

This section describes the features of the application.

> _In this section you do not need to be exhaustive and list every
> story.  Focus on top-level features from the Vision document and
> maybe Epics and critical Stories._

Potential volunteers can create Helper accounts that can view and search through needs. These accounts must also be able to add needs to the account's basket and check out any number of needs in the basket. Once the account has needs that are checked out, the user can see the needs they checked out in a schedule on their dashboard. The account data must be stored, even when the Helper logs out. The Helper accounts also have the option of donating money.

The Manager must have one Admin account that can create, edit, and delete needs. 

### Definition of MVP
> _**[Sprint 2 & 4]** Provide a simple description of the Minimum Viable Product._

Users are able to log into the website and browse through the needs. Admin can edit the need cupboard, and helpers can add these needs to their basket. Helpers can check needs out from the basket into their schedule.  

### MVP Features
>  _**[Sprint 4]** Provide a list of top-level Epics and/or Stories of the MVP._

Potential volunteers can create Helper accounts that can view and search through needs. These accounts must also be able to add needs to the account's basket and check out any number of needs in the basket. The account data must be stored, even when the Helper logs out. The account data must be stored, even when the Helper logs out.

The Manager must have one Admin account that can create, edit, and delete needs.

### Enhancements
> _**[Sprint 4]** Describe what enhancements you have implemented for the project._

Helper accounts can view their scheduled needs for the next weeks or 7 days in a listed calendar on their dashboard. 
Helpers can also donate money to Aspiration Cottage, and their past donations are saved for the helper to keep track of how much they've donated.


## Application Domain

This section describes the application domain.

The domain diagram shows how the different parts of the project work together:

![Domain Diagram](./Domain%20Analysis%20Model.png)

There are two types of users: the Admin and Helpers. Admin creates, edits, and removes needs, which updates the need cupboard since it is made up of needs. Helpers can add needs to and remove needs from their basket and check needs out from their basket. Helpers also have a stored total donated amount. 

> _**[Sprint 2 & 4]** Provide a high-level overview of the domain for this application. You
> can discuss the more important domain entities and their relationship
> to each other._


## Architecture and Design

This application uses a REST API to keep track of the user information and need information.  This API interfaces with an Angular user inerface, providing a volunteer view and a manager view for both

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture. 
**NOTE**: detailed diagrams are required in later sections of this document.
> _**[Sprint 1]** (Augment this diagram with your **own** rendition and representations of sample system classes, placing them into the appropriate M/V/VM (orange rectangle) tier section. Focus on what is currently required to support **Sprint 1 - Demo requirements**. Make sure to describe your design choices in the corresponding _**Tier Section**_ and also in the _**OO Design Principles**_ section below.)_

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

The web application, is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistance. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The ViewModel provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the ViewModel and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.


### Overview of User Interface

> This section describes the web interface flow; this is how the user views and interacts with the web application.

When opening the website, the user is automatically sent to the log in page. When logged in, the user can log out at any time using the log out button in the upper right hand corner. 

When logged in as a Helper, the user can access their dashboard, basket, schedule, and the donate page through the bar of buttons just under the website title. Helpers can access everything mentioned before in their dashboard. Also in the dashboard is a list of needs that the Helper has checked out taking place within the next two weeks. The dashboard is primarily used for browsing through needs and adding them to the basket. The Helper can access their basket through the dashboard or the basket tab. Once they have checked needs out, those needs will show up in their schedule, which can be accessed by opening the schedule view from the navigation bar. Finally, the user can navigate to the donation page, where they can donate money. This can also be done on the dashboard. 

When logged in as the Admin, the user has the view that allows them to edit the needs cupboard. There is a section that allows the admin to create needs. Underneath this is the list of current needs. Next to the individual needs are buttons with X's on them. These buttons allow the user to remove their respective needs. If the admin clicks on any of the needs in the list, they will be routed to a view where they can update or change the need. The user can click the back button to go back to the previous view or can click the save button, which saves all the changes the user made to the need. 


### View Tier
> _**[Sprint 4]** Provide a summary of the View Tier UI of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

The View Tier of the application is responsible for providing the user interface (UI) that interacts with the user and presents the data, ensuring the user has a seamless and intuitive experience. It consists of several components designed to support the needs of both volunteers and the Admin user.

Here's an overview of the key components and their responsibilities:

LoginComponent:
The entry point to the application. Users are prompted to log in, whether they are existing volunteers or new users. The component manages authentication and redirects users based on their role.

RegisterComponent:
This component allows new users to create an account. After registration, users are directed to the login page. It ensures the registration process is seamless and secure.

DashboardComponent:
After logging in, the user is directed to the dashboard. This component acts as a central hub for volunteers (Helper accounts), providing them with an overview of available needs, their schedule, and actions like viewing and searching needs. It is the main page for navigating to other features, like the basket and schedule.

CupboardComponent:
The "Cupboard" view displays a list of all available needs (volunteer opportunities) that the Admin has created. Volunteers can browse through these needs and decide which ones they wish to add to their basket. This component allows easy access to information regarding each need and includes options to filter or search for specific needs.

NeedComponent:
This component provides the detailed view of a specific need, identified by the id parameter in the route. It presents comprehensive details such as the name, description, date, and number of volunteers required. Volunteers can decide to add the need to their basket from here.

NeedSearchComponent:
This component handles search functionality, allowing users to filter through available needs based on certain criteria. This is particularly useful for volunteers looking for specific types of volunteer opportunities, such as by location or date.

BasketComponent:
This component shows the volunteer’s current basket, listing all needs they’ve selected to volunteer for. It provides options to remove needs from the basket and proceed to checkout. After checking out, these needs are added to the volunteer's schedule.

ScheduleComponent:
The schedule component presents the needs the volunteer has successfully checked out. This acts as a calendar or list view of their upcoming volunteer commitments, enabling them to manage their volunteer schedule easily. Clicking on a scheduled need reveals detailed information about the event.

EditCupboardComponent:
Accessible only by the Admin, this component allows them to create, edit, or delete needs. Admins can input details about the needs, like description, date, location, and the number of volunteers required. This functionality ensures the needs cupboard is up-to-date and accurate.

DonateComponent:
This component allows users to donate money to the Aspiration Cottage organization. The component provides a simple interface for making donations and contributes to enhancing the platform's support for the non-profit's mission.

Flow of the Application:
The application begins with the LoginComponent, where the user either logs in or registers.
After logging in, the user is taken to the DashboardComponent, where they can see a list of available needs via the NeedSearchComponent.
From there, they can search for needs still using the NeedSearchComponent and choose the needs they want to volunteer for, adding them to the BasketComponent.
Once ready, they proceed to checkout, and the selected needs are transferred to their ScheduleComponent.
Admins have access to EditCupboardComponent to manage the needs, ensuring the list remains up-to-date.
Additionally, all users can navigate to the DonateComponent to contribute financially to the cause.
The UI components are organized in a way that creates a smooth flow for the user, with clear access to different functionalities based on their role (volunteer or admin).

> _**[Sprint 4]** You must  provide at least **2 sequence diagrams** as is relevant to a particular aspects 
> of the design that you are describing.  (**For example**, in a shopping experience application you might create a 
> sequence diagram of a customer searching for an item and adding to their cart.)
> As these can span multiple tiers, be sure to include an relevant HTTP requests from the client-side to the server-side 
> to help illustrate the end-to-end flow._

![Sequence diagram to search for Needs and add to basket](docs\add_need_search.png)

> _**[Sprint 4]** To adequately show your system, you will need to present the **class diagrams** where relevant in your design. Some additional tips:_
 >* _Class diagrams only apply to the **ViewModel** and **Model** Tier_
>* _A single class diagram of the entire system will not be effective. You may start with one, but will be need to break it down into smaller sections to account for requirements of each of the Tier static models below._
 >* _Correct labeling of relationships with proper notation for the relationship type, multiplicities, and navigation information will be important._
 >* _Include other details such as attributes and method signatures that you think are needed to support the level of detail in your discussion._

### ViewModel Tier
> _**[Sprint 1]** List the classes supporting this tier and provide a description of there purpose._

The ViewModel tier provides the application’s interface for client interaction and business logic, bridging the model (data) with the controller (API endpoints):

	1.	Controllers:
	•	NeedController and UserController handle HTTP requests related to Need and User entities, respectively.
	•	They use REST endpoints (GET, POST, PUT, DELETE) for CRUD operations and return appropriate ResponseEntity objects, managing HTTP status codes based on the success or failure of each operation.
	2.	Services:
	•	NeedService and UserService encapsulate the core business logic, implementing the INeedService and IUserService interfaces.
	•	They interact with data access objects (DAOs) to perform operations like retrieving, creating, updating, and deleting Need and User records.
	•	The services handle additional logic, such as duplicate checks or error handling, ensuring data consistency before responding to controllers.

In summary, the ViewModel tier manages client requests, routes them through controllers, applies business logic via services, and communicates with the data layer for a consistent, secure user experience.


> _**[Sprint 4]** Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._
	

> _At appropriate places as part of this narrative provide **one** or more updated and **properly labeled**
> static models (UML class diagrams) with some details such as associations (connections) between classes, and critical attributes and methods. (**Be sure** to revisit the Static **UML Review Sheet** to ensure your class diagrams are using correct format and syntax.)_
> 
![Replace with your ViewModel Tier class diagram 1, etc.](model-placeholder.png)

### Model Tier
> _**[Sprint 1]** List the classes supporting this tier and provide a description of their purpose._

> _**[Sprint 2, 3 & 4]** Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

The Model Tier serves as the foundation of the application, defining the core data structures and managing how data is stored, retrieved, and manipulated. It acts as the backbone of the system, ensuring that the entities, relationships, and behaviors align with the application's domain requirements.

Responsibilities of the Model Tier
1.	Data Representation:
	•	Encapsulates the application's key entities, such as User, Need, Manager, Helper, and ScheduleItem.
	•	Provides fields, constructors, and methods to manage the attributes and relationships of these entities.
2.	Persistence Management:
	•	Handles the storage and retrieval of data through Data Access Objects (DAOs).
	•	Implements interfaces (IUserDAO, INeedDAO) to standardize data operations and ensure flexibility for potential future changes (e.g., switching to a database).
3.	Data Transformation:
	•	Supports custom deserialization (e.g., ScheduleItemDeserializer) to map external JSON data into internal Java objects, allowing seamless integration with APIs and external data sources.

Importance of the Model Tier in the Application
•	Foundation for Business Logic: The Model Tier provides the essential data structures that the business logic (ViewModel Tier) operates upon, ensuring consistency and clarity.
•	Modular Design: By encapsulating data-related functionality within the Model Tier, the application adheres to a modular architecture, making it easier to manage, test, and extend.
•	Scalability and Flexibility: Standardized interfaces and well-defined relationships allow for easy adaptation to future enhancements, such as integrating a database or adding new entity types.
•	Seamless Data Integration: Custom deserializers and DAOs ensure that the application can handle complex data flows and interactions with external systems.

Here’s a breakdown:

	1.	User and Need Models:
	•	User is an abstract class with common authentication fields (e.g., userName, password) and methods for user management, extended by specific user types (Manager, Helper).
	•	Need represents a requirement that can involve users as volunteers. It includes fields like name, facility, description, status, and methods to manage volunteer involvement.
	2.	Data Access Objects (DAOs):
	•	UserFileDAO and NeedFileDAO implement persistence by reading from and writing to files. They provide CRUD operations (create, read, update, delete) for User and Need objects.
	•	Interfaces IUserDAO and INeedDAO standardize access to User and Need data, promoting consistency and flexibility in how data is managed and accessed.
	3.	The ScheduleItem class represents a scheduled volunteer activity. It links a specific Need with its associated date and time.
	Attributes:
	•	need (Need): The volunteer opportunity linked to this schedule item.
	•	dateTime (Date): The specific date and time when the scheduled activity occurs.
	Constructor
	•	ScheduleItem(Need need, Date dateTime): Initializes the ScheduleItem with a Need and a Date.
	Methods
	•	Getters and Setters:
		•	getNeed(), setNeed(Need need): Access and modify the need.
		•	getDateTime(), setDateTime(Date dateTime): Access and modify the dateTime.
	•	toString():
		•	Returns a string representation of the ScheduleItem, e.g., "ScheduleItem{need=..., dateTime=...}".
	This class is integral to modeling a Helper's schedule, providing a clear link between a volunteer opportunity and its execution time.
	4. The ScheduleItemDeserializer class is a custom deserializer for converting JSON into ScheduleItem objects.
	Responsibilities
	•	Deserialization:
		•	Converts JSON fields:
			•	"need" into a Need object.
			•	"dateTime" into a Date object.
		•	Constructs and returns a ScheduleItem using the deserialized values.
	Key Methods
	•	deserialize(JsonParser jp, DeserializationContext ctxt):
		•	Uses Jackson's ObjectMapper to parse JSON data into Java objects.
		•	Reads nested JSON fields, maps them to their respective Java classes, and assembles a ScheduleItem.
	This deserializer ensures seamless mapping of JSON data to the application’s schedule representation, allowing Helpers' schedules to be processed dynamically from external sources.

This tier effectively defines the structure, relationships, and basic data management behaviors of key application entities.



> _At appropriate places as part of this narrative provide **one** or more updated and **properly labeled**
> static models (UML class diagrams) with some details such as associations (connections) between classes, and critical attributes and methods. (**Be sure** to revisit the Static **UML Review Sheet** to ensure your class diagrams are using correct format and syntax.)_
> 
![Replace with your Model Tier class diagram 1, etc.](model-placeholder.png)

## OO Design Principles

For Sprint 1, we have considered foundational Object-Oriented (OO) principles that support a flexible and maintainable design. Below are the key principles we’ve applied:

Encapsulation
Abstraction
Inheritance
Low Coupling

> _**[Sprint 2, 3 & 4]** Will eventually address upto **4 key OO Principles** in your final design. Follow guidance in augmenting those completed in previous Sprints as indicated to you by instructor. Be sure to include any diagrams (or clearly refer to ones elsewhere in your Tier sections above) to support your claims._

	1.	Encapsulation:
	•	Description: We encapsulate data and methods within classes, such as User, Need, UserService, and NeedService, to hide implementation details and expose only necessary interfaces.
	•	Support: This principle ensures that each class manages its state independently, improving security and modularity by preventing outside code from altering internal data directly. For example, UserService interacts with User objects through methods rather than exposing fields directly.
	2.	Abstraction:
	•	Description: Abstraction is used to simplify complex realities by modeling classes and methods in a way that only exposes necessary functionality. For instance, the INeedService and IUserService interfaces define essential methods for Need and User management, abstracting the underlying data access and logic.
	•	Support: By abstracting key services through interfaces, we allow different implementations (e.g., file-based or database-based storage) to be used interchangeably, enhancing flexibility and scalability in later sprints.
	3.	Inheritance:
	•	Description: We apply inheritance to create specific types of users by extending a base User class. Manager and Helper are subclasses of User, each with distinct attributes and behaviors.
	•	Support: This principle supports code reuse and improves clarity by enabling shared functionality within the User class and allowing subclasses to add or override methods where necessary.
		4.	Low Coupling:
	•	Descripton: Each class we have only acesses the necessary functions of other classes: for example, the UserController class, as well as any of the User classes, does not acess Need or any of the need classes at all.
	•	Support: Using classes with a signle responsability, low coupling is a kew principle in making sure that the design has minimal impacts from change.  When an error happens, or an update is made, only the ncessesary class is edited, and only its immediate neighbors are impacted.

## Static Code Analysis/Future Design Improvements
> _**[Sprint 4]** With the results from the Static Code Analysis exercise, 
> **Identify 3-4** areas within your code that have been flagged by the Static Code 
> Analysis Tool (SonarQube) and provide your analysis and recommendations.  
> Include any relevant screenshot(s) with each area._

![Static Code Coverage using SonarQube](./Static%20Code%20Coverage.png)

> _**[Sprint 4]** Discuss **future** refactoring and other design improvements your team would explore if the team had additional time._

The most important thing we are missing is code efficiency. We have a ton of stuff such as Helper email that is unused that we haven't removed due to time constraints. 

We would also like to improve user design if we had more time. The current website does not have much CSS, leaving it rather bare. We also have made a few design choices such as having two locations to view Helper basket that are not veyr optimized for user experience. 

## Testing
> _This section will provide information about the testing performed
> and the results of the testing._

### Acceptance Testing
> _**[Sprint 2 & 4]** Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

### Unit Testing and Code Coverage
> _**[Sprint 4]** Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets._

>_**[Sprint 2, 3 & 4]** **Include images of your code coverage report.** If there are any anomalies, discuss
> those._

The unit tests only cover the java aspects of the backend APIs.  To Verify that the unit test covered all nesscessary functions, Jacoco the code coverage tool was used.  Generally, the unit tests verify that the backend performs as expected.
![General Code Coverage](General Code Coverage.png)
Within the main branch, where unit tests are the most important we maintained above an 85% code coverage for all classes.  There are some functions that are internal and do not need to be verified.

## Ongoing Rationale
>_**[Sprint 1, 2, 3 & 4]** Throughout the project, provide a time stamp **(yyyy/mm/dd): Sprint # and description** of any _**mayor**_ team decisions or design milestones/changes and corresponding justification._

