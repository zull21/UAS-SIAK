package siak;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author StickerMan
 */
import java.sql.DriverManager;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.List;
import java.util.Scanner;
import model.Mahasiswa;
import model.Matkul;
import model.Nilai;
import service.ServiceJdbc;
public class Siak {
    private static String confTimeZone = "serverTimezone=UTC";
    private static String url = "jdbc:mysql://localhost:3306/siakdb" + confTimeZone;
    private static String user = "root";
    private static String password = "";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet result;
    private static PreparedStatement ps;
    private static void openDb(){
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeDb(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
		Boolean run = true;
		Scanner x = new Scanner(System.in);
		while (menuutama) {
			System.out.println("\nProgram Sistem Informasi Akademik");
			System.out.println("\n1. Input Data Mahasiswa");
			System.out.println("2. Input Data Mata Kuliah");
			System.out.println("3. Input Nilai");
			System.out.println("4. Cetak Nilai");
			System.out.println("5. Keluar");
			System.out.print("\nPilih menu : ");
                        switch(Integer.parseInt(menuutama)) {
                case 1:
                    getMnNilai(service);
                    break;
                case 2:
                    getMnMtk(service);
                    break;
                case 3:
                    getMnNilai();
                    break;
                default:
                    break;
            }

			int choice;
			try {
				choice = Integer.parseInt(x.nextLine());
			} catch (NumberFormatException nfe) { 
				choice = 0;
			}
public static void getMnNilai(ServiceJdbc service) {
        Boolean mhsmenu = true;
        Scanner in = new Scanner(System.in);
        while (mhsmenu) {
            System.out.println("\nMenu Nilai");
            System.out.println("\n1. Cetak Nilai");
            System.out.println("2. Tambah Nilai");
            System.out.println("3. Ubah Nilai");
            System.out.println("4. Hapus Nilai");
            System.out.print("\nMasukkan pilihan : ");
            String pilihannilai = in.nextLine();
            switch (Integer.parseInt(pilihannilai)) {
                case 1:
                    List<Nilai> nilaiX = service.getAllNilai();
                    if (nilaiX.isEmpty()) {
                        System.out.println("\nBelum ada Nilai yang terdaftar!");
                        break;
                    }
                    System.out.println("NIM \t | Kode Matkul \t\t\t | Nilai \t\t\t | ");
                    System.out.println("=============================================================================================");
                    for (Nilai mhs : nilaiX) {
                        System.out.println(nilai.getNim() + "\t | " + nilai.getNama() + " \t | " + nilai.kd_mk() + " | "+ nilai.nilai() ;
                    }
                    break;
                case 2:
                    System.out.print("NIM : ");
                    String npm = in.nextLine();
                    System.out.print("Kode Matkul : ");                    
                    String kd_mk = in.nextLine();
                    System.out.print("Nilai : ");                    
                    String nilai = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                        Nilai mhs = new Nilai();
                        nilai.setNim(Integer.parseInt(npm));
                        nilai.setKd_Mk(kd_mk);
                        nilai.setNilai(nilai);
                        service.save(mhs);
                    }
                default:
                    break;
            }

        }
    }
}
}
