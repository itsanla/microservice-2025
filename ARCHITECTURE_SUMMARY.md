# 📋 Multi-VPS Architecture Summary

## ✅ Completed Structure

### 📁 Directory Structure
```
arsitektur-berbasis-layanan-2025/
├── rabbitmq/                    # VPS 1 (with Eureka)
│   ├── docker-compose.yml       ✅
│   ├── .env                     ✅
│   └── rabbitmq.conf            ✅
│
├── api-gateway/                 # VPS 2
│   ├── docker-compose.yml       ✅
│   └── .env                     ✅
│
├── elk/                         # VPS 3
│   ├── docker-compose.yml       ✅
│   ├── .env                     ✅
│   └── logstash/
│       └── logstash.conf        ✅
│
├── jenkins/                     # VPS 4
│   ├── docker-compose.yml       ✅
│   └── .env                     ✅
│
├── marketplace/                 # VPS 5
│   ├── docker-compose.yml       ✅
│   ├── .env                     ✅
│   ├── produk/
│   │   └── Jenkinsfile          ✅
│   ├── pelanggan/
│   │   └── Jenkinsfile          ✅
│   └── order/
│       └── Jenkinsfile          ✅
│
└── perpustakaan/                # VPS 6
    ├── docker-compose.yml       ✅
    ├── .env                     ✅
    ├── buku/
    │   └── Jenkinsfile          ✅
    ├── anggota/
    │   └── Jenkinsfile          ✅
    ├── pengembalian/
    │   └── Jenkinsfile          ✅
    └── peminjaman/
        └── Jenkinsfile          ✅
```

## 🏗️ VPS Allocation

| VPS | Services | IP (Example) | Ports | Resources |
|-----|----------|--------------|-------|-----------|
| **VPS 1** | Eureka + RabbitMQ | 10.0.1.30 | 8761, 5672, 15672 | 2GB RAM, 1 vCPU |
| **VPS 2** | API Gateways | 10.0.1.20 | 9000, 9001 | 2GB RAM, 1 vCPU |
| **VPS 3** | ELK Stack | 10.0.1.40 | 9200, 5000, 5601 | 4GB RAM, 2 vCPU |
| **VPS 4** | Jenkins | 10.0.1.50 | 8080, 50000 | 4GB RAM, 2 vCPU |
| **VPS 5** | Marketplace (3 services) | 10.0.1.60 | 8081-8083 | 4GB RAM, 2 vCPU |
| **VPS 6** | Perpustakaan (4 services) | 10.0.1.70 | 8084-8087 | 4GB RAM, 2 vCPU |

**Total Resources:** 20GB RAM, 10 vCPU

## 🔄 Service Communication Flow

```
┌─────────────────────────────────────────────────────┐
│  Client Request                                     │
└────────────────┬────────────────────────────────────┘
                 ↓
┌─────────────────────────────────────────────────────┐
│  VPS 2: API Gateway (9000/9001)                    │
│  - Route requests to services                      │
└────────────────┬────────────────────────────────────┘
                 ↓
┌─────────────────────────────────────────────────────┐
│  VPS 1: Eureka (8761)                              │
│  - Service discovery                               │
│  - Return service location                         │
└────────────────┬────────────────────────────────────┘
                 ↓
┌─────────────────────────────────────────────────────┐
│  VPS 5/6: Business Services                        │
│  - Process request                                 │
│  - Communicate via Eureka                          │
└────────────────┬────────────────────────────────────┘
                 ↓
┌─────────────────────────────────────────────────────┐
│  VPS 1: RabbitMQ (5672)                            │
│  - Event messaging                                 │
└────────────────┬────────────────────────────────────┘
                 ↓
┌─────────────────────────────────────────────────────┐
│  VPS 3: ELK Stack                                  │
│  - Centralized logging                             │
│  - Log analysis via Kibana                         │
└─────────────────────────────────────────────────────┘
```

## 🎯 Key Features

### ✅ Dynamic IP Configuration
- All IPs configured via `.env` files
- Easy to change without code modification
- Support for AWS EC2 private networking

### ✅ Pull-Only Deployment
- All services pull from Docker Hub (`itsanla/*:latest`)
- No build process on VPS
- Fast deployment and updates

### ✅ Centralized Logging
- All services send logs to Logstash
- Elasticsearch for storage
- Kibana for visualization

### ✅ CI/CD Pipeline
- Jenkins for automated builds
- Jenkinsfile per service
- Automatic push to Docker Hub

### ✅ Service Discovery
- Eureka for dynamic service registration
- No hardcoded service URLs
- Automatic load balancing

### ✅ Message Queue
- RabbitMQ for async communication
- Event-driven architecture
- Decoupled services

## 📝 Environment Variables Reference

### Common Variables (All VPS)
```bash
# This VPS IP
VPS_*_IP=10.0.1.x

# Eureka Server
EUREKA_IP=10.0.1.30

# RabbitMQ
RABBITMQ_IP=10.0.1.30
RABBITMQ_USER=admin
RABBITMQ_PASSWORD=admin123

# Logstash
LOGSTASH_IP=10.0.1.40
```

### Service-Specific Variables
```bash
# Database
SPRING_DATASOURCE_URL=jdbc:h2:file:/app/data/{service}
SPRING_DATASOURCE_USERNAME=sa
SPRING_DATASOURCE_PASSWORD=password

# Application
SERVER_PORT=808x
SPRING_APPLICATION_NAME={SERVICE}-SERVICE
```

## 🚀 Deployment Order

1. **VPS 1** (Eureka + RabbitMQ) - Infrastructure first
2. **VPS 3** (ELK Stack) - Logging infrastructure
3. **VPS 4** (Jenkins) - CI/CD setup
4. **VPS 5** (Marketplace Services) - Business services
5. **VPS 6** (Perpustakaan Services) - Business services
6. **VPS 2** (API Gateways) - Entry points last

## 🔐 Security Considerations

### Network Security
- Private IPs for inter-VPS communication
- Security groups restrict access
- Only API Gateway exposed to internet

### Application Security
- H2 console disabled in production
- RabbitMQ with authentication
- Jenkins with admin password

### Data Security
- Persistent volumes for data
- Regular backups recommended
- Database credentials in .env (not in code)

## 📊 Monitoring & Health Checks

### Health Check Endpoints
```bash
# Eureka
http://{EUREKA_IP}:8761/actuator/health

# Services
http://{SERVICE_IP}:808x/actuator/health

# RabbitMQ
http://{RABBITMQ_IP}:15672

# Kibana
http://{ELK_IP}:5601
```

### Docker Health Checks
- All services have health check configured
- Auto-restart on failure
- 30s interval, 3 retries

## 🔄 Update Strategy

### Rolling Update
```bash
# Update one service at a time
docker-compose pull {service}
docker-compose up -d {service}
```

### Zero-Downtime Update
```bash
# Scale up new version
docker-compose up -d --scale {service}=2

# Remove old version
docker-compose up -d --scale {service}=1
```

## 📈 Scalability

### Horizontal Scaling
- Add more VPS for services
- Eureka handles multiple instances
- Load balancing automatic

### Vertical Scaling
- Increase VPS resources
- Adjust JVM memory in docker-compose
- Monitor with `docker stats`

## 🎓 Next Steps

1. ✅ Deploy to AWS EC2
2. ✅ Configure security groups
3. ✅ Setup Jenkins pipelines
4. ✅ Test inter-VPS communication
5. ⏳ Implement CQRS (future)
6. ⏳ Add monitoring (Prometheus/Grafana)
7. ⏳ Setup backup automation

## 📚 Documentation

- [DEPLOYMENT_GUIDE.md](./DEPLOYMENT_GUIDE.md) - Step-by-step deployment
- [README.md](./README.md) - Project overview
- [GIT_IGNORE_REFERENCE.md](./GIT_IGNORE_REFERENCE.md) - Git configuration

---

**Status:** ✅ Ready for deployment
**Last Updated:** 2024
**Architecture:** Multi-VPS Microservices with AWS EC2
