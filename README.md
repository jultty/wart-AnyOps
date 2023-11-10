# wart-AnyOps

Just a best-effort attempt at hacking some useful rules for my personal use.

The checks implemented here are mainly related to preventing operations between numerical and non-numerical "primitives", preventing weirdness such as:

```scala
3 + 0 // 3
3 + 'a' // 100
'a' + 'a' // 194
'3' + 0 // 99
```

In the example above, Scala will perform [implicit conversions](https://docs.scala-lang.org/scala3/book/ca-implicit-conversions.html) through which the chars will be converted to their corresponding ASCII values.

Operations with `+` and strings are already covered by the built-in wart [StringPlusAny](https://github.com/wartremover/wartremover/blob/master/core/src/main/scala-3/org/wartremover/warts/StringPlusAny.scala), upon which implementation this repo is largely based.

## Caveats
This wart will **not** work if your project uses Scala 2 or does cross-compiling to Scala 2.

## Usage

This project is built using [sbt](https://www.scala-sbt.org/). Provided you have this repository cloned and a [Scala environment configured](https://www.scala-lang.org/download/), you can compile code with `sbt compile`, run it with `sbt run`, and launch a REPL with `sbt console`.
