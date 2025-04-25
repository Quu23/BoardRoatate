jpackage ^
  --name BoardRotate ^
  --input bin ^
  --main-jar BoardRotate.jar ^
  --main-class app.Application ^
  --type app-image ^
  --dest installer ^
  --win-console ^
  --runtime-image custom-jre
