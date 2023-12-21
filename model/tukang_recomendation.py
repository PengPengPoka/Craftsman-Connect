import pandas as pd
from sklearn.neighbors import NearestNeighbors

data_tukang = pd.read_csv('data/data_tukang.csv')
data_user = pd.read_csv('data/data_user.csv')

tf_user = data_user.loc[:, ['Nama','Daerah','Order', 'Rating', 'Keterangan', 'Timestamp']]
tf_user = tf_user.rename(columns={'Nama': 'Nama User', 'Rating': 'User Rating'})

tf_tukang = data_tukang.loc[:, ['Nama','Daerah','Tempat Pekerjaan','Keahlian', 'Harga', 'Rating', 'Pengrating']]
tf_tukang = tf_tukang.rename(columns={'Nama': 'Nama Tukang', 'Tempat Pekerjaan': 'Order','Rating': 'Rating Tukang', 'Pengrating': 'Jumlah Rating'})

def cari_tukang(nama_user, kebutuhan, tf_user, tf_tukang):
    # Mengambil daerah dari data berdasarkan nama pengguna
    lokasi_user = tf_user.loc[tf_user['Nama User'] == nama_user, 'Daerah'].values[0]

    # Menggunakan pemrosesan teks sederhana untuk pencocokan kebutuhan pengguna
    matching_jobs = tf_tukang[tf_tukang['Keahlian'].str.contains(kebutuhan, case=False)]

    # Memfilter berdasarkan lokasi (daerah dari data pengguna)
    matching_jobs = matching_jobs[matching_jobs['Daerah'] == lokasi_user]

    # Fitur yang akan digunakan untuk peringkat
    features = matching_jobs[['Rating Tukang', 'Jumlah Rating','Harga']]

    # Inisialisasi model KNN
    knn = NearestNeighbors(n_neighbors=len(matching_jobs))  # Jumlah tetangga = jumlah tukang yang cocok

    # Latih model dengan fitur yang dipilih
    knn.fit(features)

    # Mendapatkan indeks tukang yang cocok
    _, indices = knn.kneighbors(features)

    # Menampilkan hasil berdasarkan peringkat dari model
    hasil_terurut = matching_jobs.iloc[indices[0]]  # Menggunakan indeks dari model KNN

    # Mengurutkan hasil berdasarkan Rating Tukang secara descending
    hasil_terurut = hasil_terurut.sort_values(by='Rating Tukang', ascending=False)

    return hasil_terurut

# Input dari pengguna
nama_user = input("Masukkan nama Anda: ")
kebutuhan_user = input("Masukkan jenis pekerjaan yang dibutuhkan: ")

# Memanggil fungsi untuk mencari tukang
hasil_pencarian = cari_tukang(nama_user, kebutuhan_user, tf_user, tf_tukang)

# Menampilkan hasil tukang yang paling sesuai dengan urutan rating descending
print("Berikut adalah tukang yang paling sesuai dengan kebutuhan Anda di", hasil_pencarian['Daerah'].iloc[0], ":")
hasil_pencarian[['Nama Tukang', 'Daerah', 'Order', 'Keahlian', 'Harga', 'Rating Tukang', 'Jumlah Rating']]