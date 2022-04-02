package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Aluno extends Pessoa{
    private double notaFinal;

    public Aluno(String nome, String telefone, LocalDate dataNascimento, double notaFinal ) {
        super(nome, telefone, dataNascimento);
        this.notaFinal = notaFinal;
        this.dataCadastro = LocalDate.now();
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " - Telefone: " + telefone + " - Data de nascimento: " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                " - Nota final: " + notaFinal + " - Data de cadastro: " + dataCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                " - Data ultima alteração: " + dataUltimaAlteracao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
