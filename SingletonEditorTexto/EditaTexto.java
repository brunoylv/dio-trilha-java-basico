import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class EditaTexto extends JFrame implements ActionListener {
    JTextArea textArea;

    private EditaTexto() {
        super("Editor de Texto");
        setLayout(new BorderLayout());
        add(textArea = new JTextArea(5, 20), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton a = new JButton("Abrir");
        JButton b = new JButton("Salvar");
        JButton c = new JButton("Salvar Como");
        JButton d = new JButton("Fechar");

        a.addActionListener(this);
        b.addActionListener(this);
        c.addActionListener(this);
        d.addActionListener(this);

        buttonPanel.add(a);
        buttonPanel.add(b);
        buttonPanel.add(c);
        buttonPanel.add(d);
        add(buttonPanel, BorderLayout.WEST);
        pack();
        setVisible(true);
    }

    private static class InstanceHolder {
        public static final EditaTexto instancia = new EditaTexto();
    }

    public static EditaTexto getInstancia() {
        return InstanceHolder.instancia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Abrir":
                AbrirArquivo();
                break;
            case "Salvar":
                SalvarArquivo();
                break;
            case "Salvar Como":
                SalvarComoArquivo();
                break;
            case "Fechar":
                Fechar();
                break;
            default:
                break;
        }
    }

    private void AbrirArquivo() {
        String nomeArquivo = JOptionPane.showInputDialog(this, "Digite o nome do arquivo:");
        if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
            try (FileInputStream in = new FileInputStream(nomeArquivo);
                 Scanner sin = new Scanner(in)) {
                StringBuilder texto = new StringBuilder();
                while (sin.hasNextLine()) {
                    texto.append(sin.nextLine()).append("\n");
                }
                textArea.setText(texto.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void SalvarArquivo() {
        String nomeArquivo = "arquivo.txt";
        SalvarArquivoComo(nomeArquivo);
    }

    private void SalvarComoArquivo() {
        String nomeArquivo = JOptionPane.showInputDialog(this, "Digite o nome do arquivo:");
        if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
            SalvarArquivoComo(nomeArquivo);
        }
    }

    private void SalvarArquivoComo(String nomeArquivo) {
        try (FileOutputStream out = new FileOutputStream(nomeArquivo)) {
            out.write(textArea.getText().getBytes());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Fechar() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditaTexto editor = EditaTexto.getInstancia();
            editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}