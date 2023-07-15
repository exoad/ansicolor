<!--
 Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.
-->

# `ansicolor` Roadmap

Things here are **never final** and may change at any time!

> **Warning**: The code format used is only used for better viewing!

## **Positional Based Formatting**

#### Motive

Currently, if you want to use multiple formatted strings in a single concat you would have to approach with the following style:

```java

System.out.println(
  jm_Ansi.make().red_bg().white().toString("ERROR!")
  + " "
  + jm_Ansi.make().yellow().toString("Please report this error to www.github.com")
);
```

This introduces annoyances of multiple declarations by breaking the cascading pattern.

#### The Fix

There are two approaches to this:

1. Make a separate `make()` that handles this

2. Interoperate with the current manipulation library

Whichever path is taken, the end result would look like something like the following:

```java
System.out.println(
  jm_Ansi._make()
    .red_bg().white().render("ERROR!")
      .append(" ")
        .yellow().toString("Please report this error to www.github.com");
);
```

This one liner style utilizes a builder function that appends to another pool for the formatted String `render`. Each formatted String would be tokenized via the non cascade breaking `render` function in order to achieve this effect. Each of these tokens would be stored as the following:

```java
List<String> {
  make().red_bg().white().render("ERROR!"),
  " ",
  yellow().toString("Please report this error to www.github.com")
};
```


## String Interpolation Formatting

#### Motive
Some users might not want to use cascading, so it might be more viable to utilize a String based interpolation method:

```java
make().red_bg().yellow().bold().underline().blink_fast().toString("String");
```

This also goes back to **Positional Based Formatting** which can help to alleviate some burden of appending multiple formatted strings.

#### The Fix

Create a new a `render()` function directly on the `jm_Ansi` class that can do this.

A syntax could be as simple as using markup tags:

```html
<red><yellow></yellow></red>
```

It could also go without saying that customization is also required for customizing these elements:
1. Begin brace: ex. `<`
2. End brace: ex. `>`
3. Last tag indicator: ex. `/`


## Convert to HTML

#### Motive

Althought it might be helpful display this in a standard ANSI supported console, sometimes it can be more usable for the developer to redirect output to another area.

For example, in use like Java Swing's `JEditorPane` this can be used to provide the developer with a way to redirect pre exsiting print statements elsewhere.

#### The Fix

[WIP]


