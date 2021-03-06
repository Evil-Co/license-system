License System [![Build Status](http://assets.evil-co.com/build/JLS-MASTER.png)](http://www.evil-co.com/ci/browse/JLS-MASTER)
==============
The License System for Java provides a lightweight and customer friendly open-source solution for license validation
and feature restrictions.

The library is hugely inspired by the [Atlassian](http://www.atlassian.com) licensing system which is used in major
project management solutions and thus has proven it's efficiency.

Please Note: This library does not try to protect your product against cracks in any way and introduces a
lightweight solution which is fair for both sides (customer and maintainer). Users who intend to get around licensing
systems will always find a way to do so as long as a binary and/or source code version of the software is directly
distributed to the user. For absolute protection please implement your main application logic on the server side
and do not distribute it in binary or source code form.

Compilation
-----------
This library used [Apache Maven](http://maven.apache.org/). After installing maven you will be able to run the following
command to compile the library:

	mvn clean install

This will compile the library and install it to your local repository. If you are already using maven you are also able
to directly include this library into your projects by adding the following repository and dependency:

	...
	<repository>
		<id>evil-co</id>
		<url>http://nexus.evil-co.org/content/repositories/free/</url>
	</repository>
	...

	...
	<dependency>
		<groupId>com.evilco.license</groupId>
		<artifactId>client</artifactId>
		<version>1.0</version>
	</dependency>
	...

	...
	<dependency>
		<groupId>com.evilco.license</groupId>
		<artifactId>server</artifactId>
		<version>1.0</version>
	</dependency>
	...

You can also use the snapshot version (unstable versions) by using ```http://nexus.evil-co.org/content/repositories/free-snapshots/```
as the repository url.

The library is currently not available through maven central but might become available in the future.

Bug Reporting
-------------
Please report bugs in our [Bugtracker](https://evilco.atlassian.net/browse/JLS/). If you don't want to wait for them to
fixed by one of our project members you can also submit a bugfix on GitHub with the pull request feature (please refer
to the contribution section for more information and notices).

Usage
-----
For examples on how to use the library please refer to ```test/src/test/java/com/evilco/license/test/GeneralTest.java```
which includes all use cases.

You will need to include the __server__ library on your server side (e.g. your online customer panel) and the __client__
library on your application side.

Implementation
--------------
The implementation uses RSA signatures to verify the origin of the supplied license file. All applications need to be
distributed with a public key generated by you while your servers (e.g. your online customer panel) will need to have
access to the private key matching the distributed public key.

License representations may be created by either implementing the *ILicense* interface (contained within the __common__
library; highly customizable; advanced users) or by extending the *AbstractLicense* class (also contained within the
__common__ library; customizable).

While the *ILicense* interface is an empty template the *AbstractLicense* class already provides an expiration date
and the licensee name. New fields can be introduced to the license by adding them to your license class (you may exclude
specific fields from your license files by adding the ```transient``` keyword).

All license implementations are serialized into the [JSON](http://www.json.org/) format (with help of the
[GSON](https://code.google.com/p/google-gson/) library) and then signed with your private key.

If you additionally choose to provide text based licenses (by using *CompressedLicenseEncoder* and
*CompressedLicenseDecoder*) the resulting license text will also be compressed with GZIP and encoded with Base64. The
resulting code may now be pasted into your application to activate additional features or authorize the application use
in general.

When the license is important the file is (optionally) decoded (Base64) and then uncompressed (GZIP). The resulting data
is then split up and the RSA signature is being verified against hte provided public key. In the last step the data is
then un-serialized and is being turned back into a POJO representation and can be used to your heart's content.

Versioning
----------
In some cases you might want to add new properties or remove old ones. For that case the library does implement Gson's
version API. You can define which properties are available in a license version by using the ```@Since``` and ```@Until```
annotations with the respective license version. The most recent license version, which is used for encoding, can be
specified with the ```@LicenseVersion``` annotation and will then be automatically added to licenses generated in the
future.

**Please Note:** If the ```@LicenseVersion``` annotation is not present version *1.0* is assumed.

Extensions
----------
Due to the interfaces provided along with the libraries you may also implement your own formats (as example extending
the original JSON format).

Contribution
------------
You may contribute to this library by cloning it on GitHub and using GitHub's built-in pull-request feature. Please note
that all contributions are placed under the terms of the Apache License, Version 2.0.

License
-------
Copyright (C) 2014 Evil-Co <http://wwww.evil-co.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.