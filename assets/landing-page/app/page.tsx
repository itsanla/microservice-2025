const services = [
  { category: "Service Perpustakaan", items: [
    { url: "https://perpustakaan-gateway.mooo.com", name: "API Gateway", desc: "Gateway utama sistem perpustakaan", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://anggota.mooo.com", name: "Anggota", desc: "Manajemen data anggota", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://bukuu.mooo.com", name: "Buku", desc: "Katalog dan stok buku", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://peminjamann.mooo.com", name: "Peminjaman", desc: "Transaksi peminjaman buku", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://pengembalian.mooo.com", name: "Pengembalian", desc: "Transaksi pengembalian & denda", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
  ]},
  { category: "Service Marketplace", items: [
    { url: "https://marketplace-gateway.mooo.com", name: "API Gateway", desc: "Gateway utama sistem jual-beli", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://pelanggan.mooo.com", name: "Pelanggan", desc: "Akun pengguna (penjual/pembeli)", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://produk.mooo.com", name: "Produk", desc: "Manajemen data produk jual", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://orderr.mooo.com", name: "Order", desc: "Keranjang belanja dan pesanan", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
  ]},
  { category: "Service Infrastructure", items: [
    { url: "https://jenkinss.mooo.com", name: "Jenkins", desc: "CI/CD otomatisasi deployment", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://graffana.mooo.com", name: "Grafana", desc: "Dashboard monitoring metrics", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://kibbana.mooo.com", name: "Kibana", desc: "Dashboard logs & searching", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://eurekaa.mooo.com", name: "Eureka", desc: "Service Discovery & Registry", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://rabbittmq.mooo.com", name: "RabbitMQ", desc: "Message Broker Management", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
  ]},
  { category: "Service Database", items: [
    { url: "https://dbh2.mooo.com", name: "H2 Console", desc: "Console H2 Database (Relational)", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
    { url: "https://dbmongo.mooo.com", name: "MongoDB", desc: "Console Admin MongoDB (NoSQL)", img: "https://i.pinimg.com/736x/6f/72/49/6f724978ad99487e8e069315ce1ed782.jpg" },
  ]},
];

const dockerImages = [
  { category: "Images Perpustakaan", items: [
    { url: "https://hub.docker.com/r/itsanla/perpustakaan-gateway", name: "Perpustakaan Gateway" },
    { url: "https://hub.docker.com/r/itsanla/anggota", name: "Anggota" },
    { url: "https://hub.docker.com/r/itsanla/buku", name: "Buku" },
    { url: "https://hub.docker.com/r/itsanla/peminjaman", name: "Peminjaman" },
    { url: "https://hub.docker.com/r/itsanla/pengembalian", name: "Pengembalian" },
  ]},
  { category: "Images Marketplace", items: [
    { url: "https://hub.docker.com/r/itsanla/marketplace-gateway", name: "Marketplace Gateway" },
    { url: "https://hub.docker.com/r/itsanla/pelanggan", name: "Pelanggan" },
    { url: "https://hub.docker.com/r/itsanla/produk", name: "Produk" },
    { url: "https://hub.docker.com/r/itsanla/order", name: "Order" },
  ]},
  { category: "Images Infrastructure", items: [
    { url: "https://hub.docker.com/r/itsanla/eureka", name: "Eureka" },
    { url: "https://hub.docker.com/r/itsanla/cqrs", name: "CQRS" },
    { url: "https://hub.docker.com/_/rabbitmq", name: "RabbitMQ" },
    { url: "https://hub.docker.com/_/mongo", name: "MongoDB" },
    { url: "https://hub.docker.com/_/mongo-express", name: "Mongo Express" },
    { url: "https://hub.docker.com/r/jenkins/jenkins", name: "Jenkins" },
    { url: "https://hub.docker.com/r/elastic/elasticsearch", name: "Elasticsearch" },
    { url: "https://hub.docker.com/r/elastic/logstash", name: "Logstash" },
    { url: "https://hub.docker.com/r/elastic/kibana", name: "Kibana" },
    { url: "https://hub.docker.com/r/grafana/grafana", name: "Grafana" },
    { url: "https://hub.docker.com/r/prom/prometheus", name: "Prometheus" },
  ]},
];

export default function Home() {
  return (
    <div className="min-h-screen bg-white">
      <nav className="bg-white shadow-sm sticky top-0 z-50">
        <div className="container mx-auto px-6 max-w-7xl">
          <div className="flex justify-between items-center h-20">
            <div className="flex items-center gap-2">
              <div className="w-10 h-10 rounded-lg flex items-center justify-center overflow-hidden">
                <img src="https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_235,h_235/https://www.pnp.ac.id/wp-content/uploads/2025/01/LOGO-PNP.png" alt="Logo" className="w-full h-full object-cover" />
              </div>
              <span className="text-xl font-bold text-gray-900">Microservices</span>
            </div>
            
            <div className="flex items-center gap-1">
              {dockerImages.map((section) => (
                <div key={section.category} className="relative group">
                  <button className="px-4 py-2 text-gray-700 hover:text-orange-600 hover:bg-orange-50 rounded-lg font-medium transition-colors">
                    {section.category}
                  </button>
                  <div className="absolute left-0 mt-1 w-56 bg-white border border-gray-100 rounded-xl shadow-xl opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all">
                    <div className="py-2">
                      {section.items.map((item) => (
                        <a
                          key={item.url}
                          href={item.url}
                          target="_blank"
                          rel="noopener noreferrer"
                          className="block px-4 py-2.5 text-sm text-gray-700 hover:bg-orange-50 hover:text-orange-600 transition-colors"
                        >
                          {item.name}
                        </a>
                      ))}
                    </div>
                  </div>
                </div>
              ))}
              
              <a
                href="https://anla.my.id"
                target="_blank"
                rel="noopener noreferrer"
                className="ml-4 px-6 py-2.5 bg-gradient-to-r from-orange-500 to-orange-600 text-white rounded-lg hover:from-orange-600 hover:to-orange-700 font-medium shadow-sm transition-all"
              >
                About Me
              </a>
            </div>
          </div>
        </div>
      </nav>

      <section className="bg-orange-500 text-white">
        <div className="container mx-auto px-4 py-20 max-w-7xl">
          <div className="grid md:grid-cols-2 gap-12 items-end">
            <div>
              <h1 className="text-4xl font-bold mb-2">Anla Harpanda</h1>
              <p className="text-lg mb-6 opacity-90">NIM: 2311083015</p>
              <p className="text-base leading-relaxed opacity-95">
                Microservice ini dibangun dalam arsitektur Kubernetes cluster yang menghubungkan 22 service dengan 16 URL endpoint. 
                Sistem terdiri dari dua domain utama yaitu Perpustakaan dan Marketplace, dilengkapi dengan infrastruktur pendukung 
                seperti service discovery (Eureka), message broker (RabbitMQ), monitoring (Grafana), logging (Kibana), 
                dan CI/CD pipeline (Jenkins) untuk otomatisasi deployment.
              </p>
            </div>
            <div className="flex justify-center items-end">
              <img src="/onboard.png" alt="Onboard" className="max-w-md w-full" />
            </div>
          </div>
        </div>
      </section>

      <main className="container mx-auto px-4 py-12 max-w-7xl">
        <div className="space-y-16">
          {services.map((section) => (
            <section key={section.category}>
              <h2 className="text-3xl font-bold text-center mb-8 text-gray-900">{section.category}</h2>
              <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                {section.items.map((service) => (
                  <a
                    key={service.url}
                    href={service.url}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="block bg-white border border-gray-200 rounded-lg overflow-hidden hover:shadow-lg hover:border-orange-500 transition-all"
                  >
                    <div className="h-32 bg-gray-100">
                      <img src={service.img} alt={service.name} className="w-full h-full object-cover" />
                    </div>
                    <div className="p-4">
                      <h3 className="font-semibold text-lg mb-1 text-gray-900">{service.name}</h3>
                      <p className="text-sm text-gray-600 mb-2">{service.desc}</p>
                      <p className="text-xs text-orange-600 truncate">{service.url}</p>
                    </div>
                  </a>
                ))}
              </div>
            </section>
          ))}
        </div>
      </main>
    </div>
  );
}
