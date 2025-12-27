#!/bin/bash

echo "🔍 Running Quality Checks..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# PMD Rules file
PMD_RULES="testing/pmd-rules.xml"

# Function to run PMD check
run_pmd_check() {
    local service_path=$1
    local service_name=$2
    
    echo -e "${YELLOW}Checking ${service_name}...${NC}"
    
    cd "$service_path"
    
    # Run PMD analysis
    mvn pmd:check -Dpmd.rulesets="../../../${PMD_RULES}" || {
        echo -e "${RED}❌ PMD check failed for ${service_name}${NC}"
        return 1
    }
    
    # Run SpotBugs
    mvn spotbugs:check || {
        echo -e "${RED}❌ SpotBugs check failed for ${service_name}${NC}"
        return 1
    }
    
    echo -e "${GREEN}✅ ${service_name} passed quality checks${NC}"
    cd - > /dev/null
    return 0
}

# Check all services
FAILED_SERVICES=()

# Eureka Server
run_pmd_check "eureka" "Eureka Server" || FAILED_SERVICES+=("Eureka Server")

# Marketplace Services
for service in "Produk" "Pelanggan" "Order"; do
    run_pmd_check "Marketplace/${service}" "Marketplace ${service}" || FAILED_SERVICES+=("Marketplace ${service}")
done

# Perpustakaan Services
for service in "Buku" "anggota" "Pengembalian" "Peminjaman"; do
    run_pmd_check "Perpustakaan/${service}" "Perpustakaan ${service}" || FAILED_SERVICES+=("Perpustakaan ${service}")
done

# Summary
echo ""
echo "📊 Quality Check Summary:"
echo "========================"

if [ ${#FAILED_SERVICES[@]} -eq 0 ]; then
    echo -e "${GREEN}✅ All services passed quality checks!${NC}"
    exit 0
else
    echo -e "${RED}❌ Failed services:${NC}"
    for service in "${FAILED_SERVICES[@]}"; do
        echo -e "${RED}  - ${service}${NC}"
    done
    exit 1
fi