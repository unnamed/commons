#!/bin/bash
##
## Script to generate and
## push JavaDocs to the
## unnamed/webpage repository
##
organization="unnamed"
repo_name="webpage"
target_repo="https://${ACCESS_TOKEN}@github.com/${organization}/${repo_name}"
target_dir="docs/commons"

rm -rf $target_repo
git clone $target_repo
bash gradlew javadoc

function copyJavadocs {
  mkdir -p ${repo_name}/docs/$1
  cp -r $1/build/docs/javadoc/* webpage/docs/commons/$1
}

copyJavadocs error
copyJavadocs functional
copyJavadocs reflect
copyJavadocs validation

cd ${repo_name}
git add .
git commit -m "auto: update 'unnamed/commons' javadoc"
git push
rm -rf $target_repo