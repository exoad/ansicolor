<!--
 Software created by Jack Meng (AKA exoad) and licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.
-->

> Read the documentation [here](https://exoad.github.io/jm_ansi_docs/)!

<h1 align="center">ansicolor</h1>

<h3 align="center">Simple <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">ANSI</a> text stylelizer for Java</h3>

**If you want to make your CLI output for your Java application look "better," you should consider formatting with ANSI!** However, it is quite painful to constantly your strings with those ANSI codes, so use `ansicolor`! It is created to simplify this tedious process by utilizing the cascading pattern:

```java
import static com.jackmeng.ansicolor.jm_Ansi.*;
...
make().red().bold().print("ANSI Colors");
System.out.println(make().blue().underline().toString("Different variations!"));
```
> **Cascading? What's that?**
> Cascading in OOP is basically calling a bunch of methods on the same object. You most likely have seen this with Java's `StringBuilder::append`.

### Overview

#### Basic Comprehension

* Everything **starts** with the `make` function:

  ```java
  jm_Ansi.make();

  jm_Ansi.make("Hello World!");
  ```
  > Whichever one you want to use is up to you, it's all syntax!


* You **insert** jm_Ansi.make("Hello World").yellow().bold().print();
after the `make` function.<br>These functions range from setting the text background to red (`red_bg()`) to making your text blink (`blink_fast()`)!

  ```java
  jm_Ansi.make().red_bg().blink_fast();

  jm_Ansi.make("Hello World").red_bg().blink_fast();
  ```
  > **Note**: Ordering doesn't matter for the most part, but if an attribute can override or appears more than once in the cascading, *the later one takes precedence*.

* Everything **ends** with a jm_Ansi.make("Hello World").yellow().bold().print();
 that can return a string `toString()` or print it directly `print()`

  ```java
  String f_String = jm_Ansi.make().red_bg().blink_fast().toString("Hello World");

  jm_Ansi.make("Hello World").red_bg().blink_fast().print();
  ```
  > **Note**: If didn't supply an argument with the `make()` function, you can always supply your content at the end! On the other hand, if you supplied the argument beforehand, you can still supply at the end and it will append to your original string.

#### Turn it off!

Without having to add bloated checks or completely refactoring your codebase to turn off ANSI for a certain condition. This library has a single switch that you can turn on and off ANSI coloring at any time.

**No more:**

```java
String toPrint_Str = "Hello World";
if(useAnsi())
  jm_Ansi.make(toPrint_Str).yellow().bold().print();
else
  System.out.println(toPrint_Str);
```

**Instead:**

```java
jm_Ansi.use_ansi(useAnsi());
...
String toPrint_Str = "Hello World";
jm_Ansi.make(toPrint_Str).yellow().bold().print();
```

> **Warning**: Detecting whether ANSI is supported is still reliant on you, **the programmer** to detect at the moment.

#### String manipulation

Besides just formatting text, what happens if you want to append text or do some string manipulation like insert a character into the original string? Without disrupting cascading, you can use a bunch of functions implemented!

```java
jm_Ansi.make(myStr).insert(0, "Insert this here", 0, 4);
jm_Ansi.make(myStr).append("Append this");
```

> **Warning**: There are methods that block cascading if they are called. Avoid methods that don't return `_ansi` unless absolutely necessary.

#### Under the hood

If you would like debug what a certain call is printing, you can call it with `escaped()` as the builder function to get the escaped version of all of the codes!

```java
String toPrint_Str = "Hello World";
String f_EscapedStr = jm_Ansi.make(toPrint_Str).yellow().bold().escaped();
```

### Road Map

Even though styling is quite simple to make, it is important to know that this library is made to simplify that process of formatting while providing as much room for customizing that process. Here is the roadmap for this library and what will be added soon:

> This is just a simple overview of what's to see. View the complete roadmap [here](roadmap.md)

1. **Positional Based Formatting** - String Manipulation but you can determine where to end formatting and start a new one without ending the cascading chain.
2. **String Interpolation Formatting** - Instead of using cascading, use simplified codes that you can interpolate your strings with to format.
3. **Convert to HTML** - What happens if you want to render the finalized formatted text to an HTML doc or Java Swing's `JEditorPane`?
4. **Detect ANSI Support** - So you won't have to write platform dependent code to detect this.
5. **Custom palettes** - Change the default looks of calls like `red()` or `blue()` and replace them with your own colors

### Use it!

You can download this package right off of GitHub [here](https://github.com/exoad/ansicolor/packages/)!

> **Warning**: This package does not provide ANSI support detection, so you must do it manually.




