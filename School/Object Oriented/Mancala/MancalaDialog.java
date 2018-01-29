package mancala;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MancalaDialog extends JDialog
{
	private Container frame;
	private int stoneCount;
	private DesignLayout[] designs;
	private DesignLayout design;
	private int width = 450;
	private int height = 200;
	
	public MancalaDialog(JFrame parent, DesignLayout[] designs)
	{
		super(parent, true);		
		this.designs = designs;
		stoneCount = 3;
		design = designs[0];
		
		frame = getContentPane();
		setSize(width,height);
		JPanel wholePanel = new JPanel(new BorderLayout());
		JPanel stonePanel = new JPanel();
		JPanel designPanel = new JPanel();

		JLabel chooseStones = new JLabel("Choose number of initial stones : ");
		stonePanel.add(chooseStones);
		JRadioButton three = new JRadioButton("Three", true);
		JRadioButton four = new JRadioButton("Four");
		ButtonGroup stoneGroup = new ButtonGroup();
		
		stonePanel.add(three);
		three.addActionListener(setStoneCount(3));
		stoneGroup.add(three);
		stonePanel.add(four);
		four.addActionListener(setStoneCount(4));
		stoneGroup.add(four);
		wholePanel.add(stonePanel, BorderLayout.NORTH);
		
		JLabel chooseDesign = new JLabel("Choose a design : ");
		designPanel.add(chooseDesign);
		
		JRadioButton[] designButtons  = new JRadioButton[designs.length];
		ButtonGroup designGroup = new ButtonGroup();
		for (int i = 0; i < designs.length; i++)
		{
			designButtons[i] = new JRadioButton(designs[i].getName(), i == 0);
			designPanel.add(designButtons[i]);
			designGroup.add(designButtons[i]);
			designButtons[i].addActionListener(setDesign(i));
		}
		wholePanel.add(designPanel, BorderLayout.CENTER);
		JButton startButton = new JButton("Start");
		
		startButton.addActionListener(new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				frame.setVisible(false);
				dispose();
			}
		});
		
		wholePanel.add(startButton, BorderLayout.SOUTH);
		
		
		frame.add(wholePanel);

	}

	public void display()
	{
		setVisible(true);		
	}
	
	public ActionListener setStoneCount(int stoneNum)
	{
		return new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				stoneCount = stoneNum;
			}
		};
	}
	
	public ActionListener setDesign(final int designNum)
	{
		return new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				design = designs[designNum];
			}
		};
	}
	
	public int getStoneNum()
	{
		return stoneCount;
	}
	
	public DesignLayout getDesign()
	{
		return design;
	}
}
