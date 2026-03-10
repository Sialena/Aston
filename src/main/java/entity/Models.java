package entity;

public enum Models {
    MERCEDES("Mercedes"),
    VOLVO("Volvo"),
    MAN("Man"),
    SCANIA("Scania"),
    GILLIG("Gillig"),
    SOLARIS("Solaris"),
    BYD("BYD"),
    NEOPLAN("Neoplan"),
    IRIZAR("Irizar"),
    MITSUBISHI("Mitsubishi"),
    PROTERRA("Proterra"),
    YUTONG("Yutong"),
    CRRC("CRRC"),
    ICARUS("Icarus"),
    PAZ("PAZ"),
    MAZ("MAZ"),
    LIAZ("LIAZ"),
    LAZ("LAZ");

    Models(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }
}
