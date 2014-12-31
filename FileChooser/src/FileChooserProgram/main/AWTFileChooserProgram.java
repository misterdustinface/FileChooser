package FileChooserProgram.main;


import generic.EditorProgram;
import AWT.UI.AWTEditorPanel;
import AWT.UI.AWTFileChooser;
import AWT.UI.AWTProgramWindow;
import AWT.UI.Mouse.AWTDefaultMouseUserDevice;
import AWT.UI.Mouse.AWTMouseUserDevice;
import AWT.UI.Mouse.AWTSimpleUserDeviceDisplayLayer;
import AWT.update.AWTProgramMain;
import UI.UILayerManager;

public class AWTFileChooserProgram {
	
	public static void main(String[] args) {
		AWTProgramWindow window = new AWTProgramWindow("File Chooser");
		window.setSize(600, 400);

		AWTFileChooser 		fileBrowser = new AWTFileChooser();
		AWTMouseUserDevice 	userDevice 	= new AWTDefaultMouseUserDevice();
		AWTEditorPanel 		editorPanel = new AWTEditorPanel(userDevice);
		UILayerManager		layerManager = new UILayerManager();
		layerManager.addLayer(fileBrowser);
		layerManager.addLayer(new AWTSimpleUserDeviceDisplayLayer(userDevice));
		
		editorPanel.setLayerManager(layerManager);
		window.add(editorPanel);
		window.revalidate();
		
		// STARTS FILEBROWSER //
		fileBrowser.chooseFile();
		
		EditorProgram editorProgram = new EditorProgram();
		editorProgram.setMain(AWTProgramMain.create(layerManager, userDevice));
		editorProgram.start();
	}

}
