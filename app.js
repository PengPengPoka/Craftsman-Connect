require('dotenv').config();

const express = require('express');
const bodyParser = require('body-parser');
const jwt = require('jsonwebtoken');

const app = express();
const port = 3000;

app.use(bodyParser.json());

const mysql = require('mysql2/promise');

const pool = mysql.createPool({
  host: process.env.DB_HOST,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_DATABASE
});

// Endpoint untuk registrasi pengguna
app.post('/register', async (req, res) => {
    const { nama, email, noHp, password } = req.body;
  
    try {
      // Periksa apakah pengguna sudah terdaftar
      const [userExistRows] = await pool.execute('SELECT * FROM users WHERE nama = ?', [nama]);
  
      if (userExistRows.length > 0) {
        return res.status(400).json({ message: 'Username sudah digunakan' });
      }
  
      // Tambahkan pengguna baru ke database
      const [insertedUserRows] = await pool.execute('INSERT INTO users (nama, email, noHp, password) VALUES (?, ?, ?, ?)', [nama, email, noHp, password]);
  
      const user = insertedUserRows;
  
      // Generate token JWT
      const token = jwt.sign({ userId: user.insertId }, process.env.JWT_SECRET);
  
      res.json({ message: 'Registrasi berhasil', token });
    } catch (error) {
      console.error('Error during registration:', error);
      res.status(500).json({ message: 'Terjadi kesalahan saat registrasi' });
    }
  });

// Endpoint untuk login pengguna
app.post('/login', async (req, res) => {
    const { email, password } = req.body;
  
    try {
      // Periksa apakah pengguna ada dalam database
      const userQuery = 'SELECT * FROM users WHERE email = ? AND password = ?';
      const [userResult] = await pool.query(userQuery, [email, password]);
  
      if (userResult.length === 0) {
        return res.status(401).json({ message: 'Username atau password salah' });
      }
  
      const user = userResult[0];
  
      // Generate token JWT
      const token = jwt.sign({ userId: user.id }, 'rahasia');
  
      res.json({ message: 'Login berhasil', token });
    } catch (error) {
      console.error('Error during login:', error);
      res.status(500).json({ message: 'Terjadi kesalahan saat login' });
    }
  });

// Endpoint untuk menambahkan tukang tanpa token
app.post('/tukang', async (req, res) => {
    const { nama, layanan, alamat, deskripsi ,telepon } = req.body;
  
    try {
      // Tambahkan tukang baru ke database
      const [insertedTukangRows] = await pool.execute('INSERT INTO tukang (nama, layanan, alamat, deskripsi, telepon) VALUES (?, ?, ?, ?, ?)', [nama, layanan, alamat, deskripsi, telepon]);
  
      const tukang = insertedTukangRows;
  
      res.json({ success: true, message: 'Tukang berhasil ditambahkan', tukang });
    } catch (error) {
      console.error('Error adding tukang:', error);
      res.status(500).json({ message: 'Terjadi kesalahan saat menambahkan tukang' });
    }
  });
  
  // Endpoint untuk menampilkan daftar tukang
app.get('/tukang', async (req, res) => {
    try {
      // Ambil daftar tukang dari database
      const [tukangResult] = await pool.execute('SELECT * FROM tukang');
      const tukangList = tukangResult;
  
      res.json({ success: true, message: 'Daftar tukang berhasil diambil', tukangList });
    } catch (error) {
      console.error('Error fetching tukang list:', error);
      res.status(500).json({ message: 'Terjadi kesalahan saat mengambil daftar tukang' });
    }
  });
  
  
// Fungsi middleware untuk otentikasi token JWT
function authenticateToken(req, res, next) {
    const token = req.headers['authorization'];
  
    if (!token) {
      return res.status(401).json({ message: 'Token tidak ada' });
    }
  
    jwt.verify(token, 'rahasia', (err, user) => {
      if (err) {
        return res.status(403).json({ message: 'Token tidak valid' });
      }
  
      req.user = user;
      next();
    });
}

app.listen(port, () => {
  console.log(`Server berjalan di http://localhost:${port}`);
});
