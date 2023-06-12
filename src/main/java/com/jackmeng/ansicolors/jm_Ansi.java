// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.ansicolors;

import java.io.PrintStream;
import java.util.Objects;

/**
 * <h1>jm_Ansi - ANSI Coloring</h1>
 * <p>
 * ANSI provides most consoles with a coloring format that you can use. This
 * simple class helps you to create cascading calls for creating concise and
 * readable ANSI display codes. You don't have to mess with making your own and
 * potentially
 * making it overburdened and verbose to use!
 * </p>
 * <p>
 * Use this project in your code for displaying colorful console messages! All
 * you have to is call {@link #make(String)} and then you have a whole bunch of
 * formatting options to utilize :)
 * </p>
 * <p>
 * You can also use functions similar to StringBuilder's class but however with
 * a very limited subset of the methods available to you. It is limited because
 * in order to create a cascading call pattern, certain functions that must
 * return a type other than the cascading type will destroy cascading. However,
 * some of them can be accessed like {@link String#charAt(int)} by calling the
 * {@link _ansi#content()}
 * method which will return the String representation of the content for you to
 * mess with. Other than that, all other methods will strictly return the
 * current _ansi instance to continue the cascading pattern.
 * </p>
 * <h2>
 * Simple usage guide
 * </h2>
 * Everything is based off of the concept of cascading. You might have seen this
 * with the class {@link StringBuilder}
 * which you can constantly call methods like
 * {@link StringBuilder#append(String)} and
 * {@link StringBuilder#insert(int, String)}
 * in a long chain like <br>
 * {@code newStringBuilder("Hello").append("World").append("!")}. This is the
 * exact concept used with {@link jm_Ansi}!
 * <br>
 * To start your cascade, you first must acquire an instance from
 * {@link jm_Ansi} which you can do via two methods:
 * <ul>
 * <li>{@link jm_Ansi#make(String)} - Supplies the payload (AKA the string you
 * want to format first).
 * This introduces the pattern of apply first, format second:
 * {@code make(payload).format().format()...toString()}
 * </li>
 * <li>{@link jm_Ansi#make()} - Intended for the programmer to supply the
 * payload later via a method like {@link _ansi#toString(String)}.
 * This introduces the pattern of format first, apply second:
 * {@code make().format().format()...toString(payload)}
 * </li>
 * </ul>
 * Whichever one you use does not effect anything as both are interchangeable.
 * It all comes down to personal preferences on how you like to order your
 * code :).
 * <br>
 * To print the message to say {@code System.out}, you can just put it like so:
 * <br>
 * {@code System.out.print(make().format().format().format()} as the inbuilt
 * {@link _ansi#toString()} takes care
 * of how to handle printing. However, this provides an automatic RESET
 * character which makes sure the formatted content
 * is only applied to that portion of the text. If you want to leave out this
 * automatic RESET character, don't use
 * these methods:
 * <ul>
 * <li>{@link _ansi#toString()}</li>
 * <li>{@link _ansi#toString(String)}</li>
 * </ul>
 * Instead you should use {@link _ansi#render()}
 * <br>
 * <h3>
 * Formatting
 * </h3>
 * Formatting is the way to modify the content which is given by
 * {@link _ansi#content()}. To modify text you use cascading
 * calls within the {@link _ansi} class. Some of these methods include:
 * <ul>
 * <li>
 * <p>
 * {@link _ansi#red()} - Makes the <em>FOREGROUND</em> of the content RED
 * </p>
 * </li>
 * <li>
 * <p>
 * {@link _ansi#green()} - Makes the <em>FOREGROUND</em> of the content GREEN
 * </p>
 * </li>
 * <li>
 * <p>
 * {@link _ansi#blue()} - Makes the <em>FOREGROUND</em> of the content BLUE
 * </p>
 * </li>
 * <li>
 * <p>
 * {@link _ansi#bold()} - Makes the content appear BOLDED (high intensity)
 * </p>
 * </li>
 * </ul>
 * You also notice that certain colored functions like
 * {@link _ansi#magenta_fg()} or {@link _ansi#yellow_bg()} has a
 * suffix like {@code _fg} and {@code _bg}. These are naming schemes and are
 * followed by for all colors:
 * <ul>
 * <li>
 * {@code _fg} or color with no suffix - FOREGROUND coloring
 * </li>
 * <li>
 * {@code _bg} - BACKGROUND coloring
 * </li>
 * </ul>
 * <em>
 * <strong>
 * [!] You should always keep in mind that different platforms support different
 * formatting looks. Sometimes
 * they don't support formatting at all!
 * </strong>
 * </em>
 * <h2>Example Usages</h2>
 * <ul>
 * <li>
 * <p>
 * <strong>Printing RED Spectrum (RGB)</strong>
 *
 * <pre>
 * for (int i = 0; i &#60;= 255; i++)
 *   System.out.print(jm_Ansi.make(" ").rgb_bg(i, 0, 0));
 * </pre>
 * </p>
 * </li>
 * <li>
 * <p>
 * <strong>Printing a Bolded Warning Text</strong>
 *
 * <pre>
 * System.out.println(
 *     jm_Ansi.make("[!]").yellow_bg().black_bg().blink_fast() + " " + jm_Ansi.make("FAILED").bold().yellow_fg());
 * </pre>
 * </p>
 * </li>
 * </ul>
 *
 * @author Jack Meng
 * @version 1.0
 * @see StringBuilder
 * @see _ansi
 */
public final class jm_Ansi
{
    private jm_Ansi()
    {
    }

    /**
     * Determines if the ANSI printer should return the ANSI formatted or not. If
     * {@link #use_ansi()} returns false, raw content would be returned without
     * formatting.
     */
    private static boolean enable_ansi = true;

    /**
     * Whether to use ANSI or not
     *
     * @param e
     *          true = on, false = off
     */
    public static synchronized void use_ansi(boolean e)
    {
        jm_Ansi.enable_ansi = e;
    }

    /**
     * The current state of using ANSI or not
     *
     * @return To use ansi or not
     */
    public static boolean use_ansi()
    {
        return jm_Ansi.enable_ansi;
    }

    /**
     * Hard coded RESET the renders
     *
     * @return "\033[0;m"
     */
    public static String reset()
    {
        return "\033[0m";
    }

    private final StringBuilder $ansi_content = new StringBuilder(); // set to empty string for now

    /**
     * Use this to grab an ANSI create instance. Primarily used if you want to
     * submit your payload before formatting such that the semantics are like so:
     * "make(content).color1().bold1()...toString()"
     * <p>
     * Which one you use is up to personal preferences, there are no differences
     * except for looks (or is it?). Contrary to {@link #make()}
     *
     * @return An ANSI create instance
     */
    public static _ansi make(String content)
    {
        return new _ansi(content);
    }

    /**
     * Use this to grab an ANSI create instance. Primarily used if you want to
     * submit your payload <strong>AFTER</strong> formatting such that the semantics
     * are like so: "make().color1().bold1()...toString(content)"
     * <p>
     * Which one you use is up to personal preferences, there are no differences
     * except for looks (or is it?). Contrary to {@link #make(String)}
     *
     * @return An ANSI create instance
     */
    public static _ansi make()
    {
        return new _ansi();
    }

    /**
     * <p>
     * This class is where all of the formatting happens. Acquire an instance via
     * {@link jm_Ansi#make()} or {@link jm_Ansi#make(String)}
     * where you can use cascading to create formatting.
     * </p>
     * <p>
     * For 4bit colors you should check out: <a href=
     * "https://i.stack.imgur.com/9UVnC.png">https://i.stack.imgur.com/9UVnC.png</a>
     * </p>
     * <strong>[!] You must always be aware that colors look different depending on
     * the system!</strong>
     *
     * @author Jack Meng
     */
    public static final class _ansi
    {
        private final jm_Ansi instance;
        private final StringBuilder content;

        private _ansi(String content)
        {
            this.content = new StringBuilder(Objects.requireNonNull(content));
            this.instance = new jm_Ansi();
        }

        /**
         * If the user provides no String, then we assume that the payload (i.e.
         * content) will be submitted later.
         */
        private _ansi()
        {
            this("");
        }

        /**
         * ANSI code maker. Constructs the ANSI code given and provides further cascading
         * simplifications.
         *
         * @param content
         *          ANSI Code
         * @return Cascaded instance
         */
        private _ansi make(String content)
        {
            instance.$ansi_content.append(content).append(";");
            return this;
        }

        // ########### START CONTENT MODIFY ########### //

        /**
         * <p>
         * Get what is currently treated as raw content in this builder. Raw content
         * signifies anything that is TO BE FORMATTED and not the things that do the
         * formatting.
         * </p>
         * <strong>[!] THIS METHOD DESTROYS CASCADING</strong>
         *
         * @return The raw text to format
         */
        public String content()
        {
            return this.content.toString();
        }

        /**
         * Extracted method
         */
        public _ansi append(boolean b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(char b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(char[] b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(char[] b, int offset, int len)
        {
            content.append(b, offset, len);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(CharSequence b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(CharSequence b, int start, int end)
        {
            content.append(b, start, end);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(double b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(float b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(int b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(long b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(Object b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(String b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi append(StringBuffer b)
        {
            content.append(b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi delete(int start, int end)
        {
            content.delete(start, end);
            return this;
        }

        /**
         * Extracted method
         */
        public StringBuilder deleteCharAt(int index)
        {
            return content.deleteCharAt(index);
        }

        /**
         * Extracted method
         *
         * <p>
         * <strong>[!] This method destroys cascading!</strong>
         * </p>
         */
        public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
        {
            content.getChars(srcBegin, srcEnd, dst, dstBegin);
        }

        /**
         * Extracted method
         * <p>
         * <strong>[!] This method destroys cascading!</strong>
         * </p>
         */
        public int indexOf(String str)
        {
            return content.indexOf(str);
        }

        /**
         * Extracted method
         * <p>
         * <strong>[!] This method destroys cascading!</strong>
         * </p>
         */
        public int indexOf(String str, int fromIndex)
        {
            return content.indexOf(str, fromIndex);
        }

        /**
         * Extracted method
         */
        public _ansi insert(int offset, boolean b)
        {
            content.insert(offset, b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int offset, char b)
        {
            content.insert(offset, b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int offset, char[] b)
        {
            content.insert(offset, b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int index, char[] b, int offset, int len)
        {
            content.insert(index, b, offset, len);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int dstOffset, CharSequence s)
        {
            content.insert(dstOffset, s);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int dstOffset, CharSequence s, int start, int end)
        {
            content.insert(dstOffset, s, start, end);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int offset, double b)
        {
            content.insert(offset, b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int offset, float b)
        {
            content.insert(offset, b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int offset, int b)
        {
            content.insert(offset, b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int offset, long b)
        {
            content.insert(offset, b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int offset, Object b)
        {
            content.insert(offset, b);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi insert(int offset, String b)
        {
            content.insert(offset, b);
            return this;
        }

        /**
         * Extracted method
         * <p>
         * <strong>[!] This method destroys cascading!</strong>
         * </p>
         */
        public int lastIndexOf(String str)
        {
            return content.lastIndexOf(str);
        }

        /**
         * Extracted method
         * <p>
         * <strong>[!] This method destroys cascading!</strong>
         * </p>
         */
        public int lastIndexOf(String str, int fromIndex)
        {
            return content.lastIndexOf(str, fromIndex);
        }

        /**
         * Extracted method
         * <p>
         * <strong>[!] This method destroys cascading!</strong>
         * </p>
         */
        public int length()
        {
            return content.length();
        }

        /**
         * Extracted method
         */
        public _ansi replace(int start, int end, String str)
        {
            content.replace(start, end, str);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi reverse()
        {
            content.reverse();
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi setCharAt(int index, char ch)
        {
            content.setCharAt(index, ch);
            return this;
        }

        /**
         * Extracted method
         */
        public _ansi setLength(int newLength)
        {
            content.setLength(newLength);
            return this;
        }

        /**
         * Extracted method
         * <p>
         * <strong>[!] This method destroys cascading!</strong>
         * </p>
         */
        public CharSequence subSequence(int start, int end)
        {
            return content.subSequence(start, end);
        }

        /**
         * Extracted method
         * <p>
         * <strong>[!] This method destroys cascading!</strong>
         * </p>
         */
        public String substring(int start)
        {
            return content.substring(start);
        }

        /**
         * Extracted method
         * <p>
         * <strong>[!] This method destroys cascading!</strong>
         * </p>
         */
        public String substring(int start, int end)
        {
            return content.substring(start, end);
        }

        /**
         * Extracted method
         */
        public _ansi trimToSize()
        {
            content.trimToSize();
            return this;
        }

        // ########### END CONTENT MODIFY ########### //

        // ########### START 4 BIT COLORS ########### //

        public _ansi black_fg()
        {
            return make("30");
        }

        /**
         * Calls {@link #black_fg()}
         */
        public _ansi black()
        {
            return black_fg();
        }

        public _ansi black_bg()
        {
            return make("40");
        }

        public _ansi red_fg()
        {
            return make("31");
        }

        /**
         * Calls {@link #red_fg()}
         */
        public _ansi red()
        {
            return red_fg();
        }

        public _ansi red_bg()
        {
            return make("41");
        }

        public _ansi green_fg()
        {
            return make("32");
        }

        /**
         * Calls {@link #green_fg()}
         */
        public _ansi green()
        {
            return green_fg();
        }

        public _ansi green_bg()
        {
            return make("42");
        }

        public _ansi yellow_fg()
        {
            return make("33");
        }

        /**
         * Calls {@link #yellow_fg()}
         */
        public _ansi yellow()
        {
            return yellow_fg();
        }

        public _ansi yellow_bg()
        {
            return make("43");
        }

        public _ansi blue_fg()
        {
            return make("34");
        }

        /**
         * Calls {@link #blue_fg()}
         */
        public _ansi blue()
        {
            return blue_fg();
        }

        public _ansi blue_bg()
        {
            return make("43");
        }

        public _ansi magenta_fg()
        {
            return make("35");
        }

        /**
         * Calls {@link #magenta_fg()}
         */
        public _ansi magenta()
        {
            return magenta_fg();
        }

        public _ansi magenta_bg()
        {
            return make("45");
        }

        public _ansi cyan_fg()
        {
            return make("36");
        }

        /**
         * Calls {@link #cyan_fg()}
         */
        public _ansi cyan()
        {
            return cyan_fg();
        }

        public _ansi cyan_bg()
        {
            return make("46");
        }

        public _ansi white_fg()
        {
            return make("37");
        }

        /**
         * NOTE: On certain renderers, white_fg and white_bg will return a GRAY color,
         * while on some it returns WHITE!
         * Calls {@link #white_fg()}
         */
        public _ansi gray()
        {
            return white_fg();
        }

        public _ansi white_bg()
        {
            return make("47");
        }

        public _ansi bright_black_fg()
        {
            return make("90");
        }

        /**
         * NOTE: On certain renderers, bright_black_fg and bright_black_bg will return
         * the same colors as black_fg and black_bg respectively.
         * Calls {@link #bright_black_fg()}
         */
        public _ansi dark_gray()
        {
            return bright_black_fg();
        }

        public _ansi bright_black_bg()
        {
            return make("100");
        }

        public _ansi bright_red_fg()
        {
            return make("91");
        }

        /**
         * Calls {@link #bright_red_fg()}
         */
        public _ansi bright_red()
        {
            return bright_red_fg();
        }

        public _ansi bright_red_bg()
        {
            return make("101");
        }

        public _ansi bright_green_fg()
        {
            return make("92");
        }

        /**
         * Calls {@link #bright_green_fg()}
         */
        public _ansi bright_green()
        {
            return bright_green_fg();
        }

        public _ansi bright_green_bg()
        {
            return make("102");
        }

        public _ansi bright_yellow_fg()
        {
            return make("93");
        }

        /**
         * Calls {@link #bright_yellow_fg()}
         */
        public _ansi bright_yellow()
        {
            return bright_yellow_fg();
        }

        public _ansi bright_yellow_bg()
        {
            return make("103");
        }

        public _ansi bright_blue_fg()
        {
            return make("94");
        }

        /**
         * Calls {@link #bright_blue_fg()}
         */
        public _ansi bright_blue()
        {
            return bright_blue_fg();
        }

        public _ansi bright_blue_bg()
        {
            return make("104");
        }

        public _ansi bright_magenta_fg()
        {
            return make("95");
        }

        /**
         * Calls {@link #bright_magenta_fg()}
         */
        public _ansi bright_magenta()
        {
            return bright_magenta_fg();
        }

        public _ansi bright_magenta_bg()
        {
            return make("105");
        }

        public _ansi bright_cyan_fg()
        {
            return make("96");
        }

        /**
         * Calls {@link #bright_cyan_fg()}
         */
        public _ansi bright_cyan()
        {
            return bright_cyan_fg();
        }

        public _ansi bright_cyan_bg()
        {
            return make("106");
        }

        public _ansi bright_white_fg()
        {
            return make("97");
        }

        /**
         * Calls {@link #bright_white_fg()}
         */
        public _ansi white()
        {
            return bright_white_fg();
        }

        public _ansi bright_white_bg()
        {
            return make("107");
        }

        // ########### END 4 BIT COLORS ########### //

        // ########### START FONT EFFECTS ########### //

        /**
         * Just use jm_Ansi::reset
         *
         * @return _ansi object
         */
        public _ansi reset()
        {
            return make("0");
        }

        public _ansi bold()
        {
            return make("1");
        }

        /**
         * Not widely supported
         */
        public _ansi faint()
        {
            return make("2");
        }

        /**
         * Not widely supported. Some renderers treat this as invert the text
         */
        public _ansi italic()
        {
            return make("3");
        }

        public _ansi underline()
        {
            return make("4");
        }

        public _ansi blink_slow()
        {
            return make("5");
        }

        public _ansi blink_fast()
        {
            return make("6");
        }

        /**
         * Swaps the foreground and background
         */
        public _ansi swap_fg_bg()
        {
            return make("7");
        }

        /**
         * Hides the text. Not widely supported.
         */
        public _ansi hide()
        {
            return make("8");
        }

        /**
         * Does not conceal. Not widely supported.
         */
        public _ansi strikethrough()
        {
            return make("9");
        }

        /**
         * Regular font effects
         */
        public _ansi primary()
        {
            return make("10");
        }

        public _ansi font_1()
        {
            return make("11");
        }

        public _ansi font_2()
        {
            return make("12");
        }

        public _ansi font_3()
        {
            return make("13");
        }

        public _ansi font_4()
        {
            return make("14");
        }

        public _ansi font_5()
        {
            return make("15");
        }

        public _ansi font_6()
        {
            return make("16");
        }

        public _ansi font_7()
        {
            return make("17");
        }

        public _ansi font_8()
        {
            return make("18");
        }

        public _ansi font_9()
        {
            return make("19");
        }

        /**
         * Unsupported.
         */
        public _ansi fraktur()
        {
            return make("20");
        }

        /**
         * Sometimes treated as double underline. Both are not widely supported.
         */
        public _ansi no_bold()
        {
            return make("21");
        }

        /**
         * No bold or low thickness
         */
        public _ansi normal()
        {
            return make("22");
        }

        /**
         * no_fraktur as well
         */
        public _ansi no_italic()
        {
            return make("23");
        }

        /**
         * Both single and double underlines eliminated
         */
        public _ansi no_underline()
        {
            return make("24");
        }

        public _ansi no_blink()
        {
            return make("25");
        }

        public _ansi no_inverse()
        {
            return make("27");
        }

        public _ansi no_hide()
        {
            return make("28");
        }

        public _ansi no_strikethrough()
        {
            return make("29");
        }

        public _ansi framed()
        {
            return make("51");
        }

        public _ansi encircled()
        {
            return make("52");
        }

        public _ansi overlined()
        {
            return make("53");
        }

        public _ansi no_framed_no_encircled()
        {
            return make("54");
        }

        public _ansi no_overlined()
        {
            return make("55");
        }

        // ! 60-65 are almost never supported, so are left out

        // ########### END FONT EFFECTS ########### //

        // ########### START CONTROL ########### //

        /**
         * Mostly used for internal creation of colors. You should rarely use this if at
         * all.
         * <p>
         * Tells to treat the following elements (in the ANSI code) as a color for the
         * text's foreground
         */
        public _ansi fg()
        {
            return make("38");
        }

        /**
         * Mostly used for internal creation of colors. You should rarely use this if at
         * all.
         * <p>
         * Tells to treat the following elements (in the ANSI code) as a color for the
         * text's background
         */
        public _ansi bg()
        {
            return make("48");
        }

        // ########### END CONTROL ########### //

        // ########### START MISC ########### //

        public _ansi make(int... codes)
        {
            for (int r : codes)
                make(r + "");
            return this;
        }

        public _ansi rgb_fg(int[] rgb)
        {
            return rgb_fg(rgb[0], rgb[1], rgb[2]);
        }

        public _ansi rgb_fg(int r, int g, int b)
        {
            r = use_Help.clamp(0, 255, r); // RED | R
            g = use_Help.clamp(0, 255, g); // GREEN | G
            b = use_Help.clamp(0, 255, b); // BLUE | B
            fg();
            make("2");
            make(r);
            make(g);
            make(b);
            return this;
        }

        public _ansi rgb_bg(int[] rgb)
        {
            return rgb_bg(rgb[0], rgb[1], rgb[2]);
        }

        public _ansi rgb_bg(int r, int g, int b)
        {
            r = use_Help.clamp(0, 255, r); // RED | R
            g = use_Help.clamp(0, 255, g); // GREEN | G
            b = use_Help.clamp(0, 255, b); // BLUE | B
            bg();
            make("2");
            make(r);
            make(g);
            make(b);
            return this;
        }

        /**
         * Apply your own custom list of ANSI codes. It is not suggested using this
         * unless there are codes outside this builder's range.
         *
         * @param i
         *          ANSI codes (vararg)
         * @return Instance for cascading
         */
        public _ansi apply(int... i)
        {
            return make(i);
        }

        // ########### END MISC ########### //

        /**
         * Should not be called by the programmer unless absolutely necessary. The
         * {@link #toString()} and {@link #escaped()} all call this method in order to
         * validate the String.
         * <p>
         * This method does not create the final values for rendering
         *
         * @return String -> A validated String for printing.
         */
        public StringBuilder end()
        {

            return instance.$ansi_content.deleteCharAt(instance.$ansi_content.lastIndexOf(";"));
        }

        /**
         * This method also
         * appends a RESET character at the end to reset all ANSI coloring after.
         */
        @Override public String toString()
        {
            return "\033[" + end().append("m").append(content.toString()).append(jm_Ansi.reset()).toString();
        }

        /**
         * Allows for content to be added at the end (either end). This method also
         * appends a RESET character at the end to reset all ANSI coloring after.
         *
         * @param content
         *          the content to append to the original content before returning the
         *          string
         * @return The formatted content
         */
        public String toString(String content)
        {
            return "\033[" + end().append("m").append(this.content.append(content)).append(jm_Ansi.reset()).toString();
        }

        /**
         * Does not provide a RESET at the end as compared to {@link #toString()} or
         * {@link #toString(String)}
         *
         * @return The finalized string without a rest character
         */
        public String render()
        {
            return "\033[" + end().append("m").append(this.content.append(content)).toString();
        }

        /**
         * For debugging purposes. Displays the built value by escaping all the
         * necessary components.
         *
         * @return String
         */
        public String escaped()
        {
            return "\\033[" + end().append("m").append(content.toString()).append("\\033[0m").toString();
        }

        public void print(PrintStream e)
        {
            e.print(toString());
        }

        public void println(PrintStream e)
        {
            e.println(toString());
        }

        public void print()
        {
            this.print(System.out);
        }

        public void println()
        {
            this.println(System.out);
        }

    }

}