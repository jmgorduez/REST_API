gradle= sh gradlew clean

clean:
	@ $(gradle)

refresh:
	@ sh gradlew --refresh-dependencies

runDev:
	@ $(gradle) build runappDev

runProd:
	@ $(gradle) build runappProd

jar:
	@ $(gradle) build

war:
	@ $(gradle) build

test:
	@ $(gradle) test

coverage:
	@ $(gradle) test jacocoTestReport