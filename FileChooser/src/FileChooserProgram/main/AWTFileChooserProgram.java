package FileChooserProgram.main;


import generic.Application;
import generic.EditorProgramMain;
import AWT.UI.AWTEditorPanel;
import AWT.UI.AWTFileChooser;
import AWT.UI.AWTProgramWindow;
import AWT.UI.Mouse.AWTMouseUserDevice;
import AWT.UI.Mouse.AWTMouseUserDeviceDisplayLayer;
import UI.UILayerManager;

public class AWTFileChooserProgram {
	
	public static void main(String[] args) {
		AWTProgramWindow window = new AWTProgramWindow("File Chooser");
		window.setSize(600, 400);

		AWTFileChooser 		fileBrowser = new AWTFileChooser();
		AWTMouseUserDevice 	userDevice 	= new AWTMouseUserDevice();
		AWTEditorPanel 		editorPanel = new AWTEditorPanel(userDevice);
		UILayerManager		layerManager = new UILayerManager();
		layerManager.addLayer(fileBrowser);
		layerManager.addLayer(new AWTMouseUserDeviceDisplayLayer(userDevice));
		
		editorPanel.setLayerManager(layerManager);
		window.add(editorPanel);
		window.revalidate();
		
		// STARTS FILEBROWSER //
		fileBrowser.chooseFile();
		
		Application editorProgram = new Application();
		editorProgram.setMain(EditorProgramMain.create(layerManager, userDevice));
		editorProgram.start();
	}

}
