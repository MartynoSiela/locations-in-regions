# Locations in Regions
## Assignment
This is the original assignment that can be found [here](https://github.com/traveltime-dev/internship-task):
>For this test task your goal is to match locations to their appropriate regions. Each location has a coordinate. You have to find which locations fit into given regions by their coordinates. A single region can contain multiple locations. A single location can appear in multiple regions, thus meaning regions can overlap with each other. Locations and regions are provided in separate JSON files. You will have to parse these files to read locations and regions data.
>
>After you successfully parsed JSON files you will need to create an algorithm or use third party library that matches locations to their regions based on their coordinates. The result of your task should be a JSON file which list all of the regions with their coresponding locations.
## Usage
The program has to be used via command line and file locations need to be provided as command line arguments. For some reason running the program via regular terminal with `scala main.scala` results with compilation errors. As it is possible to run the program via **sbt shell**, it will be the recommended way to run the program.
### sbt shell
Once inside the sbt shell `run` command can be used. Code will be compiled but it will result with the following output:
```
Usage: [--regions String] [--locations String] [--output String]
```
All 3 parameters are required and the program can be run like this:
```
run --regions path/to/file.json --locations path/to/file.json --output path/to/file.json
```
## Issues
### Point in polygon algorithm
To determine whether a point is inside a polygon the ray casting algorithm was used. The code structure was found [here](https://stackoverflow.com/a/15599478/4054411) and implemented for this task. With the data sample that was provided with the task the algorithm is giving the correct results but as I understand there might be potential issues.

As I understand from discussions there might be an issue if some edges of the polygon are parallel to the ray and if the point being tested is very close to an edge. These issues where not verified as there are no issues with the given data sample and time was limited for this task. However, this should be the main concern as this is the main functionality of this program.
### Code 
As I am not that familiar with Scala language, the code seems to be messy. Parsing JSON files results with `Either[Error, List[T]]` which seems to be weird and resulted with issues when generating results list.

Also, as this program was mainly written from various examples, some syntax seems to be from Scala 2 and some from Scala 3.

Command line arguments usage was reused from a previous project, and it also seems outdated. However, I did not find a good way to use named arguments with Scala on its own and various packages where too complicated to get familiar with in limited time.
## ChatGPT
As there was a requirement to not use any AI tools for this task, it was mostly the case... ChatGPT was used only to generate this code: 
```
val results: Either[Error, List[Result]] = (regions, locations) match {
  case (Right(regionList), Right(locationList)) =>
    val resultList = regionList.map { region =>
      val matchedLocationNames = region.polygons.flatMap { polygon =>
        locationList.filter(location => region.isPointInPolygon(location.point, polygon))
          .map(_.name)
      }
      Result(region.name, matchedLocationNames)
    }
    Right(resultList)
}
```
I've tried various ways to build the results list but I guess that as the `regions` and `locations` values where of the `Either` structure, it was this part that I could not figure out and ended up using AI to fix the code that was not fully working for me.

All other code was written using various online resources that are provided in the [References](#references) section.
## References
- [Parsing JSON arrays in Scala](https://medium.com/@djoepramono/how-to-parse-json-in-scala-c024cb44f66b)
- [Parsing JSON with Circe](https://www.baeldung.com/scala/circe-json)
- [Circe Semi-automatic Derivation](https://circe.github.io/circe/codecs/semiauto-derivation.html)
- [Scala project with Intellij and sbt](https://docs.scala-lang.org/getting-started/intellij-track/building-a-scala-project-with-intellij-and-sbt.html)
- [JSON library for Scala discussion](https://stackoverflow.com/questions/8054018/what-json-library-to-use-in-scala)
- [Read a file in Scala](https://www.baeldung.com/scala/read-file-from-resources)
- [Read a file in Scala](https://stackoverflow.com/questions/1284423/read-entire-file-in-scala)
- [.bsp directory](https://users.scala-lang.org/t/solved-should-i-add-bsp-directory-in-gitignore/6921)
- [Traits as Interfaces](https://docs.scala-lang.org/overviews/scala-book/traits-interfaces.html)
- [Traits with concrete and abstract methods](https://docs.scala-lang.org/overviews/scala-book/traits-abstract-mixins.html)
- [Circe decoder for generic case](https://stackoverflow.com/a/54401034)
- [Either, Left, Right](https://alvinalexander.com/scala/scala-either-left-right-example-option-some-none-null/)
- [Custom exceptions in Scala](https://stackoverflow.com/questions/38243530/custom-exception-in-scala)
- [Custom decoders](https://circe.github.io/circe/codecs/custom-codecs.html)
- [Circe and Scala versions](https://circe.github.io/circe/)
- [Manual JSON decoding with circe](https://medium.com/@djoepramono/how-to-parse-json-in-scala-c024cb44f66b)
- [Point in polygon](https://en.m.wikipedia.org/wiki/Point_in_polygon)
- [Point in polygon](https://stackoverflow.com/questions/11716268/point-in-polygon-algorithm)
- [Circe list as JSON](https://stackoverflow.com/questions/67918243/convert-list-to-json)
- [Scala nested iteration syntax](https://stackoverflow.com/questions/3634897/nested-iteration-in-scala)
- [Scala .gitignore template](https://alvinalexander.com/source-code/scala/sample-gitignore-file-scala-sbt-intellij-eclipse/)
- [Understanding Scala implicits](https://stackoverflow.com/questions/10375633/understanding-implicit-in-scala)
- [Scala for-comprehensions](https://docs.scala-lang.org/tour/for-comprehensions.html)
- [Pattern matching against map](https://stackoverflow.com/a/13536692/4054411)
- [Point in polygon in Scala](https://stackoverflow.com/questions/11640630/point-in-polygon-written-in-scala)
- [List values by index in Scala](https://www.baeldung.com/scala/list-get-item-by-index)
- [Unit tests in Scala](https://docs.scala-lang.org/getting-started/intellij-track/testing-scala-in-intellij-with-scalatest.html)