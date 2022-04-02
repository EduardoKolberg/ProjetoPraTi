package model;

import repository.AlunoRepo;

import java.time.LocalDate;

public class AlunoService {

    private final AlunoRepo alunoRepo = new AlunoRepo();

    public boolean alunoVerificaExistencia(String telefone){
        return alunoRepo.alunoVerificaExistencia(telefone);
    }

    public void cadastrarAluno (String nome, String telefone, LocalDate dataNascimento, double notaFinal){
        Aluno aluno = new Aluno(nome, telefone, dataNascimento, notaFinal);
        alunoRepo.addAluno(aluno);
    }

    public String listAlunos(){
        return alunoRepo.listAlunos();
    }

    public Aluno getAluno(String telefone){
        return alunoRepo.getAluno(telefone);
    }
    public boolean editAluno(Aluno aluno, String nome, String telefone, double notaFinal, LocalDate data){
        return alunoRepo.editAluno(aluno, nome, telefone,  notaFinal, data);
    }

    public void removeAluno(String telefone){
        alunoRepo.removeAluno(telefone);
    }
}
