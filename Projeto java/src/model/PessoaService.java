package model;

import repository.PessoaRepo;

import java.time.LocalDate;

public class PessoaService {

    private final PessoaRepo pessoaRepo = new PessoaRepo();


    public void cadastrarPessoa(String nome, String telefone, LocalDate dataNascimento){
        Pessoa pessoa = new Pessoa(nome, telefone, dataNascimento);
        pessoaRepo.addPessoa(pessoa);
    }

    public boolean pessoaVerificaExistencia(String telefone){
        return pessoaRepo.pessoaVerificaExistenci(telefone);
    }

    public String listPessoas(){
        return pessoaRepo.listPessoas();
    }

    public Pessoa getPessoa(String telefone){
        return pessoaRepo.getPessoa(telefone);
    }

    public boolean editPessoa(Pessoa pessoa, String nome, String telefone, LocalDate data){
        return pessoaRepo.editPessoa(pessoa, nome, telefone, data);
    }

    public void removePessoa(String telefone){
        pessoaRepo.removePessoa(telefone);
    }
}


