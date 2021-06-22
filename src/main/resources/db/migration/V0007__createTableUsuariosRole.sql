CREATE TABLE role_usuario(
    usuario_id bigint not null,
    role_nome_role varchar(45) not null
);

ALTER TABLE role_usuario ADD CONSTRAINT fk_usuario
FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE role_usuario ADD CONSTRAINT fk_role
FOREIGN KEY (role_nome_role) REFERENCES role (nome_role);

