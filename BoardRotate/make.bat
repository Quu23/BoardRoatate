jar cvf bin/BoardRotate.jar -C bin .

jlink --module-path "C:\programs\BoardRoatate\BoardRotate\bin;C:\Program Files\Java\jdk-22\jmods" --add-modules java.base,java.desktop,app --output custom-jre


jpackage ^
  --name BoardRotate ^
  --input bin ^
  --main-jar BoardRotate.jar ^
  --main-class app.Application ^
  --type app-image ^
  --dest installer ^
  --win-console ^
  --runtime-image custom-jre
