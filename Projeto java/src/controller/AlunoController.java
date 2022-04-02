package controller;

import model.Aluno;
import model.AlunoService;

import java.time.LocalDate;


public class AlunoController extends PessoaController {

    private final AlunoService alunoService = new AlunoService();

    public boolean alunoVerificaExistencia(String telefone){
        return alunoService.alunoVerificaExistencia(telefone);
    }

    public double validaNota (String nota){
        return Double.parseDouble(nota);
    }

    public void cadastrarAluno (String nome, String telefone, LocalDate dataNascimento, double notaFinal){
       alunoService.cadastrarAluno(nome, telefone, dataNascimento,notaFinal);
    }

    public String listAlunos(){
        return alunoService.listAlunos();
    }

    public Aluno getAluno(String telefone){
        return alunoService.getAluno(telefone);
    }

    public boolean editAluno(Aluno aluno, String nome, String telefone, double notaFinal, LocalDate data){
        return alunoService.editAluno(aluno, nome, telefone,  notaFinal, data);
    }

    public void removeAluno(String telefone){
        alunoService.removeAluno(telefone);
    }

}
