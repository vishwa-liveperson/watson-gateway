package watson.gateway.util;

import java.util.Base64;

public class EncodeDecode {
    public static void main(String[] args) {
//        String encodedString = encode();
//        System.out.println(encodedString);
//        String decodedString = decode(encodedString);
//        System.out.println(decodedString);
//

        System.out.println(encode("2021-07-18"));
        String decode = decode("MjAyMS0wNy0xOA==");
        System.out.println(decode);

    }

    private static String encode(String originalInput) {
        return Base64.getEncoder().encodeToString(originalInput.getBytes());
    }

    public static String decode(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }
}
