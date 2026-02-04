-- Armazena os tipos de chamados para organização.
CREATE TABLE CATEGORIA (
    ID_Categoria INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Categoria VARCHAR(100) NOT NULL UNIQUE
);

-- Tabela: FIEL
-- Armazena os dados dos usuários que abrem os chamados.
CREATE TABLE FIEL (
    ID_Fiel INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(150) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,
    Paroquia VARCHAR(100),
    Senha text NOT NULL UNIQUE 
);

-- Tabela: DESENVOLVEDOR
-- Armazena os dados dos técnicos responsáveis por resolver os chamados.
CREATE TABLE DESENVOLVEDOR (
    ID_Dev INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(150) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,   
);

-- Tabela: CHAMADO
-- Tabela central que armazena os tickets de suporte.
-- Contém chaves estrangeiras para relacionar com FIEL, DESENVOLVEDOR e CATEGORIA.
CREATE TABLE CHAMADO (
    ID_Chamado INT PRIMARY KEY AUTO_INCREMENT,
    Titulo VARCHAR(200) NOT NULL,
    Descricao TEXT NOT NULL,
    Data_Abertura DATETIME NOT NULL,
    Status VARCHAR(50) NOT NULL, -- Ex: 'Aberto', 'Em Andamento', 'Fechado'
    Prioridade VARCHAR(50), -- Ex: 'Baixa', 'Média', 'Alta'

    -- Chaves Estrangeiras (Implementando os relacionamentos)
    ID_Fiel_FK INT NOT NULL,
    ID_Dev_FK INT, -- Pode ser nulo se o chamado ainda não foi atribuído
    ID_Categoria_FK INT NOT NULL,

    FOREIGN KEY (ID_Fiel_FK) REFERENCES FIEL(ID_Fiel),
    FOREIGN KEY (ID_Dev_FK) REFERENCES DESENVOLVEDOR(ID_Dev),
    FOREIGN KEY (ID_Categoria_FK) REFERENCES CATEGORIA(ID_Categoria)
);

-- Tabela: ATUALIZACAO
-- Armazena o histórico de interações de um chamado.
CREATE TABLE ATUALIZACAO (
    ID_Atualizacao INT PRIMARY KEY AUTO_INCREMENT,
    id_chamado_fk INT NOT NULL,
    devolutiva VARCHAR(150) 
     
    -- Chave Estrangeira para relacionar com o chamado

    FOREIGN KEY (id_chamado_fk) REFERENCES CHAMADO(ID_Chamado)
);



-- DATA MANIPULATION LANGUAGE:
-- Tabela Categoria
INSERT INTO categoria (nome_categoria) VALUES 
('Horários/Dados'),
('Inconsistência de Localização'),
('Informativo'), 
('Navegabilidade (Bug)');

-- Tabela Desenvolvedor
INSERT INTO desenvolvedor (nome, email) VALUES 
('João Pedro', 'jpobrabo@gmail.com'),
('Guilherme Botelho', 'guilhermebotelho@hotmal.com'),
('Miguel Pardini', 'miguelpardini@yahoo.com'),
('Pedro Luis', 'pedrokodder@outlook.com'),
('Davi Cunha', 'davicunha@proton.me'),
('André Luis', 'guilhermebotelho@tutanota.com');

