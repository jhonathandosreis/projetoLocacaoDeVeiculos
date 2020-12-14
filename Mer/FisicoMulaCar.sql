CREATE TABLE ufs (
    uf_iden SERIAL PRIMARY KEY,
    uf_nome VARCHAR(10) CONSTRAINT uf_repetida UNIQUE
);

CREATE TABLE marcas (
    mar_iden SERIAL PRIMARY KEY,
    mar_nome VARCHAR(50) CONSTRAINT marca_repetida UNIQUE
);

CREATE TABLE categorias (
    cat_iden SERIAL PRIMARY KEY,
    cat_nome VARCHAR(80) CONSTRAINT categoria_repetida UNIQUE,
    cat_valor_diario_Locacao NUMERIC(15)
);

CREATE TABLE tipos_de_veiculos (
    tve_iden SERIAL PRIMARY KEY,
    tve_nome VARCHAR(80) CONSTRAINT tipo_veiculo_repetido UNIQUE
);

CREATE TABLE cidades (
    cid_iden SERIAL PRIMARY KEY,
    cid_nome VARCHAR(30),
    cid_ufs_iden INTEGER,
    FOREIGN KEY (cid_ufs_iden) REFERENCES ufs (uf_iden)
);

CREATE TABLE enderecos (
    end_iden SERIAL PRIMARY KEY,
    end_rua VARCHAR(100),
    end_numero VARCHAR(20),
    end_logradouro VARCHAR(50),
    end_cep VARCHAR(20) CONSTRAINT cep_repetido UNIQUE,
    end_complemento VARCHAR(200),
    end_cid_iden INTEGER,
    FOREIGN KEY (end_cid_iden) REFERENCES cidades (cid_iden)
);

CREATE TABLE clientes (
    cli_iden SERIAL PRIMARY KEY,
    cli_nome VARCHAR(80),
    cli_telefone VARCHAR(20) CONSTRAINT telefone_repetido UNIQUE,
    cli_email VARCHAR(80) CONSTRAINT email_repetido UNIQUE,
    cli_status VARCHAR(20),
    cli_multa NUMERIC(20),
    cli_tipo VARCHAR (15),
    cli_end_iden INTEGER,
    FOREIGN KEY (cli_end_iden) REFERENCES enderecos (end_iden)
);

CREATE TABLE fotos (
    fot_iden SERIAL PRIMARY KEY,
    fot_caminho VARCHAR(200) CONSTRAINT caminho_repetido UNIQUE
);

CREATE TABLE pessoas_fisicas (
    pfi_iden SERIAL PRIMARY KEY,
    pfi_rg VARCHAR(20) CONSTRAINT rg_repetido UNIQUE,
    pfi_cpf VARCHAR(20) CONSTRAINT cpf_repetido UNIQUE,
    pfi_numero_cnh VARCHAR(20) CONSTRAINT cnh_repetida UNIQUE,
    pfi_categoria_cnh VARCHAR(20),
    pfi_data_validade DATE,
    pfi_cli_iden INTEGER,
    pfi_fot_iden INTEGER,
    FOREIGN KEY (pfi_cli_iden) REFERENCES clientes (cli_iden),
    FOREIGN KEY (pfi_fot_iden) REFERENCES fotos (fot_iden)
);

CREATE TABLE pessoas_juridicas (
    pju_iden SERIAL PRIMARY KEY,
    pju_cnpj VARCHAR(20) CONSTRAINT cpj_repetido UNIQUE,
    pju_nome_fantasia VARCHAR(200) CONSTRAINT nomeFantasia_repetido UNIQUE,
    pju_razao_social VARCHAR(150) CONSTRAINT razaoSocial_repetido UNIQUE,
    pju_cli_iden INTEGER,
    FOREIGN KEY (pju_cli_iden) REFERENCES clientes (cli_iden)
);

CREATE TABLE modelos (
    mod_iden SERIAL PRIMARY KEY,
    mod_nome VARCHAR(100) CONSTRAINT modelo_repetido UNIQUE,
    mod_cat_iden INTEGER,
    mod_mar_iden INTEGER,
    mod_tve_iden INTEGER,
    FOREIGN KEY (mod_cat_iden) REFERENCES categorias (cat_iden),
    FOREIGN KEY (mod_mar_iden) REFERENCES marcas (mar_iden),
    FOREIGN KEY (mod_tve_iden) REFERENCES tipos_de_veiculos (tve_iden)
);

CREATE TABLE veiculos (
    vei_iden SERIAL PRIMARY KEY,
    vei_placa VARCHAR(30) CONSTRAINT placa_repetida UNIQUE,
    vei_km NUMERIC(30),
    vei_renavam VARCHAR(13) CONSTRAINT renavam_repetido UNIQUE,
    vei_status VARCHAR(100),
    vei_observacoes VARCHAR(100),
    vei_preco_compra NUMERIC(10),
    vei_ano_fabricacao INTEGER,
    vei_numero_passageiros INTEGER,
    vei_tipo_combustivel VARCHAR(20),
    vei_capacidade_combustivel INTEGER,
    vei_mod_iden INTEGER,
    FOREIGN KEY (vei_mod_iden) REFERENCES modelos (mod_iden)
);

CREATE TABLE locacoes (
    loc_codigo SERIAL PRIMARY KEY,
    loc_data_locacao DATE,
    loc_data_prevista_devolucao DATE,
    loc_km_inicial NUMERIC(30),
    loc_valor_locacao NUMERIC(50),
    loc_valor_caucao NUMERIC(50),
    loc_valor_seguro NUMERIC(50),
    loc_valor_total_pago NUMERIC(50),
    loc_status VARCHAR(20),
    loc_cli_iden INTEGER,
    loc_vei_iden INTEGER,
    loc_pfi_iden INTEGER,

    FOREIGN KEY (loc_cli_iden) REFERENCES clientes (cli_iden),
    FOREIGN KEY (loc_vei_iden) REFERENCES veiculos (vei_iden),
    FOREIGN KEY (loc_pfi_iden) REFERENCES pessoas_fisicas (pfi_iden)
);

CREATE TABLE devolucoes (
    dev_iden SERIAL PRIMARY KEY,
    dev_data_devolucao DATE,
    dev_km_na_entrega NUMERIC(50),
    dev_multa_por_atraso NUMERIC(50),
    dev_dias_atraso INTEGER,
    dev_diferenca_litros NUMERIC(50),
    dev_multa_por_litros NUMERIC(50),
    dev_valor_total NUMERIC(50),
    dev_multa_transito NUMERIC(50),
    dev_danos_veiculo NUMERIC(50),
    dev_loc_iden INTEGER,
    FOREIGN KEY (dev_loc_iden) REFERENCES locacoes (loc_codigo)
);
 