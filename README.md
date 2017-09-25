# ariADDna

Platform contain web services, mobile and desktop application for management strategy to use union available space in the public cloud available to your the user. It provides a single point of connection to the many clouds providers: GoogleDrive, iCloud, DropBox, Box, OneDrive, Amazon S3, Azure, OpenStack Swift.

User web service will be able to set strategy for the use of space: the mirroring of files, the union of the space, clouds priority for the use and combination of these strategies.

Desktop applications installed on your computer (Windows, Mac OS X, Linux) version 1.0 and mobile app in version 2.0 (Android, iOS), will provide selective synchronization of generalized storage space in the clouds with local storage.

See additional documentation in [doc folder in this repository](https://github.com/StnetixDevTeam/ariADDna/tree/master/doc)

# Getting started

## Building the code

A detailed list of pre-requisite tools can be found in the
[Maven](https://maven.apache.org/) for building.

Once you have installed all the pre-requisites from the root of the repository execute the following
Maven command:

~~~bash
  mvn clean test
~~~

The above command will compile the code, run checkstyle, and run unit-tests.
So you can skip some part of this build flow cycle:

Run build and install without unit test:

~~~bash
  mvn clean install -DskipTests
~~~

Run build and install without checkstyle validation:

~~~bash
  mvn clean install -Dcheckstyle.skip
~~~


## Editing the code

The team uses Eclipse or IntelliJ. Formatting style settings for both these editors can be found in
the [doc/checkstyle](https://github.com/StnetixDevTeam/ariADDna/tree/master/doc) folder.
