package kata7.app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import kata7.control.BlockPresenter;
import kata7.control.Command;
import kata7.control.DownCommand;
import kata7.control.LeftCommand;
import kata7.control.RightCommand;
import kata7.control.UpCommand;
import kata7.model.Block;
import kata7.view.BlockDisplay;

public class Main extends JFrame{
    private Block block;
    private BlockDisplay blockDisplay;
    private BlockPresenter blockPresenter;
    private Map<String,Command> commands;

    public static void main(String[] args) {
        new Main().execute();
    }

    public Main() {
        this.setTitle("Block shifter");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 762);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(),BorderLayout.SOUTH);
    }

    private void execute() {
        this.block = new Block(4,4);
        this.blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands = createCommand();
        this.setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel panel = new BlockPanel(Block.MAX);
        this.blockDisplay = panel;
        return panel;
    }

    private Map<String, Command> createCommand() {
        Map<String,Command> commands = new HashMap<>();
        commands.put("U", new UpCommand(block));
        commands.put("D", new DownCommand(block));
        commands.put("L", new LeftCommand(block));
        commands.put("R", new RightCommand(block));
        return commands;
    }

    private JMenuBar toolbar() {
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        jMenuBar.add(button("D"));
        jMenuBar.add(button("L"));
        jMenuBar.add(button("R"));
        jMenuBar.add(button("U"));
        return jMenuBar;
    }

    private JButton button(String command) {
        JButton button = new JButton(command);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(command).execute();
            }
        });
        return button;
    }
    
}
