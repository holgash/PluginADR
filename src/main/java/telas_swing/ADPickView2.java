package telas_swing;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.change_vision.jude.api.inf.project.ProjectEvent;
import com.change_vision.jude.api.inf.project.ProjectEventListener;
import com.change_vision.jude.api.inf.ui.IPluginExtraTabView;
import com.change_vision.jude.api.inf.ui.ISelectionListener;

public class ADPickView2 extends JPanel implements IPluginExtraTabView, ProjectEventListener{
	
	private JRadioButton choice1;
	private JRadioButton choice2;
	private JTextField diagram1;
	private JTextField diagram2;
	private JButton refButton;
	
	public ADPickView2() {
		initComponents();
	}
	
	public void initComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
		choice1 = new JRadioButton("Começo do diagrama");
		choice2 = new JRadioButton("Dentro do diagrama");
		ButtonGroup bg = new ButtonGroup();
		bg.add(choice1);
		bg.add(choice2);
		
		diagram1= new JTextField("Diagrama 1");
		diagram2= new JTextField("Diagrama 2");
		
		refButton= new JButton("Refinar");
		
		setLayout(new GridBagLayout());
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
		add(refButton,gbc);
		
	}
	
	
	@Override
	public void projectChanged(ProjectEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void projectClosed(ProjectEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void projectOpened(ProjectEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activated() {
		// TODO Auto-generated method stub
		
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

}
