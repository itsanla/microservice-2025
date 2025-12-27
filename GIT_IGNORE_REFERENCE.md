# 📋 Git Ignore Reference - Files & Folders to Exclude

## 🗂️ Build Output Directories
```
target/                    # Maven build output
build/                     # Gradle build output
out/                       # IDE output
dist/                      # Distribution files
```

## 💾 Database Files
```
h2db/                      # H2 database directory
*.mv.db                    # H2 database files
*.trace.db                 # H2 trace files
*.h2.db                    # H2 database files
mongodb_data/              # MongoDB data directory
postgres_data/             # PostgreSQL data directory
*_data/                    # Any data directories
```

## 🔧 IDE Configuration
```
.idea/                     # IntelliJ IDEA
*.iml                      # IntelliJ module files
*.iws                      # IntelliJ workspace
*.ipr                      # IntelliJ project
.vscode/                   # VS Code
.settings/                 # Eclipse settings
.classpath                 # Eclipse classpath
.project                   # Eclipse project
.metadata/                 # Eclipse metadata
nbproject/                 # NetBeans
```

## 📦 Compiled & Package Files
```
*.class                    # Java compiled classes
*.jar                      # Java archives (except wrapper)
*.war                      # Web archives
*.ear                      # Enterprise archives
*.zip                      # Zip files
*.tar.gz                   # Compressed archives
```

## 📝 Log & Temporary Files
```
*.log                      # Log files
logs/                      # Log directories
*.tmp                      # Temporary files
*.temp                     # Temporary files
*.bak                      # Backup files
*.swp                      # Vim swap files
*.txt                      # Text files (notes, debug)
*~                         # Backup files
```

## 🐳 Docker
```
*_data/                    # Docker volume data
```

## 🔐 Sensitive Data
```
*.env                      # Environment variables
.env.local                 # Local environment
secrets/                   # Secrets directory
credentials/               # Credentials directory
```

## 💻 OS Specific
```
.DS_Store                  # macOS
Thumbs.db                  # Windows
Desktop.ini                # Windows
```

## 📚 Maven Specific
```
.mvn/timing.properties     # Maven timing
dependency-reduced-pom.xml # Maven shade plugin
release.properties         # Maven release plugin
```

## ✅ Files to KEEP (Exceptions)
```
!.mvn/wrapper/maven-wrapper.jar    # Maven wrapper jar
!**/src/main/**/target/            # Keep source target
!**/src/test/**/target/            # Keep test target
```

## 🧹 Cleanup Commands

### Remove from Git Index (Staged)
```bash
# Run the cleanup script
./git-cleanup.sh

# Or manually:
git rm -r --cached target/
git rm -r --cached h2db/
git rm --cached **/*.class
git rm --cached **/*.log
git rm --cached **/*.txt
```

### Remove from Git History (Complete Removal)
```bash
# Using git filter-branch (slow but built-in)
git filter-branch --force --index-filter \
  "git rm -r --cached --ignore-unmatch target/ h2db/ *.class *.log *.txt" \
  --prune-empty --tag-name-filter cat -- --all

# Using BFG Repo-Cleaner (fast, recommended)
# Download from: https://reclaimtheweb.org/bfg-repo-cleaner/
java -jar bfg.jar --delete-folders target
java -jar bfg.jar --delete-folders h2db
java -jar bfg.jar --delete-files "*.class"
java -jar bfg.jar --delete-files "*.log"
java -jar bfg.jar --delete-files "*.txt"

# After cleanup, force push
git push origin --force --all
git push origin --force --tags
```

### Verify Cleanup
```bash
# Check git status
git status

# Check what's tracked
git ls-files

# Check repository size
du -sh .git
```

## 📊 Expected Results

### Before Cleanup
```
.git/          ~500MB
target/        ~200MB (tracked)
h2db/          ~50MB (tracked)
*.class        ~10MB (tracked)
*.log          ~5MB (tracked)
```

### After Cleanup
```
.git/          ~50MB (after history cleanup)
target/        ~200MB (ignored, not tracked)
h2db/          ~50MB (ignored, not tracked)
*.class        ~10MB (ignored, not tracked)
*.log          ~5MB (ignored, not tracked)
```

## 🎯 Best Practices

1. **Always add .gitignore FIRST** before committing
2. **Review .gitignore** before each commit
3. **Use git status** to verify what's being tracked
4. **Clean history** before pushing to public repos
5. **Backup** before running filter-branch or BFG

## 🔗 References

- [GitHub .gitignore Templates](https://github.com/github/gitignore)
- [BFG Repo-Cleaner](https://reclaimtheweb.org/bfg-repo-cleaner/)
- [Git Filter-Branch](https://git-scm.com/docs/git-filter-branch)
