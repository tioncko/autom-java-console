package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.xml.parsers.DocumentBuilderFactory;
import Utils.Objetos.Criptografia;
import Utils.Objetos.ValidCEP;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MetodosUtils {

    public static class Senha {
        public static Criptografia Encrypt(String password) {

            Base64.Encoder encrypt = Base64.getEncoder();
            byte[] request = (password.strip()).getBytes(StandardCharsets.ISO_8859_1);
            String encoded = encrypt.encodeToString(request);
            StringBuilder Char_byte = new StringBuilder(CharLength(encoded, Operation.ENCRYPT));

            String HEX = asciiToHex(new String(Char_byte));

            Base64.Encoder encryptHEX = Base64.getEncoder();
            byte[] requestHEX = (HEX.strip()).getBytes(StandardCharsets.ISO_8859_1);
            String encodedHEX = encryptHEX.encodeToString(requestHEX);
            Criptografia hashcode = new Criptografia();
            hashcode.setEncrypting(encodedHEX);

            return hashcode;
        }

        private static String Decrypt(String hex) {

            Base64.Decoder decryptHEX = Base64.getDecoder();
            byte[] responseHEX = decryptHEX.decode(new String(hex));
            String decodedHEX = new String(responseHEX, StandardCharsets.ISO_8859_1);
            //--
            Base64.Decoder decrypt = Base64.getDecoder();
            String password = hexToASCII(decodedHEX);
            StringBuilder Char_byte = new StringBuilder(CharLength(password, Operation.DECRYPT));

            byte[] response = decrypt.decode(new String(Char_byte));
            String decoded = new String(response, StandardCharsets.ISO_8859_1);

            return decoded;
        }

        private static String asciiToHex(String asciiValue) {

            StringBuilder hex = new StringBuilder();

            char[] chars = asciiValue.toCharArray();
            for (char aChar : chars) {
                hex.append(Integer.toHexString(aChar));
            }

            return hex.toString();
        }

        private static String hexToASCII(String hexValue) {

            StringBuilder output = new StringBuilder();

            for (int i = 0; i < hexValue.length(); i += 2) {
                String str = hexValue.substring(i, i + 2);
                output.append((char) Integer.parseInt(str, 16));
            }

            return output.toString();
        }

        private static String CharLength(String password, Operation operation) {

            StringBuilder charStr = new StringBuilder();

            int MathPass = (int) Math.round(4 * 3.14159265359 * password.length())/password.length();
            switch (operation) {
                case ENCRYPT:
                    for (int i = 0; i < password.length(); i++)
                        charStr.append((char) (password.charAt(i) - (MathPass)));
                    break;
                case DECRYPT:
                    for (int i = 0; i < password.length(); i++)
                        charStr.append((char) (password.charAt(i) + (MathPass)));
                    break;
                default:
                    break;
            }

            return new String(charStr);
        }

        private enum Operation{
            ENCRYPT,
            DECRYPT;
        }
    }

    public static class CEP {
        public static ValidCEP ResponseCEP(String NumCEP, int NumEndereco) {
            ValidCEP novoCEP = new ValidCEP();
            try {
                StringBuffer response = getResponse(NumCEP);

                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response.toString())));
                NodeList errNodes = doc.getElementsByTagName("xmlcep");
                if (errNodes.getLength() > 0) {
                    Element err = (Element) errNodes.item(0);
                    novoCEP.setCEP((NumCEP).strip());
                    novoCEP.setEndereco((err.getElementsByTagName("logradouro").item(0).getTextContent()).strip());
                    novoCEP.setBairro((err.getElementsByTagName("bairro").item(0).getTextContent()).strip());
                    novoCEP.setNum(NumEndereco);
                    novoCEP.setCidade((err.getElementsByTagName("localidade").item(0).getTextContent()).strip());
                    novoCEP.setUF((err.getElementsByTagName("uf").item(0).getTextContent()).strip());
                } else {
                    System.out.printf("Não foi possível retornar com os dados do CEP: %s informado", NumCEP);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return novoCEP;
        }

        private static StringBuffer getResponse(String NumCEP) throws IOException {
            String url = "https://viacep.com.br/ws/" + NumCEP + "/xml/";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));//"ISO-8859-1"));
            StringBuffer response = new StringBuffer();
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine.replace('\u00A0', ' '));
                }
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return response;
        }
    }


}

//#region notes
/*
                System.out.println("tipo_logradouro - " + err.getElementsByTagName("tipo_logradouro").item(0).getTextContent());
                System.out.println("logradouro - " + err.getElementsByTagName("logradouro").item(0).getTextContent());
                System.out.println("bairro - " + err.getElementsByTagName("bairro").item(0).getTextContent());
                System.out.println("cidade - " + err.getElementsByTagName("cidade").item(0).getTextContent());
                System.out.println("uf - " + err.getElementsByTagName("uf").item(0).getTextContent());


webservice de cep - https://viacep.com.br/

            //String url = "http://cep.republicavirtual.com.br/web_cep.php?cep=" + NumCEP + "&formato=xml";

                            NodeList errNodes = doc.getElementsByTagName("webservicecep");
            if (errNodes.getLength() > 0) {
                Element err = (Element) errNodes.item(0);
                novoCEP.setCEP((NumCEP).strip());
                novoCEP.setEndereco((err.getElementsByTagName("tipo_logradouro").item(0).getTextContent() + " "
                        + err.getElementsByTagName("logradouro").item(0).getTextContent()).strip());
                novoCEP.setBairro((err.getElementsByTagName("bairro").item(0).getTextContent()).strip());
                novoCEP.setNum(NumEndereco);
                novoCEP.setCidade((err.getElementsByTagName("cidade").item(0).getTextContent()).strip());
                novoCEP.setUF((err.getElementsByTagName("uf").item(0).getTextContent()).strip());


                ---------------

        String senha = "123456789";
        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        byte messageDigest[] = algorithm.digest(senha.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(messageDigest);

        String tx ="";

        for(byte b : messageDigest) tx += b;
        System.out.println(tx);
        System.out.println(encoded);

        String newcam = null;
        for (int i = 0; i < encoded.length(); i++) newcam += (char) (encoded.charAt(i) - (encoded.length() * encoded.length()));

        MessageDigest algorithm_v2 = MessageDigest.getInstance("SHA-256");
        byte messageDigest_V2[] = algorithm.digest(newcam.getBytes(StandardCharsets.UTF_8));
        String encoded_V2 = Base64.getEncoder().encodeToString(messageDigest_V2);

        System.out.print("\n" + encoded_V2);
        //---------

        Base64.Encoder enc = Base64.getEncoder();
        Base64.Decoder dec = Base64.getDecoder();
        String str = "JorgeDaCapadocia";

        // encode data using BASE64
        String encoded = enc.encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println("encoded value is \t" + encoded);

        // Decode data
        String decoded = new String(dec.decode(encoded));
        System.out.println("decoded value is \t" + decoded);
        System.out.println("original value is \t" + str);

Encryption and Decryption in AES (analisar a viabilidade)
https://howtodoinjava.com/java/java-security/java-aes-encryption-example/

java 6 - 10

import java.io.UnsupportedEncodingException;
import javax.xml.bind.DatatypeConverter;

public class EncodeString64 {
    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "77+9x6s=";
        // encode data using BASE64
        String encoded = DatatypeConverter.printBase64Binary(str.getBytes());
        System.out.println("encoded value is \t" + encoded);

        // Decode data
        String decoded = new String(DatatypeConverter.parseBase64Binary(encoded));
        System.out.println("decoded value is \t" + decoded);

        System.out.println("original value is \t" + str);
    }
}

*/
//#endregion