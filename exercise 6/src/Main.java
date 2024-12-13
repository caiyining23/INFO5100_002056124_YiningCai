public class Main {
    public static void main(String[] args) throws Exception {
        String message = "Hello, Bob! This is Alice.";

        // Symmetric Encryption
        SymmetricEncryption symmetric = new SymmetricEncryption();
        String symEncrypted = symmetric.encrypt(message);
        String symDecrypted = symmetric.decrypt(symEncrypted);
        System.out.println("Symmetric Encryption:");
        System.out.println("Encrypted: " + symEncrypted);
        System.out.println("Decrypted: " + symDecrypted);

        // Asymmetric Encryption
        AsymmetricEncryption alice = new AsymmetricEncryption();
        AsymmetricEncryption bob = new AsymmetricEncryption();
        String asymEncrypted = alice.encrypt(message, bob.getKeyPair());
        String asymDecrypted = bob.decrypt(asymEncrypted);
        System.out.println("\nAsymmetric Encryption:");
        System.out.println("Encrypted: " + asymEncrypted);
        System.out.println("Decrypted: " + asymDecrypted);

        // Digital Signature
        DigitalSignature signer = new DigitalSignature();
        String signature = signer.sign(message);
        boolean isValid = signer.verify(message, signature);
        System.out.println("\nDigital Signature:");
        System.out.println("Signature: " + signature);
        System.out.println("Is Signature Valid: " + isValid);
    }
}
