# JavaSpringBootApplication

## Swagger UI Documentation 
http://localhost:8080/swagger-ui/index.html#/

### 1. **Business Requirements for the Chosen Business Domain (Esports Tournaments and Teams)**

1. **Team Creation and Management**:  
   The system should allow the creation of esports teams, with key information such as team name, number of tournaments participated in, and the year the team was founded. It should support editing team details as needed.

2. **Player Registration and Association**:  
   Players should be registered within the system with personal details, including first name, last name, nickname, and birthdate. The system should associate players with a specific team, if applicable, through a foreign key relationship.

3. **Tournament Management**:  
   The system should manage esports tournaments by allowing the creation of tournaments with information like title, year, and associated teams. Tournaments will also link to detailed information such as attendance and prize pool, helping track the tournament’s size and financial details.

4. **Match Management**:  
   The system should support managing matches within tournaments, with key details such as match title, sport type, and score. Match data should be associated with the corresponding tournament and sport category.

5. **Match Categorization**:  
   Matches within tournaments should be categorized into different predefined categories, such as "Best of Three," "Single Elimination," etc. This will help organize the structure of tournaments and aid in filtering or grouping similar types of matches.

6. **Tournament Details Tracking**:  
   The system should store tournament details separately, including attendance figures and prize pool amounts. This data should be linked to tournaments to provide insights into tournament scale and the value of the prize pool.

7. **Unique Nickname for Players**:  
   The system must enforce uniqueness for player nicknames. No two players in the system should be allowed to have the same nickname. This ensures that each player can be uniquely identified by their nickname within the system.

8. **Data Integrity and Validation**:  
   The system should ensure data integrity through constraints like non-null fields, unique constraints (for team names, player nicknames), and checks (e.g., positive values for prize pools and attendance).

9. **Search Functionality**:  
   Users should be able to search for specific teams, players, tournaments, or matches based on attributes such as team name, player nickname, or tournament year. This will help users easily find relevant information within the system.

10. **Simplified User Interface**:  
   The system should provide an intuitive interface for managing teams, players, tournaments, and matches. Users should be able to easily add, update, or view data for each entity without needing complex navigation.

### 2. **Main Features for the MVP (Minimum Viable Product) Phase (Revised)**

1. **Team and Player Management**:
   - **Team Management**: The system should allow the creation and management of esports teams, with attributes such as team name, the number of tournaments participated in, and year founded.
   - **Player Management**: The system should allow for adding players with details such as first name, last name, nickname, date of birth, and their assigned team (if applicable).
   - **Player-Team Association**: Players can be linked to teams, and each player should have a unique nickname.

2. **Tournament and Match Management**:
   - **Tournament Creation**: The system should allow the creation of tournaments with essential information such as title, year, and the associated teams.
   - **Match Creation**: Matches can be created under specific tournaments, with information such as match title, sport type, and score. The system should allow users to track match outcomes and tournament performance.

3. **Tournament Details and Prize Pool Tracking**:
   - **Tracking Attendance and Prize Pool**: Each tournament will have specific details such as attendance and prize pool. This information will be stored separately in the `tournament_details` table and linked to each tournament.
   - **Tracking Performance**: Organizers can update tournament details to reflect attendance numbers and prize pool values, enabling tracking of tournament growth and financial data.

4. **Match Categorization**:
   - **Categorizing Matches**: The system should support categorizing matches by predefined categories (e.g., "Single Elimination," "Best of Three"). 
   - This categorization will help in organizing and filtering matches based on the type of event or game format used in the tournament.

5. **Basic Search and Filtering**:
   - **Search Functionality**: Users should be able to search for teams, players, tournaments, and matches by attributes such as team name, player nickname, tournament year, or match title.
   - **Filter Options**: Basic filtering options should be implemented, allowing users to filter and view relevant information, such as viewing tournaments by year or matches by category type.
