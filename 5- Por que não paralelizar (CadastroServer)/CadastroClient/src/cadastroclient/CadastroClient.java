package cadastroclient;

// importações
import java.io.*;
import java.net.*;
import java.util.List;
import model.Produto;

public class CadastroClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 4321);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            autenticarUsuario(out, in);

            listarProdutos(out, in);

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void autenticarUsuario(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite o Usuário: ");
        String usuario = reader.readLine();
        System.out.println("Digite a Senha: ");
        String senha = reader.readLine();

        out.writeObject(usuario);
        out.writeObject(senha);

        String result = (String) in.readObject();
        if (!"ok".equals(result)) {
            System.out.println("Erro de login");
            System.exit(1);
        }
        System.out.println("Usuário conectado com sucesso!!");
    }

    private static void listarProdutos(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException {
        out.writeObject("L");

        List<Produto> produtos = (List<Produto>) in.readObject();
        
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome());
        }
    }
}
