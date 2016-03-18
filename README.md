ABOUT
=====

Gradle build system을 지원하기 위한 Tizen plugin 이다.
이 plugin은 Tizen Native application을 gradle을 사용하여 
build/package을 수행할 수 있다

FEATURES
========

Features of the Tizen plugin include:

* Compile, package Tizen native applications
* Sign application packages 

TASKS AND LIFECYCLE
===================

The Android plugin adds the following tasks and dependencies to the
build:

:tizenBuild
 Tizen Native application을 빌드한다

:tizenPackage
 Tizen Native application을 tpk로 패키징한다

USAGE
=====

native_template을 참조하면 된다.
native_template 안의 아래 파일, 디렉터리를 Tizen Native App의 디렉터리에 복사 후 사용하면 된다.
* build.gradle
 * Native app build/package 설정 빌드 스크립트이다
* gradle  
* gradlew  
* gradlew.bat  
* local.properties
 * sdk.dir 에 Tizen SDK의 설치 위치를 설정한다

TODO
=====

* build.gradle의 설정의 validation 구현이 필요하다
* Web app도 지원해야 한다
* 빌드/패키지 유연성을 확보해야 한다
* 현재는 Tizen CLI를 사용하는 방식이지만 추후 Tizen CLI 없이 독자 동작해야 한다
