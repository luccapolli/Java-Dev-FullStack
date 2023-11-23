package cadastrobd.model;


// importações
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    private ConectorBD conectorBD;

    public PessoaFisicaDAO(ConectorBD conectorBD) {
        this.conectorBD = conectorBD;
    }

    public PessoaFisica getPessoa(int idPessoa) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConectorBD.getConnection();
            String sql = "SELECT p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf " +
                         "FROM dbo.Pessoa p " +
                         "INNER JOIN dbo.PessoaFisica pf ON p.idPessoa = pf.idPessoa " +
                         "WHERE p.idPessoa = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idPessoa);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String logradouro = resultSet.getString("logradouro");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String cpf = resultSet.getString("cpf");
                return new PessoaFisica(idPessoa, nome, logradouro, cidade, estado, telefone, email, cpf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(statement, resultSet, connection);
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConectorBD.getConnection();
            String sql = "SELECT p.idPessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pf.cpf FROM dbo.Pessoa p INNER JOIN dbo.PessoaFisica pf ON p.idPessoa = pf.idPessoa";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idf = resultSet.getInt("idPessoa");
                String nome = resultSet.getString("nome");
                String logradouro = resultSet.getString("logradouro");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String cpf = resultSet.getString("cpf");
                PessoaFisica pessoa = new PessoaFisica(idf, nome, logradouro, cidade, estado, telefone, email, cpf);
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(statement, resultSet, connection);
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoa) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConectorBD.getConnection();
            String sqlPessoa = "INSERT INTO dbo.Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sqlPessoa);
            int id = SequenceManager.getValue("PessoaSeq");
            statement.setInt(1, id);
            statement.setString(2, pessoa.getNome());
            statement.setString(3, pessoa.getLogradouro());
            statement.setString(4, pessoa.getCidade());
            statement.setString(5, pessoa.getEstado());
            statement.setString(6, pessoa.getTelefone());
            statement.setString(7, pessoa.getEmail());
            statement.executeUpdate();

            String sqlPessoaFisica = "INSERT INTO dbo.PessoaFisica (idPessoa, cpf) VALUES (?, ?)";
            statement = connection.prepareStatement(sqlPessoaFisica);
            statement.setInt(1, id);
            statement.setString(2, pessoa.getCpf());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(statement, null, connection);
        }
    }

    public void alterar(PessoaFisica pessoa) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConectorBD.getConnection();
            String sqlPessoa = "UPDATE dbo.Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
            statement = connection.prepareStatement(sqlPessoa);
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getLogradouro());
            statement.setString(3, pessoa.getCidade());
            statement.setString(4, pessoa.getEstado());
            statement.setString(5, pessoa.getTelefone());
            statement.setString(6, pessoa.getEmail());
            statement.setInt(7, pessoa.getIdPessoa());
            statement.executeUpdate();

            String sqlPessoaFisica = "UPDATE dbo.PessoaFisica SET cpf = ? WHERE idPessoa = ?";
            statement = connection.prepareStatement(sqlPessoaFisica);
            statement.setString(1, pessoa.getCpf());
            statement.setInt(2, pessoa.getIdPessoa());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(statement, null, connection);
        }
    }

    public void excluir(PessoaFisica pessoa) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConectorBD.getConnection();
            String sqlPessoaFisica = "DELETE FROM dbo.PessoaFisica WHERE idPessoa = ?";
            statement = connection.prepareStatement(sqlPessoaFisica);
            statement.setInt(1, pessoa.getIdPessoa());
            statement.executeUpdate();

            String sqlPessoa = "DELETE FROM dbo.Pessoa WHERE idPessoa = ?";
            statement = connection.prepareStatement(sqlPessoa);
            statement.setInt(1, pessoa.getIdPessoa());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(statement, null, connection);
        }
    }
}
