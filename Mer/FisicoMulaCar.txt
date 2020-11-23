CREATE TABLE Enderecos (
    end_iden SERIAL PRIMARY KEY,
    end_rua VARCHAR(100),
    end_numero INTEGER,
    end_logradouro VARCHAR(50),
    end_cep INTEGER CONSTRAINT cep_repetido UNIQUE,
    end_complemento VARCHAR(200)
);

CREATE TABLE Cidades (
    cid_iden SERIAL PRIMARY KEY,
    cid_nome VARCHAR(30),
    cid_uf VARCHAR(10) CONSTRAINT uf_repetido UNIQUE
);

CREATE TABLE Marcas (
    mar_iden SERIAL PRIMARY KEY,
    mar_descricao VARCHAR(50)
);

CREATE TABLE Categorias (
    cat_iden SERIAL PRIMARY KEY,
    cat_nome VARCHAR(80) CONSTRAINT categoria_repetida UNIQUE,
    cat_valorDiarioDaLocacao NUMERIC(15)
);

CREATE TABLE Tipo_Veiculos (
    tve_iden SERIAL PRIMARY KEY,
    tve_nome VARCHAR(80) CONSTRAINT tipo_veiculos_repetido UNIQUE
);

CREATE TABLE Clientes (
    cli_iden SERIAL PRIMARY KEY,
    cli_nome VARCHAR(80),
    cli_telefone BIGINT CONSTRAINT telefone_repetido UNIQUE,
    cli_email VARCHAR(80) CONSTRAINT email_repetido UNIQUE,
    cli_end_iden INTEGER,
    FOREIGN KEY (cli_end_iden) REFERENCES Enderecos (end_iden)
);

CREATE TABLE PessoaFisica (
    pfi_iden SERIAL PRIMARY KEY,
    pfi_rg INTEGER CONSTRAINT rg_repetido UNIQUE,
    pfi_cpf BIGINT CONSTRAINT cpf_repetido UNIQUE,
    pfi_numeroCNH BIGINT CONSTRAINT cnh_repetida UNIQUE,
    pfi_categoria VARCHAR(20),
    pfi_dataValidade VARCHAR(30),
    pfi_cli_iden INTEGER,
    FOREIGN KEY (pfi_cli_iden) REFERENCES Clientes (cli_iden)
);

CREATE TABLE Fotos (
	fot_iden SERIAL PRIMARY KEY,
	fot_caminho VARCHAR(200),
	fot_descricao VARCHAR(100),
	fot_pfi_iden INTEGER,
	FOREIGN KEY (fot_pfi_iden) REFERENCES PessoaFisica (pfi_iden)
);

CREATE TABLE PessoaJuridica (
    pju_iden SERIAL PRIMARY KEY,
    pju_cnpj BIGINT CONSTRAINT cpj_repetido UNIQUE,
    pju_nomeFantasia VARCHAR(200) CONSTRAINT nomeFantasia_repetido UNIQUE,
    pju_razaoSocial VARCHAR(150) CONSTRAINT razaoSocial_repetido UNIQUE,
    pju_cli_iden INTEGER,
    FOREIGN KEY (pju_cli_iden) REFERENCES Clientes (cli_iden)
);

CREATE TABLE Modelos (
    mod_iden SERIAL PRIMARY KEY,
    mod_nome VARCHAR(100) CONSTRAINT modelo_repetido UNIQUE,
    mod_descricao VARCHAR(200),
    mod_cat_iden INTEGER,
    mod_mar_iden INTEGER,
    FOREIGN KEY (mod_cat_iden) REFERENCES Categorias (cat_iden),
    FOREIGN KEY (mod_mar_iden) REFERENCES Marcas (mar_iden)
);

CREATE TABLE Veiculos (
    vei_iden SERIAL PRIMARY KEY,
    vei_placa VARCHAR(30) CONSTRAINT placa_repetida UNIQUE,
    vei_km NUMERIC(30),
    vei_anoModelo INTEGER,
    vei_renavam BIGINT CONSTRAINT renavam_repetido UNIQUE,
    vei_status VARCHAR(100),
    vei_precoCompra NUMERIC(10),
    vei_anoFabricacao INTEGER,
    vei_numeroPassageiros INTEGER,
    vei_mod_iden INTEGER,
    vei_tve_iden INTEGER,
    UNIQUE (vei_placa, vei_anoModelo),
    FOREIGN KEY (vei_mod_iden) REFERENCES Modelos (mod_iden),
    FOREIGN KEY (vei_tve_iden) REFERENCES Tipo_Veiculos (tve_iden)
);

CREATE TABLE Locacoes (
    loc_codigo SERIAL PRIMARY KEY,
    loc_dataLocacao VARCHAR(20),
    loc_dataPrevistaLocacao VARCHAR(20),
    loc_kmInicial NUMERIC(30),
    loc_valorDaLocacao NUMERIC(50),
    loc_valorDoCaocao NUMERIC(50),
    loc_valorDoSeguro NUMERIC(50),
    loc_valorTotalPago NUMERIC(50),
    loc_cli_iden INTEGER,
    loc_vei_iden INTEGER,
    FOREIGN KEY (loc_cli_iden) REFERENCES Clientes (cli_iden),
    FOREIGN KEY (loc_vei_iden) REFERENCES Veiculos (vei_iden)
);


CREATE TABLE Reserva (
    res_iden SERIAL PRIMARY KEY,
    res_dataReserva VARCHAR(20),
    res_status VARCHAR(15),
    res_calcao NUMERIC(50),
    res_vei_iden INTEGER,
    res_cli_iden INTEGER,
    FOREIGN KEY (res_cli_iden) REFERENCES Clientes (cli_iden),
    FOREIGN KEY (res_vei_iden) REFERENCES Veiculos (vei_iden)
);

CREATE TABLE Devolução (
    dev_iden SERIAL PRIMARY KEY,
    dev_dataDevolucao VARCHAR(20),
    dev_multaPorAtraso NUMERIC(50),
    dev_status VARCHAR(15),
    dev_kmNaEntrega NUMERIC(50),
    dev_loc_iden INTEGER,
    FOREIGN KEY (dev_loc_iden) REFERENCES Locacoes (loc_codigo)
);
 