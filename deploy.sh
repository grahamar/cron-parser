#!/usr/bin/env bash
if [ ! -z "$TRAVIS_TAG" ]
then
    echo "on a tag -> set pom.xml <version> to $TRAVIS_TAG"
    mvn --settings .travis.settings.xml org.codehaus.mojo:versions-maven-plugin:2.1:set -DprocessAllModules=true -DnewVersion=$TRAVIS_TAG 1>/dev/null 2>/dev/null
else
    echo "not on a tag -> keep snapshot version in pom.xml"
fi

# Decrypt code signing key
openssl aes-256-cbc -K $encrypted_d34e9bc71fa0_key -iv $encrypted_d34e9bc71fa0_iv -in codesigning.asc.enc -out codesigning.asc -d
gpg --fast-import codesigning.asc

# Deploy to Maven repo
mvn clean deploy --settings .travis.settings.xml -DskipTests=true -B -U
