#!/bin/bash

echo Enter branch name to create, commit all changes and push: 
read branch_name
echo Enter commit message: 
read commit_message

git checkout -b $branch_name
git add -A
git commit -m "$commit_message"
git push --set-upstream origin $branch_name


