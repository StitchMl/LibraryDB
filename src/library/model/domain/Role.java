package library.model.domain;

public enum Role {
    ADMINISTRATOR(2),
    LIBRARIAN(1),
    LOGIN(3);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public static Role fromInt(int id) {
        for (Role type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
