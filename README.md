
# **U-Fund Volunteer Management System**

**Overview**  
The U-Fund Volunteer Management System is a web application built for Aspiration Cottage, a fictional non-profit providing temporary housing for cancer patients. It connects volunteers (Helpers) with the organization, managing volunteer opportunities (needs), scheduling, and donations.

**Key Features**  
- **Admin**: Create, edit, and delete volunteer opportunities (needs).  
- **Helper**: Browse, add needs to their basket, and schedule commitments.  
- **Donation**: Helpers can donate money directly to the platform.  
- **Dashboard**: A central hub to track volunteer commitments with a 14-day calendar view.

**Architecture**  
The application follows a **Model-View-ViewModel (MVVM)** architecture:
- **Model**: Manages data related to users, needs, and donations.
- **ViewModel**: Handles business logic and communicates with the back-end services.
- **View**: Built with **Angular** for a single-page application (SPA) providing an intuitive interface.

**Tech Stack**  
- **Front-End**: Angular, TypeScript, HTML, CSS  
- **Back-End**: Java, Spring Boot, REST API  
- **Database**: MySQL  
- **Testing**: Unit tests with **JUnit** and **Jacoco** for code coverage.

**Getting Started**  
1. Clone the repository:
   ```bash
   git clone https://github.com/itzme170605/VolunterManagement.git
   cd VolunterManagement
   ```
2. **Backend**:  
   - Install dependencies and run the backend:
     ```bash
     mvn compile exec:java
     ```
   - Access the backend on `http://localhost:8080/`.

3. **Frontend**:  
   - Navigate to the `angular/` directory.
   - Run the Angular app:
     ```bash
     ng serve
     ```
   - Access the frontend on `http://localhost:4200/`.

**User Types & Workflows**  
- **Admins**: Can create, update, or delete volunteer opportunities, and manage all user data.
- **Helpers (Volunteers)**: Can browse opportunities, add them to their basket, check out, and track their schedules. Helpers can also donate money directly through the platform.

**Features for Volunteers**  
- **Helper Dashboard**: Shows current and upcoming volunteer opportunities.  
- **Basket**: Add multiple needs to the basket and check them out to schedule.  
- **Calendar View**: Helps volunteers manage their schedule for upcoming commitments.  
- **Donation**: Ability to make monetary contributions to Aspiration Cottage.

**Features for Admins**  
- **Needs Management**: Create, update, or remove volunteer opportunities.  
- **Volunteer Tracking**: Monitor which volunteers have committed to which needs.

**Enhancements**  
- **Donation Tracking**: Helpers' past donations are saved, making it easier to track their contributions.
- **Calendar for Helpers**: A view to track all scheduled volunteer commitments for the upcoming weeks.

**Application Domain**  
- **Admin**: Manages volunteer needs and tracks user activities.
- **Helper**: Participates in volunteer activities and tracks their involvement.

**Key Components**  
- **LoginComponent**: User login for both Admin and Helper.  
- **DashboardComponent**: Central page for Helpers, showing needs and upcoming events.
- **EditCupboardComponent**: Admin page to edit, create, and remove volunteer needs.
- **DonateComponent**: Allows Helpers to donate to Aspiration Cottage.
- **BasketComponent**: Allows Helpers to add needs to their basket for checkout.

**Development Notes**  
- **Code Design**: Followed **OO principles** like **encapsulation**, **inheritance**, and **low coupling** to ensure maintainable and modular code.
- **RESTful Architecture**: Designed with REST API endpoints to interact with the database and frontend, ensuring a clear separation between front-end and back-end logic.
- **Unit Testing**: Focused on ensuring backend functionality with JUnit tests, achieving **85%+** code coverage using Jacoco.

**Future Improvements**  
- **Code Optimization**: The app will undergo performance optimizations to ensure better handling of data retrieval.
- **UI/UX Enhancements**: The user interface will be further improved with CSS, providing a more visually appealing and interactive experience.
  
**License**: MIT License

---
