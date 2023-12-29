# Rainfall-Prediction-App

## 1. INTRODUCTION
The Rainfall Prediction App is a desktop-based application developed to forecast rainfall in Banyuasin Regency, South Sumatera Province, Indonesia. Developing this application is a mandatory requirement to complete the Computer Science degree at the University of Sriwijaya. The prediction model in this application employs the Tsukamoto Fuzzy Inference System (FIS) optimized using the Genetic Algorithm (GA). Based on the conducted research, the lowest Mean Absolute Percentage Error (MAPE) achieved in predicting rainfall using this model is *27.8%*.

## 2. SOFTWARE REQUIREMENTS
The software required to support the operation of this application is as follows:
1. Windows OS 10/11 64-bit
2. NetBeans IDE version 14 and above
3. Java Development Kit (JDK) version 20 and above

## 3. INSTALLING THE PROJECT
Installing the application via Git Bash CLI:
+ Opeh Git Bash
+ Choose the download location folder. For example: `cd d://downloads`
+ Type `git clone https://github.com/murafba/Rainfall-Prediction-App.git`
+ Check the download folder and open the project

## 4. RUNNING THE APP
Here are the steps to run the app:
1. Launch NetBeans
2. Open the downloaded project
> [!IMPORTANT]
> If the default JDK used is below version 20, it needs to be changed. Here are the steps:
> (a) Right-click on the project;
> (b) Select *properties*;
> (c) Choose the *Libraries* menu;
> (d) In the *Java Platform* dropdown menu, select JDK version 20 or above.
3. Open the *gui* package and select the **Main.java** class
4. Run the application

## 5. USING THE APP
There are 2 functional requirements of the application system:

![Use Case Diagram](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Gambar%20IV-10%20Diagram%20Use%20Case.drawio.png?raw=true "Usecase Diagram")
*Fig. 1 Use Case Diagram*

### 5.1 Predicting rainfall from a dataset
Use case 1 is implemented on the app's Homepage. This page is utilized to conduct research on rainfall prediction to find the lowest MAPE value using genetic algorithm parameters: generation size, population size, crossover rate, and mutation rate. Afterwards, click the button to initiate the program.
> [!CAUTION]
> The values that can be input for crossover rate and mutation rate are limited to a range between 0 and 1!
> If they are outside of this range, the program will display an error message. Additionally, each parameter only accepts numerical input data.

Below is an example of the home page:

![Homepage](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Screenshot%202023-12-28%20224822.png?raw=true "Homepage")
*Fig. 2 Homepage*



