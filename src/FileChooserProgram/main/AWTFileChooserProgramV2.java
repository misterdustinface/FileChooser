package FileChooserProgram.main;

import main.EditorProgramMain;
import AWT.UI.AWTFileChooser;
import AWT.UI.AWTProgramWindow;
import AWT.UI.Mouse.AWTMouseUserDevice;
import AWT.UI.Mouse.AWTMouseUserDeviceDisplayLayer;
import AWT.UI2.AWTDisplay;
import AWT.UI2.AWTUIDrawer;
import AWT.UI2.AWTViewport;
import AWT.UI2.FixedDrawer;
import UI.UILayerManager;
import base.Application;

public class AWTFileChooserProgramV2 {
	
	public static void main(String[] args) {
		final AWTProgramWindow window = new AWTProgramWindow("File Chooser");
		window.setSize(600, 400);

		AWTFileChooser 		fileBrowser = new AWTFileChooser();
		AWTMouseUserDevice 	userDevice 	= new AWTMouseUserDevice();
		
		UILayerManager layerManager = new UILayerManager();
		layerManager.addLayer(fileBrowser);
		layerManager.addLayer(new AWTMouseUserDeviceDisplayLayer(userDevice));
		
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
		editorProgram.setMain(EditorProgramMain.create(layerManager, userDevice));
		
		//editorProgram.addComponent("Drawer", eventDrawer);
		editorProgram.addComponent("Drawer", fixedDrawer);
		
		//editorProgram.addComponent("Renderer", null); // SWING AWT IS RENDERER
		editorProgram.start();
	}

}
