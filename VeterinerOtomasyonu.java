package veterinerotomasyonu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class VeterinerOtomasyonu {

    static int kayitEkle(String[] hayvanAdi, String[] tur, int[] yas, String[] sahip,
                         int[] hastaNo, int sayac, Scanner input) throws IOException {

        System.out.print("Hayvan Adı: ");
        hayvanAdi[sayac] = input.nextLine();

        System.out.print("Tür: ");
        tur[sayac] = input.nextLine();

        System.out.print("Yaş: ");
        yas[sayac] = input.nextInt();
        input.nextLine();

        System.out.print("Sahip Ad Soyad: ");
        sahip[sayac] = input.nextLine();

        hastaNo[sayac] = (int)(Math.random() * 9000) + 1000;

        FileWriter yazici = new FileWriter("data\\veteriner.txt", true);
        yazici.write(
            hastaNo[sayac] + " - " +
            hayvanAdi[sayac] + " - " +
            tur[sayac] + " - " +
            yas[sayac] + " - " +
            sahip[sayac] + "\n"
        );
        yazici.close();

        System.out.println("Hasta No: " + hastaNo[sayac]);
        System.out.println("Kayıt eklendi.");

        return sayac + 1;
    }

    static void kayitlariListele() throws IOException {
        File dosya = new File("data\\veteriner.txt");

        if (!dosya.exists()) {
            System.out.println("Dosya yok.");
            return;
        }

        Scanner oku = new Scanner(dosya);

        if (!oku.hasNextLine()) {
            System.out.println("Kayıt yok.");
        }

        while (oku.hasNextLine()) {
            System.out.println(oku.nextLine());
        }
        oku.close();
    }

    static void kayitAra(Scanner input) throws IOException {
        System.out.print("Aranacak Hasta No: ");
        String aranan = input.nextLine();

        Scanner oku = new Scanner(new File("data\\veteriner.txt"));
        boolean bulundu = false;

        while (oku.hasNextLine()) {
            String satir = oku.nextLine();
            if (satir.contains(aranan)) {
                System.out.println("Kayıt bulundu:");
                System.out.println(satir);
                bulundu = true;
            }
        }
        oku.close();

        if (!bulundu) {
            System.out.println("Kayıt bulunamadı.");
        }
    }

    static void kayitSil(Scanner input) throws IOException {
        System.out.print("Silinecek Hasta No: ");
        String sil = input.nextLine();

        File dosya = new File("data\\veteriner.txt");
        File gecici = new File("data\\temp.txt");

        Scanner oku = new Scanner(dosya);
        FileWriter yaz = new FileWriter(gecici);

        boolean silindi = false;

        while (oku.hasNextLine()) {
            String satir = oku.nextLine();
            if (!satir.contains(sil)) {
                yaz.write(satir + "\n");
            } else {
                silindi = true;
            }
        }

        oku.close();
        yaz.close();

        dosya.delete();
        gecici.renameTo(dosya);

        if (silindi)
            System.out.println("Kayıt silindi.");
        else
            System.out.println("Kayıt bulunamadı.");
    }

    static void kayitGuncelle(Scanner input) throws IOException {
        System.out.print("Güncellenecek Hasta No: ");
        String no = input.nextLine();

        File dosya = new File("data\\veteriner.txt");
        File gecici = new File("data\\temp.txt");

        Scanner oku = new Scanner(dosya);
        FileWriter yaz = new FileWriter(gecici);

        boolean guncellendi = false;

        while (oku.hasNextLine()) {
            String satir = oku.nextLine();
            if (satir.contains(no)) {
                System.out.print("Yeni Hayvan Adı: ");
                String ad = input.nextLine();
                System.out.print("Yeni Tür: ");
                String tur = input.nextLine();
                System.out.print("Yeni Yaş: ");
                String yas = input.nextLine();
                System.out.print("Yeni Sahip: ");
                String sahip = input.nextLine();

                yaz.write(no + " - " + ad + " - " + tur + " - " + yas + " - " + sahip + "\n");
                guncellendi = true;
            } else {
                yaz.write(satir + "\n");
            }
        }

        oku.close();
        yaz.close();

        dosya.delete();
        gecici.renameTo(dosya);

        if (guncellendi)
            System.out.println("Kayıt güncellendi.");
        else
            System.out.println("Kayıt bulunamadı.");
    }

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        String[] hayvanAdi = new String[100];
        String[] tur = new String[100];
        int[] yas = new int[100];
        String[] sahip = new String[100];
        int[] hastaNo = new int[100];

        int sayac = 0;

        while (true) {
            System.out.println("\n1- Kayıt Ekle");
            System.out.println("2- Kayıt Listele (dosyadan)");
            System.out.println("3- Kayıt Ara");
            System.out.println("4- Kayıt Sil");
            System.out.println("5- Kayıt Güncelle");
            System.out.println("0- Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = input.nextInt();
            input.nextLine();

            if (secim == 1) {
                sayac = kayitEkle(hayvanAdi, tur, yas, sahip, hastaNo, sayac, input);
            } 
            else if (secim == 2) {
                kayitlariListele();
            } 
            else if (secim == 3) {
                kayitAra(input);
            } 
            else if (secim == 4) {
                kayitSil(input);
            } 
            else if (secim == 5) {
                kayitGuncelle(input);
            } 
            else if (secim == 0) {
                System.out.println("Çıkış yapıldı.");
                break;
            } 
            else {
                System.out.println("Geçersiz seçim!");
            }
        }
    }
}