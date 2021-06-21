CREATE TABlE entregas(
	id serial,
	pessoa_id bigint not null,
	taxa decimal(10,2) not null,
	status varchar(20) not null,
	data_pedido timestamp not null,
	data_finalizacao timestamp,

	destinatario_nome varchar(60) not null,
	destinatario_logradouro varchar(255) not null,
	destinatario_numero varchar(30) not null,
	destinatario_complemento varchar(60),
	destinatario_bairro varchar(30) not null,

	PRIMARY KEY (id)
);

ALTER TABLE entregas ADD CONSTRAINT fk_entregas_pessoa
FOREIGN KEY (pessoa_id) REFERENCES pessoa (id);