package core;

import java.io.PrintWriter;

/**
 *
 * @group MyLastJavaApp
 * @author Carlos André Ferrari <caferrari@gmail.com>
 */
public class Env {
	public static boolean post = false;
	public static String controller = "index";
	public static String action = "index";
	public static String view = null;
	public static String [] pars = null;
	public static PrintWriter out = null;
}
