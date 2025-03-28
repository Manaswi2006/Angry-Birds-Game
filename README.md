### **README for Angry Birds Game**  

---

# Angry Birds Game 🐦💥
![image](https://github.com/user-attachments/assets/45a21424-46b8-416b-8d7b-f23a397dca00)


Welcome to the **Angry Birds Game** project! This is a 2D physics-based game inspired by the popular Angry Birds series. In this game, players launch various birds with unique abilities to destroy blocks and defeat pigs.
![image](https://github.com/user-attachments/assets/eb89b47d-bb93-4da4-8c42-959c63a31b94)

## **Table of Contents**
1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Setup Instructions](#setup-instructions)
4. [How to Play](#how-to-play)
5. [Project Structure](#project-structure)
6. [Contribution](#contribution)
 

---

## **Features**
- **Physics-Driven Gameplay**: Leverages Box2D physics for realistic bird launches and block destruction.
- **Multiple Bird Types**:
  - **Red Bird**: Standard bird.
  - **Yellow Bird**: High-speed bird.
  - **Black Bird**: Explosive bird.
- **Dynamic Levels**:
  - Multiple levels with unique layouts and increasing difficulty.
- **Slingshot Mechanic**: Drag and release birds to launch them toward the target.
- **Collision Detection**: Real-time contact between birds, blocks, and pigs.

---

## **Technologies Used**
- **LibGDX**: Cross-platform game development framework.
- **Box2D**: Physics engine for realistic simulations.
- **Java**: Core programming language.
- **Gradle**: Build automation system.

---

## **Setup Instructions**

### **Prerequisites**
Ensure you have the following installed:

1. [Gradle](https://gradle.org/install/)
2. [LibGDX](https://libgdx.com/)

### **Steps to Run**
1. **Clone the Repository**:
    ```bash
    git clone https://github.com/Manaswi2006/Angry-Birds-Game.git
    cd Angry-Birds-Game
    ```

2. **Build the Project**:
    ```bash
    ./gradlew build
    ```

3. **Run the Game**:
    ```bash
    ./gradlew lwjgl3:run
    ```

4. **Enjoy the Game**!

---

## **How to Play**
1. **Select a Level**: Start by selecting a level from the main menu.
2. **Launch Birds**:
    - Drag the bird on the slingshot.
    - Release to launch it toward blocks and pigs.
3. **Objective**:
    - Destroy all pigs on the screen by collapsing structures.
4. **Win or Lose**:
    - Win the level by defeating all pigs.
    - Lose if you run out of birds.

---

## **Project Structure**
```
Angry-Birds-Game/
├── core/                     # Core game logic
│   ├── src/com/badlogic/drop/
│   │   ├── Screens/          # Screen management (levels, menus)
│   │   ├── Sprites/          # Bird, Block, Pig classes
│   │   ├── Scenes/           # UI and Towers
│   │   └── Angry_Birds_Game  # Main game entry point
├── lwjgl3/                   # Desktop launcher
└── README.md                 # Documentation
```

---

##  **contribution**
manaswi singh 2023307
paridhi kotarya 2023367

---
 ## **resources**
 https://angrybirds.fandom.com/wiki/The_Angry_Birds_Movie
 https://libgdx.com/wiki/start/project-generation
 https://www.youtube.com/watch?v=Mn5rjsxWH_E&list=PLXY8okVWvwZ2Ph9LKWiNBZ1GRAc_TyDru&index=13

---
 
