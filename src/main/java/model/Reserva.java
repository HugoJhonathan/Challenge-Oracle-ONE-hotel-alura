package model;

import java.math.BigDecimal;
import java.util.Date;

public class Reserva {

    private Long id;
    private Date dataEntrada;
    private Date dataSaida;
    private BigDecimal valor;
    private MetodoPagamento formaPagamento;
    private Hospede hospede;

    public Reserva() {
    }

    public Reserva(Long id, Date dataEntrada, Date dataSaida, BigDecimal valor, MetodoPagamento formaPagamento) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public MetodoPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(MetodoPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + dataSaida +
                ", valor=" + valor +
                ", formaPagamento=" + formaPagamento +
                ", hospede=" + hospede +
                '}';
    }
}
