# Create a new repository on the command line
$> echo "# food-system-example-ms" >> README.md

git init

git add README.md 

git commit -m "first commit"

git branch -M main

git remote add origin https://github.com/ioqayl/food-system-example-ms.git

git push -u origin main

# Push an existing repository from the command line
git remote add origin https://github.com/ioqayl/food-system-example-ms.git

git branch -M main

git push -u origin main