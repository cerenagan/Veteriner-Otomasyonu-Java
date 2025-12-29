package veterinerotomasyonu;

import java.util.Scanner;

public class VeterinerOtomasyonu {

    static int kayitEkle(String[] hayvanAdi, String[] tur, int[] yas, String[] sahip,
                         int sayac, Scanner input) {

        System.out.print("Hayvan Adı: ");
        hayvanAdi[sayac] = input.nextLine();

        System.out.print("Tür: ");
        tur[sayac] = input.nextLine();

        System.out.print("Yaş: ");
        yas[sayac] = input.nextInt();
        input.nextLine();

        System.out.print("Sahip Ad Soyad: ");
        sahip[sayac] = input.nextLine();

        return sayac + 1;
    }

    static void kayitlariListele(String[] hayvanAdi, String[] tur, int[] yas, String[] sahip, int sayac) {
    
        if (sayac == 0) {
            System.out.println("Kayıt yok.");
            return; }
        
        for (int i = 0; i < sayac; i++) {
            System.out.println((i + 1) + ". " + hayvanAdi[i] + " - " + tur[i] +
                               " - " + yas[i] + " yaş - Sahip: " + sahip[i]); }}
        
    static void kayitAra() { }
    static void kayitSil() { }
    static void kayitGuncelle() { }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] hayvanAdi = new String[100];
        String[] tur = new String[100];
        int[] yas = new int[100];
        String[] sahip = new String[100];

        int sayac = 0;

        while (true) {

            System.out.println("\n1- Kayıt Ekle");
            System.out.println("2- Kayıt Listele");
            System.out.println("3- Kayıt Ara");
            System.out.println("4- Kayıt Sil");
            System.out.println("5- Kayıt Güncelle");
            System.out.println("0- Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = input.nextInt();
            input.nextLine();

            if (secim == 1) {
                sayac = kayitEkle(hayvanAdi, tur, yas, sahip, sayac, input);
                
                int hastaNo = (int)(Math.random() * 9000) + 1000;
                int siraNo = (int)(Math.random() * 100) + 1;

                System.out.println("Hasta Numarası: " + hastaNo);
                System.out.println("Sıra Numarası: " + siraNo);
    
                System.out.println("Kayıt eklendi."); } 
            
            else if (secim == 2) {
                kayitlariListele(hayvanAdi, tur, yas, sahip, sayac); } 
            
            else if (secim == 3) {
                kayitAra(); } 
            
            else if (secim == 4) {
                kayitSil(); } 
            
            else if (secim == 5) {
                kayitGuncelle(); } 
            
            else if (secim == 0) {
                System.out.println("Çıkış yapıldı.");
                break; } 
            
            else {
                System.out.println("Geçersiz seçim!"); }
            
        }
    }
}