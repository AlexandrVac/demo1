import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws exception {

        //Массив с доступными операциями
        String[] mass = {"-", "+", "/", "*"};
        int menu = -1;

        String [] mas_arab = {"10","1","2","3","4","5","6","7","8","9","11","12","13","14","15","16","17","18","19","20"};
        String [] mas_rome = {"X","I","II","III","IV","V","VI","VII","VIII","IX","XI","XII","XIII","XIV","XV","XVI",
                "XVII","XVIII","XIX","XX"};

        while (true){

            //Проверка на тип чисел.
            System.out.print("Введите математическое выражение(a   +, -, *, /   b): ");
            String mathematical = new Scanner(System.in).nextLine();
            String[] mat = mathematical.split(" ");
            boolean ar = true, ro = true;

            for (int i = 0; i < mat.length; i++) {

                //Проверяем, есть ли в веденном выражении арабские числа
                for (int j = 0; j < 10; j++) {
                    if (mat[i].contains(String.valueOf(j))){
                        ar = false;
                        break;
                    }
                }
                //Проверяем есть ли в веденном выражении римские числа
                mat[i] = mat[i].toUpperCase();
                if (mat[i].contains("I") || mat[i].contains("X") || mat[i].contains("V")){
                    ro = false;
                }
            }
            //Если арабские
            if(!ar & ro){
                menu = 1;
            }
            //Если римские
            else if (ar & !ro){
                menu = 2;
            }
            //Если и то и то
            else if(!ar & !ro){
                throw new exception("Используются одновременно разные системы счисления!");

            }
            //если ни того, не другого
            else {
                throw new exception("Введено некоректное значение!");

            }

            //арабские числа
            if(menu == 1){
                Arab(mathematical, mass);
            }

            //Римские числа
            else{
                Rom(mas_arab, mas_rome, mathematical, mass);
            }


            System.out.println("0 - завершить программу ");
            if (new Scanner(System.in).nextInt() == 0){
                System.exit(0);
            }
        }
    }



    public static void Arab(String mathematical, String[] mass) throws exception {
        System.out.println("Арабские цифры!");
        Arabic a1 = new Arabic();

        //разбиваем введенное выражение в стринговый массив
        String[] mas_s = mathematical.split(" ");

        //создаем интовый массив
        int[] mas_i = new int[mas_s.length];

        //Проверка на то, что бы все выражение было написано в одну строчку
        linee(mas_s);

        //
        if (mas_s.length > 3){
            throw new exception("Математическое выражение должно состоять из 2 переменных и 1 знака");

        }

        //проверка , целые числа или нет
        iint(mas_s);

        //берем 0 и 2 элементы и преобразуем их в инт (наши а и б)
        for (int i = 0; i < mas_i.length; i += 2) {
            mas_i[i] = Integer.parseInt(mas_s[i]);
        }

        //отправляем наши а и б в класс Arabic для проверки удовлетворения условий
        a1.setNum(mas_i[0]);
        a1.setNum(mas_i[2]);
        int a = mas_i[0];
        int b = mas_i[2];

        //проверяем математический знак на корректность
        cor(mas_s, mass);

        //подсчитываем наше выражение
        switch (mas_s[1]){
            case "+":
                a1.setRes(a + b);
                break;

            case "-":
                a1.setRes(a - b);
                break;

            case "*":
                a1.setRes(a * b);
                break;

            case "/":
                a1.setRes(a / b);
                break;

            default:
                break;
        }

        System.out.println(a1.getRes());
    }




    public static void Rom(String[] mas_arab, String[] mas_rome, String mathematical, String[] mass) throws exception {
        System.out.println("Римские цифры!");
        Arabic a1 = new Arabic();
        Rome r1 = new Rome();

        //разбиваем введенное выражение в стринговый массив
        String[] mas_s = mathematical.split(" ");

        //создаем интовый массив
        int[] mas_i = new int[mas_s.length];

        //Проверка на то, что бы все выражение было написано в одну строчку
        linee(mas_s);

        //
        if (mas_s.length > 3){
            throw new exception("Математическое выражение должно состоять из 2 переменных и 1 знака");

        }

        //переводим все элементы в верхний регистр
        for (int i = 0; i < mas_s.length; i += 2) {
            mas_s[i] = mas_s[i].toUpperCase();
        }

        //преобразуем римсие цифры в арабские
        for (int i = 0; i < mas_s.length; i += 2) {
            for (int j = 0; j < 10; j++) {
                if (mas_s[i].equals(mas_rome[j])){
                    mas_i[i] = Integer.parseInt(mas_arab[j]);
                }
            }
        }

        //отправим наши а и б в класс Rome для проверки удовлетворения условий
        a1.setNum(mas_i[0]);
        a1.setNum(mas_i[2]);
        int a = mas_i[0];
        int b = mas_i[2];

        //проверяем математический знак на корректность
        cor(mas_s, mass);

        //подсчитываем наше выражение
        switch (mas_s[1]){
            case "+":
                r1.setRes(a + b);
                break;

            case "-":
                r1.setRes(a - b);
                break;

            case "*":
                r1.setRes(a * b);
                break;

            case "/":
                r1.setRes(a / b);
                break;

            default:
                break;
        }

        // Вывод результата
        for (int i = 0; i < mas_rome.length; i++) {
            if (String.valueOf(r1.getRes()).equals(mas_arab[i])){
                System.out.println(mas_rome[i]);
                break;
            }
        }
    }




    public static void linee(String[] mas_s) throws exception {
        //Проверка на то, что бы все выражение было написано в одну строчку
        if (mas_s.length < 2){
            throw new exception("Строка не яляется математической операцией!");

        }
    }

    public static void iint(String[] mas_s){
        int indx1_1 = mas_s[0].indexOf(".");
        int indx1_2 = mas_s[0].indexOf(",");
        int indx2_1 = mas_s[2].indexOf(".");
        int indx2_2 = mas_s[2].indexOf(",");
        if (indx1_1 != -1 || indx1_2 != -1 || indx2_1 != -1 || indx2_2 != -1){
            System.out.println("Калькулятор умеет работать только с целыми числами!");
            System.exit(0);
        }
    }

    public static void cor(String[] mas_s, String[] mass) throws exception {
        for (int i = 0; i < mass.length; i++) {
            if (mas_s[1].equals(mass[i])){
                break;
            }

            if (i == 3){
                throw new exception("Введена недоступная операция!");

            }
        }
    }
}
