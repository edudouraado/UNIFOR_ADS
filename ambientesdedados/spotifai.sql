CREATE SCHEMA Spotifai;
Use Spotifai

CREATE TABLE Usuario (
    id_usuario int not null;
    nome_usuario varchar(100) null;
    email_usuario varchar(100) null;
    senha_usuario varchar(100) null;
    PRIMARY KEY (id_usuario);
);
CREATE TABLE Artista (
    id_artista int not null;
    nome_artista varchar(100) null;
    genero_artista varchar(100) null;
    PRIMARY KEY (id_artista);
);
CREATE TABLE Album (
    id_album int not null;
    nome_album varchar(100) null;
    ano_lancamento int null;
    id_artista int not null;
    PRIMARY KEY (id_album);
    FOREIGN KEY (id_artista) REFERENCES Artista(id_artista);
);
CREATE TABLE Musica (
    id_musica int not null;
    nome_musica varchar(100) null;
    duracao_musica int null;
    id_album int not null;
    PRIMARY KEY (id_musica);
    FOREIGN KEY (id_album) REFERENCES Album(id_album);
);
CREATE TABLE Playlist (
    id_playlist int not null;
    nome_playlist varchar(100) null;
    id_usuario int not null;
    PRIMARY KEY (id_playlist);
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario);
);
CREATE TABLE Playlist_Musica (
    id_playlist int not null;
    id_musica int not null;;
    PRIMARY KEY (id_playlist, id_musica);
    FOREIGN KEY (id_playlist) REFERENCES Playlist(id_playlist);
    FOREIGN KEY (id_musica) REFERENCES Musica(id_musica);
)
CREATE TABLE Avaliacao (
    id_avaliacao int not null;
    id_usuario int not null;
    id_musica int not null;
    nota int null;
    comentario varchar(255) null;
    PRIMARY KEY (id_avaliacao);
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario);
    FOREIGN KEY (id_musica) REFERENCES Musica(id_musica);
);