//Ömer Yasir Önal 2221221562

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String firstChoice;
        do {
            //Durdurma koşulları sağlanmadığı sürece menü sürekli ekrana gelir.

            System.out.println("Proje-1 Kulanıcı Menüsü\n");
            System.out.print("1. Karakter dizisini çizdir \n");
            System.out.print("2. Büyük harfe çevir\n");
            System.out.print("3. Şifrele ve şifre çöz\n");
            System.out.println("4. Harf çiz\n");
            System.out.print("Lütfen bir seçim yapınız: ");
            firstChoice = scan.next();
            scan.nextLine();

            //Girilen değeri kontrol eder ve değere göre gerekli parametreleri ister ve methodları çağırır.

            if (firstChoice.equals("1")) {
                System.out.println("\nBir dizi karakter  giriniz: ");
                String enteredText = scan.nextLine();
                String firstResult = karakterDizisiCizdir(enteredText);
                System.out.println(firstResult);
            } else if (firstChoice.equals("2")) {
                System.out.print("Cümle giriniz: ");
                String enteredText = scan.nextLine();
                String secondResult = buyukHarfeCevir(enteredText);
                System.out.println("Çıktı: " + secondResult);
            } else if (firstChoice.equals("3")) {
                System.out.print("Bir cümle giriniz: ");
                String enteredText = scan.nextLine();
                System.out.print("Kaydırma değeri giriniz: ");
                int number = scan.nextInt();
                String thirdResult = sifreleCoz(enteredText, number);
                System.out.println("Şifrelenmiş metin: " + thirdResult);
            } else if (firstChoice.equals("4")) {
                //Harf çizdir methotunda 5 ve 5'ten büyük tek sayılar girilmesi gerekir.
                //Bu yüzden bu seçeneğin içine farklı bir while döngüsü eklenir.

                System.out.print("Bir karakter giriniz: ");
                String character = scan.next();
                while (!(character.equals("z") || character.equals("Z") || character.equals("X") || character.equals("x"))) {
                    //While'ın içindekiler dışında hiç bir karakterle çalışmaz.

                    System.out.println("Girilen karakter geçersiz");
                    System.out.print("Bir karakter giriniz: ");
                    character = scan.next();
                }
                System.out.print("Boyut değeri giriniz: ");
                int number = scan.nextInt();
                while ((number % 2 == 0) || !(number >= 5)) {
                    //5'e eşit veya daha büyük bir tek sayı değilse boyut değeri girmesini istemeye devam eder.

                    System.out.println("Geçerli değil");
                    System.out.print("Boyut değeri giriniz: ");
                    number = scan.nextInt();
                }

                harfCizdir(character, number);
            }
            
            //Toplam  8 adet dur varyasyonu vardır.
        } while (!firstChoice.equals("DUR") && !firstChoice.equals("DuR") && !firstChoice.equals("DUr")
                && !firstChoice.equals("dUR") && !firstChoice.equals("duR") && !firstChoice.equals("Dur")
                && !firstChoice.equals("dUr") && !firstChoice.equals("dur"));
    }

    public static String karakterDizisiCizdir(String input) {
        //Girilen karakter dizisindeki her bir harfin kendi görevini, önündeki rakam değeri kadar ve sırasıyla gerçekleştirir.
        //s -> yıldız ("*")
        //b -> boşluk (" ")
        //t -> tab ("   ")
        //n -> yeni satır ("\n")

        String figure = "";

        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {

                if (input.charAt(i + 1) == 's') {
                    for (int j = 0; j < input.charAt(i) - '0'; j++) {
                        figure = figure + "*";
                    }
                } else if (input.charAt(i + 1) == 'b') {
                    for (int j = 0; j < input.charAt(i) - '0'; j++) {
                        figure = figure + " ";
                    }
                } else if (input.charAt(i + 1) == 't') {
                    for (int j = 0; j < input.charAt(i) - '0'; j++) {
                        figure = figure + "   ";
                    }
                } else if (input.charAt(i + 1) == 'n') {
                    for (int j = 0; j < input.charAt(i) - '0'; j++) {
                        figure = figure + "\n";
                    }
                }
                i++;
            } else {

                if (input.charAt(i) == 's') {
                    figure = figure + "*";
                } else if (input.charAt(i) == 'b') {
                    figure = figure + " ";
                } else if (input.charAt(i) == 't') {
                    figure = figure + "   ";
                } else if (input.charAt(i) == 'n') {
                    figure = figure + "\n";
                }
            }
        }
        return figure;
    }

    public static String buyukHarfeCevir(String input) {
        //Cümledeki her kelimenin ilk karakterini büyük yapıyor ve tekrar basıyor.

        String sentence = "";
        int i;
        boolean bos = true; //ilk karakter için

        //tüm harfleri tek tek tarayıp, kelimenin başıysa harfi büyüt
        for (i = 0; i < input.length(); i++) {
            //ilk karakterse ya da öncesinde boşluk varsa harfi büyüt
            if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z' && bos) {
                sentence = sentence + (char) (input.charAt(i) - 32);
                bos = false; //boşluk değil, bir harf
            } else {
                sentence = sentence + input.charAt(i);

                bos = (input.charAt(i) == ' '); //boşluk mu kontrolü.
            }
        }
        return sentence;
    }

    public static String sifreleCoz(String input, int kaydirma_degeri) {
        //Girilen string değerindeki tüm karakterleri büyük karaktere çevirilir.
        //Ardından girilen kaydırma değeri pozitif ise şifreleme, negatif ise şifre çözme işlemi gerçekleştirilir.
        //Stringin her bir karakteri, girilen kaydırma değeri kadar bir sonraki karaktere çevrilir.

        String bigLetter = "";
        String encrypted = "";

        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) <= 'z' && input.charAt(i) >= 'a' && input.charAt(i) != ' ') {
                bigLetter = bigLetter + (char) (input.charAt(i) - 32);
            } else {
                bigLetter = bigLetter + input.charAt(i);
            }
        }

        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) != ' ') {
                if ((bigLetter.charAt(i) + (kaydirma_degeri)) > 'Z') {
                    encrypted = encrypted + (char) (bigLetter.charAt(i) + (kaydirma_degeri) - 26);
                } else if ((bigLetter.charAt(i) + (kaydirma_degeri)) < 'A') {
                    encrypted = encrypted + (char) (bigLetter.charAt(i) + (kaydirma_degeri) + 26);
                } else {
                    encrypted = encrypted + (char) (bigLetter.charAt(i) + (kaydirma_degeri));
                }
            } else {
                encrypted = encrypted + bigLetter.charAt(i);
            }
        }
        return encrypted;
    }

    public static void harfCizdir(String karakter, int boyut) {
        //Methota girilen karakteri, girilen boyut değeri kadar ölçeklendirir ve ekrana yazdırır.

        if (karakter.equals("x") || karakter.equals("X")) {
            //Ekrana X şeklini, girilen boyutun ölçeğinde yıldız karakteri (*) ile yazdırır.

            for (int satir = 0; satir < boyut; satir++) {
                for (int sutun = 0; sutun < boyut; sutun++) {
                    if (satir + sutun == boyut - 1 || satir == sutun) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }

        if (karakter.equals("z") || karakter.equals("Z")) {
            //Ekrana Z şeklini, girilen boyutun ölçeğinde yıldız karakteri (*) ile yazdırır.

            for (int i = 0; i < boyut; i++) {
                System.out.print("*");
            }
            System.out.println();

            for (int a = boyut - 2; a > 0; a--) {
                for (int t = 0; t < a; t++) {
                    System.out.print(" ");
                }
                System.out.println("*");
            }

            for (int i = 0; i < boyut; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}




