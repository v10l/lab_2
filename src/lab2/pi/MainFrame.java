package lab2.pi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import static java.lang.Math.*;

@SuppressWarnings("serial")

public class MainFrame extends JFrame {

    // Размеры окна приложения в виде констант
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    // Текстовое поле для отображения результата
    private JTextField textFieldResult;
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup radioButtonsMemory = new ButtonGroup();

    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxFormulaImage = Box.createHorizontalBox();
    private Box hboxMemoryType = Box.createHorizontalBox();

    // вставка картинки
    private ImageIcon imageIcon = new ImageIcon("C:\\Users\\37529\\IdeaProjects\\qwe\\lab2\\f1.png");
    private JLabel imageLabel = new JLabel(imageIcon);
    // айди формул для использования
    private int formulaId = 1;
    private int memoryId = 1;


    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        Double up = Math.pow(Math.log(z) + (sin(Math.PI*z)*(sin(Math.PI*z))), 1/4);
        Double down = Math.pow((y*y+Math.exp(Math.cos(x))+ Math.sin(y)), Math.sin(x));
        return up/down;
    }
    // Формула №2 для рассчета
    public Double calculate2(Double x, Double y, Double z) {
        Double up = cbrt(y)*3*pow(z, x);
        Double down = cbrt(1+y*y*y);
        return up/down;
    }

    // метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JPanel imagePane = new JPanel();
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
                imageIcon.setImage(new ImageIcon("C:\\Users\\37529\\IdeaProjects\\qwe\\lab2\\f"+formulaId+".png").getImage());
                hboxFormulaImage.updateUI();
                imageLabel.revalidate();
                imageLabel.repaint();
                imagePane.updateUI();
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    //  метод для добавления кнопок памяти и переменных на панель
    private void addRadioButtonMemory(String buttonName, final int memoryId, Box hbox) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.memoryId = memoryId;
            }
        });
        radioButtonsMemory.add(button);
        hbox.add(button);
    }

    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);


        // JLabel с изображением
        hboxFormulaImage.add(imageLabel);
        //hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        hboxFormulaType.add(Box.createHorizontalStrut(20));
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        //hboxFormulaType.add(Box.createHorizontalGlue());
        //hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JLabel textFieldMemoryData1 = new JLabel("0");
        JLabel textFieldMemoryData2 = new JLabel("0");
        JLabel textFieldMemoryData3 = new JLabel("0");


        // кнопочки переменных
        Box hboxMemoryVariables = Box.createHorizontalBox();
        addRadioButtonMemory("Переменная 1", 4, hboxMemoryVariables);
        addRadioButtonMemory("Переменная 2", 5, hboxMemoryVariables);
        addRadioButtonMemory("Переменная 3", 6, hboxMemoryVariables);



        JButton buttonMemoryPlus = new JButton("M+");
        buttonMemoryPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(memoryId) {
                    case 1 : {
                        Double newValue = Double.parseDouble(textFieldResult.getText()) + Double.parseDouble(textFieldMemoryData1.getText());
                        textFieldMemoryData1.setText(newValue.toString());
                        break;
                    }
                    case 2 : {
                        Double newValue = Double.parseDouble(textFieldResult.getText()) + Double.parseDouble(textFieldMemoryData2.getText());
                        textFieldMemoryData2.setText(newValue.toString());
                        break;
                    }
                    case 3 : {
                        Double newValue = Double.parseDouble(textFieldResult.getText()) + Double.parseDouble(textFieldMemoryData3.getText());
                        textFieldMemoryData3.setText(newValue.toString());
                        break;
                    }
                    case 4 : {
                        Double newValue = Double.parseDouble(textFieldResult.getText()) + Double.parseDouble(textFieldX.getText());
                        textFieldX.setText(newValue.toString());
                        break;
                    }
                    case 5 : {
                        Double newValue = Double.parseDouble(textFieldResult.getText()) + Double.parseDouble(textFieldY.getText());
                        textFieldY.setText(newValue.toString());
                        break;
                    }
                    case 6 : {
                        Double newValue = Double.parseDouble(textFieldResult.getText()) + Double.parseDouble(textFieldZ.getText());
                        textFieldZ.setText(newValue.toString());
                        break;
                    }
                    default : {
                        break; }
                }
            }
        });

        JButton buttonMemoryClear = new JButton("MC");
        buttonMemoryClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(memoryId) {
                    case 1 : {
                        textFieldMemoryData1.setText("0");
                        break;
                    }
                    case 2 : {
                        textFieldMemoryData2.setText("0");
                        break;
                    }
                    case 3 : {
                        textFieldMemoryData3.setText("0");
                        break;
                    }
                    case 4 : {
                        textFieldX.setText("0");
                        break;
                    }
                    case 5 : {
                        textFieldY.setText("0");
                        break;
                    }
                    case 6 : {
                        textFieldZ.setText("0");
                        break;
                    }
                    default : {
                        break; }
                }
            }
        });

        // Коробка кнопок с работой с памятью
        hboxMemoryType.add(Box.createHorizontalGlue());
        addRadioButtonMemory("Память 1", 1, hboxMemoryType);
        addRadioButtonMemory("Память 2", 2, hboxMemoryType);
        addRadioButtonMemory("Память 3", 3, hboxMemoryType);
        radioButtonsMemory.setSelected(radioButtonsMemory.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());
       // hboxMemoryType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        Box hboxMemory = Box.createHorizontalBox();
        //hboxMemory.add(Box.createHorizontalStrut(100));
        hboxMemory.add(textFieldMemoryData1);
        hboxMemory.add(Box.createHorizontalStrut(20));
        hboxMemory.add(textFieldMemoryData2);
        hboxMemory.add(Box.createHorizontalStrut(20));
        hboxMemory.add(textFieldMemoryData3);

        Box hboxMemoryButtons = Box.createHorizontalBox();
        hboxMemoryButtons.add(buttonMemoryPlus);
        hboxMemoryButtons.add(Box.createHorizontalStrut(40));
        hboxMemoryButtons.add(buttonMemoryClear);



        // Поля ввода Х, Y, Z
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        // коробка с переменными
        Box hboxVariables = Box.createHorizontalBox();
        //hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));  // Отступ между Y и Z
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        //  область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        // коробка результата
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        //hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try { Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldY.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        // кнопки вычислить и очистить
        Box hboxButtons = Box.createHorizontalBox();
        // hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(Box.createHorizontalStrut(20));
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(40));
        hboxButtons.add(buttonReset);
       // hboxButtons.add(Box.createHorizontalGlue());
        //hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));


        // Связать области воедино в компоновке BoxLayout
        // вывод
        Box contentBox = Box.createVerticalBox();
         contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaImage);
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxMemoryVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxMemoryType);
        contentBox.add(hboxMemory);
        contentBox.add(hboxMemoryButtons);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}