package model;

import exceptions.MinhaException;

import java.util.Arrays;

public enum MetodoPagamento {

    CREDITO(1, "Cartão de Crédito"),
    DEBITO(2, "Cartão de Débito"),
    DINHEIRO(3, "Dinheiro");

    private Integer id;
    private String nome;

    MetodoPagamento(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public static MetodoPagamento findByName(String name) {
        return Arrays.stream(MetodoPagamento.values())
                .filter(el -> el.getNome().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new MinhaException("Método de pagamento não encontrado"));
    }

    public static MetodoPagamento findById(Integer id) {
        return Arrays.stream(MetodoPagamento.values())
                .filter(el -> el.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MinhaException("Método de pagamento não encontrado"));
    }
}
