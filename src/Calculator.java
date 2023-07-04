import java.io.IOException;

class Calculator {
    private int a;
    private int b;
    private TypeOfOperand typeOfOperand;
    private char operation;
    Calculator(String string){
        parseString(string);
    }
    private int Calculate(){
        return switch (this.operation) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> 0;
        };
    }

    public String toString(){
        String result;
        if(this.typeOfOperand == TypeOfOperand.ROME){
            result = RomanNumerals.arabToRoman(this.Calculate());
        }
        else{
            result = Integer.toString(this.Calculate());
        }
        return result;
    }
    private void fillTypeOfOperand(String a1,String b1){
        TypeOfOperand typeOfOperandA,typeOfOperandB;
        typeOfOperandA = this.typeOfOperand(a1);
        typeOfOperandB = this.typeOfOperand(b1);
        if (typeOfOperandA==TypeOfOperand.NONE || typeOfOperandB==TypeOfOperand.NONE){
            try {
                throw new IOException("//т.к. по крайней мере один из операндов не является числом");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(typeOfOperandA!=typeOfOperandB){
            try {
                throw new IOException("//т.к. используются одновременно разные системы счисления");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            this.typeOfOperand = typeOfOperandA;
        }
    }

    private void fillIntegers(String a1,String b1){
        if (this.typeOfOperand == TypeOfOperand.ROME){
            this.a = RomanNumerals.romanToArab(a1);
            this.b = RomanNumerals.romanToArab(b1);
        }else{
            this.a = Integer.parseInt(a1);
            this.b = Integer.parseInt(b1);
        }
        if (this.a>10 || this.b>10){
            try {
                throw new IOException("//т.к. числа могут быть не более 10");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void parseString(String string){
        String a1,b1;
        int count=0, place=0;
        char o;
        String operations =  "+-/*";
       string = string.replace(" ","");
        for (int i=0;i<string.length()-1;i++) {
            o = string.charAt(i);
            if(operations.indexOf(o)>=0){
                count++;
                place = i;
                this.operation = o;
            }
        }
        if (count==0){
            try {
                throw new IOException("throws Exception //т.к. строка не является математической операцией");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (count>1){
            try {
                throw new IOException("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        a1 = string.substring(0,place);
        b1 = string.substring(place+1);
        fillTypeOfOperand(a1,b1);
        fillIntegers(a1,b1);
    }

    private TypeOfOperand typeOfOperand(String string){
        TypeOfOperand ret=TypeOfOperand.NONE;
        boolean arab = true,rome = true;
        char[] chars = string.toCharArray();
        for (char o:chars){
            if(RomanNumerals.strInEnum(Character.toString(o))){
                arab = false;//Если есть римская цифра то это не арабские
            }
            if(Character.isDigit(o)){
                rome = false;//Если есть арабская то это не римская
            }
        }
        if (arab&&!rome){
            ret = TypeOfOperand.ARAB;
        }
        if (!arab&&rome){
            ret = TypeOfOperand.ROME;
        }
        return ret;
    }
}
