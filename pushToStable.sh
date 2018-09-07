#!/bin/bash

cd /Users/marinebrenet/Documents/metadata-repository

git checkout stable

heure=$(date +%H%M)
jour=$(date +%Y%m%d)

git add .

git commit -m Stable"_"$jour"_"$heure

git push

git checkout master
