public class Rome {
    // результат
    private int res;

    public void setRes(int res) throws exception {

        //Проверка на корректность ответа(может быть только положительное число)
        if(res < 1){
            throw new exception("Результатом работы калькулятора с римсикми" +
                    " цифрами могу быть только положительные числа");

        }
        this.res = res;
    }

    public int getRes() {
        return res;
    }
}
