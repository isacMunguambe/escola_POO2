/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package View.Visualizar;

import Model.DataAccessObject.AnoAcademicoDAO;
import Model.ValueObject.AnoAcademico;
import View.Create.*;
import View.MainMenu;
import View.SubMenu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.border.TitledBorder;



/**
 *
 * @author Gabriel
 */
public class AddAnoAcademico extends JComponent implements ActionListener, MouseListener {
    
    private JPanel masterPanel;
    private JPanel headerPanel;
    private JPanel leftPanel;
    private JLabel headerLabel, usr, img;
    private Font f1 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    
    private JButton[] northButton, btSearch;
    private JPanel btPanel, searchPanel;
    private JTextField search;
    
    private JPanel rightPanel;
    
    private ButtonGroup genderGroup;
    
    
    private JPanel alDataPanel, inner, inputsPanel[], genderPanel, opPanel;
    
    
    
    private JPanel classDataPanel, inner1, inputsPanel1[], genderPanel1, opPanel1;
    private JLabel sexo1;
    private JRadioButton male1, female1;
    private ButtonGroup genderGroup1;
    private JButton btnAnoAcademico, btnVoltar;
    private JTextField inputs1[];
    
    private JButton renovarSearch;
    
    private JButton update;
    
    private boolean st = false;
    private boolean st2 = false;
    
    public AddAnoAcademico() {
        
        
        masterPanel = new JPanel();
        headerPanel = new JPanel();
        leftPanel = new JPanel();
        
        
        // painel principal
        masterPanel.setBackground(Color.white);
        masterPanel.setBounds(0, 0, getWidth(), getHeight());
        masterPanel.setBorder(null);
        masterPanel.setLayout(new BorderLayout());
        
        
        // painel esquerdo componentes
        btPanel = new JPanel();
        //BoxLayout btLayout = new BoxLayout(btPanel,BoxLayout.Y_AXIS);
        btPanel.setBackground(null);
        //btPanel.setBounds(0, 150, 200, 200);
        
        
        btnVoltar = new JButton("");
        btnVoltar.setBounds(1060, 12, 45, 45);
        DefinirBackImagem();
        btnVoltar.setBackground(Color.white);
        btnVoltar.setBorderPainted(false);
        btnVoltar.addActionListener(this);
        add(btnVoltar);
        
        // righ panel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightPanel.setLayout(null);
        
        matricularPage();
        
        masterPanel.add(BorderLayout.CENTER, rightPanel);
        
        //cont.add(masterPanel);
        //setVisible(true);
        
        setSize(1100, 620);
        setLayout(new BorderLayout());
        add(masterPanel);
        show();
        
    }
    
    public void matricularPage(){
        rightPanel.removeAll();
        rightPanel.revalidate();
        rightPanel.repaint();
        
        
        Font br = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
        classDataPanel = new JPanel();
        classDataPanel.setBackground(Color.white);
        classDataPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(62, 62, 62), 1, true),
                "Dados acerca do Ano Academico",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                br,
                new Color(62, 62, 62)));
        classDataPanel.setBounds(370, 90, 420,450);
        classDataPanel.setLayout(null);
        
        inner1 =  new JPanel();
        inner1.setBackground(Color.white);
        inner1.setOpaque(false);
        inner1.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        inner1.setBounds(0, 0, 420, 240);
        BoxLayout encBox = new BoxLayout(inner1,BoxLayout.Y_AXIS);
        inner1.setLayout(encBox);
        
        
        inputs1 =  new JTextField[3];
        inputsPanel1 = new JPanel[3];
        st2 = true;
        
        for(int i=0; i<inputsPanel1.length; i++) {
            inputsPanel1[i] = new JPanel();
            inputsPanel1[i].setBackground(Color.white);
            inputsPanel1[i].setPreferredSize(new Dimension(205, 43));
            inputsPanel1[i].setMaximumSize(new Dimension(450, 43));
            inputsPanel1[i].setBorder(BorderFactory.createLineBorder(new Color(148, 148, 148), 1, true));
            inputsPanel1[i].setLayout(new GridLayout(1,1));
        }
        
        for(int i=0; i<inputs1.length; i++)
            inputs1[i] = new JTextField();
        
        for(int i=0; i<inputs1.length; i++)
            tfProperties(inputs1[i]);
        
        for(int i=0; i<inputs1.length; i++)
            inputs1[i].addMouseListener(this);
        
        
        ViewAnoAc viewAnoAc = new ViewAnoAc();
        
        
        inputs1[0].setText("ID");
        inputs1[1].setText("Ano");
        inputs1[2].setText("Trimestre");
        
        btnAnoAcademico = new JButton("Novo Ano Academico");
        btnAnoAcademico.addActionListener(this);
        btProperties(btnAnoAcademico);
        btnAnoAcademico.setBounds(10, 200, 400, 43);
        //addEncarregado.setPreferredSize(new Dimension(205,43));
        //addEncarregado.setMaximumSize(new Dimension(420,43));
        //addEncarregado.set
        
        for(int i=0; i<inputs1.length; i++)
            inputsPanel1[i].add(inputs1[i]);
        
        inner1.add(Box.createRigidArea(new Dimension(0,15)));
        
        for(int i=0; i<inputs1.length; i++){
            inner1.add(inputsPanel1[i]);
            inner1.add(Box.createRigidArea(new Dimension(0,5)));
        }
        
        //inner1.add(addEncarregado);
        classDataPanel.add(inner1);
        classDataPanel.add(btnAnoAcademico);
        
        
        rightPanel.add(classDataPanel);
        
        
    }
    
    
    private void DefinirBackImagem() {
        BufferedImage imgb = null;
        try {
            imgb = ImageIO.read(new File(System.getProperty("user.dir") + "/src/View/img/back_to_24px.png"));
        } catch (IOException e) {
        }
        Image dimg = imgb.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        btnVoltar.setIcon(new ImageIcon(dimg));
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVoltar) {
            showForm(new ViewAnoAc());
        }
        
        if (e.getSource() == btnAnoAcademico) {
            AnoAcademicoDAO acDAO = new AnoAcademicoDAO();

            if(inputs1[0].getText().equalsIgnoreCase("ID") || inputs1[0].getText().equalsIgnoreCase("") ||
                    inputs1[1].getText().equalsIgnoreCase("Ano") || inputs1[1].getText().equalsIgnoreCase("") ||
                    inputs1[2].getText().equalsIgnoreCase("Trimestre") || inputs1[2].getText().equalsIgnoreCase("") ||
                    (Integer.parseInt(inputs1[1].getText())<1990 || Integer.parseInt(inputs1[1].getText())>2023) ||
                    (Integer.parseInt(inputs1[2].getText())<1 || Integer.parseInt(inputs1[2].getText())>3)){
                
                JOptionPane.showMessageDialog(null, "Verifique se inseriu os dados corretamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                
            }else{
                AnoAcademico ac = new AnoAcademico(Integer.parseInt(inputs1[0].getText()), Integer.parseInt(inputs1[1].getText()),
                        Integer.parseInt(inputs1[2].getText()));
                try {
                    acDAO.adicionaAc(ac);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    //Logger.getLogger(AddClasse.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == inputs1[0]) {
            if(inputs1[0].getText().equals("ID"))
                tfChanges(inputs1[0]);
        }
        
        if(e.getSource() == inputs1[1]) {
            if(inputs1[1].getText().equals("Ano"))
                tfChanges(inputs1[1]);
        }
        
        if(e.getSource() == inputs1[2]) {
            if(inputs1[2].getText().equals("Trimestre"))
                tfChanges(inputs1[2]);
        }
        
        
        //======================================================================
        
        
        
        
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        
        if(e.getSource() == inputs1[0]) {
            if(inputs1[0].getText().equals("ID") || inputs1[0].getText().equals(""))
                inputs1[0].setText("ID");
        }
        
        if(e.getSource() == inputs1[1]) {
            if(inputs1[1].getText().equals("Ano") || inputs1[1].getText().equals(""))
                inputs1[1].setText("Ano");
        }
        
        if(e.getSource() == inputs1[2]) {
            if(inputs1[2].getText().equals("Trimestre") || inputs1[2].getText().equals(""))
                inputs1[2].setText("Trimestre");
        }
        
        
        // -----------------------------------------------------------------------
        
        
    }
    
    
    private void tfChanges(JTextField tfd) {
        
        tfd.setText("");
        
        
    }
    
    private void tfProperties(JTextField tf) {
        Font text = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        tf.setFont(text);
        tf.setPreferredSize(new Dimension(200, 30));
        tf.setMaximumSize(new Dimension(200, 30));
        tf.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        tf.setBackground(Color.white);
        tf.setForeground(new Color(62, 62, 62));
        tf.setLocation(0, 0);
        tf.setOpaque(true);
    }
    
    private void btProperties(JButton button) {
        Font f2 = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        
        //button.setSize(200, 50);
        button.setPreferredSize(new Dimension(200,50));
        button.setMaximumSize(new Dimension(200,50));
        //button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setFont(f2);
        button.setForeground(Color.white);
        button.setBackground(new Color(62, 62, 62));
        button.setFocusable(false);
    }
    
    private void showForm(Component com) {
        BorderLayout layout = (BorderLayout) MainMenu.main.getLayout();
        if (layout.getLayoutComponent(BorderLayout.CENTER) != null) {
            MainMenu.main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        }
        
        MainMenu.main.add(com, BorderLayout.CENTER);
        MainMenu.main.repaint();
        MainMenu.main.revalidate();
    }
    
    
}
