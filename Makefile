gradle= sh gradlew clean

clean:
	@ $(gradle)

refresh:
	@ sh gradlew --refresh-dependencies

run:
	@ $(gradle)

jar:
	@ $(gradle) build

test:
	@ $(gradle) test

coverage:
	@ $(gradle) test jacocoTestReport