package cadastroserver;


// importações
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class SaidaFrame extends JDialog {

    private JTextArea texto;

    public SaidaFrame() {
        inicializarComponentes();
        configurarJanela();
    }

    private void inicializarComponentes() {
        setBounds(100, 100, 400, 300);

        texto = new JTextArea();

        add(texto);
    }

    private void configurarJanela() {
        setVisible(true);

        setModal(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setTitle("Saída");
    }

    public JTextArea getTexto() {
        return texto;
    }

    public void setTexto(JTextArea texto) {
        this.texto = texto;
    }
}
