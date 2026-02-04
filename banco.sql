-- Armazena os tipos de chamados para organização.
CREATE TABLE CATEGORIA (
    ID_Categoria INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Nome_Categoria VARCHAR(100) NOT NULL UNIQUE
);

-- Tabela: FIEL
-- Armazena os dados dos usuários que abrem os chamados.
CREATE TABLE FIEL (
    ID_Fiel INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Nome VARCHAR(150) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,
    Paroquia VARCHAR(100)
);

-- Tabela: DESENVOLVEDOR
-- Armazena os dados dos técnicos responsáveis por resolver os chamados.
CREATE TABLE DESENVOLVEDOR (
    ID_Dev INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Nome VARCHAR(150) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,
    Nivel_Especialidade VARCHAR(50)
);

-- Tabela: CHAMADO
-- Tabela central que armazena os tickets de suporte.
CREATE TABLE CHAMADO (
    ID_Chamado INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Titulo VARCHAR(200) NOT NULL,
    Descricao TEXT NOT NULL,
    Data_Abertura TIMESTAMP NOT NULL,
    Status VARCHAR(50) NOT NULL,
    Prioridade VARCHAR(50),

    ID_Fiel_FK INT NOT NULL,
    ID_Dev_FK INT,
    ID_Categoria_FK INT NOT NULL,

    CONSTRAINT fk_fiel
        FOREIGN KEY (ID_Fiel_FK) REFERENCES FIEL(ID_Fiel),

    CONSTRAINT fk_dev
        FOREIGN KEY (ID_Dev_FK) REFERENCES DESENVOLVEDOR(ID_Dev),

    CONSTRAINT fk_categoria
        FOREIGN KEY (ID_Categoria_FK) REFERENCES CATEGORIA(ID_Categoria)
);

-- Tabela: ATUALIZACAO
-- Armazena o histórico de interações de um chamado.
CREATE TABLE ATUALIZACAO (
    ID_Atualizacao INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Descricao_Atualizacao TEXT NOT NULL,
    Data_Hora TIMESTAMP NOT NULL,
    Autor VARCHAR(150) NOT NULL,

    ID_Chamado_FK INT NOT NULL,

    CONSTRAINT fk_chamado
        FOREIGN KEY (ID_Chamado_FK) REFERENCES CHAMADO(ID_Chamado)
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

