class Conta:

    def __init__(self, numero, titular, saldo, limite):
        print("Construindo o objeto...{}".format(self))

        self.__numero = numero
        self.__titular = titular
        self.__saldo = saldo
        self.__limite = limite

    def extrato(self):
        print("Saldo {} do titular {}".format(self.__saldo, self.__titular))

    def deposita(self, valor):
        print("Depositando... {}".format(valor))
        self.__saldo += valor

    def saca(self, valor):
        if self.__pode_sacar(valor):
            print("Sacando... {}".format(valor))
            self.__saldo -= valor
        else:
            print("Sem saldo suficiente para saque.")

    def transfere(self, valor, destino):
        self.saca(valor)
        destino.deposita(valor)

    def __pode_sacar(self, valor):
        return valor <= self.__saldo + self.__limite

    @property
    def numero(self):
        return self.__numero

    @property
    def titular(self):
        return self.__titular

    @property
    def saldo(self):
        return self.__saldo

    @property
    def limite(self):
        return self.__limite

    @limite.setter
    def limite(self, limite):
        self.__limite = limite

    @staticmethod
    def codigo_banco():
        return "001"

    @staticmethod
    def codigos_bancos():
        return {'BB': '001', 'CAIXA': '104'}
