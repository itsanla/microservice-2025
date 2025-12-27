#!/bin/bash

echo "🧹 Removing files from Git History (using filter-branch)..."
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
echo "🗑️  Removing files from history..."

# Remove files from all commits
git filter-branch --force --index-filter \
  'git rm -rf --cached --ignore-unmatch \
    target/ \
    h2db/ \
    .idea/ \
    *.class \
    *.jar \
    *.log \
    *.txt \
    *.mv.db \
    *.trace.db \
    *_data/ \
    .vscode/ \
    *.iml' \
  --prune-empty --tag-name-filter cat -- --all

echo ""
echo "🧹 Cleaning up refs..."
rm -rf .git/refs/original/
git reflog expire --expire=now --all
git gc --prune=now --aggressive

echo ""
echo "✅ History cleanup completed!"
echo ""
echo "📊 Repository size:"
du -sh .git

echo ""
echo "🚀 Next steps:"
echo "1. Verify changes: git log --oneline | head -20"
echo "2. Force push to remote:"
echo "   git push origin --force --all"
echo "   git push origin --force --tags"
echo ""
echo "⚠️  IMPORTANT: All collaborators must re-clone the repository!"
echo "   git clone <repository-url>"
