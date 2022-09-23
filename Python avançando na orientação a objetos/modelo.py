class Programa:
    def __init__(self, nome, ano):
        self._nome = nome
        self._ano = ano
        self._likes = 0

    @property
    def nome(self):
        return self._nome

    @nome.setter
    def nome(self, novo_nome):
        self._nome = novo_nome

    @property
    def likes(self):
        return self._likes

    def dar_like(self):
        self._likes += 1

    def __str__(self):
        return f'{self._nome} - {self._ano} - {self._likes} Likes.'


class Filme(Programa):
    def __init__(self, nome, ano, duracao):
        super().__init__(nome, ano)
        self.__duracao = duracao

    def __str__(self):
        return f'{self._nome} - {self._ano} - {self.__duracao} min. - {self._likes} Likes.'


class Serie(Programa):
    def __init__(self, nome, ano, temporadas):
        super().__init__(nome, ano)
        self.__temporadas = temporadas

    def __str__(self):
        return f'{self._nome} - {self._ano} - {self.__temporadas} temp. - {self._likes} Likes.'


class Playlist:
    def __init__(self, nome, programas):
        self.__nome = nome
        self.__programas = programas

    def __getitem__(self, item):
        return self.__programas[item]

    @property
    def listagem(self):
        return self.__programas

    def __len__(self):
        return len(self.__programas)


vingadores = Filme('vindadores', 2020, 120)
vingadores.dar_like()
atlanta = Serie('atlanta', 2021, 10)

filmes_e_series = [vingadores, atlanta]

playlist_ver_depois = Playlist("ver depois", filmes_e_series)

for programa in playlist_ver_depois:
    print(programa)

print(f'Tamanho da playlist: {len(playlist_ver_depois)}')
