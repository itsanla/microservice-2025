## 🌐 Service Endpoints

Berikut adalah daftar lengkap 16 domain aktif untuk layanan Perpustakaan, Marketplace, dan Infrastruktur pendukung:

| Domain / Endpoint | Deskripsi |
| :--- | :--- |
| [perpustakaan-gateway.mooo.com](http://perpustakaan-gateway.mooo.com) | **[Perpustakaan]** API Gateway utama untuk akses sistem perpustakaan. |
| [anggota.mooo.com](http://anggota.mooo.com) | **[Perpustakaan]** Service manajemen data anggota. |
| [bukuu.mooo.com](http://bukuu.mooo.com) | **[Perpustakaan]** Service katalog dan stok buku. |
| [peminjamann.mooo.com](http://peminjamann.mooo.com) | **[Perpustakaan]** Service transaksi peminjaman buku. |
| [pengembalian.mooo.com](http://pengembalian.mooo.com) | **[Perpustakaan]** Service transaksi pengembalian & denda. |
| [marketplace-gateway.mooo.com](http://marketplace-gateway.mooo.com) | **[Marketplace]** API Gateway utama untuk akses sistem jual-beli. |
| [pelanggan.mooo.com](http://pelanggan.mooo.com) | **[Marketplace]** Service akun pengguna (penjual/pembeli). |
| [produk.mooo.com](http://produk.mooo.com) | **[Marketplace]** Service manajemen data produk jual. |
| [orderr.mooo.com](http://orderr.mooo.com) | **[Marketplace]** Service keranjang belanja dan pesanan. |
| [jenkinss.mooo.com](http://jenkinss.mooo.com) | **[Infra]** Jenkins CI/CD untuk otomatisasi deployment. |
| [graffana.mooo.com](http://graffana.mooo.com) | **[Infra]** Dashboard monitoring metrics (Prometheus viz). |
| [kibbana.mooo.com](http://kibbana.mooo.com) | **[Infra]** Dashboard logs & searching (ELK Stack). |
| [eurekaa.mooo.com](http://eurekaa.mooo.com) | **[Infra]** Service Discovery & Registry server. |
| [rabbittmq.mooo.com](http://rabbittmq.mooo.com) | **[Infra]** Message Broker Management UI. |
| [dbh2.mooo.com](http://dbh2.mooo.com) | **[DB]** Console H2 Database (Relational). |
| [dbmongo.mooo.com](http://dbmongo.mooo.com) | **[DB]** Console Admin MongoDB (NoSQL). |


## 📦 Service List

Total 20 services yang berjalan di cluster Kubernetes:

| No | Service Name | Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | buku-service | Custom | Service katalog dan stok buku |
| 2 | anggota-service | Custom | Service manajemen data anggota |
| 3 | pengembalian-service | Custom | Service transaksi pengembalian & denda |
| 4 | peminjaman-service | Custom | Service transaksi peminjaman buku |
| 5 | perpustakaan-gateway | Custom | API Gateway sistem perpustakaan |
| 6 | marketplace-gateway | Custom | API Gateway sistem marketplace |
| 7 | produk-service | Custom | Service manajemen data produk |
| 8 | pelanggan-service | Custom | Service akun pengguna marketplace |
| 9 | order-service | Custom | Service keranjang belanja dan pesanan |
| 10 | cqrs | Custom | CQRS Event Sourcing service |
| 11 | eureka-server | Custom | Service Discovery & Registry |
| 12 | rabbitmq | Official | Message Broker (RabbitMQ) |
| 13 | mongodb | Official | NoSQL Database (MongoDB) |
| 14 | mongo-express | Official | MongoDB Admin Console |
| 15 | jenkins | Official | CI/CD Automation Server |
| 16 | prometheus | Official | Metrics Collection & Monitoring |
| 17 | grafana | Official | Metrics Visualization Dashboard |
| 18 | elasticsearch | Official | Search & Analytics Engine |
| 19 | logstash | Official | Log Processing Pipeline |
| 20 | kibana | Official | Log Visualization Dashboard |

**Summary:** 11 Custom Services + 9 Official Images = 20 Total Services
