# Rainfall-Prediction-App

This app was developed to support the author's final research activities:
## Optimization of Tsukamoto FIS In Predicting Rainfall In Banyuasin Regency Using Genetic Algorithm

<details>
  <summary>Select The Language</summary>
  <ul>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md">Bahasa Indonesia</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md">English</a>
    </li>
  </ul>
</details>

<details>
  <summary>Table of Contents</summary>
  <ul>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#1-introduction">1. INTRODUCTION</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#2-hardware--software-requirements">2. HARDWARE & SOFTWARE REQUIREMENTS</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#3-installing-the-project">3. INSTALLING THE PROJECT</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#4-running-the-app">4. RUNNING THE APP</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#5-using-the-app">5. USING THE APP</a>
      <ul>
        <li><a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#51-predicting-rainfall-from-a-dataset">5.1 Predicting Rainfall From Dataset</a></li>
        <li><a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#52-predicting-rainfall-from-new-data">5.2 Predicting Rainfall From New Data</a></li>
      </ul>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#6-class-implementation">6. CLASS IMPLEMENTATION</a>
      <ul>
        <li><a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#61-geneticalgorithmjava">6.1 GeneticAlgorithm.java</a></li>
        <li><a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#62-fistsukamotojava">6.2 FISTsukamoto.java</a></li>
        <li><a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#63-mainjava">6.3 Main.java</a></li>
      </ul>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#7-about-the-research">7. ABOUT THE RESEARCH</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#8-about-the-author">8. ABOUT THE AUTHOR</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#9-support-me">9. SUPPORT ME</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#10-how-to-contribute">10. HOW TO CONTRIBUTE</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#11-license">11. LICENSE</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#12-acknowledgement">12. ACKNOWLEDGEMENT</a>
    </li>
  </ul>
</details>


## 1. INTRODUCTION
The Rainfall Prediction App is a desktop-based application developed to forecast rainfall in Banyuasin Regency, South Sumatera Province, Indonesia. Developing this application is a mandatory requirement to obtain the Bachelor of Computer Science degree from the University of Sriwijaya. The prediction model in this application employs the Tsukamoto Fuzzy Inference System (FIS) optimized using the Genetic Algorithm (GA). Based on the conducted research, the lowest Mean Absolute Percentage Error (MAPE) achieved in predicting rainfall using this model is *27.8%*. The monthly climate data of Banyuasin Regency from January 2018 to December 2022 will be utilized for rainfall prediction. The climate data comprises attributes such as temperature, air humidity, air pressure, wind velocity, and rainfall.


## 2. HARDWARE & SOFTWARE REQUIREMENTS
The hardware required to support the operation of this application is as follows:
1. Laptop or PC with a minimum display resolution of 1280 * 720

The software required to support the operation of this application is as follows:
1. Windows OS 10/11 64-bit
2. NetBeans IDE version 14 and above
3. Java Development Kit (JDK) version 20 and above


## 3. INSTALLING THE PROJECT
Installing the application via Git Bash CLI:
+ Open Git Bash
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
3. Open the *gui* package and select the `Main.java` class
4. Run the application


## 5. USING THE APP
There are 2 functional requirements of the application system:

![Use Case Diagram](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Gambar%20IV-10%20Diagram%20Use%20Case.drawio.png?raw=true "Usecase Diagram")
*Fig. 1 Use Case Diagram*

### 5.1 Predicting Rainfall From A Dataset
Use case 1 is implemented on the app's homepage. This page is utilized to conduct research on rainfall prediction to find the lowest MAPE value using genetic algorithm parameters: generation size, population size, crossover rate, and mutation rate. Afterwards, click the button to initiate the program.

> [!CAUTION]
> The values that can be input for crossover rate and mutation rate are limited to a range between 0 and 1!
> If they are outside of this range, the program will display an error message. Additionally, each parameter only accepts numerical input data.

Below is the display of the homepage, the activity, and the sequence diagram:

![Homepage](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Screenshot%202023-12-28%20224822.png?raw=true "Homepage")
*Fig. 2 Homepage*

![Activity Diagram 1](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Activity%20Diagram%201.drawio.png?raw=true "Activity Diagram 1")
*Fig. 3 Activity Diagram*

![Sequence Diagram 1](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Diagram%20Sequence%20Prediksi%20Curah%20hujan%20dari%20Data%20Set.drawio.png?raw=true "Sequence Diagram 1")
*Fig. 4 Sequence Diagram*

### 5.2 Predicting Rainfall From New Data
Use case 2 is implemented on the *Manual Forecasting* page. This page is used to predict rainfall values from new data. The boundaries of the fuzzy membership functions used are taken from the chromosome that had the lowest MAPE value during the research, which was 27.8%. After inputting attribute values for temperature, air humidity, air pressure, and wind speed, click the button. Here is the display of the *Manual Forecasting* page.

![Manual Forecasting Page](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Screenshot%202023-12-29%20232823.png?raw=true "Manual Forecastin Page")
*Fig. 5 Manual Forecasting Page*

![Activity Diagram 2](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Activity%20Diagram%202.drawio.png?raw=true "Activity Diagram 2")
*Fig. 6 Activity Diagram*

![Sequence Diagram 2](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Diagram%20Sequence%20Prediksi%20Curah%20hujan%20dari%20Data%20Baru.drawio.png?raw=true "Sequence Diagram 2")
*Fig. 7 Sequence Diagram*


## 6. CLASS IMPLEMENTATION

### 6.1 `GeneticAlgorithm.java`
This class is an abstract superclass that utilized to receive input data from the `Main.java` class and then initiates the process of searching for chromosome that can generate membership function boundaries with the best fitness value. The stage starts from initializing the initial population to computing the fitness value.

### 6.2 `FISTsukamoto.java`
This class is a subclass of `GeneticAlgorithm.java` used to calculate the rainfall value using the Tsukamoto FIS method, both from the homepage and the manual forecasting page. This class also computes the Absolute Percentage Error (APE) and Mean Absolute Percentage Error (MAPE), then returns the MAPE values to the superclass. The process begins with fuzzification, rule-based implication, defuzzification, and culminates in the evaluation of the MAPE value.

### 6.3 `Main.java`
This class represents the application's GUI, comprising the Homepage and the Manual Forecasting page.

Here is the class diagram of the application:

![Class Diagram](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Diagram%20Kelas.drawio.png?raw=true "Class Diagram")
*Fig. 8 Class Diagram*


## 7. ABOUT THE RESEARCH
If you're interested in my research, you can read mine at the following link.
- Undergraduate thesis: [OPTIMIZATION OF TSUKAMOTO FIS IN PREDICTING RAINFALL IN BANYUASIN REGENCY USING GENETIC ALGORITHM](http://repository.unsri.ac.id/137165/)


## 8. ABOUT THE AUTHOR
> Hi! My name is Muhammad Rafi Akbar. You can call me Rafi. I'm a bachelor of computer science from [Sriwijaya University](https://unsri.ac.id) majoring in [Informatics](https://if.ilkom.unsri.ac.id). I have a keen interest in fuzzy logic and cryptography fields. You can reach out to me through the following accounts.
- [LinkedIn](https://linkedin.com/in/murafba)
- [GitHub](https://github.com/murafba)


## 9. SUPPORT ME
If you found my project useful, you can show your support by attributing to this project and giving it a star on this repository. Alternatively, you can also provide material support through the following links:
- [PayPal](https://paypal.me/murafba)
- [Saweria (for Indonesian)](https://saweria.co/murafba)


## 10. HOW TO CONTRIBUTE
Pull requests are not available. I will provide a dedicated repository related to the research where you can make pull requests on that page. If you have any questions or recommendations, please feel free to do so in the Issues section.


## 11. LICENSE
Copyright &copy; 2023 Muhammad Rafi Akbar <br>
This project is under the [MIT](https://github.com/murafba/Rainfall-Prediction-App/blob/main/LICENSE) License.


## 12. ACKNOWLEDGEMENT
As the developer, I'm aware that the developed application still has some shortcomings. Therefore, constructive criticism and suggestions from colleagues are highly appreciated.
