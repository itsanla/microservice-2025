#!/bin/bash

echo "🧪 Running Integration Tests..."

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

# Wait for service to be ready
wait_for_service() {
    local url=$1
    local service_name=$2
    local max_attempts=30
    local attempt=1
    
    echo -e "${YELLOW}Waiting for ${service_name} to be ready...${NC}"
    
    while [ $attempt -le $max_attempts ]; do
        if curl -s "$url" > /dev/null 2>&1; then
            echo -e "${GREEN}✅ ${service_name} is ready${NC}"
            return 0
        fi
        echo "Attempt $attempt/$max_attempts - ${service_name} not ready yet..."
        sleep 5
        ((attempt++))
    done
    
    echo -e "${RED}❌ ${service_name} failed to start${NC}"
    return 1
}

# Test API endpoint
test_endpoint() {
    local url=$1
    local expected_status=$2
    local test_name=$3
    
    echo -e "${YELLOW}Testing: ${test_name}${NC}"
    
    response=$(curl -s -w "%{http_code}" "$url")
    status_code="${response: -3}"
    
    if [ "$status_code" = "$expected_status" ]; then
        echo -e "${GREEN}✅ ${test_name} - Status: ${status_code}${NC}"
        return 0
    else
        echo -e "${RED}❌ ${test_name} - Expected: ${expected_status}, Got: ${status_code}${NC}"
        return 1
    fi
}

# Start services if not running
echo "🚀 Starting services..."
docker-compose up -d

# Wait for Eureka
wait_for_service "http://localhost:8761" "Eureka Server" || exit 1

# Wait for all services to register
echo "⏳ Waiting for service registration..."
sleep 30

# Test Eureka Dashboard
test_endpoint "http://localhost:8761" "200" "Eureka Dashboard"

# Test Marketplace Services
echo ""
echo "🛒 Testing Marketplace Domain..."
test_endpoint "http://localhost:8081/actuator/health" "200" "Produk Service Health"
test_endpoint "http://localhost:8082/actuator/health" "200" "Pelanggan Service Health"
test_endpoint "http://localhost:8083/actuator/health" "200" "Order Service Health"

# Test Perpustakaan Services
echo ""
echo "📚 Testing Perpustakaan Domain..."
test_endpoint "http://localhost:8084/actuator/health" "200" "Buku Service Health"
test_endpoint "http://localhost:8085/actuator/health" "200" "Anggota Service Health"
test_endpoint "http://localhost:8086/actuator/health" "200" "Pengembalian Service Health"
test_endpoint "http://localhost:8087/actuator/health" "200" "Peminjaman Service Health"

# Test Business Logic
echo ""
echo "🔄 Testing Business Logic..."

# Test Order Detail (Aggregator)
test_endpoint "http://localhost:8083/api/order/detail/1" "200" "Order Detail Aggregation"

# Test Peminjaman Detail (Aggregator)
test_endpoint "http://localhost:8087/api/peminjaman/detail/1" "200" "Peminjaman Detail Aggregation"

# Test Service Discovery
echo ""
echo "🔍 Testing Service Discovery..."
registered_services=$(curl -s "http://localhost:8761/eureka/apps" | grep -o "<name>[^<]*</name>" | wc -l)

if [ "$registered_services" -ge 8 ]; then
    echo -e "${GREEN}✅ Service Discovery - ${registered_services} services registered${NC}"
else
    echo -e "${RED}❌ Service Discovery - Only ${registered_services} services registered${NC}"
fi

echo ""
echo "📊 Integration Test Summary:"
echo "============================"
echo -e "${GREEN}✅ All integration tests completed!${NC}"
echo "🐳 Services are running in Docker containers"
echo "🔗 Check Eureka Dashboard: http://localhost:8761"