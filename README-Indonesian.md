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
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#1-pendahuluan">1. PENDAHULUAN</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#2-kebutuhan-perangkat-lunak--perangkat-keras">2. KEBUTUHAN PERANGKAT LUNAK & PERANGKAT KERAS</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#3-menginstal-proyek">3. MENGINSTAL PROYEK</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#4-menjalankan-aplikasi">4. MENJALANKAN APLIKASI</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#5-menggunakan-aplikasi">5. MENGGUNAKAN APLIKASI</a>
      <ul>
        <li><a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#51-prediksi-curah-hujan-dari-dataset">5.1 Prediksi Curah Hujan dari Dataset</a></li>
        <li><a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#52-prediksi-curah-hujan-dari-data-baru">5.2 Prediksi Curah Hujan dari Data Baru</a></li>
      </ul>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#6-implementasi-kelas">6. IMPLEMENTASI KELAS</a>
      <ul>
        <li><a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#61-geneticalgorithmjava">6.1 GeneticAlgorithm.java</a></li>
        <li><a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#62-fistsukamotojava">6.2 FISTsukamoto.java</a></li>
        <li><a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#63-mainjava">6.3 Main.java</a></li>
      </ul>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#7-tentang-penelitian">7. TENTANG PENELITIAN</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#8-tentang-penulis">8. TENTANG PENULIS</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#9-berikan-dukungan">9. BERIKAN DUKUNGAN</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#10-cara-berkontribusi">10. CARA BERKONTRIBUSI</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#11-lisensi">11. LISENSI</a>
    </li>
    <li>
      <a href="https://github.com/murafba/Rainfall-Prediction-App/blob/main/README-Indonesian.md#12-pengakuan">12. PENGAKUAN</a>
    </li>
  </ul>
</details>


## 1. PENDAHULUAN
Rainfall Prediction App adalah aplikasi berbasis desktop yang dikembangkan untuk memprediksi curah hujan di Kabupaten Banyuasin, Sumatera Selatan, Indonesia. Pengembangan aplikasi ini merupakan syarat wajib untuk mendapatkan gelar Sarjana Komputer dari Jurusan Informatika, Universitas Sriwijaya. Model prediksi pada aplikasi ini menggunakan *Fuzzy Inference System* (FIS) Tsukamoto yang dioptimasi dengan Algoritma Genetika (GA). Berdasarkan penelitian yang telah dilakukan, nilai MAPE terendah yang didapat dari prediksi curah hujan menggunakan model ini adalah 27.8%. Data yang digunakan pada penelitian ini adalah data iklim bulanan Kabupaten Banyuasin dari Januari 2018 hingga Desember 2022. Data iklim terdiri dari atribut suhu, kelembapan, tekanan udara, kecepatan angin, dan curah hujan.


## 2. KEBUTUHAN PERANGKAT LUNAK & PERANGKAT KERAS
Perangkat keras yang diperlukan untuk mengoperasikan aplikasi ini adalah sebagai berikut:
1. Laptop atau PC dengan resolusi *display* minimum 1280 * 720

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
3. Buka *package* *gui* dan pilih kelas `Main.java`
4. Jalankan aplikasi


## 5. MENGGUNAKAN APLIKASI
Terdapat 2 kebutuhan fungsional pada sistem aplikasi:

![Diagram Use Case](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Use%20Case%20Diagram%20-%20Bahasa.drawio.png?raw=true "Usecase Diagram")
*Gambar 1. Diagram Use Case*

### 5.1 Prediksi Curah Hujan dari Dataset
Use case 1 terimplementasi pada halaman *Home*. Halaman ini digunakan untuk melakukan penelitian terhadap prediksi curah hujan untuk mencari nilai MAPE terendah dengan menggunakan parameter-parameter algoritma genetika: generation size, population size, crossover rate, dan mutation rate. Setelah itu, klik tombol untuk menjalankan program.

> [!CAUTION]
> Nilai input untuk parameter crossover rate dan mutation rate harus bernilai dari 0 hingga 1! Jika tidak, program akan menampilkan pesan eror. Selain itu, setiap parameter hanya dapat menerima input data numerik.

Di bawah ini merupakan tampilan halaman *Home*, diagram aktivitas dan sequence:

![Halaman Home](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Screenshot%202023-12-28%20224822.png?raw=true "Halaman Home")
*Gambar 2. Halaman Home*

![Diagram Aktivitas 1](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Activity%20Diagram%201%20-%20Indonesia.drawio.png?raw=true "Diagram Aktivitas 1")
*Gambar 3. Diagram Aktivitas*

![Sequence Diagram 1](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Diagram%20Sequence%20Prediksi%20Curah%20hujan%20dari%20Data%20Set.drawio.png?raw=true "Diagram Sequence 1")
*Gambar 4. Diagram Sequence 1*

### 5.2 Prediksi Curah Hujan dari Data Baru
Use case 2 terimplementasi dalam halaman *Manual Forecasting*. Halaman ini digunakan untuk memprediksi nilai curah hujan dari data baru. Batasan fungsi keanggotaan *fuzzy* yang digunakan diambil dari kromosom yang memiliki nilai MAPE terendah berdasarkan penelitian yang dilakukan yakni 27.8%. Setelah menginput nilai-nilai atribut suhu, kelembapan udara, tekanan udara, dan kecepatan angin, lalu klik tombol. Tampilan halaman ditunjukkan pada gambar berikut.

![Halaman Manual Forecasting](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Screenshot%202023-12-29%20232823.png?raw=true "Halaman Manual Forecasting")
*Gambar 5. Halaman Manual Forecasting*

![Diagram Aktivitas 2](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Activity%20Diagram%202%20-%20Indonesia.drawio.png?raw=true "Diagram Aktivitas 2")
*Gambar 6. Diagram Aktivitas*

![Diagram Sequence 2](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Diagram%20Sequence%20Prediksi%20Curah%20hujan%20dari%20Data%20Baru.drawio.png?raw=true "Sequence Diagram 2")
*Gambar 7. Diagram Sequence*


## 6. IMPLEMENTASI KELAS

### 6.1 `GeneticAlgorithm.java`
Kelas ini merupakan *superclass* abstrak yang digunakan untuk menerima data input dari kelas `Main.java` lalu memulai proses pencarian kromosom yang dapat membangkitkan batasan fungsi keanggotaan dengan nilai *fitness* terbaik. Tahapan dimulai dari menginisialisasikan populasi awal hingga perhitungan nilai *fitness*.

### 6.2 `FISTsukamoto.java`
Kelas ini merupakan *subclass* dari kelas `GeneticAlgorithm.java` yang digunakan untuk menghitung nilai curah hujan menggunakan metode FIS Tsukamoto, dari halaman *Home* dan halaman *Manual Forecasting*. Kelas ini juga menghitung nilai Absolute Percentage Error (APE) dan Mean Absolute Percentage Error (MAPE), lalu mengembalikan nilai ke *superclass*-nya. Proses diawali dengan *fuzzification*, implikasi basis aturan *fuzzy*, *deffuzzification*, hingga evaluasi nilai MAPE.

### 6.3 `Main.java`
Kelas ini merepresentasikan GUI aplikasi yang terdiri dari halaman *Home* dan halaman *Manual Forecasting*.

Berikut adalah diagram kelas aplikasi:

![Diagram Kelas](https://github.com/murafba/Rainfall-Prediction-App/blob/main/src/gui/Diagram%20Kelas.drawio.png?raw=true "Diagram Kelas")
*Gambar 8. Diagram Kelas*


## 7. TENTANG PENELITIAN
Jika Anda tertarik pada penelitian saya, Anda dapat membacanya pada pranala berikut.
- Skripsi: [OPTIMASI FIS TSUKAMOTO DALAM MEMPREDIKSI CURAH HUJAN DI KABUPATEN BANYUASIN MENGGUNAKAN ALGORITMA GENETIKA](http://repository.unsri.ac.id/137165/)


## 8. TENTANG PENULIS
> Hai! Nama saya Muhammad Rafi Akbar. Anda dapat memanggil saya Rafi. Saya adalah seorang sarjana komputer dari [Universitas Sriwijaya](https://unsri.ac.id), jurusan [Informatika](https://if.ilkom.unsri.ac.id). Saya memiliki ketertarikan di bidang *fuzzy logic* (logika samar) dan kriptografi. Anda dapat menghubungi saya melalui akun-akun berikut.
- [LinkedIn](https://linkedin.com/in/murafba)
- [GitHub](https://github.com/murafba)


## 9. BERIKAN DUKUNGAN
Apabila menurut Anda proyek saya cukup bermanfaat, Anda dapat memberikan dukungan dengan cara memberikan atribusi pada proyek ini dan memberikan bintang pada repositori ini. Selain itu, Anda juga dapat memberikan dukungan secara materi melalui pranala berikut.
- [PayPal](https://paypal.me/murafba)
- [Saweria](https://saweria.co/murafba)


## 10. CARA BERKONTRIBUSI
Pull requests tidak tersedia. Saya akan menyediakan repositori khusus terkait penelitian saya dan Anda dapat melakukan *pull requests* pada halaman tersebut. Jika Anda memiliki pertanyaan atau rekomendasi, jangan ragu untuk mengirimnya pada bagian Issues.


## 11. LISENSI
Copyright &copy; 2023 Muhammad Rafi Akbar <br>
Proyek ini berada di bawah lisensi [MIT](https://github.com/murafba/Rainfall-Prediction-App/blob/main/LICENSE).


## 12. PENGAKUAN
Saya selaku pengembang menyadari bahwa aplikasi yang dikembangkan masih terdapat kekurangan. Oleh karena itu, kritik dan saran yang membangun dari rekan-rekan sekalian sangat diperlukan.
