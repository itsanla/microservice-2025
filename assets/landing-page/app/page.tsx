const services = [
  { category: "Perpustakaan", items: [
    { url: "https://perpustakaan-gateway.mooo.com", name: "API Gateway", desc: "Gateway utama sistem perpustakaan" },
    { url: "https://anggota.mooo.com", name: "Anggota", desc: "Manajemen data anggota" },
    { url: "https://bukuu.mooo.com", name: "Buku", desc: "Katalog dan stok buku" },
    { url: "https://peminjamann.mooo.com", name: "Peminjaman", desc: "Transaksi peminjaman buku" },
    { url: "https://pengembalian.mooo.com", name: "Pengembalian", desc: "Transaksi pengembalian & denda" },
  ]},
  { category: "Marketplace", items: [
    { url: "https://marketplace-gateway.mooo.com", name: "API Gateway", desc: "Gateway utama sistem jual-beli" },
    { url: "https://pelanggan.mooo.com", name: "Pelanggan", desc: "Akun pengguna (penjual/pembeli)" },
    { url: "https://produk.mooo.com", name: "Produk", desc: "Manajemen data produk jual" },
    { url: "https://orderr.mooo.com", name: "Order", desc: "Keranjang belanja dan pesanan" },
  ]},
  { category: "Infrastructure", items: [
    { url: "https://jenkinss.mooo.com", name: "Jenkins", desc: "CI/CD otomatisasi deployment" },
    { url: "https://graffana.mooo.com", name: "Grafana", desc: "Dashboard monitoring metrics" },
    { url: "https://kibbana.mooo.com", name: "Kibana", desc: "Dashboard logs & searching" },
    { url: "https://eurekaa.mooo.com", name: "Eureka", desc: "Service Discovery & Registry" },
    { url: "https://rabbittmq.mooo.com", name: "RabbitMQ", desc: "Message Broker Management" },
  ]},
  { category: "Database", items: [
    { url: "https://dbh2.mooo.com", name: "H2 Console", desc: "Console H2 Database (Relational)" },
    { url: "https://dbmongo.mooo.com", name: "MongoDB", desc: "Console Admin MongoDB (NoSQL)" },
  ]},
];

export default function Home() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-900 via-slate-800 to-slate-900 text-white">
      <main className="container mx-auto px-4 py-12 max-w-7xl">
        <div className="text-center mb-12">
          <h1 className="text-4xl font-bold mb-3">Microservice Dashboard</h1>
          <p className="text-slate-400">16 Active Service Endpoints</p>
        </div>
        
        <div className="space-y-8">
          {services.map((section) => (
            <div key={section.category}>
              <h2 className="text-xl font-semibold mb-4 text-blue-400">{section.category}</h2>
              <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
                {section.items.map((service) => (
                  <a
                    key={service.url}
                    href={service.url}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="block p-5 bg-slate-800/50 border border-slate-700 rounded-lg hover:bg-slate-700/50 hover:border-blue-500 transition-all"
                  >
                    <h3 className="font-semibold text-lg mb-1">{service.name}</h3>
                    <p className="text-sm text-slate-400 mb-3">{service.desc}</p>
                    <p className="text-xs text-blue-400 truncate">{service.url}</p>
                  </a>
                ))}
              </div>
            </div>
          ))}
        </div>
      </main>
    </div>
  );
}
