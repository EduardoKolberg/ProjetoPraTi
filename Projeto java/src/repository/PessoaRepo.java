package repository;

import model.Pessoa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepo {
    private final List<Pessoa> pessoaCadastro = new ArrayList<>();

    public void addPessoa(Pessoa pessoa) {
        this.pessoaCadastro.add(pessoa);
    }

    public Pessoa getPessoa(String telefone) {
        for (Pessoa pessoa : pessoaCadastro) {
            if (pessoa.getTelefone().equals(telefone)) {
                return pessoa;
            }
        }
        return null;
    }

    public void removePessoa(String telefone) {
        pessoaCadastro.removeIf(pessoa -> pessoa.getTelefone().equals(telefone));
    }

    public boolean editPessoa(Pessoa pessoa, String nome, String telefone, LocalDate dataNascimento) {
        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setDataUltimaAlteracao(LocalDateTime.now());
        return true;
    }

    public String listPessoas() {
        StringBuilder lista = new StringBuilder();
        int i = pessoaCadastro.size();
        for (Pessoa pessoa : pessoaCadastro) {
            if (i == 1){
                lista.append(pessoa.toString());
            }
            else {
                lista.append(pessoa.toString()).append("\n");
            }
            i--;
        }
        return lista.toString();
    }

    public boolean pessoaVerificaExistenci(String telefone) {
        for (Pessoa pessoaList : pessoaCadastro) {
            if (pessoaList.getTelefone().equals(telefone)) { //Verifica se a pessoa já está cadastrada.
                return true;
            }
        }
        return false;
    }
}
