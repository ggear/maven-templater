# Maven Templater

A module to use the Maven resource plugin filtering as a naive script and library pre-processor of a template file.

## Requirements

To compile, build and package from source, this project requires:

* JDK 1.8
* Maven 3

## Usage

The plugin can be used as per the [example](https://github.com/ggear/maven-templater/tree/master/maven-templater-example).

## Release

To perform a release:

```bash
# Change the following variables to appropriate values for your target release
export MT_VERSION_RELEASE=1.0.0
export MT_VERSION_HEAD=1.0.1
mvn release:prepare -B -DreleaseVersion=$MT_VERSION_RELEASE -DdevelopmentVersion=$MT_VERSION_HEAD-SNAPSHOT
mvn release:perform
mvn release:clean clean
git push --all
mvn clean install -PCMP
git tag
```