import java.io.IOException;

enum RomanNumerals {
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
            num = RomanNumerals.valueOf(Character.toString(roman.charAt(i))).getValue();
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
                throw new IOException("//т.к. ограничение максимального значения римских чисел 3999");
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
        p1 = (arab-p3*1000-p2*100)/10;
        p0 = arab%10;
        r = switch (p3) {
            case 3 -> "MMM";
            case 2 -> "MM";
            case 1 -> "M";
            default -> "";
        };
        result = r;
        r = switch (p2) {
            case 9 -> "CM";
            case 8 -> "DCCC";
            case 7 -> "DCC";
            case 6 -> "DC";
            case 5 -> "D";
            case 4 -> "CD";
            case 3 -> "CCC";
            case 2 -> "CC";
            case 1 -> "C";
            default -> "";
        };
        result+=r;
        r = switch (p1) {
            case 9 -> "XC";
            case 8 -> "LXXX";
            case 7 -> "LXX";
            case 6 -> "LX";
            case 5 -> "L";
            case 4 -> "XL";
            case 3 -> "XXX";
            case 2 -> "XX";
            case 1 -> "X";
            default -> "";
        };
        result+=r;
        r = switch (p0) {
            case 9 -> "IX";
            case 8 -> "VIII";
            case 7 -> "VII";
            case 6 -> "VI";
            case 5 -> "V";
            case 4 -> "IV";
            case 3 -> "III";
            case 2 -> "II";
            case 1 -> "I";
            default -> "";
        };
        result+=r;
        return result;
    }
}
