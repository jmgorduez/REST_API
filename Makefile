gradle= sh gradlew clean

clean:
	@ $(gradle)

refresh:
	@ sh gradlew --refresh-dependencies

run:
	@ $(gradle)

jar:
	@ $(gradle) jar

test:
	@ $(gradle) test

it:
	@ $(gradle) integrationTest

coverage:
	@ $(gradle) test integrationTest jacocoTestReport