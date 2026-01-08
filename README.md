## 🌐 Service Endpoints & Domains

Sistem ini terdiri dari 20 microservices yang terbagi menjadi ekosistem **Perpustakaan** dan **Marketplace**, serta didukung oleh layanan infrastruktur terpusat.

### 📚 Ecosystem 1: Perpustakaan Digital
Layanan yang menangani sirkulasi buku dan manajemen anggota perpustakaan.

| Service Name | Domain / Endpoint | Deskripsi |
| :--- | :--- | :--- |
| **API Gateway** | [perpustakaan-gateway.mooo.com](http://perpustakaan-gateway.mooo.com) | Pintu masuk utama (Gateway) untuk semua fitur perpustakaan. |
| **Anggota Service** | [anggota.mooo.com](http://anggota.mooo.com) | Manajemen data anggota dan autentikasi. |
| **Buku Service** | [bukuu.mooo.com](http://bukuu.mooo.com) | Katalog dan inventaris buku. |
| **Peminjaman** | [peminjamann.mooo.com](http://peminjamann.mooo.com) | Transaksi peminjaman buku. |
| **Pengembalian** | [pengembalian.mooo.com](http://pengembalian.mooo.com) | Transaksi pengembalian dan denda. |

### 🛒 Ecosystem 2: Marketplace
Layanan e-commerce untuk jual-beli produk.

| Service Name | Domain / Endpoint | Deskripsi |
| :--- | :--- | :--- |
| **API Gateway** | [marketplace-gateway.mooo.com](http://marketplace-gateway.mooo.com) | Pintu masuk utama (Gateway) untuk fitur marketplace. |
| **Pelanggan Service** | [pelanggan.mooo.com](http://pelanggan.mooo.com) | Manajemen akun user pembeli/penjual. |
| **Produk Service** | [produk.mooo.com](http://produk.mooo.com) | Manajemen katalog produk jual. |
| **Order Service** | [orderr.mooo.com](http://orderr.mooo.com) | Pemrosesan pesanan dan keranjang belanja. |

### 🛠️ Infrastructure & Monitoring Tools
Layanan pendukung untuk CI/CD, database management, dan observability.

| Tool Name | Domain / Endpoint | Fungsi |
| :--- | :--- | :--- |
| **Jenkins** | [jenkinss.mooo.com](http://jenkinss.mooo.com) | CI/CD Pipeline Automation. |
| **Grafana** | [graffana.mooo.com](http://graffana.mooo.com) | Monitoring Dashboard & Visualization. |
| **Kibana** | [kibbana.mooo.com](http://kibbana.mooo.com) | Log Analysis & Searching. |
| **Eureka Server** | [eurekaa.mooo.com](http://eurekaa.mooo.com) | Service Discovery & Registry. |
| **RabbitMQ** | [rabbittmq.mooo.com](http://rabbittmq.mooo.com) | Message Broker Management UI. |
| **H2 Console** | [dbh2.mooo.com](http://dbh2.mooo.com) | Database Admin untuk H2 (Relational). |
| **Mongo Express** | [dbmongo.mooo.com](http://dbmongo.mooo.com) | Database Admin untuk MongoDB (NoSQL). |