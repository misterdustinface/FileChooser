package FileChooserProgram.main;


import AWT.UI.AWTDefaultMouseUserDevice;
import AWT.UI.AWTEditorPanel;
import AWT.UI.AWTFileChooser;
import AWT.UI.AWTLayerManager;
import AWT.UI.AWTMouseUserDevice;
import AWT.UI.AWTProgramWindow;
import AWT.UI.AWTSimpleUserDeviceDisplayLayer;
import AWT.update.AWTProgramMain;

public class AWTFileChooserProgram {
	
	public static void main(String[] args) {
		AWTProgramWindow window = new AWTProgramWindow("File Chooser");
		window.setSize(600, 400);

		AWTFileChooser 		fileBrowser = new AWTFileChooser();
		AWTMouseUserDevice 	userDevice 	= new AWTDefaultMouseUserDevice();
		AWTEditorPanel 		editorPanel = new AWTEditorPanel(userDevice);
		AWTLayerManager		layerManager = new AWTLayerManager();
		layerManager.addLayer(fileBrowser);
		layerManager.addLayer(new AWTSimpleUserDeviceDisplayLayer(userDevice));
		
		editorPanel.setLayerManager(layerManager);
		
		AWTProgramMain main = new AWTProgramMain();
		main.setMouse(userDevice);
		main.setLayerManager(layerManager);
		Thread mainThread = new Thread(main);
		mainThread.start();
		
		window.add(editorPanel);
		window.revalidate();

		fileBrowser.chooseFile();
	}

}
