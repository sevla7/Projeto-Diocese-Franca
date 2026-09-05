-- Armazena os tipos de chamados para organização.
CREATE TABLE categoria (
    ID_Categoria INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Nome_Categoria VARCHAR(100) NOT NULL UNIQUE
);

-- Tabela: FIEL
-- Armazena os dados dos usuários que abrem os chamados.
CREATE TABLE fiel (
    id_fiel INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    paroquia VARCHAR(100)
);

-- Tabela: DESENVOLVEDOR
-- Armazena os dados dos técnicos responsáveis por resolver os chamados.
CREATE TABLE desenvolvedor (
    id_dev INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
);

-- Tabela: CHAMADO
-- Tabela central que armazena os tickets de suporte.
CREATE TABLE chamado (
    id_chamado INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    descricao TEXT NOT NULL,
    data_abertura TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    prioridade VARCHAR(50),

    ID_Fiel_FK INT NOT NULL,
    ID_Dev_FK INT,
    ID_Categoria_FK INT NOT NULL,

    CONSTRAINT fk_fiel
        FOREIGN KEY (id_fiel_fk) REFERENCES fiel(id_fiel),

    CONSTRAINT fk_dev
        FOREIGN KEY (id_dev_fk) REFERENCES desenvolvedor(if_Dev),

    CONSTRAINT fk_categoria
        FOREIGN KEY (id_categoria_fk) REFERENCES CATEGORIA(id_categoria)
);

-- Tabela: ATUALIZACAO
-- Armazena o histórico de interações de um chamado.
CREATE TABLE atualizacao (
    id_atualizacao INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descricao_Atualizacao TEXT NOT NULL,
    data_fechamento TIMESTAMP NOT NULL,
    autor VARCHAR(150) NOT NULL,

    id_chamado_fk INT NOT NULL,
    id_dev_fk INT NOT NULL,
    id_fiel_fk INT NOT NULL,
    id_categoria_fk INT NOT NULL,

    CONSTRAINT fk_chamado
        FOREIGN KEY (id_chamado_fk) REFERENCES chamado(id_chamado)
    
    CONSTRAINT fk_dev
        FOREIGN KEY (id_chamado_fk) REFERENCES chamado(id_chamado)
    
    CONSTRAINT fk_chamado
        FOREIGN KEY (id_chamado_fk) REFERENCES chamado(id_chamado)
    
    CONSTRAINT fk_chamado
        FOREIGN KEY (id_chamado_fk) REFERENCES chamado(id_chamado)
);



-- DATA MANIPULATION LANGUAGE:
-- Tabela Categoria
INSERT INTO categoria (nome_categoria) VALUES 
('Horários/Dados'),
('Inconsistência de Localização'),
('Informativo'), 
('Navegabilidade (Bug)'),
('Sugestão de Melhoria'),
('Dúvida Geral/Administrativa');    

-- Tabela Desenvolvedor
INSERT INTO desenvolvedor (nome, email) VALUES 
('João Pedro', 'jpobrabo@gmail.com'),
('Guilherme Botelho', 'guilhermebotelho@hotmal.com'),
('Miguel Pardini', 'miguelpardini@yahoo.com'),
('Pedro Luis', 'pedrokodder@outlook.com'),
('Davi Cunha', 'davicunha@proton.me'),
('André Luis', 'guilhermebotelho@tutanota.com');

