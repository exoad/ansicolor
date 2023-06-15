// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.ansicolors.samples;

import com.jackmeng.ansicolors.jm_Ansi;

public class Logo
{
  public static void main(String... a)
  {
    System.out.print("\n\t");
    System.out.println(
        jm_Ansi.make().underline().red().toString("a") + " " + jm_Ansi.make().bold().green().magenta_bg().toString("n")
            + " " + jm_Ansi.make().strikethrough().blue().white_bg().toString("s")
            + " " + jm_Ansi.make().magenta().blue_bg().toString("i") + " "
            + jm_Ansi.make().yellow().blue_fg().toString("c") + " " + jm_Ansi.make().green().toString("o")
            + " " + jm_Ansi.make().cyan().red_bg().toString("l") + " " + jm_Ansi.make().gray().green_fg().toString("o")
            + " " + jm_Ansi.make().white_bg().italic().toString("r"));
    System.out.println();
  }
}