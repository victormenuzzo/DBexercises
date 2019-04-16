CREATE TABLE Pessoa(
	Login VARCHAR(30) NOT NULL,
	Nome VARCHAR(60)  ,
	CidNatal VARCHAR(60)  ,
    Nascimento DATE,
	Funcao VARCHAR(20)  ,
	PRIMARY KEY(Login)
);

CREATE TABLE ArtistasMusicais(
	Id VARCHAR(50) NOT NULL,
	NomeArtistico VARCHAR(30),
	Genero VARCHAR(10),
	Pais VARCHAR(20),
	PRIMARY KEY(Id)
);

CREATE TABLE Diretor(
	Id INTEGER  NOT NULL ,
	Endereco VARCHAR(30)  ,
	Telefone INTEGER  ,
	PRIMARY KEY(Id)
);

CREATE TABLE Filme(
	Id VARCHAR(50)  NOT NULL ,
	Nome VARCHAR(30),
	DataLancamento DATE,
	GeneroID VARCHAR(50),
	DiretorID INTEGER,
    PRIMARY KEY(Id),
	FOREIGN KEY(GeneroID)
	    REFERENCES Filme(Id),
    FOREIGN KEY(DiretorId)
        REFERENCES Diretor(Id)
);

CREATE TABLE Ator(
    Id INTEGER  NOT NULL ,
    Telefone VARCHAR(14)  ,
    Endereco VARCHAR(40)  ,
    IdDiretor INTEGER,
    PRIMARY KEY(Id),
    FOREIGN KEY(IdDiretor)
        REFERENCES Diretor(Id)
);

CREATE TABLE AtorFilme(
    IdFilme VARCHAR(50)  NOT NULL ,
    IdDiretor INTEGER  NOT NULL ,
    PRIMARY KEY(IdFilme, IdDiretor),
    FOREIGN KEY(IdFilme)
        REFERENCES Filme(Id),
    FOREIGN KEY(IdDiretor)
        REFERENCES Diretor(Id)
);


CREATE TABLE GostaPessoaFilme(
    Login VARCHAR(30)   NOT NULL,
    IdFilme VARCHAR(50)   NOT NULL,
    PRIMARY KEY(Login, IdFilme),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(IdFilme)
        REFERENCES Filme(Id)
);

CREATE TABLE AvaliaPessoaFilme(
    Login VARCHAR(30)   NOT NULL,
    IdFilme VARCHAR(50)   NOT NULL,
    Nota INTEGER  NOT NULL ,
    PRIMARY KEY(Login, IdFilme),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(IdFilme)
        REFERENCES Filme(Id)
);


CREATE TABLE Bloqueia(
    Login VARCHAR(30)   NOT NULL,
    LogBloq VARCHAR(30)   NOT NULL,
    PRIMARY KEY(Login, LogBloq),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(LogBloq)
        REFERENCES Pessoa(Login)

);

CREATE TABLE Musico(
    CPF VARCHAR(11)   NOT NULL,
    EstiloMusical VARCHAR(20)  ,
    NomeReal VARCHAR(30)  ,
    DataDeNascimento DATE  ,
    PRIMARY KEY(CPF)
);

CREATE TABLE Cantor(
    Id VARCHAR(50)  NOT NULL ,
    CPF VARCHAR(11)  ,
    PRIMARY KEY(Id, CPF),
    FOREIGN KEY(Id)
        REFERENCES ArtistasMusicais(Id),
    FOREIGN KEY(CPF)
        REFERENCES Musico(CPF)
);

CREATE TABLE Banda(
    Id VARCHAR(50)  NOT NULL ,
    PRIMARY KEY(Id),
    FOREIGN KEY(Id)
        REFERENCES ArtistasMusicais(Id)
);


CREATE TABLE BandaPossuiMusico(
    Id VARCHAR(50)  NOT NULL ,
    CPF  VARCHAR(11)  NOT NULL ,
    PRIMARY KEY(Id, CPF),
    FOREIGN KEY(Id)
        REFERENCES Banda(Id),
    FOREIGN KEY(CPF)
        REFERENCES Musico(CPF)
);

CREATE TABLE PessoaConhecePessoa(
    Login VARCHAR(30)  NOT NULL ,
    LoginConhecido VARCHAR(30)   NOT NULL,
    PRIMARY KEY(Login, LoginConhecido),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(LoginConhecido)
        REFERENCES Pessoa(Login)
);

CREATE TABLE PessoaGostaArtistasMusicais(
    Login VARCHAR(30)  NOT NULL ,
    IdArtista VARCHAR(50)  NOT NULL ,
    PRIMARY KEY(Login, IdArtista),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(IdArtista)
        REFERENCES ArtistasMusicais(Id)
);

CREATE TABLE AvaliaPessoaArtistasMusicais(
    Login VARCHAR(30) NOT NULL  ,
    IdArtista VARCHAR(50)  NOT NULL ,
    Nota INTEGER  NOT NULL ,
    PRIMARY KEY(Login, IdArtista),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(IdArtista)
        REFERENCES ArtistasMusicais(Id)
);
