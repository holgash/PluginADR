package telas_swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

//import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectEvent;
import com.change_vision.jude.api.inf.project.ProjectEventListener;
import com.change_vision.jude.api.inf.ui.IPluginExtraTabView;
import com.change_vision.jude.api.inf.ui.ISelectionListener;

@SuppressWarnings({ "serial" })
public class ADPickView extends JPanel implements IPluginExtraTabView, ProjectEventListener{

	//private JComboBox diagram1 = new JComboBox();
	//private JComboBox diagram2 = new JComboBox();
	private JButton refinar = new JButton("Refinar Diagramas");
	//private ProjectAccessor projectAccessor;
	private MouseListener Mouse = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			JComponent componente = (JComponent) e.getSource();
			TransferHandler th = componente.getTransferHandler();
			th.exportAsDrag(componente, e, TransferHandler.COPY);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	
		private JTextField Diagrama1;
	private JTextField Diagrama2;
	
	public ADPickView() {
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout());
		Diagrama1 = new JTextField();
		Diagrama1.setAlignmentX(LEFT_ALIGNMENT);
		Diagrama2 = new JTextField();
		Diagrama2.setAlignmentX(RIGHT_ALIGNMENT);
		add(Diagrama1,BorderLayout.WEST);
		add(Diagrama2,BorderLayout.EAST);
		
		Diagrama1.addMouseListener(Mouse);
		Diagrama2.addMouseListener(Mouse);
		
		Diagrama1.setText("Diagrama 1");
		Diagrama2.setText("Diagrama 2");	
		add(refinar,BorderLayout.SOUTH);
		
	}
	
	/*private void updateComboBoxes() {
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
	}*/
	
	/*private void getDiagrams(List<IActivityDiagram> diagramList) {
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
	}*/
	
	/*private static INamedElement[] findElements(ProjectAccessor projectAccessor) throws ProjectNotFoundException {
		INamedElement[] foundElements = projectAccessor.findElements(new ModelFinder() {
			public boolean isTarget(INamedElement namedElement) {
				return namedElement instanceof IActivityDiagram;
			}
		});
		return foundElements;
	}*/


	@Override
	public void activated() {
		//updateComboBoxes();
		
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
		//updateComboBoxes();
		
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
