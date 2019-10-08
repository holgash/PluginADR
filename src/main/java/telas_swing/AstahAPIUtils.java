package telas_swing;

import javax.swing.JFrame;

import com.change_vision.jude.api.inf.editor.ActivityDiagramEditor;
import com.change_vision.jude.api.inf.editor.BasicModelEditor;
import com.change_vision.jude.api.inf.editor.IDiagramEditorFactory;
import com.change_vision.jude.api.inf.editor.IModelEditorFactory;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectAccessorFactory;
import com.change_vision.jude.api.inf.view.IDiagramViewManager;
import com.change_vision.jude.api.inf.view.IViewManager;

/**
 * A class containing utilities easing astah* API operation
 * Instance must be created before this class is used.
 */
public class AstahAPIUtils {

  /**
   * Get diagramViewManager
   */
  public IDiagramViewManager getDiagramViewManager() {
    IViewManager viewManager = getViewManager();
      IDiagramViewManager diagramViewManager = viewManager.getDiagramViewManager();
    return diagramViewManager;
  }


  /**
   * Get ClassDiagramEditor by which models on class diagrams can be modified
 * @throws Exception 
   */
  public ActivityDiagramEditor getActivityDiagramEditor() throws Exception {
    try {
      return getDiagramEditorFactory().getActivityDiagramEditor();
    } catch (InvalidUsingException e) {
      throw new Exception(e);
    }
  }

  /**
   * Get BasicModelEditor by which basic models can be modified
   *
   * @return BasicModelEditor
 * @throws Exception 
   */
  public BasicModelEditor getBasicModelEditor() throws Exception {
    try {
      return getModelEditorFactory().getBasicModelEditor();
    } catch (InvalidEditingException e) {
      throw new Exception(e);
    }
  }

  /**
   * Get ProjectAccessor by which astah* project can be operated
   */
  @SuppressWarnings("deprecation")
public ProjectAccessor getProjectAccessor() {
    ProjectAccessor projectAccessor = null;
    try {
      projectAccessor = ProjectAccessorFactory.getProjectAccessor();
    } catch (ClassNotFoundException e) {
          throw new IllegalStateException(e);
    }
    if(projectAccessor == null) throw new IllegalStateException("projectAccessor is null.");
    return projectAccessor;
  }

  /**
   * Get JFrame representing the main window of astah*
   *
   * @return JFrame
 * @throws InvalidUsingException 
   */
  public JFrame getMainFrame() throws InvalidUsingException {
    return getProjectAccessor().getViewManager().getMainFrame();
  }

  /**
   * Get the edition of running astah*
   */
  public String getEdition() {
    return getProjectAccessor().getAstahEdition();
  }

  private IViewManager getViewManager() {
    ProjectAccessor projectAccessor = getProjectAccessor();
    IViewManager viewManager;
	try {
		viewManager = projectAccessor.getViewManager();
	} catch (InvalidUsingException e) {
		throw new IllegalStateException("ViewManager is null.");
	}
    
    return viewManager;
  }

  private IModelEditorFactory getModelEditorFactory() {
    ProjectAccessor projectAccessor = getProjectAccessor();
    IModelEditorFactory modelEditorFactory = projectAccessor.getModelEditorFactory();
    if(modelEditorFactory == null) throw new IllegalStateException("modelEditorFactory is null.");
    return modelEditorFactory;
  }

  private IDiagramEditorFactory getDiagramEditorFactory() {
    ProjectAccessor projectAccessor = getProjectAccessor();
    IDiagramEditorFactory diagramEditorFactory = projectAccessor.getDiagramEditorFactory();
    if(diagramEditorFactory == null) throw new IllegalStateException("diagramEditorFactory is null.");
    return diagramEditorFactory;
  }

}