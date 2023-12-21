import pandas as pd
import pandas as pd
import nltk
from datetime import datetime
from itertools import combinations
from nltk.corpus import stopwords
from collections import defaultdict
from nltk.tokenize import word_tokenize
from Sastrawi.StopWordRemover.StopWordRemoverFactory import StopWordRemoverFactory
from Sastrawi.Stemmer.StemmerFactory import StemmerFactory
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

data_tukang = pd.read_csv('data/data_tukang.csv')
data_user = pd.read_csv('data/data_user.csv')

tf_user = data_user.loc[:, ['Nama','Daerah','Order', 'Rating', 'Keterangan', 'Timestamp']]
tf_user = tf_user.rename(columns={'Nama': 'Nama User', 'Rating': 'User Rating'})

tf_tukang = data_tukang.loc[:, ['Nama','Daerah','Tempat Pekerjaan','Keahlian', 'Harga', 'Rating', 'Pengrating']]
tf_tukang = tf_tukang.rename(columns={'Nama': 'Nama Tukang', 'Tempat Pekerjaan': 'Order','Rating': 'Rating Tukang', 'Pengrating': 'Jumlah Rating'})


# Data keywords
keywords = [
    'Tukang Bangunan dan Pengecetan', 'Instalasi Listrik', 'Tukang Kayu', 'Tukang AC', 'Instalasi Pipa',
    'Tukang Aluminium', 'Instalasi Pipa', 'Perpipaan Gas', 'Listrik Rumah Tangga',
    'Design Logo', 'Design dan Cetak', 'Design dan Fotografi', 'Foto dan Videografi'
]

# Fungsi untuk mengelompokkan order berdasarkan kata kunci
def group_orders_by_keyword(order):
    for keyword in keywords:
        if keyword in order:
            return keyword
    # return 'Other'  # Jika tidak cocok dengan keyword apapun, masukkan ke grup "Other"

# Menambahkan kolom 'Keyword' ke DataFrame
tf_user['Keyword'] = tf_user['Order'].apply(group_orders_by_keyword)
# Menambahkan kolom 'Row Number' yang berisi nomor baris sebelumnya
tf_user['Row Number'] = tf_user.index + 1  # Nomor baris dimulai dari 1, bukan 0
# Melakukan pengelompokkan berdasarkan user dan keyword
grouped_data = tf_user.groupby(['Nama User', 'Keyword', 'Order'])['Row Number'].apply(list).reset_index(name='Row Numbers')
# Filter data hanya untuk yang memiliki lebih dari 1 pesanan
filtered_data = grouped_data[grouped_data['Row Numbers'].apply(len) > 1]

# Ambil kolom 'Row Numbers' dari filtered_data
row_numbers = filtered_data['Row Numbers']
# Membuat dictionary kosong untuk menyimpan set kata yang ditemukan berdasarkan indeks
kata_per_indeks = {}

# Melakukan perulangan untuk setiap indeks pada kolom 'Row Numbers'
for i, indices in enumerate(row_numbers, start=1):
    kata_yang_sama = set()  # Inisialisasi set kata untuk setiap iterasi
    rows_with_same_words = set()  # Inisialisasi set untuk menyimpan nomor baris yang memiliki kata yang sama
    for index in indices:
        # Mendapatkan nilai 'keterangan' pada tf_user berdasarkan indeks dari 'Row Numbers'
        keterangan = tf_user.loc[index-1, 'Keterangan']
        # Tokenisasi kata dalam 'keterangan'
        kata = word_tokenize(keterangan.lower())  # Ubah ke huruf kecil dan tokenisasi kata
        # Tambahkan kata ke set kata_yang_sama
        kata_yang_sama.update(kata)
        rows_with_same_words.add(index)  # Tambahkan nomor baris yang memiliki kata yang sama
    # Simpan set kata yang ditemukan ke dalam dictionary berdasarkan indeksnya beserta data barisnya
    kata_per_indeks[f"Set_{i}"] = {'Kata yang Sama': list(kata_yang_sama), 'Rows': list(rows_with_same_words)}
# Mengumpulkan informasi ke dalam DataFrame
data = []
for key, value in kata_per_indeks.items():
    for index, row in tf_user.iterrows():
        if index+1 in value['Rows']:
          data.append([row['Nama User'], value['Kata yang Sama'], value['Rows']])

# Membuat DataFrame dari data yang terkumpul
df = pd.DataFrame(data, columns=['Nama User', 'Kata Sama', 'Row Numbers'])
# Convert lists in 'Kata Sama' and 'Row Numbers' columns to tuples
df['Kata Sama'] = df['Kata Sama'].apply(tuple)
df['Row Numbers'] = df['Row Numbers'].apply(tuple)

# Drop duplicates based on columns 'Nama User', 'Kata Sama', and 'Row Numbers'
df_filtered = df.drop_duplicates(subset=['Nama User', 'Kata Sama', 'Row Numbers'])

# Membuat dictionary kosong untuk menyimpan nilai mutlak dari selisih waktu
absolute_time_diffs_dict = {}

# Melakukan perulangan untuk setiap kelompok 'Row Numbers'
for i, indices in enumerate(df_filtered['Row Numbers'], start=1):
    # Menghasilkan kombinasi unik dari indeks dalam setiap kelompok 'Row Numbers'
    index_combinations = combinations(indices, 2)  # Mengambil kombinasi 2 indeks untuk mendapatkan selisih waktu

    # Inisialisasi list untuk menyimpan nilai mutlak dari selisih waktu dalam detik
    absolute_time_diffs = []

    # Iterasi melalui setiap kombinasi indeks
    for index_pair in index_combinations:
        # Mendapatkan timestamp dari tf_user berdasarkan indeks
        timestamp1 = datetime.strptime(tf_user.loc[index_pair[0] - 1, 'Timestamp'], '%d/%m/%Y')
        timestamp2 = datetime.strptime(tf_user.loc[index_pair[1] - 1, 'Timestamp'], '%d/%m/%Y')

        # Menghitung nilai mutlak dari selisih waktu dalam detik
        abs_diff_seconds = abs((timestamp2 - timestamp1).total_seconds())
        absolute_time_diffs.append(abs_diff_seconds)  # Menyimpan nilai mutlak dari selisih waktu dalam detik

    # Simpan nilai mutlak dari selisih waktu ke dalam dictionary berdasarkan indeks kelompoknya
    absolute_time_diffs_dict[f"Set_{i}"] = absolute_time_diffs

# Menampilkan nilai mutlak dari selisih waktu antar indeks untuk setiap kelompok
for key, value in absolute_time_diffs_dict.items():
    print(f"Kelompok {key}: Nilai Mutlak Selisih Waktu: {value}")

# Membuat dictionary kosong untuk menyimpan tanggal maksimal dari setiap kelompok indeks
tanggal_maksimal_per_kelompok = {}

# Melakukan perulangan untuk setiap kelompok 'Row Numbers'
for i, indices in enumerate(df_filtered['Row Numbers'], start=1):
    # Inisialisasi variabel untuk menyimpan tanggal maksimal dalam satu kelompok
    tanggal_maksimal = None

    # Iterasi melalui setiap indeks dalam kelompok
    for index in indices:
        # Mendapatkan timestamp dari tf_user berdasarkan indeks
        timestamp = datetime.strptime(tf_user.loc[index - 1, 'Timestamp'], '%d/%m/%Y')

        # Membandingkan dan mengupdate tanggal maksimal
        if tanggal_maksimal is None or timestamp > tanggal_maksimal:
            tanggal_maksimal = timestamp

    # Simpan tanggal maksimal ke dalam dictionary berdasarkan kelompoknya
    tanggal_maksimal_per_kelompok[f"Set_{i}"] = tanggal_maksimal

# Membuat DataFrame dari tanggal_maksimal_per_kelompok
df_tanggal_maksimal = pd.DataFrame({
    'Nama User': df_filtered['Nama User'],
    'Tanggal Maksimal': [value.strftime('%d/%m/%Y') if value else 'Data tidak tersedia' for value in tanggal_maksimal_per_kelompok.values()]
})

# Membuat dictionary kosong untuk menyimpan nilai mutlak dari selisih waktu berdasarkan kata yang sama
absolute_time_diffs_per_word = defaultdict(list)

# Iterasi untuk setiap baris dalam df_filtered
for index, row in df_filtered.iterrows():
    # Mendapatkan kata yang sama dari baris saat ini sebagai satu kesatuan
    kata_sama = row['Kata Sama']

    # Menghasilkan kombinasi unik dari indeks dalam setiap kelompok 'Row Numbers'
    index_combinations = combinations(row['Row Numbers'], 2)  # Mengambil kombinasi 2 indeks untuk mendapatkan selisih waktu

    # Inisialisasi list untuk menyimpan nilai mutlak dari selisih waktu dalam detik
    absolute_time_diffs = []

    # Iterasi melalui setiap kombinasi indeks
    for index_pair in index_combinations:
        # Mendapatkan timestamp dari tf_user berdasarkan indeks
        timestamp1 = datetime.strptime(tf_user.loc[index_pair[0] - 1, 'Timestamp'], '%d/%m/%Y')
        timestamp2 = datetime.strptime(tf_user.loc[index_pair[1] - 1, 'Timestamp'], '%d/%m/%Y')

        # Menghitung nilai mutlak dari selisih waktu dalam detik
        abs_diff_seconds = abs((timestamp2 - timestamp1).total_seconds())
        absolute_time_diffs.append(abs_diff_seconds)  # Menyimpan nilai mutlak dari selisih waktu dalam detik

    # Menambahkan nilai mutlak dari selisih waktu ke dalam dictionary berdasarkan kata yang sama
    for word in kata_sama:
        absolute_time_diffs_per_word[word].extend(absolute_time_diffs)

# Menampilkan nilai mutlak dari selisih waktu antar indeks untuk setiap kata yang sama
for word, time_diffs in absolute_time_diffs_per_word.items():
    print(f"Kata Sama: {word}, Nilai Mutlak Selisih Waktu: {time_diffs}")

# Buat dictionary kosong untuk menyimpan rata-rata selisih waktu per kata
average_time_diffs_per_word = {}
# Hitung rata-rata dari nilai mutlak selisih waktu untuk setiap kata yang sama
for word, time_diffs in absolute_time_diffs_per_word.items():
    average_time_diffs_per_word[word] = sum(time_diffs) / len(time_diffs)
# Buat DataFrame dari dictionary average_time_diffs_per_word
df_average_time_diffs = pd.DataFrame(list(average_time_diffs_per_word.items()), columns=['Kata Sama', 'Selisih Waktu'])
df_average_time_diffs['Cluster']=df_average_time_diffs.index

# Download stopwords jika belum
nltk.download('stopwords')

# Fungsi untuk membersihkan teks
def preprocess_text(text):
    stop_words = set(stopwords.words('indonesian'))
    words = nltk.word_tokenize(text.lower())
    return ' '.join([word for word in words if word.isalpha() and word not in stop_words])

# Membersihkan teks di kolom 'keterangan' dan 'kata sama'
tf_user['Cleaned_Keterangan'] = tf_user['Keterangan'].apply(preprocess_text)
df_average_time_diffs['Cleaned_KataSama'] = df_average_time_diffs['Kata Sama'].apply(preprocess_text)

# Menginisialisasi objek TfidfVectorizer
tfidf_vectorizer = TfidfVectorizer()

# Menghitung vektor TF-IDF untuk kolom 'Cleaned_Keterangan' dan 'Cleaned_KataSama'
tfidf_keterangan = tfidf_vectorizer.fit_transform(tf_user['Cleaned_Keterangan'])
tfidf_kata_sama = tfidf_vectorizer.transform(df_average_time_diffs['Cleaned_KataSama'])

# Menghitung cosine similarity antara matriks TF-IDF
cosine_sim = cosine_similarity(tfidf_keterangan, tfidf_kata_sama)

# Dapatkan indeks kata yang paling mirip untuk setiap kalimat dari tf_user
indices = cosine_sim.argmax(axis=1)

# Buat kolom baru di tf_user untuk indeks kata yang paling mirip
tf_user['Most_Similar_Index'] = indices

# Tampilkan hasil
print(tf_user[['Keterangan', 'Most_Similar_Index']])

# Membuat DataFrame contoh tf_user dan df_average_time_diffs
# Silakan gantikan ini dengan definisi sebenarnya dari tf_user dan df_average_time_diffs

# Mengkonversi kolom 'Timestamp' ke tipe data datetime
tf_user['Timestamp'] = pd.to_datetime(tf_user['Timestamp'])

# Fungsi untuk menambahkan waktu berdasarkan selisih waktu dari df_average_time_diffs
def add_time_by_cluster(row):
    cluster = row['Cluster']
    time_diff_seconds = df_average_time_diffs[df_average_time_diffs['Cluster'] == cluster]['Selisih Waktu'].values[0]
    time_diff = pd.Timedelta(seconds=time_diff_seconds)
    new_time = row['Timestamp'] + time_diff
    return new_time

# Menambahkan kolom baru 'Time Recomendation' dengan timestamp yang sudah ditambahkan waktu
tf_user['Time Recomendation'] = tf_user.apply(add_time_by_cluster, axis=1)

tf_user = tf_user.loc[:, ['Nama User','Daerah','Order', 'Timestamp', 'Time Recomendation']]

# Memasukkan nama dari user
nama_user = input("Masukkan nama user: ")

# Mencocokkan nama user
filtered_data = tf_user[tf_user['Nama User'] == nama_user]

# Mengurutkan hasil berdasarkan 'Time Recommendation' secara descending
filtered_data = filtered_data.sort_values(by='Time Recomendation', ascending=False)

# Mendapatkan tanggal hari ini
today = datetime.now().date()

# Memfilter 'Time Recommendation' setelah hari ini
filtered_data = filtered_data[pd.to_datetime(filtered_data['Time Recomendation']).dt.date > today]
filtered_data
