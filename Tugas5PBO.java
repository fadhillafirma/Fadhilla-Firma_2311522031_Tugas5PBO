import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Custom exception untuk login gagal
class LoginFailedException extends Exception {
    public LoginFailedException(String message) {
        super(message);
    }
}

// Custom exception untuk input transaksi
class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String message) {
        super(message);
    }
}

// Parent class untuk menangani login
class LoginSystem {
    private final String USERNAME = "kasir";
    private final String PASSWORD = "qwerty";

    public void login(String inputUsername, String inputPassword) throws LoginFailedException {
        if (!inputUsername.equals(USERNAME) || !inputPassword.equals(PASSWORD)) {
            throw new LoginFailedException("Login gagal, silakan coba lagi.");
        }
    }
}

// Child class untuk transaksi
class TransactionSystem extends LoginSystem {
    public void processTransaction() {
        Scanner scanner = new Scanner(System.in);

        try {
            // Menampilkan tanggal dan waktu
            Date date = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

            System.out.println("+-----------------------------------------------------+");
            System.out.println("Selamat Datang di Supermarket Fadimart");
            System.out.println("Tanggal dan Waktu : " + dateFormatter.format(date) + " " + timeFormatter.format(date));
            System.out.println("+-----------------------------------------------------+");

            // Input Nama Kasir
            System.out.print("Nama Kasir: ");
            String namaKasir = scanner.nextLine();

            // Input No Faktur
            System.out.print("No. Faktur: ");
            String noFaktur = scanner.nextLine();
            if (noFaktur.isEmpty()) {
                throw new InvalidTransactionException("No. Faktur tidak boleh kosong!");
            }

            // Input Kode Barang dan detail lainnya
            System.out.print("Kode Barang: ");
            String kodeBarang = scanner.nextLine();

            System.out.print("Nama Barang: ");
            String namaBarang = scanner.nextLine();

            System.out.print("Harga Barang: ");
            double hargaBarang = scanner.nextDouble();

            System.out.print("Jumlah Beli: ");
            int jumlahBeli = scanner.nextInt();
            if (jumlahBeli <= 0) {
                throw new InvalidTransactionException("Jumlah beli tidak boleh kurang dari atau sama dengan 0.");
            }

            // Perhitungan total
            double total = hargaBarang * jumlahBeli;

            // Menampilkan hasil transaksi
            System.out.println("+-----------------------------------------------------+");
            System.out.println("No. Faktur      : " + noFaktur);
            System.out.println("Kode Barang     : " + kodeBarang);
            System.out.println("Nama Barang     : " + namaBarang);
            System.out.println("Harga Barang    : Rp " + hargaBarang);
            System.out.println("Jumlah Beli     : " + jumlahBeli);
            System.out.println("TOTAL           : Rp " + total);
            System.out.println("+-----------------------------------------------------+");
            System.out.println("Kasir           : " + namaKasir);
            System.out.println("+-----------------------------------------------------+");

        } catch (InvalidTransactionException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan input. Silakan ulangi!");
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionSystem transactionSystem = new TransactionSystem();

        // Login process
        try {
            System.out.println("Log in");
            System.out.println("+-----------------------------------------------------+");
            System.out.print("Username: ");
            String inputUsername = scanner.nextLine();
            System.out.print("Password: ");
            String inputPassword = scanner.nextLine();

            // Validasi login
            transactionSystem.login(inputUsername, inputPassword);
            System.out.println("Login berhasil!");

            // Memproses transaksi
            transactionSystem.processTransaction();

        } catch (LoginFailedException e) {
            System.out.println(e.getMessage());
        }
    }
}
