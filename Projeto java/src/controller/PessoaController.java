package controller;

import model.Pessoa;
import model.PessoaService;
import util.Read;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PessoaController {

    private final PessoaService pessoaService = new PessoaService();

    public String validaTelefone (){
        String telefone = Read.readString();
        telefone = normalizaTelefone(telefone);
        while(!Pattern.matches("[0-9]{11}",telefone)) {  //Telefone deve ser composto por números, tendo 11 digitos
            System.out.println("telefone inválido digite novamente: ");
            telefone = Read.readString();
           telefone = normalizaTelefone(telefone);
        }
        return telefone;
    }

    public String validaTelefoneEditar (){
        String telefone = Read.readString();
        telefone = normalizaTelefone(telefone);
        while(!Pattern.matches("[0-9]{11}",telefone) && !telefone.equals("")) { //Telefone deve ser composto por números, tendo 11 digitos ou vazio para não ser modificado
            System.out.println("telefone inválido digite novamente: ");
            telefone = Read.readString();
            telefone = normalizaTelefone(telefone);
        }
        return telefone;

    }

    public String validaNome(){
        String nome = Read.readString();
        while(!nome.matches("^[A-Za-z].*")) { //Nome deve ser composto por letras, isso impedi que deixe o nome vazio ou com números
            System.out.println("Nome inválido, deve conter pelo menos uma letra, tente novamente: ");
            nome = Read.readString();
        }
        return nome;
    }

    public String validaNomeEditar(){
        String nome = Read.readString();
        while(!nome.matches("^[A-Za-z].*") && !nome.equals("")) { //Versão que aceita nome como vazio para não alterar o cadastro
            System.out.println("Nome inválido, deve conter pelo menos uma letra, tente novamente: ");
            nome = Read.readString();
        }
        return nome;
    }

    public String normalizaTelefone (String telefone){ // Remove parenteses e espaço em branco para padronizar o telefone.
        Pattern regex = Pattern.compile("[(]*[)]*[\s]*");
        Matcher m = regex.matcher(telefone);
        if (m.find()){
           telefone = telefone.replace("(","").replace(")","").replace(" ","");
        }
        return telefone;
    }

    public LocalDate validaDataNascimento(){
        LocalDate localDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT); //Garante que datas como 31/02 deem erro.
        while (true) {
            String data = Read.readString();
            while (true) {
                try {
                    localDate = LocalDate.parse(data, formatter);
                    break;
                } catch (Exception e) {
                    System.out.println("Data inválida, fovor tentar novamente com uma data válida no formato dd/MM/aaaa: ");
                    System.out.println("Digite uma data no formato dd/MM/aaaa");
                    data = Read.readString();
                }
            }
            if (localDate.isAfter(LocalDate.now())) { //Data de nascimento não pode ser futura
                System.out.println("Data de nascimento não pode ser data futura! Tente novamente: ");
            } else if (localDate.isBefore(LocalDate.now().minusYears(100))){ //Data de nascimento não pode ser mais antiga que 100 anos da data atual.
                System.out.println("Data de nascimento não pode ser mais antiga que 100 anos da data presente! Tente novamente: ");
            }
            else break;
        }
        return localDate;
    }

    public LocalDate validaDataNascimentoEditar(){
        LocalDate localDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        while (true) {
            String data = Read.readString();
            if(data.equals("")) return null; // retorna null para não modificar a data nos casos de edição
            while (true) {
                try {
                    localDate = LocalDate.parse(data, formatter);
                    break;
                } catch (Exception e) {
                    System.out.println("Data inválida, fovor tentar novamente com uma data válida no formato dd/MM/aaaa: ");
                    System.out.println("Digite uma data no formato dd/MM/aaaa");
                    data = Read.readString();
                }
            }
            if (localDate.isAfter(LocalDate.now())) {
                System.out.println("Data de nascimento não pode ser data futura! Tente novamente: ");
            } else if (localDate.isBefore(LocalDate.now().minusYears(100))){
                System.out.println("Data de nascimento não pode ser mais antiga que 100 anos da data presente! Tente novamente: ");
            }
            else break;
        }
        return localDate;
    }


    public void cadastrarPessoa (String nome, String telefone, LocalDate dataNascimento){
        pessoaService.cadastrarPessoa(nome, telefone, dataNascimento);
    }

    public boolean pessoaVerificaExistencia(String telefone){
        return pessoaService.pessoaVerificaExistencia(telefone);
    }

    public String listPessoas() {
        return pessoaService.listPessoas();
    }

    /*Há outra maneira de fazer, mudando o retorno para Double, onde verifica se a String está vazia, retornando Null caso esteja e, caso não esteja, convertendo para Double
    * e fazendo os testes para validar, escolhi já testar como String e usar outra função para transformar em double.
    */
    public String validaNota (){
        String nota = Read.readString();
        while(!Pattern.matches("([0-9][.]*[0-9]*)|10 ",nota) && !nota.equals("")) {
            System.out.println("Nota inválida digite novamente (0-10) ou deixe sem nada para criar uma pessoa: ");
            nota = Read.readString();
        }
        return nota;
    }

    public Pessoa getPessoa (String telefone){
        return pessoaService.getPessoa(telefone);
    }

    public boolean editPessoa(Pessoa pessoa, String nome, String telefone, LocalDate data){
        return pessoaService.editPessoa(pessoa, nome, telefone, data);
    }

    public void removePessoa(String telefone){
        pessoaService.removePessoa(telefone);
    }

}
