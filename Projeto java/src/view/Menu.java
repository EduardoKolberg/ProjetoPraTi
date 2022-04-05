package view;

import controller.AlunoController;
import controller.PessoaController;
import model.Aluno;
import model.Pessoa;
import util.Read;


import java.time.LocalDate;

public class Menu {

    private final PessoaController pessoaController = new PessoaController();
    private final AlunoController alunoController = new AlunoController();

    public boolean menuInicial(){
        System.out.println("Bem vindo!");
        System.out.println("Digite 1 para novo cadastro, 2 para listar todos os cadastros, 3 para editar, 4 para excluir e 0 para sair: ");
        int opcao = Read.readInt();
        Read.comeLinha();

        if (opcao == 0) {
            System.out.println("Obrigado e volte sempre!");
            return false;
        }

        else if (opcao == 1) {
            return menuCadastro();
        }

        else if (opcao == 2){
            System.out.println("==================================================================================================================================================================================");
            System.out.println("                                                            Pessoas cadastradas: ");
            System.out.println(pessoaController.listPessoas());
            System.out.println("==================================================================================================================================================================================");
            System.out.println("                                                             Alunos cadastrados: ");
            System.out.println(alunoController.listAlunos());
            System.out.println("==================================================================================================================================================================================");
        }

        else if (opcao == 3){
            return menuEditar();
        }

        else if (opcao == 4){
            return menuExcluir();
        }
        else System.out.println("Opção inválida!");

        return true;
    }

    public boolean menuCadastro(){

        System.out.println("Digite o nome completo do aluno/pessoa no seguinte formato Nome Sobrenome: ");
        String nome = pessoaController.validaNome();

        System.out.println("Digite o telefone do aluno/pessoa no seguinte formato 99 999999999: ");
        String telefone = pessoaController.validaTelefone();

        if (pessoaController.pessoaVerificaExistencia(telefone)){
            System.out.println("Pessoa/número de telefone já se encontra cadastrado no cadastro de pessoas, caso queira recadastrar como aluno, favor excluir do cadastro existente. ");
            System.out.println();
            return true;
        }
        else if (alunoController.alunoVerificaExistencia(telefone)) {
            System.out.println("A pessoa/número de telefone já se encontra no cadastro de alunos, caso queira recadastrar como pessoa, favor excluir do cadastro existente.");
            System.out.println();
            return true;
        }

        System.out.println("Digite a data de nascimento do aluno/pessoa no formato dd/MM/yyyy: ");
        LocalDate dataNascimento = pessoaController.validaDataNascimento();

        System.out.println("Digite a nota final (0-10) para cadastrar um aluno, caso queira cadastrar uma pessoa não digite nada, apenas aperte enter: ");
        String nota= pessoaController.validaNota();

        if(nota.equals("")){
            pessoaController.cadastrarPessoa(nome,telefone,dataNascimento);
            System.out.println("Pessoa cadastrada com sucesso!");
            System.out.println();
        }
        else {
            double notaFinal = alunoController.validaNota(nota);
            alunoController.cadastrarAluno(nome, telefone, dataNascimento,notaFinal);
            System.out.println("Aluno cadastrado com sucesso!");
            System.out.println();
        }
        return true;
    }

    public boolean menuEditar(){
        System.out.println("Digite 1 para editar pessoas, 2 para alunos ou 0 para voltar:");
        int opcao;
        opcao = Read.readInt();
        Read.comeLinha();
        if (opcao == 0){
            return true;
        }
        else if (opcao == 1){
            System.out.println(pessoaController.listPessoas());
            System.out.println("Digite o telefone da pessoa que deseja editar");
            String telefone = Read.readString();
            Pessoa pessoa = pessoaController.getPessoa(telefone);
            if (pessoa == null){
                System.out.println("Numero inválido!");
                return true;
            }
            System.out.println("Digite o novo Nome ou deixe em branco caso não queira modificar: ");
            String nome = pessoaController.validaNomeEditar();
            if (nome.equals("")){
                nome = pessoa.getNome();
            }
            System.out.println("Digite o novo telefone ou deixe em branco caso não queira modificar: ");
            String telefoneNovo;
            while (true) {
                telefoneNovo = pessoaController.validaTelefoneEditar();
                if (pessoaController.pessoaVerificaExistencia(telefoneNovo)) {
                    System.out.println("A pessoa/número de telefone já se encontra cadastrado no cadastro de pessoas! Tente novamente com outro número");
                } else if (alunoController.alunoVerificaExistencia(telefoneNovo)) {
                    System.out.println("O aluno/número de telefone já se encontra no cadastro de alunos! Tente novamente com outro número");
                }
                else break;
            }
            if (telefoneNovo.equals("")) {
                telefoneNovo = pessoa.getTelefone();
            }

            System.out.println("Digite a nova data de nascimento ou deixe em branco caso não queira modificar: ");
            LocalDate data = pessoaController.validaDataNascimentoEditar();
            if (data == null){
                data = pessoa.getDataNascimento();
            }

            if(pessoaController.editPessoa(pessoa,nome,telefoneNovo,data)) {
                System.out.println("Modificado com sucesso!");
            }
            else System.out.println("Não foi possivel atualizar, tente novamente!");
        }
        else if (opcao == 2){
            System.out.println(alunoController.listAlunos());
            System.out.println("Digite o telefone do aluno que deseja editar");
            String telefone = Read.readString();
            Aluno aluno = alunoController.getAluno(telefone);
            if (aluno == null){
                System.out.println("Numero inválido!");
                return true;
            }
            System.out.println("Digite o novo Nome ou deixe em branco caso não queira modificar: ");
            String nome = alunoController.validaNomeEditar();
            if (nome.equals("")){
                nome = aluno.getNome();
            }
            System.out.println("Digite o novo telefone ou deixe em branco caso não queira modificar: ");
            String telefoneNovo;
            while (true) {
                telefoneNovo = alunoController.validaTelefoneEditar();
                if (pessoaController.pessoaVerificaExistencia(telefoneNovo)) {
                    System.out.println("Pessoa/número de telefone já se encontra cadastrado no cadastro de pessoas! Tente novamente com outro número");
                } else if (alunoController.alunoVerificaExistencia(telefoneNovo)) {
                    System.out.println("A pessoa/número de telefone já se encontra no cadastro de alunos! Tente novamente com outro número");
                }
                else break;
            }
            if (telefoneNovo.equals("")){
                telefoneNovo = aluno.getTelefone();
            }

            System.out.println("Digite a nova data de nascimento ou deixe em branco caso não queira modificar: ");
            LocalDate data = alunoController.validaDataNascimentoEditar();
            if (data == null){
                data = aluno.getDataNascimento();
            }
            System.out.println("Digite a nova nota ou deixe em branco caso não queira modificar: ");
            String nota = alunoController.validaNota();
            double notaFinal;
            if(nota.equals("")){
                notaFinal = aluno.getNotaFinal();
            }
            else {
                notaFinal = alunoController.validaNota(nota);
            }

            if(alunoController.editAluno(aluno,nome,telefoneNovo,notaFinal,data)) {
                System.out.println("Modificado com sucesso!");
            }
            else System.out.println("Não foi possivel atualizar, tente novamente!");
        }
        else System.out.println("Opção inválida!");
        return true;
    }

    public boolean menuExcluir(){
        System.out.println("Digite 1 para excluir pessoas, 2 para alunos ou 0 para voltar:");
        int opcao;
        opcao = Read.readInt();
        Read.comeLinha();
        if (opcao == 0){
            return true;
        }
        else if (opcao == 1){
            System.out.println(pessoaController.listPessoas());
            System.out.println("Digite o telefone da pessoa que deseja excluir");
            String telefone = Read.readString();
            if (!pessoaController.pessoaVerificaExistencia(telefone)){
                System.out.println("Não há Pessoa cadastrada com o número informado!");
                return true;
            }
            else {
                pessoaController.removePessoa(telefone);
                System.out.println("Pessoa Excluida com sucesso!");
            }

        }
        else if (opcao == 2){
            System.out.println(alunoController.listAlunos());
            System.out.println("Digite o telefone do aluno que deseja excluir");
            String telefone = Read.readString();
            if(!alunoController.alunoVerificaExistencia(telefone)){
                System.out.println("Não há Aluno cadastrada com o número informado!");
            }
            else {
                alunoController.removeAluno(telefone);
                System.out.println("Aluno excluido com sucesso!");
            }
        }
        else System.out.println("Opção inválida!");
        return true;
    }

}
