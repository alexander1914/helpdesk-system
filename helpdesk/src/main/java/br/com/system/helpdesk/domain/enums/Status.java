package br.com.system.helpdesk.domain.enums;
//TODO: Enum -> Para ter enums mais seguro para futuras alteções precisa criar,
// com o codigo e o valor e um construtor
public enum Status {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }
        for (Status x : Status.values()){
            if (codigo.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status inválido");
    }
}
