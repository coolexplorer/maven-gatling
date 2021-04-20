# Upgrade Gatling version
When you create your project with maven archetype, you might get the old version compared to the latest one. 
At the time I created, the archetype template had the version `3.2.x` so I'm going to upgrade Gatling the latest version `3.5.1`


## Change Gatling version in pom.xml

Just change the gatling version in pom.xml file. Super easy!!

```xml
<!-- Change the version from 3.2.x to 3.5.1-->
<gatling.version>3.5.1</gatling.version>
``` 

## Change `gatling.conf`

Refer the [gatling.conf](../src/test/resources/gatling.conf) in this project. 


## Change files based on the latest version

You cannot use `io.gatling.commons.util.PathHelper` anymore so need to change it.

```scala
// Gatling v3.2.x

import io.gatling.commons.util.PathHelper._

object IDEPathHelper {

  val gatlingConfUrl: Path = getClass.getClassLoader.getResource("gatling.conf")
  val projectRootDir = gatlingConfUrl.ancestor(3)

...
}
```

Because of this change, you need to check `Engine.scala` and `Recorder.scala` as well.

Refer the files in this project.

