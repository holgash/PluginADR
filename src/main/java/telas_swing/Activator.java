package telas_swing;


import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.change_vision.jude.api.inf.view.IDiagramViewManager;

public class Activator implements BundleActivator {

	private static ResourceBundle resourceBundle;
	private AstahAPIUtils utils = new AstahAPIUtils();
	
	public void start(BundleContext context) {
		initializeResourceBundle();
	}

	private void initializeResourceBundle() {
		Locale locale = Locale.getDefault();
		resourceBundle = ResourceBundle.getBundle("plugin", locale);
	}
	
	public static String getLabel(String key) {
		return resourceBundle.getString(key);
	}

	public void stop(BundleContext context) {
	}
	
}
