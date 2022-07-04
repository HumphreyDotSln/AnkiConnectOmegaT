plugins {
    java
    // groovy
    // kotlin("jvm") version "1.3.72"
    // checkstyle
    distribution
    maven
    id("org.omegat.gradle") version "1.4.2"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

version = "0.0.1"

omegat {
    version = "5.7.1"
    pluginClass = "org.unlsycn.AnkiConnectOmegaT"
    debugPort = 2999
}

dependencies {
    packIntoJar("org.slf4j:slf4j-api:1.7.36")
    packIntoJar("commons-io:commons-io:2.11.0")
    packIntoJar("commons-lang:commons-lang:2.6")
    packIntoJar("org.slf4j:slf4j-nop:1.7.36")
    packIntoJar("com.intellij:forms_rt:7.0.3")
    packIntoJar("com.squareup.okhttp3:okhttp:4.10.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("xmlunit:xmlunit:1.6")
    testImplementation("org.madlonkay.supertmxmerge:supertmxmerge:2.0.1")
    testImplementation("com.alibaba:fastjson:2.0.7")
}
repositories {
    mavenCentral()
}

//checkstyle {
//    isIgnoreFailures = true
//    toolVersion = "7.1"
//}

distributions {
    main {
        contents {
            from(tasks["jar"], "README.md", "COPYING", "CHANGELOG.md")
        }
    }
}
