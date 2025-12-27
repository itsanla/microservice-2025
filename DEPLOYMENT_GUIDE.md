# 🚀 Multi-VPS Deployment Guide

## 📊 Architecture Overview

```
VPS 1 (10.0.1.30): Eureka + RabbitMQ
VPS 2 (10.0.1.20): API Gateways
VPS 3 (10.0.1.40): ELK Stack
VPS 4 (10.0.1.50): Jenkins CI/CD
VPS 5 (10.0.1.60): Marketplace Services
VPS 6 (10.0.1.70): Perpustakaan Services
```

## 🔧 Prerequisites

- AWS EC2 instances with Ubuntu 20.04+
- Docker & Docker Compose installed on all VPS
- Private network configured (VPC)
- Security groups configured for inter-VPS communication

## 📝 Deployment Steps

### 1️⃣ VPS 1: Eureka + RabbitMQ

```bash
# SSH to VPS 1
ssh ubuntu@<VPS1_PUBLIC_IP>

# Clone repository
git clone <repo-url>
cd arsitektur-berbasis-layanan-2025/rabbitmq

# Edit .env file with actual private IP
nano .env
# Set: VPS_RABBITMQ_IP=<actual_private_ip>

# Start services
docker-compose up -d

# Verify
docker-compose ps
curl http://localhost:8761  # Eureka
curl http://localhost:15672 # RabbitMQ UI
```

### 2️⃣ VPS 2: API Gateways

```bash
# SSH to VPS 2
ssh ubuntu@<VPS2_PUBLIC_IP>

cd arsitektur-berbasis-layanan-2025/api-gateway

# Edit .env file
nano .env
# Set: VPS_GATEWAY_IP=<actual_private_ip>
# Set: EUREKA_IP=<VPS1_private_ip>

# Start services
docker-compose up -d

# Verify
docker-compose ps
curl http://localhost:9000/actuator/health  # Marketplace Gateway
curl http://localhost:9001/actuator/health  # Perpustakaan Gateway
```

### 3️⃣ VPS 3: ELK Stack

```bash
# SSH to VPS 3
ssh ubuntu@<VPS3_PUBLIC_IP>

cd arsitektur-berbasis-layanan-2025/elk

# Edit .env file
nano .env
# Set: VPS_ELK_IP=<actual_private_ip>

# Start services
docker-compose up -d

# Verify
docker-compose ps
curl http://localhost:9200  # Elasticsearch
curl http://localhost:5601  # Kibana
```

### 4️⃣ VPS 4: Jenkins

```bash
# SSH to VPS 4
ssh ubuntu@<VPS4_PUBLIC_IP>

cd arsitektur-berbasis-layanan-2025/jenkins

# Edit .env file
nano .env
# Set: VPS_JENKINS_IP=<actual_private_ip>
# Set: DOCKER_HUB_TOKEN=<your_token>

# Start services
docker-compose up -d

# Get initial admin password
docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword

# Access Jenkins
# http://<VPS4_PUBLIC_IP>:8080
```

### 5️⃣ VPS 5: Marketplace Services

```bash
# SSH to VPS 5
ssh ubuntu@<VPS5_PUBLIC_IP>

cd arsitektur-berbasis-layanan-2025/marketplace

# Edit .env file
nano .env
# Set: VPS_MARKETPLACE_IP=<actual_private_ip>
# Set: EUREKA_IP=<VPS1_private_ip>
# Set: RABBITMQ_IP=<VPS1_private_ip>
# Set: LOGSTASH_IP=<VPS3_private_ip>

# Start services
docker-compose up -d

# Verify
docker-compose ps
curl http://localhost:8081/actuator/health  # Produk
curl http://localhost:8082/actuator/health  # Pelanggan
curl http://localhost:8083/actuator/health  # Order
```

### 6️⃣ VPS 6: Perpustakaan Services

```bash
# SSH to VPS 6
ssh ubuntu@<VPS6_PUBLIC_IP>

cd arsitektur-berbasis-layanan-2025/perpustakaan

# Edit .env file
nano .env
# Set: VPS_PERPUSTAKAAN_IP=<actual_private_ip>
# Set: EUREKA_IP=<VPS1_private_ip>
# Set: RABBITMQ_IP=<VPS1_private_ip>
# Set: LOGSTASH_IP=<VPS3_private_ip>

# Start services
docker-compose up -d

# Verify
docker-compose ps
curl http://localhost:8084/actuator/health  # Buku
curl http://localhost:8085/actuator/health  # Anggota
curl http://localhost:8086/actuator/health  # Pengembalian
curl http://localhost:8087/actuator/health  # Peminjaman
```

## ✅ Verification

### Check Eureka Dashboard
```bash
# Access from browser
http://<VPS1_PUBLIC_IP>:8761

# Should show all registered services:
- PRODUK-SERVICE
- PELANGGAN-SERVICE
- ORDER-SERVICE
- BUKU-SERVICE
- ANGGOTA-SERVICE
- PENGEMBALIAN-SERVICE
- PEMINJAMAN-SERVICE
- API-GATEWAY-MARKETPLACE
- API-GATEWAY-PERPUSTAKAAN
```

### Check RabbitMQ
```bash
http://<VPS1_PUBLIC_IP>:15672
# Login: admin / admin123
```

### Check Kibana (Logs)
```bash
http://<VPS3_PUBLIC_IP>:5601
```

### Check Jenkins
```bash
http://<VPS4_PUBLIC_IP>:8080
```

## 🔒 Security Groups Configuration

### VPS 1 (Eureka + RabbitMQ)
- Inbound: 8761 (Eureka) from all VPS
- Inbound: 5672, 15672 (RabbitMQ) from all VPS
- Inbound: 22 (SSH) from your IP

### VPS 2 (API Gateways)
- Inbound: 9000, 9001 from Internet (0.0.0.0/0)
- Inbound: 22 (SSH) from your IP

### VPS 3 (ELK)
- Inbound: 5000 (Logstash) from VPS 5, 6
- Inbound: 5601 (Kibana) from your IP
- Inbound: 22 (SSH) from your IP

### VPS 4 (Jenkins)
- Inbound: 8080 from your IP
- Inbound: 22 (SSH) from your IP

### VPS 5 & 6 (Services)
- Inbound: 8081-8087 from VPS 2 (API Gateway)
- Inbound: 22 (SSH) from your IP

## 🛠️ Troubleshooting

### Service not registering to Eureka
```bash
# Check Eureka IP in .env
# Check network connectivity
ping <EUREKA_IP>

# Check service logs
docker logs <container_name>
```

### RabbitMQ connection failed
```bash
# Check RabbitMQ is running
docker ps | grep rabbitmq

# Check credentials in .env
# Check network connectivity
telnet <RABBITMQ_IP> 5672
```

### Logs not appearing in Kibana
```bash
# Check Logstash is running
docker logs logstash

# Check service can reach Logstash
telnet <LOGSTASH_IP> 5000
```

## 📊 Monitoring

### Check all services status
```bash
# On each VPS
docker-compose ps
docker-compose logs -f
```

### Resource usage
```bash
docker stats
```

## 🔄 Update Services

```bash
# Pull latest image
docker-compose pull

# Restart services
docker-compose up -d

# Or specific service
docker-compose up -d <service_name>
```

## 🗑️ Cleanup

```bash
# Stop all services
docker-compose down

# Remove volumes (WARNING: deletes data)
docker-compose down -v
```
