package infnet.domain;

import java.io.Serializable;
import java.text.NumberFormat;

public abstract class Funcionario implements Serializable {

    protected long matricula;
    protected String nome;
    protected String cargo;
    protected NumberFormat formatador = NumberFormat.getCurrencyInstance();


    public Funcionario() {
    }

    public Funcionario(long matricula, String nome, String cargo) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public abstract void calcularSalario();

    public void mostrarCabecalho() {
        System.out.println("----- Contracheque -----");
        System.out.println("Nome............" + nome);
        System.out.println("Cargo..........." + cargo);
    }

    public double calcularImposto(double valor) {
        double imposto = 0;
        if (valor > 2000) {
            imposto = valor * 0.15;
        } else if (valor > 3000) {
            imposto = valor * 0.25;
        } else if (valor > 5000) {
            imposto = valor * 0.30;
        }
        return imposto;
    }
}
