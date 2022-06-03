package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Planner {
    private static Planner form;
    private JPanel panel1;
    private JPanel mainPanel;
    private String[] months = { "Styczen", "Luty", "Marzec", "Kwiecien", "Maj", "Czerwiec", "Lipiec", "Sierpien",
            "Wrzesien", "Pazdziernik", "Listopad", "Grudzien" };
    public JComboBox comboBox1;
    private JTextArea a1TextArea;
    private JTextArea a2TextArea;
    private JTextArea a3TextArea;
    private JTextArea a4TextArea;
    private JTextArea a5TextArea;
    private JTextArea a6TextArea;
    private JTextArea a7TextArea;
    private JTextArea a8TextArea;
    private JTextArea a9TextArea;
    private JTextArea a10TextArea;
    private JTextArea a11TextArea;
    private JTextArea a12TextArea;
    private JTextArea a13TextArea;
    private JTextArea a14TextArea;
    private JTextArea a15TextArea;
    private JTextArea a16TextArea;
    private JTextArea a17TextArea;
    private JTextArea a21TextArea;
    private JTextArea a20TextArea;
    private JTextArea a19TextArea;
    private JTextArea a18TextArea;
    private JTextArea a22TextArea;
    private JTextArea a28TextArea;
    private JTextArea a27TextArea;
    private JTextArea a26TextArea;
    private JTextArea a25TextArea;
    private JTextArea a24TextArea;
    private JTextArea a23TextArea;
    private JTextArea a30TextArea;
    private JTextArea a31TextArea;
    private JProgressBar progressBar1;
    private JLabel LabelBilans;
    private Double bilans;
    private JLabel Bilans;
    private JLabel LabelStan;
    private Double stan;
    private JLabel Stan;
    private JTextField textFieldEdycjaBilansu;
    private JTextArea a29TextArea;
    private JButton button1;


    public Planner(){
        bilans = 0.;
        stan = null;
        progressBar1.setMinimum(0);
        progressBar1.setMaximum(10000);
        Bilans.setText(bilans.toString());
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String plikZapisNazwa = comboBox1.getSelectedItem() + ".ser";
                    FileInputStream plikWczyt = new FileInputStream(plikZapisNazwa);
                    ObjectInputStream objektWczyt = new ObjectInputStream(plikWczyt);
                    Logi logi = (Logi) objektWczyt.readObject();
                    logi.odczyt(a1TextArea,
                            a2TextArea,
                            a3TextArea,
                            a4TextArea,
                            a5TextArea,
                            a6TextArea,
                            a7TextArea,
                            a8TextArea,
                            a9TextArea,
                            a10TextArea,
                            a11TextArea,
                            a12TextArea,
                            a13TextArea,
                            a14TextArea,
                            a15TextArea,
                            a16TextArea,
                            a17TextArea,
                            a21TextArea,
                            a20TextArea,
                            a19TextArea,
                            a18TextArea,
                            a22TextArea,
                            a28TextArea,
                            a27TextArea,
                            a26TextArea,
                            a25TextArea,
                            a24TextArea,
                            a23TextArea,
                            a30TextArea,
                            a31TextArea,
                            a29TextArea);
                    bilans = logi.getBilansMiesieczny();
                    if(stan == null) {
                        stan = logi.getStanKonta();
                        Stan.setText(stan.toString());
                        progressBar1.setValue(stan.intValue());
                    }
                    odswierz();
                }catch (IOException ex){
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        textFieldEdycjaBilansu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String zmiana = textFieldEdycjaBilansu.getText();
                Double zmianaDouble = Double.parseDouble(zmiana);
                bilans = bilans + zmianaDouble;
                stan  = stan + zmianaDouble;
                odswierz();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Logi logi = new Logi(a1TextArea,
                            a2TextArea,
                            a3TextArea,
                            a4TextArea,
                            a5TextArea,
                            a6TextArea,
                            a7TextArea,
                            a8TextArea,
                            a9TextArea,
                            a10TextArea,
                            a11TextArea,
                            a12TextArea,
                            a13TextArea,
                            a14TextArea,
                            a15TextArea,
                            a16TextArea,
                            a17TextArea,
                            a21TextArea,
                            a20TextArea,
                            a19TextArea,
                            a18TextArea,
                            a22TextArea,
                            a28TextArea,
                            a27TextArea,
                            a26TextArea,
                            a25TextArea,
                            a24TextArea,
                            a23TextArea,
                            a30TextArea,
                            a31TextArea,
                            a29TextArea,
                            bilans,
                            stan);
                    String plikZapisNazwa = comboBox1.getSelectedItem() + ".ser";
                    FileOutputStream plikZapis = new FileOutputStream(plikZapisNazwa);
                    ObjectOutputStream objektZapis = null;
                    objektZapis = new ObjectOutputStream(plikZapis);
                    objektZapis.writeObject(logi);
                    objektZapis.flush();
                    objektZapis.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    private void odswierz() {
        Bilans.setText(bilans.toString());
        Stan.setText(stan.toString());
        progressBar1.setValue(stan.intValue());
    }

    public static void initForm(Planner frm){
        int i = 0;
        List<String> cList = List.of(frm.months);
        for(i = 0; i < cList.size(); i++){
            frm.comboBox1.addItem(cList.get(i));
        }
        //table1 = new JTable(model);
    }

    public static void main(String[] args) {
        JFrame mainframe =new JFrame("Kalendarz");
        form = new Planner();
        mainframe.setContentPane(form.mainPanel);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.pack();
        mainframe.setVisible(true);
        initForm(form);
    }
}

class Logi implements Serializable{
    private ArrayList<String> dni;
    private Double bilansMiesieczny;
    transient private Double stanKonta;

    public Double getStanKonta() {
        return stanKonta;
    }

    public Double getBilansMiesieczny() {
        return bilansMiesieczny;
    }

    Logi(JTextArea a1TextArea,
         JTextArea a2TextArea,
         JTextArea a3TextArea,
         JTextArea a4TextArea,
         JTextArea a5TextArea,
         JTextArea a6TextArea,
         JTextArea a7TextArea,
         JTextArea a8TextArea,
         JTextArea a9TextArea,
         JTextArea a10TextArea,
         JTextArea a11TextArea,
         JTextArea a12TextArea,
         JTextArea a13TextArea,
         JTextArea a14TextArea,
         JTextArea a15TextArea,
         JTextArea a16TextArea,
         JTextArea a17TextArea,
         JTextArea a21TextArea,
         JTextArea a20TextArea,
         JTextArea a19TextArea,
         JTextArea a18TextArea,
         JTextArea a22TextArea,
         JTextArea a28TextArea,
         JTextArea a27TextArea,
         JTextArea a26TextArea,
         JTextArea a25TextArea,
         JTextArea a24TextArea,
         JTextArea a23TextArea,
         JTextArea a30TextArea,
         JTextArea a31TextArea,
         JTextArea a29TextArea,
         Double bilans,
         Double stan){
        this.dni = new ArrayList<String>();
        this.dni.add(a1TextArea.getText());
        this.dni.add(a2TextArea.getText());
        this.dni.add(a3TextArea.getText());
        this.dni.add(a4TextArea.getText());
        this.dni.add(a5TextArea.getText());
        this.dni.add(a6TextArea.getText());
        this.dni.add(a7TextArea.getText());
        this.dni.add(a8TextArea.getText());
        this.dni.add(a9TextArea.getText());
        this.dni.add(a10TextArea.getText());
        this.dni.add(a11TextArea.getText());
        this.dni.add(a12TextArea.getText());
        this.dni.add(a13TextArea.getText());
        this.dni.add(a14TextArea.getText());
        this.dni.add(a15TextArea.getText());
        this.dni.add(a16TextArea.getText());
        this.dni.add(a17TextArea.getText());
        this.dni.add(a18TextArea.getText());
        this.dni.add(a19TextArea.getText());
        this.dni.add(a20TextArea.getText());
        this.dni.add(a21TextArea.getText());
        this.dni.add(a22TextArea.getText());
        this.dni.add(a23TextArea.getText());
        this.dni.add(a24TextArea.getText());
        this.dni.add(a25TextArea.getText());
        this.dni.add(a26TextArea.getText());
        this.dni.add(a27TextArea.getText());
        this.dni.add(a28TextArea.getText());
        this.dni.add(a29TextArea.getText());
        this.dni.add(a30TextArea.getText());
        this.dni.add(a31TextArea.getText());

        this.bilansMiesieczny = bilans;
        this.stanKonta = stan;
        saveStanKonta();
    }

    void saveStanKonta(){
        try {
            FileWriter file = new FileWriter("stanKonta.txt");
            file.write(stanKonta.toString());
            file.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    void odczyt(JTextArea a1TextArea,
                JTextArea a2TextArea,
                JTextArea a3TextArea,
                JTextArea a4TextArea,
                JTextArea a5TextArea,
                JTextArea a6TextArea,
                JTextArea a7TextArea,
                JTextArea a8TextArea,
                JTextArea a9TextArea,
                JTextArea a10TextArea,
                JTextArea a11TextArea,
                JTextArea a12TextArea,
                JTextArea a13TextArea,
                JTextArea a14TextArea,
                JTextArea a15TextArea,
                JTextArea a16TextArea,
                JTextArea a17TextArea,
                JTextArea a21TextArea,
                JTextArea a20TextArea,
                JTextArea a19TextArea,
                JTextArea a18TextArea,
                JTextArea a22TextArea,
                JTextArea a28TextArea,
                JTextArea a27TextArea,
                JTextArea a26TextArea,
                JTextArea a25TextArea,
                JTextArea a24TextArea,
                JTextArea a23TextArea,
                JTextArea a30TextArea,
                JTextArea a31TextArea,
                JTextArea a29TextArea){
        a1TextArea.setText(dni.get(0));
        a2TextArea.setText(dni.get(1));
        a3TextArea.setText(dni.get(2));
        a4TextArea.setText(dni.get(3));
        a5TextArea.setText(dni.get(4));
        a6TextArea.setText(dni.get(5));
        a7TextArea.setText(dni.get(6));
        a8TextArea.setText(dni.get(7));
        a9TextArea.setText(dni.get(8));
        a10TextArea.setText(dni.get(9));
        a11TextArea.setText(dni.get(10));
        a12TextArea.setText(dni.get(11));
        a13TextArea.setText(dni.get(12));
        a14TextArea.setText(dni.get(13));
        a15TextArea.setText(dni.get(14));
        a16TextArea.setText(dni.get(15));
        a17TextArea.setText(dni.get(16));
        a18TextArea.setText(dni.get(17));
        a19TextArea.setText(dni.get(18));
        a20TextArea.setText(dni.get(19));
        a21TextArea.setText(dni.get(20));
        a22TextArea.setText(dni.get(21));
        a23TextArea.setText(dni.get(22));
        a24TextArea.setText(dni.get(23));
        a25TextArea.setText(dni.get(24));
        a26TextArea.setText(dni.get(25));
        a27TextArea.setText(dni.get(26));
        a28TextArea.setText(dni.get(27));
        a29TextArea.setText(dni.get(28));
        a30TextArea.setText(dni.get(29));
        a31TextArea.setText(dni.get(30));
        try {
            FileReader plikOdczyt = new FileReader("stanKonta.txt");
            Scanner scanner = new Scanner(plikOdczyt);
            String string = scanner.nextLine();
            stanKonta = Double.parseDouble(string);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}