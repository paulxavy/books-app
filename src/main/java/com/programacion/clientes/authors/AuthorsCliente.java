package com.programacion.clientes.authors;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class AuthorsCliente {
    @Getter @Setter private Long id;
    @Getter @Setter private String first_name;
    @Getter @Setter private String last_name;
}
