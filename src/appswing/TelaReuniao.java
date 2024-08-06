package appswing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.BorderLayout;
import java.util.List;

import modelo.Reuniao;
import modelo.Pessoa;

public class TelaReuniao extends JFrame {
    private Reuniao reuniao;
    private JLabel lblId;
    private JLabel lblData;
    private JLabel lblAssunto;
    private JTextArea textAreaParticipantes;
    private JLabel label;

    public TelaReuniao(Reuniao reuniao) {
        this.reuniao = reuniao;
        initialize();
    }

    private void initialize() {
        setTitle("Detalhes da Reunião");
        setBounds(100, 100, 297, 348);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // ID Label
        lblId = new JLabel("ID: " + reuniao.getId());
        lblId.setBounds(10, 25, 64, 14);
        getContentPane().add(lblId);

        // Data Label
        lblData = new JLabel("Data: " + reuniao.getData());
        lblData.setBounds(84, 25, 186, 14);
        getContentPane().add(lblData);

        // Assunto Label
        lblAssunto = new JLabel("Assunto: " + reuniao.getAssunto());
        lblAssunto.setBounds(10, 50, 200, 14);
        getContentPane().add(lblAssunto);

        // TextArea for Participants
        textAreaParticipantes = new JTextArea();
        textAreaParticipantes.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaParticipantes);
        scrollPane.setBounds(10, 100, 260, 200);
        getContentPane().add(scrollPane);

        // Populate Participants
        List<Pessoa> participantes = reuniao.getPessoas();
        StringBuilder sb = new StringBuilder();
        for (Pessoa pessoa : participantes) {
            sb.append(pessoa.getNome()).append("\n");
        }
        textAreaParticipantes.setText(sb.toString());
        
        label = new JLabel("Participantes:");
        label.setBounds(10, 75, 97, 14);
        getContentPane().add(label);
    }
}