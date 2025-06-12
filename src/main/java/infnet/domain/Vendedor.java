package infnet.domain;

public class Vendedor extends Mensalista {

    private double comissao;
    private double totalVendas;

    public Vendedor() {
    }

    public Vendedor(long matricula, String nome, double salario, double comissao, double totalVendas) {
        super(matricula, nome, "Vendedor", salario);
        this.comissao = comissao;
        this.totalVendas = totalVendas;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(double totalVendas) {
        this.totalVendas = totalVendas;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
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

    @Override
    public void calcularSalario() {
        super.mostrarCabecalho();
        System.out.println("Salário Bruto..." + formatador.format(salario));
        double comissoes = totalVendas * comissao;
        System.out.println("Comissões......." + formatador.format(comissoes));
        double imposto = calcularImposto(this.salario + comissoes);
        double salarioLiq = this.salario + comissoes - imposto;
        System.out.println("Imposto..........." + formatador.format(imposto));
        System.out.println("Salário Liquido." + formatador.format(salarioLiq));
    }
}
