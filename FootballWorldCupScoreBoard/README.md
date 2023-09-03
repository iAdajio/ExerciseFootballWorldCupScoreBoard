_Author_: Ivan Verburgt

_Date_: 03/11/2023

# Design Procedure
## Steps:
1. Outline project structure with no functional code
2. Create Test all methods for the score board operations outlined in Task document
3. Run the test which should fail
4. Write some code to validate the operation and to pass the tests
5. Refactor the code and test
6. Add boundary tests for invalid inputs
7. Enhance tests to achieve +95% coverage
8. Submit for code review

# Code Structure:
The code adheres to the SOLID principles by the use of OOP and design Patterns

ScoreBoardService.java
- This is used for controlling the score by
- Start games
- Update game scores
- Finish games
- output current games

GameDataSource.java
- implements IDataService
- Acts as a source for the game data
- It was used to decouple the game data from ScoreBoardService
- Allows simple process of implementing different data source solutions without breaking ScoreBoardService

FootballMatch.java
- implements IFootballMatch
- Holds the basic game data like {teamHome,teamAway,matchScore}

MatchScore.java
- used by FootballMatch.java
- holds the score data
- reason for separate class, the match score could be stored in a separate database table

TeamName.java
- We want to avoid users adding the team name by typing
- This prevents team name typos which could create undesired results

# Acceptance Criteria
- Start a game and record the game match data to a collection containing active games.
- Finish a game and remove it from the game match data.
- Update a chosen game score with input scores (home,away)
- Output the summary of active games. Each game output format must be "{teamHome} - {teamAway}: {scoreHome} - {scoreAway}"

# Tutorial
See Main.java

