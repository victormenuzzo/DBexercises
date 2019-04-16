CREATE TABLE Pessoa(
	Login VARCHAR(30) NOT NULL,
	Nome VARCHAR(60) NOT NULL,
	CidNatal VARCHAR(60) NOT NULL,
	Funcao VARCHAR(20) NOT NULL,
	PRIMARY KEY(Login)
);

CREATE TABLE ArtistasMusicais(
	Id INTEGER NOT NULL,
	NomeArtistico VARCHAR(30) NOT NULL,
	Genero VARCHAR(10) NOT NULL,
	Pais VARCHAR(20) NOT NULL,
	PRIMARY KEY(Id)
);

CREATE TABLE Diretor(
	Id INTEGER NOT NULL,
	Endereco VARCHAR(30) NOT NULL,
	Telefone INTEGER NOT NULL,
	PRIMARY KEY(Id)
);

CREATE TABLE Filme(
	Id INTEGER NOT NULL,
	Nome VARCHAR(30) NOT NULL,
	DataLancamento DATE,
	GeneroID INTEGER,
	DiretorID INTEGER,
    PRIMARY KEY(Id),
	FOREIGN KEY(GeneroID)
	    REFERENCES Filme(Id),
    FOREIGN KEY(DiretorId)
        REFERENCES Diretor(Id)
);

CREATE TABLE Ator(
    Id INTEGER NOT NULL,
    Telefone VARCHAR(14) NOT NULL,
    Endereco VARCHAR(40) NOT NULL,
    IdDiretor INTEGER,
    PRIMARY KEY(Id),
    FOREIGN KEY(IdDiretor)
        REFERENCES Diretor(Id)
);

CREATE TABLE AtorFilme(
    IdFilme INTEGER NOT NULL,
    IdDiretor INTEGER NOT NULL,
    PRIMARY KEY(IdFilme, IdDiretor),
    FOREIGN KEY(IdFilme)
        REFERENCES Filme(Id),
    FOREIGN KEY(IdDiretor)
        REFERENCES Diretor(Id)
);


CREATE TABLE GostaPessoaFilme(
    Login VARCHAR(30) NOT NULL,
    IdFilme INTEGER NOT NULL,
    PRIMARY KEY(Login, IdFilme),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(IdFilme)
        REFERENCES Filme(Id)
);

CREATE TABLE AvaliaPessoaFilme(
    Login VARCHAR(30) NOT NULL,
    IdFilme INTEGER NOT NULL,
    Nota INTEGER NOT NULL,
    PRIMARY KEY(Login, IdFilme),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(IdFilme)
        REFERENCES Filme(Id)
);


CREATE TABLE Bloqueia(
    Login VARCHAR(30) NOT NULL,
    LogBloq VARCHAR(30) NOT NULL,
    PRIMARY KEY(Login, LogBloq),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(LogBloq)
        REFERENCES Pessoa(Login)

);

CREATE TABLE Musico(
    CPF VARCHAR(11) NOT NULL,
    EstiloMusical VARCHAR(20) NOT NULL,
    NomeReal VARCHAR(30) NOT NULL,
    DataDeNascimento DATE NOT NULL,
    PRIMARY KEY(CPF)
);

CREATE TABLE Cantor(
    Id INTEGER NOT NULL,
    CPF VARCHAR(11) NOT NULL,
    PRIMARY KEY(Id, CPF),
    FOREIGN KEY(Id)
        REFERENCES ArtistasMusicais(Id),
    FOREIGN KEY(CPF)
        REFERENCES Musico(CPF)
);

CREATE TABLE Banda(
    Id INTEGER NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(Id)
        REFERENCES ArtistasMusicais(Id)
);


CREATE TABLE BandaPossuiMusico(
    Id INTEGER NOT NULL,
    CPF  VARCHAR(11) NOT NULL,
    PRIMARY KEY(Id, CPF),
    FOREIGN KEY(Id)
        REFERENCES Banda(Id),
    FOREIGN KEY(CPF)
        REFERENCES Musico(CPF)
);

CREATE TABLE PessoaConhecePessoa(
    Login VARCHAR(30) NOT NULL,
    LoginConhecido VARCHAR(30) NOT NULL,
    PRIMARY KEY(Login, LoginConhecido),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(LoginConhecido)
        REFERENCES Pessoa(Login)
);

CREATE TABLE PessoaGostaArtistasMusicais(
    Login VARCHAR(30) NOT NULL,
    IdArtista INTEGER NOT NULL,
    PRIMARY KEY(Login, IdArtista),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(IdArtista)
        REFERENCES ArtistasMusicais(Id)
);

CREATE TABLE AvaliaPessoaArtistasMusicais(
    Login VARCHAR(30) NOT NULL,
    IdArtista INTEGER NOT NULL,
    Nota INTEGER NOT NULL,
    PRIMARY KEY(Login, IdArtista),
    FOREIGN KEY(Login)
        REFERENCES Pessoa(Login),
    FOREIGN KEY(IdArtista)
        REFERENCES ArtistasMusicais(Id)
);

