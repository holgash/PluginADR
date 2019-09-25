package telas_swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.model.IActivityDiagram;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.project.ModelFinder;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectEvent;
import com.change_vision.jude.api.inf.project.ProjectEventListener;
import com.change_vision.jude.api.inf.ui.IPluginExtraTabView;
import com.change_vision.jude.api.inf.ui.ISelectionListener;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ADPickView extends JPanel implements IPluginExtraTabView, ProjectEventListener{

	//private DefaultTableModel model = new DefaultTableModel();
	//private JTable table = new JTable(model);
	private IModel currentProject;
	private JComboBox diagram1 = new JComboBox();
	private JComboBox diagram2 = new JComboBox();
	private JButton refinar = new JButton("Refinar Diagramas");
	
	public ADPickView() {
		initComponents();
	}
	
	private void initComponents() {
		//diagram1.setModel(new DefaultComboBoxModel(new String[] {"diagrama 1", "diagrama 2"}));
		//diagram2.setModel(new DefaultComboBoxModel(new String[] {"diagrama 1", "diagrama 2"}));
		setLayout(new BorderLayout());
		add(diagram1,BorderLayout.WEST);
		add(diagram2,BorderLayout.EAST);
		add(refinar,BorderLayout.SOUTH);
		addProjectEventListener();
	}
	
   /*
   private void updateTable() {
	   model.getDataVector().clear();
	   List<IClass> classList = new ArrayList<IClass>();
	   getAllClasses(currentProject,classList);
	   for (IClass cl : classList) {
          String name = cl.getName();
          String upperCase = name.toUpperCase();
          if (name.charAt(0) != upperCase.charAt(0)) {
        	  model.addRow(new Object[]{name, 
              Activator.getLabel("validationErrorMessageFirstLetter")});
          } else if (upperCase.equals(name)) {
        	  model.addRow(new Object[]{name, 
              Activator.getLabel("validationErrorMessageAcronym")});
          } else if (name.contains(" ")) {
        	  model.addRow(new Object[]{name, 
              Activator.getLabel("validationErrorMessageBlankSpace")});
          }
	   }
   }
   
   
   
	private void getAllClasses(INamedElement element, List<IClass> classList) {
		if (element instanceof IPackage) {
	       for (INamedElement ownedNamedElement : ((IPackage) element).getOwnedElements()) {
	    	   getAllClasses(ownedNamedElement, classList);
	       }
	     } else if (element instanceof IClass) {
	    	 classList.add((IClass) element);
	     }
	}*/
	
	private void updateComboBoxes() {
		List<IActivityDiagram> diagramList = new ArrayList<IActivityDiagram>();
		getAllDiagrams(currentProject,diagramList);
		List<String> names = new ArrayList<String>();
		for(IActivityDiagram dg: diagramList) {
			 names.add(dg.getName());
		}
		if(names.size()!= 0) {
			diagram1.setModel(new DefaultComboBoxModel(names.toArray()));
			diagram2.setModel(new DefaultComboBoxModel(names.toArray()));
		}
		else {
			diagram1.setModel(new DefaultComboBoxModel(new String[] {"Não Existem Diagramas"}));
			diagram2.setModel(new DefaultComboBoxModel(new String[] {"Não Existem Diagramas"}));
		}
	}
	
	private void getAllDiagrams(INamedElement element, List<IActivityDiagram> diagramList) {
		/*if (element instanceof IPackage) {
	       for (INamedElement ownedNamedElement : ((IPackage) element).getOwnedElements()) {
	    	   getAllDiagrams(ownedNamedElement, diagramList);
	       }
	     } else if (element instanceof IActivityDiagram) {
	    	 diagramList.add((IActivityDiagram) element);
	     }*/
		
		try {
			INamedElement[] findElements = findElements((ProjectAccessor) currentProject);
			for (int i = 0; i < findElements.length; i++) {
				if (findElements[i] instanceof IActivityDiagram) {
					diagramList.add((IActivityDiagram) findElements[i]);
				}
			}
		} catch (ProjectNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static INamedElement[] findElements(ProjectAccessor projectAccessor) throws ProjectNotFoundException {
		INamedElement[] foundElements = projectAccessor.findElements(new ModelFinder() {
			public boolean isTarget(INamedElement namedElement) {
				return namedElement instanceof IActivityDiagram;
			}
		});
		return foundElements;
	}

	private void addProjectEventListener() {
		try {
			AstahAPI api = AstahAPI.getAstahAPI();
			ProjectAccessor projectAcessor = api.getProjectAccessor();
			projectAcessor.addProjectEventListener(this);
			currentProject = projectAcessor.getProject();
		} catch (ProjectNotFoundException e) {
			System.out.println(Activator.getLabel("projectNoOpenedTitle"));
	    } catch (ClassNotFoundException e) {
	    	System.out.println(Activator.getLabel("unexpectedError"));
	    }
	}

	@Override
	public void activated() {
		updateComboBoxes();
		
	}

	@Override
	public void addSelectionListener(ISelectionListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		return Activator.getLabel("viewTitle");
	}

	@Override
	public void projectChanged(ProjectEvent arg0) {
		updateComboBoxes();
		
	}

	@Override
	public void projectClosed(ProjectEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void projectOpened(ProjectEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
