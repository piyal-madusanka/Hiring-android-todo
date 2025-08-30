## 🚀 Description

Briefly describe the features you’ve implemented in this PR.  
Mention any key implementation details, edge cases, or design decisions.

---

## 💡 Solution Rationale & User Value

Please take a moment to explain:

- Why you structured the solution the way you did

  *The solution was structured using Jetpack Compose and a mvvm architecture + clean architecture
  principles.
  This approach was chosen to leverage Compose's declarative UI capabilities for a modern and
  responsive user interface,
  while the mvvm pattern helps in managing UI-related data in a lifecycle-conscious way. Clean
  architecture principles were applied
  to ensure clear separation of concerns, making the codebase maintainable and scalable.

- What you were optimizing for (e.g. performance, readability, UX)

*The main optimizations were for user experience (responsive UI with keyboard handling),
readability (using Compose idioms and ViewModel for state),
and performance (efficient recompositions and local database persistence).

- How this benefits the end user  
  *The end user benefits from a smooth and intuitive interface for managing tasks,
  with features like adding, editing, completing, and deleting tasks.
  The use of a local database ensures that their data is reliably stored and quickly accessible,
  enhancing the overall user experience.

---

## 💾 Database Persistence Rationale

Explain which database engine you used (e.g. Room, SQLite, Realm) and why.  
Include any trade-offs or reasons behind your choice.

*Room was chosen as the database engine for this project due to its seamless integration with
Android's architecture components,
making it easier to implement a robust and maintainable data layer. Room provides an abstraction
over SQLite,
which simplifies database operations and reduces boilerplate code. It also offers compile-time
verification of SQL queries,
ensuring that any errors are caught early in the development process. Additionally, Room supports
LiveData and Flow,
allowing for reactive data handling that fits well with modern Android development practices.
---

## 🧠 State Management

If you used a state management approach (e.g. ViewModel + LiveData, StateFlow, Compose State),
briefly explain:

- Why it was used
  *ViewModel with StateFlow was used to manage UI-related data in a lifecycle-aware manner,
  ensuring that the data survives configuration changes and is efficiently updated.

- What value it added over simpler local state
  *Using ViewModel with StateFlow allows for a clear separation of UI logic from business logic,
  making the codebase more maintainable and testable.
  It also enables reactive programming, where the UI automatically updates in response to data
  changes,
  enhancing the user experience.

---

## ⚙️ Build Configuration

Explain how you set up:

- Debug and Release build types
-
    * Debug build type is configured for development with logging and debugging tools enabled.
      Release build type is optimized for performance and security, with code shrinking and
      obfuscation
      enabled using R8.

- Product flavors/variants (application IDs, app names, strings)
-
    * Two product flavors were created: "SL" and "UK".
    * Each flavor has a unique application ID and app name to differentiate them in the Play Store.

    * lk -- com.example.todoapp.sl
      uk -- com.example.todoapp.uk

    * Flavor-specific resources (like strings) are provided to customize the app for different
      audiences or markets.
    * and theres 3 environment variants: dev, staging, prod

Include any important details about ProGuard/R8 or signing setup.

---

## 💫 Animations (Bonus, if implemented)

If you added animations (e.g. list item transitions, checkbox animations), please describe them
here.  
Let us know what you animated and why you chose to do so.

---

## 🤖 AI Assistance Transparency

If you used AI tools (e.g. ChatGPT, GitHub Copilot, etc.), please explain:

- Which tools you used
  *I used GitHub Copilot to assist with code suggestions

- What parts of the implementation they helped with
  *GitHub Copilot helped with UI related changes, suggesting best practices,

- How you validated and refined the results
  *I reviewed and tested the suggestions provided by GitHub Copilot to ensure they met the project
  requirements and adhered to best practices.

---

## 🎥 Demo Video

Include a link to a short screen recording (e.g. Loom or MP4) showing the app in use.

---

## 🛠️ Setup Instructions (if different from README)

Mention any additional setup steps or environment changes, if applicable.

---

## 📌 Known Limitations / Assumptions

List any known bugs, incomplete features, or assumptions made during implementation.

* UI bit different than figma
* No unit tests yet
* There is no input validation for empty tasks
* there is no confirmation dialog for deleting tasks
* there is a ime padding issue when keyboard is open add todo page
---

## ✅ Checklist

- [ ] Tasks can be added
- [ ] Tasks can be viewed
- [ ] Tasks can be edited
- [ ] Tasks can be marked complete/incomplete
- [ ] Tasks can be deleted
- [ ] Data is persisted locally using a **database engine** (not SharedPreferences)
- [ ] Database choice explained in PR
- [ ] State management approach explained
- [ ] Debug and release build types configured
- [ ] Multiple product flavors/variants configured
- [ ] (Optional) Animations added
- [ ] Demo video included
- [ ] Solution rationale & user value explained
- [ ] AI usage transparency provided  

