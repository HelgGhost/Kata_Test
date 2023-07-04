import java.io.IOException;

public enum RomanNumerals {
    I(1),V(5),X(10),L(50),C(100),D(500),M(1000);
    private int value;
    RomanNumerals(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    static public boolean strInEnum(String string){
        boolean exists = true;
        try {
            valueOf(string);
        } catch (IllegalArgumentException e) {
            exists = false;
        }
        return exists;
    }
    static public int romanToArab(String roman){
        int prev=0,num=0,sum=0;
        for (int i=roman.length()-1;i>=0;i--){
            num = RomanNumerals.valueOf(Character.toString(roman.charAt(i))).value;
            if (num<prev){//Если предыдущее значение меньше текущего то вычитаем
                sum-=num;
            }else{
                sum+=num;
            }
            prev = num;
        }
        return sum;
    }
    static public String arabToRoman(int arab){
        int p3,p2,p1,p0;
        String result,r;
        if (arab > 3999){
            try {
                throw new IOException("//т.к. ограничение максимального числа римских цифр 3999");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (arab <0){
            try {
                throw new IOException("//т.к. в римской системе нет отрицательных чисел");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        p3 = arab/1000;
        p2 = (arab-p3*1000)/100;
        p1 = (arab-p3*1000-p2/100)/10;
        p0 = arab%10;
        switch (p3) {
            case 3:
                r = "MMM";
                break;
            case 2:
                r = "MM";
                break;
            case 1:
                r = "M";
                break;
            default:
                r = "";
                break;
        }
        result = r;
        switch (p2) {
            case 9:
                r = "CM";
                break;
            case 8:
                r = "DCCC";
                break;
            case 7:
                r = "DCC";
                break;
            case 6:
                r = "DC";
                break;
            case 5:
                r = "D";
                break;
            case 4:
                r = "CD";
                break;
            case 3:
                r = "CCC";
                break;
            case 2:
                r = "CC";
                break;
            case 1:
                r = "C";
                break;
            default:
                r = "";
                break;
        }
        result+=r;
        switch (p1) {
            case 9:
                r = "XC";
                break;
            case 8:
                r = "LXXX";
                break;
            case 7:
                r = "LXX";
                break;
            case 6:
                r = "LX";
                break;
            case 5:
                r = "L";
                break;
            case 4:
                r = "XL";
                break;
            case 3:
                r = "XXX";
                break;
            case 2:
                r = "XX";
                break;
            case 1:
                r = "X";
                break;
            default:
                r = "";
                break;
        }
        result+=r;
        switch (p0) {
            case 9:
                r = "IX";
                break;
            case 8:
                r = "VIII";
                break;
            case 7:
                r = "VII";
                break;
            case 6:
                r = "VI";
                break;
            case 5:
                r = "V";
                break;
            case 4:
                r = "IV";
                break;
            case 3:
                r = "III";
                break;
            case 2:
                r = "II";
                break;
            case 1:
                r = "I";
                break;
            default:
                r = "";
                break;
        }
        result+=r;
        return result;
    }
}
