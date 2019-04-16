INSERT INTO Diretor
	VALUES(0001, 'Rua 1', 99990131);

INSERT INTO Diretor
	VALUES(0002, 'Rua 2', 99991242);

INSERT INTO Diretor
	VALUES(0003, 'Rua 3', 99992353);

INSERT INTO Diretor
	VALUES(0004, 'Rua 4', 99993464);

INSERT INTO Diretor
	VALUES(0005, 'Rua 5', 99994575);

INSERT INTO Diretor
	VALUES(0006, 'Rua 6', 99995686);

INSERT INTO Pessoa
	VALUES('login1', 'Nome1', 'Cidade1', 'Professor');

INSERT INTO Pessoa
	VALUES('login2', 'Nome2', 'Cidade2', 'Professor');

INSERT INTO Pessoa
	VALUES('login3', 'Nome3', 'Cidade3', 'Professor');

INSERT INTO Pessoa
	VALUES('login4', 'Nome4', 'Cidade4', 'Aluno');

INSERT INTO Pessoa
	VALUES('login5', 'Nome5', 'Cidade5', 'Aluno');

INSERT INTO Pessoa
	VALUES('login6', 'Nome6', 'Cidade6', 'Aluno');

INSERT INTO ArtistasMusicais
	VALUES(0001, 'NomeArtístico1', 'Rap', 'Brasil');

INSERT INTO ArtistasMusicais
	VALUES(0002, 'NomeArtístico2', 'Rap', 'Brasil');

INSERT INTO ArtistasMusicais
	VALUES(0003, 'NomeArtístico3', 'Rap', 'Brasil');

INSERT INTO ArtistasMusicais
	VALUES(0004, 'NomeArtístico4', 'Rock', 'Canadá');

INSERT INTO ArtistasMusicais
	VALUES(0005, 'NomeArtístico5', 'Rock', 'Canadá');

INSERT INTO ArtistasMusicais
	VALUES(0006, 'NomeArtístico6', 'Rock', 'Canadá');

INSERT INTO Filme
	VALUES(1001, 'Filme1', '2001-03-02', 0001, 0001);

INSERT INTO Filme
	VALUES(1002, 'Filme2', '2002-04-03', 0002,0002);

INSERT INTO Filme
	VALUES(1003, 'Filme3', '2003-05-04', 0003,0003);

INSERT INTO Filme
	VALUES(1004, 'Filme4', '2004-06-05', 0004,0004);

INSERT INTO Filme
	VALUES(1005, 'Filme5', '2005-07-06', 0005,0005);

INSERT INTO Filme
	VALUES(1006, 'Filme6', '2006-08-07', 0006,0006);

INSERT INTO Filme
	VALUES(0001, 'Genero1', NULL, NULL, NULL);

INSERT INTO Filme
	VALUES(0002, 'Genero2', NULL, NULL, NULL);

INSERT INTO Filme
	VALUES(0003, 'Genero3', NULL, NULL, NULL);

INSERT INTO Filme
	VALUES(0004, 'Genero4', NULL, NULL, NULL);

INSERT INTO Filme
	VALUES(0005, 'Genero5', NULL, NULL, NULL);

INSERT INTO Filme
	VALUES(0006, 'Genero6', NULL, NULL, NULL);

INSERT INTO Ator
	VALUES(0001, 5511995431124, 'rua 1', 0001);

INSERT INTO Ator
	VALUES(0002, '5511995432235', 'rua 2', 0002);

INSERT INTO Ator
	VALUES(0003, '5511995433346', 'rua 3', 0003);

INSERT INTO Ator
	VALUES(0004, '5511995434457', 'rua 4', 0004);

INSERT INTO Ator
	VALUES(0005, '5511995435568', 'rua 5', 0005);

INSERT INTO Ator
	VALUES(0006, '5511995436679', 'rua 6', 0006);

INSERT INTO AtorFilme
	VALUES(1001, 0001);

INSERT INTO AtorFilme
	VALUES(1002, 0002);

INSERT INTO AtorFilme
	VALUES(1003, 0003);

INSERT INTO AtorFilme
	VALUES(1004, 0004);

INSERT INTO AtorFilme
	VALUES(1005, 0005);

INSERT INTO AtorFilme
	VALUES(1006, 0006);

INSERT INTO  GostaPessoaFilme
	VALUES('login1', 0001 );

INSERT INTO  GostaPessoaFilme
	VALUES('login2', 0002 );

INSERT INTO  GostaPessoaFilme
	VALUES('login3', 0003 );

INSERT INTO  GostaPessoaFilme
	VALUES('login4', 0004 );

INSERT INTO  GostaPessoaFilme
	VALUES('login5', 0005 );

INSERT INTO  GostaPessoaFilme
	VALUES('login6', 0006 );

INSERT INTO  AvaliaPessoaFilme
	VALUES('login1', 0001, 4);

INSERT INTO  AvaliaPessoaFilme
	VALUES('login2', 0002, 5);

INSERT INTO  AvaliaPessoaFilme
	VALUES('login3', 0003, 6);

INSERT INTO  AvaliaPessoaFilme
	VALUES('login4', 0004, 7);

INSERT INTO  AvaliaPessoaFilme
	VALUES('login5', 0005, 8);

INSERT INTO  AvaliaPessoaFilme
	VALUES('login6', 0006, 9);

INSERT INTO Bloqueia
	VALUES('login1', 'login2');

INSERT INTO Bloqueia
	VALUES('login2', 'login3');

INSERT INTO Bloqueia
	VALUES('login3', 'login4');

INSERT INTO Bloqueia
	VALUES('login4', 'login5');

INSERT INTO Bloqueia
	VALUES('login5', 'login6');

INSERT INTO Bloqueia
	VALUES('login6', 'login1');

INSERT INTO  Musico
	VALUES('44223453414', 'Rap', 'NomeMusico1', '2001-05-04' );

INSERT INTO  Musico
	VALUES('44223453425', 'Rap', 'NomeMusico2', '2002-06-05' );

INSERT INTO  Musico
	VALUES('44223453436', 'Rap', 'NomeMusico3', '2003-07-06' );

INSERT INTO  Musico
	VALUES('44223453447', 'Rock', 'NomeMusico4', '2004-08-07' );

INSERT INTO  Musico
	VALUES('44223453458', 'Rock', 'NomeMusico5', '2005-09-08' );

INSERT INTO  Musico
	VALUES('44223453469', 'Rock', 'NomeMusico6', '2006-10-09' );

INSERT INTO Cantor
	VALUES(0001, '44223453414');

INSERT INTO Cantor
	VALUES(0002, '44223453425');

INSERT INTO Cantor
	VALUES(0003, '44223453436');

INSERT INTO Cantor
	VALUES(0004, '44223453447');

INSERT INTO Cantor
	VALUES(0005, '44223453458');

INSERT INTO Cantor
	VALUES(0006, '44223453469');

INSERT INTO  Banda
	VALUES(0001);

INSERT INTO  Banda
	VALUES(0002);

INSERT INTO  Banda
	VALUES(0003);

INSERT INTO  Banda
	VALUES(0004);

INSERT INTO  Banda
	VALUES(0005);

INSERT INTO  Banda
	VALUES(0006);

INSERT INTO PessoaConhecePessoa
	VALUES('login1','login2');

INSERT INTO PessoaConhecePessoa
	VALUES('login2','login3');

INSERT INTO PessoaConhecePessoa
	VALUES('login3','login4');

INSERT INTO PessoaConhecePessoa
	VALUES('login4','login5');

INSERT INTO PessoaConhecePessoa
	VALUES('login5','login6');

INSERT INTO PessoaConhecePessoa
	VALUES('login6','login1');

INSERT INTO BandaPossuiMusico
	VALUES(0001, '44223453414');

INSERT INTO BandaPossuiMusico
	VALUES(0002, '44223453425');

INSERT INTO BandaPossuiMusico
	VALUES(0003, '44223453436');

INSERT INTO BandaPossuiMusico
	VALUES(0004, '44223453447');

INSERT INTO BandaPossuiMusico
	VALUES(0005, '44223453458');

INSERT INTO BandaPossuiMusico
	VALUES(0006, '44223453469');

INSERT INTO PessoaGostaArtistasMusicais
	VALUES('login1', 0001);

INSERT INTO PessoaGostaArtistasMusicais
	VALUES('login2', 0002);

INSERT INTO PessoaGostaArtistasMusicais
	VALUES('login3', 0003);

INSERT INTO PessoaGostaArtistasMusicais
	VALUES('login4', 0004);

INSERT INTO PessoaGostaArtistasMusicais
	VALUES('login5', 0005);

INSERT INTO PessoaGostaArtistasMusicais
	VALUES('login6', 0006);

INSERT INTO AvaliaPessoaArtistasMusicais
	VALUES('login1', 0001, 4);

INSERT INTO AvaliaPessoaArtistasMusicais
	VALUES('login2', 0002, 5);

INSERT INTO AvaliaPessoaArtistasMusicais
	VALUES('login3', 0003, 6);

INSERT INTO AvaliaPessoaArtistasMusicais
	VALUES('login4', 0004, 7);

INSERT INTO AvaliaPessoaArtistasMusicais
	VALUES('login5', 0005, 8);

INSERT INTO AvaliaPessoaArtistasMusicais
	VALUES('login6', 0006, 9);
