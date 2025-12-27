#!/bin/bash

echo "🧹 Removing files from Git History..."
echo ""
echo "⚠️  WARNING: This will rewrite git history!"
echo "⚠️  Make sure you have a backup before proceeding."
echo ""
read -p "Continue? (yes/no): " confirm

if [ "$confirm" != "yes" ]; then
    echo "Aborted."
    exit 1
fi

echo ""
echo "📦 Step 1: Installing git-filter-repo (if needed)..."

# Check if git-filter-repo is installed
if ! command -v git-filter-repo &> /dev/null; then
    echo "Installing git-filter-repo..."
    
    # Try pip install
    if command -v pip3 &> /dev/null; then
        pip3 install git-filter-repo
    elif command -v pip &> /dev/null; then
        pip install git-filter-repo
    else
        echo "❌ Please install git-filter-repo manually:"
        echo "   pip install git-filter-repo"
        echo "   or download from: https://github.com/newren/git-filter-repo"
        exit 1
    fi
fi

echo ""
echo "🗑️  Step 2: Removing files from history..."

# Remove target directories
echo "Removing target/ directories..."
git filter-repo --path target --invert-paths --force

# Remove h2db directories
echo "Removing h2db/ directories..."
git filter-repo --path h2db --invert-paths --force

# Remove .idea directories
echo "Removing .idea/ directories..."
git filter-repo --path .idea --invert-paths --force

# Remove compiled files
echo "Removing *.class files..."
git filter-repo --path-glob '*.class' --invert-paths --force

# Remove jar files (except maven-wrapper.jar)
echo "Removing *.jar files (except wrapper)..."
git filter-repo --path-glob '*.jar' --invert-paths --force
git filter-repo --path .mvn/wrapper/maven-wrapper.jar --force

# Remove log files
echo "Removing *.log files..."
git filter-repo --path-glob '*.log' --invert-paths --force

# Remove txt files
echo "Removing *.txt files..."
git filter-repo --path-glob '*.txt' --invert-paths --force

# Remove database files
echo "Removing database files..."
git filter-repo --path-glob '*.mv.db' --invert-paths --force
git filter-repo --path-glob '*.trace.db' --invert-paths --force

echo ""
echo "✅ History cleanup completed!"
echo ""
echo "📊 Repository size:"
du -sh .git

echo ""
echo "🚀 Next steps:"
echo "1. Verify changes: git log --all --oneline"
echo "2. Force push to remote: git push origin --force --all"
echo "3. Force push tags: git push origin --force --tags"
echo ""
echo "⚠️  Note: All collaborators must re-clone the repository!"
