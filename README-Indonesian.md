# Rainfall-Prediction-App

Aplikasi ini dikembangkan untuk mendukung kegiatan penelitian penulis:
## Optimasi FIS Tsukamoto dalam Memprediksi Curah Hujan di Kabupaten Banyuasin Menggunakan Algoritma Genetika

<details>
  <summary>Pilih Bahasa</summary>
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
  <summary>Daftar Isi</summary>
  <ul>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#1-pendahuluan">1. PENDAHULUAN</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#2-kebutuhan-perangkat-lunak">2. KEBUTUHAN PERANGKAT LUNAK</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#3-menginstal-proyek">3. MENGINSTAL PROYEK</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#4-menjalankan-aplikasi">4. MENJALANKAN APLIKASI</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README.md#5-menggunakan-aplikasi">5. MENGGUNAKAN APLIKASI</a>
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


## 1. PENDAHULUAN
Rainfall Prediction App adalah aplikasi berbasis desktop yang dikembangkan untuk memprediksi curah hujan di Kabupaten Banyuasin, Sumatera Selatan, Indonesia. Pengembangan aplikasi ini merupakan syarat wajib untuk mendapatkan gelar Sarjana Komputer dari Jurusan Informatika, Universitas Sriwijaya. Model prediksi pada aplikasi ini menggunakan *Fuzzy Inference System* (FIS) Tsukamoto yang dioptimasi dengan Algoritma Genetika (GA). Berdasarkan penelitian yang telah dilakukan, nilai MAPE terendah yang didapat dari prediksi curah hujan menggunakan model ini adalah 27.8%.


## 2. KEBUTUHAN PERANGKAT LUNAK
Perangkat lunak yang diperlukan untuk mengoperasikan aplikasi ini adalah sebagai berikut:
1. Windows OS 10/11 64-bit
2. NetBeans IDE versi 14 ke atas
3. Java Development Kit (JDK) versi 20 ke atas


## 3. MENGINSTAL PROYEK
Cara menginstal aplikasi via CLI Git Bash:
+ Buka aplikasi Git Bash
+ Pilih folder lokasi unduhan. Misalnya: `cd d://downloads`
+ Ketik `git clone https://github.com/murafba/Rainfall-Prediction-App.git`
+ Cek folder unduhan lalu buka proyek


## 4. MENJALANKAN APLIKASI
Berikut ini adalah langkah-langkah untuk menjalankan aplikasi:
1. Jalankan NetBeans
2. Buka proyek yang telah diunduh
> [!IMPORTANT]
> Jika JDK *default* yang digunakan di bawah versi 20, maka harus diubah terlebih dahulu. Berikut langkah-langkahnya:
> (a) Klik kanan pada proyek;
> (b) Pilih *properties*;
> (c) Klik menu *Libraries*;
> (d) Pada menu *dropdown* *Java Platform*, pilihi versi JDK 20 ke atas.
3. Buka *package* *gui* dan pilih kelas **Main.java**
4. Jalankan aplikasi


## 5. MENGGUNAKAN APLIKASI
Terdapat 2 kebutuhan fungsional pada sistem aplikasi:

![Diagram Use Case](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Gambar%20IV-10%20Diagram%20Use%20Case.drawio.png?raw=true "Usecase Diagram")
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
This class is an abstract superclass that utilizes to receive input data from the `Main.java` class and then initiates the process of searching for chromosome that can generate membership function boundaries with the best fitness value. The stage starts from initializing the initial population to computing the fitness value.

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
If you found my project useful, you can show you support by attributing to this project and giving it a star on this repository. Alternatively, you can also provide material support through the following links:
- [PayPal](https://paypal.me/murafba)
- [Ko-fi](https://ko-fi.com/murafba)
- [Saweria (for Indonesian)](https://saweria.co/murafba)


## 10. HOW TO CONTRIBUTE
Pull requests are not available. I will provide a dedicated repository related to the research where you can make pull requests on that page. If you have any questions or recommendations, please feel free to do so in the Issues section.


## 11. LICENSE
Copyright &copy; 2023 Muhammad Rafi Akbar
This project is under the [MIT](https://github.com/murafba/Rainfall-Prediction-App/blob/main/LICENSE) License.


## 12. ACKNOWLEDGEMENT
As the developer, I'm aware that the developed application still has some shortcomings. Therefore, constructive criticism and suggestions from colleagues are highly appreciated.
