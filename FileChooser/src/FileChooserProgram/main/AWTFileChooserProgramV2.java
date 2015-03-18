package FileChooserProgram.main;

import generic.Application;
import AWT.UI.AWTFileChooser;
import AWT.UI.AWTProgramWindow;
import AWT.UI.Mouse.AWTDefaultMouseUserDevice;
import AWT.UI.Mouse.AWTMouseUserDevice;
import AWT.UI.Mouse.AWTSimpleUserDeviceDisplayLayer;
import AWT.UI2.AWTDisplay;
import AWT.UI2.AWTUIDrawer;
import AWT.UI2.AWTViewport;
import AWT.UI2.FixedDrawer;
import AWT.update.AWTProgramMain;
import UI.UILayerManager;

public class AWTFileChooserProgramV2 {
	
	public static void main(String[] args) {
		final AWTProgramWindow window = new AWTProgramWindow("File Chooser");
		window.setSize(600, 400);

		AWTFileChooser 		fileBrowser = new AWTFileChooser();
		AWTMouseUserDevice 	userDevice 	= new AWTDefaultMouseUserDevice();
		
		UILayerManager layerManager = new UILayerManager();
		layerManager.addLayer(fileBrowser);
		layerManager.addLayer(new AWTSimpleUserDeviceDisplayLayer(userDevice));
		
		final AWTViewport menuView = new AWTViewport();
		menuView.setSize(600, 400);	

		final AWTDisplay display = new AWTDisplay(userDevice);
		display.addView(menuView);
		
		final AWTUIDrawer uiDrawer = new AWTUIDrawer();
		uiDrawer.setDrawing(menuView);
		uiDrawer.setLayerManager(layerManager);
		
		/**
		 * Draw only when mouse events happen within the display
		 */
		//MouseEventDrawer eventDrawer = new MouseEventDrawer(display, uiDrawer);
		
		/**
		 * Draw at a fixed rate so there are no mystery bugs later on
		 */
		FixedDrawer fixedDrawer = new FixedDrawer(uiDrawer);
		fixedDrawer.setDrawsPerSecond(60);

		window.add(display);
		window.revalidate();
		
		// STARTS FILEBROWSER //
		fileBrowser.chooseFile();
		
		Application editorProgram = new Application();
		editorProgram.setMain(AWTProgramMain.create(layerManager, userDevice));
		
		//editorProgram.setDrawer(eventDrawer);
		editorProgram.setDrawer(fixedDrawer);
		
		//editorProgram.setRenderer(null); // SWING AWT IS RENDERER
		editorProgram.start();
	}

}
