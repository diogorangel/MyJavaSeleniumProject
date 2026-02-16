
## ☕ Java + Selenium + Cucumber (BDD) Framework

Author: Diogo Rangel (diogorangel)
This branch contains a high-level automation framework using **Java** and **Cucumber** for Behavioral Driven Development (BDD). It follows the **Page Object Model (POM)** and uses **Maven** for dependency management.

### 🛠️ Tech Stack
* **Language:** Java 17
* **Framework:** Cucumber with JUnit 4
* **Browser Automation:** Selenium WebDriver
* **Driver Management:** WebDriverManager
* **Build Tool:** Maven

### 📊 Reports & Evidence
* **Automatic Screenshots:** Every scenario failure (or success) triggers a screenshot handled by `Hooks.java`.
* **HTML Dashboard:** A visual report is generated at `target/cucumber-reports.html`.
* **PDF Export:** To generate a PDF evidence file, open the HTML report in a browser and use the **Print to PDF** feature.

### 🚀 How to Run
1. Make sure **Maven** is installed (`mvn -version`).
2. Run all scenarios:
   ```bash
   mvn clean test
   ```
3. Run specific tags:
   mvn test "-Dcucumber.filter.tags="@001_Login_StandardUser"
   
4. Examples : 
   Terminal,Format Needed
   PowerShell (VS Code): mvn test "-Dproperty.name=value"
   CMD (Command Prompt): mvn test -Dproperty.name=value
   Git Bash / Linux: mvn test -Dproperty.name=@tag
---

### ✅ Final Checklist for Success:

1.  **Install Maven:** As seen in your image, the `mvn` command failed. Make sure to download Maven and add the `bin` folder to your Windows **PATH**.
2.  **Organize the Files:** Make sure `LoginPage.java` is in the `src/test/java/pages` folder and `LoginSteps.java` is in `src/test/java/steps`.
3.  **Tags:** In your `TestRunner`, the tag `@001_Login_StandardUser` must exist exactly as written at the top of your `.feature` file.


With this structure, you demonstrate proficiency in **Python (Robot and Pytest)** and **Java (Cucumber/BDD)**. This puts you at a **QA Fullstack** level, ready for any technical challenge.

**Congratulations on the project! Would you like a final review of any of the files?**

Commands to Run:

Run all tests:
```bash
mvn test
```

Generate Report:
Check `target/cucumber-reports.html` after running.