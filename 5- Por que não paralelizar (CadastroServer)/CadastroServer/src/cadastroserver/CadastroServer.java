package cadastroserver;


// importações necessárias
import java.io.IOException;
import javax.swing.SwingUtilities;

public class CadastroServer {

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(CadastroServer::iniciarAplicacao);
    }

    private static void iniciarAplicacao() {
        SaidaFrame frame = new SaidaFrame();


        ThreadClient client = new ThreadClient(frame.getTexto());

        client.start();
    }
}

