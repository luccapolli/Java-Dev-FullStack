package cadastroserver;


// importações
import controller.MovimentoJpaController;
import controller.PessoaJpaController;
import controller.ProdutoJpaController;
import controller.UsuarioJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTextArea;

public class ThreadClient extends Thread {
    private final JTextArea entrada;

    public ThreadClient(JTextArea entrada) {
        this.entrada = entrada;
    }

    @Override
    public void run() {
        try {
            EntityManagerFactory em = Persistence.createEntityManagerFactory("CadastroServerPU");

            ProdutoJpaController ctrl = new ProdutoJpaController(em);
            UsuarioJpaController ctrlUsu = new UsuarioJpaController(em);
            PessoaJpaController ctrlPessoa = new PessoaJpaController(em);
            MovimentoJpaController ctrlMov = new MovimentoJpaController(em);

            ServerSocket serverSocket = new ServerSocket(4321);

            while (true) {
                Socket s1 = serverSocket.accept();

                CadastroThreadV2 cadastroThread = new CadastroThreadV2(ctrl, ctrlUsu, ctrlPessoa, ctrlMov, entrada, s1);
                cadastroThread.start();
            }
        } catch (Exception ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
