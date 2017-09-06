package org.jclub.coreclasses;

import java.io.IOException;
import java.util.Properties;





public class JclubGUIProperties extends Properties  {

	
	private static JclubGUIProperties props = null;

	private JclubGUIProperties() throws IOException
	{
		load(getClass().getResourceAsStream("/JclubGUI.properties"));
	}

	public static synchronized JclubGUIProperties getInstance() throws IOException
	{
		if (props == null)
		{
			props = new JclubGUIProperties();
		}
		return props;
	}

}
