package infnet.domain;

public class Horista extends Funcionario {

    private double valorHora;
    private long totalHoras;

    public Horista() {
    }

    public Horista(long matricula, String nome, String cargo, double valorHora, long totalHoras) {
        super(matricula, nome, cargo);
        this.valorHora = valorHora;
        this.totalHoras = totalHoras;
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

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public long getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(long totalHoras) {
        this.totalHoras = totalHoras;
    }

    @Override
    public void calcularSalario() {
        super.mostrarCabecalho();
        double salBruto = valorHora * totalHoras;
        System.out.println("Salário Bruto..." + formatador.format(salBruto));
        double imposto = calcularImposto(salBruto);
        double salarioLiq = salBruto - imposto;
        System.out.println("Imposto..........." + formatador.format(imposto));
        System.out.println("Salário Liquido." + formatador.format(salarioLiq));
    }
}
