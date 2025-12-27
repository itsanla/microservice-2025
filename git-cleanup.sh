#!/bin/bash

echo "🧹 Cleaning Git Repository..."
echo ""

# Remove cached files from git index
echo "📦 Removing cached files from git index..."

# Remove target directories
find . -type d -name "target" -exec git rm -r --cached {} \; 2>/dev/null

# Remove h2db directories
find . -type d -name "h2db" -exec git rm -r --cached {} \; 2>/dev/null

# Remove IDE files
git rm -r --cached .idea/ 2>/dev/null
git rm -r --cached .vscode/ 2>/dev/null
git rm -r --cached *.iml 2>/dev/null

# Remove compiled files
git rm --cached **/*.class 2>/dev/null
git rm --cached **/*.jar 2>/dev/null
git rm --cached **/*.log 2>/dev/null

# Remove txt files
git rm --cached **/*.txt 2>/dev/null

# Remove database files
git rm --cached **/*.mv.db 2>/dev/null
git rm --cached **/*.trace.db 2>/dev/null

echo ""
echo "✅ Cleanup completed!"
echo ""
echo "📝 Next steps:"
echo "1. Review changes: git status"
echo "2. Commit changes: git commit -m 'chore: cleanup ignored files'"
echo "3. Push to remote: git push"
echo ""
echo "⚠️  Note: This only removes files from git index."
echo "   To remove from history completely, use git filter-branch or BFG Repo-Cleaner"
