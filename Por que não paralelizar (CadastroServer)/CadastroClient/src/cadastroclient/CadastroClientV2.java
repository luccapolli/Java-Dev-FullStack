package cadastroclient;

// importações
import java.io.*;
import java.net.*;
import java.util.List;
import model.Produto;

public class CadastroClientV2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket clientSocket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            clientSocket = new Socket(InetAddress.getByName("localhost"), 4321);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            autenticarUsuario(out, in, reader);

            menuPrincipal(out, in, reader);

        } finally {
            fecharRecursos(clientSocket, in, out);
        }
    }

    private static void autenticarUsuario(ObjectOutputStream out, ObjectInputStream in, BufferedReader reader) throws IOException, ClassNotFoundException {
        System.out.println("Digite o Usuário: ");
        out.writeObject(reader.readLine());

        System.out.println("Digite a Senha: ");
        out.writeObject(reader.readLine());

        String result = (String) in.readObject();
        if (!"ok".equals(result)) {
            System.out.println("Erro de login");
            System.exit(1);
        }
        System.out.println("Login com sucesso");
    }

    private static void menuPrincipal(ObjectOutputStream out, ObjectInputStream in, BufferedReader reader) throws IOException, ClassNotFoundException {
        String comando;
        do {
            // Exibe o menu principal
            System.out.println("╔═══════════════════════════╗");
            System.out.println("║     Menu Principal    ║");
            System.out.println("╚═══════════════════════════╝");
            System.out.println("Opções:");
            System.out.println("L - Listar");
            System.out.println("E - Entrada");
            System.out.println("S - Saída");
            System.out.println("X - Sair");
            System.out.print(" Digite o comando:");

            comando = reader.readLine().toUpperCase();

            out.writeObject(comando);

            if ("L".equalsIgnoreCase(comando)) {
                listarProdutos(in);
            } else if ("E".equalsIgnoreCase(comando) || "S".equalsIgnoreCase(comando)) {
                realizarMovimento(out, reader);
            }

        } while (!"X".equalsIgnoreCase(comando));
    }

    private static void listarProdutos(ObjectInputStream in) throws IOException, ClassNotFoundException {
        List<Produto> produtos = (List<Produto>) in.readObject();
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome());
        }
    }

    private static void realizarMovimento(ObjectOutputStream out, BufferedReader reader) throws IOException {
        System.out.println("Digite o id da Pessoa");
        out.writeObject(reader.readLine());

        System.out.println("Digite o id do Produto");
        out.writeObject(reader.readLine());

        System.out.println("Digite a quantidade do Produto");
        out.writeObject(reader.readLine());

        System.out.println("Digite o valor do Produto");
        out.writeObject(reader.readLine());
    }

    private static void fecharRecursos(Socket clientSocket, ObjectInputStream in, ObjectOutputStream out) {
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
