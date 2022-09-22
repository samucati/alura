import conta
import util
# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    conta = conta.Conta(122, "Nico", 100, 1000.0)
    conta.extrato()
    conta.deposita(100)
    conta.saca(50)
    conta.extrato()

    data = util.Data(21, 11, 2017)
    data.formatada()