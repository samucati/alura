from abc import ABCMeta, abstractmethod, ABC


# Apenas com isso, podemos garantir que o __str__ será implementado nas nossas subclasses,
# se não for implementado em alguma, será avisado em tempo de instanciação (não vai conseguir criar instâncias).

class Programa(metaclass=ABCMeta):
    @abstractmethod
    def __str__(self):
        pass


class PlayList(Programa, ABC):
    pass
    # def __str__(self):
    #    return 'implementei'


lista = PlayList()
print(lista)
