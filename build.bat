javac -d Framework/build/web/WEB-INF/classes framework/src/java/etu1954/framework/*.java framework/src/java/etu1954/framework/annotation/*.java

jar cvf Framework/build/web/WEB-INF/lib/framework.jar -C Framework/build/web/WEB-INF/classes/ .

@REM cd Test_Framework

@REM mkdir build

@REM mkdir build/web

@REM mkdir build\web\WEB-INF\lib
@REM mkdir build\web\WEB-INF\classes

@REM cd ..

copy Framework\build\web\WEB-INF\lib\framework.jar Test_Framework\build\web\WEB-INF\lib\
javac -d Test_Framework/build/web/WEB-INF/classes Test_Framework/src/java/Models/*.java 

