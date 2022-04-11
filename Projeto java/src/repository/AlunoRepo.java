package repository;

import model.Aluno;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepo  {
    private final List<Aluno> alunoCadastro = new ArrayList<>();


    public void addAluno(Aluno aluno) {
        this.alunoCadastro.add(aluno);
    }

    public Aluno getAluno(String telefone){
        for (Aluno aluno : alunoCadastro){
            if (aluno.getTelefone().equals(telefone)){
                return aluno;
            }
        }
        return null;
    }

    public void removeAluno(String telefone){
        alunoCadastro.removeIf(aluno -> aluno.getTelefone().equals(telefone));
    }

    public boolean editAluno(Aluno aluno, String nome, String telefone, double notaFinal, LocalDate dataNascimento){
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setNotaFinal(notaFinal);
        aluno.setDataNascimento(dataNascimento);
        aluno.setDataUltimaAlteracao(LocalDateTime.now());
        return true;
    }

    public String listAlunos(){
        StringBuilder lista = new StringBuilder();
        int i = alunoCadastro.size();
        for (Aluno aluno : alunoCadastro){
            if (i == 1){
                lista.append(aluno.toString()); //Faz com que o último aluno listado não começe uma nova linha.
            }
            else {
                lista.append(aluno.toString()).append("\n");
            }
            i--;
        }
        return lista.toString();
    }

    public boolean alunoVerificaExistencia(String telefone) {
        for (Aluno alunoList : alunoCadastro) {
            if (alunoList.getTelefone().equals(telefone)) { //Verifica se a pessoa já está cadastrada.
                return true;
            }
        }
        return false;
    }
}
