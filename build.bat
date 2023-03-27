javac -d Framework/build/web/WEB-INF/classes framework/src/java/etu1954/framework/*.java framework/src/java/etu1954/framework/annotation/*.java
jar cvf framework.jar -C .\Framework\build\web\WEB-INF\classes\ .
jar cvf test_framework.war -C .\Test_Framework\build\web\WEB-INF\ .

jar cvf Framework/build/web/WEB-INF/lib/framework.jar -C Framework/build/web/WEB-INF/classes/ .

cd Test_Framework

mkdir build

mkdir build/web

mkdir build\web\WEB-INF\lib
mkdir build\web\WEB-INF\classes

cd ..

copy framework.jar Test_Framework\build\web\WEB-INF\lib\
copy test_framework.war "C:\Program Files\Apache Software Foundation\Apache Tomcat 8.0.27\webapps\"

javac -d Test_Framework/build/web/WEB-INF/classes Test_Framework/src/java/Models/*.java 