package telas_swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
//import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.model.IActivityDiagram;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.project.ModelFinder;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
//import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectEvent;
import com.change_vision.jude.api.inf.project.ProjectEventListener;
import com.change_vision.jude.api.inf.ui.IPluginExtraTabView;
import com.change_vision.jude.api.inf.ui.ISelectionListener;

@SuppressWarnings({ "serial" })
public class ADPickView extends JPanel implements IPluginExtraTabView, ProjectEventListener{

	private JComboBox diagram1 = new JComboBox();
	private JComboBox diagram2 = new JComboBox();
	private JRadioButton choice1 = new JRadioButton("Começo do diagrama");
	private JRadioButton choice2 = new JRadioButton("Dentro do diagrama");
	private JButton refinar = new JButton("Refinar Diagramas");
	private ProjectAccessor projectAccessor;
	
	public ADPickView() {
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		ButtonGroup bg = new ButtonGroup();
		bg.add(choice1);
		bg.add(choice2);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(diagram1,gbc);
		
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(diagram2,gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(choice1,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(choice2,gbc);
		
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = 2;
		gbc.gridy = 0;
		add(refinar,gbc);
		
	}
	
	private void updateComboBoxes() {
		List<IActivityDiagram> diagramList = new ArrayList<IActivityDiagram>();
		getDiagrams(diagramList);
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
	
	private void getDiagrams(List<IActivityDiagram> diagramList) {
		try {
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			INamedElement[] findElements = findElements(projectAccessor);
			for (int i = 0; i < findElements.length; i++) {
				if (findElements[i] instanceof IActivityDiagram) {
					diagramList.add((IActivityDiagram) findElements[i]);
				}
			}
		}catch (ProjectNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
		return Activator.getLabel("viewDescription");
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
